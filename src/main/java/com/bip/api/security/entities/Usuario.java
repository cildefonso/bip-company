package com.bip.api.security.entities;

import java.io.Serializable;
import java.util.Date;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;

import com.bip.api.security.enums.PerfilEnum;


@Document(collection = "users")
public class Usuario implements Serializable {

	private static final long serialVersionUID = 306411570471828345L;
    
	@org.springframework.data.annotation.Id
	private ObjectId _id;
	private boolean isActivated; 
	private String fullname;
	private String email;
	private String hashedPassword;
	private com.bip.api.enums.PerfilEnum userTypeAccess;

	private Date dataAtualizacao;

	public Usuario() {
	}

	//@GeneratedValue(strategy = GenerationType.AUTO)
	public ObjectId get_id() {
		return _id;
	}

	public void set_id(ObjectId _id) {
		this._id = _id;
	}
	
	//@Column(name = "email", nullable = false)
	public String getEmail() {
		return email;
	}

	public boolean isActivated() {
		return isActivated;
	}

	public void setActivated(boolean isActivated) {
		this.isActivated = isActivated;
	}

	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	//@Enumerated(EnumType.STRING)
	//@Column(name = "profile", nullable = false)
	

	//@Column(name = "password", nullable = false)
	public String getHashedPassword() {
		return hashedPassword;
	}
	
	//@Enumerated(EnumType.STRING)
	public com.bip.api.enums.PerfilEnum getUserTypeAccess() {
		return userTypeAccess;
	}

	public void setUserTypeAccess(com.bip.api.enums.PerfilEnum roleUsuario) {
		this.userTypeAccess = roleUsuario;
	}

	public void setHashedPassword(String hashedPassword) {
		this.hashedPassword = hashedPassword;
	}

	
	public Date getDataAtualizacao() {
		return dataAtualizacao;
	}

	public void setDataAtualizacao(Date dataAtualizacao) {
		this.dataAtualizacao = dataAtualizacao;
	}

	@Override
	public String toString() {
		return "Usuario [_id=" + _id + ", isActivated=" + isActivated + ", fullname=" + fullname + ", email=" + email
				+ ", hashedPassword=" + hashedPassword + ", userTypeAccess=" + userTypeAccess + ", dataAtualizacao="
				+ dataAtualizacao + "]";
	}



	
		
}
