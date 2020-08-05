package br.com.xpto.data;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

public interface UsuarioTicketRepositorio extends CrudRepository<UsuarioTicket, Long> {

	Optional<UsuarioTicket> findByIdUsuario(Long id);

}
