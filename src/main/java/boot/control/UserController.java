package boot.control;

import boot.domain.User;
import boot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @创建人 sgwang
 * @name TestController
 * @user shiguang.wang
 * @创建时间 2019/7/8
 * @描述
 */
@RestController
@RequestMapping
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/list")
    public List<User> list() {
        System.out.println("执行list----------");
        List<User> users = userService.findAll();

        for (User user : users) {
            System.out.println(user.getName());
        }

        return userService.findAll();
    }

}
