import {Component, OnInit} from '@angular/core';

import {JqueryHelperService} from './services/helpers/jquery.helper';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html'
})
export class AppComponent implements OnInit {

  title = 'app';

  ngOnInit(): void {
    this.jqueryHelperService.init();
  }

  constructor(private jqueryHelperService: JqueryHelperService) {
  }

}
