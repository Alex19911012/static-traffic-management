package ht.statictrafficmanagement.base.entity;

import java.io.Serializable;
import java.util.Arrays;

public class AGVInfo implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 104508243799874528L;
	private Integer agvId;//id
	private String agvName;//Name
	private Byte agvType;//agvType
	private Double length;//length
	private Double width;//width
	private Double height;//height
	private Byte[] agvIp;//IP asiic
	private String agvIpI;//10进制ip用于前端显示
	private Integer udpPort;//tcpPort
	private Integer nodeId;//relatedNode
	private Double frontAngle;//frontAngle
	private Byte moveMode;//moveMode
	private Integer displayColor;//displayColor
	private String miscellaneous;//Miscellaneous
	private Double currentSpeed;//currentSpeed
	private Double POSITION_X;//POSITION_X
	private Double POSITION_Y;//POSITION_Y
	private String warningType;//warningType
	private String status;//status
	private String battery;//battery
	
	public Integer getAgvId() {
		return agvId;
	}
	public void setAgvId(Integer agvId) {
		this.agvId = agvId;
	}
	public String getAgvName() {
		return agvName;
	}
	public void setAgvName(String agvName) {
		this.agvName = agvName;
	}
	public Byte getAgvType() {
		return agvType;
	}
	public void setAgvType(Byte agvType) {
		this.agvType = agvType;
	}
	public Double getLength() {
		return length;
	}
	public void setLength(Double length) {
		this.length = length;
	}
	public Double getWidth() {
		return width;
	}
	public void setWidth(Double width) {
		this.width = width;
	}
	public Double getHeight() {
		return height;
	}
	public void setHeight(Double height) {
		this.height = height;
	}
	public Byte[] getAgvIp() {
		return agvIp;
	}
	public void setAgvIp(Byte[] agvIp) {
		this.agvIp = agvIp;
	}
	public Integer getUdpPort() {
		return udpPort;
	}
	public void setUdpPort(Integer udpPort) {
		this.udpPort = udpPort;
	}
	public Integer getNodeId() {
		return nodeId;
	}
	public void setNodeId(Integer nodeId) {
		this.nodeId = nodeId;
	}
	public Double getFrontAngle() {
		return frontAngle;
	}
	public void setFrontAngle(Double frontAngle) {
		this.frontAngle = frontAngle;
	}
	public Byte getMoveMode() {
		return moveMode;
	}
	public void setMoveMode(Byte moveMode) {
		this.moveMode = moveMode;
	}
	public Integer getDisplayColor() {
		return displayColor;
	}
	public void setDisplayColor(Integer displayColor) {
		this.displayColor = displayColor;
	}
	public String getMiscellaneous() {
		return miscellaneous;
	}
	public void setMiscellaneous(String miscellaneous) {
		this.miscellaneous = miscellaneous;
	}
	public Double getCurrentSpeed() {
		return currentSpeed;
	}
	public void setCurrentSpeed(Double currentSpeed) {
		this.currentSpeed = currentSpeed;
	}
	public Double getPOSITION_X() {
		return POSITION_X;
	}
	public void setPOSITION_X(Double pOSITION_X) {
		POSITION_X = pOSITION_X;
	}
	public Double getPOSITION_Y() {
		return POSITION_Y;
	}
	public void setPOSITION_Y(Double pOSITION_Y) {
		POSITION_Y = pOSITION_Y;
	}
	public String getWarningType() {
		return warningType;
	}
	public void setWarningType(String warningType) {
		this.warningType = warningType;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getBattery() {
		return battery;
	}
	public void setBattery(String battery) {
		this.battery = battery;
	}
	public String getAgvIpI() {
		return agvIpI;
	}
	public void setAgvIpI(String agvIpI) {
		this.agvIpI = agvIpI;
	}
	@Override
	public String toString() {
		return "AGVInfo [agvId=" + agvId + ", agvName=" + agvName + ", agvType=" + agvType + ", length=" + length
				+ ", width=" + width + ", height=" + height + ", agvIp=" + Arrays.toString(agvIp) + ", agvIpI="
				+ agvIpI + ", udpPort=" + udpPort + ", nodeId=" + nodeId + ", frontAngle=" + frontAngle
				+ ", moveMode=" + moveMode + ", displayColor=" + displayColor + ", miscellaneous=" + miscellaneous
				+ ", currentSpeed=" + currentSpeed + ", POSITION_X=" + POSITION_X + ", POSITION_Y=" + POSITION_Y
				+ ", warningType=" + warningType + ", status=" + status + ", battery=" + battery + "]";
	}
		
	
	
	
}
