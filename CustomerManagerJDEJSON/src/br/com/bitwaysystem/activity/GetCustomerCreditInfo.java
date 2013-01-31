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
import br.com.bitwaysystem.util.FormatCNPJorCPF;

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
		
		TextView limiteDeCredito = (TextView) findViewById(R.id.txt_Limite);
		TextView cnpjOucpf = (TextView) findViewById(R.id.txt_CNPJCPF);
		TextView pedidoAberto = (TextView) findViewById(R.id.txt_PedidoAberto);
		
		/*Reset variables*/
		limiteDeCredito.setText("0");
		cnpjOucpf.setText("0");
		
		StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
				.permitAll().build();
		StrictMode.setThreadPolicy(policy);
		TextView idCliente_txt = (TextView) findViewById(R.id.txt_idCliente);

		int idCliente_an8 = Integer
				.parseInt(idCliente_txt.getText().toString());

		Entity entity = new Entity(idCliente_an8);

		ShowCustomerCreditInformation customer = new ShowCustomerCreditInformation(
				entity);
		
		ShowCustomerCreditInformation response = RestMethods.showCredit(customer);
		
		limiteDeCredito.setText(String.valueOf(response.getAmountCreditLimit()));
		
		if(!String.valueOf(response.getEntity().getEntityTaxId()).equals("")){
			
			String cnpjOucpfformat;
			
			if(String.valueOf(response.getEntity().getEntityTaxId()).length() == 11){
				cnpjOucpfformat = FormatCNPJorCPF.formatarCpf(String.valueOf(response.getEntity().getEntityTaxId()));
			}
			else
			{
			    cnpjOucpfformat = FormatCNPJorCPF.formatarCnpj(String.valueOf(response.getEntity().getEntityTaxId()));
			}
			
			cnpjOucpf.setText(cnpjOucpfformat);			
		}
		
		if(!String.valueOf(response.getAmountTotalExposure()).equals("")){
			pedidoAberto.setText(String.valueOf(response.getAmountTotalExposure()));			
		}

	}

}