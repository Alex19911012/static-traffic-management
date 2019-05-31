package ht.statictrafficmanagement.base.entity;

import java.io.Serializable;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import ht.statictrafficmanagement.base.MsgType;
import ht.statictrafficmanagement.base.UniquenessIDMessage;
import ht.statictrafficmanagement.base.communication.NeedConfirmMessage;



public class MapInfo extends UniquenessIDMessage implements NeedConfirmMessage,Serializable{
	private int agvNum;
	private List<AGVInfo> agvInfos;
	private int magNum;
	private List<MagPoint> magPoints;
	private int nodeNum;
	private List<NodeMessage> nodes;
	private int actionPointNum;
	private List<ActionPoint> actionPoints;
	private int segmentNum;
	private List<SegmentMessage> segments;
	private int mapVersion;
	
	public int getAgvNum() {
		return agvNum;
	}
	
	public List<AGVInfo> getAgvInfos() {
		return agvInfos;
	}
	public void setAgvInfos(List<AGVInfo> agvInfos) {
		this.agvInfos = agvInfos;
		agvNum = agvInfos.size();
	}
	public int getMagNum() {
		return magNum;
	}
	public List<MagPoint> getMagPoints() {
		return magPoints;
	}
	public void setMagPoints(List<MagPoint> magPoints) {
		this.magPoints = magPoints;
		magNum = magPoints.size();
	}
	public int getNodeNum() {
		return nodeNum;
	}
	public List<NodeMessage> getNodes() {
		return nodes;
	}
	public void setNodes(List<NodeMessage> nodes) {
		this.nodes = nodes;
		nodeNum = nodes.size();
	}
	public int getActionPointNum() {
		return actionPointNum;
	}
	public List<ActionPoint> getActionPoints() {
		return actionPoints;
	}
	public void setActionPoints(List<ActionPoint> actionPoints) {
		this.actionPoints = actionPoints;
		actionPointNum = actionPoints.size();
	}
	public int getSegmentNum() {
		return segmentNum;
	}
	public List<SegmentMessage> getSegments() {
		return segments;
	}
	public void setSegments(List<SegmentMessage> segments) {
		this.segments = segments;
		segmentNum = segments.size();
	}
	public int getMapVersion() {
		return mapVersion;
	}
	public void setMapVersion(int mapVersion) {
		this.mapVersion = mapVersion;
	}

	@Override
	public void decode(byte[] bytes) {
		 ByteBuffer buffer = ByteBuffer.allocate(bytes.length);
	        buffer.put(bytes);
	        buffer.flip();
	        messageId = buffer.getLong();
	        agvNum = buffer.getInt();
	        List<AGVInfo> agvInfos = new ArrayList<>();
	        byte[] subData;
	        for (int i = 0; i < agvNum; i++) {
	            subData = new byte[70];
	            buffer.get(subData);
	            AGVInfo agvInfo = new AGVInfo();
	            agvInfo.decode(subData);
	            String miscellaneous = getMiscellaneousFromBytes(buffer);
	            agvInfo.setMiscellaneous(miscellaneous);
	            agvInfos.add(agvInfo);
	        }
	        this.agvInfos = agvInfos;

	        nodeNum = buffer.getInt();
	        List<NodeMessage> nodes = new ArrayList<>();
	        for (int i = 0; i < nodeNum; i++) {
	            subData = new byte[59];
	            buffer.get(subData);
	            NodeMessage node = new NodeMessage();
	            node.decode(subData);
	            String miscellaneous = getMiscellaneousFromBytes(buffer);
	            node.setMiscellaneous(miscellaneous);
	            nodes.add(node);
	        }
	        this.nodes = nodes;

	        segmentNum = buffer.getInt();
	        List<SegmentMessage> segments = new ArrayList<>();
	        for (int i = 0; i < segmentNum; i++) {
	            subData = new byte[67];
	            buffer.get(subData);
	            SegmentMessage segment = new SegmentMessage();
	            segment.decode(subData);
	            String miscellaneous = getMiscellaneousFromBytes(buffer);
	            segment.setMiscellaneous(miscellaneous);
	            segments.add(segment);
	        }
	        this.segments = segments;

	        actionPointNum = buffer.getInt();
	        List<ActionPoint> actionPoints = new ArrayList<>();
	        for (int i = 0; i < actionPointNum; i++) {
	            subData = new byte[60];
	            buffer.get(subData);
	            ActionPoint actionPoint = new ActionPoint();
	            actionPoint.decode(subData);
	            actionPoints.add(actionPoint);
	        }
	        this.actionPoints = actionPoints;

	        magNum = buffer.getInt();
	        List<MagPoint> magPoints = new ArrayList<>();
	        for (int i = 0; i < magNum; i++) {
	            subData = new byte[25];
	            buffer.get(subData);
	            MagPoint magPoint = new MagPoint();
	            magPoint.decode(subData);
	            magPoints.add(magPoint);
	        }
	        this.magPoints = magPoints;

	        mapVersion = buffer.getInt();
	    
		
	}
	
	private String getMiscellaneousFromBytes(ByteBuffer buffer) {
        int customDataNum = buffer.getInt();
        StringBuffer sb = new StringBuffer();
        for (int i=0 ;i<customDataNum;i++){
            int keyLength = buffer.getInt();
            byte[] keyBytes = new byte[keyLength];
            buffer.get(keyBytes);
            int valueLength = buffer.getInt();
            byte[] valueBytes = new byte[valueLength];
            buffer.get(valueBytes);
            String key = new String(keyBytes);
            String value = new String(valueBytes);
            sb.append(key);
            sb.append(":");
            sb.append(value);
            sb.append(";");
        }
        return sb.toString();
    }

	@Override
	public byte[] encode() {
		 	int agvInfoDataLength = 0;
	        for (AGVInfo agvInfo : agvInfos) {
	            agvInfoDataLength += agvInfo.encode().length;
	        }

	        int nodeDataLength = 0;
	        for (NodeMessage node : nodes) {
	            nodeDataLength += node.encode().length;
	        }
	        int segmentDataLength = 0;
	        for (SegmentMessage segment : segments) {
	            segmentDataLength += segment.encode().length;
	        }
	        ByteBuffer buffer = ByteBuffer.allocate(32 + agvInfoDataLength + magNum * 25 + nodeDataLength + actionPointNum * 60 + segmentDataLength);
	        buffer.putLong(messageId);
	        buffer.putInt(agvNum);
	        for (AGVInfo agvInfo : agvInfos) {
	            buffer.put(agvInfo.encode());
	        }
	        buffer.putInt(nodeNum);
	        for (NodeMessage node : nodes) {
	            buffer.put(node.encode());
	        }
	        buffer.putInt(segmentNum);
	        for (SegmentMessage segment : segments) {
	            buffer.put(segment.encode());
	        }
	        buffer.putInt(actionPointNum);
	        for (ActionPoint actionPoint : actionPoints) {
	            buffer.put(actionPoint.encode());
	        }
	        buffer.putInt(magNum);
	        for (MagPoint mag : magPoints) {
	            buffer.put(mag.encode());
	        }
	        buffer.putInt(mapVersion);
	        int position = buffer.position();
	        buffer.flip();
	        byte[] data = new byte[position];
	        buffer.get(data);
	        return data;
	}

	@Override
	public byte getMessageType() {
		return 1;
	}
	
	
	@Override
    public String toString() {
        return "MapInfoMessage{" +
                "messageId=" + messageId +
                ", agvNum=" + agvNum +
                ", agvInfo : " + Arrays.toString(agvInfos.toArray()) +
                ", magNum=" + magNum +
                ", magPoint : " + Arrays.toString(magPoints.toArray()) +
                ", nodeNum=" + nodeNum +
                ", node : " + Arrays.toString(nodes.toArray()) +
                ", actionPointNum=" + actionPointNum +
                ", actionPoint : " + Arrays.toString(actionPoints.toArray()) +
                ", segmentNum=" + segmentNum +
                ", segment : " + Arrays.toString(segments.toArray()) +
                ", mapVersion=" + mapVersion +
                '}';
    }
	

	


}
