package id.my.hendisantika.postgresqltestcontainer.exception;

import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * Created by IntelliJ IDEA.
 * Project : spring-boot-postgresql-testcontainer
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 02/11/24
 * Time: 06.35
 * To change this template use File | Settings | File Templates.
 */
@RestControllerAdvice
public class ControllerExceptionHandler {

//	@ExceptionHandler(value = NoSuchElementException.class)
//	@ResponseStatus(HttpStatus.NOT_FOUND)
//	public ErrorResponse noSuchElement(NoSuchElementException e) {
//		return ErrorResponse.builder().message(e.getMessage()).errorCode(404).status(HttpStatus.NOT_FOUND).build();
//	}
}
