package com.gary.algorithmlib.concurrence.interview_a1b2c3d4e5f6;


/**
 * @author gary
 *
 * 两个线程交叉打印，一个打印数字，一个打印小写字母，结果为a1b2...y25z26
 *
 * 实现方式5：synchronized（同步锁、互斥锁） + wait（让出锁） +notify（唤醒）
 *
 * 说明2：如果想用sychronized,必须是对某个对象加锁,而不是包含的代码。
 *  --notify：叫醒锁定这个对象的等待队列里面的任意一个线程,notifyAll是叫醒所有
 *  --wait：本来还在运行那。wait后就进入等待队列了，同时释放这把锁
 *
 * 说明1：存在缺陷：线程执行顺序不能保证！！！---导致可能打印出来：1a2b...25y26z!!!
 * 为什么先调用了t1.start() ,还不一定就是他先运行？
 * --因为start()后，线程是进入cpu的等待里面,准备好状态。什么时候调用是cpu里面调度的事情了。
 *
 * */
public class T005_sync_wait_notify_01 {
    public static void main(String[] args) {
        char[] aI = "123456789".toCharArray();
        char[] aC = "ABCDEFJHI".toCharArray();
        final Object o = new Object();

        new Thread(() -> {
            synchronized (o) {
                for (char c : aI) {
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
                for (char c : aC) {
                    System.out.println(c);
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