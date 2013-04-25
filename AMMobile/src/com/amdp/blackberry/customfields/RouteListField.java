package com.amdp.blackberry.customfields;

import java.util.Vector;
import com.amdp.bb.basic.entity.RouteEntity;
import com.amdp.bb.ui.ListFieldRowSelectionListener;
import net.rim.device.api.ui.DrawStyle;
import net.rim.device.api.ui.Graphics;
import net.rim.device.api.ui.XYRect;
import net.rim.device.api.ui.component.ListField;
import net.rim.device.api.ui.component.ListFieldCallback;

public class RouteListField extends ListField implements
		ListFieldCallback {

		private ListFieldRowSelectionListener listFieldRowSelectionListener;
	private Vector categories = new Vector();
	private boolean paintFocus = false;
	public RouteListField(Vector categories) {
		super(0);
		setEmptyString("No hay rutas", DrawStyle.HCENTER);
		this.categories = categories;

		setRowHeight(RouteListRow.getRowHeight());
		setSize(this.categories.size());
	}

	public void drawListRow(ListField listField, Graphics graphics, int index,
			int y, int width) {

		RouteEntity category = (RouteEntity) categories.elementAt(index);
		RouteListRow routeListRow = new RouteListRow(category, paintFocus);
		routeListRow.drawRow(graphics, 0, y, width,routeListRow.getRowHeight());
	}

	public Object get(ListField listField, int index) {
		return null;
	}

	public int getPreferredWidth(ListField listField) {
		return 0;
	}

	public int indexOfList(ListField listField, String prefix, int start) {
		return 0;
	}



	public void setListRowSelectionListener(
			ListFieldRowSelectionListener listRowSelectionListener) {
		this.listFieldRowSelectionListener = listRowSelectionListener;
	}

	public boolean navigationClick(int status, int time) {
		if (listFieldRowSelectionListener != null) {
			itemDidSelected(this.getSelectedIndex());
		}
		return true;
	}

	private void itemDidSelected(int transactionIndex) {
		if (listFieldRowSelectionListener != null) {
			listFieldRowSelectionListener.listRowDidSelect(transactionIndex,
					this);
		}
	}
	

	public void setListFieldRowSelectionListener(ListFieldRowSelectionListener listFieldRowSelectionListener) {
		this.listFieldRowSelectionListener = listFieldRowSelectionListener;
	}

	public void onUnfocus() {
		super.onUnfocus();
		invalidate();
	}

	protected void drawFocus(Graphics graphics, boolean on) {
		XYRect focusRect = new XYRect();
		getFocusRect(focusRect);

		boolean oldDrawStyleFocus = graphics.isDrawingStyleSet(Graphics.DRAWSTYLE_FOCUS);
		try {
			if (on) {
				// set the style so the fields in the row will update its
				// color accordingly
				graphics.setDrawingStyle(Graphics.DRAWSTYLE_FOCUS, true);
				int oldColour = graphics.getColor();

				try {
					graphics.setColor(0x007D98B6);

					
					graphics.fillRect(focusRect.x, focusRect.y, focusRect.width, focusRect.height);

				} finally {
					graphics.setColor(oldColour);
				}
				// to draw the row again
				paintFocus = true;
				drawListRow(this, graphics, getSelectedIndex(), focusRect.y, focusRect.width);
			}
			// This is necessary for no repaint all list rows
			paintFocus = false;

		} finally {
			graphics.setDrawingStyle(Graphics.DRAWSTYLE_FOCUS, oldDrawStyleFocus);
		}

	}
}
