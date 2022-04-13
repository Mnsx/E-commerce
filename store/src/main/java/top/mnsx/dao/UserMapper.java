package top.mnsx.dao;

import top.mnsx.entity.User;

/**
 * 用户模块的持久层接口
 */
public interface UserMapper {
   /**
    * 插入用户的数据
    * @param user 用户的数据
    * @return 受影响的行数
    */
   Integer insert(User user);

   /**
    * 根据用户名查询用户的数据
    * @param username 用户名
    * @return 如果找到对应的用户则返回这个用户的数据，如果没有找到则返回null
    */
   User findByUsername(String username);
}
