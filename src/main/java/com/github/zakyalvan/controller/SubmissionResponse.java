package com.github.zakyalvan.controller;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

public class SubmissionResponse implements Serializable {
        private final boolean containsErrors;
        private final List<ErrorDescriptor> errorDescriptors;

        public SubmissionResponse() {
            this.containsErrors = false;
            this.errorDescriptors = Collections.emptyList();
        }
        public SubmissionResponse(List<ErrorDescriptor> errorDescriptors) {
            this.containsErrors = true;
            this.errorDescriptors = errorDescriptors;
        }

        public boolean isContainsErrors() {
            return containsErrors;
        }

        public List<ErrorDescriptor> getErrorDescriptors() {
            return errorDescriptors;
        }
    }