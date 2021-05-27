import { Component, OnInit } from '@angular/core';
import {DermatologistService} from "@app/service/dermatologist/dermatologist.service";
import {Pharmacy} from "@app/model/pharmacy/pharmacy";
import {WorkDay} from "@app/model/schedule/workDay";
import {Dermatologist} from "@app/model/users/dermatologist/dermatologist";
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {PharmacyAdminService} from "@app/service/pharmacyAdmin/pharmacy-admin.service";
import {WorkdayService} from "@app/service/workday/workday.service";
import {Pharmacist} from "@app/model/users/pharmacist/pharmacist";
import {AverageGrade} from "@app/model/users/averageGrade";
import {MedicalStaff} from "@app/model/users/medicalStaff";
import {AppointmentService} from "@app/service/appointment/appointment.service";

@Component({
  selector: 'app-dermatologists-in-pharmacy',
  templateUrl: './dermatologists-in-pharmacy.component.html',
  styleUrls: ['./dermatologists-in-pharmacy.component.css']
})
export class DermatologistsInPharmacyComponent implements OnInit {
  setWorkTime: boolean=false;
  workTime: FormGroup;
  workdays: WorkDay[]=new Array();
  dermatologistsInPharmacy: boolean=true;
  dermatologists: Dermatologist[]=new Array();
  pharmacy : Pharmacy;
  dermatologistsNotInPharmacy:Dermatologist[]=new Array();
  addDermatologistDialog:boolean=false;
  workdaysForDermatologist:WorkDay[]=new Array();
  private StringIsNumber = value => isNaN(Number(value)) === false;
  selectedDermatologist: Dermatologist;
  dermatologistSelected: boolean = false;
  currentDate:Date=new Date();

  constructor(private dermatologistService:DermatologistService,
              private pharmacyAdminService:PharmacyAdminService,
              private workdayService:WorkdayService,
              private appointmentService:AppointmentService) { }

  ngOnInit(): void {
    this.pharmacyAdminService.getPharmacyByAdmin(Number(localStorage.getItem('userId'))).subscribe(
      result => {
        this.pharmacy = result;
        this.getDermatologistNotInPharmacy();
        this.getDermatologistByPharmacy();
      });

    this.workTime = new FormGroup({
      'startTime' : new FormControl(null,  [Validators.required]),
      'endTime' : new FormControl(null,  [Validators.required]),
      'date': new FormControl(null,  [Validators.required])
    });

  }

  addWorkDay() {
    var inputDate=new Date(this.workTime.value.date);
    var startTime=new Date(inputDate.toString().split(":")[0].slice(0,-2) + this.workTime.value.startTime);
    var endTime=new Date(inputDate.toString().split(":")[0].slice(0,-2) + this.workTime.value.endTime);
    var overlaps=false;
    for(let i=0;i<this.workdaysForDermatologist.length;i++){
      if(new Date(this.workdaysForDermatologist[i].date).toISOString().slice(0,10) == new Date(this.workTime.value.date).toISOString().slice(0,10)){
        var existingStartTime=new Date(inputDate.toString().split(":")[0].slice(0,-2) + new Date(this.workdaysForDermatologist[i].startTime).getHours());
        var existingEndTime=new Date(inputDate.toString().split(":")[0].slice(0,-2) + new Date(this.workdaysForDermatologist[i].endTime).getHours());
        if(existingStartTime.getTime() <= endTime.getTime() || startTime.getTime() <= existingEndTime.getTime()){
          overlaps=true;
        }
      }
    }
    let overlapWithAddedWorkdays = this.workdays.filter(workday => new Date(workday.date).toISOString().slice(0,10) ==new Date(this.workTime.value.date).toISOString().slice(0,10)
      && (new Date(workday.startTime).getTime() <= endTime.getTime()) && (startTime.getTime() <= new Date(workday.endTime).getTime()));
    if(this.workTime.value.startTime > this.workTime.value.endTime  || (inputDate.getTime() < this.currentDate.getTime())){
      alert("Invalid work time input.Check if start time is after end time or selected date is before current date.Work time must be at least one hour.");
    }
    else if(overlaps || overlapWithAddedWorkdays.length!=0){
      alert("Invalid work time.Check if your inputs are overlapping with some of already existing or already added workdays.");
    }
    else{
      let workDay=new WorkDay(null,new Date(this.workTime.value.date),startTime
        ,endTime,null,null);
      console.log(workDay);
      this.workdays.push(workDay);
    }
  }

  isWorkDaysEmpty() {
    return this.workdays.length==0;
  }

  deleteWorkday(workday: any) {
    this.workdays=this.workdays.filter( wd => wd.endTime!=workday.endTime
      || wd.startTime!=workday.startTime || wd.date!=workday.date);
  }

  isDermatologistsEmpty() {
    return this.dermatologists.length==0;
  }

  showAddDermatologistDialog() {
    this.addDermatologistDialog=true;
    this.dermatologistsInPharmacy=false;
    this.getDermatologistNotInPharmacy();
  }

  deleteDermatologist(id:Number) {
    this.appointmentService.occupiedExaminationsExists(id,this.pharmacy.id).subscribe(result=>{
      if(!result){
        this.dermatologistService.deleteDermatologistFromPharmacy(id,this.pharmacy.id).subscribe(result=>{
          this.getDermatologistByPharmacy();
        });
      }
      else{
        alert("Can't delete this dermatologist at the moment.There are scheduled future examinations.");
      }

    });

  }

  addDermatologistSubmitted() {
        console.log(this.selectedDermatologist);
        console.log(this.pharmacy);
          this.dermatologistService.addDermatologistToPharmacy(this.pharmacy.id,this.selectedDermatologist.id).subscribe(result => {
            for(let i=0;i<this.workdays.length;i++){
              this.workdays[i].pharmacy=this.pharmacy;
              this.workdays[i].medicalStaff=new MedicalStaff(this.selectedDermatologist.id
                ,this.selectedDermatologist.name,this.selectedDermatologist.surname,this.selectedDermatologist.dateOfBirth
                ,this.selectedDermatologist.phoneNumber,this.selectedDermatologist.email,this.selectedDermatologist.password,this.selectedDermatologist.gender
                ,this.selectedDermatologist.address,this.selectedDermatologist.role,null,null,null);
              console.log(this.workdays[i]);
              this.workdayService.addWorkday(this.workdays[i]).subscribe(result=>{});
            }
            this.getDermatologistByPharmacy();
            this.dermatologistsInPharmacy=true;
            this.dermatologistSelected = false;
            this.addDermatologistDialog=false;

    });
  }

   getDermatologistByPharmacy() {
    this.dermatologistService.getDermatologistsByPharmacy(this.pharmacy.id).subscribe(result =>{
      this.dermatologists=this.toArray(result);
    });
  }
  getDermatologistNotInPharmacy() {
    this.dermatologistService.getDermatologistsNotInPharmacy(this.pharmacy.id).subscribe(result =>{
      this.dermatologistsNotInPharmacy=this.toArray(result);
    });
  }

  toArray(enumme) {
    return Object.keys(enumme)
      .filter(this.StringIsNumber)
      .map(key => enumme[key]);
  }

  chooseDermatologist(dermatologist:Dermatologist) {
    this.workdayService.getWorkdaysByMedicalStaff(dermatologist.id).subscribe(result =>
    {
      this.workdaysForDermatologist=this.toArray(result);
      this.selectedDermatologist=dermatologist;
    });
    this.dermatologistSelected=true;
  }

  isWorkDaysForDermatologistEmpty() {
    return this.workdaysForDermatologist.length==0;
  }
}
