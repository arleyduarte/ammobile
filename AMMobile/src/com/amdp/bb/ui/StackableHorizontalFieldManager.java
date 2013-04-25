package com.amdp.bb.ui;


import net.rim.device.api.ui.container.HorizontalFieldManager;

public class StackableHorizontalFieldManager extends HorizontalFieldManager {
	
	private StackSubnavigator stackSubnavigator;

	public StackSubnavigator getStackSubnavigator() {
		return stackSubnavigator;
	}

	public void setStackSubnavigator(StackSubnavigator stackSubnavigator) {
		this.stackSubnavigator = stackSubnavigator;
	}

	public StackableHorizontalFieldManager() {
		super();
	}

	public StackableHorizontalFieldManager(long style) {
		super(style);
	}

}
