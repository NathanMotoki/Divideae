package com.divideae.divideae.domain.user;

public enum ChavePixType {
    CPF("cpf"),
    EMAIL("email"),
    TELEFONE("telefone"),
    ALEATORIA("aleatoria");

    private String chavePix;

    ChavePixType(String chavePix){
        this.chavePix = chavePix;
    }

    public String getChavePix(){
        return chavePix;
    }
}
