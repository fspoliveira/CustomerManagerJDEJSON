package br.com.bitwaysystem.activity;

import br.com.bitwaysystem.util.Validates;

import com.example.customermanagerjdejson.R;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class EndpointActivity extends Activity implements View.OnClickListener {

	private SharedPreferences prefs;
	private String prefName = "EndpointServer";
	private EditText editText;
	private final int DIALOG_URL = 1;

	@Override
	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.endpoint);

		final Button ok = (Button) findViewById(R.id.ButtonOk);
		ok.setOnClickListener(this);

		final Button back = (Button) findViewById(R.id.ButtonBack);
		back.setOnClickListener(this);

		editText = (EditText) findViewById(R.id.txtURL);

		// Recupera URL do Shared Prefereces, se o arquivo não existe recupera
		// do Default do arquivo String
		SharedPreferences prefs = getSharedPreferences(prefName, MODE_PRIVATE);

		editText.setText(prefs.getString(getString(R.string.url),
				getResources().getString(R.string.URLDefautl)));

	}

	@SuppressWarnings("deprecation")
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {

		case R.id.ButtonOk:

			Validates.url = editText.getText().toString();

			if (Validates.validateURL()) {

				// ---get the SharedPreferences object---
				prefs = getSharedPreferences(prefName, MODE_PRIVATE);
				SharedPreferences.Editor editor = prefs.edit();

				// ---save the values in the EditText view to preferences---
				/* Armazena a chave "url" e endpoint */
				editor.putString(getString(R.string.url), editText.getText()
						.toString());
				// ---saves the values---
				editor.commit();

				finish();

			} else {

				/* Url inválida */
				showDialog(DIALOG_URL);

			}

			break;

		case R.id.ButtonBack:
			finish();

		}

	}

	protected Dialog onCreateDialog(int id) {
		if (id == DIALOG_URL) {

			AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
					this);
			alertDialogBuilder.setTitle("URL Inválida");
			alertDialogBuilder.setMessage("Endereço do servidor inválido");
			alertDialogBuilder.setPositiveButton("OK", null);
			AlertDialog alertDialog = alertDialogBuilder.create();
			return alertDialog;

		}
		return null;

	}

}