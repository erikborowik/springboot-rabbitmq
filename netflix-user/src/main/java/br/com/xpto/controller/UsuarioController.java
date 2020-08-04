package br.com.xpto.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.xpto.data.Usuario;
import br.com.xpto.service.UsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import javassist.NotFoundException;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

	@Autowired
	private UsuarioService service;

	@PostMapping() 
	@Operation(summary = "Novo Usuário", description = "Adiciona usuário",
	responses = {
			@ApiResponse(responseCode = "200", description = "Sucesso", content =
					@Content(mediaType = "application/json")),
			@ApiResponse(responseCode = "500", description = "Internal Server Error", content =
			@Content(mediaType = "application/json"))
	})
	public @ResponseBody Usuario criarUsuario (@RequestBody Usuario user) {
		return service.save(user);
	}

	@GetMapping()
	@Operation(summary = "Usuários", description = "Este serviço recupera uma lista de usuários",
	responses = {
			@ApiResponse(responseCode = "200", description = "Usuários localizados", content =
					@Content(mediaType = "application/json", schema = @Schema(implementation = Usuario.class))),
			@ApiResponse(responseCode = "404", description = "Nenhuma usuário", content =
			@Content(mediaType = "application/json")),
			@ApiResponse(responseCode = "500", description = "Internal Server Error", content =
			@Content(mediaType = "application/json"))
	})
	public @ResponseBody Iterable<Usuario> listarTodosUsuarios() {
		return service.findAll();
	}
	
	@Operation(summary = "Usuário", description = "Este serviço busca usuário pelo ID",
	responses = {
			@ApiResponse(responseCode = "200", description = "Usuários localizados", content =
					@Content(mediaType = "application/json", schema = @Schema(implementation = Usuario.class))),
			@ApiResponse(responseCode = "404", description = "Nenhuma usuário", content =
			@Content(mediaType = "application/json")),
			@ApiResponse(responseCode = "500", description = "Internal Server Error", content =
			@Content(mediaType = "application/json"))
	})
	@GetMapping(path = "/{id}")
	public @ResponseBody Usuario buscarUsarioPeloID(@PathVariable(value = "id") Integer id) throws NotFoundException {
		return service.findById(id);
	}

}
