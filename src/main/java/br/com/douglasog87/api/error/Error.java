package br.com.douglasog87.api.error;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Error {
    private String title;
    private int status;
    private String detail;
    private long timestamp;
    private String developerMessage;
}