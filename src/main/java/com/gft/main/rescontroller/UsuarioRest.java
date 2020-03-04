package com.gft.main.rescontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gft.main.dto.UsuarioDto;
import com.gft.main.repository.UserRepository;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/usuariocontroller")
public class UsuarioRest {
	
	@Autowired
	UserRepository repositorio;
	
	@ApiOperation(value = "Retorna uma lista de usuarios")
	@ApiResponses(value = {
	    @ApiResponse(code = 200, message = "Retorna a lista de eventos"),
	    @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
	    @ApiResponse(code = 404, message = "Não encontrado"),
	    @ApiResponse(code = 500, message = "Foi gerada uma exceção"),
	})
	@GetMapping
	public ResponseEntity<List<UsuarioDto>> getUsuarios() {
		List<UsuarioDto> dto = UsuarioDto.converter(repositorio.findAll());
		return ResponseEntity.ok(dto);
	}

}
