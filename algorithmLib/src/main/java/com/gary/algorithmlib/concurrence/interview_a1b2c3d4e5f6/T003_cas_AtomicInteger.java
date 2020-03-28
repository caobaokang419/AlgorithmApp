package com.gary.algorithmlib.concurrence.interview_a1b2c3d4e5f6;

import java.util.concurrent.atomic.AtomicInteger;


/**
 * @author gary
 *
 * 两个线程交叉打印，一个打印数字，一个打印小写字母，结果为a1b2...y25z26
 *
 * 实现方式3：自旋锁（空等待）：借助AtomicInteger变量的操作原子性
 *
 * 模拟自旋锁，自旋就是原地打转。--没轮到它的时候，不放弃cpu。自旋锁有个好处是不经过操作系统
 * 除了自旋锁，扩展信息：
 * 1.偏向锁：只记录线程id
 * 2.自旋锁：线程不放弃cpu。线程可以用wait放弃cpu，进入等待队列里面。应用场景。这段程序速度特别快。空转几遍就轮到他了。
 * 3.重量级锁：并发量大，线程多
 *
 * 内核态，用户态。正常情况下，申请一把锁是从用户态到内核态（操作系统内核）。效率偏低。
 */
public class T003_cas_AtomicInteger {
    static AtomicInteger threadNo = new AtomicInteger(1);

    public static void main(String[] args) {
        char[] aI = "1234567".toCharArray();
        char[] aC = "ABCDEFG".toCharArray();

        new Thread(() -> {
            for (char c : aI) {
                while (threadNo.get() != 1) {}
                System.out.print(c);
                threadNo.set(2);
            }

        }, "t1").start();

        new Thread(() -> {
            for (char c : aC) {
                while (threadNo.get() != 2) {}
                System.out.print(c);
                threadNo.set(1);
            }
        }, "t2").start();
    }
}
