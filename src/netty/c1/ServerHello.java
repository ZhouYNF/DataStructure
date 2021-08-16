package netty.c1;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.sctp.nio.NioSctpServerChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import jdk.nashorn.internal.runtime.regexp.joni.ast.StringNode;

import java.net.InetSocketAddress;

/**
 * @author ZHOU
 * @date 2021/8/7 14:58
 */
public class ServerHello {
    public static void main(String[] args) {
        //启动器，负责组装netty，启动服务器
        new ServerBootstrap()
                //添加EventLoop,group组
                .group(new NioEventLoopGroup())
                //选择服务器的ServerSocketChannel 实现类
                .channel(NioServerSocketChannel.class)
                //负责处理连接worker(child)负责处理读写，决定worker(child)做什么操作
                .childHandler(
                        //channel代表和客户端的通道进行初始化，负责添加handler
                        new ChannelInitializer<NioSocketChannel>() {
                            @Override
                            //添加处理的handler
                            protected void initChannel(NioSocketChannel ch) throws Exception {
                                ch.pipeline().addLast(new StringDecoder());//将ByteBuf转成字符串
                                ch.pipeline().addLast(new ChannelInboundHandlerAdapter() { //自定义handler
                                    @Override
                                    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
                                        System.out.println(msg);
                                    }
                                });
                            }
                        })
                .bind(new InetSocketAddress(8080));
    }
}
