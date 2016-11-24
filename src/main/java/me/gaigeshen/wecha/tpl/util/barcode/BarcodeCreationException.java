package me.gaigeshen.wecha.tpl.util.barcode;

/**
 * 
 * 
 * @author gaigeshen
 */
public class BarcodeCreationException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public BarcodeCreationException(String message, Throwable cause) {
		super(message, cause);
	}

	public BarcodeCreationException(String message) {
		super(message);
	}

	public BarcodeCreationException(Throwable cause) {
		super(cause);
	}

	public BarcodeCreationException() {
		super();
	}

}
