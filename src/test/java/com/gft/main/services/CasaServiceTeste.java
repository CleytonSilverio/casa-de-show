package com.gft.main.services;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.gft.main.entidade.Casa;
import com.gft.main.repository.CasaRepository;
import com.gft.main.service.CasaService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CasaServiceTeste {

	@Autowired
	CasaRepository repositorio;
	
	@Mock
	CasaService service;

	@Test
	public void deveriaCadastrarUmaCasa() {

		Casa a = new Casa();
		a.setIdCasa((Long) null);
		a.setNome("Pesada");
		a.setEndereco("true");

		try {
			repositorio.save(a);
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail();
		}
	}

	@Test
	public void deveriaListarAsCasas() {

		try {
			List<Casa> casas = repositorio.findAll();
			for (Casa a : casas) {
				System.out.println("Casa: " + a.getNome());
			}
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail();
		}
	}

	@Test
	public void deveriaListarUmaCasaPorId() {
		Long busca = (long) 17;
		try {
			System.out.println("Casa: " + repositorio.findById(busca).get().getNome());
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail();
		}
	}
	
	@Test
	public void deveriaListarPorIdViaService() {
		try {
			Long busca =  (long) 17;
			Mockito.when(service.acharPorId(busca));
		}catch (Exception e) {
			e.printStackTrace();
			Assert.fail();
		}
	}
	
	@Test
	public void esperoErroBuscaCasaService() throws Exception {
		Long busca =  (long) 500;
		Mockito.when(service.acharPorId(busca)).thenThrow(new Exception("Não existe esta casa"));
	}

}
