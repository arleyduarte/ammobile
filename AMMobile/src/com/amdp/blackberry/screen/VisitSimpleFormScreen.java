package com.amdp.blackberry.screen;

import com.amdp.bb.AppStyle;
import com.amdp.bb.api.ResponseActionDelegate;
import com.amdp.bb.basic.entity.Checkin;
import com.amdp.bb.basic.entity.RouteEntity;
import com.amdp.bb.basic.handler.AddCheckinResourceHandler;
import net.rim.device.api.system.Bitmap;
import net.rim.device.api.ui.Color;
import net.rim.device.api.ui.DrawStyle;
import net.rim.device.api.ui.Field;
import net.rim.device.api.ui.FieldChangeListener;
import net.rim.device.api.ui.Font;
import net.rim.device.api.ui.Graphics;
import net.rim.device.api.ui.Manager;
import net.rim.device.api.ui.TransitionContext;
import net.rim.device.api.ui.Ui;
import net.rim.device.api.ui.UiApplication;
import net.rim.device.api.ui.UiEngineInstance;
import net.rim.device.api.ui.XYEdges;
import net.rim.device.api.ui.component.ButtonField;
import net.rim.device.api.ui.component.Dialog;
import net.rim.device.api.ui.component.EditField;
import net.rim.device.api.ui.component.LabelField;
import net.rim.device.api.ui.component.ObjectChoiceField;
import net.rim.device.api.ui.container.HorizontalFieldManager;
import net.rim.device.api.ui.container.MainScreen;
import net.rim.device.api.ui.container.VerticalFieldManager;
import net.rim.device.api.ui.decor.Background;
import net.rim.device.api.ui.decor.BackgroundFactory;
import net.rim.device.api.ui.decor.Border;
import net.rim.device.api.ui.decor.BorderFactory;
import net.rim.device.api.ui.text.NumericTextFilter;
import net.rim.device.api.ui.text.TextFilter;

public class VisitSimpleFormScreen extends MainScreen implements
		ResponseActionDelegate {

	private String amountChoices[] = { "EE Normal", "EE Plus",
			"EE En Sucursal", "Retorno", "DT Producto", "Rechaza Producto",
			"Confirma con el Banco", "GD Fallida", "Sin ID", "ID No Coincide",
			"Datos Básicos Errados", "Radicado en Otro Lugar",
			"De Viaje (+ de 1 semana)", "Hospitalizado (+ de 1 semana)",
			"Fallecido", "Otro ",
			"No Se Encuentra - Se Deja Tarjeta de Visita",
			"No Labora Allí - Se Deja Tarjeta de Visita",
			"No Vive Allí - Se Deja Tarjeta de Visita",
			"G Cerrado - Se Deja Tarjeta de Visita",
			"Se Obtienen Datos Nuevos", "Fuera de Ruta", "No Labora Allí",
			"No Vive Allí", "No Lo Conocen", "G No Existe", "G Incompleto",
			"Zona Roja", "Dificil Acceso", "Incumplimiento CO",
			"Incumplimiento CL", "3 Gestiones OE Fallidas", "Otro" };

	private String codigoCausal[] = { "D2-3", "D2-4", "D2-5", "D2-6", "D2-7",
			"D2-8", "D2-9", "D2-10", "D2-11", "D2-12", "D2-13", "D2-14",
			"D2-15", "D2-16", "D2-17", "D2-18", "G2-1", "G2-2", "G2-3", "G2-4",
			"G2-5", "G2-6", "G2-7", "G2-8", "G2-9", "G2-10", "G2-11", "G2-12",
			"G2-13", "G2-14", "G2-15", "G2-16", "G2-17" };

	private EditField inoEditField = new EditField("", "", 200,
			EditField.EDITABLE);
	private EditField egEditField = new EditField("", "", 200,
			EditField.EDITABLE);
	private EditField datosEditField = new EditField("", "", 200,
			EditField.EDITABLE);
	private ObjectChoiceField gestionChoiceField;
	private Checkin checkin;

	private VerticalFieldManager manager1 = new VerticalFieldManager(
			VerticalFieldManager.USE_ALL_HEIGHT
					| VerticalFieldManager.USE_ALL_WIDTH);
	private final EditField identifierEditField = new EditField("", "", 15,
			EditField.EDITABLE);

	private XYEdges fieldMarginBetweenField = new XYEdges(10, 0, 0, 10);
	private XYEdges fieldMargin = new XYEdges(0, 10, 0, 10);
	private Background fieldBackground = BackgroundFactory
			.createLinearGradientBackground(Color.LIGHTGRAY, Color.LIGHTGRAY,
					Color.WHITE, Color.WHITE);
	private Border roundedBorder = BorderFactory.createRoundedBorder(
			new XYEdges(2, 2, 2, 2), Color.BLACK, Border.STYLE_SOLID);
	private TextFilter numericFilter = new NumericTextFilter(
			NumericTextFilter.ALLOW_DECIMAL);
	private ButtonField nextButton = new ButtonField("Aceptar",
			ButtonField.CONSUME_CLICK | ButtonField.FIELD_HCENTER);
	public final static int fontHeightTitle = (int) Math.floor(Font
			.getDefault().getHeight() * 0.8);
	public final static Font fontTitle = Font.getDefault().derive(Font.PLAIN,
			fontHeightTitle);
	private ResponseActionDelegate responseActionDelegate;
	private int nextScreen = 0;
	private int SUCCESSFUL_SCREEN = 2;

	private RouteEntity currentRoute;

	public VisitSimpleFormScreen(RouteEntity currentRoute) {
		this.currentRoute = currentRoute;
		responseActionDelegate = this;
		this.checkin = new Checkin(currentRoute);
	    buildView();

	}

	private void buildView() {
		responseActionDelegate = this;

		nextButton.setChangeListener(new FieldChangeListener() {
			public void fieldChanged(Field arg0, int arg1) {
				if (isValidData()) {

					setValuesForForm();
					registerVisit();
				}
			}

		});

		nextButton.setMargin(fieldMarginBetweenField);
		manager1.add(buildRouteView());
		manager1.add(buildTitle("INO"));
		manager1.add(buildEditFieldView(inoEditField));
		manager1.add(buildTitle("EG"));
		
		egEditField.setFilter(numericFilter);
		manager1.add(buildEditFieldView(egEditField));

		manager1.add(buildTitle("Datos"));
		manager1.add(buildEditFieldView(datosEditField));
		manager1.add(buildTitle("Causal"));
		manager1.add(buildCausalView());
		manager1.add(nextButton);

		this.add(manager1);
	}

	private void setValuesForForm() {
		checkin.setIno(inoEditField.getText());
		checkin.setEg(egEditField.getText());
		checkin.setDatos(datosEditField.getText());
		
		int indice = gestionChoiceField.getSelectedIndex();
		
		checkin.setGestionOE(gestionChoiceField.getChoice(indice).toString());
		checkin.setCausal(codigoCausal[indice]);
		
		if(indice < 16){
			checkin.setEstadoVisitaID(Checkin.EFECTIVA);
		}else
		{
			checkin.setEstadoVisitaID(Checkin.NO_EFECTIVA);
		}
		
	}

	private void registerVisit() {
		Thread threadToRun = new Thread() {
			public void run() {
				AddCheckinResourceHandler resourceHandler = new AddCheckinResourceHandler(
						checkin);
				resourceHandler.sendAPIMessage(responseActionDelegate);

			}
		};
		WaitDialogScreen.showScreenAndWait(threadToRun);
		goToNextScreen();

	}

	private void goToNextScreen() {

		if (nextScreen == SUCCESSFUL_SCREEN) {
			MainScreen routeScreen = new RouteSelectScreen();
			TransitionContext transition = new TransitionContext(
					TransitionContext.TRANSITION_SLIDE);
			transition.setIntAttribute(TransitionContext.ATTR_DURATION, 500);
			transition.setIntAttribute(TransitionContext.ATTR_DIRECTION,
					TransitionContext.DIRECTION_LEFT);
			transition.setIntAttribute(TransitionContext.ATTR_STYLE,
					TransitionContext.STYLE_PUSH);
//
//			// sets our transition
			UiEngineInstance engine = Ui.getUiEngineInstance();
			engine.setTransition(null, routeScreen,
					UiEngineInstance.TRIGGER_PUSH, transition);

		UiApplication.getUiApplication().pushScreen(routeScreen);
			  this.close();
			
		} else {
			Dialog dialog = new Dialog("Error", new String[] { "Aceptar" },
					new int[] { 1 }, 1,
					Bitmap.getPredefinedBitmap(Bitmap.EXCLAMATION), Dialog.D_OK);
			dialog.show();

		}

	}

	private VerticalFieldManager buildCausalView() {
		VerticalFieldManager formManager = new VerticalFieldManager();
		formManager.setPadding(new XYEdges(1, 1, 1, 1));
		formManager.setBackground(BackgroundFactory
				.createSolidBackground(Color.WHITE));

		gestionChoiceField = new ObjectChoiceField("", amountChoices);

		formManager.add(gestionChoiceField);

		return formManager;
	}

	private VerticalFieldManager buildEditFieldView(EditField localEditField) {
		VerticalFieldManager formManager = new VerticalFieldManager();
		formManager.setPadding(new XYEdges(1, 1, 1, 1));
		formManager.setBackground(BackgroundFactory
				.createSolidBackground(Color.WHITE));


		localEditField.setBackground(fieldBackground);
		localEditField.setBorder(roundedBorder);

		formManager.add(localEditField);
		return formManager;
	}

	private LabelField buildTitle(String title) {
		LabelField transfersSubTitle = new LabelField(title,
				LabelField.USE_ALL_WIDTH | DrawStyle.LEFT);
		transfersSubTitle.setFont(fontTitle);
		transfersSubTitle.setMargin(fieldMargin);
		transfersSubTitle.setMargin(fieldMarginBetweenField);
		return transfersSubTitle;
	}

	public static int fontBigHeight = (int) Math.floor(Font.getDefault()
			.getHeight() * 1.1);

	private HorizontalFieldManager buildRouteView() {

		XYEdges localMarginBetweenField = new XYEdges(1, 0, 1, 20);

		LabelField billerDescriptionLabel = new LabelField(
				currentRoute.getDescription(), LabelField.USE_ALL_WIDTH
						| DrawStyle.LEFT) {
			protected void paint(Graphics graphics) {
				//TODO: Color
				graphics.setColor(AppStyle.APP_CORPORATE_COLOR);
				super.paint(graphics);
			}
		};
		billerDescriptionLabel.setFont(billerDescriptionLabel.getFont().derive(
				Font.ITALIC, fontBigHeight));
		billerDescriptionLabel.setMargin(fieldMargin);
		billerDescriptionLabel.setMargin(localMarginBetweenField);

		LabelField categoryDescriptionLabel = new LabelField(
				currentRoute.getDescritionInfo(), LabelField.USE_ALL_WIDTH
						| DrawStyle.LEFT);
		categoryDescriptionLabel.setFont(fontTitle);
		categoryDescriptionLabel.setMargin(fieldMargin);
		categoryDescriptionLabel.setMargin(localMarginBetweenField);

		HorizontalFieldManager hManager = new HorizontalFieldManager(
				HorizontalFieldManager.FIELD_LEFT);

		VerticalFieldManager vManager = new VerticalFieldManager();
		vManager.setMargin(new XYEdges(0, 0, 5, 0));
		vManager.setPadding(new XYEdges(1, 1, 1, 1));
		vManager.setBackground(BackgroundFactory
				.createSolidBackground(0xECEDEE));
		identifierEditField.setFilter(numericFilter);
		identifierEditField.setBackground(fieldBackground);
		identifierEditField.setBorder(roundedBorder);
		vManager.add(billerDescriptionLabel);
		vManager.add(categoryDescriptionLabel);

		hManager.add(vManager);
		hManager.setBackground(BackgroundFactory
				.createSolidBackground(0xECEDEE));

		return hManager;

	}

	private boolean isValidData() {

		if (inoEditField.getText().length() == 0) {
			Dialog dialog = new Dialog(Dialog.D_OK, "Debe ingresar el INO",
					Dialog.OK, Bitmap.getPredefinedBitmap(Bitmap.EXCLAMATION),
					Manager.VERTICAL_SCROLL);
			dialog.show();
			return false;
		}

		if (egEditField.getText().length() == 0) {
			Dialog dialog = new Dialog(Dialog.D_OK, "Debe ingresar el EG",
					Dialog.OK, Bitmap.getPredefinedBitmap(Bitmap.EXCLAMATION),
					Manager.VERTICAL_SCROLL);
			dialog.show();
			return false;
		}


		return true;
	}

	public void didSuccessfully(String message) {
		nextScreen = SUCCESSFUL_SCREEN;
	}

	public void didNotSuccessfully(String message) {
	}
	
	public boolean onClose() 
	{
	  this.close();
	  return true;
	}

}
