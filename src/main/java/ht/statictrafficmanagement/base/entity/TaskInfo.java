package ht.statictrafficmanagement.base.entity;

import java.io.Serializable;
import java.nio.ByteBuffer;
import java.util.Arrays;

import ht.statictrafficmanagement.base.MsgType;
import ht.statictrafficmanagement.base.NotUniquenIDMessage;
import ht.statictrafficmanagement.base.Utils;


public class TaskInfo extends NotUniquenIDMessage implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 6201465330596084764L;
	private Integer TaskId;
	private Integer PathNum;
	private Integer StartNode;
	private int agvId;
	private Byte[] actionTypes;
	private Integer[] actionContents;
	public Integer getTaskId() {
		return TaskId;
	}
	public void setTaskId(Integer taskId) {
		TaskId = taskId;
	}
	public Integer getPathNum() {
		return PathNum;
	}
	public void setPathNum(Integer pathNum) {
		PathNum = pathNum;
	}
	public Integer getStartNode() {
		return StartNode;
	}
	public void setStartNode(Integer startNode) {
		StartNode = startNode;
	}
	
	public int getAgvId() {
		return agvId;
	}
	public void setAgvId(int agvId) {
		this.agvId = agvId;
	}
	public Byte[] getActionTypes() {
		return actionTypes;
	}
	public void setActionTypes(Byte[] actionTypes) {
		this.actionTypes = actionTypes;
	}
	public Integer[] getActionContents() {
		return actionContents;
	}
	public void setActionContents(Integer[] actionContents) {
		this.actionContents = actionContents;
	}
	
	@Override
	public String toString() {
		return "TaskInfo [TaskId=" + TaskId + ", PathNum=" + PathNum + ", StartNode=" + StartNode + ", agvId=" + agvId
				+ ", actionTypes=" + Arrays.toString(actionTypes).replace("[", "").replace("]", "") + ", actionContents="
				+ Arrays.toString(actionContents).replace("[", "").replace("]", "") + "]";
	}
	@Override
	public void decode(byte[] bytes) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public byte[] encode() {
		ByteBuffer byteBuffer = ByteBuffer.allocate(52);
        byteBuffer.putInt(TaskId);
        byteBuffer.putInt(PathNum);
        byteBuffer.putInt(StartNode);
        byte[] actionTypeArray = Utils.getActionTypeArrayFormString(Arrays.toString(actionTypes).replace("[", "").replace("]", "").replaceAll(" ", ""));
        
        for (Byte b : actionTypeArray) {
            byteBuffer.put(b);
        }
        int[] actionContentArray = Utils.getActionContentsArrayFormString(Arrays.toString(actionContents).replace("[", "").replace("]", "").replaceAll(" ", ""));
        
        for (int actionContent : actionContentArray) {
            byteBuffer.putInt(actionContent);
        }

        byte[] bytes = new byte[byteBuffer.position()];
        byteBuffer.flip();
        byteBuffer.get(bytes);

        return bytes;
	}
	@Override
	public byte getMessageType() {
		return MsgType.ROUTE_TASK_INFO;
	}
	
}
