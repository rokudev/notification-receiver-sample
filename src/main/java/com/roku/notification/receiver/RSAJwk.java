package com.roku.notification.receiver;

import com.auth0.jwk.Jwk;

import java.util.HashMap;
import java.util.Map;

public class RSAJwk {
    private static final String KTY = "RSA";
    private static final String ALG = "RS256";

    private String kty = KTY;
    private String alg = ALG;
    private String kid;
    private String n;
    private String e;

    public Jwk toJwk() {
        Map<String, Object> jwkMap = new HashMap<>();
        jwkMap.put("kty", kty);
        jwkMap.put("alg", alg);
        jwkMap.put("kid", kid);
        jwkMap.put("n", n);
        jwkMap.put("e", e);
        return Jwk.fromValues(jwkMap);
    }

    public String getKid() {
        return kid;
    }

    public void setKty(String kty) {
        this.kty = kty;
    }

    public void setAlg(String alg) {
        this.alg = alg;
    }

    public void setKid(String kid) {
        this.kid = kid;
    }

    public void setN(String n) {
        this.n = n;
    }

    public void setE(String e) {
        this.e = e;
    }
}
