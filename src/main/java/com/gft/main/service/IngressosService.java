package com.gft.main.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gft.main.entidade.Ingressos;
import com.gft.main.exception.RecordNotFoundException;
import com.gft.main.repository.IngressosRepository;

@Service
public class IngressosService {
	
	@Autowired
	IngressosRepository repositorio;
	
	public List<Ingressos> findAll() {
		return repositorio.findAll();
	}
	
	public Ingressos acharPorId(Long id) throws RecordNotFoundException {
		Optional<Ingressos> ingressos = repositorio.findById(id);
		
		if(ingressos.isPresent()) {
			return ingressos.get();
		} else {
			throw new RecordNotFoundException("Nenhum ingresso vendido.");
		}
	}
	
	public Ingressos criarAtualizarIngressos(Ingressos entidade) {
		if(entidade.getId() == null) {
			entidade = repositorio.save(entidade);
			
			return entidade;
		} 
		else {
			Optional<Ingressos> ingressos = repositorio.findById(entidade.getId());
			
			if(ingressos.isPresent()) {
				Ingressos novaEntidade = ingressos.get();
				novaEntidade.setComprador(entidade.getComprador());
				novaEntidade.setEvento(entidade.getEvento());

				novaEntidade = repositorio.save(novaEntidade);
				
				return novaEntidade;
			} else {
				entidade = repositorio.save(entidade);
				
				return entidade;
			}
		}
	}
	
	public void apagarCasa(Long id) throws RecordNotFoundException {
		Optional<Ingressos> ingressos = repositorio.findById(id);
		
		if(ingressos.isPresent()) 
		{
			repositorio.deleteById(id);
		} else {
			throw new RecordNotFoundException("Nenhum ingresso encontrado!");
		}
	}

}
