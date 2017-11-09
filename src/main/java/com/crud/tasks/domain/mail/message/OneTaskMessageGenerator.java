package com.crud.tasks.domain.mail.message;

import org.springframework.stereotype.Component;

/**
 * One Task sub - message Generator.
 */
@Component("oneTaskMessageGenerator")
public class OneTaskMessageGenerator  implements TaskQtyBasedMessageGenerator {
  public String getPartOfMessageBaseOnTaskQuantity(final Long taskQuantity) {
    return taskQuantity + " task";
  }
}
