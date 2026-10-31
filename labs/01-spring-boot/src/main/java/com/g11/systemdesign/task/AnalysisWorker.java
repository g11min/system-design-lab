package com.g11.systemdesign.task;

import org.springframework.stereotype.Component;

@Component
public class AnalysisWorker {

    private final TaskService taskService;

    public AnalysisWorker(TaskService taskService) {
        this.taskService = taskService;
    }

    public void execute(Long taskId) {

        try {
            System.out.println(
                    "开始分析任务：" + taskId);

            // 模拟 AI 分析耗时 5 秒
            Thread.sleep(5000);

            // 更新数据库状态
            taskService.completeTask(taskId);

            System.out.println(
                    "分析完成：" + taskId);

        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();

            System.err.println(
                    "任务被中断：" + taskId);

        } catch (Exception e) {
            System.err.println(
                    "任务执行失败：" + taskId
                            + "，原因：" + e.getMessage());
        }
    }
}