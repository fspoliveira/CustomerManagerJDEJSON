package br.com.bitwaysystem.activity;

import com.example.customermanagerjdejson.R;
import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class EndpointActivity extends Activity implements View.OnClickListener {

	private SharedPreferences prefs;
	private String prefName = "MyPref";
	private static final String FONT_SIZE_KEY = "fontsize";
	private static final String TEXT_VALUE_KEY = "textvalue";
	private EditText editText;


	@Override
	public void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.endpoint);

		final Button ok = (Button) findViewById(R.id.ButtonOk);
		ok.setOnClickListener(this);

		final Button back = (Button) findViewById(R.id.ButtonBack);
		back.setOnClickListener(this);
		
		editText = (EditText) findViewById(R.id.txtURL);
		
	     SharedPreferences prefs = getSharedPreferences(prefName, MODE_PRIVATE);
	     
	     String urlDefault = getResources().getString(R.string.URLDefautl);
	     
	     String pontuacao = prefs.getString(getString(R.string.url), urlDefault);
	     
	     editText.setText(pontuacao.toString());


	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {

		case R.id.ButtonOk:
			 //---get the SharedPreferences object---
            prefs = getSharedPreferences(prefName, MODE_PRIVATE);
            SharedPreferences.Editor editor = prefs.edit();
            //---save the values in the EditText view to preferences---
             editor.putString(getString(R.string.url), editText.getText().toString());
            //---saves the values---
            editor.commit();

			break;

		case R.id.ButtonBack:
			finish();

		}

	}

}