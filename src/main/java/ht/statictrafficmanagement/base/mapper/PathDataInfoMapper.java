package ht.statictrafficmanagement.base.mapper;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import ht.statictrafficmanagement.base.entity.PathDataInfo;
import ht.statictrafficmanagement.base.vo.PathDB;


/**
 * 这是处理用户数据的持久层接口
 * @author Alex
 *
 */
public interface PathDataInfoMapper {
	/**
	 * 插入路径数据
	 */
	Integer insert(PathDB pathDB);

	
	/**
	 * 删除所有路径数据
	 * @return
	 */
	Integer delete(int pathID);	
	
	
	/**
	 * 得到所有路径数据
	 * @return
	 */
	List<PathDB> getPahtDBList();
	
}
