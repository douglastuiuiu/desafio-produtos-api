package br.com.douglasog87.error;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class Error {
    private String title;
    private int status;
    private String detail;
    private long timestamp;
    private String developerMessage;
}