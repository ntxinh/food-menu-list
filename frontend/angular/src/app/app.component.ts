import {Component, OnInit} from '@angular/core';

import {JqueryHelperService} from "./services/helpers/jquery.helper";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {

  title = 'app';

  ngOnInit(): void {
    this.jqueryHelperService.activeDatePicker();
    this.jqueryHelperService.activeFlexSlider();
  }

  constructor(private jqueryHelperService: JqueryHelperService) {
  }
}
