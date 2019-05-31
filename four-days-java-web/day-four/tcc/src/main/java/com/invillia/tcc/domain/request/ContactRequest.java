package com.invillia.tcc.domain.request;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ContactRequest {

    @NotBlank
    private String name;

    @Email
    @NotBlank
    private String email;

}
