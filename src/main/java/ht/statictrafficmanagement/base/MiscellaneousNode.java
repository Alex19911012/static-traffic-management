package ht.statictrafficmanagement.base;

/**
 * 内部使用，解析地图扩展属性过程中使用
 * @author shuangjiaxu
 *
 */
public class MiscellaneousNode {
	 private final String key;
	    private final String value;

	    public MiscellaneousNode(String key, String value) {
	        this.key = key;
	        this.value = value;
	    }

	    public String getKey() {
	        return key;
	    }

	    public String getValue() {
	        return value;
	    }

}
