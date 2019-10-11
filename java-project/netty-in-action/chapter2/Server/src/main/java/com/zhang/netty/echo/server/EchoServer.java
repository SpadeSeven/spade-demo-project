package com.zhang.netty.echo.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import java.net.InetSocketAddress;

public class EchoServer {

  private final int port;

  public EchoServer(int port) {
    this.port = port;
  }

  public static void main(String[] args) throws InterruptedException {
    if (args.length != 1) {
      System.out.println("Usage: " + EchoServer.class.getSimpleName() + " <port>");
      return;
    }

    int port = Integer.parseInt(args[0]);
    new EchoServer(port).start();
  }

  public void start() throws InterruptedException {
    final EchoServerHandler serverHandler = new EchoServerHandler();

    EventLoopGroup group = new NioEventLoopGroup();

    try {
      final ServerBootstrap bootstrap = new ServerBootstrap();
      bootstrap
          .group(group)
          // 指定所使用到NIO传输Channel
          .channel(NioServerSocketChannel.class)
          .localAddress(new InetSocketAddress(port))
          // 添加一个echoServerChannel到ChannelPipeline
          .childHandler(
              new ChannelInitializer<SocketChannel>() {
                @Override
                protected void initChannel(SocketChannel socketChannel) throws Exception {
                  // EchoServerHandler被标注为Shareable，所以我们可以总是使用同样到实例
                  socketChannel.pipeline().addLast(serverHandler);
                }
              });
      ChannelFuture future = bootstrap.bind().sync();
      future.channel().closeFuture().sync();
    } finally {
      group.shutdownGracefully().sync();
    }
  }
}
