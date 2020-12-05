package com.atlaspharmacy.atlaspharmacy.pswregistration.rabbitmq;
import com.atlaspharmacy.atlaspharmacy.pswregistration.service.ISpecialOfferService;
import com.atlaspharmacy.atlaspharmacy.pswregistration.rabbitmq.RabbitMQSender;
import com.atlaspharmacy.atlaspharmacy.pswregistration.model.SpecialOffer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import java.util.Random; 


@Component
public class ScheduledTasks {
    private final RabbitMQSender sender;
    @Autowired
    private ISpecialOfferService specialOfferService;
    private Random rnd;


    @Autowired
    public ScheduledTasks(RabbitMQSender sender)
    {
        this.rnd = new Random();
        this.sender = sender;
        
    }

    @Scheduled(fixedRate = 180000)
    public void sendMessage()
    {
        SpecialOffer specialOffer = specialOfferService.get(rnd.nextInt(4) + 1);
        sender.send(specialOffer);
        System.out.println("Special offer sent: " + specialOffer.toString());
    }
}