package br.com.xpto.service.message;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class TesteQueueConsumer {
	
//    @RabbitListener(queues = {"${queue.order.name}"})
//    public void receive(@Payload String fileBody) {
//       System.out.println("Consumer TesteQueueConsumer");
//       System.out.println("Body: " + fileBody);
//    }
    
    
    @RabbitListener(queues = MachineAMQPConfig.QUEUE_FILMES_AVALIADOS)
    public void consumer(@Payload String fileBody) {
    	  System.out.println("Consumer QUEUE_FILMES_AVALIADOS");
          System.out.println("Body: " + fileBody);
    }
    
    @RabbitListener(queues = MachineAMQPConfig.QUEUE_FILMES_ASSISTIDOS)
    public void consumer2(@Payload String fileBody) {
    	  System.out.println("Consumer QUEUE_FILMES_ASSISTIDOS");
          System.out.println("Body: " + fileBody);
    }

}
