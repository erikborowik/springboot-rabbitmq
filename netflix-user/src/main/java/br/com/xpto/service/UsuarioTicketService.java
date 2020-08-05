package br.com.xpto.service;

import java.util.Optional;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.xpto.data.UsuarioSuporteNotaTicket;
import br.com.xpto.data.UsuarioSuporteNotaTicketRepositorio;
import br.com.xpto.data.UsuarioTicket;
import br.com.xpto.data.UsuarioTicketRepositorio;
import br.com.xpto.service.message.UserAMQPConfig;
import javassist.NotFoundException;

@Service
public class UsuarioTicketService {
	
	@Autowired
	private UsuarioTicketRepositorio ticketRepo;
	
	@Autowired
	private UsuarioSuporteNotaTicketRepositorio notaTicketRepo;

	@Autowired
	private RabbitTemplate rabbitTemplate;

	public UsuarioTicket save(UsuarioTicket ticket) throws Exception {
		UsuarioTicket newTicket = ticketRepo.save(ticket);
		enviarMensagemProSuporte(newTicket);
        return newTicket;
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
	
	private void enviarMensagemProSuporte(UsuarioTicket newTicket) throws JsonProcessingException {
		String json = new ObjectMapper().writeValueAsString(newTicket);
        rabbitTemplate.convertAndSend(UserAMQPConfig.EXCHANGE_SUPORTE, "suporte.ticket", json);
	}


}
