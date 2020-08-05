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
public class UserAMQPConfig {


	public static String EXCHANGE_FILME = "Filmes-Exchange";
	public static String EXCHANGE_SUPORTE = "Suporte-Exchange";

	public static final String QUEUE_RESPOSTA_SUPORTE = "suporteResposta";
	public static final String ROUTING_KEY_SUPORTE_RESPOSTA = "suporte.resposta";
	
	//CONFIG PRODUCER FILME
	@Bean
	public Exchange declareExchangeFilme() {
		return ExchangeBuilder.directExchange(EXCHANGE_FILME)
				.durable(true)
				.build();
	}
	
	//CONFIG PRODUCER SUPORTE
	@Bean
	public Exchange declareExchangeSuporte() {
		return ExchangeBuilder.directExchange(EXCHANGE_SUPORTE)
				.durable(true)
				.build();
	}
	
	
	//CONFIG CONSUMER FILA SUPORTE
    @Bean
    public Queue declareQueue() {
        return QueueBuilder.durable(QUEUE_RESPOSTA_SUPORTE)
                .build();
    }

    @Bean
    public Binding declareBinding(@Qualifier("declareExchangeSuporte")Exchange exchange, @Qualifier("declareQueue")Queue queue) {
        return BindingBuilder.bind(queue)
                .to(exchange)
                .with(ROUTING_KEY_SUPORTE_RESPOSTA)
                .noargs();
    }

}
