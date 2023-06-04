package com.asrevo.stcfileshare.domain;

import jakarta.persistence.Embeddable;

@Embeddable
public class Meta {
    private String contentType;
    private String contentLength;
}
