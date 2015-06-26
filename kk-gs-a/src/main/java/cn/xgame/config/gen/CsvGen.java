package cn.xgame.config.gen;import java.util.List;import java.util.Map;import x.javaplus.collections.Lists;import x.javaplus.csv.util.Csv;import cn.xgame.a.system.SystemCfg;import cn.xgame.config.o.Building;
import cn.xgame.config.o.Captain;
import cn.xgame.config.o.Item;
import cn.xgame.config.o.Material;
import cn.xgame.config.o.Ship;
import cn.xgame.config.o.Stars;
import cn.xgame.config.o.Weapon;

public class CsvGen {	public static final List<Building> buildings = Lists.newArrayList();	public static final List<Captain> captains = Lists.newArrayList();	public static final List<Item> items = Lists.newArrayList();	public static final List<Material> materials = Lists.newArrayList();	public static final List<Ship> ships = Lists.newArrayList();	public static final List<Stars> starss = Lists.newArrayList();	public static final List<Weapon> weapons = Lists.newArrayList();
	public static void load(){		loadBuilding( "building.csv" );		loadCaptain( "captain.csv" );		loadItem( "item.csv" );		loadMaterial( "material.csv" );		loadShip( "ship.csv" );		loadStars( "stars.csv" );		loadWeapon( "weapon.csv" );
	}	private static void loadBuilding( String file ){				Csv csv = new Csv( SystemCfg.FILE_NAME + "/" + file );				for( Map<String, String> data : csv.getValues() ){			Building o = new Building(data);			buildings.add( o );		}	}	private static void loadCaptain( String file ){				Csv csv = new Csv( SystemCfg.FILE_NAME + "/" + file );				for( Map<String, String> data : csv.getValues() ){			Captain o = new Captain(data);			captains.add( o );		}	}	private static void loadItem( String file ){				Csv csv = new Csv( SystemCfg.FILE_NAME + "/" + file );				for( Map<String, String> data : csv.getValues() ){			Item o = new Item(data);			items.add( o );		}	}	private static void loadMaterial( String file ){				Csv csv = new Csv( SystemCfg.FILE_NAME + "/" + file );				for( Map<String, String> data : csv.getValues() ){			Material o = new Material(data);			materials.add( o );		}	}	private static void loadShip( String file ){				Csv csv = new Csv( SystemCfg.FILE_NAME + "/" + file );				for( Map<String, String> data : csv.getValues() ){			Ship o = new Ship(data);			ships.add( o );		}	}	private static void loadStars( String file ){				Csv csv = new Csv( SystemCfg.FILE_NAME + "/" + file );				for( Map<String, String> data : csv.getValues() ){			Stars o = new Stars(data);			starss.add( o );		}	}	private static void loadWeapon( String file ){				Csv csv = new Csv( SystemCfg.FILE_NAME + "/" + file );				for( Map<String, String> data : csv.getValues() ){			Weapon o = new Weapon(data);			weapons.add( o );		}	}
	public static Building getBuilding( int x ){		for( Building o : buildings ){			if( o.id == x )				return o;		}		return null;	}	public static Captain getCaptain( int x ){		for( Captain o : captains ){			if( o.id == x )				return o;		}		return null;	}	public static Item getItem( int x ){		for( Item o : items ){			if( o.ID == x )				return o;		}		return null;	}	public static Material getMaterial( int x ){		for( Material o : materials ){			if( o.id == x )				return o;		}		return null;	}	public static Ship getShip( short x ){		for( Ship o : ships ){			if( o.id == x )				return o;		}		return null;	}	public static Stars getStars( short x ){		for( Stars o : starss ){			if( o.id == x )				return o;		}		return null;	}	public static Weapon getWeapon( int x ){		for( Weapon o : weapons ){			if( o.id == x )				return o;		}		return null;	}
}