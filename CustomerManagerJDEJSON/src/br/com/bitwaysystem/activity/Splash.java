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

public class Splash extends Activity implements Runnable {

	// ! ID of the progress dialog.
	private final int DIALOG_PROGRESS = 1;

	@Override
	protected Dialog onCreateDialog(int id) {
		if (id == DIALOG_PROGRESS) {

			ProgressDialog dialog = ProgressDialog.show(Splash.this,
					"Bem vindo...", "Carregando, por favor aguarde...", false,
					false);
			return dialog;
		} else {
			return null;
		}
	}

	@SuppressWarnings("deprecation")
	public void onCreate(Bundle savedInstanceState) {

		// Remove title bar
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);

		// Remove notification bar
		this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);

		showDialog(DIALOG_PROGRESS);

		super.onCreate(savedInstanceState);
		setContentView(R.layout.splash);

		Handler handler = new Handler();
		handler.postDelayed(this, 2000);
	}

	public void run() {
		startActivity(new Intent(this, GetCustomerCreditInfo.class));
		finish();
	}
}
