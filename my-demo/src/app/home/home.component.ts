import { Component, OnInit } from '@angular/core';
import { DataService } from '../services/data.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  customer:any;
  overdraft:string;
  constructor(private dataService:DataService) { 

    this.customer = this.dataService.customer;
    this.overdraft = (this.customer.overdraftflag == 1) ? "Yes":"No";
  }

  ngOnInit(): void {
  }

}
