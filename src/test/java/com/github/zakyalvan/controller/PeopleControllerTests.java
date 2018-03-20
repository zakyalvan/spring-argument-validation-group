package com.github.zakyalvan.controller;

import com.github.zakyalvan.DemoApplication;
import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.web.util.UriComponentsBuilder;
import org.testng.annotations.Test;

import java.net.URI;

/**
 * @author zakyalvan
 */
@SpringBootTest(classes = DemoApplication.class,
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PeopleControllerTests extends AbstractTestNGSpringContextTests {
    @Autowired
    private RestTemplateBuilder restTemplates;

    @LocalServerPort
    private Integer boundPort;

    @Test
    public void givenValidBasicInfo_whenSubmitToBasiInfoEndpoint_thenMustNoError() {
        String body = "{\"fullName\": \"Muhammad Zaky Alvan\", \"gender\": \"MALE\"}";

        URI endpointUri = UriComponentsBuilder.newInstance()
                .scheme("http")
                .host("localhost")
                .port(boundPort)
                .path("/people/only-basic-info")
                .build()
                .toUri();


        RequestEntity<String> request = RequestEntity.method(HttpMethod.POST, endpointUri)
                .contentType(MediaType.APPLICATION_JSON)
                .body(body);

        ResponseEntity<SubmissionResponse> response = restTemplates.build().exchange(request, SubmissionResponse.class);
        MatcherAssert.assertThat(response.getBody().isContainsErrors(), CoreMatchers.is(false));
        MatcherAssert.assertThat(response.getBody().getErrorDescriptors(), Matchers.hasSize(0));
    }

    @Test
    public void givenValidPersonWithBirthInfo_whenSubmitToBirthDateRequiredEndpoint_thenMustNoError() {
        String body = "{\"fullName\": \"Muhammad Zaky Alvan\", \"gender\": \"MALE\", \"birthPlace\": \"Bima\", \"birthDate\": \"1985-06-18\"}";

        URI endpointUri = UriComponentsBuilder.newInstance()
                .scheme("http")
                .host("localhost")
                .port(boundPort)
                .path("/people/birth-info-required")
                .build()
                .toUri();

        RequestEntity<String> request = RequestEntity.method(HttpMethod.POST, endpointUri)
                .contentType(MediaType.APPLICATION_JSON)
                .body(body);

        ResponseEntity<SubmissionResponse> response = restTemplates.build().exchange(request, SubmissionResponse.class);
        MatcherAssert.assertThat(response.getBody().isContainsErrors(), CoreMatchers.is(false));
        MatcherAssert.assertThat(response.getBody().getErrorDescriptors(), Matchers.hasSize(0));
    }

    @Test
    public void givenValidBasicInfo_whenSubmitToBirthDateRequiredEndpoint_thenReturnError() {
        String body = "{\"fullName\": \"Muhammad Zaky Alvan\", \"gender\": \"MALE\"}";

        URI endpointUri = UriComponentsBuilder.newInstance()
                .scheme("http")
                .host("localhost")
                .port(boundPort)
                .path("/people/birth-info-required")
                .build()
                .toUri();

        RequestEntity<String> request = RequestEntity.method(HttpMethod.POST, endpointUri)
                .contentType(MediaType.APPLICATION_JSON)
                .body(body);

        ResponseEntity<SubmissionResponse> response = restTemplates.build().exchange(request, SubmissionResponse.class);
        MatcherAssert.assertThat(response.getBody().isContainsErrors(), CoreMatchers.is(true));
        MatcherAssert.assertThat(response.getBody().getErrorDescriptors(), Matchers.hasSize(2));
    }


    @Test
    public void givenValidFullPersonInfo_whenSubmitFullEndpoint_thenMustNoError() {
        String body = "{\"fullName\": \"Muhammad Zaky Alvan\", \"gender\": \"MALE\", \"birthPlace\": \"Bima\", \"birthDate\": \"1985-06-18\", \"emailAddress\": \"zakyalvan@gmail.com\"}";

        URI endpointUri = UriComponentsBuilder.newInstance()
                .scheme("http")
                .host("localhost")
                .port(boundPort)
                .path("/people/person-full-details")
                .build()
                .toUri();

        RequestEntity<String> request = RequestEntity.method(HttpMethod.POST, endpointUri)
                .contentType(MediaType.APPLICATION_JSON)
                .body(body);

        ResponseEntity<SubmissionResponse> response = restTemplates.build().exchange(request, SubmissionResponse.class);
        MatcherAssert.assertThat(response.getBody().isContainsErrors(), CoreMatchers.is(false));
        MatcherAssert.assertThat(response.getBody().getErrorDescriptors(), Matchers.hasSize(0));
    }
}
