package br.com.bitwaysystem.activity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import br.com.bitwaysystem.bean.Entity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import br.com.bitwaysystem.bean.ShowCustomerCreditInformation;
import br.com.bitwaysystem.service.RestMethods;
import com.example.customermanagerjdejson.R;

public class GetCustomerCreditInfo extends Activity implements
		View.OnClickListener {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		Button ok = (Button) findViewById(R.id.button1);
		ok.setOnClickListener(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

	@SuppressLint("NewApi")
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
				.permitAll().build();
		StrictMode.setThreadPolicy(policy);
		TextView idCliente_txt = (TextView) findViewById(R.id.idCliente_txt);

		int idCliente_an8 = Integer
				.parseInt(idCliente_txt.getText().toString());

		Entity entity = new Entity(idCliente_an8);

		ShowCustomerCreditInformation customer = new ShowCustomerCreditInformation(
				entity);
		RestMethods.ShowCustomerCreditInformation(customer);

	}

}
