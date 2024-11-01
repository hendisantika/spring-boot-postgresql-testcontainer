package id.my.hendisantika.postgresqltestcontainer.service;

import id.my.hendisantika.postgresqltestcontainer.repository.EmployeeRepository;
import io.micrometer.observation.ObservationRegistry;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by IntelliJ IDEA.
 * Project : spring-boot-postgresql-testcontainer
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 02/11/24
 * Time: 06.41
 * To change this template use File | Settings | File Templates.
 */
@Service
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final ObservationRegistry registry;

}
