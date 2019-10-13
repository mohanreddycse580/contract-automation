package com.klm.contract.pojo;

public class Contract {

	private String contractId;
	private String contractEndDate;
	private String contractOwner;
	private String contractOwnerEmail;

	public Contract(String contractId, String contractEndDate, String contractOwner, String contractOwnerEmail) {
		super();
		this.contractId = contractId;
		this.contractEndDate = contractEndDate;
		this.contractOwner = contractOwner;
		this.contractOwnerEmail = contractOwnerEmail;
	}

	public Contract() {
		// TODO Auto-generated constructor stub
	}

	public String getContractId() {
		return contractId;
	}

	public void setContractId(String contractId) {
		this.contractId = contractId;
	}

	public String getContractEndDate() {
		return contractEndDate;
	}

	public void setContractEndDate(String contractEndDate) {
		this.contractEndDate = contractEndDate;
	}

	public String getContractOwner() {
		return contractOwner;
	}

	public void setContractOwner(String contractOwner) {
		this.contractOwner = contractOwner;
	}

	public String getContractOwnerEmail() {
		return contractOwnerEmail;
	}

	public void setContractOwnerEmail(String contractOwnerEmail) {
		this.contractOwnerEmail = contractOwnerEmail;
	}

	@Override
	public String toString() {
		return "Contract [contractId=" + contractId + ", contractEndDate=" + contractEndDate + ", contractOwner="
				+ contractOwner + ", contractOwnerEmail=" + contractOwnerEmail + "]";
	}

}
