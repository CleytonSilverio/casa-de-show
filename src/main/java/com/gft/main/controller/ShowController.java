package com.gft.main.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Role;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.gft.main.entidade.Casa;
import com.gft.main.entidade.Ingressos;
import com.gft.main.entidade.Show;
import com.gft.main.exception.RecordNotFoundException;
import com.gft.main.repository.ShowRepository;
import com.gft.main.service.CasaService;
import com.gft.main.service.IngressosService;
import com.gft.main.service.ShowService;

@Controller
public class ShowController {

	@Autowired
	private ShowService service;
	
	@Autowired
	private CasaService home;
	
	@Autowired
	private ShowRepository repositorio;
	
	@Autowired
	private IngressosService ingressos;
	
	@GetMapping("/")
	public ModelAndView findAll() {

		ModelAndView mv = new ModelAndView("shows");
		mv.addObject("shows", service.findAll());

		return mv;
	}

	
	@GetMapping("/adicionar")
	@PreAuthorize("hasRole('GERENTE')")
	public ModelAndView addShow(Show show, BindingResult result) {
		
		ModelAndView mv = new ModelAndView("/addshow");
		if(result.hasErrors()) {
			mv.addObject(result);
		}else {
			System.out.println("Deu merda aqui");
		}
		mv.addObject("shows", show);
		mv.addObject("listar", service.findAll());

		return mv;
	}
	
	@ModelAttribute(value="casinha")
	public List<Casa> buscarCasas() {

		return home.findAll();
	}

	
	@PostMapping("/saveshow")
	@PreAuthorize("hasRole('GERENTE')")
	public ModelAndView saveShow(@Valid Show shows, BindingResult result, Casa casa) {
		
		if (result.hasErrors()) {
			return addShow(shows, result);
		}
		
		repositorio.save(shows);
		
		return findAll();
	}
	
	
	@RequestMapping(path = {"/edit", "/edit/{id}"})
	@PreAuthorize("hasRole('GERENTE')")
    public String editarPorId(Model model, @PathVariable("id") Optional<Long> id) throws RecordNotFoundException {
        if (id.isPresent()) {
            Show entity = service.acharPorId(id.get());
            model.addAttribute("shows", entity);
        } else {
            model.addAttribute("shows", new Show());
        }
        return "addshow";
    }
	
	
	@RequestMapping(path = "/delete/{id}")
	@PreAuthorize("hasRole('GERENTE')")
    public String deleteShowById(Model model, @PathVariable("id") Long id) throws RecordNotFoundException {
        service.apagarShow(id);
        return "redirect:/";
    }
	
	@RequestMapping(path = {"/comprar", "/comprarteste/{id}"})
	public String comprar(Model model, @PathVariable("id") Optional<Long> id) throws RecordNotFoundException {
		if (id.isPresent()) {
            Show entity = service.acharPorId(id.get());
            entity.setIngRestante(entity.getIngRestante() - entity.getCompra());
            model.addAttribute("shows", entity);
        } else {
            model.addAttribute("shows", new Show());
        }
		
		return "comprar";
	}
	
	
	@PostMapping("/comprar")
	public String comprar(Long id, int compra, Ingressos ingressos) {
		
		Show show = repositorio.findById(id).get(); 
		
		show.setIngRestante(show.getIngRestante() - compra);
		
		repositorio.save(show);
		
			
		return "redirect:/";
	}
     

}