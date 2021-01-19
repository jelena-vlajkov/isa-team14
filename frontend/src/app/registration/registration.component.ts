import { Component, Inject, OnInit } from '@angular/core';
import { from } from 'rxjs';
import { Proba } from './../service/Proba';

@Component({
  selector: 'app-registration',
  templateUrl: './registration.component.html',
  styleUrls: ['./registration.component.css']
})
export class RegistrationComponent implements OnInit {

  constructor(@Inject(Proba) private Proba : Proba) { }

  ngOnInit(): void {
    console.log("OM<GGSDGSD");
    this.Proba.proba().subscribe();
  }

}
