/// <reference types="@types/googlemaps" />
import { state } from '@angular/animations';
import { Component, ViewChild, EventEmitter, Output, OnInit, AfterViewInit, Input } from '@angular/core';
import { FormGroup, FormBuilder } from '@angular/forms';
import { zip } from 'rxjs';
import { City } from '../model/address/city';
import { Coordinates } from '../model/address/coordinates';
import { State } from '../model/address/state';
import { Address } from './../model/address/address';
// import { } from '@types/googlemaps';
declare let google: any;
@Component({
  selector: 'app-google-places',
  template: `
    <input name="address-ff" class="input"
      type="text"
      [(ngModel)]="autocompleteInput"
      placeholder="Enter location*"
      #addresstext style="font-size:20px;margin-bottom: 15px; margin-top:15px;
    width: 200px !important;"
      >
  `,
})

export class GooglePlacesComponent implements OnInit {
  @Input() adressType: string;
  @Output() setAddress: EventEmitter<any> = new EventEmitter();
  @ViewChild('addresstext') addresstext: any;
  public long : Number;
  public lat: Number;
  public autocompleteInput: string;
  queryWait: boolean;
  public address : Address;
  public state : State;
  public city :City;
  public coords : Coordinates;
  public addressArray;
  public zipcode : String;
  constructor() {
  }

  ngOnInit() {
  }

  ngAfterViewInit() {
      this.getPlaceAutocomplete();
  }

  private getPlaceAutocomplete() {
      const autocomplete = new google.maps.places.Autocomplete(this.addresstext.nativeElement,
          {
              componentRestrictions: { country: 'SRB' },
              types: ['address']  // 'establishment' / 'address' / 'geocode'
          });
      google.maps.event.addListener(autocomplete, 'place_changed', () => {
          const place = autocomplete.getPlace();
          this.invokeEvent(place);
          console.log(place);
          if(place.geometry == undefined || place.geometry == null){
              return;
          }

        //   this.address.street = place.formated_address();
          this.coords = new Coordinates(place.geometry.location.lng(), place.geometry.location.lat());
           this.addressArray = place.address_components;
        //   this.state = this.retriveAddressComponents('administrative_area_level_1');
        this.parseAddress(place.formatted_address, this.coords);
        });
  }
  retriveAddressComponents(type : string) {
    let res =  this.addressArray.find(address_components => address_components.types[0] === type);
    return res.long_name;
  }
  parseAddress(formated_address_string: String, coords: Coordinates){
      let address_chunks : String[];
      address_chunks = formated_address_string.split(', ');
      let street = address_chunks[0];
      let state_name = address_chunks[2]
      let city_name = address_chunks[1];
      city: City;
      this.state = new State(state_name);
      this.city = new City(city_name);

      this.address = new Address(null,street, this.city, coords, this.state);
  }
  invokeEvent(place: Object) {
      this.setAddress.emit(place);
  }

}
