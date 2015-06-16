package cn.xgame.logic.gs;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.xgame.net.netty.Netty.Attr;


import io.netty.channel.ChannelHandlerContext;
import x.javaplus.collections.Lists;
import x.javaplus.util.ErrorCode;

/**
 * 游戏服务器 管理中心
 * @author deng		
 * @date 2015-6-12 下午4:25:17
 */
public class GSManager {

	public static GSManager o = new GSManager();
	private GSManager(){}
	
	
	// 服务器 列表
	private Map<Short, GSData> gss = new HashMap<Short, GSData>();
	
	/**
	 * 获取开启 服务器 列表
	 * @return
	 */
	public List<GSData> getOpenGs(){
		List<GSData> ret = Lists.newArrayList();
		
		for( GSData gs : gss.values() ){
			if( gs.getStatus() == GSStatus.OPEN ){
				ret.add(gs);
			}
		}
		return ret;
	}
	
	
	/**
	 * 服务器 连接
	 * @param gsid
	 * @param name
	 * @param port
	 * @param ctx
	 * @return
	 */
	public ErrorCode connect( short gsid, String name, int port, ChannelHandlerContext ctx ) {
		
		GSData gs = get( gsid );
		
		if( gs == null ){
			
			gs = new GSData( gsid );
			gss.put( gs.getId(), gs );
			
		}else if( gs.getStatus() == GSStatus.OPEN ){
			
			return ErrorCode.GS_EXIST;
		}
		
		gs.setCtx( ctx );
		gs.setName( name );
		gs.setPort( port );
		
		return ErrorCode.SUCCEED;
	}

	
	public GSData get( short gsid ) {
		return gss.get(gsid);
	}


	/**
	 * 有服务器 断开 
	 * @param ctx
	 */
	public void disconnect( ChannelHandlerContext ctx ) {
		
		short id 	= getGsid( ctx );
		GSData gs 	= get( id );
		if( gs == null )
			return ;
		// 直接设置为null
		gs.setCtx( null );
	}
	
	
	private short getGsid( ChannelHandlerContext ctx ){
		String attr = Attr.getAttachment(ctx);
		return attr == null ? -1 : Short.parseShort( attr.replaceAll( "gs:", "") );
	}

	/**
	 * 更新 服务器 人数
	 * @param gsid
	 * @param peopleNum
	 */
	public void updatePeople( short gsid, int peopleNum ) {
		
		GSData gs = get( gsid );
		if( gs == null || gs.getStatus() != GSStatus.OPEN )
			return;
		
		gs.setPeopleNum( peopleNum );
	}
	
}
