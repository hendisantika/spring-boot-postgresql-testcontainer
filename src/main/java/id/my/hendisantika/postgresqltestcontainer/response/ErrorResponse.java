package id.my.hendisantika.postgresqltestcontainer.response;

import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

/**
 * Created by IntelliJ IDEA.
 * Project : spring-boot-postgresql-testcontainer
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 02/11/24
 * Time: 06.37
 * To change this template use File | Settings | File Templates.
 */
@Builder
@Data
public class ErrorResponse {
    private String message;
    private HttpStatus status;
    private Integer errorCode;
}
