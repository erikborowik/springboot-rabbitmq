package br.com.xpto.data;

import org.springframework.data.repository.CrudRepository;

public interface UsuarioFilmesAvaliadosRepositorio extends CrudRepository<UsuarioFilmeAvaliado, Integer> {
	
	
	Iterable<UsuarioFilmeAvaliado> findByIdFilmeAndIdUsuario(Integer idFilme, Integer idUsuario);

	Iterable<UsuarioFilmeAvaliado> findByIdFilme(Integer idFilme);

	Iterable<UsuarioFilmeAvaliado> findByIdUsuario(Integer idUsuario);

}
