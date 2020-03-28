package com.gary.algorithmlib.concurrence.interview_a1b2c3d4e5f6;

import java.util.concurrent.locks.LockSupport;


/**
 * @author gary
 *
 * 两个线程交叉打印，一个打印数字，一个打印小写字母，结果为a1b2...y25z26
 *
 * 实现方式1：LockSupport
 *
 */
public class T001_LockSupport {
    static Thread t1 = null, t2 = null;

    public static void main(String[] args) {
        char[] aI = "123456789".toCharArray();
        char[] aC = "ABCDEFJHI".toCharArray();

        t1 = new Thread(() -> {
            for (char c : aI) {
                System.out.println(c);
                LockSupport.unpark(t2);//唤醒t2
                LockSupport.park();//阻塞t1
            }
        }, "t1");

        t2 = new Thread(() -> {
            for (char c : aC) {
                LockSupport.park();//阻塞t2,等着t1叫醒它
                System.out.println(c);
                LockSupport.unpark(t1);//唤醒t1

            }
        }, "t2");

        t1.start();
        t2.start();
    }
}