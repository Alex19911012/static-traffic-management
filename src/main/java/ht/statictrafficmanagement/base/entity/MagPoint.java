package ht.statictrafficmanagement.base.entity;

import java.io.Serializable;
import java.nio.ByteBuffer;

import ht.statictrafficmanagement.base.MsgType;
import ht.statictrafficmanagement.base.NotUniquenIDMessage;


public class MagPoint extends NotUniquenIDMessage implements Serializable{
	
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
        return "MagPointMessage{" +
                "magId=" + magPointId +
                ", magType=" + magType +
                ", x=" + xValue +
                ", y=" + yValue +
                ", magInterval=" + magInterval +
                '}';
    }
	@Override
	public void decode(byte[] bytes) {
		ByteBuffer byteBuf = ByteBuffer.allocate(bytes.length);
        byteBuf.put(bytes);
        byteBuf.flip();

        magPointId = byteBuf.getInt();
        magName = "MagPoint-" + magPointId;

        magType = byteBuf.get();
        xValue = byteBuf.getDouble();
        yValue = byteBuf.getDouble();
        magInterval = byteBuf.getInt();
		
	}
	@Override
	public byte[] encode() {
		 ByteBuffer byteBuf = ByteBuffer.allocate(25);

	        byteBuf.putInt(magPointId);
	        byteBuf.put(magType);
	        byteBuf.putDouble(xValue);
	        byteBuf.putDouble(yValue);
	        byteBuf.putInt(magInterval);
	        byte[] bytes = new byte[byteBuf.position()];
	        byteBuf.flip();
	        byteBuf.get(bytes);
	        return bytes;
	}
	@Override
	public byte getMessageType() {
		return MsgType.MAG_NODE_INFO;
	}	
	
	
}
