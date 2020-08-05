package br.com.xpto.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.xpto.data.UsuarioSuporteNotaTicket;
import br.com.xpto.data.UsuarioSuporteNotaTicketRepositorio;
import br.com.xpto.data.UsuarioTicket;
import br.com.xpto.data.UsuarioTicketRepositorio;
import javassist.NotFoundException;

@Service
public class UsuarioTicketService {
	
	@Autowired
	private UsuarioTicketRepositorio ticketRepo;
	
	@Autowired
	private UsuarioSuporteNotaTicketRepositorio notaTicketRepo;

	public UsuarioTicket save(UsuarioTicket ticket) {
		return ticketRepo.save(ticket);
	}

	public Iterable<UsuarioTicket> findAllTickets(Long idUsuario) {
		return ticketRepo.findByIdUsuario(idUsuario);
	}

	public UsuarioTicket findById(Long id) throws NotFoundException {
		Optional<UsuarioTicket> findById = ticketRepo.findById(id);
		if(findById.isPresent()) {
			return findById.get();
		}else {
			throw new NotFoundException("Não localizado");
		}
	}
	
	public void addNotaTicket(UsuarioSuporteNotaTicket nota) {
		notaTicketRepo.save(nota);
	}


}
