package br.com.douglasog87.error;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ValidationError extends Error {
    private String field;
    private String fieldMessage;
}