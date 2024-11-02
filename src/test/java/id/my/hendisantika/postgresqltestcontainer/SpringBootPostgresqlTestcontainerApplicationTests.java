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

}
