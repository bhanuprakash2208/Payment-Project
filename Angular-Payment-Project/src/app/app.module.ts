import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import {MatToolbarModule} from '@angular/material/toolbar';
import { AppComponent } from './app.component';
import {MatIconModule} from '@angular/material/icon';
import {MatButtonModule} from '@angular/material/button';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { RouterModule } from '@angular/router';
import {MatSidenavModule} from '@angular/material/sidenav';
import {MatListModule} from '@angular/material/list';
import {MatDividerModule} from '@angular/material/divider';
import {FlexLayoutModule, BREAKPOINT} from '@angular/flex-layout';
import { HeaderComponent } from './header/header.component';
import { HomeComponent } from './home/home.component';
import {MatCardModule} from '@angular/material/card';
import {MatStepperModule} from '@angular/material/stepper';
import {MatFormFieldModule} from '@angular/material/form-field';
import {MatInputModule} from '@angular/material/input';
import {MatSelectModule} from '@angular/material/select';
import {MatProgressSpinnerModule} from '@angular/material/progress-spinner';
import { TransferComponent } from './transfer/transfer.component';
import { ReactiveFormsModule, FormsModule } from '@angular/forms';
import { DataService } from './services/data.service';
import { HttpClientModule } from '@angular/common/http';
import { LoginComponent } from './login/login.component';
import { ContainerComponent } from './container/container.component';

@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    HomeComponent,
    TransferComponent,
    LoginComponent,
    ContainerComponent
  ],
  imports: [
    BrowserModule,
    BrowserAnimationsModule,
    FormsModule,
    ReactiveFormsModule,
    HttpClientModule,
    MatToolbarModule,
    MatIconModule,
    MatButtonModule,
    MatSidenavModule,
    MatListModule,
    FlexLayoutModule,
    MatDividerModule,
    MatCardModule,
    MatStepperModule,
    MatFormFieldModule,
    MatInputModule,
    MatSelectModule,
    MatProgressSpinnerModule,
    RouterModule.forRoot([
      
      { path: "", component: LoginComponent,children:[
        { path: "login", component:LoginComponent}
      ]},
      { path: "user", component: ContainerComponent,children:[
        { path: "", component: HomeComponent},
        { path: "home", component: HomeComponent},
        { path: "transfer", component:TransferComponent}
      ] }
      // { path: "login", component: LoginComponent},
      // { path: "home", component: HomeComponent},
      // { path: "transfermoney", component:TransferComponent}


    ])

  ],
  providers: [DataService],
  bootstrap: [AppComponent]
})
export class AppModule { }
