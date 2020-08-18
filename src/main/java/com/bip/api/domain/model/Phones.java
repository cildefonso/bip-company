package com.bip.api.domain.model;

import java.io.Serializable;
import java.util.Objects;


import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "companies")
public class Phones implements Serializable {
		
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String main;
	private String secundary;
	 	
	
	public Phones() {
	}


	@NotEmpty(message = "É obrigatório o preenchimento do telefone.")
	public String getMain() {
		return main;
	}

	public void setMain(String main) {
		this.main = main;
	}

	
	public String getSecundary() {
		return secundary;
	}

	public void setSecundary(String secundary) {
		this.secundary = secundary;
	}


    @Override
    public int hashCode() {
        return Objects.hash(main,secundary);
    }


	@Override
	public String toString() {
		return "Phones [main='%s', secondary='%s']";
	}
    	

}
