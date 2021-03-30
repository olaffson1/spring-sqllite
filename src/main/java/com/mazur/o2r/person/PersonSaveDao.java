package com.mazur.o2r.person;

import javax.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class PersonSaveDao {

    @NotBlank
    private String name;
    @NotBlank
    private String message;
}
