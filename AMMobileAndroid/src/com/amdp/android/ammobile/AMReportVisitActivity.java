package com.amdp.android.ammobile;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.admp.android.ammobile.entity.AMReportVisit;
import com.admp.android.ammobile.entity.AMRoute;
import com.admp.android.ammobile.entity.CausalNoVisitaBLL;
import com.amdp.android.ammobile.handler.AddCheckinResourceHandler;
import com.amdp.android.api.IResponseActionDelegate;
import com.amdp.android.api.IStatus;
import com.amdp.android.basic.entity.APIEntity;
import com.amdp.android.basic.entity.Route;
import com.amdp.android.basic.entity.RouteBLL;
import com.amdp.android.basic.entity.UserBLL;
import com.amdp.android.basic.gui.RouteActivity;
import com.amdp.android.basic.gui.utils.DataAdapter;
import com.amdp.android.basic.sync.CategoricDataSynchronizer;

public class AMReportVisitActivity extends Activity implements IResponseActionDelegate {

	private TextView routeTitle;
	private TextView routeInfo;
	private EditText inoEditText;
	private EditText egEditText;
	private EditText datosEditText;
	private Spinner causalSpinner;
	private AMRoute currentRoute;

	private CausalNoVisitaBLL causalNoVisitaBLL = CausalNoVisitaBLL.getInstance();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.report_visit_form);
		routeTitle = (TextView) findViewById(R.id.routeTitle);
		routeInfo = (TextView) findViewById(R.id.routeInfo);

		inoEditText = (EditText) findViewById(R.id.inoEditText);
		egEditText = (EditText) findViewById(R.id.egEditText);
		datosEditText = (EditText) findViewById(R.id.datosEditText);
		causalSpinner = (Spinner) findViewById(R.id.causal_spinner);

		currentRoute = (AMRoute) RouteBLL.getInstance().getCurrentRoute();
		routeTitle.setText(currentRoute.getVenueName());
		routeInfo.setText(currentRoute.getDescritionInfo());

		Button buttonOk = (Button) findViewById(R.id.ok_button);
		buttonOk.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(final View v) {
				sendMessage();
			}
		});

		updateCategoricData();

	}

	private boolean isValidData() {

		boolean isValidData = true;

		if (inoEditText.getText().toString().length() == 0) {
			Toast.makeText(this, "Debe ingresar el INO", Toast.LENGTH_LONG).show();
			isValidData = false;
		}

		if (egEditText.getText().length() == 0) {
			Toast.makeText(this, "Debe ingresar el EG", Toast.LENGTH_LONG).show();
			isValidData = false;
		}

		return isValidData;
	}

	private void updateCategoricData() {

		if (causalNoVisitaBLL.isEmpty()) {
			causalNoVisitaBLL.add(new APIEntity("D23", "EE Normal"));
			causalNoVisitaBLL.add(new APIEntity("D24", "EE Plus"));
			causalNoVisitaBLL.add(new APIEntity("D25", "EE En Sucursal"));
			causalNoVisitaBLL.add(new APIEntity("D26", "Retorno"));
			causalNoVisitaBLL.add(new APIEntity("D27", "DT Producto"));
			causalNoVisitaBLL.add(new APIEntity("D28", "Rechaza Producto"));
			causalNoVisitaBLL.add(new APIEntity("D29", "Confirma con el Banco"));
			causalNoVisitaBLL.add(new APIEntity("D210", "GD Fallida"));
			causalNoVisitaBLL.add(new APIEntity("D211", "Sin ID"));
			causalNoVisitaBLL.add(new APIEntity("D212", "ID No Coincide"));
			causalNoVisitaBLL.add(new APIEntity("D213", "Datos B‡sicos Errados"));
			causalNoVisitaBLL.add(new APIEntity("D214", "Radicado en Otro Lugar"));
			causalNoVisitaBLL.add(new APIEntity("D215", "De Viaje (+ de 1 semana)"));
			causalNoVisitaBLL.add(new APIEntity("D216", "Hospitalizado (+ de 1 semana)"));
			causalNoVisitaBLL.add(new APIEntity("D217", "Fallecido"));
			causalNoVisitaBLL.add(new APIEntity("D218", "Otro "));
			causalNoVisitaBLL.add(new APIEntity("G21", "No Se Encuentra - Se Deja Tarjeta de Visita"));
			causalNoVisitaBLL.add(new APIEntity("G22", "No Labora All’ - Se Deja Tarjeta de Visita"));
			causalNoVisitaBLL.add(new APIEntity("G23", "No Vive All’ - Se Deja Tarjeta de Visita"));
			causalNoVisitaBLL.add(new APIEntity("G24", "G Cerrado - Se Deja Tarjeta de Visita"));
			causalNoVisitaBLL.add(new APIEntity("G25", "Se Obtienen Datos Nuevos"));
			causalNoVisitaBLL.add(new APIEntity("G26", "Fuera de Ruta"));
			causalNoVisitaBLL.add(new APIEntity("G27", "No Labora All’"));
			causalNoVisitaBLL.add(new APIEntity("G28", "No Vive All’"));
			causalNoVisitaBLL.add(new APIEntity("G29", "No Lo Conocen"));
			causalNoVisitaBLL.add(new APIEntity("G210", "G No Existe"));
			causalNoVisitaBLL.add(new APIEntity("G211", "G Incompleto"));
			causalNoVisitaBLL.add(new APIEntity("G212", "Zona Roja"));
			causalNoVisitaBLL.add(new APIEntity("G213", "Dificil Acceso"));
			causalNoVisitaBLL.add(new APIEntity("G214", "Incumplimiento CO"));
			causalNoVisitaBLL.add(new APIEntity("G215", "Incumplimiento CL"));
			causalNoVisitaBLL.add(new APIEntity("G216", "3 Gestiones OE Fallidas"));
			causalNoVisitaBLL.add(new APIEntity("G217", "Otro"));
		}

		causalSpinner.setAdapter(new DataAdapter().getAdapter(this, new CategoricDataSynchronizer("CategoricalData/Payments", causalNoVisitaBLL)));
	}

	private void sendMessage() {
		if (isValidData()) {

			AMReportVisit reportVisit = new AMReportVisit();
			reportVisit.setRutaID(currentRoute.getId());
			reportVisit.setEG(egEditText.getText().toString());
			reportVisit.setINO(inoEditText.getText().toString());
			reportVisit.setDatos(datosEditText.getText().toString());
			reportVisit.setGestionOE(causalSpinner.getSelectedItem().toString());
			reportVisit.setCausal(causalNoVisitaBLL.getItemByName(causalSpinner.getSelectedItem().toString()).getId());
			reportVisit.setUsuarioID(UserBLL.getInstanceBLL().getCurrentUser().getUserId());

			int indice = causalSpinner.getSelectedItemPosition();
			if (indice < 16) {
				reportVisit.setEstadoVisitaID(AMReportVisit.EFECTIVA);
			} else {
				reportVisit.setEstadoVisitaID(AMReportVisit.NO_EFECTIVA);
			}
			currentRoute.setState(Route.ROUTE_ACCOUMPLISHED);

			AddCheckinResourceHandler resourceHandler = new AddCheckinResourceHandler(reportVisit);
			resourceHandler.setRequestHandle(this, this);
		}

	}

	@Override
	public void didSuccessfully(IStatus status) {
		Toast.makeText(this, "La visita fue registrada correctamente: " + status.getDescription(), Toast.LENGTH_LONG).show();
		Intent intent = new Intent(AMReportVisitActivity.this, RouteActivity.class);
		startActivity(intent);
		finish();
	}

	@Override
	public void didNotSuccessfully(IStatus status) {
		Toast.makeText(this, status.getDescription(), Toast.LENGTH_LONG).show();

	}
}
