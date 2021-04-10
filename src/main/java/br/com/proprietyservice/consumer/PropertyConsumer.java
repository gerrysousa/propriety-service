package br.com.proprietyservice.consumer;

import br.com.proprietyservice.config.MessagingConfig;
import br.com.proprietyservice.model.Propriety;
import br.com.proprietyservice.repository.ProprietyRepository;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PropertyConsumer {
  private final ProprietyRepository proprietyDAO;

  @Autowired
  public PropertyConsumer(ProprietyRepository proprietyDAO) {
    this.proprietyDAO = proprietyDAO;
  }


  @RabbitListener(queues = MessagingConfig.QUEUE)
  public void consumeMessageFromQueue(Propriety propriety) {
    System.out.println("Message recieved from queue : " + propriety.toString());

    proprietyDAO.save(propriety);
  }
}
