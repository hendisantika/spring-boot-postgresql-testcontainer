package id.my.hendisantika.postgresqltestcontainer.controller;

import id.my.hendisantika.postgresqltestcontainer.request.UserInfoRequest;
import id.my.hendisantika.postgresqltestcontainer.response.UserInfoDTO;
import id.my.hendisantika.postgresqltestcontainer.service.UserInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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
 * Time: 06.50
 * To change this template use File | Settings | File Templates.
 */
@RestController
@RequestMapping(value = "/api/v1")
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class UserController {

    private final UserInfoService userInfoService;

    @PostMapping(value = "/user")
    @ResponseStatus(HttpStatus.CREATED)
    public UserInfoDTO addUser(@RequestBody UserInfoRequest userInfo) {
        return userInfoService.addUser(userInfo);
    }

    @GetMapping(value = "/user")
    @ResponseStatus(HttpStatus.OK)
    public List<UserInfoDTO> users() {
        return userInfoService.users();
    }
}
