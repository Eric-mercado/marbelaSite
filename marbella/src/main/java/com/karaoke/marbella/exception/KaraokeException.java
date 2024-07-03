package com.karaoke.marbella.exception;

public class KaraokeException extends RuntimeException{
    public KaraokeException() {
        super();
    }
    public KaraokeException(String message, Throwable cause) {
        super(message, cause);
    }
    public KaraokeException(String message) {
        super(message);
    }
    public KaraokeException(Throwable cause) {
        super(cause);
    }
}
