package com.amdp.bb.ui;


import net.rim.device.api.ui.container.VerticalFieldManager;

public class StackableVerticalFieldManager extends VerticalFieldManager {
	private StackSubnavigator stackSubnavigator;

	public StackSubnavigator getStackSubnavigator() {
		

		return stackSubnavigator;
	}

	public void setStackSubnavigator(StackSubnavigator stackSubnavigator) {
		this.stackSubnavigator = stackSubnavigator;
	}

	public StackableVerticalFieldManager() {
		super();
		
	}

	public StackableVerticalFieldManager(long style) {
		super(style);
	}

}
