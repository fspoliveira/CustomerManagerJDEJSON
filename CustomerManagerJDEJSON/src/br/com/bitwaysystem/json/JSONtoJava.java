package br.com.bitwaysystem.json;

import org.json.JSONException;
import org.json.JSONObject;
import br.com.bitwaysystem.bean.Entity;
import br.com.bitwaysystem.bean.ShowCustomerCreditInformation;

/**
 * JSONtoJava é a classe Base que efetua a conversão do objeto JSON para um
 * objeto Java
 * 
 * @author Fernando Santiago
 * @version %I%, %G%
 * @since 1.0
 * */
public class JSONtoJava {

	/**
	 * ShowCustomerCreditInformation é o método que recebe a String JSON, faz o
	 * parse e transforma par um objeto Java
	 * 
	 * @param strJSON
	 *            - String JSON que foi recuperada pelo consumo do web services
	 * @return showCredit - Objeto Java com as informações do cliente, ou os
	 *         campos de erro populados com o motivo do erro
	 * @author Fernando Santiago
	 * @version %I%, %G%
	 * @since 1.0
	 * */
	public static ShowCustomerCreditInformation showCredit(String strJson) {

		Entity entity = new Entity("", "", 0);
		ShowCustomerCreditInformation customer = new ShowCustomerCreditInformation(
				0.0, 0.0, true, "", entity, "", "");

		try {

			JSONObject userObject = new JSONObject(strJson);
			String xmlFragment = null;
			String helloResponse = null;

			/* Retorno sem erro */

			if ((userObject.has("getCustomerCreditInformationResponse"))) {

				String getCustomerCreditInformationResponse = userObject
						.getString("getCustomerCreditInformationResponse");

				JSONObject userObject1 = new JSONObject(
						getCustomerCreditInformationResponse);

				customer.setAmountCreditLimit(userObject1
						.getDouble("amountCreditLimit"));

				customer.setAmountTotalExposure(userObject1
						.getDouble("amountTotalExposure"));

				JSONObject entityJSON = (JSONObject) userObject1.get("entity");

				entity.setEntityTaxId(entityJSON.getString("entityTaxId"));

				customer.setEntity(entity);
			}

			else if (userObject.has("xml-fragment")) {

				/* Servidor SOA-SUITE (WebLogic fora do ar) */

				xmlFragment = userObject.getString("xml-fragment");
				JSONObject userObject1 = new JSONObject(xmlFragment);
				helloResponse = userObject1.getString("errorHandler");
				JSONObject userObject2 = new JSONObject(helloResponse);
				customer.setErrorCodeBea(userObject2.getString("errorCode"));

			} else if ((userObject.has("errorHandler"))) {

				/* Servidor do JDE (Weblogic) fora do ar */

				String errorHandler = userObject.getString("errorHandler");
				JSONObject userObject1 = new JSONObject(errorHandler);
				customer.setErrorCodeBea(userObject1.getString("errorCode"));
			}

		} catch (JSONException e) {
			e.printStackTrace();
		}

		return customer;
	}
}