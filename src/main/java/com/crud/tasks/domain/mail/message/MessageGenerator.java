package com.crud.tasks.domain.mail.message;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Message Generator.
 */
@Component
public class MessageGenerator {
  @Autowired
  TaskQtyBasedMessageGenerator oneTaskMessageGenerator;
  @Autowired
  TaskQtyBasedMessageGenerator multipleTasksMessageGenerator;

  public String generateMessage(final Long taskQuantiy) {

    return "Currently in your database you got: " + getPartOfMessageBaseOnTaskQuantity(taskQuantiy);
  }

  private String getPartOfMessageBaseOnTaskQuantity(final Long taskQuantiy) {
    if (taskQuantiy == 1) {
      return oneTaskMessageGenerator.getPartOfMessageBaseOnTaskQuantity(taskQuantiy);
    } else if (taskQuantiy >= 0) {
      return multipleTasksMessageGenerator.getPartOfMessageBaseOnTaskQuantity(taskQuantiy);
    } else {
      throw new IllegalArgumentException("Task quantity less than zero!");
    }
  }
}
