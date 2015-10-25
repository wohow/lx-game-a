package cn.xgame.net.event.all.pl.ectype;

import io.netty.buffer.ByteBuf;

import java.io.IOException;
import java.util.List;

import x.javaplus.collections.Lists;
import x.javaplus.util.Util.Time;
import x.javaplus.util.lua.Lua;


import cn.xgame.a.player.ectype.EctypeControl;
import cn.xgame.a.player.ectype.o.ChapterEctype;
import cn.xgame.a.player.ectype.o.IEctype;
import cn.xgame.a.player.ectype.o.StarEctype;
import cn.xgame.a.player.fleet.info.FleetInfo;
import cn.xgame.a.player.u.Player;
import cn.xgame.a.world.WorldManager;
import cn.xgame.a.world.planet.IPlanet;
import cn.xgame.net.event.IEvent;
import cn.xgame.utils.Logs;
import cn.xgame.utils.LuaUtil;

/**
 * 申请某个星球的副本信息
 * @author deng		
 * @date 2015-7-20 上午10:12:10
 */
public class ApplyEctypeEvent extends IEvent{
	
	@Override
	public void run(Player player, ByteBuf data) throws IOException {
		
		byte fleetId	= data.readByte();//舰队ID
		int snid 		= data.readInt();//星球ID
		
		IPlanet planet;
		try {
			planet = WorldManager.o.getPlanet(snid);
		} catch (Exception e) {
			return;
		}
		
		FleetInfo fleet 		= player.getFleets().getFleetInfo( fleetId );
		EctypeControl control 	= player.getEctypes();
		// 如果没有舰船直接返回
		if( fleet.isEmpty() )
			return;
		
		ByteBuf buffer = buildEmptyPackage( player.getCtx(), 1024 );
		
		// 获取星球副本列表
		List<StarEctype> starectypes = control.getEctypeList(planet);
		Logs.debug( player, "申请副本信息 " + starectypes );
		
		// 这里分别把 常规副本和普通限时副本 筛选出来
		List<ChapterEctype> general = Lists.newArrayList();
		List<ChapterEctype> normals = Lists.newArrayList();
		for( StarEctype se : starectypes ){
			general.addAll( se.getGeneral() );
			normals.addAll( se.getNormal() );
		}
		
		// 常规副本
		int endtime = (int) (Time.refTimeInMillis( 24, 0, 0 )/1000);
		buffer.writeByte( general.size() );
		for( ChapterEctype o : general ){
			buffer.writeByte( 1 );
			buffer.writeInt( o.getSnid() );
			o.buildTransformStream(buffer);
			buffer.writeInt( endtime );
			List<IEctype> ectypes = o.getEctypes();
			buffer.writeByte( ectypes.size() );
			for( IEctype x : ectypes ){
				buffer.writeInt( x.getNid() );
				Lua lua = LuaUtil.getEctypeCombat();
				lua.getField( "arithmeticShowData" ).call( 0, o.getSnid(), x, fleet, buffer );
			}
		}
		// 普通限时副本
		buffer.writeByte( normals.size() );
		for( ChapterEctype o : normals ){
			buffer.writeByte( 2 );
			buffer.writeInt( o.getSnid() );
			o.buildTransformStream(buffer);
			buffer.writeInt( o.getEndtime() );
			List<IEctype> ectypes = o.getEctypes();
			buffer.writeByte( ectypes.size() );
			for( IEctype x : ectypes ){
				buffer.writeInt( x.getNid() );
				Lua lua = LuaUtil.getEctypeCombat();
				lua.getField( "arithmeticShowData" ).call( 0, o.getSnid(), x, fleet, buffer );
			}
		}
		// 特殊限时副本
		buffer.writeByte( 0 );
		sendPackage( player.getCtx(), buffer );
	}

	
	
}
