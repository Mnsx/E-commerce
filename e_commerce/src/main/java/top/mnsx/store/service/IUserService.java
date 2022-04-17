package top.mnsx.store.service;

import top.mnsx.store.entity.User;

/**
 * 用户模块业务层接口
 */
public interface IUserService {
    /**
     * 用户注册方法
     * @param user 用户数据对象
     */
    void register(User user);

    /**
     * 登录功能
     * @param username 用户名
     * @param password 登录密码
     * @return 返回登录的用户信息
     */
    User login(String username, String password);

    /**
     * 修改密码功能
     * @param uid 用户编号
     * @param username 用户名
     * @param oldPassword 旧密码
     * @param newPassword 新密码
     */
    void changePassword(Integer uid,
                        String username,
                        String oldPassword,
                        String newPassword);

    /**
     * 根据用户的id查找用户信息
     * @param uid 用户编号
     * @return 返回用户信息
     */
    User getByUid(Integer uid);

    /**
     * 修改用户信息
     * @param uid 用户编号
     * @param username 用户名
     * @param user 用户信息
     */
    void changeInfo(Integer uid, String username, User user);

    /**
     * 修改用户的头像
     * @param uid 用户id
     * @param avatar 用户头像的路径
     * @param username 用户的名称
     */
    void changeAvatar(Integer uid, String avatar, String username);
}
