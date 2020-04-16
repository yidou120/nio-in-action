package com.edou.nio;

import org.junit.Test;

import java.nio.ByteBuffer;

/**
 * @ClassName TestBuffer
 * @Description Buffer的使用
 * @Author 中森明菜
 * @Date 2020/4/14 15:46
 * @Version 1.0
 * 缓冲区有多种，除了boolean类型外，都有对应类型的缓冲区。
 * Buffer抽象类主要有以下几个变量：
 * 1.capacity 缓冲区的容量
 * 2.limit 操作缓存区数据的界限
 * 3.positon 操作当前数据在缓冲区中的位置
 * 方法：
 * 1.mark 标记当前positon位置
 * 2.reset 恢复到mark标记的位置
 * 3.flip 切换读写操作
 * 4.clear 清空缓冲区,回到最初状态,但是数据还是在的,处于被遗忘状态
 * 5.rewind 重复读,可更改position的位置为0
 * 使用：1.通过allocate()获取对应类型的缓冲区
 * 2.通过put()存储数据
 * 3.通过get()获取数据
 */
public class TestBuffer {
    @Test
    public void testBuffer() {
        String s = "abcde";
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        //写
        buffer.put(s.getBytes());
        System.out.println("-------写-------");
        System.out.println(buffer.position());
        System.out.println(buffer.limit());
        System.out.println(buffer.capacity());

        //切换状态
        buffer.flip();
        System.out.println("-------flip-------");
        System.out.println(buffer.position());
        System.out.println(buffer.limit());
        System.out.println(buffer.capacity());
        //读数据
        byte[] bs = new byte[buffer.limit()];
        buffer.get(bs);
        System.out.println("-------读-------");
        System.out.println(new String(bs, 0, bs.length));
        System.out.println(buffer.position());
        System.out.println(buffer.limit());
        System.out.println(buffer.capacity());
        //重复读
        buffer.rewind();
        System.out.println("-------rewind-------");
        System.out.println(buffer.position());
        System.out.println(buffer.limit());
        System.out.println(buffer.capacity());
        //清空缓冲区
        buffer.clear();
        System.out.println("-------clear-------");
        System.out.println(buffer.position());
        System.out.println(buffer.limit());
        System.out.println(buffer.capacity());
        System.out.println((char) buffer.get());
    }


    @Test
    public void testMark() {
        ByteBuffer buf = ByteBuffer.allocate(1024);
        String s = "abcde";
        //写
        buf.put(s.getBytes());
        //切换状态
        buf.flip();
        //读
        byte[] bs = new byte[1024];
        buf.get(bs, 0, 2);
        System.out.println(new String(bs, 0, 2));
        System.out.println("第一次读:" + buf.position());
        //标记
        buf.mark();
        buf.get(bs, 2, 2);
        System.out.println(new String(bs, 2, 2));
        System.out.println("第二次读:" + buf.position());
        //恢复
        buf.reset();
        System.out.println("重置标记位置之后:" + buf.position());
        //判断缓冲区是否还有剩余数据
        if (buf.hasRemaining()) {
            int remaining = buf.remaining();
            System.out.println(remaining);
        }
    }

    //测试直接缓存区和非直接缓冲区的分配
    @Test
    public void testDirectBuffer(){
        ByteBuffer byteBuffer = ByteBuffer.allocateDirect(1024);
        boolean direct = byteBuffer.isDirect();
        System.out.println(direct);
    }
}
