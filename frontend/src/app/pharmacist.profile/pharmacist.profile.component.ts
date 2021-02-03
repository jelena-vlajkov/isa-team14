import { Component, OnInit } from '@angular/core';
import { Form, FormControl, FormGroup, Validators } from '@angular/forms';


declare interface RouteInfo {
    path: string;
    title: string;
    icon: string;
    class: string;
}

export const ROUTES: RouteInfo[] = [
    { path: '/pharmacist/profile', title: 'Profile',  icon: 'person', class: '' },
    { path: '/pharmacist/calendar', title: 'Calendar',  icon: 'calendar', class: ''}
];

@Component({
    selector: 'app-pharmacistprofile',
    templateUrl: './pharmacist.profile.component.html',
    styleUrls: ['./pharmacist.profile.component.css']
  })

export class PharmacistProfileComponent implements OnInit {
  
    profileForm: FormGroup;
    changePasswordForm:FormGroup;
    name:String;
    surname:String;
    gender : String;
    selectedGender : String;
    dob: Date;
    address:String;
    phone:String;
    mail:String;
    password1:String;
    password2:String;
    dateString: String;
    oldpassword:String;
    editDate : FormControl;
    public profile:boolean = true;
    public edit:boolean = false;
    public changePassword:boolean = false;
    public isRequired:boolean = true;
    public isEditMode:boolean = false;
    public isNotEditMode:boolean = true;
  
  
    editProfileForm: FormGroup;
  
    constructor() {

      this.isRequired = true;
      this.isEditMode = false;
      this.isNotEditMode = true;
     }
  
    ngOnInit(): void {
      this.editProfileForm = new FormGroup({});
      this.changePasswordForm = new FormGroup({});
      this.profileForm = new FormGroup({});
      this.password1 = "";
      this.password2 = "";
      this.isRequired = true;
      this.isEditMode = false;
      this.isNotEditMode = true;
  
      this.oldpassword = "peraBijeKera";
  
      this.name = "Pera";
      this.surname = "Peric";
      this.selectedGender = "Male";
      this.address = "Bulevar Revolucije 69, Novi Sad, Srbija";
      this.phone = "19257124";
      this.mail = "pera.peric@uns.ac.rs";
      this.dob = new Date("1998-01-16");
      this.editDate = new FormControl(this.dob.toISOString());
      this.dateString = this.dob.toLocaleDateString();
    }
  
    registerPharmacy(){
  
    }
    registerDermatologist(){
  
    }
    registerAdmin(){
  
    }
    registerSupplier(){
  
    }
    operationsWithDrugs(){
  
    }
    respondToComplaints(){
  
    }
    defineLoyalty(){
      
    }
    adminLogout(){
      
    }
    cancelEdit(){
      this.profile = true; 
      this.edit = false;
      this.changePassword = false;
    }
    changePasswordFunction(){
      this.edit = false;
      this.profile = false;
      this.changePassword = true;
  
      this.changePasswordForm = new FormGroup({
        'oldpassword' : new FormControl(null, Validators.required),
        'newpassword' : new FormControl(null, Validators.required),
        'confirmpassword' : new FormControl(null, Validators.required)
      });
    }
    submitChangePassword(){
      this.password1 = this.changePasswordForm.value.newpassword;
      this.password2 = this.changePasswordForm.value.confirmpassword;
      if(this.password1 !== this.password2){
        console.log('NISU ISTI NE MOZE MATORI KONTAS BRT MOJ');
      }
    }
    addAdmin(){}
    editProfile(){
      this.isRequired = false;
      this.isEditMode = true;
      this.isNotEditMode = false;
      
  
    }
    
    saveProfile(){
        this.isRequired = true;
        this.isEditMode = false;
        this.isNotEditMode = true;
        
    
      }
  
}

