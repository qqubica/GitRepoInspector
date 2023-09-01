package com.example.gitrepoinspector.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApiError {
    @JsonProperty(index = 0)
    int status;
    @JsonProperty(index = 1)
    String message;
}
