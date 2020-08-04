package br.com.xpto.service.message;

import org.springframework.amqp.core.Exchange;
import org.springframework.amqp.core.ExchangeBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MachineAMQPConfig {


	public static String EXCHANGE_FILME = "Filmes-Exchange";

	public static final String QUEUE_FILMES_AVALIADOS = "filmesAvaliados";
	public static final String QUEUE_FILMES_ASSISTIDOS = "filmesAssistidos";
	public static final String ROUTING_KEY_FILMES_AVALIADOS = "filmes.avaliados";
	public static final String ROUTING_KEY_FILMES_ASSISTIDOS = "filmes.assistidos";
	
	//CONFIG PRODUCER
	@Bean
	public Exchange declareExchange() {
		return ExchangeBuilder.directExchange(EXCHANGE_FILME)
				.durable(true)
				.build();
	}
 

}
