package br.com.bitwaysystem.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.view.WindowManager;

import com.example.customermanagerjdejson.R;

public class Splash extends Activity implements Runnable {

		public void onCreate(Bundle savedInstanceState) {
			
			//Remove title bar
			this.requestWindowFeature(Window.FEATURE_NO_TITLE);

			//Remove notification bar
			this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
			
			super.onCreate(savedInstanceState);
			setContentView(R.layout.splash);

			Handler handler = new Handler();
			handler.postDelayed(this, 1000);
		}

		public void run(){
			startActivity(new Intent(this, GetCustomerCreditInfo.class));
			finish();
		}
	}
