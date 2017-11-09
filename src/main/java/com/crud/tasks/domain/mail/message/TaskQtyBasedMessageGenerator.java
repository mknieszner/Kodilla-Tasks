package com.crud.tasks.domain.mail.message;

/**
 * Task Quantity Based Message Generator interface.
 */
public interface TaskQtyBasedMessageGenerator {
  String getPartOfMessageBaseOnTaskQuantity(final Long taskQuantity);
}
