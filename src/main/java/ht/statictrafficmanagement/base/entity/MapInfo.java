package ht.statictrafficmanagement.base.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class MapInfo implements Serializable{
	private List<NodeMessage> nodes;
	private List<SegmentMessage> segments;
	private List<MagPoint> magPoints;
	private List<ActionPoint> actionPoints;
	private List<AGVInfo> agvInfos;
	public List<NodeMessage> getNodes() {
		return nodes;
	}
	public void setNodes(List<NodeMessage> nodes) {
		this.nodes = nodes;
	}
	public List<SegmentMessage> getSegments() {
		return segments;
	}
	public void setSegments(List<SegmentMessage> segments) {
		this.segments = segments;
	}
	public List<MagPoint> getMagPoints() {
		return magPoints;
	}
	public void setMagPoints(List<MagPoint> magPoints) {
		this.magPoints = magPoints;
	}
	public List<ActionPoint> getActionPoints() {
		return actionPoints;
	}
	public void setActionPoints(List<ActionPoint> actionPoints) {
		this.actionPoints = actionPoints;
	}
	public List<AGVInfo> getAgvInfos() {
		return agvInfos;
	}
	public void setAgvInfos(List<AGVInfo> agvInfos) {
		this.agvInfos = agvInfos;
	}
	@Override
	public String toString() {
		return "MapInfo [nodes=" + nodes + ", segments=" + segments + ", magPoints=" + magPoints + ", actionPoints="
				+ actionPoints + ", agvInfos=" + agvInfos + "]";
	}
	
	
}
