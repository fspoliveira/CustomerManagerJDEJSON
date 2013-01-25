package br.com.bitwaysystem.json;

import org.json.JSONException;
import org.json.JSONObject;
import br.com.bitwaysystem.bean.Entity;
import br.com.bitwaysystem.bean.ShowCustomerCreditInformation;

public class JSONtoJava {

	public static ShowCustomerCreditInformation showCredit(String strJson) {

		ShowCustomerCreditInformation customer = new ShowCustomerCreditInformation();

		try {
			JSONObject userObject = new JSONObject(strJson);
			Entity entity = new Entity();

			String getCustomerCreditInformationResponse = userObject
					.getString("getCustomerCreditInformationResponse");

			JSONObject userObject1 = new JSONObject(
					getCustomerCreditInformationResponse);

			customer.setAmountCreditLimit(userObject1
					.getDouble("amountCreditLimit"));

			JSONObject entityJSON = (JSONObject) userObject1.get("entity");

			/* Recupera CNPJ ou CPF */
			
			entity.setEntityTaxId(entityJSON.getString("entityTaxId"));	
			
			customer.setEntity(entity);

		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return customer;
	}
}