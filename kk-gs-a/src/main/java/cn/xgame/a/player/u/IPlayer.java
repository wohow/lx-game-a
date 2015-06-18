package cn.xgame.a.player.u;

import cn.xgame.gen.dto.MysqlGen.PlayerDataDto;

/**
 * 玩家 基础 数据结构 （数据库相关）
 * @author deng		
 * @date 2015-6-17 下午2:49:58
 */
public abstract class IPlayer {

	// 唯一ID
	private String UID;
	// 服务器ID
	private short gsid;
	
	// 玩家昵称
	private String nickname;
	// 头像图标ID
	private int headIco;
	
	// 区域
	private String country;
	
	// 游戏币
	private int currency;
	// 充值币
	private int gold;
	
	
	// 创建时间 (单位：毫秒)
	private long createTime;
	
	// 上次下线时间 (单位：毫秒)
	private long lastLogoutTime;
	
	
	
	public void wrap( PlayerDataDto dto ){
		
		this.UID 		= dto.getUid();
		this.gsid		= dto.getGsid();
		this.nickname 	= dto.getNickname();
		this.headIco	= dto.getHeadIco();
		this.country	= dto.getCountry();
		this.currency	= dto.getCurrency();
		this.gold		= dto.getGold();
		this.setCreateTime(dto.getCreateTime());
		this.lastLogoutTime = dto.getLastLogoutTime();
	}
	
	public void update( PlayerDataDto dto ) {
		dto.setUid(UID);
		dto.setGsid(gsid);
		dto.setNickname(nickname);
		dto.setHeadIco(headIco);
		dto.setCountry(country);
		dto.setCurrency(currency);
		dto.setGold(gold);
		dto.setCreateTime(createTime);
		dto.setLastLogoutTime(lastLogoutTime);
	}
	
	
	public String getUID() {
		return UID;
	}
	public void setUID(String uID) {
		UID = uID;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public int getHeadIco() {
		return headIco;
	}
	public void setHeadIco(int headIco) {
		this.headIco = headIco;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public int getCurrency() {
		return currency;
	}
	public void setCurrency(int currency) {
		this.currency = currency;
	}
	public int getGold() {
		return gold;
	}
	public void setGold(int gold) {
		this.gold = gold;
	}
	public long getLastLogoutTime() {
		return lastLogoutTime;
	}
	public void setLastLogoutTime(long lastLogoutTime) {
		this.lastLogoutTime = lastLogoutTime;
	}
	public short getGsid() {
		return gsid;
	}
	public void setGsid(short gsid) {
		this.gsid = gsid;
	}
	public long getCreateTime() {
		return createTime;
	}
	public void setCreateTime(long createTime) {
		this.createTime = createTime;
	}
	
	
	
	
}
