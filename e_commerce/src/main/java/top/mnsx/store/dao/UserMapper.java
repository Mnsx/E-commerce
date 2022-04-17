package top.mnsx.store.dao;

import org.apache.ibatis.annotations.Param;
import top.mnsx.store.entity.User;

import java.util.Date;

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

   /**
    * 根据用户的uid来修改密码
    * @param uid 用户的id
    * @param password 用户输入的新密码
    * @param modifiedUser 表示修改的执行者
    * @param modifiedTime 表示修改的执行时间
    * @return 返回值手影响的行数
    */
   Integer updatePasswordByUid(Integer uid, String password, String modifiedUser, Date modifiedTime);

   /**
    * 根据用户的id查询，用户的数据
    * @param uid 用户的id
    * @return 如果找到则返回对象，反之返回null
    */
   User findByUid(Integer uid);

   /**
    * 更新用户的数据信息
    * @param user 用户的数据
    * @return 返回值为受影响的行数
    */
   Integer updateInfoByUid(User user);

   Integer updateAvatarByUid(@Param("uid") Integer uid, @Param("avatar") String avatar, @Param("modifiedUser") String modifiedUser, @Param("modifiedTime") Date modifiedTime);
}
