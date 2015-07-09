package cn.xgame.a.player.captain;

import io.netty.buffer.ByteBuf;

import java.util.List;

import x.javaplus.collections.Lists;
import x.javaplus.mysql.db.Condition;
import cn.xgame.a.IFromDB;
import cn.xgame.a.ITransformStream;
import cn.xgame.a.player.captain.o.CaptainInfo;
import cn.xgame.a.player.u.Player;
import cn.xgame.a.system.SystemCfg;
import cn.xgame.gen.dto.MysqlGen.CaptainsDao;
import cn.xgame.gen.dto.MysqlGen.CaptainsDto;
import cn.xgame.gen.dto.MysqlGen.SqlUtil;

/**
 * 舰长室 操作类
 * @author deng		
 * @date 2015-7-9 下午12:28:12
 */
public class CaptainsControl implements ITransformStream,IFromDB{

	private Player root;
	
	// 舰长 列表
	private List<CaptainInfo> captains = Lists.newArrayList();
	
	public CaptainsControl( Player player ){
		this.root = player;
	}

	@Override
	public void fromDB() {
		captains.clear();
		CaptainsDao dao = SqlUtil.getCaptainsDao();
		String sql = new Condition( CaptainsDto.gsidChangeSql( SystemCfg.ID ) ).AND( CaptainsDto.unameChangeSql( root.getUID() ) ).toString();
		List<CaptainsDto> dtos = dao.getByExact( sql );
		for( CaptainsDto dto : dtos ){
			CaptainInfo ship = new CaptainInfo( dto );
			captains.add(ship);
		}
	}
	
	@Override
	public void buildTransformStream(ByteBuf buffer) {
		buffer.writeShort( captains.size() );
		for( CaptainInfo captain : captains ){
			captain.buildTransformStream(buffer);
		}
	}

	/**
	 * 创建一个 舰长
	 * @param nid
	 */
	public void createCaptain( int nid ) {
		
		CaptainInfo cap = new CaptainInfo( root.generatorCaptainUID(), nid );
		
		append(cap);
		
		// 在数据库创建数据
		createDB( cap );
	}

	private void append( CaptainInfo captain ) {
		captains.add(captain);
	}

	//TODO------------数据库相关
	
	private void createDB( CaptainInfo cap ) {
		CaptainsDao dao = SqlUtil.getCaptainsDao();
		CaptainsDto dto = dao.create();
		dto.setGsid( root.getGsid() );
		dto.setUname( root.getUID() );
		dto.setUid( cap.getuId() );
		dto.setNid( cap.getnId() );
		dao.commit(dto);
	}
	
}