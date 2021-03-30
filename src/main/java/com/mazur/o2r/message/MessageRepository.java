package com.mazur.o2r.message;

import java.util.List;
import java.util.UUID;
import org.springframework.data.repository.CrudRepository;

interface MessageRepository extends CrudRepository<Message, UUID> {

  List<Message> findAll();

}
