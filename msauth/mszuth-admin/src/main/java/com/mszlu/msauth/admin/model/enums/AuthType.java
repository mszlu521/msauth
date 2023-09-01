package com.mszlu.msauth.admin.model.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * @author 码神之路
 */
public enum AuthType {

    OAUTH2(0,"oauth2"),
    OIDC(1,"oidc"),
    SAML2(2,"saml2");

    private int code;

    private String desc;


    AuthType(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public static String codeOf(int code){
        AuthType[] values = AuthType.values();
        for (AuthType value : values) {
            if (value.code == code){
                return value.desc;
            }
        }
        return "";
    }
    public String getDesc() {
        return desc;
    }

    public int getCode() {
        return code;
    }
}
