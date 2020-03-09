package com.gft.main.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.gft.main.entidade.Usuario;
import com.gft.main.repository.UserRepository;

@Controller
public class UsuarioController {

	@Autowired
	UserRepository service;

	@GetMapping("/cadastro")
	public ModelAndView user(Usuario usuario) {
		ModelAndView mv = new ModelAndView("cadastro");
		mv.addObject("usuario", usuario);
		return mv;
	}

	@PostMapping("/cadastro")
	public ModelAndView novoUsuario(@Valid Usuario usuario) {

		ModelAndView mv = new ModelAndView("shows");
		

		System.out.println(usuario.getNome() + "<<<<<<<<<<<<<<<<<<<<<");
		
		if(!usuario.getNome().equals("administrador")) {
			System.out.println("Setou como usuario");
			usuario.setRole("USUARIO");
		}
		if(usuario.getNome().equals("administrador")) {
			System.out.println("Setou como gerente");
			usuario.setRole("GERENTE");
		}

		service.save(usuario);

		return mv;

	}

}
