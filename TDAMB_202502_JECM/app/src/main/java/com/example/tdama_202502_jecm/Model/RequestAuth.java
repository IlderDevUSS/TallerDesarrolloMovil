package com.example.tdama_202502_jecm.Model;

public class RequestAuth {
    private String username;
    private String password;

    public RequestAuth(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
