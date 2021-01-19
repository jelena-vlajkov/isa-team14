import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LoginComponent } from './login/login.component';
import { RegistrationComponent } from './registration/registration.component';
import { FormsModule, ReactiveFormsModule } from "@angular/forms";
import {MatFormFieldModule} from '@angular/material/form-field';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import {MatButtonModule} from '@angular/material/button'; 
import {MatCheckboxModule} from '@angular/material/checkbox';
import {MatList, MatListModule} from '@angular/material/list';
import { HttpClient, HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import {MatCardModule} from '@angular/material/card';
import {MatDividerModule} from '@angular/material/divider';
import {MatRadioModule} from '@angular/material/radio'; 
import {MatTableModule} from '@angular/material/table'; 
import {MatInputModule} from '@angular/material/input';
import {MatDatepickerModule} from '@angular/material/datepicker';
import {MatIconModule} from '@angular/material/icon';
import {MatTooltipModule} from '@angular/material/tooltip';
import { MatAutocompleteModule } from '@angular/material/autocomplete';
import {MatTabsModule} from '@angular/material/tabs';
import {MatSelectModule} from '@angular/material/select';
import { MatNativeDateModule } from '@angular/material/core';
import { Proba } from './service/Proba';

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    RegistrationComponent
  ],
  imports: [
    HttpClientModule,
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    FormsModule,
    ReactiveFormsModule,
    MatListModule,
    MatFormFieldModule,
    MatInputModule,
    MatCardModule,
    MatDividerModule,
    MatButtonModule,
    MatTableModule,
    MatDatepickerModule,
    MatRadioModule,
    MatInputModule,
    MatTabsModule,
    MatTableModule,
    MatSelectModule,
    MatNativeDateModule,
    MatIconModule,
    MatSelectModule,
    MatTooltipModule,
    MatDatepickerModule,
    MatNativeDateModule,
    MatAutocompleteModule,
    MatFormFieldModule,
    MatRadioModule,
    MatInputModule,
    MatSelectModule,
    
  ],
  providers: [Proba],
  bootstrap: [AppComponent]
})
export class AppModule { }
