package com.bip.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.bip.api.enums.PerfilEnum;
import com.bip.api.security.entities.Usuario;
import com.bip.api.security.repositories.UsuarioRepository;
import com.bip.api.utils.SenhaUtils;

@SpringBootApplication
public class BipCompanyApplication {
	
	//@Value("${paginacao.qtd_por_pagina}")
	//private int intQtdPorPagina;
	@Autowired
	//private UsuarioRepository usuarioRepository;

	public static void main(String[] args) {
		SpringApplication.run(BipCompanyApplication.class, args);
	}
	
	@Bean
	public CommandLineRunner commandLineRunner() {
		return args -> {
			System.out.println("5 - Apresentação Deleon Mongo CommandLineRunner");
			
			/*Usuario usuario = new Usuario();
			usuario.setEmail("usuario@email.com");
			usuario.setUserTypeAccess(PerfilEnum.ROLE_USUARIO);
			usuario.setHashedPassword(SenhaUtils.gerarBCrypt("123456"));
			this.usuarioRepository.save(usuario);
			
			Usuario admin = new Usuario();
			admin.setEmail("cildefonso@gmail.com");
			admin.setUserTypeAccess(PerfilEnum.ROLE_ADMIN);
			admin.setHashedPassword(SenhaUtils.gerarBCrypt("123456"));
			System.out.println("Senha :" + admin.getHashedPassword());
			this.usuarioRepository.save(admin);*/
			
		};
	}
}
