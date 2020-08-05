package br.com.xpto.service.message;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.xpto.data.UsuarioSuporteNotaTicket;
import br.com.xpto.service.UsuarioTicketService;

@Component
public class UserQueueConsumer {
	
	@Autowired
	private ObjectMapper objectMapper;
	
	@Autowired
	private UsuarioTicketService ticketService;
	
	@RabbitListener(queues = UserAMQPConfig.QUEUE_RESPOSTA_SUPORTE)
	public void consumer(@Payload String fileBody) {
		UsuarioSuporteNotaTicket notaTicket = null;
		try {
			notaTicket = objectMapper.readValue(fileBody, new TypeReference<UsuarioSuporteNotaTicket>(){});
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		ticketService.addNotaTicket(notaTicket);
	}

}
