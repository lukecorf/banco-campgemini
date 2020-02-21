import {Component, OnInit} from "@angular/core";
import {BlockUI, NgBlockUI} from "ng-block-ui";
import {EnumSexo, Usuario} from "../../models/entidades.model";
import {AppService} from "../../app.service";
import {SelectItem} from "primeng/primeng";
import {ActivatedRoute, Router} from "@angular/router";
import {ToastrService} from "ngx-toastr";

@Component({
  selector: 'usuario-form-component',
  templateUrl: './usuario-form.component.html',
  styleUrls: ['../../app.component.css']
})
export class UsuarioFormComponent implements OnInit {

  @BlockUI() blockUI: NgBlockUI;

  id: number;

  usuario: Usuario = new Usuario();

  titulo: string;

  sexoLista : SelectItem[];

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
              private router: Router) { }

  /**
   * Funcao executada ao iniciar o componente. Verifica se possui um parametro ID na url o que indicaria ser uma edicao, caso contrario interpreta como uma insercao
   */
  ngOnInit(): void {
    this.sexoLista = [{value:EnumSexo.MASCULINO, label:'Masculino'},{value:EnumSexo.FEMININO, label:'Feminino'}]
    this.route.params.subscribe((params) => {
      if (params['id']) {
          this.id = params['id'];
          this.titulo = 'Editar Usuario';
          this.servico.buscarUsuarioPorId(this.id).subscribe(res=>{
              this.usuario = res;
              this.usuario.sexo = (this.usuario.sexo.toString() == 'MASCULINO') ? EnumSexo.MASCULINO : EnumSexo.FEMININO;
          }, error => {
              this.blockUI.stop();
              this.pageNotification.error("Registro não encontrado");
          })
      } else{
          this.titulo = 'Inserir Usuario';
          this.usuario.sexo = EnumSexo.MASCULINO;
      }
    })
  }

  /**
   * Verifica os campos obrigatorios e salva o usuario.
   *
   * @param form Form da tela
   */
  salvar(form) {

      form.submitted = true;

      if ( !form.valid ) {
          this.pageNotification.error("Preencher campos obrigatorios! Somente a idade não é obrigatoria");
          return;
      }

      this.blockUI.start("Salvando Usuario...");
      if(this.usuario.id){
          this.servico.atualizar(this.usuario).subscribe(res=>{
              this.subscribeSave(res);
          }, error => {
              this.blockUI.stop();
              this.pageNotification.error("Erro ao atualizar");
          })
      }else{
          this.servico.inserir(this.usuario).subscribe(res=>{
              this.subscribeSave(res);
          }, error => {
              this.blockUI.stop();
              this.pageNotification.error("Erro ao salvar");
          })
      }


  }

  /**
   * Trata o retorno do requisicao no backend.
   *
   * @param res retorno da requisicao.
   */
  subscribeSave(res){
      this.usuario = res;
      this.blockUI.stop();
      this.pageNotification.success("Registro salvo com sucesso!");
      this.retornarListagem();
  }

  /**
   * Retorna para a tela de listagem
   */
  retornarListagem(){
      this.router.navigate(['']);
  }


}
