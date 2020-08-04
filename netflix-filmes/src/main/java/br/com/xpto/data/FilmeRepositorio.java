package br.com.xpto.data;

import org.springframework.data.repository.CrudRepository;

public interface FilmeRepositorio extends CrudRepository<Filme, Integer> {
	
	Iterable<Filme> findByIdGeneroFilmeOrderByNameAsc(Integer idGeneroFilme);
	
	Iterable<Filme> findByIdGeneroFilmeOrderByQuantidadeAssistidaDesc(Integer idGeneroFilme);
	
	Iterable<Filme> findByName(String name);

	Iterable<Filme> findByIdGeneroFilmeAndName(Integer idGeneroFilme, String name);
}
