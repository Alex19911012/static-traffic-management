package ht.statictrafficmanagement.base.vo;

import java.io.Serializable;

public class PathDB implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1313672370347076912L;
	private int PathID;
	private String NodeList;
	public int getPathID() {
		return PathID;
	}
	public void setPathID(int pathID) {
		PathID = pathID;
	}
	public String getNodeList() {
		return NodeList;
	}
	public void setNodeList(String nodeList) {
		NodeList = nodeList;
	}
	@Override
	public String toString() {
		return "PathDB [PathID=" + PathID + ", NodeList=" + NodeList + "]";
	}
	
	
	
}
