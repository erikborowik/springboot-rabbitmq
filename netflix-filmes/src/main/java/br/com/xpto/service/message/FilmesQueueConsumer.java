package br.com.xpto.service.message;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.xpto.service.FilmeService;
import br.com.xpto.service.message.dto.UsuarioFilmeAssistido;
import br.com.xpto.service.message.dto.UsuarioFilmeAvaliado;

@Component
public class FilmesQueueConsumer {
	
	@Autowired
	private FilmeService filmeService;
	
	@Autowired
	private ObjectMapper objectMapper;


	@RabbitListener(queues = MachineAMQPConfig.QUEUE_FILMES_AVALIADOS)
	public void consumer(@Payload String fileBody) {
		UsuarioFilmeAvaliado filmeAvaliado = null;
		try {
			filmeAvaliado = objectMapper.readValue(fileBody, new TypeReference<UsuarioFilmeAvaliado>(){});
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		filmeService.adicionarAvaliacaoUsuario(filmeAvaliado);
	}

	@RabbitListener(queues = MachineAMQPConfig.QUEUE_FILMES_ASSISTIDOS)
	public void consumer2(@Payload String fileBody) {
		UsuarioFilmeAssistido filmeAssistido = null;
		try {
			filmeAssistido = objectMapper.readValue(fileBody, new TypeReference<UsuarioFilmeAssistido>(){});
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		filmeService.adicionarFilmeAssistido(filmeAssistido);
	}

}
