package br.com.xpto.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.xpto.data.GeneroFilme;
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
	
	
	@GetMapping(path = "/genero")
	@Operation(summary = "Gênero", description = "Este serviço recupera tipos de filmes",
	responses = {
			@ApiResponse(responseCode = "200", description = "Tipos localizados", content =
					@Content(mediaType = "application/json", schema = @Schema(implementation = GeneroFilme.class))),
			@ApiResponse(responseCode = "404", description = "Nenhum tipo localizado", content =
			@Content(mediaType = "application/json")),
			@ApiResponse(responseCode = "500", description = "Internal Server Error", content =
			@Content(mediaType = "application/json"))
	})
	public @ResponseBody Iterable<GeneroFilme> listarTodosGenerosDeFilmes() {
		return filmeService.findAll();
	}
	
	
	@PostMapping(path = "/genero") 
	@Operation(summary = "Novo Genero", description = "Adiciona genero",
	responses = {
			@ApiResponse(responseCode = "200", description = "Sucesso", content =
					@Content(mediaType = "application/json")),
			@ApiResponse(responseCode = "500", description = "Internal Server Error", content =
			@Content(mediaType = "application/json"))
	})
	public @ResponseBody GeneroFilme criarUsuario (@RequestBody GeneroFilme genero) {
		return filmeService.save(genero);
	}



}
