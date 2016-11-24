package me.gaigeshen.wecha.tpl.controller.util;

public class FileHandleException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public FileHandleException() {
		super();
	}

	public FileHandleException(String message) {
		super(message);
	}

	public FileHandleException(Throwable cause) {
		super(cause);
	}

	public FileHandleException(String message, Throwable cause) {
		super(message, cause);
	}

}
