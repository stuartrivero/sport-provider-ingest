package com.stuartrivero.jsoningest

import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class JsonIngestApplicationTests(@Autowired val restTemplate: TestRestTemplate) {



    @Test
    fun `data is ingested and saved`() {

        val headers = HttpHeaders()
        headers.set("X-API-KEY", "apiKey1")

        val request = HttpEntity<String>(""""{}"""", headers)

        restTemplate.postForEntity("/ingest/golf", request, String::class.java)
    }

}
