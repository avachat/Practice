package avachat.webapp.api;

import avachat.persistence.entity.User;
import avachat.persistence.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Slf4j
public class UserAPI {

    private final UserService userService;

    public UserAPI(@Autowired UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/users")
    public User createOne(@RequestBody User user) {
        return userService.createOne(user);
    }

    @GetMapping("/users")
    public List<User> findAll() {
        return userService.findAll();
    }
}
