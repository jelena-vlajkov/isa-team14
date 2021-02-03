import { Component, OnInit, ViewChild } from '@angular/core';
import { Form, FormControl, FormGroup, Validators } from '@angular/forms';
import { Patient } from '../model/users/patient/patient';
import { Gender } from '../model/users/patient/gender';
import { Role } from '../model/users/role';
import { GooglePlacesComponent } from '../google-places/google-places.component';
import { Address } from '../model/address/address';
import { PatientService} from '../service/patient/patient.service'
import {Location} from '@angular/common';
import {Ingredient} from '../model/medications/ingredient';
import {IngredientService} from '../service/medication/ingredients.service';


@Component({
  selector: 'app-user-profile',
  templateUrl: './user-profile.component.html',
  styleUrls: ['./user-profile.component.css']
})
export class UserProfileComponent implements OnInit {

    profileForm: FormGroup;
    changePasswordForm:FormGroup;
    name:String;
    surname:String;
    gender : Gender;
    selectedGender : String;
    selectedDate:Date;
    dob: Date;
    updateDate;
    address : Address;
    phone:String;
    mail:String;
    password1:String;
    password2:String;
    dateString: String;
    oldpassword:String;
    editDate : FormControl;
    points:String;
    categoryProgram:String;
    public profile:boolean = true;
    public edit:boolean = false;
    public changePassword:boolean = false;
    public loyalty:boolean = false;
    editProfileForm: FormGroup;
    loyaltyForm: FormGroup;
    @ViewChild(GooglePlacesComponent) googleplaces;
    public allIngredients: Ingredient[] = new Array();
    ingredientSelected: string;
    allergies = new FormControl();


    allergiesList = ['Brufen', 'Brufen', 'Brufen', 'Brufen', 'Brufen', 'Brufen'];
    selectedAllergies;

  public patient : Patient;
  constructor(private patientService : PatientService, private _location: Location, private ingredientService : IngredientService) { }

  ngOnInit(): void {
    
    this.editProfileForm = new FormGroup({});
    this.changePasswordForm = new FormGroup({});
    this.profileForm = new FormGroup({});
    this.loyaltyForm = new FormGroup({});
    this.points = "28";
    this.categoryProgram = "Gold";
    /*
    this.password1 = 
    this.password2 = "";

    this.name = "Stefan";
    this.surname = "Stefan";
    this.selectedGender = "Male";
    this.address = "Cara Dusana 77, Novi Sad, Srbija";
    this.phone = "064";
    this.mail = "stefolino@uns.ac.rs";
    this.dob = new Date("1998-01-16");
    this.editDate = new FormControl(this.dob.toISOString());
    this.dateString = this.dob.toLocaleDateString();
  
    */
    //this.patient = this.patientService.getPatientById();

    this.loadPatient();
      
  }

  loadPatient(){
    this.patientService.getPatientById(Number(localStorage.getItem('userId'))).subscribe(data =>
      {
        this.patient = data;
      });
  }

  comboChange(event) {
    if(!event) {
      console.log('dropdown is closed');
      this.ingredientSelected = this.allergies.value && this.allergies.value.toString();
      console.log(this.allergies.value);
    }
    
  }


  loadIngredients(){
    this.ingredientService.findAllIngredients().subscribe(data =>
      {
        this.allIngredients = data;
        
      });
  }

  loyaltyClick(){
    this.loyalty = true;
    this.profile= false;
    this.changePassword = false;
    this.edit = false;
  }
  
  backClick(){
    this.loyalty = false;
    this.profile = true;
  }

  editProfile(){
   
    this.edit = true;
    this.profile= false;
    this.changePassword = false;
    this.loyalty = false;
    this.name = this.patient.name;
    this.surname = this.patient.surname;
    this.mail = this.patient.email;
    this.phone = this.patient.phoneNumber;
    this.address = this.patient.address;
    this.gender = this.patient.gender;
    if(this.gender.toString() == 'MALE'){     
      this.selectedGender = 'Male';
    }
    if(this.gender.toString() == 'FEMALE'){
      this.selectedGender = 'Female';
    }
    this.selectedDate = this.patient.dateOfBirth;
    this.updateDate = this.patient.dateOfBirth;
    //this.googleplaces.address = this.patient.address;
 

   
    this.editProfileForm = new FormGroup({
      'name' : new FormControl(this.name, Validators.required),
      'surname' : new FormControl(this.surname, Validators.required),
      'email' : new FormControl(null, Validators.required),
      'telephone' : new FormControl(this.phone, Validators.required),
      'address' : new FormControl(this.address, Validators.required),
      'gender': new FormControl(this.selectedGender, Validators.required),
      'selectedDate': new FormControl(this.selectedDate, Validators.required)
    });

    /*
    if(this.googleplaces == undefined){
      this.address = this.patient.address;
    }else{
      this.patient.address = this.googleplaces.address;
    }*/

    this.loadIngredients();

   


  }

  confirmEditButton(){

    this.patient.name = this.editProfileForm.controls.name.value;
    this.patient.surname = this.editProfileForm.controls.surname.value;
    if(this.editProfileForm.controls.gender.value == 'Male'){
        this.patient.gender = Gender.MALE;
    }else{
      this.patient.gender = Gender.FEMALE;
    }
    this.patient.phoneNumber = this.editProfileForm.controls.telephone.value;
    if(this.googleplaces.address == undefined){
      console.log(this.patient.address.street)
    }else{
      this.patient.address = this.googleplaces.address;
    }
    this.patient.dateOfBirth = this.updateDate;

    this.patientService.updatePatient(this.patient).subscribe(
      res=>{
        this.editProfileForm.reset(); 
        alert('Success');
        location.reload();      
       // this.loadPatient();
        
        },
        error=>{
          alert("Fail")
        }
      )

      

  }

  changePasswordFunction(){
    this.edit = false;
    this.profile = false;
    this.changePassword = true;
    this.loyalty = false;

    this.changePasswordForm = new FormGroup({
      'oldpassword' : new FormControl(null, Validators.required),
      'newpassword' : new FormControl(null, Validators.required),
      'confirmpassword' : new FormControl(null, Validators.required)
    });

 
  }

  cancelEdit(){
    this.profile = true; 
    this.edit = false;
    this.changePassword = false;
    this.loyalty = false;
    console.log(this.selectedAllergies.value);
  }

  submitChangePassword(){
    this.password1 = this.changePasswordForm.controls.newpassword.value;
    this.password2 = this.changePasswordForm.controls.confirmpassword.value;
    if(this.password1 !== this.password2){
      console.log('Passwords are not matching! Try again');
    }else{
      this.patient.password = this.changePasswordForm.controls.confirmpassword.value;
      this.patientService.updatePatient(this.patient).subscribe(
        res=>{
          this.editProfileForm.reset(); 
          alert('Success');
          location.reload();      
          
          },
          error=>{
            alert("Fail")
          }
        )
    }

  }

  backToolBar(){
    this._location.back();
  }

}
