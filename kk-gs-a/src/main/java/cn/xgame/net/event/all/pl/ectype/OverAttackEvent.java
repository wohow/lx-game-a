package cn.xgame.net.event.all.pl.ectype;

import io.netty.buffer.ByteBuf;

import java.io.IOException;
import java.util.List;

import x.javaplus.collections.Lists;
import x.javaplus.util.ErrorCode;

import cn.xgame.a.award.AwardInfo;
import cn.xgame.a.player.ectype.IEctype;
import cn.xgame.a.player.ship.o.ShipInfo;
import cn.xgame.a.player.ship.o.v.ShipStatus;
import cn.xgame.a.player.ship.o.v.StatusControl;
import cn.xgame.a.player.u.Player;
import cn.xgame.a.prop.IProp;
import cn.xgame.net.event.IEvent;
import cn.xgame.utils.Logs;

/**
 * 申请结束副本
 * @author deng		
 * @date 2015-7-31 上午7:43:03
 */
public class OverAttackEvent extends IEvent{

	@Override
	public void run(Player player, ByteBuf data) throws IOException {
		
		int snid = data.readInt();
		int enid = data.readInt();
		int suid = data.readInt();
		
		Logs.debug( player, "申请结束副本 星球ID=" + snid + ", 副本ID=" + enid + ", 舰船UID=" + suid );
		
		ErrorCode code 	= null;
		int combatTime 	= 0;// 战斗时间
		List<AwardInfo> awards = null;
		List<IProp> ret = Lists.newArrayList();
		try {
			
			// 判断副本是否可以打
			IEctype ectype = player.getEctypes().getEctype( snid, enid );
			if( ectype == null )
				throw new Exception( ErrorCode.ECTYPE_NOTEXIST.name() );
			
			ShipInfo ship 			= player.getDocks().getShip(suid);
			StatusControl status 	= ship.getStatus();
			if( status.getStatus() == ShipStatus.COMBAT ){
				combatTime			= status.getSurplusTime();
				if( combatTime != 0 )
					throw new Exception( ErrorCode.COMBATTIME_NOTOVER.name() );
			}else{
				throw new Exception( ErrorCode.OTHER_ERROR.name() );
			}
			
			// 这里设置 返航状态
			status.levitation();
			// 获取奖励
			awards = ship.getKeepInfo().getAwards();
			// 清空副本记录
			ship.getKeepInfo().clear();
			
			// 这里把奖励发放到玩家身上
			for( AwardInfo award : awards ){
				
				List<IProp> add = player.getDepots().appendProp( award.getId(), award.getCount() );
				
				ret.addAll(add);
			}
			
			code = ErrorCode.SUCCEED;
		} catch (Exception e) {
			code = ErrorCode.valueOf( e.getMessage() );
		}
		
		ByteBuf response = buildEmptyPackage( player.getCtx(), 1024 );
		response.writeShort( code.toNumber() );
		if( code == ErrorCode.SUCCEED ){
			// 奖励个数 用于更新
			response.writeByte( ret.size() );
			for( IProp prop : ret ){
				prop.putBaseBuffer(response);
			}
		}
		// 时间还没结束 让前端继续播放
		if( code == ErrorCode.COMBATTIME_NOTOVER ){
			response.writeInt( combatTime );
		}
		sendPackage( player.getCtx(), response );
		
	}

}
