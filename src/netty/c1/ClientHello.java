package netty.c1;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.epoll.EpollEventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringEncoder;

import java.net.InetSocketAddress;

/**
 * @author ZHOU
 * @date 2021/8/7 15:18
 */
public class ClientHello {
    public static void main(String[] args) {
        try {
            //启动类
            new Bootstrap()
                    //添加EventLoop
                    .group(new NioEventLoopGroup())
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
                    .connect(new InetSocketAddress("localhost", 8080))

                    .sync()
                    .channel()
                    .writeAndFlush("Hello,world");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
