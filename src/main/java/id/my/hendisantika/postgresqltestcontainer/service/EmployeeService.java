package id.my.hendisantika.postgresqltestcontainer.service;

import id.my.hendisantika.postgresqltestcontainer.entity.Employee;
import id.my.hendisantika.postgresqltestcontainer.repository.EmployeeRepository;
import id.my.hendisantika.postgresqltestcontainer.request.EmployeeRequest;
import id.my.hendisantika.postgresqltestcontainer.response.EmployeeDTO;
import id.my.hendisantika.postgresqltestcontainer.util.Utility;
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
                .observe(() -> employeeRepository.findAll().stream().map(Utility::mapToEmployeeDTO).toList());
    }

    /**
     * @param emp
     * @return
     */
    public EmployeeDTO save(EmployeeRequest emp) {
        Employee employee = Employee.builder().name(emp.getName()).address(emp.getAddress()).build();
//		return EmployeeUtil.mapToEmployeeDTO(repository.save(employee));

        return Observation.createNotStarted("saveEmployee", registry)
                .observe(() -> Utility.mapToEmployeeDTO(employeeRepository.save(employee)));
    }

    /**
     * @param empId
     * @return
     */
    public String delete(Integer empId) {
        Employee employee = employeeRepository.findById(empId).orElseThrow(() -> Utility.notFound(empId));
        employeeRepository.delete(employee);
        return "Employee with id=" + empId + " removed";
    }

    /**
     * @return
     */
    public String deleteAll() {
        List<Employee> employees = employeeRepository.findAll();
        if (employees.isEmpty())
            return "No employees available";
        employeeRepository.deleteAll();
        return "All employees are removed.";
    }

    /**
     * @param empId
     * @return
     */
    public EmployeeDTO employee(Integer empId) {
//		Employee employee = employeeRepository.findById(empId).orElseThrow(() -> EmployeeUtil.notFound(empId));
//		return EmployeeUtil.mapToEmployeeDTO(employee);
        return Observation.createNotStarted("getEmployee", registry).observe(() -> Utility
                .mapToEmployeeDTO(employeeRepository.findById(empId).orElseThrow(() -> Utility.notFound(empId))));
    }

    /**
     * @param emp
     * @return
     */
    public EmployeeDTO update(EmployeeRequest emp) {
        Employee employee = employeeRepository.findById(emp.getId()).orElseThrow(() -> Utility.notFound(emp.getId()));

        employee.setId(emp.getId());
        employee.setName(emp.getName());
        employee.setAddress(emp.getAddress());

//		return EmployeeUtil.mapToEmployeeDTO(employeeRepository.save(employee));

        return Observation.createNotStarted("updateEmployee", registry)
                .observe(() -> Utility.mapToEmployeeDTO(employeeRepository.save(employee)));
    }

}
