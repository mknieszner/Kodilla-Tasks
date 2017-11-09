package com.crud.tasks.domain.mail;

import lombok.AllArgsConstructor;
import lombok.Getter;
/**
 * Represents mail.
 */
@AllArgsConstructor
@Getter
public class Mail {
  private String mailTo;
  private String toCc = "";
  private String subject;
  private String message;

  public Mail(final String mailTo, final String subject, final String message) {
    this.mailTo = mailTo;
    this.subject = subject;
    this.message = message;
  }
}
