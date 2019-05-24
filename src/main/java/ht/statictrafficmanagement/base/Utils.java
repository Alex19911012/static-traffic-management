package ht.statictrafficmanagement.base;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 工具类
 * @author Alex
 *
 */
public class Utils {
	/**
     * 将任务中的以String表示的动作类型解析为byte数组
     *
     * @param str
     * @return
     */
    public static byte[] getActionTypeArrayFormString(String str) {
        byte[] bytes = new byte[8];
        if (str != null && str.length() > 0) {
            String[] strings = str.split(",");
            int len = strings.length > 8 ? 8 : strings.length;
            for (int i = 0; i < len; i++) {
                try {
                    bytes[i] = Byte.parseByte(strings[i]);
                } catch (NumberFormatException e) {
                    bytes[i] = 0;
                }
            }
        }
        return bytes;
    }

    /**
     * 将任务中的以String表示的动作说明解析为byte数组
     *
     * @param str
     * @return
     */
    public static int[] getActionContentsArrayFormString(String str) {
        int[] bytes = new int[8];
        if (str != null && str.length() > 0) {
            String[] strings = str.split(",");
            int len = strings.length > 8 ? 8 : strings.length;
            for (int i = 0; i < len; i++) {
                try {
                    bytes[i] = Integer.parseInt(strings[i]);
                } catch (NumberFormatException e) {
                    bytes[i] = 0;
                }
            }
        }
        return bytes;
    }

    /**
     * 将地图中扩展属性解析为Map
     *
     * @param miscellaneous
     * @return
     */
    public static Map<String, String> getMapFromMiscellaneous(String miscellaneous) {
        if (miscellaneous == null || miscellaneous.length() == 0) {
            return new HashMap<>();
        }
        String[] keyValues = miscellaneous.trim().split(";");
        Map<String, String> result = new HashMap<>();
        if (keyValues != null && keyValues.length > 0) {
            for (String keyValue : keyValues) {
                String[] tmp = keyValue.split(":");
                if (tmp != null && tmp.length == 2) {
                    result.put(tmp[0].trim(), tmp[1].trim());
                }
            }
        }
        return result;
    }


    private static List<MiscellaneousNode> getByteFromMiscellaneous(String miscellaneous) {
        if (miscellaneous == null || miscellaneous.length() == 0) {
            return new ArrayList<>();
        }
        String[] keyValues = miscellaneous.trim().split(";");
        List<MiscellaneousNode> result = new ArrayList<>();
        if (keyValues != null && keyValues.length > 0) {
            for (String keyValue : keyValues) {
                String[] tmp = keyValue.split(":");
                if (tmp != null && tmp.length == 2) {
                    MiscellaneousNode map = new MiscellaneousNode(tmp[0].trim(), tmp[1].trim());
                    result.add(map);
                }
            }
        }
        return result;
    }


    /**
     * 将地图的扩展属性解析为byte数组，以便用于传输
     *
     * @param miscellaneous
     * @return
     */
    public static byte[] getByteFromKeyValue(String miscellaneous) {
        ByteBuffer buffer;
        List<MiscellaneousNode> keyValues = getByteFromMiscellaneous(miscellaneous);

        if (keyValues == null || keyValues.size() == 0) {
            buffer = ByteBuffer.allocate(4);
            buffer.putInt(0);
        } else {
            int length = miscellaneous.getBytes().length + keyValues.size() * 8 + 4;
            buffer = ByteBuffer.allocate(length);
            buffer.putInt(keyValues.size());
            for (MiscellaneousNode node : keyValues) {
                String key = node.getKey().trim();
                String value = node.getValue().trim();
                byte[] keyData = key.getBytes();
                byte[] valueData = value.getBytes();
                buffer.putInt(keyData.length);
                buffer.put(keyData);
                buffer.putInt(valueData.length);
                buffer.put(valueData);
            }
        }
        int dataLen = buffer.position();
        buffer.flip();
        byte[] data = new byte[dataLen];
        buffer.get(data);
        return data;
    }

	
	
	
}
