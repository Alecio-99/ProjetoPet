package com.projeto.pet.enuns;

public enum Status {

    ATIVO("ativo"),
    INATIVO("inativo");

    private String status;

    Status(String status){
        this.status = status;
    }
    public String getStatus(){

        return status;
    }

}
