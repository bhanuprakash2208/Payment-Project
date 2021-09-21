import { Component, OnInit } from '@angular/core';
import { DataService } from '../services/data.service';
import { HttpClient, HttpHeaders } from "@angular/common/http";
@Component({
  selector: 'app-transfer',
  templateUrl: './transfer.component.html',
  styleUrls: ['./transfer.component.css']
})
export class TransferComponent implements OnInit {
  example="";
  example2="";
  selectedValue="";
  errordesc = null;
  tresult:any;
  sdnResponse:any;

  customer: any;
  transfertypes: any;
  messagecodes: any;
  
  demo: any;
  transaction: any;

  constructor(private dataService: DataService,
    private http: HttpClient) {
      this.tresult={};
      this.sdnResponse={};
    this.demo = {};
    this.customer = this.dataService.customer;
    this.transaction = {
      customerid: this.customer.customerid,
      currencycode: {
        currencycode: "INR",
        currencyname: "Indian Rupees",
        conversionrate: 1.0
      },
      senderbic: null,
      receiverBIC: {
        bic: "",
        bankname: ""
      },
      receiveraccountholdernumber: "",
      receiveraccountholdername: "",
      transfertypecode: {
        transfertypecode: "",
        transfertypedescription: ""
      },
      messagecode: {
        messagecode: "",
        instruction: ""
      },
      currencyamount: 1.0,
      transferfees: 0,
      inramount: null,
      transferdate: ""

    }
  }
  searchNameInSDN(){
    this.dataService.getReceiverDataThroughApi(this.transaction.receiveraccountholdername)
    .subscribe(response => {
      this.sdnResponse = response;
      if(this.sdnResponse.found){
        alert('You cannot transfer the money to ' + this.sdnResponse.name);
      this.transaction.receiveraccountholdername="";
      }
    })
  }

  fecthBankBIC() {
    this.dataService.getBankDataThroughApi(this.transaction.receiverBIC.bic)
      .subscribe(response => {
        this.transaction.receiverBIC = response;
      }, (error) => {
        this.transaction.receiverBIC.bic="";
        this.transaction.receiverBIC.bankname="";
      });
  }

  ngOnInit(): void {
   
    //data format to make post request
    this.dataService.getTransactionThroughApi(1)
      .subscribe(response => {
        //console.log(response);
        this.demo = response;
        console.log(this.demo.currencycode.currencyname)
      }, (error) => {
        console.log(error)
      });

    this.dataService.getTransferTypesThroughApi()
      .subscribe(response => {
        this.transfertypes = response;
      }, (error) => {
      });

    this.dataService.getMessagesThroughApi()
      .subscribe(response => {
        console.log(response);
        this.messagecodes = response;
      }, (error) => {
        console.log(error)
      });
  }

  apiResult={
    success:false,
    error:false
  }

  makeTransfer() {
    var date = new Date();
    var dd = String(date.getDate()).padStart(2, '0');
    var mm = String(date.getMonth() + 1).padStart(2, '0'); //January is 0!
    var yyyy = date.getFullYear();
    var today = yyyy + '-' + mm + '-' + dd;

    const httpOptions = {
      headers:new HttpHeaders({
          'Content-Type':'application/json',
      })
  }

    this.transaction.transferdate = today;
    this.transaction.transferfees = Math.round(this.transaction.inramount * 0.25) / 100;
    console.log("MY Trans", this.transaction);

    this.http.post(this.dataService.url+'/transaction/transfer',this.transaction,httpOptions)
    .subscribe((result:any) => {
      console.log("After Transaction: ",result);
      this.tresult = result;
      this.dataService.customer.clearbalance -= (result.inramount + this.transaction.transferfees);
      this.apiResult.success=true;
      this.apiResult.error =false;
    }, err => { 
      console.log(err);
      //  console.log("Status", err.status);
      //  console.log("Error: ",err.error);
      //  console.log("Error: ",err.error.message);
      //  console.log("Error: ",err.error.description);
      
      this.errordesc = err.error.description;
      this.apiResult.success=false;
      this.apiResult.error =true;
    })
  }

}

