import {Injectable} from '@angular/core';

import * as FileSaver from "file-saver";

@Injectable()
export class FileHelperService {

  constructor() {

  }

  /* DOWNLOAD */
  public downloadFile(content, file_name, media_type): void {
    let blob = new Blob([content], {type: media_type});
    FileSaver.saveAs(blob, file_name);
  }

  /* UPLOAD */
  public createFormData(data: any, files: FileList): FormData {

    let formData: FormData = new FormData();

    // Add data to FormData
    for (let prop in data) {
      if (data.hasOwnProperty(prop)) {
        formData.append(prop, data[prop]);
      }
    }

    // Add file to FormData
    for (let i = 0; i < files.length; i++) {
      let file: File = files[i];
      formData.append(`files[]`, file, file.name);
    }

    return formData;
  }

  public createDownloadUrl(id: number): string {
    return `${window.location.origin}/files/download/${id}`;
  }

  public convertFileListToArray(file_list: FileList): any[] {
    let data: any[] = [];

    if (typeof file_list !== "undefined" && file_list != null && file_list.length > 0) {
      for (let i = 0; i < file_list.length; i++) {
        let file: File = file_list[i];
        data.push({
          name: file.name,
          size: file.size,
          type: file.type
        });
      }
    }

    return data;
  }
}
