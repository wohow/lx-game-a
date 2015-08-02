package cn.xgame.net.event.all.pl.update;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;

import java.io.IOException;

import cn.xgame.a.player.u.Player;
import cn.xgame.net.event.IEvent;
import cn.xgame.net.netty.Netty.RW;
import cn.xgame.utils.Logs;

/**
 * 聊天同步消息
 * @author deng		
 * @date 2015-7-25 上午10:30:52
 */
public class Update_3000 extends IEvent{

	@Override
	public void run(Player player, ByteBuf data) throws IOException {
	}

	/**
	 * 同步聊天信息
	 * @param axnId 频道ID
	 * @param socket 接收人socket
	 * @param sponsor 发起人
	 * @param content 内容
	 */
	public void run(int axnId, ChannelHandlerContext socket, Player sponsor, String content) {
		try {
			ByteBuf response = buildEmptyPackage( socket, 125 );
			
			response.writeInt( axnId );
			RW.writeString( response, sponsor.getUID() );
			RW.writeString( response, sponsor.getNickname() );
			response.writeInt( sponsor.getHeadIco() );
			RW.writeString( response, content );
			
			sendPackage( socket, response );
		} catch (IOException e) {
			Logs.error( "Update_3000 " + e.getMessage() );
		}
	}
}
