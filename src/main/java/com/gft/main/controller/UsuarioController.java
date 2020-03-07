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
		
		if(service.findAll() == null ) {
			usuario.setRole("GERENTE");
		}
		else {
			usuario.setRole("USUARIO");
		}
		
		service.save(usuario);

		return mv;

	}

}
