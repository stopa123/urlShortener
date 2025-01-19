
package com.mycompany.urlshortener;

public class LoginResponse {

    private String token;

    private long expiresIn;

    public String getToken() {
        return token;
    }

    // Getters and setters...

    public void setToken(String token) {
        this.token = token;
    }

    public long getExpiresIn() {
        return expiresIn;
    }

    public void setExpiresIn(long expiresIn) {
        this.expiresIn = expiresIn;
    }
    
    
}
