import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Injectable} from "@angular/core";
import {Conta, Extrato, Usuario} from "./models/entidades.model";

@Injectable()
export class AppService {

  usuarioUrl = 'http://localhost:8080/api/usuario';
  contaUrl = 'http://localhost:8080/api/conta';
  extratoUrl = 'http://localhost:8080/api/extrato';

  constructor(private http: HttpClient) {}

  /**
   * Busca os usuarios cadastrados
   */
  buscarUsuarios(): Observable<Usuario[]> {
    return this.http.get<Usuario[]>(`${this.usuarioUrl}`);
  }

  /**
   * Busca um usuario de acordo com seu ID
   *
   * @param id ID do usuario
   */
  buscarUsuarioPorId(id: number): Observable<Usuario> {
    return this.http.get<Usuario>(`${this.usuarioUrl}/${id}`);
  }

  /**
   * Salva um novo usuario
   *
   * @param usuario Usuario a ser salvo
   */
  inserir(usuario: Usuario): Observable<Usuario> {
    return this.http.post<Usuario>(`${this.usuarioUrl}`,usuario);
  }

  /**
   * Atualiza um usuario
   *
   * @param usuario Usuario a ser atualizado
   */
  atualizar(usuario: Usuario): Observable<Usuario> {
    return this.http.put<Usuario>(`${this.usuarioUrl}`,usuario);
  }

  /**
   * Busca uma conta pelo ID do usuario
   *
   * @param id ID do usuario
   */
  buscarContaPorIdUsuario(id:number):Observable<Conta>{
    return this.http.get<Usuario>(`${this.contaUrl}/${id}/por-usuario`);
  }

  /**
   * Saca uma determinada quantia de uma determinada conta
   *
   * @param id ID da conta
   * @param valor Valor sacado
   */
  sacar(id: number, valor: number): Observable<number>{
    return this.http.put<number>(`${this.contaUrl}/${id}/sacar`,valor);
  }

  /**
   * Deposita uma determinada quantia de uma determinada conta
   *
   * @param id ID da conta
   * @param valor Valor depositado
   */
  depositar(id: number, valor: number): Observable<number>{
    return this.http.put<number>(`${this.contaUrl}/${id}/depositar`,valor);
  }

  /**
   * Busca o extrato pela conta.
   *
   * @param idConta Id da conta.
   */
  buscarExtrato(idConta: number):Observable<Extrato[]>{
    return this.http.get<Extrato[]>(`${this.extratoUrl}/${idConta}/por-conta`);
  }
}
