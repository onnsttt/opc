package seckil.opc.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.util.StringUtils;
import seckil.opc.Result.CodeMsg;
import seckil.opc.Var.Login_var;
import seckil.opc.dao.UserDao;
import seckil.opc.dodao.User;
import seckil.opc.exception.GlobleException;
import seckil.opc.redis.RedisService;
import seckil.opc.redis.UserKey;
import seckil.opc.util.MD5Util;
import seckil.opc.util.UUIDUtil;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

@Service
public class UserService {


    public static final String COOKI_NAME_TOKEN="token";

    @Autowired
    UserDao userDao;

    @Autowired
    RedisService redisService;

    public User getById(long id){
        return userDao.getById(id);
    }

    public boolean login(HttpServletResponse response,Login_var login_var) {
        if(login_var==null)
            throw new GlobleException(CodeMsg.SERVER_ERROR);
        String mobile = login_var.getMobile();
        String formPass = login_var.getPassword();
        User user = getById(Long.parseLong(mobile));
        if(user==null){
            throw new GlobleException(CodeMsg.MOBILE_NOT_EXIST);
        }
        String dbPass = user.getPassword();
        String saltDB = user.getSalt();
        String calcPass = MD5Util.FormPassToDBPass(formPass,saltDB);
        System.out.println(calcPass);
        if(!calcPass.equals(dbPass)){
           throw new GlobleException(CodeMsg.PASSWORD_ERROR);
        }
        addCookie(user,response);
        return true;
    }

    public User getByToken(HttpServletResponse response,String token) {
        if(StringUtils.isEmpty(token))
            return null;
        User user =redisService.get(UserKey.token,token,User.class);
        if(user!=null)
            addCookie(user,response);
        return user;
    }

    private void addCookie(User user,HttpServletResponse response){
        String token = UUIDUtil.uuid();
        redisService.set(UserKey.token,token,user);
        Cookie cookie = new Cookie(COOKI_NAME_TOKEN,token);
        cookie.setMaxAge(UserKey.token.expireSeconds());
        cookie.setPath("/");
        response.addCookie(cookie);
    }
}
