package com.gary.algorithmlib.concurrence.interview_a1b2c3d4e5f6;

import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;


/**
 * @author gary
 *
 * 两个线程交叉打印，一个打印数字，一个打印小写字母，结果为a1b2...y25z26
 *
 * 实现方式8：PipedStream（管道流）：半双工方式通信：
 *
 * （t1.input1 ->t2.output2）+ （t2.input2 ->t1.output1） 实现线程双向通信
 *
 * 缺点：效率低
 */
public class T008_PipedStream {

    public static void main(String[] args) throws Exception {

        char[] aI = "1234567".toCharArray();
        char[] aC = "ABCDEFG".toCharArray();

        PipedInputStream input1 = new PipedInputStream();
        PipedInputStream input2 = new PipedInputStream();
        PipedOutputStream output1 = new PipedOutputStream();
        PipedOutputStream output2 = new PipedOutputStream();

        input1.connect(output2);
        input2.connect(output1);

        String msg = "Your turn";

        new Thread(() -> {
            byte[] buffer = new byte[9];
            try {
                for (char c : aI) {
                    input1.read(buffer);
                    if (new String(buffer).equals(msg)) {
                        System.out.print(c);
                    }
                    output1.write(msg.getBytes());
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }, "t1").start();

        new Thread(() -> {
            byte[] buffer = new byte[9];
            try {
                for (char c : aC) {
                    System.out.print(c);
                    output2.write(msg.getBytes());//写入
                    input2.read(buffer);
                    if (new String(buffer).equals(msg)) {
                        continue;
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }, "t2").start();
    }
}
