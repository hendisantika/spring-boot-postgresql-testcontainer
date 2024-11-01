package id.my.hendisantika.postgresqltestcontainer.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.NoSuchElementException;

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
@ExceptionHandler(value = NoSuchElementException.class)
public ProblemDetail onNoSuchElement(NoSuchElementException e) throws URISyntaxException {
    ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, e.getMessage());
    HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes())
            .getRequest();

    problemDetail.setInstance(new URI(request.getRequestURI()));
    problemDetail.setType(URI.create("http://api.employees.com/errors/not-found"));
    problemDetail.setTitle("Element Not Found");
    return problemDetail;
}
}
