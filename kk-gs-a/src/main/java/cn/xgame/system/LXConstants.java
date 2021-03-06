package cn.xgame.system;

/**
 * 游戏常量
 * @author deng		
 * @date 2015-6-11 下午2:54:48
 */
public class LXConstants {
	
	/** 协商公钥 用于登录验证 */
	public static final String PUBLICKEY 		= "longxun-kuake";
	
	/** 聊天ID位数 */
	public static final int CHAT_UID 			= 1000000;

	

	

	
	

	
	
	//--------------------------通过配置获取
	
	/** 初始母星球ID */
	public static int INIT_HOMESTAR_NID		= 1004;
	
	/** 酒馆刷新时间 (单位秒) */
	public static int TAVERN_UPDATE_TIME 	= 600;

	/** 货币表格ID */
	public static int CURRENCY_NID 			= 60000;
	
	/** 私聊频道最大拥有个数 */
	public static int PRIVATEAXN_MAX 		= 2;
	/** 临时频道最大拥有个数 */
	public static int TEMPAXN_MAX 			= 3;
	/** 临时频道人数上限 */
	public static int TEMPAXN_MAXMEMBER		= 20;
	
	/** 队伍频道最大拥有个数 */
	public static int TEAMAXN_MAX 			= 2;
	/** 队伍频道人数上限 */
	public static int TEAMAXN_MAXMEMBER		= 4;
	
	/** 邮件持续时间 (单位秒)*/
	public static int MAIL_DURATION  		= 86400;
	/** 发送邮件手续费*/
	public static int MAIL_FACTORAGE  		= 10;
	
	/** 副本投票等待时间 (单位秒)*/
	public static int ECTYPE_VOTE_TIME 		= 60;
	
	/** 基地塔默认ID*/
	public static int BASE_BUILD_ID 		= 20025;
	
	/** 领地建筑商店刷新时间(秒)*/
	public static int[] BUILD_SHOP_UPDATETIME 	= {86400};
	/** 领地建筑商店刷新时间(秒)*/
	public static int[] BUILD_SHOP_NUM 			= {1};
	/** 领地建筑商店刷新时间(秒)*/
	public static int[] BUILD_SHOP_QUALITY 		= {1};
	
	/**
	 * 加载配置文件
	 */
	public static void load() {
		// TODO Auto-generated method stub
		
	}
	
	
	
	
	
}
