package ht.statictrafficmanagement.base.entity;

import java.io.Serializable;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ActionType implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8339677750603264324L;

	@Value("${con.actionTypeS.list}")
	private String[] actionTypeS;
	
	@Value("${con.actionTypeI.list}")
	private int[] actionTypeI;

	public String[] getActionTypeS() {
		return actionTypeS;
	}

	public void setActionTypeS(String[] actionTypeS) {
		this.actionTypeS = actionTypeS;
	}

	public int[] getActionTypeI() {
		return actionTypeI;
	}

	public void setActionTypeI(int[] actionTypeI) {
		this.actionTypeI = actionTypeI;
	}

	@Override
	public String toString() {
		return "ActionType [actionTypeS=" + Arrays.toString(actionTypeS) + ", actionTypeI="
				+ Arrays.toString(actionTypeI) + "]";
	}
	
	
	
	
}
