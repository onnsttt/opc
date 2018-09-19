package seckil.opc.controller;

import com.sun.org.apache.regexp.internal.RE;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.thymeleaf.util.StringUtils;
import org.thymeleaf.util.Validate;
import seckil.opc.Result.CodeMsg;
import seckil.opc.Result.Result;
import seckil.opc.Var.Login_var;
import seckil.opc.service.UserService;
import seckil.opc.util.ValidatorUtil;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;


@Controller
@RequestMapping("/login")
public class LoginController {

    private static Logger logger = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    UserService userService;

    @RequestMapping("/to_login")
    public String toLogin(){
        return "login";
    }

    @RequestMapping("/do_login")
    @ResponseBody
    public Result<Boolean> doLogin(HttpServletResponse response, @Valid Login_var login_var){
        logger.info(login_var.toString());
        userService.login(response,login_var);
        return Result.success(true);
    }
}
