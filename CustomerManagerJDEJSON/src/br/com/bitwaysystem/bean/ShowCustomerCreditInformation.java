package br.com.bitwaysystem.bean;

/**
 * ShowCustomerCreditInformation é a classe Bean que armazena as informações do
 * cliente tais como: limite de crédito, O Total de pedidos em aberto, a
 * mensagem de erro caso não seja possíve fazer a consulta (ou o servidor
 * SOA-SUITE não está no ar ou o Web Logic do ERP JDE e o endereço HTTP
 * 
 * @author Fernando Santiago
 * @version %I%, %G%
 * @since 1.0
 * */
public class ShowCustomerCreditInformation {

	private double amountCreditLimit;
	private Double amountTotalExposure;
	private boolean creditHoldExempt;
	private String e1MessageList;
	private Entity entity;
	private String errorCodeBea;
	private String uri;

	public String getErrorCodeBea() {
		return errorCodeBea;
	}

	public void setErrorCodeBea(String errorCodeBea) {
		this.errorCodeBea = errorCodeBea;
	}

	public ShowCustomerCreditInformation() {
		super();
	}

	public ShowCustomerCreditInformation(Entity entity) {
		super();
		this.entity = entity;
	}

	public ShowCustomerCreditInformation(double amountCreditLimit,
			Double amountTotalExposure, boolean creditHoldExempt,
			String e1MessageList, Entity entity, String errorCodeBea, String uri) {
		super();
		this.amountCreditLimit = amountCreditLimit;
		this.amountTotalExposure = amountTotalExposure;
		this.creditHoldExempt = creditHoldExempt;
		this.e1MessageList = e1MessageList;
		this.entity = entity;
		this.errorCodeBea = errorCodeBea;
		this.uri = uri;
	}

	public String getE1MessageList() {
		return e1MessageList;
	}

	public void setE1MessageList(String e1MessageList) {
		this.e1MessageList = e1MessageList;
	}

	public double getAmountCreditLimit() {
		return amountCreditLimit;
	}

	public void setAmountCreditLimit(double amountCreditLimit) {
		this.amountCreditLimit = amountCreditLimit;
	}

	public Double getAmountTotalExposure() {
		return amountTotalExposure;
	}

	public void setAmountTotalExposure(Double amountTotalExposure) {
		this.amountTotalExposure = amountTotalExposure;
	}

	public boolean isCreditHoldExempt() {
		return creditHoldExempt;
	}

	public void setCreditHoldExempt(boolean creditHoldExempt) {
		this.creditHoldExempt = creditHoldExempt;
	}

	public Entity getEntity() {
		return entity;
	}

	public void setEntity(Entity entity) {
		this.entity = entity;
	}

	public String getUri() {
		return uri;
	}

	public void setUri(String uri) {
		this.uri = uri;
	}
}