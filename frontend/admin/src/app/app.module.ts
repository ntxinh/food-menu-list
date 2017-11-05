import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';
import {HttpModule} from '@angular/http';

import {AppComponent} from './app.component';
import {NavComponent} from './layout-components/nav/nav.component';
import {FormComponent} from './layout-components/form/form.component';
import {BreadcrumbComponent} from './layout-components/breadcrumb/breadcrumb.component';
import {SocialMediaComponent} from './layout-components/social-media/social-media.component';

import {JqueryHelperService} from "./services/helpers/jquery.helper";

@NgModule({
  declarations: [
    AppComponent,
    NavComponent,
    FormComponent,
    BreadcrumbComponent,
    SocialMediaComponent
  ],
  imports: [
    BrowserModule,
    HttpModule
  ],
  providers: [
    JqueryHelperService
  ],
  bootstrap: [AppComponent]
})
export class AppModule {
}
