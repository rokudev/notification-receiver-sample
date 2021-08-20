
package com.roku.notification.receiver;

import com.auth0.jwk.Jwk;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.time.Duration;

/**
 * This class downloads JWKs from an URL.
 */
public class JwkProvider {
    private final WebClient webClient = WebClient.create();
    private String jwkProviderUrl;
    private ObjectMapper objectMapper = new ObjectMapper();

    public JwkProvider(String jwkProviderUrl) {
        this.jwkProviderUrl = jwkProviderUrl;
    }

    /**
     * Return the JWK that corresponds to a key ID.
     *
     * @param kid the key ID
     * @return the JWK of the kid
     */
    public Jwk get(String kid) {
        RSAJwkSet jwkSet = getJwkSet();
        if (jwkSet == null)
            throw new RuntimeException("Failed to get JwkSet for kid: " + kid);

        RSAJwk rsaJwk = jwkSet.getJwk(kid);
        if (rsaJwk == null)
            throw new RuntimeException("Failed to get Jwk for kid: " + kid);

        return rsaJwk.toJwk();
    }

    private RSAJwkSet getJwkSet() {
        WebClient.RequestHeadersUriSpec<?> uriSpec = webClient.get();
        uriSpec.uri(jwkProviderUrl).accept(MediaType.TEXT_PLAIN);
        Mono<String> response = uriSpec.retrieve().bodyToMono(String.class);
        String jsonString = response.block(Duration.ofSeconds(10));
        try {
            return objectMapper.readValue(jsonString, RSAJwkSet.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
