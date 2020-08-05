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
public class SuporteAMQPConfig {


	public static String EXCHANGE_SUPORTE = "Support-Exchange";

	public static final String QUEUE_SUPORTE_TICKET = "suporteTicket";
	public static final String ROUTING_KEY_SUPORTE_TICKET = "suporte.ticket";
	
	//CONFIG PRODUCER
	@Bean
	public Exchange declareExchange() {
		return ExchangeBuilder.directExchange(EXCHANGE_SUPORTE)
				.durable(true)
				.build();
	}
	
	//CONFIG CONSUMER 1
    @Bean
    public Queue declareQueue() {
        return QueueBuilder.durable(QUEUE_SUPORTE_TICKET)
                .build();
    }

    @Bean
    public Binding declareBinding(@Qualifier("declareExchange")Exchange exchange, @Qualifier("declareQueue")Queue queue) {
        return BindingBuilder.bind(queue)
                .to(exchange)
                .with(ROUTING_KEY_SUPORTE_TICKET)
                .noargs();
    }
    
}
