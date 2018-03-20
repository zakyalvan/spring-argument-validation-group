package com.github.zakyalvan.controller;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.github.zakyalvan.dto.Person;
import com.github.zakyalvan.validate.PersonBasicInfo;
import com.github.zakyalvan.validate.WithBirthInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/people")
public class PeopleController {
    private static final Logger LOGGER = LoggerFactory.getLogger(PeopleController.class);

    @PostMapping("/only-basic-info")
    public SubmissionResponse handleBasicInfo(@Validated(PersonBasicInfo.class) @RequestBody Person person, BindingResult bindings) {
        LOGGER.info("Handle basic person info submission : {}", person.toString());

        if(bindings.hasErrors()) {
            LOGGER.error("Submitted basic person data contains errors");
            List<ErrorDescriptor> errorDescriptors = bindings.getFieldErrors().stream()
                    .map(error -> new ErrorDescriptor(error.getField(), error.getDefaultMessage()))
                    .collect(Collectors.toList());
            return new SubmissionResponse(errorDescriptors);
        }

        LOGGER.info("Submitted basic person data has no errors");
        return new SubmissionResponse();
    }

    @PostMapping("/birth-info-required")
    public SubmissionResponse handleWithBirthInfo(@Validated(WithBirthInfo.class) @RequestBody Person person, BindingResult bindings) {
        LOGGER.info("Handle birth info required submission : {}", person.toString());

        if(bindings.hasErrors()) {
            LOGGER.error("Submitted birth info required data contains errors");
            List<ErrorDescriptor> errorDescriptors = bindings.getFieldErrors().stream()
                    .map(error -> new ErrorDescriptor(error.getField(), error.getDefaultMessage()))
                    .collect(Collectors.toList());
            return new SubmissionResponse(errorDescriptors);
        }

        LOGGER.info("Submitted birth info required data has no errors");
        return new SubmissionResponse();
    }

    @PostMapping("/person-full-details")
    public SubmissionResponse handleFullDetails(@Validated(WithBirthInfo.class) @RequestBody Person person, BindingResult bindings) {
        if(bindings.hasErrors()) {
            List<ErrorDescriptor> errorDescriptors = bindings.getFieldErrors().stream()
                    .map(error -> new ErrorDescriptor(error.getField(), error.getDefaultMessage()))
                    .collect(Collectors.toList());
            return new SubmissionResponse(errorDescriptors);
        }

        return new SubmissionResponse();
    }
}
