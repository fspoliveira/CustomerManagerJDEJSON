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

/**
 * RestMethods é a classe Base que faz o consumo do Web Service REST/JSON do
 * Oracle Service Bus
 * 
 * @author Fernando Santiago
 * @version %I%, %G%
 * @since 1.0
 * */
public class RestMethods {

	/**
	 * getASCIIContentFromEntity é que recebe a informação do protocolo HTTP e
	 * transforma para String
	 * 
	 * @param entity
	 *            - Objeto HTTP
	 * @return String - String JSON
	 * @author Fernando Santiago
	 * @version %I%, %G%
	 * @since 1.0
	 * */
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

	/**
	 * ShowCustomerCreditInformation efetua a conexão com servidor
	 * 
	 * @param ShowCustomerCreditInformation
	 *            - id do cliente que está sendo consultado
	 * @return ShowCustomerCreditInformation - Objeto com as informações do
	 *         cliente que foram recuperadas do web service consumido
	 * @author Fernando Santiago
	 * @version %I%, %G%
	 * @since 1.0
	 * */
	public static ShowCustomerCreditInformation showCredit(
			ShowCustomerCreditInformation showCustomerCI) {

		ShowCustomerCreditInformation showCreditInfo = null;

		HttpParams httpParameters = new BasicHttpParams();

		// Timeout até que a conexão seja estabelecida
		// O valor padrão é Zero que indica que o timeout não foi usado
		int timeoutConnection = 4000;
		HttpConnectionParams.setConnectionTimeout(httpParameters,
				timeoutConnection);

		// Tempo padrão do socket (SO_TIMEOUT)
		// Tempo em milisegndos para aguardar os dados
		int timeoutSocket = 6000;
		HttpConnectionParams.setSoTimeout(httpParameters, timeoutSocket);

		// Monta a query String
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