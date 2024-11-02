package id.my.hendisantika.postgresqltestcontainer;

import com.fasterxml.jackson.databind.ObjectMapper;
import id.my.hendisantika.postgresqltestcontainer.repository.EmployeeRepository;
import id.my.hendisantika.postgresqltestcontainer.repository.UserInfoRepository;
import id.my.hendisantika.postgresqltestcontainer.request.EmployeeRequest;
import id.my.hendisantika.postgresqltestcontainer.request.UserInfoRequest;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@AutoConfigureMockMvc
@Testcontainers
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class SpringBootPostgresqlTestcontainerApplicationTests {

    private final static List<EmployeeRequest> employees = new ArrayList<>();
    private final static List<UserInfoRequest> users = new ArrayList<>();
    @Container
    private static final PostgreSQLContainer<?> postgreSQLContainer = new PostgreSQLContainer<>("postgres:17-alpine3.20")
            .withDatabaseName("integration-tests-db").withUsername("username").withPassword("password")
            .withInitScript("test-data.sql");

    static {
        postgreSQLContainer.start();
    }

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private UserInfoRepository userInfoRepository;

    @Autowired
    private ObjectMapper objectMapper;

    static {
        EmployeeRequest emp1 = EmployeeRequest.builder().name("test emp1").address("address1").build();
        EmployeeRequest emp2 = EmployeeRequest.builder().name("test emp2").address("address2").build();
        EmployeeRequest emp3 = EmployeeRequest.builder().name("test emp3").address("address3").build();
        EmployeeRequest emp4 = EmployeeRequest.builder().name("test emp4").address("address4").build();
        EmployeeRequest emp5 = EmployeeRequest.builder().name("test emp5").address("address5").build();

        employees.add(emp1);
        employees.add(emp2);
        employees.add(emp3);
        employees.add(emp4);
        employees.add(emp5);

        UserInfoRequest admin = UserInfoRequest.builder().name("admin").email("admin@gmail.com").password("password")
                .roles("ROLE_ADMIN").build();
        UserInfoRequest user = UserInfoRequest.builder().name("user").email("user@gmail.com").password("password")
                .roles("ROLE_USER").build();
        UserInfoRequest adminUser = UserInfoRequest.builder().name("adminuser").email("adminuser@gmail.com")
                .password("password").roles("ROLE_ADMIN,ROLE_USER").build();

        users.add(admin);
        users.add(user);
        users.add(adminUser);
    }

}
