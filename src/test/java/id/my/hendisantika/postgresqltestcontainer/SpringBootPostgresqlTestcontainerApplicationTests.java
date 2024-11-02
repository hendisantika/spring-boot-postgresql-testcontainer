package id.my.hendisantika.postgresqltestcontainer;

import com.fasterxml.jackson.databind.ObjectMapper;
import id.my.hendisantika.postgresqltestcontainer.repository.EmployeeRepository;
import id.my.hendisantika.postgresqltestcontainer.repository.UserInfoRepository;
import id.my.hendisantika.postgresqltestcontainer.request.EmployeeRequest;
import id.my.hendisantika.postgresqltestcontainer.request.UserInfoRequest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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

    @DynamicPropertySource
    static void setProperties(DynamicPropertyRegistry dynamicPropertyRegistry) {
        dynamicPropertyRegistry.add("spring.datasource.url", postgreSQLContainer::getJdbcUrl);
        dynamicPropertyRegistry.add("spring.datasource.username", postgreSQLContainer::getUsername);
        dynamicPropertyRegistry.add("spring.datasource.password", postgreSQLContainer::getPassword);
    }

    @Test
    @Order(value = 1)
    void testConnectionToDatabase() {
        Assertions.assertNotNull(employeeRepository);
        Assertions.assertNotNull(userInfoRepository);
    }

    @Test
    @Order(value = 2)
    @WithMockUser(username = "admin@gmail.com", roles = {"USER", "ADMIN"})
    void testAddEmployees() throws Exception {
        for (EmployeeRequest employee : employees) {
            String emp = objectMapper.writeValueAsString(employee);
            mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/employees").contentType(MediaType.APPLICATION_JSON)
                    .content(emp)).andExpect(status().isCreated());
        }
        Assertions.assertEquals(5, employeeRepository.findAll().size());
    }

    @Test
    @Order(value = 3)
    @WithMockUser(username = "admin@gmail.com", roles = {"ADMIN"})
    void testGetAllEmployees() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/employees")).andExpect(status().isOk());
        Assertions.assertEquals(employees.get(3).getName(), employeeRepository.findById(4).get().getName());
    }
}
