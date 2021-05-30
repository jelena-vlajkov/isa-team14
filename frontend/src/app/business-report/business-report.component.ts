import { Component, OnInit } from '@angular/core';
import {ChartDataSets, ChartOptions } from 'chart.js';
import { Color, Label } from 'ng2-charts';
import {BusinessReportsService} from "@app/service/business-reports/business-reports.service";
import {PharmacyAdminService} from "@app/service/pharmacyAdmin/pharmacy-admin.service";
import {Pharmacist} from "@app/model/users/pharmacist/pharmacist";
import {Dermatologist} from "@app/model/users/dermatologist/dermatologist";
import {Pharmacy} from "@app/model/pharmacy/pharmacy";
import {DermatologistService} from "@app/service/dermatologist/dermatologist.service";
import {PharmacistService} from "@app/service/pharmacist/pharmacist.service";
import {AverageGrade} from "@app/model/users/averageGrade";

@Component({
  selector: 'app-business-report',
  templateUrl: './business-report.component.html',
  styleUrls: ['./business-report.component.css']
})
export class BusinessReportComponent implements OnInit {
  pharmacyId:Number;
  pharmacy:Pharmacy;
  pharmacists:Pharmacist[]=new Array();
  dermatologists:Dermatologist[]=new Array();
  lineChartDataForScheduledByMonth: ChartDataSets[];
  lineChartLabelsForMonth: Label[];
  lineChartDataForScheduledForHalfYear: ChartDataSets[];
  lineChartLabelsForHalfYear: Label[];
  lineChartDataForScheduledForYear: ChartDataSets[];
  lineChartLabelsForYear: Label[];

  lineChartDataForDrugIssuedByMonth: ChartDataSets[];
  lineChartDataForDrugIssuedForHalfYear: ChartDataSets[];
  lineChartDataForDrugIssuedForYear: ChartDataSets[];

  lineChartDataForPharmacyIncomeByMonth: ChartDataSets[];
  lineChartDataForPharmacyIncomeForHalfYear: ChartDataSets[];
  lineChartDataForPharmacyIncomeForYear: ChartDataSets[];

  lineChartColors: Color[];
  lineChartType:String='line';

  scheduledAppointmentForMonth:boolean = false;
  scheduledAppointmentForHalfYear:boolean = false;
  scheduledAppointmentForYear:boolean = false;

  issuedForMonth:boolean = false;
  issuedForHalfYear:boolean = false;
  issuedForYear:boolean = false;

  pharmacyIncomeForMonth:boolean = false;
  pharmacyIncomeForHalfYear:boolean = false;
  pharmacyIncomeForYear:boolean = false;

  dataForScheduledAppointmentsForMonth : number[] = new Array();
  dataForScheduledAppointmentsForHalfYear : number[] = new Array();
  dataForScheduledAppointmentsForYear : number[] = new Array();

  dataForIssuedDrugsForMonth : number[] = new Array();
  dataForIssuedDrugsForHalfYear : number[] = new Array();
  dataForIssuedDrugsForYear : number[] = new Array();

  dataForPharmacyIncomeForMonth : number[] = new Array();
  dataForPharmacyIncomeForHalfYear : number[] = new Array();
  dataForPharmacyIncomeForYear : number[] = new Array();


  constructor(private businessReportService:BusinessReportsService,
              private pharmacyAdminService:PharmacyAdminService,
              private pharmacistService:PharmacistService,
              private dermatologistService:DermatologistService) {
  }
  ngOnInit() {
    this.lineChartLabelsForHalfYear= ['January-April', 'May-August', 'September-December'];
    this.lineChartLabelsForMonth= ['January', 'February', 'March', 'April', 'May', 'June', 'July','Aug','Sept','Oct','Nov','Dec'];
    this.lineChartLabelsForYear= ['2018','2019','2020','2021'];
    this.pharmacyAdminService.getPharmacyByAdmin(Number(localStorage.getItem('userId'))).subscribe(result=>{
      this.pharmacyId = result.id;
      this.pharmacy = result;
      this.pharmacistService.getPharmacistsByPharmacy(this.pharmacyId).subscribe(result =>{
        this.pharmacists = result;
      });

      this.dermatologistService.getDermatologistsByPharmacy(this.pharmacyId).subscribe(result => {
        this.dermatologists = result;
      });
    });


    this.lineChartColors= [
      {
        backgroundColor: 'rgba(77,83,96,0.2)',
        borderColor: 'rgba(77,83,96,1)',
      }
    ];
  }

  showScheduledByMonth(){
    for(let i = 1; i<13;i++){
      this.businessReportService.getNumberOfScheduledByMonth(i,2021,this.pharmacyId).subscribe(result =>{
          this.dataForScheduledAppointmentsForMonth.push(result);
      });
    }

    this.lineChartDataForScheduledByMonth = [
      { data : this.dataForScheduledAppointmentsForMonth,label:"Number of scheduled appointments for particular month" }];

    this.scheduledAppointmentForHalfYear = false;
    this.scheduledAppointmentForMonth = true;
    this.scheduledAppointmentForYear = false;
  }

  showScheduledByHalfYear() {
    for(let i=1;i<4;i++){
      this.businessReportService.getNumberOfScheduledForHalfYear(i,2021,this.pharmacyId).subscribe(result =>{
        this.dataForScheduledAppointmentsForHalfYear.push(result);
      });
    }
    this.lineChartDataForScheduledForHalfYear = [{ data : this.dataForScheduledAppointmentsForHalfYear,label:"Number of scheduled appointments for particular part of year"}];
    this.scheduledAppointmentForHalfYear = true;
    this.scheduledAppointmentForMonth = false;
    this.scheduledAppointmentForYear = false;

  }

  showScheduledByYear() {

    for(let i=2018;i<2022;i++){
      this.businessReportService.getNumberOfScheduledForYear(i,this.pharmacyId).subscribe(result =>{
        this.dataForScheduledAppointmentsForYear.push(result);
      });
    }
    console.log(this.dataForScheduledAppointmentsForYear);
    this.lineChartDataForScheduledForYear = [{ data : this.dataForScheduledAppointmentsForYear,label:"Number of scheduled appointments for particular year"}];
    this.scheduledAppointmentForHalfYear = false;
    this.scheduledAppointmentForMonth = false;
    this.scheduledAppointmentForYear = true;

  }

  showIssuedDrugsByMonth(){
    for(let i = 1; i<13;i++){
      this.businessReportService.getNumberOfIssuedDrugsByMonth(i,2021,this.pharmacyId).subscribe(result =>{
        this.dataForIssuedDrugsForMonth.push(result);
      });
    }

    this.lineChartDataForDrugIssuedByMonth = [
      { data : this.dataForIssuedDrugsForMonth,label:"Number of issued drugs for particular month" }];

    this.issuedForMonth = true;
    this.issuedForHalfYear = false;
    this.issuedForYear = false;
  }

  showIssuedDrugsForHalfYear() {
    for(let i=1;i<4;i++){
      this.businessReportService.getNumberOfIssuedDrugsForHalfYear(i,2021,this.pharmacyId).subscribe(result =>{
        this.dataForIssuedDrugsForHalfYear.push(result);
      });
    }
    this.lineChartDataForDrugIssuedForHalfYear = [{ data : this.dataForIssuedDrugsForHalfYear,label:"Number of issued drugs for particular part of year"}];
    this.issuedForMonth = false;
    this.issuedForHalfYear = true;
    this.issuedForYear = false;

  }
  showIssuedDrugsForYear() {

    for(let i=2018;i<2022;i++){
      this.businessReportService.getNumberOfIssuedDrugsForYear(i,this.pharmacyId).subscribe(result =>{
        this.dataForIssuedDrugsForYear.push(result);
      });
    }
    this.lineChartDataForScheduledForYear = [{ data : this.dataForIssuedDrugsForYear,label:"Number of issued drugs for particular year"}];
    this.issuedForMonth = false;
    this.issuedForHalfYear = false;
    this.issuedForYear = true;

  }


  showPharmacyIncomeForMonth(){
    for(let i = 1; i<13;i++){
      this.businessReportService.getPharmacyIncomeForMonth(i,2021,this.pharmacyId).subscribe(result =>{
        this.dataForPharmacyIncomeForMonth.push(result);
      });
    }

    this.lineChartDataForPharmacyIncomeByMonth = [
      { data : this.dataForPharmacyIncomeForMonth,label:"Total pharmacy income for particular month" }];

    this.pharmacyIncomeForMonth = true;
    this.pharmacyIncomeForHalfYear = false;
    this.pharmacyIncomeForYear = false;
  }

  showPharmacyIncomeForHalfYear() {
    for(let i=1;i<4;i++){
      this.businessReportService.getPharmacyIncomeForHalfYear(i,2021,this.pharmacyId).subscribe(result =>{
        this.dataForPharmacyIncomeForHalfYear.push(result);
      });
    }
    this.lineChartDataForPharmacyIncomeForHalfYear = [{ data : this.dataForPharmacyIncomeForHalfYear,label:"Total pharmacy income for particular part of year"}];
    this.pharmacyIncomeForMonth = false;
    this.pharmacyIncomeForHalfYear = true;
    this.pharmacyIncomeForYear = false;

  }
  showPharmacyIncomeForYear() {

    for(let i=2018;i<2022;i++){
      this.businessReportService.getPharmacyIncomeForYear(i,this.pharmacyId).subscribe(result =>{
        this.dataForPharmacyIncomeForYear.push(result);
      });
    }
    this.lineChartDataForPharmacyIncomeForYear = [{ data : this.dataForPharmacyIncomeForYear,label:"Total pharmacy income for particular year"}];
    this.pharmacyIncomeForMonth = false;
    this.pharmacyIncomeForHalfYear = false;
    this.pharmacyIncomeForYear = true;

  }

  count(pharmacyAverageGrade: AverageGrade):String{
    let grade=((5 * pharmacyAverageGrade.excellent.valueOf()) + (4 * pharmacyAverageGrade.veryGood.valueOf())
      + (3 * pharmacyAverageGrade.good.valueOf()) + (2 * pharmacyAverageGrade.poor.valueOf())
      + (1 * pharmacyAverageGrade.veryPoor.valueOf()))
      / (pharmacyAverageGrade.excellent.valueOf() + pharmacyAverageGrade.veryGood.valueOf()
        + pharmacyAverageGrade.good.valueOf() + pharmacyAverageGrade.poor.valueOf()
        + pharmacyAverageGrade.veryPoor.valueOf());
    return grade.toFixed(1);
  }
}
