import {Component, OnInit} from "@angular/core";
import {BlockUI, NgBlockUI} from "ng-block-ui";
import {AppService} from "../../app.service";
import {ActivatedRoute, Router} from "@angular/router";
import {Conta, Extrato} from "../../models/entidades.model";
import {ToastrService} from "ngx-toastr";

@Component({
  selector: 'conta-component',
  templateUrl: './conta.component.html',
  styleUrls: ['../../app.component.css']
})
export class ContaComponent implements OnInit {

  @BlockUI() blockUI: NgBlockUI;

  idUsuario: number;
  titulo = 'Conta';
  mensagem: string;
  conta: Conta = new Conta();
  valor: number;
  operacao: string;
  mostrarModal: boolean = false;
  extratoList: Extrato[] = [];

  /**
   * Construtor do componente
   *
   * @param servico Servico de comunicacao com o Backend
   * @param route Route utilizado para a consulta dos parametros
   * @param pageNotification Utilizado para exibir notificacoes na tela
   * @param router Utilizado para navegar entre as rotas
   */
  constructor(private servico: AppService,
              private route: ActivatedRoute,
              private pageNotification: ToastrService,
              private router: Router) {
  }

  /**
   * Funcao executada ao iniciar o componente. Ela e responsavel por pegar o ID do usuario e buscar a conta pertencente a ele.
   */
  ngOnInit(): void {
    this.route.params.subscribe((params) => {
      if (params['id']) {
          this.idUsuario = params['id'];
          this.blockUI.start();
          this.servico.buscarContaPorIdUsuario(this.idUsuario).subscribe(res=>{
              this.conta = res;
              this.blockUI.stop()
              this.buscarExtrato();
          }, error => {
              this.blockUI.stop();
              this.retornarListagem();
          })
      }
    })
  }

  /**
   * Abre o modal para realizar um saque
   */
  abreModalSacar(){
      this.mensagem = 'Quanto você deseja sacar?';
      this.operacao = 'Sacar';
      this.mostrarModal = true;
  }

  /**
   * Abre o modal para realizar um deposito
   */
  abreModalDepositar(){
    this.mensagem = 'Quanto você deseja depositar?';
    this.operacao = 'Depositar';
    this.mostrarModal = true;
  }

  /**
   * Fecha o modal
   */
  fecharDialog(){
    this.mostrarModal = false;
  }

  /**
   * Executa a operacao podendo ser saque ou deposito
   */
  realizarTarefa(){
    this.fecharDialog();
    if(this.operacao ==  'Sacar'){
        if(this.valor > this.conta.saldo){
            this.pageNotification.error("Saldo insuficiente!");
            this.valor = null;
        }else{
            this.blockUI.start("Sacando dinheiro...")
            this.servico.sacar(this.conta.id,this.valor).subscribe(res=>{
                this.conta.saldo = res;
                this.valor = null;
                this.blockUI.stop();
                this.pageNotification.success("Saque realizado!");
                this.buscarExtrato();
            }, error => {
                this.pageNotification.error("Erro ao sacar!");
            })
        }
    } else{
        this.blockUI.start("Depositando dinheiro...")
        this.servico.depositar(this.conta.id,this.valor).subscribe(res=>{
            this.conta.saldo = res;
            this.valor = null;
            this.blockUI.stop();
            this.pageNotification.success("Deposito realizado!");
            this.buscarExtrato();
        }, error => {
          this.pageNotification.error("Erro ao depositar!");
        });
    }
  }

  /**
   * Retorna para a tela de listagem.
   */
  retornarListagem(){
    this.router.navigate(['']);
  }

  /**
   * Busca o extrato da conta.
   */
  buscarExtrato(){
    this.blockUI.start("Buscando extrato...")
    this.servico.buscarExtrato(this.conta.id).subscribe(res=>{
        this.extratoList = res;
        this.blockUI.stop();
    })
  }
}


