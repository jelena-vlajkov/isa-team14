import { Component, OnInit } from '@angular/core';
import {User} from "@app/model/users";
import {Dermatologist} from "@app/model/users/dermatologist/dermatologist";
import {Pharmacy} from "@app/model/pharmacy/pharmacy";
import {UserService} from "@app/service/user";
import {DermatologistService} from "@app/service/dermatologist/dermatologist.service";
import {PharmacyAdminService} from "@app/service/pharmacyAdmin/pharmacy-admin.service";
import {PharmacyService} from "@app/service/pharmacy/pharmacy.service";
import {AverageGrade} from "@app/model/users/averageGrade";
import {Pharmacist} from "@app/model/users/pharmacist/pharmacist";
import {PharmacistService} from "@app/service/pharmacist/pharmacist.service";

@Component({
  selector: 'app-pharmacist-list-and-filter',
  templateUrl: './pharmacist-list-and-filter.component.html',
  styleUrls: ['./pharmacist-list-and-filter.component.css']
})
export class PharmacistListAndFilterComponent implements OnInit {
  user:User;
  pharmacists:Pharmacist[]=new Array();
  allPharmacies:Pharmacy[] = new Array();
  selectedPharmacy: any;
  selectedGrade: any;
  isRegisteredUser:boolean = false;
  pharmacyId:Number;
  searchInput: String;
  gradeFiltered:boolean = false;
  pharmacyFiltered:boolean = false;
  searched:boolean = false;
  searchedPharmacists:Pharmacist[] = new Array();
  pharmacists2:Pharmacist[]=new Array();


  constructor(private userService:UserService
    ,private pharmacistService:PharmacistService
    ,private pharmacyAdminService:PharmacyAdminService
    ,private pharmacyService:PharmacyService) { }

  ngOnInit(): void {
    this.userService.getLoggedInUser().subscribe(data => {
      this.user = data;
      if (this.user.role == "PharmacyAdmin") {
        this.pharmacyAdminService.getPharmacyByAdmin(this.user.id).subscribe(result => {
          this.pharmacyId = result.id;
          this.getPharmacistsByPharmacy(this.pharmacyId);
        });
      } else {
        this.isRegisteredUser = true;
        this.getAllPharmacists();
      }
    });

    this.getAllPharmacies();
  }

  private getAllPharmacies() {
    this.pharmacyService.getAll().subscribe(result =>{
      this.allPharmacies = result;
    });
  }

  filterByPharmacy(pharmacy: Pharmacy) {
    if(this.gradeFiltered && this.pharmacyFiltered){
      this.filterByGradeAndPharmacy()
    }
    else if(this.pharmacyFiltered && this.searched){
      this.pharmacistService.filterPharmacistsByPharmacy(this.searchedPharmacists,pharmacy.id).subscribe(result =>{
        this.pharmacists = result;
      });
    }
    else{
      if(this.searched){
        this.pharmacistService.filterPharmacistsByPharmacy(this.pharmacists,pharmacy.id).subscribe(result =>{
          this.pharmacists = result;
          this.pharmacyFiltered = true;
        });
      }
      else{
        this.pharmacistService.getPharmacistsByPharmacy(pharmacy.id).subscribe(result => {
          this.pharmacists = result;
          this.pharmacyFiltered = true;
        });
      }
    }
  }


  filterByGrade(grade: Number) {
    if(this.gradeFiltered && this.pharmacyFiltered){
      this.filterByGradeAndPharmacy();
    }
    else if(this.gradeFiltered && this.searched){
      this.pharmacistService.filterPharmacistsByGrade(this.searchedPharmacists,grade).subscribe(result =>{
        this.pharmacists = result;
      });
    }
    else{
    }
    this.pharmacistService.filterPharmacistsByGrade(this.pharmacists,grade).subscribe(result =>{
      this.pharmacists = result;
      this.gradeFiltered = true;
    });

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

  private getPharmacistsByPharmacy(id: Number) {
    this.pharmacistService.getPharmacistsByPharmacy(id).subscribe(result => {
      this.pharmacists = result;
      this.pharmacists2 = result;
    });
    return this.pharmacists;
  }

  private getAllPharmacists() {
    this.pharmacistService.getAll().subscribe(result => {
      this.pharmacists = result;
      this.pharmacists2 = result;
    });
    return this.pharmacists;
  }

  search() {
    console.log(this.searchInput);
    this.searched = true;
    if(this.user.role == "PharmacyAdmin"){

      this.pharmacistService.searchPharmacists(this.pharmacyId,this.searchInput).subscribe(result =>{
        this.pharmacists = result;
        this.searchedPharmacists = result;
      });
    }
    else{
      this.pharmacistService.searchPharmacists(null,this.searchInput).subscribe(result =>{
        this.pharmacists = result;
        this.searchedPharmacists = result;
      });
    }

  }

  filterByGradeAndPharmacy() {
    console.log(this.selectedPharmacy.id);
    console.log(this.selectedGrade);

    if(this.searched){
      this.pharmacistService.filterPharmacistsByPharmacy(this.searchedPharmacists,this.selectedPharmacy.id).subscribe(result =>{
        this.pharmacistService.filterPharmacistsByGrade(result,this.selectedGrade).subscribe(result =>{
          this.pharmacists = result;
        });
      });
    }
    else{
      this.pharmacistService.filterPharmacistsByPharmacy(this.pharmacists2,this.selectedPharmacy.id).subscribe(result =>{
        this.pharmacistService.filterPharmacistsByGrade(result,this.selectedGrade).subscribe(result =>{
          this.pharmacists= result;
        });
      });
    }
  }


}
