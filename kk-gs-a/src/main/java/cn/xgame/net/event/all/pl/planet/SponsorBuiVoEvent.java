package cn.xgame.net.event.all.pl.planet;

import io.netty.buffer.ByteBuf;

import java.io.IOException;

import x.javaplus.util.ErrorCode;

import cn.xgame.a.player.u.Player;
import cn.xgame.a.world.WorldManager;
import cn.xgame.a.world.planet.IPlanet;
import cn.xgame.net.event.IEvent;

/**
 * 发起建筑投票 
 * @author deng		
 * @date 2015-7-1 上午10:27:47
 */
public class SponsorBuiVoEvent extends IEvent{

	@Override
	public void run(Player player, ByteBuf data) throws IOException {
		int nid 	= data.readInt();
		byte index 	= data.readByte();
		byte type 	= data.readByte();
		
		ErrorCode code = null;
		
		try {
			// 先将时间转换
			int time = conversionTime( type );
			
			// 获取玩家 母星 - 这里暂时 默认在母星发起投票
			IPlanet planet = WorldManager.o.getHPlanetInPlayer(player);
			
			// 开始发起投票
			planet.sponsorBuivote( player, nid, index, time );
			
			code = ErrorCode.SUCCEED;
		} catch (Exception e) {
			code = ErrorCode.valueOf( e.getMessage() );
		}
		
		ByteBuf response = buildEmptyPackage( player.getCtx(), 2 );
		response.writeShort( code.toNumber() );
		sendPackage( player.getCtx(), response );
	}

	private int conversionTime(byte type) {
		if( type == 1 ){
			return 43200;
		}else if( type == 2 ){
			return 86400;
		}else if( type == 3 ){
			return 172800;
		}
		return 300;
	}

}
