package br.com.proprietyservice.endpoint;

import br.com.proprietyservice.config.MessagingConfig;
import br.com.proprietyservice.error.ResourceNotFoundException;

import br.com.proprietyservice.model.Propriety;
import br.com.proprietyservice.repository.ProprietyRepository;
import javax.validation.Valid;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("properties")
public class ProprietyEndpoints {
  private final ProprietyRepository proprietyDAO;

  @Autowired
  private RabbitTemplate rabbitTemplate;

  @Autowired
  public ProprietyEndpoints(ProprietyRepository proprietyDAO) {
    this.proprietyDAO = proprietyDAO;
  }

  @GetMapping
  public ResponseEntity<?> listAll() {
    return new ResponseEntity<>(proprietyDAO.findAll(), HttpStatus.OK);
  }

  @GetMapping(path = "/{id}")
  public ResponseEntity<?> getProprietyById(@PathVariable("id") Long id) {
    verifyIfProprietyExists(id);
    Propriety propriety = proprietyDAO.findById(id).get();
    return new ResponseEntity<>(propriety, HttpStatus.OK);
  }

  @GetMapping(path = "/findByUserId/{userId}")
  public ResponseEntity<?> findByUserId(@PathVariable String userId) {
    return new ResponseEntity<>(proprietyDAO.findByUserIdIgnoreCaseContaining(userId), HttpStatus.OK);
  }

  @PostMapping
  @Transactional(rollbackFor = Exception.class)
  public ResponseEntity<?> save(@Valid @RequestBody Propriety propriety) {
    return new ResponseEntity<>(proprietyDAO.save(propriety), HttpStatus.CREATED);
  }

  @DeleteMapping(path = "/{id}")
  public ResponseEntity<?> delete(@PathVariable Long id) {
    verifyIfProprietyExists(id);
    proprietyDAO.deleteById(id);
    return new ResponseEntity<>(HttpStatus.OK);
  }

  @PutMapping
  public ResponseEntity<?> update(@RequestBody Propriety propriety) {
    verifyIfProprietyExists(propriety.getProprietyId());
    proprietyDAO.save(propriety);
    return new ResponseEntity<>(HttpStatus.OK);
  }
  @GetMapping(path = "/hello")
  public ResponseEntity<?> hello() {


    return new ResponseEntity<>( "testes ok", HttpStatus.OK);
  }

  @PostMapping(path = "/relay")
  @Transactional(rollbackFor = Exception.class)
  public ResponseEntity<?> publisher(@Valid @RequestBody Propriety propriety) {
    rabbitTemplate.convertAndSend(MessagingConfig.EXCHANGE, MessagingConfig.ROUTING_KEY, propriety);
    return new ResponseEntity<>(HttpStatus.ACCEPTED);
  }

  private void verifyIfProprietyExists(Long id){
    if (!proprietyDAO.findById(id).isPresent()){
      throw new ResourceNotFoundException("Propriety not found for ID: "+id);
    }
  }
}