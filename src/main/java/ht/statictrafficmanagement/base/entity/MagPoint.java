package ht.statictrafficmanagement.base.entity;

import java.io.Serializable;

public class MagPoint implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1887082216443579374L;
	private int magPointId;
	private String magName;
	private byte magType;
	private double xValue;
	private double yValue;
	private int magInterval;
	public int getMagPointId() {
		return magPointId;
	}
	public void setMagPointId(int magPointId) {
		this.magPointId = magPointId;
	}
	public String getMagName() {
		return magName;
	}
	public void setMagName(String magName) {
		this.magName = magName;
	}
	public byte getMagType() {
		return magType;
	}
	public void setMagType(byte magType) {
		this.magType = magType;
	}
	public double getxValue() {
		return xValue;
	}
	public void setxValue(double xValue) {
		this.xValue = xValue;
	}
	public double getyValue() {
		return yValue;
	}
	public void setyValue(double yValue) {
		this.yValue = yValue;
	}
	public int getMagInterval() {
		return magInterval;
	}
	public void setMagInterval(int magInterval) {
		this.magInterval = magInterval;
	}
	@Override
	public String toString() {
		return "MagPoint [magPointId=" + magPointId + ", magName=" + magName + ", magType=" + magType + ", xValue="
				+ xValue + ", yValue=" + yValue + ", magInterval=" + magInterval + "]";
	}
	
	
	
}
