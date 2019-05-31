package ht.statictrafficmanagement.base.entity;

import java.io.Serializable;
import java.nio.ByteBuffer;
import java.util.Arrays;

import ht.statictrafficmanagement.base.UniquenessIDMessage;
import ht.statictrafficmanagement.base.communication.NeedConfirmMessage;

public class PathDataInfo extends UniquenessIDMessage implements NeedConfirmMessage,Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2069791507413746517L;
	private Integer PathID;
	private Integer NodeListLen;
	private Integer[] NodeList;
	public Integer getPathID() {
		return PathID;
	}
	public void setPathID(Integer pathID) {
		PathID = pathID;
	}
	public Integer[] getNodeList() {
		return NodeList;
	}
	public void setNodeList(Integer[] nodeList) {
		NodeList = nodeList;
	}
	public Integer getNodeListLen() {
		return NodeListLen;
	}
	public void setNodeListLen(Integer nodeListLen) {
		NodeListLen = nodeListLen;
	}
	@Override
	public String toString() {
		return "PathDataInfo [PathID=" + PathID + ", NodeListLen=" + NodeListLen + ", NodeList="
				+ Arrays.toString(NodeList) + "]";
	}
	@Override
	public void decode(byte[] bytes) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public byte[] encode() {
		ByteBuffer byteBuffer = ByteBuffer.allocate(8 + NodeListLen * 4);
        byteBuffer.putInt(PathID);
        byteBuffer.putInt(NodeListLen);
        for (int i = 0; i < NodeListLen; i++) {
            byteBuffer.putInt(NodeList[i]);
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
