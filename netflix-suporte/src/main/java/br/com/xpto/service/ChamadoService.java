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
import br.com.xpto.service.message.SuporteAMQPConfig;
import javassist.NotFoundException;

@Service
public class ChamadoService {
	
	@Autowired
	private UsuarioTicketRepositorio userTicketRepo;
	
	@Autowired
	private UsuarioSuporteNotaTicketRepositorio userNotaRepo;
	
	@Autowired
	private RabbitTemplate rabbitTemplate;

	public void addUsuarioTicket(UsuarioTicket chamado) {
		userTicketRepo.save(chamado);
	}
	
	public void novaNotaTicketUsuario(UsuarioSuporteNotaTicket nota) throws Exception {
	    String json = new ObjectMapper().writeValueAsString(nota);
        rabbitTemplate.convertAndSend(SuporteAMQPConfig.EXCHANGE_SUPORTE, "suporte.resposta", json);
		userNotaRepo.save(nota);
	}
	
	public Iterable<UsuarioTicket> findAll(){
		return userTicketRepo.findAll();
	}
	
	public UsuarioTicket getTicketId(Long id) throws NotFoundException {
		Optional<UsuarioTicket> findById = userTicketRepo.findById(id);
		
		if(findById.isPresent()) {
			return findById.get();
		}else {
			throw new NotFoundException("Ticket nã localizado");
		}
	}

	
	public UsuarioTicket getTicketbyUsuario(Long id) throws NotFoundException {
		Optional<UsuarioTicket> findById = userTicketRepo.findByIdUsuario(id);
		if(findById.isPresent()) {
			return findById.get();
		}else {
			throw new NotFoundException("Ticket nã localizado");
		}
	}
	

}
