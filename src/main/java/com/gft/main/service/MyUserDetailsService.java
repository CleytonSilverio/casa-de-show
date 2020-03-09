package com.gft.main.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.gft.main.entidade.Usuario;
import com.gft.main.repository.UserRepository;
import com.gft.main.security.UserPrincipal;

@Service
public class MyUserDetailsService implements UserDetailsService {

	@Autowired
	private UserRepository repo;
	
	@Override
	public UserDetails loadUserByUsername(String nome) throws UsernameNotFoundException {
		
		Usuario user = repo.findByNome(nome);
		if(user==null) {
			throw new UsernameNotFoundException("Usuário inexistente");
		}
		
		return new UserPrincipal(user);
	}

}
