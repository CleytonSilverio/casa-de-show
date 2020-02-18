package com.gft.main.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gft.main.entidade.Ingressos;

@Repository
public interface IngressosRepository extends JpaRepository<Ingressos, Long>{

}
