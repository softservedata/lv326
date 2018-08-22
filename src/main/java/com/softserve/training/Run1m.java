package com.softserve.training;

public class Run1m implements Runnable {
    public void run() {
        int k;
        System.out.println("- Thread ID = " + Thread.currentThread().getId());
        for (int i = 0; i < 100; i++) {
            //Appl.go(Thread.currentThread().getId());
            synchronized (Appl.class) {
                k = Appl.sum;
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Appl.sum = k - 1;
                System.out.print("-");
            }
        }
        System.out.println(" DONE-, sum=" + Appl.sum);
    }
}
