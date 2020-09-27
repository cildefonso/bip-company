package com.bip.api.domain.model;

import java.io.Serializable;
import java.util.Objects;


import org.bson.types.ObjectId;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "companies")
public class CompanyMongoDB implements Serializable {
	
	
	private static final long serialVersionUID = -4823329335813040815L;
	@org.springframework.data.annotation.Id
	private ObjectId _id;
	private String fullnamecompany;
	private String email;
	private String cnpj;
	private String idaddress;
	private Integer numberAddress;
	private String complementAddress;
	private Phones phones;
	private String userId;

		 	
	
	public CompanyMongoDB() {
	}
	

	public ObjectId get_Id() {
		return _id;
	}


	public void set_Id(ObjectId _id) {
		this._id = _id;
	}

	@NotEmpty(message = "É obrigatório o preenchimento do id do usuário.")
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	@NotEmpty(message = "É obrigatório o preenchimento do nome de usuário.")
	public String getFullnamecompany() {
		return fullnamecompany;
	}

	public void setFullnamecompany(String fullnamecompany) {
		this.fullnamecompany = fullnamecompany;
	}
	
	@NotEmpty(message = "É obrigatório o preenchimento do e-mail.")
	@Email(message = "Email inválido.")
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

	@NotEmpty(message = "É obrigatório o preenchimento do número do endereço.")
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
	
	@NotEmpty(message = "É obrigatório o preenchimento do id do endereço.")
	public String getIdaddress() {
		return idaddress;
	}

	public Phones getPhones() {
		return phones;
	}

	public void setPhones(Phones phones) {
		this.phones = phones;
	}

	public void setIdaddress(String idaddress) {
		this.idaddress = idaddress;
	}


    @Override
    public int hashCode() {
        return Objects.hash(_id,fullnamecompany,email,
        		cnpj,numberAddress,complementAddress,idaddress);
    }

	
	@Override
	  public String toString() {
	    return String.format(
	        "Company[_id='%s',fullnamecompany='%s',email='%s',cnpj='%s',idaddress='%s',numberAddress='%s',complementAddress='%s', userId='%s']",
	                  _id,fullnamecompany,email,cnpj,idaddress,numberAddress,complementAddress,userId);
	  }
 	
}
