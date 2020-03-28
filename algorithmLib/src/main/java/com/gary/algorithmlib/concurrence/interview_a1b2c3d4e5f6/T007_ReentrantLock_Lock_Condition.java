package com.gary.algorithmlib.concurrence.interview_a1b2c3d4e5f6;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


/**
 * @author gary
 *
 * 两个线程交叉打印，一个打印数字，一个打印小写字母，结果为a1b2...y25z26
 *
 * 实现方式7：ReentrantLock（重入锁） + Condition（可指定唤醒特定线程，notify不能指定唤醒特定线程）
 *
 * 说明：借助CountDownLatch，确保先t1->再t2！
 *
 * 特点：一把锁。不同条件下面的锁定。这个的优点是能准确通知哪个线程运行。如果轮到自己。是从wait方法后面执行。
 *
 * */
public class T007_ReentrantLock_Lock_Condition {
    public static void main(String[] args) {
        char[] aI = "123456789".toCharArray();
        char[] aC = "ABCDEFJHI".toCharArray();
        Lock lock = new ReentrantLock();
        //condition是一个队列，有一个condition就有一个队列，好处是指定哪个队列唤醒
        Condition conditionT1 = lock.newCondition();
        Condition conditionT2 = lock.newCondition();
        new Thread(() -> {
            try {
                lock.lock();//锁住，相当于sync
                for (char c : aI) {
                    System.out.println(c);
                    conditionT2.signal();//叫醒conditionT2队列的线程
                    conditionT1.await();
                }
                conditionT2.signal();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }, "t1").start();

        new Thread(() -> {
            try {
                lock.lock();
                for (char c : aC) {
                    System.out.println(c);
                    conditionT1.signal();
                    conditionT2.await();
                }
                conditionT1.signal();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }, "t2").start();
    }
}