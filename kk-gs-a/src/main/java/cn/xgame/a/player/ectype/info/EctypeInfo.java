package cn.xgame.a.player.ectype.info;

import cn.xgame.a.player.ectype.classes.IEctype;
import cn.xgame.config.o.EctypePo;
import cn.xgame.utils.LuaUtil;

/**
 * 一个副本信息 - 该类数据不保存数据库
 * @author deng		
 * @date 2015-10-27 下午7:44:04
 */
public class EctypeInfo extends IEctype{
	
	public EctypeInfo( byte level, EctypePo templet ) {
		super(level);
	}
	
	
	/**
	 * 通过lua脚本设置属性
	 * @param level 难度 从1开始
	 * @param templet 
	 */
	public void setAttribute( int level, EctypePo templet ){
		if( templet == null ) 
			return;
		if( level < 1 ) level = 1;
		LuaUtil.getEctypeInfo().getField( "generateEctypeInfo" ).call( 0, this, level, templet );
	}
	
	public void setAttribute( EctypePo templet ) {
		setAttribute( getLevel(), templet );
	}

}
