package com.amdp.blackberry.customfields;


import com.amdp.bb.basic.entity.RouteEntity;

import net.rim.device.api.system.Bitmap;
import net.rim.device.api.system.Display;
import net.rim.device.api.ui.Color;
import net.rim.device.api.ui.DrawStyle;
import net.rim.device.api.ui.Field;
import net.rim.device.api.ui.Font;
import net.rim.device.api.ui.Graphics;
import net.rim.device.api.ui.Manager;
import net.rim.device.api.ui.component.BitmapField;
import net.rim.device.api.ui.component.LabelField;

public class RouteListRow extends Manager {
	private static int separator = 4;
	private static int fontBigHeight = (int) Math.floor(Font.getDefault().getHeight() * 1.1);
	private static int fontNormalHeight = (int) Math.floor(Font.getDefault().getHeight() * 0.8);
 	private static int rowHeight = separator * 4 + fontNormalHeight * 2  + 1;
	protected boolean inFocus = true;
	
	public RouteListRow(RouteEntity category, boolean _inFocus) {
		super(0);

		this.inFocus = _inFocus;

		// description field -----------------------------------------
		LabelField descriptionLabel = new LabelField(category.getDescription(), DrawStyle.ELLIPSIS | LabelField.USE_ALL_WIDTH | DrawStyle.LEFT) {
			protected void paint(Graphics graphics) {
				if (inFocus) {
					graphics.setColor(Color.WHITE);
				} else {
					//TODO: Color
					graphics.setColor(Color.BLACK);
				}
				super.paint(graphics);
			}
		};
		descriptionLabel.setFont(descriptionLabel.getFont().derive(Font.ITALIC, fontBigHeight));
		this.add(descriptionLabel);

		// disclosure image
		// --------------------------------------------------------------------------
		Bitmap b = Bitmap.getBitmapResource("chevron.png");
		BitmapField disclosureIcon = new BitmapField(b);
		this.add(disclosureIcon);

	}

	// Causes the fields within this row manager to be layed out then
	// painted.
	public void drawRow(Graphics graphics, int x, int y, int width, int height) {
		// Arrange the cell fields within this row manager.
		layout(width, height);

		// Place this row manager within its enclosing list.
		setPosition(x, y);

		// Apply a translating/clipping transformation to the graphics
		// context so that this row paints in the right area.
		graphics.pushRegion(getExtent());

		// Paint this manager's controlled fields.
		subpaint(graphics);

		graphics.setColor(0x007D98B6);
		graphics.drawLine(0, getPreferredHeight() - 1, getPreferredWidth(), getPreferredHeight() - 1);

		// Restore the graphics context.
		graphics.popContext();
	}

	protected void sublayout(int width, int height) {

		int preferredWith = getPreferredWidth();

		Field field;
		int i = 0;
		// sets the transaction day number
		field = getField(i++);
		layoutChild(field, preferredWith - 50, fontBigHeight);
		setPositionChild(field, 20, 15);

		// set disclosure icon
		field = getField(i++);
		int fieldWidth = 25;
		int fieldHeight = 20;
		layoutChild(field, fieldWidth, fieldHeight);
		setPositionChild(field, preferredWith - (fieldWidth - 5), (getPreferredHeight() / 2) - (fieldHeight / 2));

		setExtent(getPreferredWidth(), getPreferredHeight());
	}

	public int getPreferredWidth() {
		return Display.getWidth();
	}
	public static int getRowHeight() {
		return rowHeight;
	}
	
	public int getPreferredHeight() {
		return rowHeight;
	}
}
