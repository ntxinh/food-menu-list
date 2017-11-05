import {Injectable} from '@angular/core';

declare let $: any;
declare let gnMenu: any;
declare let screenfull: any;

@Injectable()
export class JqueryHelperService {

  init() {
    new gnMenu( document.getElementById( 'gn-menu' ) );

    $('#supported').text('Supported/allowed: ' + !!screenfull.enabled);
    
    if (!screenfull.enabled) {
        return false;
    }

    $('#toggle').click(function () {
        screenfull.toggle($('#container')[0]);
    });
  }

}
