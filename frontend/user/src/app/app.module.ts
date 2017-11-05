import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';
import {HttpModule} from '@angular/http';

import {AppComponent} from './app.component';
import {BannerComponent} from './layout-components/banner/banner.component';
import {AboutComponent} from './layout-components/about/about.component';
import {SlidComponent} from './layout-components/slid/slid.component';
import {MenuComponent} from './layout-components/menu/menu.component';
import {TeamComponent} from './layout-components/team/team.component';
import {GalleryComponent} from './layout-components/gallery/gallery.component';
import {ContactUsComponent} from './layout-components/contact-us/contact-us.component';

import {JqueryHelperService} from "./services/helpers/jquery.helper";

@NgModule({
  declarations: [
    AppComponent,
    BannerComponent,
    AboutComponent,
    SlidComponent,
    MenuComponent,
    TeamComponent,
    GalleryComponent,
    ContactUsComponent
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
