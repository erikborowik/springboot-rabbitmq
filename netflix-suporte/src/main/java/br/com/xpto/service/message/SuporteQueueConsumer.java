package br.com.xpto.service.message;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.xpto.data.UsuarioTicket;
import br.com.xpto.service.ChamadoService;

@Component
public class SuporteQueueConsumer {
	
	@Autowired
	private ObjectMapper objectMapper;
	
	@Autowired
	private ChamadoService service;
	
	@RabbitListener(queues = SuporteAMQPConfig.QUEUE_SUPORTE_TICKET)
	public void consumer(@Payload String fileBody) {
		UsuarioTicket chamado = null;
		try {
			chamado = objectMapper.readValue(fileBody, new TypeReference<UsuarioTicket>(){});
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		service.addUsuarioTicket(chamado);
	}

}
