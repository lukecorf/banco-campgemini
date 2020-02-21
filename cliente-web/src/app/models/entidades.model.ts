/**
 * Entidade que representa o Usuario
 */
export class Usuario {

  public id  ?: number ;
  public nome  ?: string ;
  public cpf  ?: string ;
  public idade  ?: number ;
  public sexo  ?: EnumSexo ;

  constructor() {
  }

}

/**
 * Entidade que representa a conta
 */
export class Conta {
  public id ?: number;
  public saldo ?: number;
  public proprietario ?: Usuario;
  constructor() {
    this.proprietario = new Usuario();
  }

}

/**
 * Entidade que representa o Extrato
 */
export class Extrato{
  public id?: number;
  public valor?: number;
  public operacao?: EnumOperacao;

  constructor() {
  }
}

/**
 * Enum com os possiveis tipos de Sexo
 */
export enum EnumSexo{
  FEMININO = 0,
  MASCULINO = 1
}

/**
 * Enum com os possivei stipos de Operacao
 */
export enum EnumOperacao{
  DEPOSITO = 0,
  SAQUE = 1
}
