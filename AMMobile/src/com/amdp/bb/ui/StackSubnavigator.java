package com.amdp.bb.ui;

import java.util.Vector;
import net.rim.device.api.ui.container.VerticalFieldManager;

public class StackSubnavigator extends VerticalFieldManager {

	private Stack stack = new Stack();
	private StackableVerticalFieldManager rootElement;
	
	public StackableVerticalFieldManager getRootElement(){
		return  rootElement;
	}
	
	public StackSubnavigator(StackableVerticalFieldManager rootElement,
			long style) {
		super(style);
		if (rootElement != null) {
			pushManager(rootElement);
		}
		this.rootElement = rootElement;
	}

	public StackSubnavigator(StackableVerticalFieldManager rootElement) {
		this(rootElement, 0);
	}

	public void pushManager(StackableVerticalFieldManager newElement) {
		if(stack.isEmpty()) {
			this.add(newElement);

		}else {
			changeFields(stack.top(), newElement);	
		}
		
		stack.push(newElement);
		newElement.setStackSubnavigator(this);
		
	}
	
	
	public void clearStack(){
		if (stack.size() > 1) {
			StackableVerticalFieldManager elementToRemove = stack.top();
			stack.pop();
			StackableVerticalFieldManager elementToShow = stack.getRoot();
			changeFields(elementToRemove, elementToShow);
			elementToRemove.setStackSubnavigator(null);
		}
	}


	public void popManager() {
		if (stack.size() > 1) {
			StackableVerticalFieldManager elementToRemove = stack.top();
			stack.pop();
			StackableVerticalFieldManager elementToShow = stack.top();
			changeFields(elementToRemove, elementToShow);
			elementToRemove.setStackSubnavigator(null);
		}
		
		else{
			System.exit(0);
		}


	}
	
	private void changeFields(StackableVerticalFieldManager fieldToRemove,
			StackableVerticalFieldManager fieldToShow) {
		this.replace(fieldToRemove, fieldToShow);
	}

	/* ========== Basic Stack operations ============ */

	private class Stack {

		private Vector stack;

		public Stack() {
			super();
			stack = new Vector();
		}

		public StackableVerticalFieldManager push(
				StackableVerticalFieldManager manager) {
			stack.addElement(manager);
			return manager;
		}

		public StackableVerticalFieldManager pop() {
			StackableVerticalFieldManager itemToBePopped = top();
			if (stack.size() > 0) {
				stack.removeElementAt(stack.size() - 1);
				return itemToBePopped;
			}
			return null;
		}

		public StackableVerticalFieldManager top() {
			return (StackableVerticalFieldManager) stack
					.elementAt(stack.size() - 1);
		}

		public StackableVerticalFieldManager getRoot() {
			return (StackableVerticalFieldManager) stack
					.elementAt(0);
		}
		public int size() {
			return stack.size();
		}
		
		public boolean isEmpty() {
			return stack.isEmpty();
		}
		

	}
}
