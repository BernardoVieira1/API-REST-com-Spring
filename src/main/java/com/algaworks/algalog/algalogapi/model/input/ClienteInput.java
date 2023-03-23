package com.algaworks.algalog.algalogapi.model.input;

import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClienteInput {

    @NotNull
    private Long id;
}
