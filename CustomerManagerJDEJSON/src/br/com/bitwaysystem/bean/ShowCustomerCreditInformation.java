package br.com.bitwaysystem.bean;

public class ShowCustomerCreditInformation {

	private boolean creditHoldExempt;
	private Double amountTotalExposure;
	private double amountCreditLimit;
	Entity entity;

	public ShowCustomerCreditInformation() {
		super();
	}

	public ShowCustomerCreditInformation(Entity entity) {
		super();
		this.entity = entity;
	}

	public ShowCustomerCreditInformation(boolean creditHoldExempt,
			Double amountTotalExposure, double amountCreditLimit, Entity entity) {
		super();
		this.creditHoldExempt = creditHoldExempt;
		this.amountTotalExposure = amountTotalExposure;
		this.amountCreditLimit = amountCreditLimit;
		this.entity = entity;
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

	public double getAmountCreditLimit() {
		return amountCreditLimit;
	}

	public void setAmountCreditLimit(double amountCreditLimit) {
		this.amountCreditLimit = amountCreditLimit;
	}

	public Entity getEntity() {
		return entity;
	}

	public void setEntity(Entity entity) {
		this.entity = entity;
	}

}