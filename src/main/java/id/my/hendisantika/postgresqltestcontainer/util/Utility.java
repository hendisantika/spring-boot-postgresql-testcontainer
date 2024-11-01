package id.my.hendisantika.postgresqltestcontainer.util;

import id.my.hendisantika.postgresqltestcontainer.entity.Employee;
import id.my.hendisantika.postgresqltestcontainer.entity.UserInfo;
import id.my.hendisantika.postgresqltestcontainer.response.EmployeeDTO;
import id.my.hendisantika.postgresqltestcontainer.response.UserInfoDTO;
import lombok.extern.slf4j.Slf4j;

import java.util.NoSuchElementException;

/**
 * Created by IntelliJ IDEA.
 * Project : spring-boot-postgresql-testcontainer
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 02/11/24
 * Time: 06.46
 * To change this template use File | Settings | File Templates.
 */
@Slf4j
public class Utility {
    public Utility() {
    }

    public static NoSuchElementException notFound(Integer empId) {
        log.error("Employee with id={} not found.", empId);
        return new NoSuchElementException("Employee with id=" + empId + " not found.");
    }

    public static EmployeeDTO mapToEmployeeDTO(Employee emp) {
        EmployeeDTO employeeDTO = EmployeeDTO.builder().id(emp.getId()).name(emp.getName()).address(emp.getAddress())
                .build();

        log.info("Employee details : {}", employeeDTO);
        return employeeDTO;
    }

    public static UserInfoDTO mapToUserInfoDTO(UserInfo userInfo) {
        UserInfoDTO userInfoDTO = UserInfoDTO.builder().id(userInfo.getId()).name(userInfo.getName())
                .email(userInfo.getEmail()).roles(userInfo.getRoles()).build();
        log.info("User details : {}", userInfoDTO);
        return userInfoDTO;
    }
}
