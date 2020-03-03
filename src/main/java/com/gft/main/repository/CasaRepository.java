package com.gft.main.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.gft.main.entidade.Casa;

@Repository
public interface CasaRepository extends JpaRepository<Casa, Long>{
	
}
