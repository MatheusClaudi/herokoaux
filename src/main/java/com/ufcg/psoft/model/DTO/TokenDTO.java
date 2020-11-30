package com.ufcg.psoft.model.DTO;

import java.io.Serializable;

import javax.persistence.MappedSuperclass;

@MappedSuperclass
public class TokenDTO implements Serializable {

    private String token;

    public TokenDTO(){}

    public TokenDTO(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
