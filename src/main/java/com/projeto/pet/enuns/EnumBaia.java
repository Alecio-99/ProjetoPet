package com.projeto.pet.enuns;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum EnumBaia {

    BAIA1 ("1", "Baia 1"),
    BAIA2 ("2", "Baia 2"),
    BAIA3 ("3", "Baia 3");

    private String codigo;
    private String descricao;;

    private EnumBaia(String codigo, String descricao){
        this.codigo =  codigo;
        this.descricao = descricao;
    }
    @JsonValue
    public String getCodigo(){ return codigo;}

    public void setCodigo(String codigo){this.codigo = codigo;}

    public String getDescricao(){ return descricao;}

    public void setDescricao(String descricao){this.descricao = descricao;}

    @JsonCreator
    public static EnumBaia valor(String codigo){
      if(codigo.equals("1") || codigo.equals("Baia 1")){
          return BAIA1;
      }else if(codigo.equals("2") || codigo.equals("Baia 2")){
          return BAIA2;
      }else if(codigo.equals("3") || codigo.equals("Baia 3")){
          return BAIA3;
      }else{
          return null;
      }
    }
}
