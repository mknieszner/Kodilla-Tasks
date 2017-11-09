package com.crud.tasks.domain.mail.message;

/**
 * Message Generator interface.
 */
public interface TaskQtyBasedMessageGenerator {
  String getPartOfMessageBaseOnTaskQuantity(final Long taskQuantity);
}
