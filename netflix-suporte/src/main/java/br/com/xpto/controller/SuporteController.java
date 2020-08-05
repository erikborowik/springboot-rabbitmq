package br.com.xpto.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.xpto.data.UsuarioSuporteNotaTicket;
import br.com.xpto.data.UsuarioTicket;
import br.com.xpto.service.ChamadoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import javassist.NotFoundException;

@RestController
@RequestMapping("/chamado")
public class SuporteController {
	
	@Autowired
	private ChamadoService service;
	
	@GetMapping()
	@Operation(summary = "Chamados", description = "Este serviço recupera uma lista chamados",
	responses = {
			@ApiResponse(responseCode = "200", description = "Usuários localizados", content =
					@Content(mediaType = "application/json", schema = @Schema(implementation = UsuarioTicket.class))),
			@ApiResponse(responseCode = "404", description = "Nenhum chamado", content =
			@Content(mediaType = "application/json")),
			@ApiResponse(responseCode = "500", description = "Internal Server Error", content =
			@Content(mediaType = "application/json"))
	})
	public @ResponseBody Iterable<UsuarioTicket> listarTodosTickets() {
		return service.findAll();
	}
	
	
	@GetMapping("/{id}")
	@Operation(summary = "Chamados", description = "Este serviço recupera uma lista chamados",
	responses = {
			@ApiResponse(responseCode = "200", description = "Usuários localizados", content =
					@Content(mediaType = "application/json", schema = @Schema(implementation = UsuarioTicket.class))),
			@ApiResponse(responseCode = "404", description = "Nenhum chamado", content =
			@Content(mediaType = "application/json")),
			@ApiResponse(responseCode = "500", description = "Internal Server Error", content =
			@Content(mediaType = "application/json"))
	})
	public @ResponseBody UsuarioTicket buscarTicketPorID(@PathVariable Long id) throws NotFoundException {
		return service.getTicketId(id);
	}
	
	@GetMapping("/usuario/{id}")
	@Operation(summary = "Chamados", description = "Este serviço recupera uma lista chamados",
	responses = {
			@ApiResponse(responseCode = "200", description = "Usuários localizados", content =
					@Content(mediaType = "application/json", schema = @Schema(implementation = UsuarioTicket.class))),
			@ApiResponse(responseCode = "404", description = "Nenhum chamado", content =
			@Content(mediaType = "application/json")),
			@ApiResponse(responseCode = "500", description = "Internal Server Error", content =
			@Content(mediaType = "application/json"))
	})
	public @ResponseBody UsuarioTicket buscarTicketPorIdusuario(@PathVariable Long id) throws NotFoundException {
		return service.getTicketbyUsuario(id);
	}
	
	@PostMapping("/nota") 
	@Operation(summary = "Nova nota no ticket", description = "Adiciona uma nota a um ticket",
	responses = {
			@ApiResponse(responseCode = "200", description = "Sucesso", content =
					@Content(mediaType = "application/json")),
			@ApiResponse(responseCode = "500", description = "Internal Server Error", content =
			@Content(mediaType = "application/json"))
	})
	public void adicionarNota (@RequestBody UsuarioSuporteNotaTicket nota) throws Exception {
		service.novaNotaTicketUsuario(nota);
	}

}
