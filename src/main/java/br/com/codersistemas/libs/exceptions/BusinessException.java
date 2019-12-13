package br.com.codersistemas.libs.exceptions;

import org.apache.commons.lang3.StringUtils;

public final class BusinessException extends Exception {

	private static final long serialVersionUID = 3654488056868256185L;

	public BusinessException() {
		// LOG.error(ERROR_GENERICO);
	}

	public BusinessException(final String message) {
		super(message);
		// LOG.error(message);
	}

}
