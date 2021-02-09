import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { AnswerToComplaint } from '@app/model/membershipinfo/answerToComplaint';
import { AnswerToDermComplaint } from '@app/model/membershipinfo/answerToDermComplaint';
import { AnswerToPharmComplaint } from '@app/model/membershipinfo/answerToPharmComplaint';
import { AnswerToPhComplaint } from '@app/model/membershipinfo/answerToPhComplaint';
import { Complaint } from '@app/model/membershipinfo/complaint';
import { DermatologistComplaint } from '@app/model/membershipinfo/dermatologistComplaint';
import { PharmacistComplaint } from '@app/model/membershipinfo/pharmacistComplaint';
import { PharmacyComplaint } from '@app/model/membershipinfo/pharmacyComplaint';
import { SystemAdmin } from '@app/model/users/systemAdmin/systemAdmin';
import { ComplaintsService } from '@app/service/complaints/complaints.service';
import { SysadminRegistrationService } from '@app/service/sysadmin-registration/sysadmin-registration.service';
import { AuthenticationService } from '@app/service/user';

@Component({
  selector: 'app-admin-complaints',
  templateUrl: './admin-complaints.component.html',
  styleUrls: ['./admin-complaints.component.css']
})
export class AdminComplaintsComponent implements OnInit {
  public sysAdmin : SystemAdmin;
  public dermatoligstComplaints : DermatologistComplaint[];
  public pharmacyComplaints : PharmacyComplaint[];
  public pharmacistComplaints : PharmacistComplaint[];              
  public answerFormDerm : FormGroup;
  public answerFormPh : FormGroup;
  public answerFormPharm : FormGroup;
  public answerForDermComplaint: AnswerToDermComplaint;
  public answerForPhComplaint: AnswerToPhComplaint;
  public answerForPharmComplaint: AnswerToPharmComplaint;
  public answerToComplaint : AnswerToComplaint;
  constructor(private systemAdminService: SysadminRegistrationService, private router: Router, private authenticationService : AuthenticationService, private complaintsService : ComplaintsService) { }

  ngOnInit(): void {
    this.loadUnanswered();
    this.answerFormDerm = new FormGroup({
      'answer' : new FormControl(null, Validators.required)
    });
    this.answerFormPharm = new FormGroup({
      'answer' : new FormControl(null, Validators.required)
    });
    this.answerFormPh = new FormGroup({
      'answer' : new FormControl(null, Validators.required)
    });
    this.loadSystemAdmin();
  }
  
  loadSystemAdmin(){
    this.systemAdminService.getSysAdmin(Number(localStorage.getItem('userId'))).subscribe(
      data => 
      {
        this.sysAdmin = new SystemAdmin(Number(localStorage.getItem('userId')), data.sysName, data.sysSurname, data.sysDateOfBirth, data.sysPhoneNumber, data.sysEmail, data.sysPassword, data.sysGender, data.sysAddress, data.sysRole, data.sysAuthorities, data.firstTimeChanged);
      });

  }
  adminLogout(){
    this.authenticationService.logout;
    this.router.navigate(['/login']);
  }
  loadUnanswered(){
    this.complaintsService.getUnansweredDermatoligstComplaints().subscribe(data => 
      {
        this.dermatoligstComplaints = data
      });
      this.complaintsService.getUnansweredPharmacyComplaints().subscribe(data => 
        {
          this.pharmacyComplaints = data
        });
        this.complaintsService.getUnansweredPharmacistComplaints().subscribe(data => 
          {
            this.pharmacistComplaints = data
          });
  }
  answerToComplaintPharmacy(complaint){
    this.answerToComplaint = new AnswerToComplaint(null, complaint.id, this.answerFormPh.controls.answer.value, this.sysAdmin);
    this.complaintsService.answerToComplaint(this.answerToComplaint).subscribe(
      res=>{
        alert('Success');
        this.loadUnanswered();
      },
      error=>{
        alert("Fail")
      }
    )
  }
  answerToComplaintPharmacist(complaint){
    this.answerToComplaint = new AnswerToComplaint(null, complaint.id, this.answerFormPharm.controls.answer.value, this.sysAdmin);
    console.log(this.answerToComplaint)
    this.complaintsService.answerToComplaint(this.answerToComplaint).subscribe(
      res=>{
        alert('Success');
        this.loadUnanswered();
      },
      error=>{
        alert("Fail")
      }
    )
  }
  answerToComplaintDermatologist(complaint){
    this.answerToComplaint = new AnswerToComplaint(null, complaint.id, this.answerFormDerm.controls.answer.value, this.sysAdmin);
    this.complaintsService.answerToComplaint(this.answerToComplaint).subscribe(
      res=>{
        alert('Success');
        this.loadUnanswered();
        
      },
      error=>{
        alert("Fail")
      }
    )
  }
}
