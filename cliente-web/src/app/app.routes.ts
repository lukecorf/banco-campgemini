
import {NgModule} from "@angular/core";
import {Routes, RouterModule} from "@angular/router";
import {UsuarioComponent} from "./usuario/usuario.component";
import {UsuarioFormComponent} from "./usuario/usuario-form/usuario-form.component";
import {ContaComponent} from "./usuario/conta/conta.component";
const appRoutes: Routes = [
  {path:'', component: UsuarioComponent},
  {path:'editar-usuario/:id', component: UsuarioFormComponent},
  {path:'inserir-usuario', component: UsuarioFormComponent},
  {path:'conta-usuario/:id', component: ContaComponent},
];

@NgModule({
  imports: [
    RouterModule.forRoot(appRoutes)
  ],
  //Exporta as rotas que existem nesse modulo para que outros modulos possam usa-las
  exports: [RouterModule]
})

export class  AppRoutingModule{

}
