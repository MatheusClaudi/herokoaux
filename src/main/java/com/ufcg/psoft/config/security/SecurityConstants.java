package com.ufcg.psoft.config.security;

public class SecurityConstants {
    public static final String AUTH_LOGIN_URL = "/auth/login";

    public static final String JWT_SECRET = "n2r5u8x/A%D" +
            "*G-KaPdSgVkYp3" +
            "s6v9y$B&E(H+MbQeThWmZq4t7w!z%C*F-J@NcRf";

    public static final String TOKEN_HEADER = "Authorization";
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String TOKEN_TYPE = "JWT";
    public static final String TOKEN_ISSUER = "secure-api";
    public static final String TOKEN_AUDIENCE = "secure-app";

    private SecurityConstants() {
        throw new IllegalStateException
                ("Cannot create instance of static util class");
    }
}


/*
public class SecurityConstants {
   
    static final String SECRET = "DevDojoFoda";
    static final String TOKEN_PREFIX = "Bearer ";
    static final String HEADER_STRING = "Authorization";
    static final String SIGN_UP_URL = "/users/sign-up";
    static final long EXPIRATION_TIME = 86400000L;
}

*/