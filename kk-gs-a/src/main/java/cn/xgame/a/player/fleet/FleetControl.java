package cn.xgame.a.player.fleet;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;

import java.util.List;

import x.javaplus.collections.Lists;

import cn.xgame.a.IArrayStream;
import cn.xgame.a.player.dock.ship.ShipInfo;
import cn.xgame.a.player.fleet.classes.IStatus;
import cn.xgame.a.player.fleet.classes.StatusType;
import cn.xgame.a.player.fleet.info.FleetInfo;
import cn.xgame.a.player.u.Player;

/**
 * 舰队操作中心
 * @author deng		
 * @date 2015-9-10 下午11:47:20
 */
public class FleetControl implements IArrayStream{
	
	private Player root;
	
	// 舰队列表
	private List<FleetInfo> fleets = Lists.newArrayList();
	
	
	public FleetControl( Player player ) {
		this.root = player;
	}
	
	@Override
	public void fromBytes( byte[] data ) {
		if( data == null ) return ;
		ByteBuf buf = Unpooled.copiedBuffer(data);
		byte size = buf.readByte();
		for( int i = 0; i < size; i++ ){
			FleetInfo fleet = new FleetInfo( buf.readByte() );
			fleet.setBerthSnid( buf.readInt() );
			List<ShipInfo> ships = fleet.getShips();
			byte count = buf.readByte();
			for( int j = 0; j < count; j++ ){
				ShipInfo ship = root.getDocks().getShip( buf.readInt() );
				if( ship == null ) continue;
				ships.add( ship );
			}
			byte type = buf.readByte();
			fleet.setStatus( IStatus.create( type, buf ) );
			fleets.add( fleet );
		}
	}
	
	@Override
	public byte[] toBytes() {
		ByteBuf buf = Unpooled.buffer();
		buf.writeByte( fleets.size() );
		for( FleetInfo fleet : fleets ){
			buf.writeByte( fleet.getNo() );
			buf.writeInt( fleet.getBerthSnid() );
			List<ShipInfo> ships = fleet.getShips();
			buf.writeByte( ships.size() );
			for( ShipInfo ship : ships )
				buf.writeInt( ship.getuId() );
			IStatus status = fleet.getStatus();
			buf.writeByte( status.type().toNumber() );
			status.putBuffer(buf);
		}
		return buf.array();
	}
	
	public List<FleetInfo> getFleet(){ return fleets; }
	/**
	 * 获取拥有舰船的舰队
	 * @return
	 */
	public List<FleetInfo> getFleetHavaShip() {
		List<FleetInfo> ret = Lists.newArrayList();
		for( FleetInfo fleet : fleets ){
			if( !fleet.isEmpty() )
				ret.add(fleet);
		}
		return ret;
	}
	
	/**
	 * 获取还有队伍的舰队
	 * @return
	 */
	public List<FleetInfo> getHaveTeam() {
		List<FleetInfo> ret = Lists.newArrayList();
		for( FleetInfo fleet : fleets ){
			if( fleet.getAxnId() != -1 )
				ret.add(fleet);
		}
		return ret;
	}
	
	/**
	 * 根据舰队编号获取舰队信息 -
	 * @param No
	 * @return
	 */
	public FleetInfo getFleetInfo( byte No ) {
		for( FleetInfo fleet : fleets ){
			if( fleet.getNo() == No )
				return fleet;
		}
		return null;
	}
	/**
	 * 根据舰船获取 舰队信息
	 * @param ship
	 * @return
	 */
	public FleetInfo getFleetInfo( ShipInfo ship ) {
		for( FleetInfo fleet : fleets ){
			if( fleet.getShip( ship.getuId() ) != null )
				return fleet;
		}
		return null;
	}
	/**
	 * 根据频道获取舰队
	 * @param axnId
	 * @return
	 */
	public FleetInfo getFleetInfo( int axnId ) {
		for( FleetInfo fleet : fleets ){
			if( fleet.getAxnId() == axnId )
				return fleet;
		}
		return null;
	}
	
	
	/**
	 * 创建一个舰队 
	* @param sid 星球ID 
	 */
	public void createFleet( int sid ){
		FleetInfo e = new FleetInfo( (byte)(fleets.size()+1) );
		e.setBerthSnid( sid );
		e.changeStatus( StatusType.HOVER );
		fleets.add( e );
	}

	/**
	 * 是否有队伍
	 * @return
	 */
	public boolean isHaveTeam() {
		for( FleetInfo fleet : fleets ){
			if( fleet.getAxnId() != 0 )
				return true;
		}
		return false;
	}


}
