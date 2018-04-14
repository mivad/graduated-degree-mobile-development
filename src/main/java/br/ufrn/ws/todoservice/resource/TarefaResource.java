package br.ufrn.ws.todoservice.resource;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Sort;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;


import br.ufrn.ws.todoservice.dominio.Tarefa;
import br.ufrn.ws.todoservice.repository.TarefaRespository;


import java.util.List;

import javax.validation.Valid;

@RestController
@RequestMapping("/Tarefas")
public class TarefaResource {
	
	
	private int page = 2;
	private int pageSize = 2;
	@Autowired
	private TarefaRespository tarefaRespository;
	
	@GetMapping
	public List<Tarefa> getTarefas(@RequestParam(required=false, defaultValue="descricao") String sort)
	{
		return tarefaRespository.findAll(new org.springframework.data.domain.Sort(Direction.ASC, sort));
	}
	
	@GetMapping("/limit")
	public List<Tarefa> getTarefas(@RequestParam(required=false, defaultValue="0") int page,
			@RequestHeader("Authorization") String token){
		Page<Tarefa> objs = tarefaRespository.findAll(PageRequest.of(page, pageSize));
		return objs.getContent();
	}
	
	@PostMapping
	@ResponseStatus(code=HttpStatus.CREATED)
	public Tarefa save(@Valid @RequestBody Tarefa tarefa){
		return tarefaRespository.save(tarefa);
	}

	@GetMapping("/{id}")
	public Tarefa getByid(@PathVariable Long id)
	{
		//return tarefaRespository.getOne(id);
		return tarefaRespository.findById(id).get();
	}
}
