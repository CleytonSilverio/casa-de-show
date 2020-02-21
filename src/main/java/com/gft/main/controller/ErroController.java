package com.gft.main.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.gft.main.exception.RecordNotFoundException;

@Controller
public class ErroController {

	@RequestMapping(path = "/acessonegado")
    public ModelAndView deleteShowById() throws RecordNotFoundException {
       
		ModelAndView mv = new ModelAndView("/pagerro");
		
        return mv;
    }
	
}
