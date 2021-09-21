import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-demo',
  templateUrl: './demo.component.html',
  styleUrls: ['./demo.component.css']
})
export class DemoComponent implements OnInit {

  constructor() { }
  navitems = [{
    text: "Home",
    link: "home"
  },
  {
    text: "Login",
    link: "login"
  },
  {
    text: "Customer Dashboard",
    link: "dashboard"
  },
  {
    text: "Profile",
    link: "profile"
  },
  {
    text: "Transfer Money",
    link: "transfer-money"
  },
  {
    text: "Transactions",
    link: "transactions"
  },
  {
    text: "Logout",
    link: "logout"
  }]
  ngOnInit(): void {
  }

  
  clickMethod(name: string) {
    if(confirm("Are you sure to delete "+name)) {
      console.log("Implement delete functionality here");
    }
  }
}
