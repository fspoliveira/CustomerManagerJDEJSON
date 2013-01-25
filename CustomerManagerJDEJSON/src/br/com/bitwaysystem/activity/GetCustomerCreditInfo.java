package br.com.bitwaysystem.activity;

import android.annotation.SuppressLint;
import android.app.Activity;
import br.com.bitwaysystem.bean.Entity;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
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
		
		TextView LimiteDeCredito = (TextView) findViewById(R.id.txt_Limite);
		TextView CNPJouCPF = (TextView) findViewById(R.id.txt_CNPJCPF);
		
		/*Reset variables*/
		LimiteDeCredito.setText("0");
		CNPJouCPF.setText("0");
		
		StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
				.permitAll().build();
		StrictMode.setThreadPolicy(policy);
		TextView idCliente_txt = (TextView) findViewById(R.id.idCliente_txt);

		int idCliente_an8 = Integer
				.parseInt(idCliente_txt.getText().toString());

		Entity entity = new Entity(idCliente_an8);

		ShowCustomerCreditInformation customer = new ShowCustomerCreditInformation(
				entity);
		
		ShowCustomerCreditInformation response = RestMethods.showCredit(customer);
		
		LimiteDeCredito.setText(String.valueOf(response.getAmountCreditLimit()));
		
		if(!String.valueOf(response.getEntity().getEntityTaxId()).equals("")){
			CNPJouCPF.setText(String.valueOf(response.getEntity().getEntityTaxId()));			
		}

	}

}