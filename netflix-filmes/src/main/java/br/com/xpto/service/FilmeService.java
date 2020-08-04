package br.com.xpto.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.xpto.data.FilmeGeneroRepositorio;
import br.com.xpto.data.GeneroFilme;

@Service
public class FilmeService {
	
	
	@Autowired
	private FilmeGeneroRepositorio generoRepo;

	public Iterable<GeneroFilme> findAll() {
		return generoRepo.findAll();
	}

	public GeneroFilme save(GeneroFilme genero) {
		return generoRepo.save(genero);
	}

}
