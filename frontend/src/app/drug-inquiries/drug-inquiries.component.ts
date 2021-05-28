import { Component, OnInit } from '@angular/core';
import {DrugInquiryService} from "@app/service/drug-inquiry/drug-inquiry.service";
import {DrugInquiry} from "@app/model/reports/drugInquiry";

@Component({
  selector: 'app-drug-inquiries',
  templateUrl: './drug-inquiries.component.html',
  styleUrls: ['./drug-inquiries.component.css']
})
export class DrugInquiriesComponent implements OnInit {
  drugInquiries:DrugInquiry[]=new Array();

  constructor(private drugInquiryService:DrugInquiryService) { }

  ngOnInit(): void {
    this.drugInquiryService.getAll().subscribe(result => {
      this.drugInquiries=result;
      console.log(this.drugInquiries);
    });
  }

  inquiriesExists() {
    return this.drugInquiries.length!=0;
  }
}
