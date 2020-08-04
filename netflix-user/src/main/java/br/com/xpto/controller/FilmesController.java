package br.com.xpto.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.xpto.data.UsuarioFilmeAssistido;
import br.com.xpto.data.UsuarioFilmeAvaliado;
import br.com.xpto.data.UsuarioListaFilme;
import br.com.xpto.service.FilmeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@RestController
@RequestMapping("/filme")
public class FilmesController {
	
	@Autowired
	private FilmeService service;
	
	@GetMapping(path = "/avaliacao")
	@Operation(summary = "Consulta Avaliações", description = "Este serviço recupera as avaliações realiazadas por um usuário",
	responses = {
			@ApiResponse(responseCode = "200", description = "Avaliações localizadas", content =
					@Content(mediaType = "application/json", schema = @Schema(implementation = UsuarioFilmeAvaliado.class))),
			@ApiResponse(responseCode = "404", description = "Nenhuma avaliação", content =
			@Content(mediaType = "application/json")),
			@ApiResponse(responseCode = "500", description = "Internal Server Error", content =
			@Content(mediaType = "application/json"))
	})
	public Iterable<UsuarioFilmeAvaliado> buscarAvaliacaoDoUsuario(@RequestParam(required = false) Integer idFilme, @RequestParam(required = false) Integer idUsuario) {
		return service.buscarAvaliacaoDoUsuario(idFilme, idUsuario);
		
	}
	
	@PostMapping("/avaliacao") 
	@Operation(summary = "Criar Avaliação", description = "Adiciona avaliação do usuário",
	responses = {
			@ApiResponse(responseCode = "200", description = "Sucesso", content =
					@Content(mediaType = "application/json")),
			@ApiResponse(responseCode = "500", description = "Internal Server Error", content =
			@Content(mediaType = "application/json"))
	})
	public void avaliarFilme (@RequestBody UsuarioFilmeAvaliado req) throws Exception {
		service.save(req);
	}
	
	
	@PostMapping("/play") 
	@Operation(summary = "Play Filme", description = "Adicionar filme na lista de assistidos",
	responses = {
			@ApiResponse(responseCode = "200", description = "Sucesso", content =
					@Content(mediaType = "application/json")),
			@ApiResponse(responseCode = "500", description = "Internal Server Error", content =
			@Content(mediaType = "application/json"))
	})
	public void playMovie (UsuarioFilmeAssistido filme) throws Exception {
		service.playFilm(filme);
	}
	
	@PostMapping("/marcar-lista-para-assistir}") 
	@Operation(summary = "Adicionar filme na lista pra assistir",
	responses = {
			@ApiResponse(responseCode = "200", description = "Sucesso", content =
					@Content(mediaType = "application/json")),
			@ApiResponse(responseCode = "500", description = "Internal Server Error", content =
			@Content(mediaType = "application/json"))
	})
	public void adicionarNaListaDeFilmesParaAssistir (UsuarioListaFilme filme) {
		service.adicionarNaListaDeFilmesParaAssistir(filme);
	}
	
	@GetMapping(path = "/lista-de-filmes-marcados")
	@Operation(summary = "Lista de filmes", description = "Este serviço recupera a lista de filmes marcados para assistir",
	responses = {
			@ApiResponse(responseCode = "200", description = "Lista de filmes", content =
					@Content(mediaType = "application/json", schema = @Schema(implementation = UsuarioListaFilme.class))),
			@ApiResponse(responseCode = "404", description = "Nenhum filme na lista", content =
			@Content(mediaType = "application/json")),
			@ApiResponse(responseCode = "500", description = "Internal Server Error", content =
			@Content(mediaType = "application/json"))
	})
	public Iterable<UsuarioListaFilme> buscarListaDeFilmesDoUsuario(@RequestParam(required = false) 
	Integer idFilme, @RequestParam(required = true) Integer idUsuario) {
		return service.buscarListaDeFilmesDoUsuario(idFilme, idUsuario);
		
	}

}
