package br.com.bitwaysystem.service;

import java.io.IOException;
import java.io.InputStream;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;

import android.util.Log;
import br.com.bitwaysystem.bean.ShowCustomerCreditInformation;
import br.com.bitwaysystem.json.JSONtoJava;

public class RestMethods {

	protected static String getASCIIContentFromEntity(HttpEntity entity)
			throws IllegalStateException, IOException {
		InputStream in = entity.getContent();
		StringBuffer out = new StringBuffer();
		int n = 1;
		while (n > 0) {
			byte[] b = new byte[4096];
			n = in.read(b);
			if (n > 0)
				out.append(new String(b, 0, n));
		}
		return out.toString();
	}

	public static ShowCustomerCreditInformation showCredit(
			ShowCustomerCreditInformation showCustomerCI) {

		HttpClient httpClient = new DefaultHttpClient();
		HttpContext localContext = new BasicHttpContext();
		String uri = "http://soa-suite.no-ip.org:7001/exposing-restful-service/CustomerManagerServiceJSON?id="
				+ showCustomerCI.getEntity().getEntityId();

		HttpGet httpGet = new HttpGet(uri);
		String text = null;
		try {
			HttpResponse response = httpClient.execute(httpGet, localContext);
			HttpEntity entity = response.getEntity();
			
			 if (entity != null) {
			
			text = getASCIIContentFromEntity(entity);	
			 }
			 else{
				 
				 Log.w("Chegou nULL DO RESPONSE", text);
				 
			 }
			
		} catch (Exception e) {
			e.getLocalizedMessage();
		}
		
		Log.w("O que chegou no retorno do response", text);
		
		ShowCustomerCreditInformation showCreditInfo = JSONtoJava.showCredit(text);
		return showCreditInfo;
	}
}