package com.projeto.pet.enuns;

import com.fasterxml.jackson.annotation.JsonValue;

public enum EnumStatusDog {

    PENDENTE ("P", "Pendente"),
    AGENDADO ("A", "Agendado"),
    REALIZADO ("R", "Realizado"),
    CANCELADO ("C", "Cancelado"),
    EM_ANDAMENTO ("E", "Em andamento");

    private String codigo;;
    private String descricao;

    private EnumStatusDog(String codigo, String descricao){
        this.codigo = codigo;
        this.descricao = descricao;
    }

    @JsonValue
    public String getCodigo(){ return  codigo; }

    public void setCodigo(String codigo){this.codigo = codigo; }

    public String getDescricao(){ return  descricao; }

    public void setDescricao(String descricao){ this.descricao = descricao; }


    public static EnumStatusDog valor (String codigo) {

        if(codigo.equals("P")){
            return PENDENTE;
        }else if(codigo.equals("A")){
            return AGENDADO;
        }else if(codigo.equals("R")){
            return REALIZADO;
        }else if(codigo.equals("C")){
            return CANCELADO;
        }else if(codigo.equals("E")){
            return EM_ANDAMENTO;
        }else{
            return null;
        }
    }
}
