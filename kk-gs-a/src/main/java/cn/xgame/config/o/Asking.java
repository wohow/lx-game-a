package cn.xgame.config.o;import java.util.Map;import x.javaplus.string.StringUtil;public class Asking {	public final int id;	public final String name;	public final String des;	public final String icon;	public final byte type;	public final byte atttype;	public final int intvalue;	public final float pctvalue;

	public Asking( Asking clone ){		this.id = clone.id;		this.name = clone.name;		this.des = clone.des;		this.icon = clone.icon;		this.type = clone.type;		this.atttype = clone.atttype;		this.intvalue = clone.intvalue;		this.pctvalue = clone.pctvalue;

	}	public Asking( Map<String, String> data ){		id = Integer.parseInt( StringUtil.convertNumberString( data.get("id") ) );		name = data.get("name");		des = data.get("des");		icon = data.get("icon");		type = Byte.parseByte( StringUtil.convertNumberString( data.get("type") ) );		atttype = Byte.parseByte( StringUtil.convertNumberString( data.get("atttype") ) );		intvalue = Integer.parseInt( StringUtil.convertNumberString( data.get("intvalue") ) );		pctvalue = Float.parseFloat( StringUtil.convertNumberString( data.get("pctvalue") ) );

	}}