package ht.statictrafficmanagement.base.entity;

import java.io.Serializable;
import java.util.Arrays;

public class PathDataInfo implements Serializable{
	
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

	
}
