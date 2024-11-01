package id.my.hendisantika.postgresqltestcontainer.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * Created by IntelliJ IDEA.
 * Project : spring-boot-postgresql-testcontainer
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 02/11/24
 * Time: 06.38
 * To change this template use File | Settings | File Templates.
 */
@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {
    // authentication
    @Bean
//	public UserDetailsService userDetailsService(PasswordEncoder encoder) {
    public UserDetailsService userDetailsService() {
//		UserDetails admin = User.withUsername("admin").password(encoder.encode("password")).roles("ADMIN").build();
//		UserDetails user = User.withUsername("user").password(encoder.encode("pwd")).roles("USER").build();
//		return new InMemoryUserDetailsManager(admin, user);

        return new UserInfoService();

    }

}
