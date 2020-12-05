package com.atlaspharmacy.atlaspharmacy.pswregistration.rabbitmq;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import com.atlaspharmacy.atlaspharmacy.pswregistration.model.SpecialOffer;

@Service
public class RabbitMQSender {
    private final AmqpTemplate rabbitTemplate;

    @Autowired
    public RabbitMQSender(AmqpTemplate rabbitTemplate)
    {
        this.rabbitTemplate = rabbitTemplate;
    }

    @Value("athlas-exchange")
    String exchange;
    @Value("athlas-key")
    private String routingKey;
    
    public void send(SpecialOffer message)
    {
        rabbitTemplate.convertAndSend(exchange, routingKey, message);
    }
}