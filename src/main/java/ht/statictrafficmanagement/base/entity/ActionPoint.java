package ht.statictrafficmanagement.base.entity;

import java.io.Serializable;
import java.util.Arrays;

public class ActionPoint implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 6669212688273574309L;
	
	private int actionID;
	private String actionName;
	private Byte[] actionTypes;
	private Integer[] actionContents;
	private double xValue;
	private double yValue;
	public int getActionID() {
		return actionID;
	}
	public void setActionID(int actionID) {
		this.actionID = actionID;
	}
	public String getActionName() {
		return actionName;
	}
	public void setActionName(String actionName) {
		this.actionName = actionName;
	}
	public Byte[] getActionTypes() {
		return actionTypes;
	}
	public void setActionTypes(Byte[] actionTypes) {
		this.actionTypes = actionTypes;
	}
	public Integer[] getActionContents() {
		return actionContents;
	}
	public void setActionContents(Integer[] actionContents) {
		this.actionContents = actionContents;
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
	@Override
	public String toString() {
		return "ActionPoint [actionID=" + actionID + ", actionName=" + actionName + ", actionTypes="
				+ Arrays.toString(actionTypes) + ", actionContents=" + Arrays.toString(actionContents) + ", xValue="
				+ xValue + ", yValue=" + yValue + "]";
	}
	
	
	
}
