package br.com.bitwaysystem.bean;

import java.util.List;

public class ShowCustomerCreditInformation {
	
	private double amountCreditLimit;
	private Double amountTotalExposure;
	private boolean creditHoldExempt;
	private List e1MessageList;
	private Entity entity;
	
	public ShowCustomerCreditInformation() {
		super();
	}

	public ShowCustomerCreditInformation(Entity entity) {
		super();
		this.entity = entity;
	}

	public ShowCustomerCreditInformation(double amountCreditLimit,
			Double amountTotalExposure, boolean creditHoldExempt,
			List e1MessageList, Entity entity) {
		super();
		this.amountCreditLimit = amountCreditLimit;
		this.amountTotalExposure = amountTotalExposure;
		this.creditHoldExempt = creditHoldExempt;
		this.e1MessageList = e1MessageList;
		this.entity = entity;
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

	public List getE1MessageList() {
		return e1MessageList;
	}

	public void setE1MessageList(List e1MessageList) {
		this.e1MessageList = e1MessageList;
	}

	public Entity getEntity() {
		return entity;
	}

	public void setEntity(Entity entity) {
		this.entity = entity;
	}

}