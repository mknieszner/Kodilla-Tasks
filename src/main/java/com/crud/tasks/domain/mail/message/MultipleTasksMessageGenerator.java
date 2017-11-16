package com.crud.tasks.domain.mail.message;

import org.springframework.stereotype.Component;

/**
 * Multiple Tasks sub - message Generator.
 */
@Component("multipleTasksMessageGenerator")
public class MultipleTasksMessageGenerator implements TaskQtyBasedMessageGenerator {
  public String getPartOfMessageBaseOnTaskQuantity(final Long taskQuantity) {
    return taskQuantity + " tasks";
  }
}