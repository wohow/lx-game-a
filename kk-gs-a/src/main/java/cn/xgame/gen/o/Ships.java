package cn.xgame.gen.o;

/**
 * 所有舰船 数据
 * @author deng		
 * @date 2015-7-9 下午2:44:12
 */
public interface Ships {
	
	/** 服务器ID */
	short gsid();
	
	/** 玩家唯一ID */
	String uname();
	
	/** 唯一ID */
	int uid();
	
	/** 表格ID */
	int nid();
	
	/** 附加属性 */
	byte[] attachAttr();
	
	/** 当前血量 */
	int currentHp();
	
	/** 舰长唯一ID */
	int captainUid();
	
	/** 停靠星球ID */
	int berthSid();
	
	/** 货仓 */
	byte[] holds();
	
	/** 武器 */
	byte[] weapons();
	
	/** 辅助 */
	byte[] assists();
}
