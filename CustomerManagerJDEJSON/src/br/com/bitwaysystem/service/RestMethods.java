package br.com.bitwaysystem.service;

import java.io.IOException;
import java.io.InputStream;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
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

		ShowCustomerCreditInformation showCreditInfo = null;

		HttpParams httpParameters = new BasicHttpParams();

		// Set the timeout in milliseconds until a connection is established.
		// The default value is zero, that means the timeout is not used.
		int timeoutConnection = 4000;
		HttpConnectionParams.setConnectionTimeout(httpParameters,
				timeoutConnection);

		// Set the default socket timeout (SO_TIMEOUT)
		// in milliseconds which is the timeout for waiting for data.
		int timeoutSocket = 6000;
		HttpConnectionParams.setSoTimeout(httpParameters, timeoutSocket);

		String uri = showCustomerCI.getUri() + "?id="
				+ showCustomerCI.getEntity().getEntityId();

		DefaultHttpClient httpClient = new DefaultHttpClient(httpParameters);

		HttpGet httpGet = new HttpGet(uri);
		String text = null;
		try {

			HttpResponse response = httpClient.execute(httpGet);
			HttpEntity entity = response.getEntity();
			text = getASCIIContentFromEntity(entity);
			showCreditInfo = JSONtoJava.showCredit(text);

		} catch (Exception e) {
			e.getLocalizedMessage();
		}

		return showCreditInfo;
	}
}