package cn.xgame.net.event;

import io.netty.buffer.ByteBuf;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import cn.xgame.a.player.u.Player;
import cn.xgame.net.event.all.TestEevet;
import cn.xgame.net.event.all.ls.ConnectEvent;
import cn.xgame.net.event.all.ls.RLastGsidEvent;
import cn.xgame.net.event.all.ls.UpdatePeopleEvent;
import cn.xgame.net.event.all.pl.CreateEvent;
import cn.xgame.net.event.all.pl.LoginEvent;
import cn.xgame.net.event.all.pl.NewbieguideEvent;
import cn.xgame.net.event.all.pl.TimeRevisionEvent;
import cn.xgame.net.event.all.pl.captain.EatEquipEvent;
import cn.xgame.net.event.all.pl.captain.EatIntimacyEvent;
import cn.xgame.net.event.all.pl.chat.AlterGroupNameEvent;
import cn.xgame.net.event.all.pl.chat.AnswerTeamAxnEvent;
import cn.xgame.net.event.all.pl.chat.ExitGroupAxnEvent;
import cn.xgame.net.event.all.pl.chat.InviteTeamAxnEvent;
import cn.xgame.net.event.all.pl.chat.AnswerGroupAxnEvent;
import cn.xgame.net.event.all.pl.chat.LookGroupInfoEvent;
import cn.xgame.net.event.all.pl.chat.LookPlayerInfoEvent;
import cn.xgame.net.event.all.pl.chat.SponsorChatEvent;
import cn.xgame.net.event.all.pl.chat.InviteGroupAxnEvent;
import cn.xgame.net.event.all.pl.ectype.AnswerAttackEvent;
import cn.xgame.net.event.all.pl.ectype.ApplyAttackEvent;
import cn.xgame.net.event.all.pl.ectype.ApplyEctypeEvent;
import cn.xgame.net.event.all.pl.ectype.ApplyRetreatEvent;
import cn.xgame.net.event.all.pl.ectype.ApplySettlementEvent;
import cn.xgame.net.event.all.pl.ectype.LotteryEvent;
import cn.xgame.net.event.all.pl.ectype.OverAttackEvent;
import cn.xgame.net.event.all.pl.ectype.StartAttackEvent;
import cn.xgame.net.event.all.pl.fleet.ExitTeamEvent;
import cn.xgame.net.event.all.pl.fleet.FleetAwayEvent;
import cn.xgame.net.event.all.pl.fleet.FleetIntoEvent;
import cn.xgame.net.event.all.pl.fleet.KickPlayerEvent;
import cn.xgame.net.event.all.pl.fleet.LookTeamEvent;
import cn.xgame.net.event.all.pl.mail.ApplyMailEvent;
import cn.xgame.net.event.all.pl.mail.DeleteMailEvent;
import cn.xgame.net.event.all.pl.mail.ExtractAdjunctEvent;
import cn.xgame.net.event.all.pl.mail.ReadMailEvent;
import cn.xgame.net.event.all.pl.mail.SendMailEvent;
import cn.xgame.net.event.all.pl.manor.ApplyPedleryEvent;
import cn.xgame.net.event.all.pl.manor.ApplyProduceEvent;
import cn.xgame.net.event.all.pl.manor.ApplyBShopEvent;
import cn.xgame.net.event.all.pl.manor.BuildBuildingEvent;
import cn.xgame.net.event.all.pl.manor.BuyManorEvent;
import cn.xgame.net.event.all.pl.manor.BuyShopItemEvent;
import cn.xgame.net.event.all.pl.manor.DestroyBuildingEvent;
import cn.xgame.net.event.all.pl.manor.ExchangeChestsEvent;
import cn.xgame.net.event.all.pl.manor.SellGoodsEvent;
import cn.xgame.net.event.all.pl.manor.TakeGoodsEvent;
import cn.xgame.net.event.all.pl.manor.UpgradeBuildingEvent;
import cn.xgame.net.event.all.pl.planet.ApplyAlllAffairEvent;
import cn.xgame.net.event.all.pl.planet.ApplyExchEvent;
import cn.xgame.net.event.all.pl.planet.ApplyExchMeEvent;
import cn.xgame.net.event.all.pl.planet.ApplyGenrsEvent;
import cn.xgame.net.event.all.pl.planet.ApplyHomeEvent;
import cn.xgame.net.event.all.pl.planet.ApplyResEvent;
import cn.xgame.net.event.all.pl.planet.ApplySShopEvent;
import cn.xgame.net.event.all.pl.planet.ApplyTavernEvent;
import cn.xgame.net.event.all.pl.planet.DonateStuffEvent;
import cn.xgame.net.event.all.pl.planet.ParticipateBuildVoEvent;
import cn.xgame.net.event.all.pl.planet.ParticipateExpelEvent;
import cn.xgame.net.event.all.pl.planet.ParticipateTechVoEvent;
import cn.xgame.net.event.all.pl.planet.SponsorBuildVoEvent;
import cn.xgame.net.event.all.pl.planet.SponsorExpelEvent;
import cn.xgame.net.event.all.pl.planet.SponsorTechVoEvent;
import cn.xgame.net.event.all.pl.ship.MountCaptainEvent;
import cn.xgame.net.event.all.pl.ship.UnloadCaptainEvent;
import cn.xgame.net.event.all.pl.ship.UnloadEquipEvent;
import cn.xgame.net.event.all.pl.ship.MountEquipEvent;
import cn.xgame.net.event.all.pl.ship.HoldOperateEvent;
import cn.xgame.net.event.all.pl.staratlas.AppendAirlineEvent;
import cn.xgame.net.event.all.pl.staratlas.ApplyBerthEvent;
import cn.xgame.net.event.all.pl.staratlas.ApplySailendEvent;
import cn.xgame.net.event.all.pl.staratlas.ApplyStaratlasEvent;
import cn.xgame.net.event.all.pl.staratlas.SailoutEvent;
import cn.xgame.net.event.all.pl.task.AbandonTaskEvent;
import cn.xgame.net.event.all.pl.task.CompleteTaskEvent;
import cn.xgame.net.event.all.pl.task.NPCDialogueEvent;
import cn.xgame.net.event.all.pl.task.ReceiveTaskEvent;
import cn.xgame.net.event.all.pl.transaction.ApplySSwopEvent;
import cn.xgame.net.event.all.pl.transaction.ExchAddedEvent;
import cn.xgame.net.event.all.pl.transaction.ExchBuyEvent;
import cn.xgame.net.event.all.pl.transaction.ExchCollectEvent;
import cn.xgame.net.event.all.pl.transaction.ExchSoldoutEvent;
import cn.xgame.net.event.all.pl.transaction.ShopBuyEvent;
import cn.xgame.net.event.all.pl.transaction.SpecialSwopEvent;
import cn.xgame.net.event.all.pl.transaction.TavernBuyEvent;
import cn.xgame.net.event.all.pl.update.Update_1050;
import cn.xgame.net.event.all.pl.update.Update_1290;
import cn.xgame.net.event.all.pl.update.Update_1291;
import cn.xgame.net.event.all.pl.update.Update_1400;
import cn.xgame.net.event.all.pl.update.Update_2301;
import cn.xgame.net.event.all.pl.update.Update_3012;
import cn.xgame.net.event.all.pl.update.Update_2101;
import cn.xgame.net.event.all.pl.update.Update_2111;
import cn.xgame.net.event.all.pl.update.Update_2201;
import cn.xgame.net.event.all.pl.update.Update_2211;
import cn.xgame.net.event.all.pl.update.Update_2221;
import cn.xgame.net.event.all.pl.update.Update_2231;
import cn.xgame.net.event.all.pl.update.Update_2241;
import cn.xgame.net.event.all.pl.update.Update_2252;
import cn.xgame.net.event.all.pl.update.Update_3000;
import cn.xgame.net.event.all.pl.update.Update_3010;
import cn.xgame.net.event.all.pl.update.Update_3011;
import cn.xgame.net.event.all.pl.update.Update_3013;
import cn.xgame.net.event.all.pl.update.Update_3020;
import cn.xgame.net.event.all.pl.update.Update_3021;

/**
 * 通信 消息
 * @author deng		
 * @date 2015-6-11 下午3:34:41
 */
public enum Events {

	//-----------------登录服务器
	GS_CONNECT					( 201, 		new ConnectEvent()				, "" ),
	UPDATA_PEOPLE				( 210, 		new UpdatePeopleEvent()			, "" ),
	RLAST_GSID					( 220, 		new RLastGsidEvent()			, "" ),
	
	
	/////////////////////////////////////////////////////////////////////////////////////////////
	//-----------------玩家
	PLAYER_LOGIN				( 1001,		new LoginEvent() 				, "登录游戏" ),
	PLAYER_CREATE				( 1002, 	new CreateEvent() 				, "创建角色" ),
	NEWBIE_GUIDE				( 1003, 	new NewbieguideEvent() 			, "保存新手引导状态" ),
	TIME_REVISION				( 1004, 	new TimeRevisionEvent() 		, "时间校对" ), 
	// 星图
	APPLY_STARATLAS				( 1014, 	new ApplyStaratlasEvent()		, "航站-申请星图信息" ),
	SAIL_OUT					( 1015, 	new SailoutEvent()				, "航站-开始出航" ),
	APPLY_SAILEND				( 1016, 	new ApplySailendEvent()			, "航站-申请航行结束" ),
	APPLY_BERTH					( 1017, 	new ApplyBerthEvent()			, "航站-就近停靠" ),
	APPEND_AIRLINE				( 1018, 	new AppendAirlineEvent()		, "航站-追加航线" ),
	// 舰队
	FLEET_INTO					( 1027, 	new FleetIntoEvent()			, "舰队操作-实装舰队" ),
	FLEET_AWAY					( 1028, 	new FleetAwayEvent()			, "舰队操作-停用舰队" ),
	FLEET_LOOKTEAM				( 1061, 	new LookTeamEvent()				, "舰队操作-查看队伍" ),
	FLEET_EXITTEAM				( 1062, 	new ExitTeamEvent()				, "舰队操作-退出队伍" ),
	KICK_PLAYER					( 1063, 	new KickPlayerEvent()			, "舰队操作-踢人" ),
	// 舰船
	MOUNT_HOLD					( 1021, 	new HoldOperateEvent()			, "舰船操作-放入船仓" ),
	MOUNT_EQUIP					( 1023, 	new MountEquipEvent()			, "舰船操作-装上装备" ),
	UNLOAD_EQUIP				( 1024, 	new UnloadEquipEvent()			, "舰船操作-取下装备" ),
	MOUNT_CAPTAIN				( 1025, 	new MountCaptainEvent() 		, "舰船操作-指派舰长" ),
	UNLOAD_CAPTAIN				( 1026, 	new UnloadCaptainEvent()		, "舰船操作-卸下舰长" ),
	// 舰长
	EAT_EQUIP					( 1031, 	new EatEquipEvent()				, "舰长操作-装上装备" ),
	EAT_INTIMACY				( 1032, 	new EatIntimacyEvent()			, "舰长操作-吃亲密度丹" ),
	// 领地
	BUY_MANOR					( 1041, 	new BuyManorEvent()				, "领地操作-购买领地" ),
	APPLY_GOODS					( 1042, 	new ApplyProduceEvent()			, "领地操作-申请产出" ),
	BUILD_BUILDING				( 1043, 	new BuildBuildingEvent()		, "领地操作-建造建筑" ),
	UPGRADE_BUILDING			( 1044, 	new UpgradeBuildingEvent()		, "领地操作-建筑升级" ),
	DESTROY_BUILDING			( 1045, 	new DestroyBuildingEvent()		, "领地操作-建筑销毁" ),
	TAKE_GOODS					( 1046, 	new TakeGoodsEvent()			, "领地操作-收取产出" ),
	SELL_GOODS					( 1047, 	new SellGoodsEvent()			, "领地操作-出售产出" ),
	APPLY_SHOP					( 1048, 	new ApplyBShopEvent()			, "领地操作-申请商店详细信息" ),
	APPLY_PEDLERY				( 1049, 	new ApplyPedleryEvent()			, "领地操作-申请行商详细信息" ),
	BUY_SHOPITEM				( 1071, 	new BuyShopItemEvent()			, "领地操作-购买商店物品" ),
	EXCHANGE_CHESTS				( 1072, 	new ExchangeChestsEvent()		, "领地操作-兑换行商宝箱" ),
	// 邮件
	APPLY_MAIL					( 1051, 	new ApplyMailEvent()			, "邮件操作-申请邮件列表" ),
	SEND_MAIL					( 1052, 	new SendMailEvent()				, "邮件操作-发送邮件" ),
	READ_MAIL					( 1053, 	new ReadMailEvent()				, "邮件操作-读取邮件" ),
	EXTRACT_ADJUNCT				( 1054, 	new ExtractAdjunctEvent()		, "邮件操作-提取附件" ),
	DELETE_MAIL					( 1055, 	new DeleteMailEvent()			, "邮件操作-删除邮件" ),
	
	//-----------------星球
	APPLY_PLANET				( 1101, 	new ApplyHomeEvent()			, "星球操作-申请基础数据" ),
	APPLY_PLANET_RES			( 1111, 	new ApplyResEvent()				, "星球操作-申请资源" ),
	APPLY_PLANET_SPE			( 1112, 	new ApplySShopEvent()			, "星球操作-申请商店" ),
	APPLY_ALLLAFFAIR			( 1113, 	new ApplyAlllAffairEvent()		, "星球操作-申请政务" ),
	APPLY_GENRS					( 1114, 	new ApplyGenrsEvent()			, "星球操作-申请元老数据" ),
	APPLY_TAVERN				( 1115, 	new ApplyTavernEvent()			, "星球操作-申请酒馆" ),
	APPLY_EXCH					( 1116, 	new ApplyExchEvent()			, "星球操作-申请交易所数据" ),
	APPLY_EXCHME				( 1117, 	new ApplyExchMeEvent()			, "星球操作-申请交易所自己的数据" ),
	DONATE_STUFF				( 1121, 	new DonateStuffEvent()			, "星球操作-捐献资源" ),
	
	// 建筑
	SPONSOR_BUILDVOTE			( 1131, 	new SponsorBuildVoEvent()		, "星球操作-发起建筑投票" ),
	PARTICIPATE_BUILDVOTE		( 1132, 	new ParticipateBuildVoEvent()	, "星球操作-参与建筑投票" ),
	// 科技
	SPONSOR_TECHVOTE			( 1141, 	new SponsorTechVoEvent()		, "星球操作-发起科技投票" ),
	PARTICIPATE_TECHVOTE		( 1142, 	new ParticipateTechVoEvent()	, "星球操作-参与科技投票" ),
	// 元老
	SPONSOR_EXPEL				( 1151, 	new SponsorExpelEvent()			, "星球操作-驱逐元老投票" ),
	PARTICIPATE_EXPEL			( 1152, 	new ParticipateExpelEvent()		, "星球操作-参与驱逐元老投票" ),
	//-----------------副本
	APPLY_ACCECTYPE				( 1201, 	new ApplyEctypeEvent()			, "副本操作-申请副本列表" ),
	START_ATTACK				( 1202, 	new StartAttackEvent()			, "副本操作-出击" ),
	APPLY_ATTACK				( 1203, 	new ApplyAttackEvent()			, "副本操作-请求出击" ),
	ANSWER_ATTACK				( 1204, 	new AnswerAttackEvent()			, "副本操作-是否同意出击" ),
	OVER_ATTACK					( 1211, 	new OverAttackEvent()			, "副本操作-申请结束" ),
	APPLY_RETREAT				( 1212, 	new ApplyRetreatEvent()			, "副本操作-申请撤退" ),
	APPLY_SETTLEMENT			( 1213, 	new ApplySettlementEvent()		, "副本操作-申请结算面板" ),
	LOTTERY						( 1214, 	new LotteryEvent()				, "副本操作-抽奖" ),
	//-----------------交易
	SHOP_BUY					( 1301, 	new ShopBuyEvent()				, "星球操作-购买商店道具" ),
	TAVERN_BUY					( 1311, 	new TavernBuyEvent()			, "星球操作-购买酒馆道具" ),
	EXCH_ADDED					( 1321, 	new ExchAddedEvent()			, "交易所-上架" ),
	EXCH_BUY					( 1322, 	new ExchBuyEvent()				, "交易所-购买" ),
	EXCH_COLLECT				( 1323, 	new ExchCollectEvent()			, "交易所-收款" ),
	EXCH_SOLDOUT				( 1324, 	new ExchSoldoutEvent()			, "交易所-下架" ),
	APPLY_SSWOP					( 1331, 	new ApplySSwopEvent()			, "兑换-申请特殊兑换剩余次数" ),
	SPECIAL_SWOP				( 1332, 	new SpecialSwopEvent()			, "兑换-特殊兑换" ),
	//-----------------任务
	RECEIVE_TASK				( 1403, 	new ReceiveTaskEvent()			, "任务系统-接任务" ),
	ABANDON_TASK				( 1404, 	new AbandonTaskEvent()			, "任务系统-放弃任务" ),
	COMPLETE_TASK				( 1405, 	new CompleteTaskEvent()			, "任务系统-完成任务" ),
	NPC_DIALOGUE				( 1406, 	new NPCDialogueEvent()			, "任务系统-NPC对话" ),
	
	//-----------------聊天
	SPONSOR_CHAT				( 3001, 	new SponsorChatEvent()			, "聊天系统-发起聊天" ),
	LOOK_PLAYERINFO				( 3002, 	new LookPlayerInfoEvent()		, "聊天系统-查看玩家信息" ),
	// 群聊
	CREATE_TEMPAXN				( 3015, 	new InviteGroupAxnEvent()		, "聊天系统-邀请加入群聊" ),
	INVITE_TEMPAXN				( 3016, 	new AnswerGroupAxnEvent()		, "聊天系统-是否同意加入群聊" ),
	ALTER_GROUPNAME				( 3017, 	new AlterGroupNameEvent()		, "聊天系统-修改群聊名字" ),
	EXIT_GROUPAXN				( 3018, 	new ExitGroupAxnEvent()			, "聊天系统-退出群聊" ),
	LOOK_GROUPINFO				( 3019, 	new LookGroupInfoEvent()		, "聊天系统-查看群聊成员" ),
	// 组队
	INVITE_TEAM					( 3025, 	new InviteTeamAxnEvent()		, "聊天系统-邀请组队" ),
	ANSWER_TEAM					( 3026, 	new AnswerTeamAxnEvent()		, "聊天系统-是否同意加入队伍" ),
	// 私聊
	
	
	//-----------------更新包
	UPDATE_2101					( 2101, 	new Update_2101()				, "更新包-" ),
	UPDATE_2111					( 2111, 	new Update_2111()				, "更新包-" ),
	
	UPDATE_2201					( 2201, 	new Update_2201()				, "更新包-" ),
	UPDATE_2211					( 2211, 	new Update_2211()				, "更新包-" ),
	UPDATE_2221					( 2221, 	new Update_2221()				, "更新包-" ),
	UPDATE_2231					( 2231, 	new Update_2231()				, "更新包-" ),
	UPDATE_2241					( 2241, 	new Update_2241()				, "更新包-" ),
	UPDATE_2252					( 2252, 	new Update_2252()				, "更新包-" ),
	// 聊天
	UPDATE_3000					( 3000, 	new Update_3000()				, "更新包-" ),
	UPDATE_3010					( 3010, 	new Update_3010()				, "更新包-" ),
	UPDATE_3011					( 3011, 	new Update_3011()				, "更新包-" ),
	UPDATE_3012					( 3012, 	new Update_3012()				, "更新包-" ),
	UPDATE_3013					( 3013, 	new Update_3013()				, "更新包-" ),
	UPDATE_3020					( 3020, 	new Update_3020()				, "更新包-" ),
	UPDATE_3021					( 3021, 	new Update_3021()				, "更新包-" ),
	UPDATE_2301					( 2301, 	new Update_2301()				, "更新包-" ),
	// 邮件
	UPDATE_1050					( 1050, 	new Update_1050()				, "更新包-" ),
	// 任务
	UPDATE_1400					( 1400, 	new Update_1400()				, "更新包-" ),
	// 副本
	UPDATE_1290					( 1290, 	new Update_1290()				, "更新包-" ),
	UPDATE_1291					( 1291, 	new Update_1291()				, "更新包-" ),

	
	
	
	
	/////////////////////////////////////////////////////////////////////////////////////////////
	TEST						( Short.MAX_VALUE-1, 	new TestEevet()		, "测试包" );
	
	
	private final short			number;
	private final IEvent 		eventInstance;
	private final String		desc;
	
	Events( int value, IEvent eventInstance, String desc ) {
		if( value >= Short.MAX_VALUE || value < 0 ){
			throw new IllegalArgumentException( "包号不符合规范：" + value );
		}
		this.number 		= (short) value;
		this.eventInstance 	= eventInstance;
		this.desc			= desc;
		this.eventInstance.setEventId( number );
	}
	Events( int value, IEvent eventInstance ) {
		if( value >= Short.MAX_VALUE || value < 0 ){
			throw new IllegalArgumentException( "包号不符合规范：" + value );
		}
		this.number 		= (short) value;
		this.eventInstance 	= eventInstance;
		this.desc			= "";
		this.eventInstance.setEventId( number );
	}
	
	private static final Map<Short, Events> numToEnum = new HashMap<Short, Events>();
	
	static{
		for( Events a : values() ){
			
			Events p = numToEnum.put( a.number, a );
			if( p != null ){
				throw new RuntimeException( "通信包" + a.number + "重复了" );
			}
		}
	}
	
	public IEvent toInstance() {
		return eventInstance;
	}
	public short toNumber() {
		return number;
	}
	public String toDesc(){
		return desc;
	}
	public static Events fromNumber( int n ){
		return numToEnum.get( (short)n );
	}
	
	/**
	 * 运行此枚举所对应的包的run方法
	 * @param user
	 * @param buf
	 * @throws IOException 
	 */
	public void run( Player player, ByteBuf buf ) throws IOException {
		eventInstance.run( player, buf );
	}

}
