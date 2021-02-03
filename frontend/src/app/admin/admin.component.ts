import { Component, OnInit } from '@angular/core';
import { Form, FormControl, FormGroup, Validators } from '@angular/forms';
import { AuthenticationService } from '@app/service/user';

@Component({
  selector: 'app-adminprofile',
  templateUrl: './admin.component.html',
  styleUrls: ['./admin.component.css']
})
export class AdminComponent implements OnInit {
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


  editProfileForm: FormGroup;

  constructor(private authenticationService : AuthenticationService) { }

  ngOnInit(): void {
    this.editProfileForm = new FormGroup({});
    this.changePasswordForm = new FormGroup({});
    this.profileForm = new FormGroup({});
    this.password1 = "";
    this.password2 = "";

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
    this.authenticationService.logout();
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
    this.edit = true;

    this.profile= false;
    this.changePassword = false;

    this.editProfileForm = new FormGroup({

      'name' : new FormControl(this.name, Validators.required),
      'surname' : new FormControl(this.surname, Validators.required),
      'email' : new FormControl(null, Validators.required),
      'telephone' : new FormControl(this.phone, Validators.required),
      'address' : new FormControl(this.address, Validators.required),
      'gender': new FormControl(this.selectedGender, Validators.required)
    });
    

  }
}
