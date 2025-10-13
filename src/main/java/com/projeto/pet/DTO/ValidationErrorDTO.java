package com.projeto.pet.DTO;

import java.util.List;

public record ValidationErrorDTO(List<String> errors, int status) {

}
