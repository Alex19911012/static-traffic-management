package ht.statictrafficmanagement.base.entity;

import java.io.Serializable;
import java.util.Arrays;

public class TaskDataInfo implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 8892000314563578124L;
	private Integer TaskID;
	private Integer AlisLen;
	private String AlisData;
	private Integer PathListLen;
	private Integer[] PathList;
	public Integer getTaskID() {
		return TaskID;
	}
	public void setTaskID(Integer taskID) {
		this.TaskID = taskID;
	}
	public Integer getAlisLen() {
		return AlisLen;
	}
	public void setAlisLen(Integer alisLen) {
		AlisLen = alisLen;
	}
	public String getAlisData() {
		return AlisData;
	}
	public void setAlisData(String alisData) {
		AlisData = alisData;
	}
	public Integer getPathListLen() {
		return PathListLen;
	}
	public void setPathListLen(Integer pathListLen) {
		PathListLen = pathListLen;
	}
	public Integer[] getPathList() {
		return PathList;
	}
	public void setPathList(Integer[] pathList) {
		PathList = pathList;
	}
	@Override
	public String toString() {
		return "TaskDataInfo [taskID=" + TaskID + ", AlisLen=" + AlisLen + ", AlisData=" + AlisData + ", PathListLen="
				+ PathListLen + ", PathList=" + Arrays.toString(PathList) + "]";
	}
	
	
	
	
}
