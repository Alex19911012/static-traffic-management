package ht.statictrafficmanagement.base.entity;

import java.io.Serializable;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import ht.statictrafficmanagement.base.UniquenessIDMessage;
import ht.statictrafficmanagement.base.communication.NeedConfirmMessage;

public class TaskDataMessage extends UniquenessIDMessage implements NeedConfirmMessage,Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -2903995759424220943L;
	private int TaskNum;
	private List<TaskDataInfo> TaskList;
	private int PathNum;
	private List<PathDataInfo> PathList;
	
	public List<TaskDataInfo> getTaskList() {
		return TaskList;
	}
	public void setTaskList(List<TaskDataInfo> taskList) {
		TaskList = taskList;
		TaskNum = TaskList.size();
	}
	public List<PathDataInfo> getPathList() {
		return PathList;
	}
	public void setPathList(List<PathDataInfo> pathList) {
		PathList = pathList;
		PathNum = PathList.size();
	}
	
	public int getTaskNum() {
		return TaskNum;
	}
	public void setTaskNum(int taskNum) {
		this.TaskNum = taskNum;
		
	}
	public int getPathNum() {
		return PathNum;
	}
	public void setPathNum(int pathNum) {
		this.PathNum = pathNum;
		
	}
	@Override
	public String toString() {
		return "TaskDataMessage {TaskNum=" + TaskNum + ", TaskList=" + Arrays.toString(TaskList.toArray())  +", PathNum=" + PathNum +  ", PathList=" +Arrays.toString(PathList.toArray()) + "}";
	}
	@Override
	public void decode(byte[] bytes) {
//		ByteBuffer buffer = ByteBuffer.allocate(bytes.length);
//        buffer.put(bytes);
//        buffer.flip();
//        messageId = buffer.getLong();
//        TaskNum = buffer.getInt();
//        List<TaskDataInfo> TaskDataInfos = new ArrayList<>();
//        byte[] subData;
//        for (int i = 0; i < TaskNum; i++) {
//            subData = new byte[70];
//            buffer.get(subData);
//            TaskDataInfo TaskDataInfo = new TaskDataInfo();
//            TaskDataInfo.decode(subData);
//            String miscellaneous = getMiscellaneousFromBytes(buffer);
//            TaskDataInfo.setMiscellaneous(miscellaneous);
//            TaskDataInfos.add(agvInfo);
//        }
//        this.agvInfos = agvInfos;
//
//        nodeNum = buffer.getInt();
//        List<NodeMessage> nodes = new ArrayList<>();
//        for (int i = 0; i < nodeNum; i++) {
//            subData = new byte[59];
//            buffer.get(subData);
//            NodeMessage node = new NodeMessage();
//            node.decode(subData);
//            String miscellaneous = getMiscellaneousFromBytes(buffer);
//            node.setMiscellaneous(miscellaneous);
//            nodes.add(node);
//        }
//        this.nodes = nodes;
//
//        segmentNum = buffer.getInt();
//        List<SegmentMessage> segments = new ArrayList<>();
//        for (int i = 0; i < segmentNum; i++) {
//            subData = new byte[67];
//            buffer.get(subData);
//            SegmentMessage segment = new SegmentMessage();
//            segment.decode(subData);
//            String miscellaneous = getMiscellaneousFromBytes(buffer);
//            segment.setMiscellaneous(miscellaneous);
//            segments.add(segment);
//        }
//        this.segments = segments;
//
//        actionPointNum = buffer.getInt();
//        List<ActionPoint> actionPoints = new ArrayList<>();
//        for (int i = 0; i < actionPointNum; i++) {
//            subData = new byte[60];
//            buffer.get(subData);
//            ActionPoint actionPoint = new ActionPoint();
//            actionPoint.decode(subData);
//            actionPoints.add(actionPoint);
//        }
//        this.actionPoints = actionPoints;
//
//        magNum = buffer.getInt();
//        List<MagPoint> magPoints = new ArrayList<>();
//        for (int i = 0; i < magNum; i++) {
//            subData = new byte[25];
//            buffer.get(subData);
//            MagPoint magPoint = new MagPoint();
//            magPoint.decode(subData);
//            magPoints.add(magPoint);
//        }
//        this.magPoints = magPoints;
//
//        mapVersion = buffer.getInt();
    

		
	}
	
	
	@Override
	public byte[] encode() {
		int TaskDataInfoLength = 0;
        for (TaskDataInfo TaskDataInfo : TaskList) {
        	TaskDataInfoLength += TaskDataInfo.encode().length;
        }

        int PathDataInfoLength = 0;
        for (PathDataInfo PathDataInfo : PathList) {
        	PathDataInfoLength += PathDataInfo.encode().length;
        }
       
        ByteBuffer buffer = ByteBuffer.allocate(12  + TaskDataInfoLength + 4 +PathDataInfoLength );
        buffer.putLong(messageId);
        System.out.println(TaskNum);
        buffer.putInt(TaskNum);
        for (TaskDataInfo TaskDataInfo : TaskList) {
            buffer.put(TaskDataInfo.encode());
        }
        buffer.putInt(PathNum);
        for (PathDataInfo PathDataInfo : PathList) {
            buffer.put(PathDataInfo.encode());
        }
        
        int position = buffer.position();
        buffer.flip();
        byte[] data = new byte[position];
        buffer.get(data);
        return data;
	}
	@Override
	public byte getMessageType() {
		
		return 3;
	}
	@Override
	public long getMessageId() {
		
		return 0;
	}
	
}
