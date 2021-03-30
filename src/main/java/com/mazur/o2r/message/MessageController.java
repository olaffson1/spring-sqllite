package com.mazur.o2r.message;

import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("message")
public class MessageController {

  @Autowired
  private MessageRepository messageRepository;


  @GetMapping(path = "", produces = "application/json")
  ResponseEntity<List<Message>> findAll() {
    log.info("Responding with all messages");
    return ResponseEntity.ok(messageRepository.findAll());
  }

}
