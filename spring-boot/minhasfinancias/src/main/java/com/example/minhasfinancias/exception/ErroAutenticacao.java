package com.example.minhasfinancias.exception;

public class ErroAutenticacao  extends  RuntimeException{
    
    public ErroAutenticacao(String mensagem) {
        super(mensagem);
    }
}
