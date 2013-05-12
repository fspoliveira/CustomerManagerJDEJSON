package br.com.bitwaysystem.activity;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.view.WindowManager;
import com.example.customermanagerjdejson.R;

/**
 * Splash é a Classe da tela de Apresentação do programa
 * <p>
 * A tela fica carregando durante 2 segundos
 * 
 * @author Fernando Santiago
 * @version %I%, %G%
 * @since 1.0
 * */

public class Splash extends Activity implements Runnable {

	private final int DIALOG_PROGRESS = 1;

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
		if (id == DIALOG_PROGRESS) {

			ProgressDialog dialog = ProgressDialog.show(Splash.this, "",
					"Carregando", false, false);

			return dialog;
		} else {
			return null;
		}
	}

	/**
	 * Cria a intencao EndpointActivity baseada no layoyt splash
	 * 
	 * @param savedInstanceState
	 * @author Fernando Santiago
	 * @version %I%, %G%
	 * @since 1.0
	 */
	public void onCreate(Bundle savedInstanceState) {

		// Remove o título da aplicação
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);

		// Remove barra de notificação
		this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);

		super.onCreate(savedInstanceState);
		setContentView(R.layout.splash);

		Handler handler = new Handler();
		handler.postDelayed(this, 2000);
	}

	/**
	 * Efetua chamada da aplicação consulta Limite de Cŕedito
	 * 
	 * @author Fernando Santiago
	 * @version %I%, %G%
	 * @since 1.0
	 */
	public void run() {
		startActivity(new Intent(this, GetCustomerCreditInfo.class));
		finish();
	}
}