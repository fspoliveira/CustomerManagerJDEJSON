package br.com.bitwaysystem.activity;

import com.example.customermanagerjdejson.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class EndpointActivity extends Activity implements
View.OnClickListener{
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.endpoint);

		Button ok = (Button) findViewById(R.id.button1);
		ok.setOnClickListener(this);
	}
	
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
	}

}