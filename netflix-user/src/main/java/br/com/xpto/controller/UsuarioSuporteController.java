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
import br.com.xpto.data.UsuarioTicket;
import br.com.xpto.service.UsuarioTicketService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import javassist.NotFoundException;

@RestController
@RequestMapping("/suporte")
public class UsuarioSuporteController {

	@Autowired
	private UsuarioTicketService service;

	@PostMapping() 
	@Operation(summary = "Novo Ticket", 
	responses = {
			@ApiResponse(responseCode = "200", description = "Sucesso", content =
					@Content(mediaType = "application/json")),
			@ApiResponse(responseCode = "500", description = "Internal Server Error", content =
			@Content(mediaType = "application/json"))
	})
	public @ResponseBody UsuarioTicket criarTicket (@RequestBody UsuarioTicket ticket) {
		return service.save(ticket);
	}

	@GetMapping("/usuario/{idUsuario}")
	@Operation(summary = "Buscar Tickets", description = "Este serviço recupera uma lista de tickets aberto pelo usuário",
	responses = {
			@ApiResponse(responseCode = "200", description = "Tickets localizados", content =
					@Content(mediaType = "application/json", schema = @Schema(implementation = Usuario.class))),
			@ApiResponse(responseCode = "404", description = "Nenhum Ticket", content =
			@Content(mediaType = "application/json")),
			@ApiResponse(responseCode = "500", description = "Internal Server Error", content =
			@Content(mediaType = "application/json"))
	})
	public @ResponseBody Iterable<UsuarioTicket> listarTodosTicketsUsuarios(@PathVariable Long idUsuario) {
		return service.findAllTickets(idUsuario);
	}
	
	@Operation(summary = "Buscar Ticket", description = "Este serviço busca Ticket pelo ID",
	responses = {
			@ApiResponse(responseCode = "200", description = "Ticket localizado", content =
					@Content(mediaType = "application/json", schema = @Schema(implementation = Usuario.class))),
			@ApiResponse(responseCode = "404", description = "Nenhum Ticket", content =
			@Content(mediaType = "application/json")),
			@ApiResponse(responseCode = "500", description = "Internal Server Error", content =
			@Content(mediaType = "application/json"))
	})
	@GetMapping(path = "/{id}")
	public @ResponseBody UsuarioTicket buscarTicketPeloID(@PathVariable(value = "id") Long id) throws NotFoundException {
		return service.findById(id);
	}

}
