package cn.xgame.a.player.ship;

import io.netty.buffer.ByteBuf;

import java.util.List;

import x.javaplus.collections.Lists;
import x.javaplus.mysql.db.Condition;
import x.javaplus.util.ErrorCode;

import cn.xgame.a.IFromDB;
import cn.xgame.a.ITransformStream;
import cn.xgame.a.player.ship.o.FleetInfo;
import cn.xgame.a.player.ship.o.ShipInfo;
import cn.xgame.a.player.u.Player;
import cn.xgame.a.prop.IProp;
import cn.xgame.gen.dto.MysqlGen.ShipsDao;
import cn.xgame.gen.dto.MysqlGen.ShipsDto;
import cn.xgame.gen.dto.MysqlGen.SqlUtil;
import cn.xgame.system.SystemCfg;

/**
 * 船坞 操作类
 * @author deng		
 * @date 2015-7-9 下午12:20:41
 */
public class DockControl implements ITransformStream,IFromDB{
	
	private Player root;
	
	// 舰船 列表
	private List<ShipInfo> ships = Lists.newArrayList();
	// 舰船最大个数
	private byte maxShipNum = 4 ;
	
	public DockControl( Player player ){
		this.root = player;
	}

	public List<ShipInfo> getAll(){
		return ships;
	}
	
	@Override
	public void fromDB() {
		ships.clear();
		ShipsDao dao = SqlUtil.getShipsDao();
		String sql = new Condition( ShipsDto.gsidChangeSql( SystemCfg.ID ) ).AND( ShipsDto.unameChangeSql( root.getUID() ) ).toString();
		List<ShipsDto> dtos	= dao.getByExact( sql );
		dao.commit();
		for( ShipsDto dto : dtos ){
			ShipInfo ship = new ShipInfo( dto );
			ship.updateStatus(root);
			ships.add(ship);
		}
	}
	
	/** 保存所有数据到数据库 */
	public void update() {
		for( ShipInfo ship : ships )
			ship.updateDB(root);
	}
	
	@Override
	public void buildTransformStream(ByteBuf buffer) {
		buffer.writeByte( ships.size() );
		for( ShipInfo ship : ships ){
			ship.buildTransformStream( buffer );
		}
	}

	public List<ShipInfo> getAllShip() {
		return ships;
	}
	

	/**
	 * 创建一个 舰船 并装备上
	 * @param nid
	 */
	public void createShip( int nid ) {
		
		if( ships.size() >= maxShipNum )
			return;
		
		ShipInfo ship = new ShipInfo( root.generatorShipUID(), nid );
		
		append( ship );
		
		// 最后在数据库 创建数据
		ship.createDB( root );
	}
	private void append( ShipInfo ship ) {
		// 默认设置停靠在自己母星上
		ship.getStatus().init( root.getCountryId() );
		ships.add(ship);
	}

	/**
	 * 根据唯一ID 获取舰船
	 * @param suid
	 * @return
	 */
	public ShipInfo getShip( int suid ) {
		for( ShipInfo o : ships ){
			if( o.getuId() == suid )
				return o;
		}
		return null;
	}
	/**
	 * 根据唯一ID 获取舰船  附带异常
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public ShipInfo getShipOfException( int id ) throws Exception {
		ShipInfo ret = getShip( id );
		if( ret == null )
			throw new Exception( ErrorCode.SHIP_NOTEXIST.name() );
		return ret;
	}

	/**
	 * 从某艘船删除道具
	 * @param ship
	 * @param prop
	 */
	public void removeEquipAtShip( ShipInfo ship, IProp prop ) {
		if( ship == null ) return;
		ship.getEquips().remove(prop);
		ship.updateDB(root);
	}
	
	/**
	 * 是否还有空闲的船
	 * @return
	 */
	public boolean isHaveLeisure() {
		for( ShipInfo ship : ships ){
			// 悬停并且还没有队伍
			if( ship.isLevitation() && ship.getTeamId() == 0 )
				return true;
		}
		return false;
	}
	
	/**
	 * 获取所有频道
	 * @return
	 */
	public List<Integer> getAllAxn() {
		List<Integer> ret = Lists.newArrayList();
		for( ShipInfo ship : ships ){
			if( ship.getTeamId() != 0 )
				ret.add( ship.getTeamId() );
		}
		return ret;
	}
	
	/**
	 * 删除一个频道ID
	 * @param axnId
	 */
	public void removeAxn(int axnId) {
		for( ShipInfo ship : ships ){
			if( ship.getTeamId() == axnId ){
				ship.setTeamId(0);
				return;
			}
		}
	}
	
	//TODO-------------其他函数

	/**
	 * 放一个道具到 舰船货仓
	 * @param ship 
	 * @param clone
	 * @return
	 * @throws Exception 
	 */
	public List<IProp> putinHold( ShipInfo ship, IProp clone ) throws Exception {
		
		// 看货仓是否 还有空间
		if( !ship.getHolds().roomIsEnough( clone ) )
			throw new Exception( ErrorCode.ROOM_LAZYWEIGHT.name() );
		
		// 放入货仓
		List<IProp> ret = ship.getHolds().appendProp( clone );
		
		// 最后保存数据库
		ship.updateDB(root);
		
		return ret;
	}

	/**
	 * 在舰船货仓里面卸下一个道具
	 * @param ship
	 * @param uid
	 * @param count
	 * @return
	 * @throws Exception 
	 */
	public IProp unloadHoldProp( ShipInfo ship, int uid, int count ) throws Exception {
		// 执行扣除
		IProp ret = ship.getHolds().deductProp( uid, count );
		if( ret == null )
			throw new Exception( ErrorCode.PROP_NOTEXIST.name() ) ;
		// 最后保存一下数据库
		ship.updateDB(root);
		return ret;
	}

	/**
	 * 装上一个装备
	 * @param ship
	 * @param clone
	 * @return
	 * @throws Exception 
	 */
	public IProp mountEquip( ShipInfo ship, IProp clone ) throws Exception {
		
		// 检查是否有舰长 如果没有舰长那么就不能装备
		// TODO
		
		// 看货仓是否 还有空间
		if( !ship.getEquips().roomIsEnough( clone ) )
			throw new Exception( ErrorCode.ROOM_LAZYWEIGHT.name() );
		// 检测复杂度是否足够
		// TODO
		
		// 直接放入
		IProp ret = ship.getEquips().put(clone);
		
		ship.updateDB( root );
		return ret;
	}

	/**
	 * 根据舰队ID获取舰队信息
	 * @param fleetId
	 * @return
	 */
	public FleetInfo getFleetInfo(byte fleetId) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	
}
