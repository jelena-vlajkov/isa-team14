import { Component, OnInit } from '@angular/core';
import { Form, FormControl, FormGroup, Validators } from '@angular/forms';
import {AuthenticatedUser} from "../model/users/authenticatedUser";
import {PharmacyAdminService} from "@app/service/pharmacyAdmin/pharmacy-admin.service";
import {Role, User} from "@app/models";
import {Address} from "@app/model/address/address";
import {DermatologistService} from "@app/service/dermatologist/dermatologist.service";
import {valueReferenceToExpression} from "@angular/compiler-cli/src/ngtsc/annotations/src/util";
import {PharmacistService} from "@app/service/pharmacist/pharmacist.service";

@Component({
  selector: 'app-pharmacy-profile',
  templateUrl: './pharmacy-profile.component.html',
  styleUrls: ['./pharmacy-profile.component.css']
})
export class PharmacyProfileComponent implements OnInit {

  name:String;
  address:String;
  grade:number;
  about:String;
  currentUserId:String;
  dermatologists: String[]=new Array();
  pharmacists: String[]=new Array();
  pharmacyId:number;
  private StringIsNumber = value => isNaN(Number(value)) === false;
  public profile:boolean = true;
  public edit:boolean = false;
  public changePassword:boolean = false;

  editProfileForm: FormGroup;

  constructor(private pharmacyAdminService:PharmacyAdminService
              ,private dermatologistService:DermatologistService
              ,private pharmacistService:PharmacistService) { }

  ngOnInit(): void {
    this.currentUserId=localStorage.getItem('userId');
    console.log(this.currentUserId);

    this.pharmacyAdminService.getPharmacyByAdmin(Number(this.currentUserId)).subscribe(
      result => {
        this.pharmacyId = result.id;
        this.name = result.name;
        this.grade = result.average_grade;
        console.log(result.average_grade);
        this.about = result.description;
        this.address = result.address.street + ", " + result.address.city.name + ", " + result.address.state.name;

        this.dermatologistService.getDermatologistByPharmacy(this.pharmacyId).subscribe(
          result => {
            result=this.ToArray(result);
            for(let i=0;i<result.length;i++)
            {
              this.dermatologists.push(result[i].name+" "+result[i].surname);
            }
          });
        this.pharmacistService.getPharmacistsByPharmacy(this.pharmacyId).subscribe(
          result => {
            result=this.ToArray(result);
            for(let i=0;i<result.length;i++)
            {
              this.pharmacists.push(result[i].name+" "+result[i].surname);
            }
          });
       });


        this.editProfileForm = new FormGroup({});
      }

  ToArray(enumme) {
    return Object.keys(enumme)
      .filter(this.StringIsNumber)
      .map(key => enumme[key]);
  }


  cancelEdit(){
    this.profile = true;
    this.edit = false;
    this.changePassword = false;
  }

  addAdmin(){}
  editProfile(){
    this.edit = true;

    this.profile= false;
    this.changePassword = false;

    this.editProfileForm = new FormGroup({

      'name' : new FormControl(this.name, Validators.required),
      'address' : new FormControl(this.address, Validators.required),
      'grade': new FormControl(this.grade, Validators.required)
    });
  }

}
