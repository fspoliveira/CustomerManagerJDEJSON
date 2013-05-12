package br.com.bitwaysystem.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import br.com.bitwaysystem.bean.Entity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import br.com.bitwaysystem.bean.ShowCustomerCreditInformation;
import br.com.bitwaysystem.service.RestMethods;
import br.com.bitwaysystem.util.Connection;
import br.com.bitwaysystem.util.FormatCNPJorCPF;
import com.example.customermanagerjdejson.R;

/**
 * GetCustomerCreditInfo é a classe base principal da aplicação responsável por
 * fazer a conexão com o servidor Oracle Service Bus e consumir o web service no
 * formato REST/JSON
 * 
 * @author Fernando Santiago
 * @version %I%, %G%
 * @since 1.0
 * */

public class GetCustomerCreditInfo extends Activity implements
		View.OnClickListener {

	private final int DIALOG_PROGRESS = 1;
	private final int DIALOG_EXIT = 2;
	private String prefName = "EndpointServer";

	/**
	 * Botao do celular pressionado
	 * 
	 * @param keyCode
	 * @param event
	 * @return true se o botao voltar foi pressionado
	 * @author Fernando Santiago
	 * @version %I%, %G%
	 * @since 1.0
	 */
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {

			this.onBackPressed();
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}

	/**
	 * Botao voltar do celular pressionado
	 * 
	 * @author Fernando Santiago
	 * @version %I%, %G%
	 * @since 1.0
	 */
	@SuppressWarnings("deprecation")
	@Override
	public void onBackPressed() {
		showDialog(DIALOG_EXIT);
	}

	/**
	 * Selecionar o Item do menu
	 * 
	 * @param featureId
	 * @param item
	 * @author Fernando Santiago
	 * @return true caso o menu selecionado seja o URLDefault, senão retorna
	 *         false
	 * @version %I%, %G%
	 * @since 1.0
	 */
	@Override
	public boolean onMenuItemSelected(int featureId, MenuItem item) {
		super.onMenuItemSelected(featureId, item);

		switch (item.getItemId()) {
		// Finaliza a aplicação
		case R.id.iSair:
			finish();
			break;

		// Efetua chamada da tela de configuração do Endpoint da Aplicação
		case R.id.iEndpoint:
			startActivity(new Intent(this, EndpointActivity.class));
			break;
		}

		return false;
	}

	/**
	 * Cria a intencao EndpointActivity baseada no layoyt layout
	 * 
	 * @param savedInstanceState
	 * @author Fernando Santiago
	 * @version %I%, %G%
	 * @since 1.0
	 */
	@Override
	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.layout);

		Button ok = (Button) findViewById(R.id.button1);
		ok.setOnClickListener(this);
	}

	/**
	 * Criação da Caixa de Diálogo
	 * 
	 * @param id
	 * @return alertDialog
	 * @author Fernando Santiago
	 * @version %I%, %G%
	 * @since 1.0
	 */
	@Override
	protected Dialog onCreateDialog(int id) {
		// Dialog progress que é invocada quando o web service está sendo
		// consumindo
		if (id == DIALOG_PROGRESS) {

			ProgressDialog dialog = new ProgressDialog(this);
			dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
			dialog.setMessage("Consultando");
			dialog.setCancelable(false);
			return dialog;
		} else {
			// Caixa de diálogo que confirma saída do usuário
			if (id == DIALOG_EXIT) {
				AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
						this);
				alertDialogBuilder.setTitle("Mensagem");

				alertDialogBuilder
						.setMessage("Deseja realmente sair?")
						.setCancelable(false)
						.setPositiveButton("Sim",
								new DialogInterface.OnClickListener() {
									public void onClick(DialogInterface dialog,
											int id) {
										finish();
									}
								})
						.setNegativeButton("Não",
								new DialogInterface.OnClickListener() {
									public void onClick(DialogInterface dialog,
											int id) {
										dialog.cancel();
									}
								});

				AlertDialog alertDialog = alertDialogBuilder.create();
				return alertDialog;
			}
		}
		return null;
	}

	/**
	 * Método cria o menu Endpoint
	 * 
	 * @param menu
	 * @return true
	 * @author Fernando Santiago
	 * @version %I%, %G%
	 * @since 1.0
	 */
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		super.onCreateOptionsMenu(menu);
		MenuInflater blowUp = getMenuInflater();
		blowUp.inflate(R.menu.down_menu, menu);

		return true;
	}

	/**
	 * Método clicar no botao ok
	 * 
	 * @param menu
	 * @return true
	 * @author Fernando Santiago
	 * @version %I%, %G%
	 * @since 1.0
	 */
	@Override
	public void onClick(View v) {

		// Verifica se o cliente está conectado na WiFi ou 3G
		if (Connection.conectado(getBaseContext())) {
			TextView idCliente = (TextView) findViewById(R.id.txt_idCliente);

			/** Id do cliente é obrigatório */
			if (idCliente.getText().toString().equals("")) {
				this.camposObrigatorios("Id Cliente é obrigatório");
			} else {
				// Invoca web service REST para consultar o limite de crédito
				GetCustomerCreditInfoTask task = new GetCustomerCreditInfoTask();
				task.execute(idCliente.getText().toString());
			}

		} else {
			/** Sem conexão com a Internet */
			Toast.makeText(getApplicationContext(),
					"Sem conexão com a Internet", Toast.LENGTH_SHORT).show();

		}

	}

	/**
	 * The AsyncTask é responsável por recuperar as informações de Crédito do
	 * Cliente O processo é assíncrono para não bloquear a UI enquanto o web
	 * service é chamado
	 */
	private class GetCustomerCreditInfoTask extends
			AsyncTask<String, Void, ShowCustomerCreditInformation> {
		/**
		 * Executa a UI thread antes doInBackground(Params...).
		 */
		@SuppressWarnings("deprecation")
		@Override
		protected void onPreExecute() {
			showDialog(DIALOG_PROGRESS);
		}

		/**
		 * Esconde a caixa de diálogo
		 */
		@SuppressWarnings("deprecation")
		@Override
		protected void onCancelled() {
			dismissDialog(DIALOG_PROGRESS);
		}

		/**
		 * Executa UI thread depois doInBackground(Params...) foi completado.
		 * Esconde a caixa de diálogo "Consultando" e apresenta resultado nos
		 * respectivos TextView.
		 */
		@SuppressWarnings("deprecation")
		@Override
		protected void onPostExecute(ShowCustomerCreditInformation result) {
			dismissDialog(DIALOG_PROGRESS);

			if (m_error != null) {
				Toast.makeText(GetCustomerCreditInfo.this, m_error,
						Toast.LENGTH_SHORT).show();
				return;
			}

			/** Recupera objeto result e atribui aos campos da tela */
			if (result != null) {
				TextView limiteDeCredito = (TextView) findViewById(R.id.txt_Limite);
				TextView cnpjOucpf = (TextView) findViewById(R.id.txt_CNPJCPF);
				TextView pedidoAberto = (TextView) findViewById(R.id.txt_PedidoAberto);

				limiteDeCredito.setText("");
				cnpjOucpf.setText("");
				pedidoAberto.setText("");

				limiteDeCredito.setText(String.valueOf(result
						.getAmountCreditLimit()));

				if (!String.valueOf(result.getEntity().getEntityTaxId())
						.equals("")) {

					String cnpjOucpfformat;

					if (String.valueOf(result.getEntity().getEntityTaxId())
							.length() == 11) {
						cnpjOucpfformat = FormatCNPJorCPF.formatarCpf(String
								.valueOf(result.getEntity().getEntityTaxId()));
					} else {
						cnpjOucpfformat = FormatCNPJorCPF.formatarCnpj(String
								.valueOf(result.getEntity().getEntityTaxId()));
					}

					cnpjOucpf.setText(cnpjOucpfformat);
				}

				if (!String.valueOf(result.getAmountTotalExposure()).equals("")) {
					pedidoAberto.setText(String.valueOf(result
							.getAmountTotalExposure()));
				}

				/** Tratamento de erro do SOA-SUITE */
				if (String.valueOf(result.getErrorCodeBea()).equals(
						"BEA-380001")) {
					Toast.makeText(getApplicationContext(),
							"Id do cliente não existe no Cadastro Geral",
							Toast.LENGTH_LONG).show();

				} else if (String.valueOf(result.getErrorCodeBea()).equals(
						"BEA-380002")) {
					Toast.makeText(getApplicationContext(),
							"Falha de conexão com o servidor",
							Toast.LENGTH_LONG).show();
				}

			} else {
				Toast.makeText(getApplicationContext(),
						"Falha de conexão com o servidor", Toast.LENGTH_SHORT)
						.show();
			}
		}

		/**
		 * Performs the web service invocation on a separate thread.
		 * 
		 * The first parameter in params should be the "fromCurrency" value. The
		 * second parameter in params should be the "toCurrency" value.
		 */
		@Override
		protected ShowCustomerCreditInformation doInBackground(String... params) {
			if ((params == null))
				return null; // Shouldn't happen.

			Entity entity = new Entity(Integer.parseInt(params[0]));

			ShowCustomerCreditInformation customer = new ShowCustomerCreditInformation(
					entity);

			// Recupera uri
			SharedPreferences prefs = getSharedPreferences(prefName,
					MODE_PRIVATE);

			customer.setUri((prefs.getString(getString(R.string.url),
					getResources().getString(R.string.URLDefautl))));

			customer = RestMethods.showCredit(customer);

			return customer;
		}

		private String m_error = null;
	}

	/**
	 * Metodo ao clicar no botao
	 * 
	 * @param mensagem
	 *            - Mensagem de erro
	 * @author Fernando Santiago
	 * @version %I%, %G%
	 * @since 1.0
	 */
	public void camposObrigatorios(String mensagem) {
		AlertDialog.Builder m = new AlertDialog.Builder(this);
		m.setTitle("Aviso");
		m.setMessage(mensagem);
		m.setPositiveButton("OK", null);
		m.show();
	}
}