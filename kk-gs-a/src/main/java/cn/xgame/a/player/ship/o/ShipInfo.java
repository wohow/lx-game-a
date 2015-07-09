package cn.xgame.a.player.ship.o;

import io.netty.buffer.ByteBuf;
import cn.xgame.a.ITransformStream;
import cn.xgame.a.player.IObject;
import cn.xgame.gen.dto.MysqlGen.ShipsDto;

/**
 * 一个舰船 的信息
 * @author deng		
 * @date 2015-7-9 下午12:22:54
 */
public class ShipInfo extends IObject implements ITransformStream{

	
	public ShipInfo( int uid, int nid ) {
		setuId(uid);
		setnId(nid);
	}

	public ShipInfo( ShipsDto dto ) {
		setuId(dto.getUid());
		setnId(dto.getNid());
	}

	@Override
	public void buildTransformStream(ByteBuf buffer) {
		buffer.writeInt( getuId() );
		buffer.writeInt( getnId() );
	}
	
}