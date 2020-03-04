package com.gft.main.dto;

import java.util.List;
import java.util.stream.Collectors;

import com.gft.main.entidade.Usuario;
import com.gft.main.entidade.Vendas;

public class VendaDto {
	
	private Long id;
	private Long usuarioId;
	private Long eventoId;
	
	public VendaDto(Vendas venda) {
		super();
		this.id = venda.getId();
		this.usuarioId = venda.getUsuarioId();
		this.eventoId = venda.getEventoId();
	}
	
	public static List<VendaDto> converter(List<Vendas> venda) {
		return venda.stream().map(VendaDto::new).collect(Collectors.toList());
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getUsuarioId() {
		return usuarioId;
	}
	public void setUsuarioId(Long usuarioId) {
		this.usuarioId = usuarioId;
	}
	public Long getEventoId() {
		return eventoId;
	}
	public void setEventoId(Long eventoId) {
		this.eventoId = eventoId;
	}
	
	

}
