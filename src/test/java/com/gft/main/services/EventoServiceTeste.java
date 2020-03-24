package com.gft.main.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.gft.main.entidade.Casa;
import com.gft.main.entidade.Show;
import com.gft.main.repository.CasaRepository;
import com.gft.main.repository.ShowRepository;


@RunWith(SpringRunner.class)
@SpringBootTest
public class EventoServiceTeste {
	
	@Autowired
	ShowRepository repositorio;
	
	@Autowired
	CasaRepository casarepo;
	Date data = new Date();
	
	@Test
	public void deveriaCadastrarUmEvento() {
		Optional<Casa> casa = casarepo.findById((long) 17);
		Show s = new Show();
		s.setShows("Um show");
		s.setEstilo("rock");
		s.setIngRestante(200);
		s.setData(data);
		s.setLocal("cwb");
		s.setCasa(casa.get());
		
		try {
			repositorio.save(s);
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail();
		}
	}
	
	@Test
	public void deveriaListarOsEventos() {

		try {
			List<Show> shows = repositorio.findAll();
			for (Show a : shows) {
				System.out.println("Evento: " + a.getShows());
			}
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail();
		}
	}
	
	@Test
	public void deveriaListarUmEventoPorId() {
		Long busca = (long) 30;
		try {
			System.out.println("Evento: " + repositorio.findById(busca).get().getShows());
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail();
		}
	}

}
