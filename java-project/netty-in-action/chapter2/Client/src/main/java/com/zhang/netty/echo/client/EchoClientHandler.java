package com.zhang.netty.echo.client;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.CharsetUtil;

public class EchoClientHandler extends SimpleChannelInboundHandler<ByteBuf> {

  @Override
  public void channelActive(ChannelHandlerContext ctx) {
    ctx.writeAndFlush(Unpooled.copiedBuffer("netty rocks!", CharsetUtil.UTF_8));
  }

  @Override
  protected void channelRead0(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf) {
    System.out.println("Client received: " + byteBuf.toString(CharsetUtil.UTF_8));
  }

  @Override
  public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
    cause.printStackTrace();
    ctx.close();
  }
}
