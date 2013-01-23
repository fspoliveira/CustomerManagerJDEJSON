package br.com.bitwaysystem.json;

import br.com.bitwaysystem.bean.ShowCustomerCreditInformation;

import com.google.gson.Gson;

public class JSONtoJava {

	public ShowCustomerCreditInformation showCredit(String json) {
		Gson gson = new Gson();

		ShowCustomerCreditInformation customer = gson.fromJson(json,
				ShowCustomerCreditInformation.class);
		return customer;
	}
}