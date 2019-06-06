package ht.statictrafficmanagement.base.vo;

import java.io.Serializable;

public class TaskDB implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4665727798145419515L;
	private Integer TaskID;
	private String AlisData;
	private String PathList;
	public Integer getTaskID() {
		return TaskID;
	}
	public void setTaskID(Integer taskID) {
		TaskID = taskID;
	}
	public String getAlisData() {
		return AlisData;
	}
	public void setAlisData(String alisData) {
		AlisData = alisData;
	}
	public String getPathList() {
		return PathList;
	}
	public void setPathList(String pathList) {
		PathList = pathList;
	}
	@Override
	public String toString() {
		return "TaskDB [TaskID=" + TaskID + ", AlisData=" + AlisData + ", PathList=" + PathList + "]";
	}
	
	

}
