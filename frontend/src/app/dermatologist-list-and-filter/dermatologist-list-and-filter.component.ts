import { Component, OnInit } from '@angular/core';
import {User} from "@app/model/users";
import {UserService} from "@app/service/user";
import {DermatologistService} from "@app/service/dermatologist/dermatologist.service";
import {Dermatologist} from "@app/model/users/dermatologist/dermatologist";
import {PharmacyAdminService} from "@app/service/pharmacyAdmin/pharmacy-admin.service";
import {Pharmacy} from "@app/model/pharmacy/pharmacy";
import {PharmacyService} from "@app/service/pharmacy/pharmacy.service";
import {AverageGrade} from "@app/model/users/averageGrade";

@Component({
  selector: 'app-dermatologist-list-and-filter',
  templateUrl: './dermatologist-list-and-filter.component.html',
  styleUrls: ['./dermatologist-list-and-filter.component.css']
})
export class DermatologistListAndFilterComponent implements OnInit {
  user:User;
  dermatologists:Dermatologist[]=new Array();
  allPharmacies:Pharmacy[] = new Array();
  selectedPharmacy: any;
  selectedGrade: any;
  isRegisteredUser:boolean = false;
  pharmacyId:Number;
  searchInput: String;
  gradeFiltered:boolean = false;
  pharmacyFiltered:boolean = false;
  searched:boolean = false;
  searchedDermatologists:Dermatologist[] = new Array();
  dermatologists2:Dermatologist[]=new Array();


  constructor(private userService:UserService
              ,private dermatologistService:DermatologistService
              ,private pharmacyAdminService:PharmacyAdminService
              ,private pharmacyService:PharmacyService) { }

  ngOnInit(): void {
    this.userService.getLoggedInUser().subscribe(data => {
      this.user = data;
      if (this.user.role == "PharmacyAdmin") {
        this.pharmacyAdminService.getPharmacyByAdmin(this.user.id).subscribe(result => {
          this.pharmacyId = result.id;
          this.getDermatologistsByPharmacy(this.pharmacyId);
        });
      } else {
        this.isRegisteredUser = true;
        this.getAllDermatologists();
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
      this.dermatologistService.filterDermatologistsByPharmacy(this.searchedDermatologists,pharmacy.id).subscribe(result =>{
        this.dermatologists = result;
      });
    }
    else{
          if(this.searched){
            this.dermatologistService.filterDermatologistsByPharmacy(this.dermatologists,pharmacy.id).subscribe(result =>{
              this.dermatologists = result;
              this.pharmacyFiltered = true;
            });
          }
          else{
            this.dermatologistService.getDermatologistsByPharmacy(pharmacy.id).subscribe(result => {
              this.dermatologists = result;
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
      this.dermatologistService.filterDermatologistsByGrade(this.searchedDermatologists,grade).subscribe(result =>{
        this.dermatologists = result;
      });
    }
    else{
      this.dermatologistService.filterDermatologistsByGrade(this.dermatologists,grade).subscribe(result =>{
        this.dermatologists = result;
        this.gradeFiltered = true;
      });
    }

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

  private getDermatologistsByPharmacy(id: Number) {
    this.dermatologistService.getDermatologistsByPharmacy(id).subscribe(result => {
      this.dermatologists = result;
      this.dermatologists2 = result;
    });
    return this.dermatologists;
  }

  private getAllDermatologists() {
    this.dermatologistService.getAll().subscribe(result => {
      this.dermatologists = result;
      this.dermatologists2 = result;
    });
    return this.dermatologists;
  }

  search() {
    console.log(this.searchInput);
    this.searched = true;
    if(this.user.role == "PharmacyAdmin"){

        this.dermatologistService.searchDermatologists(this.pharmacyId,this.searchInput).subscribe(result =>{
          this.dermatologists = result;
          this.searchedDermatologists = result;
        });
    }
    else{
        this.dermatologistService.searchDermatologists(null,this.searchInput).subscribe(result =>{
          this.dermatologists = result;
          this.searchedDermatologists = result;
        });
    }

  }

  filterByGradeAndPharmacy() {
    console.log(this.selectedPharmacy.id);
    console.log(this.selectedGrade);

    if(this.searched){
      this.dermatologistService.filterDermatologistsByPharmacy(this.searchedDermatologists,this.selectedPharmacy.id).subscribe(result =>{
        this.dermatologistService.filterDermatologistsByGrade(result,this.selectedGrade).subscribe(result =>{
          this.dermatologists = result;
        });
      });
    }
    else{
      this.dermatologistService.filterDermatologistsByPharmacy(this.dermatologists2,this.selectedPharmacy.id).subscribe(result =>{
        this.dermatologistService.filterDermatologistsByGrade(result,this.selectedGrade).subscribe(result =>{
          this.dermatologists = result;
        });
      });
    }
    }


}
