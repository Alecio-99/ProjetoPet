package com.projeto.pet.exceptions;

public class PetException extends RuntimeException{

    public PetException (String message) {

        super(message);
    }

    public static PetException senhaAtualIncorreta() {
        return new PetException("Senha atual incorreta");
    }

    public static PetException novaSenhaNaoConfere(){
        return new PetException("As novas senhas não conferem");
    }

    public static PetException usuarioNaoEncontrado(Long id){
        return new PetException("Usuário com ID " + id + " não encontrado");
    }
}

