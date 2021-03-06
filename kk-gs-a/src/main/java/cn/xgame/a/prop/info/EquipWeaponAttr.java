package cn.xgame.a.prop.info;

import java.util.List;

import x.javaplus.collections.Lists;
import x.javaplus.util.lua.Lua;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import cn.xgame.a.fighter.o.Attackattr;
import cn.xgame.a.prop.IProp;
import cn.xgame.a.prop.classes.Quality;
import cn.xgame.config.gen.CsvGen;
import cn.xgame.config.o.Equip_weaponPo;
import cn.xgame.config.o.ItemPo;
import cn.xgame.utils.LuaUtil;

/**
 * 舰船装备 - 武器&&防具 属性
 * @author deng		
 * @date 2015-6-18 下午1:49:18
 */
public class EquipWeaponAttr extends IProp{

	private static final int version = 1;
	
	private final Equip_weaponPo templet;
	
	// 当前耐久度
	private int currentDur;
	// 总耐久度
	private int maxDur;
	// 当前弹药量
	private int curAmmo;
	
	// 消耗能量
	private int energy;
	// 精密度
	private int accuracy;
	// 复杂度
	private int perplexity;
	// 战斗属性列表
	private List<Attackattr> battleAttrs = Lists.newArrayList();
	// 应答 - 问
	private List<Integer> askings = Lists.newArrayList();
	// 应答 - 答
	private List<Integer> answers = Lists.newArrayList();
	
	public EquipWeaponAttr( ItemPo item, int uid, int nid, int count, Quality quality ) {
		super( item, uid, nid, count, quality );
		templet 	= CsvGen.getEquip_weaponPo(nid);
	}
	
	private EquipWeaponAttr( EquipWeaponAttr clone ){
		super( clone );
		templet 	= clone.templet;
		energy		= clone.energy;
		accuracy	= clone.accuracy;
		perplexity	= clone.perplexity;
		currentDur 	= clone.currentDur;
		maxDur		= clone.maxDur;
		curAmmo		= clone.curAmmo;
		battleAttrs.addAll( clone.battleAttrs );
		askings.addAll( clone.askings );
		answers.addAll( clone.answers );
	}
	
	@Override
	public EquipWeaponAttr clone() { return new EquipWeaponAttr( this ); }
	
	public Equip_weaponPo templet() { return templet; }

	@Override
	public byte[] toAttachBytes() {
		ByteBuf buf = Unpooled.buffer( );
		Lua lua 	= LuaUtil.getDatabaseBufferForm();
		lua.getField( "SEquipAttr_ToBytes" ).call( 0, version, buf, this );
		return buf.array();
	}

	@Override
	public void wrapAttachBytes(byte[] bytes) {
		if( bytes == null ) return;
		ByteBuf buf = Unpooled.copiedBuffer(bytes);
		Lua lua 	= LuaUtil.getDatabaseBufferForm();
		lua.getField( "SEquipAttr_WrapBytes" ).call( 0, buf, this );
	}
	
	@Override
	public void randomAttachAttr() {
		LuaUtil.getGameData().getField( "randomAttachAttr" ).call( 0, this );
	}
	
	@Override
	public void buildTransformStream(ByteBuf buffer) {
		buffer.writeInt( energy );
		buffer.writeInt( accuracy );
		buffer.writeInt( perplexity );
		buffer.writeInt( currentDur );
		buffer.writeInt( maxDur );
		buffer.writeInt( curAmmo );
		buffer.writeByte( battleAttrs.size() );
		for( Attackattr a : battleAttrs )
			a.buildTransformStream( buffer );
		buffer.writeByte( answers.size() );
		for( int id : answers )
			buffer.writeInt(id);
		buffer.writeByte( askings.size() );
		for( int id : askings )
			buffer.writeInt(id);
	}
	
	/**
	 * 装满弹药
	 */
	public void fillupAmmo() {
		curAmmo = templet.surplus;
	}
	
	public int getCurrentDur() {
		return currentDur;
	}
	public void setCurrentDur(int currentDur) {
		this.currentDur = currentDur;
	}
	public void addCurrentDur( int value ) {
		this.currentDur += value;
		if( this.currentDur < 0 )
			this.currentDur = 0;
		if( this.currentDur > maxDur )
			this.currentDur = maxDur;
	}
	public int getEnergy() {
		return energy;
	}
	public void setEnergy(int energy) {
		this.energy = energy;
	}
	public int getAccuracy() {
		return accuracy;
	}
	public void setAccuracy(int accuracy) {
		this.accuracy = accuracy;
	}
	public int getPerplexity() {
		return perplexity;
	}
	public void setPerplexity(int perplexity) {
		this.perplexity = perplexity;
	}
	public int getMaxDur() {
		return maxDur;
	}
	public void setMaxDur(int maxDur) {
		this.maxDur = maxDur;
	}
	public int getCurAmmo() {
		return curAmmo;
	}
	public void setCurAmmo(int curAmmo) {
		this.curAmmo = curAmmo;
	}
	public List<Attackattr> getBattleAttrs() {
		return battleAttrs;
	}
	public void setBattleAttrs( byte type, float value ){
		battleAttrs.add( new Attackattr( type, value ) );
	}
	public List<Integer> getAskings() {
		return askings;
	}
	public List<Integer> getAnswers() {
		return answers;
	}

}
