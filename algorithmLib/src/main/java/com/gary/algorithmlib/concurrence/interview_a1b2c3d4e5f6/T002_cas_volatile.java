package com.gary.algorithmlib.concurrence.interview_a1b2c3d4e5f6;


/**
 * @author gary
 *
 * 两个线程交叉打印，一个打印数字，一个打印小写字母，结果为a1b2...y25z26
 *
 * 实现方式2：自旋锁（空等待），并借助volatile变量的线程可见性。
 *
 * 模拟自旋锁，自旋就是原地打转。--没轮到它的时候，不放弃cpu。自旋锁有个好处是不经过操作系统
 * 除了自旋锁，扩展信息：
 * 1.偏向锁：只记录线程id
 * 2.自旋锁：线程不放弃cpu。线程可以用wait放弃cpu，进入等待队列里面。应用场景。这段程序速度特别快。空转几遍就轮到他了。
 * 3.重量级锁：并发量大，线程多
 *
 * 内核态，用户态。正常情况下，申请一把锁是从用户态到内核态（操作系统内核）。效率偏低。
 *
 */
public class T002_cas_volatile {
    enum ReadToRun {T1, T2}

    static volatile ReadToRun r = ReadToRun.T1;

    public static void main(String[] args) {
        char[] aI = "123456789".toCharArray();
        char[] aC = "ABCDEFJHI".toCharArray();
        new Thread(() -> {
            for (char c : aI) {
                while (r != ReadToRun.T2) {}
                System.out.println(c);
                r = ReadToRun.T1;
            }
        }, "t1").start();

        new Thread(() -> {
            for (char c : aC) {
                while (r != ReadToRun.T1) {}
                System.out.println(c);
                r = ReadToRun.T2;
            }
        }, "t2").start();
    }
}