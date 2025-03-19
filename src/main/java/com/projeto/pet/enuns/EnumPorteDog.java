package com.projeto.pet.enuns;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum EnumPorteDog {

    PEQUENO ("P", "Pequeno"),
    MEDIO ("M", "Medio"),
    GRANDE ("G", "Grande");

    private String codigo;
    private String descricao;

     private EnumPorteDog(String codigo, String descricao){
         this.codigo = codigo;
         this.descricao = descricao;
     }

     @JsonValue
    public String getCodigo(){ return codigo;}

    public void setCodigo(String codigo){this.codigo = codigo;}

    public String getDescricao() { return descricao; }

    public void setDescricao(String descricao) {this.descricao = descricao;}

     @JsonCreator
     public static EnumPorteDog valor(String codigo){
        if(codigo.equals("P") || codigo.equals("Pequeno")){
           return PEQUENO;
        }else if(codigo.equals("M") || codigo.equals("Medio")) {
            return MEDIO;
        }else if(codigo.equals("G") || codigo.equals("Grande")){
            return GRANDE;
        }else{
            return null;
        }
     }
}
