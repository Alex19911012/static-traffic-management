package ht.statictrafficmanagement.base.entity;

import java.io.Serializable;
import java.nio.ByteBuffer;
import java.util.Arrays;


import ht.statictrafficmanagement.base.UniquenessIDMessage;
import ht.statictrafficmanagement.base.communication.NeedConfirmMessage;

public class TaskDataInfo extends UniquenessIDMessage implements NeedConfirmMessage,Serializable{
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
		AlisLen  = AlisData.length();
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
		PathListLen = PathList.length;
	}
	@Override
	public String toString() {
		return "TaskDataInfo [taskID=" + TaskID + ", AlisLen=" + AlisLen + ", AlisData=" + AlisData + ", PathListLen="
				+ PathListLen + ", PathList=" + Arrays.toString(PathList).replace("[", "").replace("]", "").replaceAll(" ", "") + "]";
	}
	@Override
	public void decode(byte[] bytes) {
		 ByteBuffer byteBuffer = ByteBuffer.allocate(bytes.length);
	        byteBuffer.put(bytes);
	        byteBuffer.flip();
	        messageId = byteBuffer.getLong();
	        TaskID = byteBuffer.getInt();
	        AlisLen = byteBuffer.getInt();
	        
	        PathListLen = byteBuffer.getInt();
	        PathList = new Integer[PathListLen];
	        for (int i = 0; i < PathListLen; i++) {
	        	PathList[i] = byteBuffer.getInt();
	        }
	        
		
	}
	@Override
	public byte[] encode() {
		ByteBuffer byteBuffer = ByteBuffer.allocate(8 + AlisLen * 2 + 4 + PathListLen * 4);
        byteBuffer.putInt(TaskID);
        byteBuffer.putInt(AlisLen);
        byteBuffer.put(AlisData.getBytes());
        byteBuffer.putInt(PathListLen);
        for (int i = 0; i < PathListLen; i++) {
            byteBuffer.putInt(PathList[i]);
        }
       
        byte[] data = new byte[byteBuffer.position()];
        byteBuffer.flip();
        byteBuffer.get(data);
        return data;
	}
	@Override
	public byte getMessageType() {
		
		return 3;
	}
	
	
	
	
}
