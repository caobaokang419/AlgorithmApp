package com.gary.algorithmlib.concurrence.interview_a1b2c3d4e5f6;

import java.util.concurrent.CountDownLatch;

/**
 * @author gary
 *
 * 两个线程交叉打印，一个打印数字，一个打印小写字母，结果为a1b2...y25z26
 *
 * 实现方式6：synchronized（同步锁、互斥锁） + wait（让出锁） +notify（唤醒）
 *
 * 说明：借助CountDownLatch，确保先t1->再t2！
 *
 * CountDownLatch
 * CountDownLatch(int count) //实例化一个倒计数器，count指定计数个数
 * countDown() // 计数减一
 * await() //等待，当计数减到0时，所有线程并行执行
 *
 * */
public class T006_sync_wait_notify_02 {
    //控制两个线程先后：方式1：CountDownLatch
    private static CountDownLatch countDownLatch = new CountDownLatch(1);
    //控制两个线程先后：方式2：volatile变量
    private static volatile boolean t2Started = false;

    public static void main(String[] args) {
        char[] aI = "123456789".toCharArray();
        char[] aC = "ABCDEFJHI".toCharArray();
        final Object o = new Object();

        new Thread(() -> {
            try {
                countDownLatch.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            /*while(!t2Started) {
                try {
                    o.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }*/

            synchronized (o) {
                for (char c : aC) {
                    System.out.println(c);
                    try {
                        o.notify();//叫醒另外一个线程
                        o.wait();//让出锁
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                o.notify();//必须 否则无法停止程序
            }
        }, "t1").start();

        new Thread(() -> {
            synchronized (o) {
                for (char c : aI) {
                    System.out.println(c);
                    countDownLatch.countDown();
                    //t2Started = true;
                    try {
                        o.notify();
                        o.wait();//让出锁
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                o.notify();//必须 否则无法停止程序

            }
        }, "t2").start();
    }
}