package com.deutschebank.trading;

import com.deutschebank.trading.rest.dto.Step;
import com.deutschebank.trading.rest.dto.StepConfiguration;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient
class TradingApplicationTests {

	@Autowired
	private WebTestClient webClient;

	@Test
	public void testFirstSignal() {
		webClient.post().uri("/api/signal/1")
				.contentType(MediaType.APPLICATION_JSON)
				.exchange()
				.expectStatus().isAccepted();
	}

	@Test
	public void testSecondSignal() {
		webClient.post().uri("/api/signal/2")
				.contentType(MediaType.APPLICATION_JSON)
				.exchange()
				.expectStatus().isAccepted();
	}

	@Test
	public void testThirdSignal() {
		webClient.post().uri("/api/signal/3")
				.contentType(MediaType.APPLICATION_JSON)
				.exchange()
				.expectStatus().isAccepted();
	}

	@Test
	public void testConfiguredSignal() {
		webClient.post().uri("/api/strategy/4")
				.contentType(MediaType.APPLICATION_JSON)
				.exchange()
				.expectStatus().isAccepted();
		webClient.put().uri("/api/strategy/4")
				.contentType(MediaType.APPLICATION_JSON)
				.bodyValue(new StepConfiguration(Step.SETUP, 0, 0))
				.exchange()
				.expectStatus().isAccepted();
		webClient.post().uri("/api/signal/4")
				.contentType(MediaType.APPLICATION_JSON)
				.exchange()
				.expectStatus().isAccepted();
	}
}
