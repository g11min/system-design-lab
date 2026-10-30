public class BankAccountDemo {
    static class BankAccount {
        private int balance = 1000;

        public synchronized boolean withdraw(int amount) {
            if (amount <= 0)
                throw new IllegalArgumentException("amount must be positive");
            if (balance < amount)
                return false;
            balance -= amount;
            return true;
        }

        public synchronized int getBalance() {
            return balance;
        }
    }

    public static void main(String[] args) throws InterruptedException {
        BankAccount account = new BankAccount();
        Runnable withdraw = () -> {
            boolean result = account.withdraw(800);
            System.out.println(Thread.currentThread().getName() + " success=" + result);
        };
        Thread a = new Thread(withdraw, "A");
        Thread b = new Thread(withdraw, "B");
        a.start();
        b.start();
        a.join();
        b.join();
        System.out.println("balance=" + account.getBalance());
    }
}