import { Component, OnInit, ViewChild } from '@angular/core';
import { Form, FormControl, FormGroup, Validators } from '@angular/forms';
import { Patient } from '../model/patient/patient';
import { Gender } from '../model/patient/gender';
import { Role } from '../model/users/role';
import { GooglePlacesComponent } from '../google-places/google-places.component';
import { Address } from '../model/address/address';
import { PatientService} from '../service/patient/patient.service'

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
  //gender : String;
  gender : Gender;
  selectedGender : String;
  selectedDate:Date;
  dob: Date;
  //address:String;
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
  allergies = new FormControl();
  @ViewChild(GooglePlacesComponent) googleplaces;

  allergiesList = ['Brufen', 'Brufen', 'Brufen', 'Brufen', 'Brufen', 'Brufen'];
  selectedAllergies;

  public patient : Patient;
  constructor(private patientService : PatientService) { }

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
    this.patientService.getPatientById().subscribe(data =>
      {
        this.patient = data;
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
  }

  submitChangePassword(){
    this.password1 = this.changePasswordForm.value.newpassword;
    this.password2 = this.changePasswordForm.value.confirmpassword;
    if(this.password1 !== this.password2){
      console.log('Passwords are not matching! Try again');
    }
  }

}