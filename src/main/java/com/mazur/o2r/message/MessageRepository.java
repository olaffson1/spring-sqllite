package com.mazur.o2r.message;

import java.util.List;
import org.springframework.data.repository.CrudRepository;

interface MessageRepository extends CrudRepository<Message, String> {

  List<Message> findAll();

}
