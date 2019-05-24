package ht.statictrafficmanagement.base.entity;


import java.io.Serializable;
import java.nio.ByteBuffer;

import ht.statictrafficmanagement.base.MsgType;
import ht.statictrafficmanagement.base.NotUniquenIDMessage;
import ht.statictrafficmanagement.base.Utils;

/**
 * 节点信息
 * Created by wudw on 2017/11/02.
 */
public class NodeMessage extends NotUniquenIDMessage implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = -1137361270412939443L;

	@Override
    public byte getMessageType() {
        return MsgType.NODE_INFO;
    }

    private int id;
    private String Name;
    private double POSITION_X;
    private double POSITION_Y;

    private byte isStation;   //表示该节点是否为站位，0表示非站位，1表示站位。
    private byte stationType; //站位类型，该节点为站位时有效，0表示空闲停靠站位，1表示充电站位，2表示货架站位，3表示收货站位，4表示打包站位。
    private byte endMode;   //作为终点时处理模式，0:正常；1：停止，后退，停止；2：停止，后退，二次校准；3：停止，后退，前进，停止
    private double height;
    private double width;
    private double x1;
    private double y1;
    private double x2;
    private double y2;
    private int codeId;         //节点上二维码ID
    private String miscellaneous;

    public String getMiscellaneous() {
        return miscellaneous;
    }

    public void setMiscellaneous(String miscellaneous) {
        this.miscellaneous = miscellaneous;
    }

    public int getCodeId() {
        return codeId;
    }

    public void setCodeId(int codeId) {
        this.codeId = codeId;
    }

    @Override
    public byte[] encode() {
        byte[] miscellaneousData = Utils.getByteFromKeyValue(miscellaneous);
        ByteBuffer byteBuf = ByteBuffer.allocate(59 + miscellaneousData.length);
        byteBuf.putInt(id);
        byteBuf.putDouble(POSITION_X);
        byteBuf.putDouble(POSITION_Y);
        byteBuf.put(isStation);
        byteBuf.put(stationType);
        byteBuf.put(endMode);
        byteBuf.putDouble(x1);
        byteBuf.putDouble(y1);
        byteBuf.putDouble(x2);
        byteBuf.putDouble(y2);
        byteBuf.putInt(codeId);
        byteBuf.put(miscellaneousData);
        byte[] bytes = new byte[byteBuf.position()];
        byteBuf.flip();
        byteBuf.get(bytes);
        return bytes;
    }

    @Override
    public void decode(byte[] bytes) {
        ByteBuffer byteBuf = ByteBuffer.allocate(bytes.length);
        byteBuf.put(bytes);
        byteBuf.flip();

        id = byteBuf.getInt();

        Name = "Node-" + id;

        POSITION_X = byteBuf.getDouble();
        POSITION_Y = byteBuf.getDouble();
        isStation = byteBuf.get();
        stationType = byteBuf.get();
        endMode = byteBuf.get();
        x1 = byteBuf.getDouble();
        y1 = byteBuf.getDouble();
        x2 = byteBuf.getDouble();
        y2 = byteBuf.getDouble();
        codeId = byteBuf.getInt();

    }

    public int getid() {
        return id;
    }

    public void setid(int id) {
        this.id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public double getPOSITION_X() {
        return POSITION_X;
    }

    public void setPOSITION_X(double POSITION_X) {
        this.POSITION_X = POSITION_X;
    }

    public double getPOSITION_Y() {
        return POSITION_Y;
    }

    public void setPOSITION_Y(double POSITION_Y) {
        this.POSITION_Y = POSITION_Y;
    }

    /**
     * 0表示非站位，
     * 1表示站位
     *
     * @return
     */
    public byte getIsStation() {
        return isStation;
    }

    /**
     * 0表示空闲停靠站位，
     * 1表示充电站位，
     * 2表示货架站位，
     * 3表示收货站位，
     * 4表示打包站位
     *
     * @return
     */
    public byte getStationType() {
        return stationType;
    }

    /**
     * 0表示空闲停靠站位，
     * 1表示充电站位，
     * 2表示货架站位，
     * 3表示收货站位，
     * 4表示打包站位
     *
     * @param stationType
     */
    public void setStationType(byte stationType) {
        this.stationType = stationType;
    }

    /**
     * 作为终点时处理模式，
     * 0:正常；
     * 1：停止，后退，停止；
     * 2：停止，后退，二次校准；
     * 3：停止，后退，前进，停止
     *
     * @return
     */
    public byte getEndMode() {
        return endMode;
    }

    public double getX1() {
        return x1;
    }

    public void setX1(double x1) {
        this.x1 = x1;
    }

    public double getY1() {
        return y1;
    }

    public void setY1(double y1) {
        this.y1 = y1;
    }

    public double getX2() {
        return x2;
    }

    public void setX2(double x2) {
        this.x2 = x2;
    }

    public double getY2() {
        return y2;
    }

    public void setY2(double y2) {
        this.y2 = y2;
    }


    /**
     * 作为终点时处理模式，
     * 0:正常；
     * 1：停止，后退，停止；
     * 2：停止，后退，二次校准；
     * 3：停止，后退，前进，停止
     *
     * @param endMode
     */
    public void setEndMode(byte endMode) {
        this.endMode = endMode;
    }

    /**
     * 0表示非站位，
     * 1表示站位
     *
     * @param isStation
     */
    public void setIsStation(byte isStation) {
        this.isStation = isStation;
    }

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getHeight() {
		return height;
	}

	public void setHeight(double height) {
		this.height = height;
	}

	public double getWidth() {
		return width;
	}

	public void setWidth(double width) {
		this.width = width;
	}

	@Override
	public String toString() {
		return "NodeMessage [id=" + id + ", Name=" + Name + ", POSITION_X=" + POSITION_X + ", POSITION_Y=" + POSITION_Y
				+ ", isStation=" + isStation + ", stationType=" + stationType + ", endMode=" + endMode + ", height="
				+ height + ", width=" + width + ", x1=" + x1 + ", y1=" + y1 + ", x2=" + x2 + ", y2=" + y2 + ", codeId="
				+ codeId + ", miscellaneous=" + miscellaneous + "]";
	}

    
    

}
