package top.mnsx.store.controller;

import org.apache.ibatis.annotations.Insert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import top.mnsx.store.controller.ex.*;
import top.mnsx.store.entity.User;
import top.mnsx.store.service.IUserService;
import top.mnsx.store.service.ex.InsertException;
import top.mnsx.store.service.ex.UsernameDuplicatedException;
import top.mnsx.store.util.JsonResult;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("users")
public class UserController extends BaseController{
    @Autowired
    private IUserService userService;

    /**
     * 注册功能
     * @param user 用户数据
     * @return 如果成功将200状态码返回，出现问题，将问题返回
     */
    @RequestMapping("register")
    public JsonResult<Void> reg(User user) {
        // 创建响应结果对象
        userService.register(user);
        return new JsonResult<>(OK);
    }

    /**
     *
     * @param username 用户名
     * @param password 用户密码
     * @return 如果成功将200状态码返回，出现问题，将问题返回
     */
    @RequestMapping("login")
    public JsonResult<User> login(String username, String password, HttpSession session) {
        User data = userService.login(username, password);

        session.setAttribute("uid", data.getUid());
        session.setAttribute("username", data.getUsername());

        return new JsonResult<User>(OK, data);
    }

    @RequestMapping("change_password")
    public JsonResult<Void> changePassword(String oldPassword, String newPassword, HttpSession session) {
        Integer uid = getuidFromSession(session);
        String username = getUsernameFromSession(session);
        userService.changePassword(uid, username, oldPassword, newPassword);
        return new JsonResult<>(OK);
    }

    @RequestMapping("get_by_uid")
    public JsonResult<User> getByUid(HttpSession session) {
        User data = userService.getByUid(getuidFromSession(session));
        return new JsonResult<>(OK, data);
    }

    @RequestMapping("change_info")
    public JsonResult<Void> changeInfo(User user, HttpSession session) {
        Integer uid = getuidFromSession(session);
        String username = getUsernameFromSession(session);
        userService.changeInfo(uid, username, user);
        return new JsonResult<>(OK);
    }

    /** 设置上传文件的最大值 */
    public static final int AVATAE_MAX_SIZE = 10 * 1024 * 1024;

    /**
     * 限制上传文件的类型
     */
    public static final List<String> AVATAR_TYPE = new ArrayList<>();

    static {
        AVATAR_TYPE.add("image/jpeg");
        AVATAR_TYPE.add("image/png");
        AVATAR_TYPE.add("image/gif");
        AVATAR_TYPE.add("image/bmp");
    }

    @RequestMapping("change_avatar")
    public JsonResult<String> changeAvatar(HttpSession session, @RequestParam("file") MultipartFile multipartFile) {
        if(multipartFile.isEmpty()) {
            throw new FileEmptyException("文件为空");
        }
        if(multipartFile.getSize() > AVATAE_MAX_SIZE) {
            throw new FileSizeException("文件长度超过限制");
        }
        String contentType = multipartFile.getContentType();
        if(!AVATAR_TYPE.contains(contentType)) {
            throw new FileTypeException("文件类型不支持");
        }
        String parent = session.getServletContext().getRealPath("upload");
        File dir = new File(parent);
        if(!dir.exists()){
            dir.mkdirs();
        }
        String originalFileName = multipartFile.getOriginalFilename();
        String suffix = originalFileName.substring(originalFileName.lastIndexOf("."));
        String fileName = UUID.randomUUID().toString().toUpperCase() + suffix;
        File file = new File(dir, fileName);
        if(!file.exists()){
            file.mkdir();
        }
        try {
            multipartFile.transferTo(file);
        }catch (IOException e){
            throw new FileUploadIOException("文件读写异常");
        }catch (FileStateException e) {
            throw new FileStateException("文件状态异常");
        }

        Integer uid = getuidFromSession(session);
        String username = getUsernameFromSession(session);
        String avatar = "/upload" + File.separator + fileName;
        userService.changeAvatar(uid, avatar, username);
        // 返回用户头像给前端页面用户头像展示使用
        return new JsonResult<>(OK, avatar);
    }
}
