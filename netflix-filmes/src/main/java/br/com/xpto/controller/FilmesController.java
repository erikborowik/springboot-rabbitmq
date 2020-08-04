package br.com.xpto.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.xpto.data.Filme;
import br.com.xpto.service.FilmeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@RestController
@RequestMapping("/filme")
public class FilmesController {
	
	@Autowired
	private FilmeService filmeService;
	
	
	@GetMapping()
	@Operation(summary = "Filme", description = "Este serviço recupera lista filmes para filtro pode passar idGenero e/ou nomeFilme",
	responses = {
			@ApiResponse(responseCode = "200", description = "Tipos localizados", content =
					@Content(mediaType = "application/json", schema = @Schema(implementation = Filme.class))),
			@ApiResponse(responseCode = "404", description = "Nenhum tipo localizado", content =
			@Content(mediaType = "application/json")),
			@ApiResponse(responseCode = "500", description = "Internal Server Error", content =
			@Content(mediaType = "application/json"))
	})
	public @ResponseBody Iterable<Filme> listarFilmes(@RequestParam(required = false) Integer idGenero, @RequestParam(required = false) String nomeFilme) {
		return filmeService.findAll(idGenero, nomeFilme);
	}
	
	@GetMapping("/categoria/{idCategoria}")
	@Operation(summary = "Filme", description = "Este serviço recupera lista filmes de uma determinada categoria/genero. "
			+ "Como opcional pode passar no queryparam o orderBy=name ou orderBy=maisAssistidos",
	responses = {
			@ApiResponse(responseCode = "200", description = "Tipos localizados", content =
					@Content(mediaType = "application/json", schema = @Schema(implementation = Filme.class))),
			@ApiResponse(responseCode = "404", description = "Nenhum tipo localizado", content =
			@Content(mediaType = "application/json")),
			@ApiResponse(responseCode = "500", description = "Internal Server Error", content =
			@Content(mediaType = "application/json"))
	})
	public @ResponseBody Iterable<Filme> listarFilmesCategoria(@PathVariable Integer idCategoria, @RequestParam(required = false) @Value("") String orderBy) {
		return filmeService.findByCategoria(idCategoria, orderBy);
	}
	
	
	@PostMapping 
	@Operation(summary = "Novo Filme",
	responses = {
			@ApiResponse(responseCode = "200", description = "Sucesso", content =
					@Content(mediaType = "application/json")),
			@ApiResponse(responseCode = "500", description = "Internal Server Error", content =
			@Content(mediaType = "application/json"))
	})
	public @ResponseBody Filme criarUsuario (@RequestBody Filme filme) {
		return filmeService.save(filme);
	}



}
