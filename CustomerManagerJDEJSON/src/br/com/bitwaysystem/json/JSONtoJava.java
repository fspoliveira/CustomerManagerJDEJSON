package br.com.bitwaysystem.json;

import br.com.bitwaysystem.bean.ShowCustomerCreditInformation;

import com.google.gson.Gson;

public class JSONtoJava {

	public static ShowCustomerCreditInformation showCredit(String strJson) {
		Gson gson = new Gson();

		ShowCustomerCreditInformation customer = gson.fromJson(strJson,
				ShowCustomerCreditInformation.class);
		return customer;
	}
}