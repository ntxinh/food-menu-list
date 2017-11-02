import {Component, OnInit} from '@angular/core';
import {Http, Response} from '@angular/http';

import {JqueryHelperService} from './services/helpers/jquery.helper';

import 'rxjs/add/operator/map';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html'
})
export class AppComponent implements OnInit {

  title = 'app';

  ngOnInit(): void {
    this.jqueryHelperService.activeDatePicker();
    this.jqueryHelperService.activeFlexSlider();
  }

  constructor(private http: Http, private jqueryHelperService: JqueryHelperService) {
    this.findByAll();
  }

  findByAll() {
    return this.http.get('/api/contacts')
      .map((response: Response) =>response.json())
      .subscribe(
        (success: any) => {
          console.log('success');
        },
        (error: any) => {
          console.log('error');
        }
      );
  }

}
