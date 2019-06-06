package ht.statictrafficmanagement.base.entity;

import java.io.Serializable;
import java.nio.ByteBuffer;
import java.util.List;

import ht.statictrafficmanagement.base.MsgType;
import ht.statictrafficmanagement.base.UniquenessIDMessage;
import ht.statictrafficmanagement.base.communication.NeedConfirmMessage;


public class TaskExecMessage extends UniquenessIDMessage implements NeedConfirmMessage,Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 586664079311260252L;
	private Integer TaskNum;
	private List<TaskInfo> TaskList;
	public Integer getTaskNum() {
		return TaskNum;
	}
	
	public List<TaskInfo> getTaskList() {
		return TaskList;
	}
	public void setTaskList(List<TaskInfo> taskList) {
		TaskList = taskList;
		TaskNum = taskList.size();
	}
	@Override
	public String toString() {
		return "TaskExecMessage {TaskNum=" + TaskNum + ", TaskList=" + TaskList + "}";
	}
	@Override
	public void decode(byte[] bytes) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public byte[] encode() {
		ByteBuffer byteBuffer = ByteBuffer.allocate(12 + 52 * TaskNum);
        byteBuffer.putLong(messageId);
        byteBuffer.putInt(TaskNum);
        for (TaskInfo r : TaskList) {
            byteBuffer.put(r.encode());
        }
        int position = byteBuffer.position();
        byte[] bytes = new byte[position];
        byteBuffer.flip();
        byteBuffer.get(bytes);
        return bytes;
	}
	@Override
	public byte getMessageType() {
		return 5;
	}
	
}
