package com.deutschebank.trading;

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

}
