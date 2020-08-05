package br.com.xpto.data;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface FilmeRepositorio extends CrudRepository<Filme, Integer> {
	
	Iterable<Filme> findByIdGeneroFilmeOrderByNameAsc(Integer idGeneroFilme);
	
	Iterable<Filme> findByIdGeneroFilmeOrderByQuantidadeAssistidaDesc(Integer idGeneroFilme);
	
	@Query("select f from Filme f where lower(f.name) like %:texto% or lower(f.detalhes) like %:texto%")
	Iterable<Filme> findByNameIgnoreCaseOrDetalhesIgnoreCase(String texto);
	
	Iterable<Filme> findByName(String name);

	Iterable<Filme> findByIdGeneroFilmeAndName(Integer idGeneroFilme, String name);
	
}
