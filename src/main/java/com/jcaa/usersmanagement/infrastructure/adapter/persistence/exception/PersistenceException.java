package com.jcaa.usersmanagement.infrastructure.adapter.persistence.exception;

public final class PersistenceException extends RuntimeException {

  private static final String MESSAGE_SAVE = "Failed to save user with ID: '%s'.";
  private static final String MESSAGE_UPDATE = "Failed to update user with ID: '%s'.";
  private static final String MESSAGE_FIND = "Failed to find user with ID: '%s'.";
  private static final String MESSAGE_EMAIL = "Failed to find user with email: '%s'.";
  private static final String MESSAGE_ALL = "Failed to retrieve all users.";
  private static final String MESSAGE_DELETE = "Failed to delete user with ID: '%s'.";
  private static final String MESSAGE_CONNECTION = "Could not establish database connection.";

  private static final String MESSAGE_SAVE_BIEN = "Failed to save bien with ID: '%s'.";
  private static final String MESSAGE_UPDATE_BIEN = "Failed to update bien with ID: '%s'.";
  private static final String MESSAGE_FIND_BIEN = "Failed to find bien with ID: '%s'.";
  private static final String MESSAGE_ALL_BIEN = "Failed to retrieve all bienes.";
  private static final String MESSAGE_DELETE_BIEN = "Failed to delete bien with ID: '%s'.";

  private PersistenceException(final String message, final Throwable cause) {
    super(message, cause);
  }

  public static PersistenceException becauseSaveFailed(final String userId, final Throwable cause) {
    return new PersistenceException(String.format(MESSAGE_SAVE, userId), cause);
  }

  public static PersistenceException becauseUpdateFailed(
      final String userId, final Throwable cause) {
    return new PersistenceException(String.format(MESSAGE_UPDATE, userId), cause);
  }

  public static PersistenceException becauseFindByIdFailed(
      final String userId, final Throwable cause) {
    return new PersistenceException(String.format(MESSAGE_FIND, userId), cause);
  }

  public static PersistenceException becauseFindByEmailFailed(
      final String email, final Throwable cause) {
    return new PersistenceException(String.format(MESSAGE_EMAIL, email), cause);
  }

  public static PersistenceException becauseFindAllFailed(final Throwable cause) {
    return new PersistenceException(MESSAGE_ALL, cause);
  }

  public static PersistenceException becauseDeleteFailed(
      final String userId, final Throwable cause) {
    return new PersistenceException(String.format(MESSAGE_DELETE, userId), cause);
  }

  public static PersistenceException becauseConnectionFailed(final Throwable cause) {
    return new PersistenceException(MESSAGE_CONNECTION, cause);
  }

  public static PersistenceException becauseSaveBienFailed(
          final String bienId, final Throwable cause) {
    return new PersistenceException(String.format(MESSAGE_SAVE_BIEN, bienId), cause);
  }

  public static PersistenceException becauseUpdateBienFailed(
          final String bienId, final Throwable cause) {
    return new PersistenceException(String.format(MESSAGE_UPDATE_BIEN, bienId), cause);
  }

  public static PersistenceException becauseFindBienByIdFailed(
          final String bienId, final Throwable cause) {
    return new PersistenceException(String.format(MESSAGE_FIND_BIEN, bienId), cause);
  }

  public static PersistenceException becauseFindAllBienesFailed(final Throwable cause) {
    return new PersistenceException(MESSAGE_ALL_BIEN, cause);
  }

  public static PersistenceException becauseDeleteBienFailed(
          final String bienId, final Throwable cause) {
    return new PersistenceException(String.format(MESSAGE_DELETE_BIEN, bienId), cause);
  }
}
