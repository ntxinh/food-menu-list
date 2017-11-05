import {Injectable} from '@angular/core';

declare let $: any;

@Injectable()
export class JqueryHelperService {

  public activeFlexSlider() {
    $('.flexslider').flexslider({
      animation: "slide",
      controlsContainer: $(".custom-controls-container"),
      customDirectionNav: $(".custom-navigation a")
    });
  }

  public activeDatePicker() {
    $( "#datepicker" ).datepicker();
  }

}
