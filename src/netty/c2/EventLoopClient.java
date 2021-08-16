package netty.c2;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;

import java.net.InetSocketAddress;
import java.util.Scanner;

/**
 * @author ZHOU
 * @date 2021/8/7 23:37
 */
public class EventLoopClient {
    public static void main(String[] args) {
        try {
            NioEventLoopGroup group = new NioEventLoopGroup();
            //启动类
            ChannelFuture future = new Bootstrap()
                    //添加EventLoop
                    .group(group)
                    //选择客户端channel实现
                    .channel(NioSocketChannel.class)
                    //添加处理器
                    .handler(new ChannelInitializer<NioSocketChannel>() {
                        //有连接被建立后调用
                        @Override
                        protected void initChannel(NioSocketChannel ch) throws Exception {
                            ch.pipeline().addLast(new StringEncoder());
                        }
                    })


                    //连接端口，到服务器
                    .connect(new InetSocketAddress("localhost", 8080));


            //1.使用sync 方法同步线程，以阻塞的方式进行
            /*future.sync();
            Channel channel = future.channel();
            channel.writeAndFlush("Hello,world");*/

            //2.使用addListener(回调对象)方法异步处理结束
            /*future.addListener(new ChannelFutureListener() {
                @Override
               //在nio线程建立好后，会调用operationComplete
                public void operationComplete(ChannelFuture channelFuture) throws Exception {
                    Channel channel = future.channel();
                    channel.writeAndFlush("Hello,world");
                }
            });*/

            Channel channel = future.sync().channel();

            new Thread(() -> {
                Scanner scanner = new Scanner(System.in);
                while (true) {
                    String s = scanner.nextLine();
                    if ("q".equals(s)) {
                        channel.close();
                        break;
                    }
                    channel.writeAndFlush(s);
                }
            }, "input").start();


            //1)同步线程处理啊关闭之后的操作
            ChannelFuture channelFuture = channel.closeFuture();
          /*  System.out.println("关闭");
            channelFuture.sync();
            System.out.println("关闭之后");*/

            //2)使用异步线的方式处理关闭之后的操作
            channelFuture.addListener((ChannelFutureListener) channelFuture1 -> {
                group.shutdownGracefully();
                System.out.println("关闭之后");
            });
            /*channelFuture.addListener(new GenericFutureListener<Future<? super Void>>() {
                @Override
                public void operationComplete(Future<? super Void> future) throws Exception {

                }
            });*/


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
