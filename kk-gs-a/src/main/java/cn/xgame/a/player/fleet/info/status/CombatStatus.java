package cn.xgame.a.player.fleet.info.status;

import java.util.List;

import x.javaplus.collections.Lists;

import io.netty.buffer.ByteBuf;
import cn.xgame.a.award.AwardInfo;
import cn.xgame.a.player.depot.o.StarDepot;
import cn.xgame.a.player.fleet.classes.IStatus;
import cn.xgame.a.player.fleet.classes.StatusType;
import cn.xgame.a.player.fleet.info.FleetInfo;
import cn.xgame.a.player.u.Player;

/**
 * 战斗状态
 * @author deng		
 * @date 2015-9-11 上午12:39:04
 */
public class CombatStatus extends IStatus{
	
	// 副本类型 1.常规副本 2.普通限时 3.特殊限时
	private byte type;
	
	// 章节ID
	private int chapterId;
	
	// 难度类型 1.普通本 2.挂机本
	private byte ltype;
	
	// 难度
	private byte level;
	
	// 起始时间
	private int starttime;
	
	// 深度时间 
	private int depthtime;
	
	// 战斗时间
	private int fighttime;
	
	// 是否胜利
	private byte isWin;
	
	// 奖励列表
	private List<AwardInfo> awards = Lists.newArrayList();
	
	// 评分
	private int score;
	

	
	public CombatStatus() {
		super( StatusType.COMBAT );
	}
	
	@Override
	public void init( Object[] objects ) {
		int i = 0;
		type 		= (Byte) objects[i++];
		chapterId 	= (Integer) objects[i++];
		ltype		= (Byte) objects[i++];
		level		= (Byte) objects[i++];
		starttime	= (Integer) objects[i++];
		depthtime	= (Integer) objects[i++];
		fighttime	= (Integer) objects[i++];
		isWin		= (Byte) objects[i++];
		List<?>	x	= (List<?>) objects[i++];
		for( Object o : x )
			awards.add( (AwardInfo) o );
		score		= (Integer) objects[i++];
	}
	
	@Override
	public void putBuffer(ByteBuf buf) {
		buf.writeByte( type );
		buf.writeInt( chapterId );
		buf.writeByte( ltype );
		buf.writeByte( level );
		buf.writeInt( starttime );
		buf.writeInt( depthtime );
		buf.writeInt( fighttime );
		buf.writeByte( isWin );
		buf.writeByte( awards.size() );
		for( AwardInfo award : awards )
			award.buildTransformStream(buf);
		buf.writeInt( score );
	}
	
	@Override
	public void wrapBuffer(ByteBuf buf) {
		this.type 		= buf.readByte();
		this.chapterId 	= buf.readInt();
		this.ltype 		= buf.readByte();
		this.level 		= buf.readByte();
		this.starttime 	= buf.readInt();
		this.depthtime 	= buf.readInt();
		this.fighttime 	= buf.readInt();
		this.isWin 		= buf.readByte();
		byte size		= buf.readByte();
		for( int i = 0; i < size; i++ )
			awards.add( new AwardInfo(buf) );
		score 			= buf.readInt();
	}
	
	@Override
	public void buildTransformStream(ByteBuf buffer) {
		super.buildTransformStream(buffer);
		buffer.writeByte( type );
		buffer.writeInt( chapterId );
		buffer.writeByte( ltype );
		buffer.writeByte( level );
		buffer.writeInt( getAlreadyFighttime() );
		buffer.writeInt( fighttime );
	}
	
	// 获取已经战斗的时间 - 主要用于前端显示
	private int getAlreadyFighttime() {
		return (int)(System.currentTimeMillis()/1000) - starttime;
	}
	
	@Override
	public boolean isComplete() {
		// 起始时间 + (深度时间 x 2) + 战斗时间 = 结束时间
		int i = starttime + (depthtime*2) + fighttime;
		return (int) (System.currentTimeMillis()/1000) >= i;
	}
	
	@Override
	public void update( FleetInfo fleet, Player player ) {
		// 发送奖励
		for( AwardInfo award : awards ){
			StarDepot depot = player.getDepots(fleet.getBerthSnid());
			depot.appendProp( award.getId(), award.getCount() );
		}
		// 计算评星奖励
		// TODO
		
		// 设置悬停
		fleet.changeStatus( StatusType.HOVER );
	}
	
	public byte getType() {
		return type;
	}
	public void setType(byte type) {
		this.type = type;
	}
	public int getChapterId() {
		return chapterId;
	}
	public void setChapterId(int chapterId) {
		this.chapterId = chapterId;
	}
	public byte getIsWin() {
		return isWin;
	}
	public void setIsWin(byte isWin) {
		this.isWin = isWin;
	}
	public List<AwardInfo> getAwards() {
		return awards;
	}
	public void setAwards(List<AwardInfo> awards) {
		this.awards = awards;
	}
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	public byte getLtype() {
		return ltype;
	}
	public void setLtype(byte ltype) {
		this.ltype = ltype;
	}
	public byte getLevel() {
		return level;
	}
	public void setLevel(byte level) {
		this.level = level;
	}
	public int getStarttime() {
		return starttime;
	}
	public void setStarttime(int starttime) {
		this.starttime = starttime;
	}
	public int getDepthtime() {
		return depthtime;
	}
	public void setDepthtime(int depthtime) {
		this.depthtime = depthtime;
	}
	public int getFighttime() {
		return fighttime;
	}
	public void setFighttime(int fighttime) {
		this.fighttime = fighttime;
	}

}
