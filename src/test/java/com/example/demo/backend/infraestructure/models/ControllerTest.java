package com.example.demo.backend.infraestructure.models;

import com.handyman.backend.infraestructure.models.ServiceDAO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;
import org.junit.jupiter.api.TestReporter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;

import java.net.URI;
import java.net.URISyntaxException;

import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest(webEnvironment=WebEnvironment.RANDOM_PORT)

public class ControllerTest {

    ServiceDAO serviceDAO;

    @BeforeEach
    public void initMetodoTest(TestInfo testInfo, TestReporter testReporter) {
        this.serviceDAO = new ServiceDAO(null, "9876543", "55111345", "2022-08-13 05:37","2022-08-14 05:37");
    }
    @Autowired
    private TestRestTemplate restTemplate;


    @Test
    public void testAddServiceSuccess() throws URISyntaxException {
        final String baseUrl = "http://localhost:" + 8080 + "/services/";
        URI uri = new URI(baseUrl);

        HttpHeaders headers = new HttpHeaders();
        headers.set("X-COM-PERSIST", "true");

        HttpEntity<ServiceDAO> request = new HttpEntity<>(serviceDAO, headers);

        ResponseEntity<String> result = this.restTemplate.postForEntity(uri, request, String.class);

        //Verify request succeed
        assertEquals(201, result.getStatusCodeValue());
    }
}