package ht.statictrafficmanagement.base.entity;

import java.io.Serializable;
import java.nio.ByteBuffer;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import ht.statictrafficmanagement.base.MsgType;
import ht.statictrafficmanagement.base.NotUniquenIDMessage;
import ht.statictrafficmanagement.base.Utils;


/**
 * 片段信息
 * @author shuangjiaxu
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class SegmentMessage extends NotUniquenIDMessage implements Serializable{
	 	/**
	 * 
	 */
	private static final long serialVersionUID = -2528027388754220355L;
		private int segmentId;
	    private String segmentName;
	    private byte type;

	    private int startNodeId;
	    private int endNodeId;
	    private double rotAngle;
	    private byte isClockwiseDirection;
	    private double control1X;
	    private double control1Y;
	    private double Control2X;
	    private double Control2Y;

	    private double length;
	    private byte isLink;
	    private byte directionality;
	    private int maxSpeed;
	    private String miscellaneous;
	    
	    public String getMiscellaneous() {
	        return miscellaneous;
	    }

	    public void setMiscellaneous(String miscellaneous) {
	        this.miscellaneous = miscellaneous;
	    }

	    public int getMaxSpeed() {
	        return maxSpeed;
	    }

	    public void setMaxSpeed(int maxSpeed) {
	        this.maxSpeed = maxSpeed;
	    }
	    
	   
	    
	    @Override
		public String toString() {
			return "SegmentMessage [segmentId=" + segmentId + ", segmentName=" + segmentName + ", type=" + type
					+ ", startNodeId=" + startNodeId + ", endNodeId=" + endNodeId + ", rotAngle=" + rotAngle
					+ ", isClockwiseDirection=" + isClockwiseDirection + ", control1X=" + control1X + ", control1Y="
					+ control1Y + ", Control2X=" + Control2X + ", Control2Y=" + Control2Y + ", length=" + length
					+ ", isLink=" + isLink + ", directionality=" + directionality + ", maxSpeed=" + maxSpeed
					+ ", miscellaneous=" + miscellaneous + "]";
		}

		/**
	     * 仅当类型为圆弧有效，0表示逆时针，1表示顺时针
	     *
	     * @return
	     */
	    public byte getIsClockwiseDirection() {
	        return isClockwiseDirection;
	    }
	    
	    /**
	     * 仅当类型为圆弧有效，0表示逆时针，1表示顺时针
	     *
	     * @param isClockwiseDirection
	     */
	    public void setIsClockwiseDirection(byte isClockwiseDirection) {
	        this.isClockwiseDirection = isClockwiseDirection;
	    }

	    public double getRotAngle() {
	        return rotAngle;
	    }

	    public void setRotAngle(double rotAngle) {
	        this.rotAngle = rotAngle;
	    }

	    public int getSegmentId() {
	        return segmentId;
	    }

	    public void setSegmentId(int segmentId) {
	        this.segmentId = segmentId;
	    }

	    public String getSegmentName() {
	        return segmentName;
	    }

	    public void setSegmentName(String segmentName) {
	        this.segmentName = segmentName;
	    }
	    
	    
	    /**
	     * 路段类型，0表示直线，1表示圆弧，2表示贝塞尔曲线
	     *
	     * @return
	     */
	    public byte getType() {
	        return type;
	    }

	    /**
	     * 路段类型，0表示直线，1表示圆弧，2表示贝塞尔曲线
	     *
	     * @param type
	     */
	    public void setType(byte type) {
	        this.type = type;
	    }

	    /**
	     * 方向性，0表示双向，1表示单向
	     *
	     * @return
	     */
	    public byte getDirectionality() {
	        return directionality;
	    }

	    /**
	     * 方向性，0表示双向，1表示单向
	     *
	     * @param directionality
	     */
	    public void setDirectionality(byte directionality) {
	        this.directionality = directionality;
	    }

	    public int getStartNodeId() {
	        return startNodeId;
	    }

	    public void setStartNodeId(int startNodeId) {
	        this.startNodeId = startNodeId;
	    }

	    public int getEndNodeId() {
	        return endNodeId;
	    }

	    public void setEndNodeId(int endNodeId) {
	        this.endNodeId = endNodeId;
	    }

	    public double getControl1X() {
	        return control1X;
	    }

	    public void setControl1X(double control1X) {
	        this.control1X = control1X;
	    }

	    public double getControl1Y() {
	        return control1Y;
	    }

	    public void setControl1Y(double control1Y) {
	        this.control1Y = control1Y;
	    }

	    public double getControl2X() {
	        return Control2X;
	    }

	    public void setControl2X(double control2X) {
	        Control2X = control2X;
	    }

	    public double getControl2Y() {
	        return Control2Y;
	    }

	    public void setControl2Y(double control2Y) {
	        Control2Y = control2Y;
	    }

	    public double getLength() {
	        return length;
	    }

	    public void setLength(double length) {
	        this.length = length;
	    }
	    
	    /**
	     * 表示该路段是否为Link，
	     * 如否为0，
	     * 如是为1，且只能是双向直线
	     *
	     * @return
	     */
	    public byte getIsLink() {
	        return isLink;
	    }

	    /**
	     * 表示该路段是否为Link，
	     * 如否为0，
	     * 如是为1，且只能是双向直线
	     *
	     * @param isLink
	     */
	    public void setIsLink(byte isLink) {
	        this.isLink = isLink;
	    }
	    
	    @Override
	    public void decode(byte[] bytes) {
	        ByteBuffer byteBuf = ByteBuffer.allocate(bytes.length);
	        byteBuf.put(bytes);
	        byteBuf.flip();

	        segmentId = byteBuf.getInt();
	        type = byteBuf.get();
	        startNodeId = byteBuf.getInt();
	        endNodeId = byteBuf.getInt();
	        rotAngle = byteBuf.getDouble();
	        isClockwiseDirection = byteBuf.get();
	        control1X = byteBuf.getDouble();
	        control1Y = byteBuf.getDouble();
	        Control2X = byteBuf.getDouble();
	        Control2Y = byteBuf.getDouble();
	        length = byteBuf.getDouble();
	        directionality = byteBuf.get();
	        maxSpeed = byteBuf.getInt();

	    }
	    
	    @Override
	    public byte[] encode() {
	        byte[] miscellaneousData = Utils.getByteFromKeyValue(miscellaneous);
	        ByteBuffer byteBuf = ByteBuffer.allocate(67 + miscellaneousData.length);

	        byteBuf.putInt(segmentId);
	        byteBuf.put(type);
	        byteBuf.putInt(startNodeId);
	        byteBuf.putInt(endNodeId);
	        byteBuf.putDouble(rotAngle);
	        byteBuf.put(isClockwiseDirection);
	        byteBuf.putDouble(control1X);
	        byteBuf.putDouble(control1Y);
	        byteBuf.putDouble(Control2X);
	        byteBuf.putDouble(Control2Y);
	        byteBuf.putDouble(length);
	        byteBuf.put(directionality);
	        byteBuf.putInt(maxSpeed);
	        byteBuf.put(miscellaneousData);
	        byte[] bytes = new byte[byteBuf.position()];
	        byteBuf.flip();
	        byteBuf.get(bytes);
	        return bytes;
	    }


	    @Override
	    public byte getMessageType() {
	        return MsgType.SEGMENT_INFO;
	    }




}
