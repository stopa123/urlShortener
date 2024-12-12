package com.mycompany.urlshortener;

public class PayLoad {

    private String sub;
    private String name;
    private String iat;

    public PayLoad() {
    }

    public PayLoad(String sub, String name, String iat) {
        this.sub = sub;
        this.name = name;
        this.iat = iat;
    }

    public String getSub() {
        return sub;
    }

    public void setSub(String sub) {
        this.sub = sub;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIat() {
        return iat;
    }

    public void setIat(String iat) {
        this.iat = iat;
    }
}
