package cn.xgame.utils.runnable.task;


import cn.xgame.a.player.PlayerManager;
import cn.xgame.a.world.WorldManager;
import cn.xgame.utils.Logs;
import cn.xgame.utils.runnable.IThread;


/**
 * 每日处理 线程
 * @author Administrator
 *
 */
public class DailyHandleImp extends IThread{
	
	@Override
	public void run() {
		Logs.debug( "每日更新 --> 开始" );
		try {
			
			// 刷新每个玩家的 每日数据
			PlayerManager.o.allDailyHandle();
			
			// 更新星球体制
			WorldManager.o.runUpdateInstitution();
			
			// 更新星球常规副本
			WorldManager.o.updateGeneralEctype();
			
		} catch (Exception e) {
			Logs.error( "DailyHandleImp:" , e );
		}
		Logs.debug( "每日更新 --> 结束" );
	}

}
