package week_03.homework_01.gateway;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.PooledByteBufAllocator;
import io.netty.channel.Channel;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import week_03.homework_01.gateway.inbound.HttpInboundInitializer;

import java.util.ArrayList;
import java.util.List;

public class ApiGateway {

    public List<String> proxyServer;

    public ApiGateway() {
        this.proxyServer = new ArrayList<>();
        proxyServer.add("http://localhost:8081");
        proxyServer.add("http://localhost:8082");
    }

    public void run() {
        EventLoopGroup mainRecator = new NioEventLoopGroup(1);
        EventLoopGroup subRecator = new NioEventLoopGroup(4);

        // ServerBootstrap负责初始化netty服务器，并且开始监听端口的socket请求
        ServerBootstrap serverBootstrap = new ServerBootstrap();
        // option针对mainRecator,childOption针对subRecator
        serverBootstrap
                .option(ChannelOption.SO_BACKLOG, 128)
                // 是否开启Nagle算法
                .childOption(ChannelOption.TCP_NODELAY, true)
                .childOption(ChannelOption.SO_KEEPALIVE, true)
                // 允许完全重复捆绑
                .childOption(ChannelOption.SO_REUSEADDR, true)
                // 接收缓冲区大小
                .childOption(ChannelOption.SO_RCVBUF, 32 * 1024)
                // 发送缓冲区大小
                .childOption(ChannelOption.SO_SNDBUF, 32 * 1024)
                // 允许我们将任意数目的socket绑定到完全相同的源地址端口
//                .childOption(EpollChannelOption.SO_REUSEPORT, true)
                // Netty4使用内存池，重用缓冲区
                .childOption(ChannelOption.ALLOCATOR, PooledByteBufAllocator.DEFAULT);
        try{
            serverBootstrap.group(mainRecator, subRecator)
                    .channel(NioServerSocketChannel.class)
                    // handler在初始化时就会执行
                    .handler(new LoggingHandler(LogLevel.DEBUG))
                    // 而childHandler会在客户端成功connect后才执行,所以childHandler需要自定义实现
                    .childHandler(new HttpInboundInitializer(proxyServer));

            Channel ch = serverBootstrap.bind(8080).sync().channel();
            System.out.println("开启netty http服务器，监听地址和端口为 http://127.0.0.1:8080");
            ch.closeFuture().sync();
        }catch (InterruptedException e){
            System.out.println("我中断了");
        }finally {
            mainRecator.shutdownGracefully();
            subRecator.shutdownGracefully();
        }
    }

    public static void main(String[] args) {
        // netty server start to receive incoming connections
        // route back server
        ApiGateway gateway = new ApiGateway();
        gateway.run();
    }
}
