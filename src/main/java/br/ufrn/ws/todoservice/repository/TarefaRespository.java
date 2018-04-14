package br.ufrn.ws.todoservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.ufrn.ws.todoservice.dominio.Tarefa;

public interface TarefaRespository extends JpaRepository<Tarefa, Long> {

	List<Tarefa> findAll();
	
	

}
