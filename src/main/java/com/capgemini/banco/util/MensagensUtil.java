package com.capgemini.banco.util;

/**
 * Util responsavel por armazenar as mensagens do sistema.
 */
public final class MensagensUtil {

    private MensagensUtil(){
        //Construtor Vazio.
    }

    public static final String ERRO_ENTIDADE_COM_ID = "Não é possivel criar uma entidade que já possui ID";
    public static final String ERRO_REGISTRO_NAO_ENCONTRADO = "Registro nao encontrado";
    public static final String ERRO_SALDO_INSUFICIENTE = "Saldo insuficiente";

}
