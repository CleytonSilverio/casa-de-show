package com.gft.main.dto;

import java.util.List;
import java.util.stream.Collectors;

import com.gft.main.entidade.Usuario;

public class UsuarioDto {

	private int id;
	private String nome;
	private String email;

	public UsuarioDto(Usuario usuario) {
		super();
		this.id = usuario.getId();
		this.nome = usuario.getNome();
		this.email = usuario.getEmail();
	}

	public static List<UsuarioDto> converter(List<Usuario> usuario) {

		return usuario.stream().map(UsuarioDto::new).collect(Collectors.toList());
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
