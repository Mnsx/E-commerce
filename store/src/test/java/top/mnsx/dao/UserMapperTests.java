package top.mnsx.dao;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import top.mnsx.entity.User;

@SpringBootTest
public class UserMapperTests {
    @Autowired
    private UserMapper userMapper;

    /**
     * 测试UserMapper的insert方法
     */
    @Test
    public void testInsert(){
        User user = new User();
        user.setUsername("Mnsx_x");
        user.setPassword("123123");
        Integer rows = userMapper.insert(user);
        System.out.println(rows);
    }

    @Test
    public void testSelect(){
        User user = userMapper.findByUsername("Mnsx_x");
        System.out.println(user);
    }
}
