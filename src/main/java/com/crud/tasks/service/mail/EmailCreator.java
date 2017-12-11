package com.crud.tasks.service.mail;

/**
 * Email Creator interface.
 */
@FunctionalInterface
public interface EmailCreator {
  String buildEmail(final String message);
}
