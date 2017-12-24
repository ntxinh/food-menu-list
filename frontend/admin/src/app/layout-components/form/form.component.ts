import {Component, OnInit, ViewChild} from '@angular/core';
import {Http, RequestOptions, Response} from '@angular/http';

import 'rxjs/add/operator/map';
import {environment} from '../../../environments/environment';

@Component({
  selector: 'app-form',
  templateUrl: './form.component.html'
})
export class FormComponent implements OnInit {

  public items: any = [];
  public item: any = {
    name: "",
    description: "",
    imageUrl: ""
  };

  @ViewChild('file')
  file_view_child: any;

  ngOnInit(): void {
    this.findAll();
  }

  constructor(private http: Http) {
  }

  public getFileList(): FileList {
    return this.file_view_child.nativeElement.files;
  }

  findAll(): void {
    this.http.get(`${environment.apiUrl}/api/user/items`)
      .map((response: Response) => response.json())
      .subscribe(
        (success: any) => {
          this.items = success;
        },
        (error: any) => {
        }
      );
  }

  upload(): void {
    if (this.getFileList().length > 0) {
      let formData: FormData = new FormData();
      formData.append("name", this.item.name);
      formData.append("description", this.item.description);
      formData.append("file", this.getFileList()[0]);

      this.http.post(`${environment.apiUrl}/api/user/items/upload/0`, formData)
        .map((response: Response) => response.json())
        .subscribe(
          (success: any) => {
            this.findAll();
          },
          (error: any) => {
          }
        );
    }
  }

  login(): void {
    let user = {
      "username": "admin",
      "password": "password"
    };
    this.http.post(`${environment.apiUrl}/login`, user)
      .subscribe(
        (success: Response) => {
          let authheader = success.headers.get('Authorization');
          console.log(authheader);
          console.log(success);
        },
        (error: Response) => {
          console.log(error);
        }
      );
  }

}
