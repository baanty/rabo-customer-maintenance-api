package com.rabo.api.exception;

/**
 * Use this exception to catch generic application exceptions and 
 * trap those exceptions in the custome exception handler, <code>CustomGlobalExceptionHandler</code>
 * class.
 * 
 * @author Pijush Kanti Das.
 *
 */
public class GenericCustomerApplicationRuntimeException extends RuntimeException {

	/**
	 * 
	 */
	
	private static final long serialVersionUID = -2665276316016963349L;
	
	/**
	 * This is the constructor for the Custom exception.
	 * USe the customer id to detail the error to the user.
	 * @param customerId : The customer id, on which operation was performed.
	 * 
	 */
	public GenericCustomerApplicationRuntimeException(int customerId, Throwable throwable) {
		super("Exception Occured while doing operation for customer id - " + customerId + ". The real error is - " + throwable.getMessage(), throwable);
	}
	
	/**
	 * This constructor for the Custom exception.
	 * USe the customer id to detail the error to the user.
	 * @param throwable : Original Throwable.
	 * 
	 */
	public GenericCustomerApplicationRuntimeException(Throwable throwable) {
		super("Exception Occured while doing operation. Detailed exception is - " , throwable);
	}

}
