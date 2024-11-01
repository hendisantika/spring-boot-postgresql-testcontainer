package id.my.hendisantika.postgresqltestcontainer.controller;

import id.my.hendisantika.postgresqltestcontainer.request.EmployeeRequest;
import id.my.hendisantika.postgresqltestcontainer.response.EmployeeDTO;
import id.my.hendisantika.postgresqltestcontainer.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * Project : spring-boot-postgresql-testcontainer
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 02/11/24
 * Time: 06.48
 * To change this template use File | Settings | File Templates.
 */
@RestController
@RequestMapping(value = "/api/v1")
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class EmployeeController {

    private final EmployeeService employeeService;

    @GetMapping(value = "/employees")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public List<EmployeeDTO> employees() {
        return employeeService.employees();
    }

    @GetMapping(value = "/employees/{id}")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public EmployeeDTO employee(@PathVariable(value = "id") Integer empId) {
        return employeeService.employee(empId);
    }

    @PostMapping(value = "/employees")
    @ResponseStatus(HttpStatus.CREATED)
    public EmployeeDTO save(@RequestBody EmployeeRequest emp) {
        return employeeService.save(emp);
    }

    @DeleteMapping(value = "/employees")
    @ResponseStatus(HttpStatus.OK)
    public String deleteAll() {
        return employeeService.deleteAll();
    }

    @DeleteMapping(value = "/employees/{id}")
    @ResponseStatus(HttpStatus.OK)
    public String delete(@PathVariable(value = "id") Integer empId) {
        return employeeService.delete(empId);
    }

    @PutMapping(value = "/employees")
    @ResponseStatus(HttpStatus.OK)
    public EmployeeDTO update(@RequestBody EmployeeRequest emp) {
        return employeeService.update(emp);
    }
}
