import {Component, OnInit} from '@angular/core';
import {Http, Response} from '@angular/http';

import 'rxjs/add/operator/map';

@Component({
  selector: 'app-menu',
  templateUrl: './menu.component.html'
})
export class MenuComponent implements OnInit {

  private url: string = 'http://api-dot-foodmenulist.appspot.com';
  public items: any[];

  ngOnInit(): void {
  }

  constructor(private http: Http) {
    this.findByAll();
  }

  findByAll() {
    this.http.get(`${this.url}/api/items`)
      .map((response: Response) =>response.json())
      .subscribe(
        (success: any) => {
          this.items = success;
        },
        (error: any) => {
          console.log(error);
        }
      );
  }

}
