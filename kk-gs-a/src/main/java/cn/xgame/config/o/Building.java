package cn.xgame.config.o;import java.util.Map;import x.javaplus.string.StringUtil;public class Building {	public final int id;	public final String name;	public final String intro;	public final String des;	public final String icon;	public final String model;	public final String res;	public final int time;	public final int tpye;	public final byte room;	public final int ProduceValue;	public final String Produce;	public final int cache;

	public Building( Building clone ){		this.id = clone.id;		this.name = clone.name;		this.intro = clone.intro;		this.des = clone.des;		this.icon = clone.icon;		this.model = clone.model;		this.res = clone.res;		this.time = clone.time;		this.tpye = clone.tpye;		this.room = clone.room;		this.ProduceValue = clone.ProduceValue;		this.Produce = clone.Produce;		this.cache = clone.cache;

	}	public Building( Map<String, String> data ){		id = Integer.parseInt( StringUtil.convertNumberString( data.get("id") ) );		name = data.get("name");		intro = data.get("intro");		des = data.get("des");		icon = data.get("icon");		model = data.get("model");		res = data.get("res");		time = Integer.parseInt( StringUtil.convertNumberString( data.get("time") ) );		tpye = Integer.parseInt( StringUtil.convertNumberString( data.get("tpye") ) );		room = Byte.parseByte( StringUtil.convertNumberString( data.get("room") ) );		ProduceValue = Integer.parseInt( StringUtil.convertNumberString( data.get("ProduceValue") ) );		Produce = data.get("Produce");		cache = Integer.parseInt( StringUtil.convertNumberString( data.get("cache") ) );

	}}