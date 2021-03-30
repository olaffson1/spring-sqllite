package com.mazur.o2r.message;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("message")
public class MessageController {

  private final MessageRepository messageRepository;

  public MessageController(
      MessageRepository messageRepository) {
    this.messageRepository = messageRepository;
  }

  @GetMapping(path = "", produces = "application/json")
  ResponseEntity<List<Message>> findAll() {
    log.info("Responding with all messages");
    return ResponseEntity.ok(messageRepository.findAll());
  }

  @PostMapping
  ResponseEntity<Message> save(@RequestBody String content) {
    log.info("Creating new message: {}", content);

    Message message = new Message();
    message.setContent(content);

    message = messageRepository.save(message);

    return ResponseEntity.status(HttpStatus.ACCEPTED).body(message);
  }

  @PutMapping(path = "{uuid}", produces = "application/json")
  ResponseEntity<Message> update(@PathVariable String uuid, @RequestBody String content) {
    log.info("Updating messageOpt with uuid: {}", uuid);

    Optional<Message> messageOpt = messageRepository.findById(UUID.fromString(uuid));

    log.debug("Updating {} messageOpt content: {}", messageOpt, content);

    if (messageOpt.isPresent()) {
      Message message = messageOpt.get();
      message.setContent(content);
      message = messageRepository.save(message);
      return ResponseEntity.status(HttpStatus.OK).body(message);
    } else {
      log.error("Not found user");
      return ResponseEntity.notFound().build();
    }
  }
}
