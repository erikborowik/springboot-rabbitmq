package br.com.xpto.service.message;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Exchange;
import org.springframework.amqp.core.ExchangeBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.beans.factory.annotation.Qualifier;
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
	
	//CONFIG CONSUMER 1
    @Bean
    public Queue declareQueue() {
        return QueueBuilder.durable(QUEUE_FILMES_AVALIADOS)
                .build();
    }

    @Bean
    public Binding declareBinding(@Qualifier("declareExchange")Exchange exchange, @Qualifier("declareQueue")Queue queue) {
        return BindingBuilder.bind(queue)
                .to(exchange)
                .with(ROUTING_KEY_FILMES_AVALIADOS)
                .noargs();
    }
    
  //CONFIG CONSUMER 2
    @Bean
    public Queue declareQueue2() {
        return QueueBuilder.durable(QUEUE_FILMES_ASSISTIDOS)
                .build();
    }

    @Bean
    public Binding declareBinding2(@Qualifier("declareExchange")Exchange exchange2, @Qualifier("declareQueue2")Queue queue2) {
        return BindingBuilder.bind(queue2)
                .to(exchange2)
                .with(ROUTING_KEY_FILMES_ASSISTIDOS)
                .noargs();
    }
    
 

}
