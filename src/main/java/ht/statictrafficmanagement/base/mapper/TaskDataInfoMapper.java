package ht.statictrafficmanagement.base.mapper;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import ht.statictrafficmanagement.base.entity.PathDataInfo;
import ht.statictrafficmanagement.base.entity.TaskDataInfo;
import ht.statictrafficmanagement.base.vo.PathDB;
import ht.statictrafficmanagement.base.vo.TaskDB;


/**
 * 这是处理用户数据的持久层接口
 * @author Alex
 *
 */
public interface TaskDataInfoMapper {
	/**
	 * 插入任务数据
	 */
	Integer insert(TaskDB taskDB);

	
	/**
	 * 删除所有任务数据
	 * @return
	 */
	Integer delete(int taskID);	
	
	/**
	 * 得到所有任务数据
	 * @return
	 */
	List<TaskDB> getTaskDBList();

	
}
