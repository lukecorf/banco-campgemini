import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';


import {AppComponent} from './app.component';
import {UsuarioComponent} from './usuario/usuario.component';
import {AppRoutingModule} from "./app.routes";
import {BlockUIModule} from "ng-block-ui";
import {CommonModule} from "@angular/common";
import {FormsModule} from "@angular/forms";
import {
  AccordionModule,
  ButtonModule,
  DataTableModule,
  DialogModule,
  FieldsetModule,
  InputTextModule,
  RadioButtonModule
} from "primeng/primeng";
import {HttpClientModule} from "@angular/common/http";
import {AppService} from "./app.service";
import {UsuarioFormComponent} from "./usuario/usuario-form/usuario-form.component";
import {BrowserAnimationsModule} from "@angular/platform-browser/animations";
import {ToastrModule} from "ngx-toastr";
import {ContaComponent} from "./usuario/conta/conta.component";


@NgModule({
  declarations: [
    AppComponent,
    UsuarioComponent,
    UsuarioFormComponent,
    ContaComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BlockUIModule.forRoot(),
    CommonModule,
    FormsModule,
    DataTableModule,
    HttpClientModule,
    ButtonModule,
    DialogModule,
    InputTextModule,
    RadioButtonModule,
    FieldsetModule,
    BrowserAnimationsModule,
    ToastrModule.forRoot(),
    AccordionModule

  ],
  providers: [AppService],
  bootstrap: [AppComponent]
})
export class AppModule { }
