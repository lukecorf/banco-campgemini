import {Component, OnInit, ViewChild} from '@angular/core';
import {AppService} from "../app.service";
import {BlockUI, NgBlockUI} from "ng-block-ui";
import {Usuario} from "../models/entidades.model";
import {UsuarioFormComponent} from "./usuario-form/usuario-form.component";
import {Router} from "@angular/router";

@Component({
  selector: 'app-home',
  templateUrl: './usuario.component.html'
})
export class UsuarioComponent implements OnInit {

  @BlockUI() blockUI: NgBlockUI;

  @ViewChild("usuarioFormComponent") usuarioFormComponent : UsuarioFormComponent;

  usuarios: Usuario[] = [];

  usuarioSelecionado: Usuario;

  /**
   * Construtor do componente
   *
   * @param servico Servico de comunicacao com o backend
   * @param router  Utilizado para navegar entre as rotas
   */
  constructor(private servico: AppService,
              private router: Router) { }

  ngOnInit() {
      this.blockUI.start("Carregando Tela...");
      this.servico.buscarUsuarios().subscribe(res =>{
            this.usuarios = res;
            this.blockUI.stop();
      })
  }

  /**
   * Redireciona para a tela de novo usuario
   */
  novo(){
      this.router.navigate(['/inserir-usuario']);
  }

  /**
   * Redireciona para a tela de editar usuario
   */
  editar(){
      this.router.navigate(['/editar-usuario',this.usuarioSelecionado.id]);
  }

  /**
   * Redireciona para a tela de Conta do usuario
   */
  conta(){
     this.router.navigate(['/conta-usuario',this.usuarioSelecionado.id]);
  }

}
