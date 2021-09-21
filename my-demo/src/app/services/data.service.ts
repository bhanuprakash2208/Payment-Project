import { Injectable } from "@angular/core";
import { HttpClient, HttpHeaders } from "@angular/common/http";
import { Router } from "@angular/router";

@Injectable()

export class DataService {
    url = 'http://localhost:8080';
    customer: any;
    transfertypes: any;
    public isLoggedIn:boolean;

    constructor(private http: HttpClient, private router: Router) {
        this.isLoggedIn = false;
        this.customer = {};
        this.transfertypes = [];
    }
    authenticate(username:string,password:string){
        const body={
            "username" : username,
            "password" : password
        }
        const httpOptions = {
            headers:new HttpHeaders({
                'Content-Type':'application/json',
            })
        }
        
        this.http.post(this.url+'/login',body,httpOptions)
        .subscribe((result:any) => {
            if(result!=null){
            console.log(result);
            this.customer = result;
            console.log(this.customer.customerid);
            this.isLoggedIn=true;
            
            this.router.navigate(['/user']);
            }
          }, err => { console.log(err);
            this.isLoggedIn=false;
            
            
            alert("Invalid Login credentials");

          })
       
    }
   
    getTransferTypesThroughApi(){ 
        return this.http.get(this.url+'/transfertypes'); 
    }
    getMessagesThroughApi(){
        return this.http.get(this.url+'/messages');
    }
    getBankDataThroughApi(bic:string){
        return this.http.get(this.url+'/bank'+'/'+bic);
    }
    getReceiverDataThroughApi(name:string){
        return this.http.get(this.url+'/receivername'+'/'+name);
    }

    //demo later to be deleted
    getTransactionThroughApi(id:number){
        return this.http.get(this.url+'/transaction/tid/'+id);
    } 
}
