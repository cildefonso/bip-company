package com.bip.api.dtos;

public class CompanyDto {

	 private String fullnamecompany;
	 private String email;
	 private String cnpj;
	 private String idaddress;
	 private Integer numberAddress;
	 private String complementAddress;
     private PhonesDto phones;
	 private String userid;
	
	
	public String getFullnamecompany() {
		return fullnamecompany;
	}
	public void setFullnamecompany(String fullnamecompany) {
		this.fullnamecompany = fullnamecompany;
	}
	

	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getCnpj() {
		return cnpj;
	}
	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}
	
	public String getIdaddress() {
		return idaddress;
	}
	public void setIdaddress(String idaddress) {
		this.idaddress = idaddress;
	}
	
	
	public Integer getNumberAddress() {
		return numberAddress;
	}
	public void setNumberAddress(Integer numberAddress) {
		this.numberAddress = numberAddress;
	}
	
	public String getComplementAddress() {
		return complementAddress;
	}
	public void setComplementAddress(String complementAddress) {
		this.complementAddress = complementAddress;
	}
	
	public PhonesDto getPhones() {
		return phones;
	}
	
	public void setPhones(PhonesDto phones) {
		this.phones = phones;
	}
	
	
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}

	 
}
