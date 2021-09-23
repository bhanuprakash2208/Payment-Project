import { Component, OnInit, ViewChild} from '@angular/core';
import { BreakpointObserver } from '@angular/cdk/layout';
import { MatSidenav } from '@angular/material/sidenav';
import { delay } from 'rxjs/operators';
import { DataService } from '../services/data.service';
import { Router } from "@angular/router";

@Component({
  selector: 'app-container',
  templateUrl: './container.component.html',
  styleUrls: ['./container.component.scss']
})
export class ContainerComponent {

  @ViewChild(MatSidenav)
  sidenav!: MatSidenav;

  name:String;
  constructor(private observer: BreakpointObserver,
              private dataService:DataService,
              private router: Router ) {

                this.name = this.dataService.customer.accountholdername;
              }

  ngAfterViewInit() {
    this.observer
      .observe(['(max-width: 800px)'])
      .pipe(delay(1))
      .subscribe((res) => {
        if (res.matches) {
          this.sidenav.mode = 'over';
          this.sidenav.close();
        } else {
          this.sidenav.mode = 'side';
          this.sidenav.open();
        }
      });
  }

  onLogout(){
    this.dataService.customer=null;
    this.dataService.isLoggedIn = false;
    this.router.navigate(['/login']);
  }

}
