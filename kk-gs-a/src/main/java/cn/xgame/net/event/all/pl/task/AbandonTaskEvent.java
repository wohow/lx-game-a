package cn.xgame.net.event.all.pl.task;

import io.netty.buffer.ByteBuf;

import java.io.IOException;

import x.javaplus.collections.Lists;
import x.javaplus.util.ErrorCode;

import cn.xgame.a.player.task.TaskControl;
import cn.xgame.a.player.task.classes.ITask;
import cn.xgame.a.player.task.classes.TaskType;
import cn.xgame.a.player.u.Player;
import cn.xgame.net.event.IEvent;

/**
 * 放弃任务
 * @author deng		
 * @date 2015-10-25 上午2:52:16
 */
public class AbandonTaskEvent extends IEvent {

	@Override
	public void run(Player player, ByteBuf data) throws IOException {
		
		int tid = data.readInt();
		
		ErrorCode code = null;
		try {
			TaskControl taskControl = player.getTasks();
			
			ITask task = taskControl.getYetInTask(tid);
			
			if( task == null )
				throw new Exception(ErrorCode.SUCCEED.name());
			
			// 这里直接删除
			taskControl.removeYetTask( tid );
			
			// 如果不是日常任务 那么就再次放入到可接任务列表
			if( task.type() == TaskType.EVERYDAY ){
			}else{
				taskControl.addCanTask( Lists.newArrayList(tid) );
			}
			
			code = ErrorCode.SUCCEED;
		} catch (Exception e) {
			code = ErrorCode.valueOf( e.getMessage() );
		}
		
		ByteBuf buffer = buildEmptyPackage( player.getCtx(), 12 );
		buffer.writeShort( code.toNumber() );
		if( code == ErrorCode.SUCCEED ){
			buffer.writeInt(tid);
		}
		sendPackage( player.getCtx(), buffer );
	}

}
