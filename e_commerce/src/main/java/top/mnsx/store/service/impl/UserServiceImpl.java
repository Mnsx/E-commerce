package top.mnsx.store.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
import top.mnsx.store.dao.UserMapper;
import top.mnsx.store.entity.User;
import top.mnsx.store.service.IUserService;
import top.mnsx.store.service.ex.*;

import java.util.Date;
import java.util.UUID;

/**
 * 用户模块业务层的实现类
 */
@Service
public class UserServiceImpl implements IUserService {
    @Autowired
    private UserMapper userMapper;

    /**
     *
     * @param user 用户数据对象
     */
    @Override
    public void register(User user) {
        // 通过用户名，如果重复抛出异常
        String username = user.getUsername();
        User result = userMapper.findByUsername(username);
        if(result != null) {
            throw new UsernameDuplicatedException("用户名被占用");
        }

        // 密码加密md5
        String oldPassword = user.getPassword();
        //获取盐值——随机生成盐值
        String salt = UUID.randomUUID().toString().toUpperCase();
        //密码加颜值加密处理
        String md5Password = getMD5Password(oldPassword, salt);
        //将数据存放到用户类中
        user.setSalt(salt);
        user.setPassword(md5Password);
        //设置逻辑删除
        user.setIsDelete(0);
        //设值BaseEntity的数据
        user.setCreatedUser(user.getUsername());
        user.setModifiedUser(user.getUsername());
        Date date = new Date();
        user.setCreatedTime(date);
        user.setModifiedTime(date);
        //调用insert方法插入
        Integer rows = userMapper.insert(user);
        //如果行数不为1，那么抛出异常
        if(rows != 1) {
            throw new InsertException("在用户注册过程中产生了未知的异常");
        }
    }

    @Override
    public User login(String username, String password) {
        User result = userMapper.findByUsername(username);

        if(result == null){
            throw new UserNotFoundException("用户数据不存在");
        }

        String oldPassword = result.getPassword();

        String salt = result.getSalt();

        String newMd5Password = getMD5Password(password, salt);

        if(!newMd5Password.equals(oldPassword)){
            throw new PasswordNotMatchException("用户密码错误");
        }

        if(result.getIsDelete() == 1){
            throw new UserNotFoundException("用户数据不存在");
        }

        //提升效率
        User user = new User();
        user.setUid(result.getUid());
        user.setUsername(result.getUsername());
        user.setAvatar(result.getAvatar());

        return user;
    }

    @Override
    public void changePassword(Integer uid, String username, String oldPassword, String newPassword) {
        User result = userMapper.findByUid(uid);
        if(result == null || result.getIsDelete() == 1){
            throw new UserNotFoundException("用户数据不存在");
        }
        String oldMd5Password = getMD5Password(oldPassword, result.getSalt());
        if(!result.getPassword().equals(oldMd5Password)) {
            throw new PasswordNotMatchException("密码错误");
        }
        String newMd5Password = getMD5Password(newPassword, result.getSalt());
        Integer rows = userMapper.updatePasswordByUid(uid, newMd5Password, username, new Date());
        if(rows != 1){
            throw new UpdateException("更新操作时产生了未知的异常");
        }
    }

    /**
     * 定义md5算法加密
     */
    private String getMD5Password(String password, String salt){
        for(int i = 0; i < 3; ++i){
            //md5加密算法方法调用
            password = DigestUtils.md5DigestAsHex((salt + password + salt).getBytes()).toUpperCase();
        }
        //返回加密后的密码
        return password;
    }
}
