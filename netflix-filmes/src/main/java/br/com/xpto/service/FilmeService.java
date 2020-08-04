package br.com.xpto.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.xpto.data.Filme;
import br.com.xpto.data.FilmeGeneroRepositorio;
import br.com.xpto.data.FilmeRepositorio;
import br.com.xpto.data.GeneroFilme;

@Service
public class FilmeService {
	
	
	@Autowired
	private FilmeGeneroRepositorio generoRepo;
	
	@Autowired
	private FilmeRepositorio filmeRepo;

	public Iterable<GeneroFilme> findAllGeneros() {
		return generoRepo.findAll();
	}

	public GeneroFilme saveGenero(GeneroFilme genero) {
		return generoRepo.save(genero);
	}

	public Iterable<Filme> findAll(Integer idGenero, String nomeFilme) {
		
		if(idGenero != null && nomeFilme != null) {
			return filmeRepo.findByIdGeneroFilmeAndName(idGenero, nomeFilme);
		}else if (idGenero != null){
			return filmeRepo.findByIdGeneroFilmeOrderByNameAsc(idGenero);
		}else if (nomeFilme != null) {
			return filmeRepo.findByName(nomeFilme);
		}else {
			return filmeRepo.findAll();
		}
	}

	public Filme save(Filme filme) {
		return filmeRepo.save(filme);
	}

	public Iterable<Filme> findByCategoria(Integer idGenero, String orderBy) {
		
		if(orderBy != null && orderBy.equals("name")) {
			return filmeRepo.findByIdGeneroFilmeOrderByNameAsc(idGenero);
		}else if(orderBy != null  && orderBy.equals("maisAssistidos")) {
			return filmeRepo.findByIdGeneroFilmeOrderByQuantidadeAssistidaDesc(idGenero);
		}else {
			return filmeRepo.findByIdGeneroFilmeOrderByNameAsc(idGenero);
		}
	}

}
