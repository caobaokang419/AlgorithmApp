package com.gary.algorithmlib.concurrence.interview_a1b2c3d4e5f6;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;


/**
 * @author gary
 *
 * 两个线程交叉打印，一个打印数字，一个打印小写字母，结果为a1b2...y25z26
 *
 * 实现方式4：阻塞队列：线程安全类ArrayBlockingQueue来实现 生产者-消费者模型
 *
 * 特性：杀鸡用牛刀，开拓思路用！
 *
 * */
public class T004_BlockingQueue {

    static BlockingQueue<String> q1 = new ArrayBlockingQueue(1);
    static BlockingQueue<String> q2 = new ArrayBlockingQueue(1);

    public static void main(String[] args) throws Exception {
        char[] aI = "1234567".toCharArray();
        char[] aC = "ABCDEFG".toCharArray();

        new Thread(() -> {
            for (char c : aI) {
                System.out.print(c);
                try {
                    q1.put(" --- t1 ok");
                    //阻塞中，等t2把ok放进来
                    q2.take();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "t1").start();

        new Thread(() -> {
            for (char c : aC) {
                try {
                    //先阻塞,等t1把ok放进来（），就取出 然后往后执行
                    //如当前阻塞队列q1为空队列，则此线程阻塞并一直等待（确保t1先执行!）
                    System.out.println(q1.take());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.print(c);
                try {
                    //把 ok 放到 q2 里
                    q2.put(" --- t2 ok");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "t2").start();
    }
}
