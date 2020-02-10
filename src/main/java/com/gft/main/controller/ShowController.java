package com.gft.main.controller;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.gft.main.entidade.Show;
import com.gft.main.exception.RecordNotFoundException;
import com.gft.main.service.ShowService;

@Controller
public class ShowController {

	@Autowired
	private ShowService service;

	@GetMapping("/")
	public ModelAndView findAll() {

		ModelAndView mv = new ModelAndView("shows");
		mv.addObject("shows", service.findAll());

		return mv;
	}

	@GetMapping("/adicionar")
	public ModelAndView add(Show show) {

		ModelAndView mv = new ModelAndView("/addshow");
		mv.addObject("shows", show);

		return mv;
	}

	@PostMapping("/save")
	public ModelAndView save(@Valid Show show, BindingResult result) {

		if (result.hasErrors()) {
			return add(show);
		}

		service.criarAtualizarShow(show);

		return findAll();
	}
	
	@RequestMapping(path = {"/edit", "/edit/{id}"})
    public String editarPorId(Model model, @PathVariable("id") Optional<Long> id) throws RecordNotFoundException {
        if (id.isPresent()) {
            Show entity = service.acharPorId(id.get());
            model.addAttribute("show", entity);
        } else {
            model.addAttribute("show", new Show());
        }
        return "addshow";
    }
	
	@RequestMapping(path = "/delete/{id}")
    public String deleteEmployeeById(Model model, @PathVariable("id") Long id) throws RecordNotFoundException {
        service.apagarShow(id);
        return "redirect:/";
    }
     

}
