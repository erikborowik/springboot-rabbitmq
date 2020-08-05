package br.com.xpto.data;

import org.springframework.data.repository.CrudRepository;

public interface UsuarioTicketRepositorio extends CrudRepository<UsuarioTicket, Long> {
	
	Iterable<UsuarioTicket> findByIdUsuario(Long idUsuario);
	
}
