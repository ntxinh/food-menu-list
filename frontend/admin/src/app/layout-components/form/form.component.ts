import {Component, OnInit, ViewChild} from '@angular/core';
import {Http, RequestOptions, Response} from '@angular/http';

import 'rxjs/add/operator/map';

@Component({
  selector: 'app-form',
  templateUrl: './form.component.html'
})
export class FormComponent implements OnInit {

  private url: string = 'http://api-dot-foodmenulist.appspot.com';

  item: any = {
    name: "",
    description: "",
    imageUrl: ""
  };

  @ViewChild('file')
  file_view_child: any;

  ngOnInit(): void {
  }

  constructor(private http: Http) {

  }

  public getFileList(): FileList {
    return this.file_view_child.nativeElement.files;
  }

  upload() {
    if (this.getFileList().length > 0) {
      let formData: FormData = new FormData();
      formData.append("name", this.item.name);
      formData.append("description", this.item.description);
      formData.append("file", this.getFileList()[0]);

      this.http.post(`${this.url}/api/items/upload/0`, formData)
        .map((response: Response) => response.json())
        .subscribe(
          (success: any) => {
            console.log(success);
          },
          (error: any) => {
            console.log(error);
          }
        );
    }
  }

}
