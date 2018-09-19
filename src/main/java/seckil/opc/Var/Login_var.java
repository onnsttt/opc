package seckil.opc.Var;

import org.hibernate.validator.constraints.Length;
import seckil.opc.validator.IsMobile;

import javax.validation.constraints.NotNull;

public class Login_var {

    @NotNull
    @IsMobile
    private String mobile;

    @NotNull
    @Length(min=32)
    private String password;

    public String getMobile() {
        return mobile;
    }

    @Override
    public String toString() {
        return "Login_var{" +
                "mobile='" + mobile + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
