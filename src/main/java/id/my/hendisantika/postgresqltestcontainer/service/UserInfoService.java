package id.my.hendisantika.postgresqltestcontainer.service;

import id.my.hendisantika.postgresqltestcontainer.config.UserInfoUserDetails;
import id.my.hendisantika.postgresqltestcontainer.entity.UserInfo;
import id.my.hendisantika.postgresqltestcontainer.repository.UserInfoRepository;
import id.my.hendisantika.postgresqltestcontainer.request.UserInfoRequest;
import id.my.hendisantika.postgresqltestcontainer.response.UserInfoDTO;
import id.my.hendisantika.postgresqltestcontainer.util.Utility;
import io.micrometer.observation.Observation;
import io.micrometer.observation.ObservationRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

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
    private UserInfoRepository userInfoRepository;

    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    private ObservationRegistry registry;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserInfo> userInfo = userInfoRepository.findByEmail(username);

        return userInfo.map(UserInfoUserDetails::new)
                .orElseThrow(() -> Utility.usernameNotFoundException("Given user not found : " + username));
    }

    public UserInfoDTO addUser(UserInfoRequest userInfoRequest) {
        userInfoRequest.setPassword(encoder.encode(userInfoRequest.getPassword()));

        UserInfo userInfo = UserInfo.builder().name(userInfoRequest.getName()).email(userInfoRequest.getEmail())
                .password(userInfoRequest.getPassword()).roles(userInfoRequest.getRoles()).build();

//		return userInfoRepository.save(userInfo);

        return Observation.createNotStarted("addUser", registry)
                .observe(() -> Utility.mapToUserInfoDTO(userInfoRepository.save(userInfo)));
    }

    public List<UserInfoDTO> users() {
//		return userInfoRepository.findAll();
        return Observation.createNotStarted("getUsers", registry)
                .observe(() -> userInfoRepository.findAll().stream().map(Utility::mapToUserInfoDTO).toList());
    }
}
