package com.eteration.simplebanking.model.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class BankAccountRequest {

    @NotNull(message = "Owner is not null")
    @NotBlank(message = "Owner is not blank")
    private String owner;

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

}
