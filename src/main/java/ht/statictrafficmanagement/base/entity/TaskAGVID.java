package ht.statictrafficmanagement.base.entity;

import java.io.Serializable;
import java.util.Arrays;

public class TaskAGVID implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -4097787924886664831L;
	private Integer[] TaskID;
	private Integer[] AGVID;
	public Integer[] getTaskID() {
		return TaskID;
	}
	public void setTaskID(Integer[] taskID) {
		TaskID = taskID;
	}
	public Integer[] getAGVID() {
		return AGVID;
	}
	public void setAGVID(Integer[] aGVID) {
		AGVID = aGVID;
	}
	@Override
	public String toString() {
		return "TaskAGVID [TaskID=" + Arrays.toString(TaskID) + ", AGVID=" + Arrays.toString(AGVID) + "]";
	}
	
	
}
