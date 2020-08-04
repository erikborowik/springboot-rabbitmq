package br.com.xpto.data;

import org.springframework.data.repository.CrudRepository;

public interface UsuarioListaFilmeRepositorio extends CrudRepository<UsuarioListaFilme, Integer> {
	
	
	Iterable<UsuarioListaFilme> findByIdFilmeAndIdUsuario(Integer idFilme, Integer idUsuario);

	Iterable<UsuarioListaFilme> findByIdFilme(Integer idFilme);

	Iterable<UsuarioListaFilme> findByIdUsuario(Integer idUsuario);

}
