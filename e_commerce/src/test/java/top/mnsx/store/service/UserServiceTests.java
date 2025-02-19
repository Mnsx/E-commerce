package top.mnsx.store.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import top.mnsx.store.entity.User;
import top.mnsx.store.service.ex.ServiceException;

@SpringBootTest
public class UserServiceTests {
    @Autowired
    private IUserService userService;

    @Test
    public void testRegister(){
        try {
            User user = new User();
            user.setUsername("test1");
            user.setPassword("123123");
            userService.register(user);
            System.out.println("Ok");
        } catch (ServiceException e) {
            System.out.println(e.getClass().getSimpleName());
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void testLogin(){
        try{
            String username = "test1";
            String password = "123123";
            User user = userService.login(username, password);
        } catch(ServiceException e){
            System.out.println(e.getClass().getSimpleName());
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void testChangePassword(){
        userService.changePassword(7, "mnsx", "123123", "123456");
    }

    @Test
    public void testChangeInfo(){
        User user = new User();
        user.setUid(7);
        user.setPhone("120");
        user.setEmail("120@163.com");
        user.setGender(1);
        userService.changeInfo(7, "mnsx", user);
    }

    @Test
    public void testGetByUid(){
        System.out.println(userService.getByUid(7));
    }

    @Test
    public void changeAvatar() {
        userService.changeAvatar(7, "/upload/test.png", "mnsx");
    }
}
