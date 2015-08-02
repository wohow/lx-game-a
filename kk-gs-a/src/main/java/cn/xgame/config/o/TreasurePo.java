package cn.xgame.config.o;import java.util.Map;import x.javaplus.string.StringUtil;public class TreasurePo {	public final int id;	public final String name;	public final int control;	public final int perception;	public final int affinity;	public final String answers;	public final String askings;

	public TreasurePo( TreasurePo clone ){		this.id = clone.id;		this.name = clone.name;		this.control = clone.control;		this.perception = clone.perception;		this.affinity = clone.affinity;		this.answers = clone.answers;		this.askings = clone.askings;

	}	public TreasurePo( Map<String, String> data ){		id = Integer.parseInt( StringUtil.convertNumberString( data.get("id") ) );		name = data.get("name");		control = Integer.parseInt( StringUtil.convertNumberString( data.get("control") ) );		perception = Integer.parseInt( StringUtil.convertNumberString( data.get("perception") ) );		affinity = Integer.parseInt( StringUtil.convertNumberString( data.get("affinity") ) );		answers = data.get("answers");		askings = data.get("askings");

	}}