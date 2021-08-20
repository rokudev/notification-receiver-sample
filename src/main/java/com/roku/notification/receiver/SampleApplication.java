package com.roku.notification.receiver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

/**
 * This is a sample application that shows how to receive and process notifications sent by Roku.
 * The notifications come in the form of JWT messages. This sample application shows how to verify
 * the signature of a JWT message to ensure that the message is created by Roku and no one else.
 */
@SpringBootApplication
public class SampleApplication {
	// Be sure to download JWKs only from these known, trusted Roku URLs.
	// ACTUAL_JWKS_URL has the JWKs for the actual messages.
	// TEST_JWKS_URL has the JWKs for the test messages.
	private static final String ACTUAL_JWKS_URL = "https://assets.cs.roku.com/keys/partner-jwks.json";
	private static final String TEST_JWKS_URL = "https://assets.cs.roku.com/keys/partner-jwks-test.json";

	public static void main(String[] args) {
		SpringApplication.run(SampleApplication.class, args);
	}

	@Bean("actualJwtMessageService")
	public JwtMessageService actualJwtMessageService() {
		JwkProvider jwkProvider = new JwkProvider(ACTUAL_JWKS_URL);
		return new JwtMessageService(new JwtMessageVerifier(jwkProvider), new MessageProcessor());
	}

	@Bean("testJwtMessageService")
	public JwtMessageService testJwtMessageService() {
		JwkProvider jwkProvider = new JwkProvider(TEST_JWKS_URL);
		return new JwtMessageService(new JwtMessageVerifier(jwkProvider), new MessageProcessor());
	}
}
