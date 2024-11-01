package id.my.hendisantika.postgresqltestcontainer.service;

import io.micrometer.observation.ObservationRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * Created by IntelliJ IDEA.
 * Project : spring-boot-postgresql-testcontainer
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 02/11/24
 * Time: 06.43
 * To change this template use File | Settings | File Templates.
 */
@Component
public class UserInfoService implements UserDetailsService {

    @Autowired
    private IUserInfoRepository repository;

    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    private ObservationRegistry registry;
}
