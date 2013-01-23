package br.com.bitwaysystem.json;

import org.json.JSONException;
import org.json.JSONObject;
import br.com.bitwaysystem.bean.ShowCustomerCreditInformation;

public class JSONtoJava {

	public static ShowCustomerCreditInformation showCredit(String strJson) {
		
		ShowCustomerCreditInformation customer = new ShowCustomerCreditInformation();
		
		try {
			JSONObject userObject = new JSONObject(strJson);
			
			String getCustomerCreditInformationResponse = userObject
			.getString("getCustomerCreditInformationResponse");
			
			JSONObject userObject1 = new JSONObject(
					getCustomerCreditInformationResponse);
			
			customer.setAmountCreditLimit(userObject1.getDouble("amountCreditLimit"));
			
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return customer;
	}
}