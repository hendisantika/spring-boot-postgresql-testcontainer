package id.my.hendisantika.postgresqltestcontainer.service;

import id.my.hendisantika.postgresqltestcontainer.entity.Employee;
import id.my.hendisantika.postgresqltestcontainer.repository.EmployeeRepository;
import id.my.hendisantika.postgresqltestcontainer.request.EmployeeRequest;
import id.my.hendisantika.postgresqltestcontainer.response.EmployeeDTO;
import io.micrometer.observation.Observation;
import io.micrometer.observation.ObservationRegistry;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    /**
     * @return
     */
    public List<EmployeeDTO> employees() {
//		return repository.findAll().stream().map(EmployeeUtil::mapToEmployeeDTO).toList();
        return Observation.createNotStarted("getEmployees", registry)
                .observe(() -> employeeRepository.findAll().stream().map(Utililty::mapToEmployeeDTO).toList());
    }

    /**
     * @param emp
     * @return
     */
    public EmployeeDTO save(EmployeeRequest emp) {
        Employee employee = Employee.builder().name(emp.getName()).address(emp.getAddress()).build();
//		return EmployeeUtil.mapToEmployeeDTO(repository.save(employee));

        return Observation.createNotStarted("saveEmployee", registry)
                .observe(() -> Utililty.mapToEmployeeDTO(repository.save(employee)));
    }

}
