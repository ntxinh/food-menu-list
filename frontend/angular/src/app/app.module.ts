import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppComponent } from './app.component';

import {JqueryHelperService} from "./services/helpers/jquery.helper";

@NgModule({
  declarations: [
    AppComponent
  ],
  imports: [
    BrowserModule
  ],
  providers: [
    JqueryHelperService
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
