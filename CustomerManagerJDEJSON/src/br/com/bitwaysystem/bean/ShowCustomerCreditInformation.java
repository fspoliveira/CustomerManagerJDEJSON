package br.com.bitwaysystem.bean;

public class ShowCustomerCreditInformation {
	
	private double amountCreditLimit;
	private Double amountTotalExposure;
	private boolean creditHoldExempt;
	private String e1MessageList;
	private Entity entity;
	

	public ShowCustomerCreditInformation() {
		super();
	}

	public ShowCustomerCreditInformation(Entity entity) {
		super();
		this.entity = entity;
	}

	public ShowCustomerCreditInformation(String e1MessageList,
			boolean creditHoldExempt, Double amountTotalExposure,
			Entity entity, double amountCreditLimit) {
		super();
		this.e1MessageList = e1MessageList;
		this.creditHoldExempt = creditHoldExempt;
		this.amountTotalExposure = amountTotalExposure;
		this.entity = entity;
		this.amountCreditLimit = amountCreditLimit;
	}

	public String getE1MessageList() {
		return e1MessageList;
	}

	public void setE1MessageList(String e1MessageList) {
		this.e1MessageList = e1MessageList;
	}

	public boolean isCreditHoldExempt() {
		return creditHoldExempt;
	}

	public void setCreditHoldExempt(boolean creditHoldExempt) {
		this.creditHoldExempt = creditHoldExempt;
	}

	public Double getAmountTotalExposure() {
		return amountTotalExposure;
	}

	public void setAmountTotalExposure(Double amountTotalExposure) {
		this.amountTotalExposure = amountTotalExposure;
	}

	public Entity getEntity() {
		return entity;
	}

	public void setEntity(Entity entity) {
		this.entity = entity;
	}

	public double getAmountCreditLimit() {
		return amountCreditLimit;
	}

	public void setAmountCreditLimit(double amountCreditLimit) {
		this.amountCreditLimit = amountCreditLimit;
	}

}