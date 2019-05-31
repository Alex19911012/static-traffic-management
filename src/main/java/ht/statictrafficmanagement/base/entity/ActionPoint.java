package ht.statictrafficmanagement.base.entity;

import java.io.Serializable;
import java.nio.ByteBuffer;
import java.util.Arrays;

import ht.statictrafficmanagement.base.MsgType;
import ht.statictrafficmanagement.base.NotUniquenIDMessage;
import ht.statictrafficmanagement.base.Utils;

public class ActionPoint extends NotUniquenIDMessage implements Serializable{
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
	        return "ActionPointMessage{" +
	                "actionId=" + actionID +
	                ", actionTypes=" + actionTypes +
	                ", actionContents=" + actionContents +
	                ", x=" + xValue +
	                ", y=" + yValue +
	                '}';
	    }
	 
	@Override
	public void decode(byte[] bytes) {
		ByteBuffer buf = ByteBuffer.allocate(bytes.length);
        buf.put(bytes);
        buf.flip();

        actionID = buf.getInt();

       
        xValue = buf.getDouble();
        yValue = buf.getDouble();
		
	}
	@Override
	public byte[] encode() {
		 ByteBuffer buf = ByteBuffer.allocate(60);
	        buf.putInt(actionID);
	        Byte[] actionTypeArray = actionTypes;//20
	        for (Byte b : actionTypeArray) {
	            buf.put(b);
	        }
	        Integer[] actionContentArray = actionContents;//20
	        for (int actionContent : actionContentArray) {
	            buf.putInt(actionContent);
	        }
	        buf.putDouble(xValue);
	        buf.putDouble(yValue);
	        byte[] bs = new byte[buf.position()];
	        buf.flip();
	        buf.get(bs);
	        return bs;
	}
	@Override
	public byte getMessageType() {
		 return MsgType.ACTION_NODE_INFO;
	}
	
	
	
}
