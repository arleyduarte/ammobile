package com.amdp.blackberry.screen;

import java.util.Vector;

import com.amdp.bb.api.ResponseActionDelegate;
import com.amdp.bb.basic.entity.RouteBusinessLogic;
import com.amdp.bb.basic.entity.RouteEntity;
import com.amdp.bb.basic.entity.UserBLL;
import com.amdp.bb.basic.handler.GetRoutesResourceHandler;
import com.amdp.bb.ui.ListFieldRowSelectionListener;
import com.amdp.blackberry.customfields.RouteListField;

import net.rim.device.api.ui.Font;
import net.rim.device.api.ui.UiApplication;
import net.rim.device.api.ui.XYEdges;
import net.rim.device.api.ui.component.LabelField;
import net.rim.device.api.ui.component.ListField;
import net.rim.device.api.ui.container.MainScreen;
import net.rim.device.api.ui.container.VerticalFieldManager;

public class RouteSelectScreen extends MainScreen implements
		ListFieldRowSelectionListener, ResponseActionDelegate {
	private VerticalFieldManager manager1 = new VerticalFieldManager(
			VerticalFieldManager.USE_ALL_HEIGHT
					| VerticalFieldManager.USE_ALL_WIDTH);
	private Vector rutas = new Vector();
	private ResponseActionDelegate responseActionDelegate;

	public RouteSelectScreen() {
		responseActionDelegate = this;

		RouteBusinessLogic.getInstance().getCategories().removeAllElements();

		fetchRoutes();

	}

	private void fetchRoutes() {
		try {
			Thread threadToRun = new Thread() {
				public void run() {
					GetRoutesResourceHandler resourceHandler = new GetRoutesResourceHandler(
							UserBLL.getInstanceBLL().getCurrentUser()
									.getUserId());
					resourceHandler.sendAPIMessage(responseActionDelegate);

				}
			};

			WaitDialogScreen.showScreenAndWait(threadToRun);
			goToNextScreen();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void goToNextScreen() {
		rutas = RouteBusinessLogic.getInstance().getCategories();

		if (!rutas.isEmpty()) {
			buildAndShowLists();
		} else {
			buildWhitoutRoutes();
		}

	}

	private void buildWhitoutRoutes() {
		VerticalFieldManager withoutAccountsManager = new VerticalFieldManager(
				VerticalFieldManager.USE_ALL_HEIGHT
						| VerticalFieldManager.USE_ALL_WIDTH);
		LabelField paymentsTitle = new LabelField("Rutas",
				VerticalFieldManager.FOCUSABLE | LabelField.HCENTER
						| LabelField.USE_ALL_WIDTH);
		paymentsTitle.setMargin(new XYEdges(0, 0, 10, 0));
		paymentsTitle.setFont(paymentsTitle.getFont().derive(Font.BOLD));
		withoutAccountsManager.add(paymentsTitle);
		withoutAccountsManager.add(new LabelField(
				"Actualmente no hay rutas pendientes", LabelField.HCENTER
						| LabelField.USE_ALL_WIDTH));
		add(withoutAccountsManager);
	}

	private void buildAndShowLists() {
		RouteListField categoriesListField = new RouteListField(rutas);
		categoriesListField.setListFieldRowSelectionListener(this);
		manager1.add(categoriesListField);
		this.add(manager1);
	}

	public void listRowDidSelect(int index, ListField targetListField) {
		VisitSimpleFormScreen paymentSelectBillerScreen = new VisitSimpleFormScreen(
				(RouteEntity) rutas.elementAt(index));
		UiApplication.getUiApplication().pushScreen(paymentSelectBillerScreen);

	}

	public void didSuccessfully(String message) {
	}

	public void didNotSuccessfully(String message) {
	}

	public void close() {
		System.exit(0);
	}

	protected boolean onSavePrompt() {
		System.exit(0);
		return false;
	}

}
