package com.invillia.feign.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Todo {

    private String id;

    @JsonProperty("userId")
    private Long customerId;

    private String title;

    private Boolean completed;

}
