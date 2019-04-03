package EncJob.X509Demo;

/**
 * Created by Scott on 2019/1/6
 *
 * 用户类型，采用枚举形式实现
 * 用户类型暂时分两类，管理员和普通登录用户
 */
public enum UserAuthority {

    ADMIN("admin"),USERLOGIN("loginUser"),VISITOR("visitor");

    private String userType;
    private String authValue;
    private UserAuthority(String value){
        this.userType = value;
        if(value.equals("admin")){
            this.authValue = "you are administrator, so you can do everything";
        }else if(value.equals("loginUser")){
            this.authValue = "you are loginUser, so you can login the system.";
        }else if(value.equals("visitor")){
            this.authValue = "you can do nothing, because you are only a visitor.";
        }
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getAuthValue() {
        return authValue;
    }

    public void setAuthValue(String authValue) {
        this.authValue = authValue;
    }
}
