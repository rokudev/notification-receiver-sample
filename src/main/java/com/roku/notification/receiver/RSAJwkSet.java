package com.roku.notification.receiver;

import java.util.ArrayList;
import java.util.List;

public class RSAJwkSet {
    // This field has to be named 'keys' for JSON serialization.
    private List<RSAJwk> keys = new ArrayList<>();

    public RSAJwk getJwk(String kid) {
        for (RSAJwk jwk : keys) {
            if (kid.equals(jwk.getKid())) {
                return jwk;
            }
        }
        return null;
    }

    public void setKeys(List<RSAJwk> keys) {
        this.keys = keys;
    }
}
