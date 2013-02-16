package br.com.bitwaysystem.json;

import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;
import br.com.bitwaysystem.bean.Entity;
import br.com.bitwaysystem.bean.ShowCustomerCreditInformation;

public class JSONtoJava {

	public static ShowCustomerCreditInformation showCredit(String strJson) {

		Entity entity = new Entity("", "", 0);
		ShowCustomerCreditInformation customer = new ShowCustomerCreditInformation(
				0.0, 0.0, true, "", entity, "");

		try {
			
			JSONObject userObject = new JSONObject(strJson);

			/* Tratamento de erro */
			String xmlFragment = null;	
		    String helloResponse = null;		    

			if (userObject.has("xml-fragment")) {

				xmlFragment = userObject.getString("xml-fragment");
				
				JSONObject userObject1 = new JSONObject(
						xmlFragment);
				
				helloResponse = userObject1.getString("ns0:errorHandler");
				
				JSONObject userObject2 = new JSONObject(
						helloResponse);
				
				customer.setErrorCodeBea(userObject2.getString("errorCode"));

			} else {
				String getCustomerCreditInformationResponse = userObject
						.getString("getCustomerCreditInformationResponse");

				JSONObject userObject1 = new JSONObject(
						getCustomerCreditInformationResponse);

				customer.setAmountCreditLimit(userObject1
						.getDouble("amountCreditLimit"));

				customer.setAmountTotalExposure(userObject1
						.getDouble("amountTotalExposure"));

				JSONObject entityJSON = (JSONObject) userObject1.get("entity");

				/* Recupera CNPJ ou CPF */

				entity.setEntityTaxId(entityJSON.getString("entityTaxId"));

				customer.setEntity(entity);
			}

		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return customer;
	}
}