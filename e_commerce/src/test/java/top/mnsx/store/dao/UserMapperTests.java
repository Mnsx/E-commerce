package top.mnsx.store.dao;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import top.mnsx.store.dao.UserMapper;
import top.mnsx.store.entity.User;

import java.util.Date;

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

    @Test
    public void testUpdatePasswordByUid(){
        userMapper.updatePasswordByUid(8, "321", "管理员", new Date());
    }

    @Test
    public void testFindByUid(){
        System.out.println(userMapper.findByUid(8));
    }

    @Test
    public void testUpdateInfoByUid(){
        User user = new User();
        user.setUid(7);
        user.setPhone("110");
        user.setEmail("110@163.com");
        user.setGender(1);
        userMapper.updateInfoByUid(user);
    }

    @Test
    public void testUpdateAvatarByUid() {
        userMapper.updateAvatarByUid(7, "/upload/avatar.png", "mnsx", new Date());
    }
}
