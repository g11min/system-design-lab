package com.g11.systemdesign.task;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Semaphore;

@Component
public class TaskScheduler {

    private final TaskService taskService;
    private final AnalysisWorker worker;
    private final ExecutorService taskExecutor;

    // 最多允许 2 个任务在途
    private final Semaphore slots = new Semaphore(2);

    public TaskScheduler(
            TaskService taskService,
            AnalysisWorker worker,
            ExecutorService taskExecutor) {

        this.taskService = taskService;
        this.worker = worker;
        this.taskExecutor = taskExecutor;
    }

    @Scheduled(fixedDelay = 1000)
    public void dispatch() {

        while (slots.tryAcquire()) {

            AnalysisTask task;

            try {
                var claimed = taskService.claimNextTask();

                if (claimed.isEmpty()) {
                    slots.release();
                    break;
                }

                task = claimed.get();

            } catch (Exception e) {
                slots.release();
                System.err.println(
                        "领取任务失败：" + e.getMessage());
                break;
            }

            Long taskId = task.getId();

            try {
                taskExecutor.execute(() -> {
                    try {
                        worker.execute(taskId);
                    } finally {
                        slots.release();
                    }
                });

                System.out.println(
                        "任务已提交到线程池：" + taskId);

            } catch (RuntimeException e) {
                slots.release();
                System.err.println(
                        "提交任务失败：" + taskId
                                + "，原因：" + e.getMessage());
                // 任务已领取但未执行，阶段 D 处理恢复
                break;
            }
        }
    }
}