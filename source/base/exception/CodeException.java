package base.exception;

/**
 * Throw a CodeException when something seemingly impossible has happened because of a mistake in the code.
 * When you catch a CodeException, close the program and try to contact the programmer.
 */
public class CodeException extends RuntimeException {}
