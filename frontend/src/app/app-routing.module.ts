import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { AdminComponent } from './admin/admin.component';
import { LoginComponent } from './login/login.component';
import { RegistrationComponent } from './registration/registration.component';
import { RegisterPharmacyComponent } from './register-pharmacy/register-pharmacy.component';
import { RegisterSupplierComponent } from './register-supplier/register-supplier.component';
import {AddAdminComponent} from './add-admin/add-admin.component';
import {PharmacyAdminProfileComponent} from './pharmacy-admin-profile/pharmacy-admin-profile.component';
import {PharmacyProfileComponent} from './pharmacy-profile/pharmacy-profile.component';
import {PharmacyPricelistComponent} from './pharmacy-pricelist/pharmacy-pricelist.component';
import {DermatologyAppointmentComponent} from './dermatology-appointment/dermatology-appointment.component';
import{BusinessReportComponent } from './business-report/business-report.component';
import { DefineLoyaltyComponent } from './define-loyalty/define-loyalty.component';
import { AdminRegisterDrugComponent } from './admin-register-drug/admin-register-drug.component';
import {HomeComponent} from "./home";
import { AuthGuard } from './helpers';
import { RegisterPharmacyadminComponent } from './register-pharmacyadmin/register-pharmacyadmin.component';
import { RegisterDermatologistComponent } from './register-dermatologist/register-dermatologist.component';
import { PharmacistComponent } from './pharmacist/pharmacist.component';
import { PharmacistProfileComponent } from './pharmacist.profile/pharmacist.profile.component';
import { PharmacistCalendarComponent } from './pharmacist.calendar/pharmacist.calendar.component';
import { PharmacistPatientsComponent } from './pharmacist.patient/pharmacist.patient.component';
import { PharmacistReportsComponent } from './pharmacist.reports/pharmacist.reports.component';
import { PharmacistAddReportComponent } from './pharmacist.add-report/pharmacist.add-report.component';
import { PharmDermMedicationsComponent } from './pharm-derm-medications/pharm.derm.medication.component';
import { UnauthenticatedUserComponent } from './unauthenticated-user/unauthenticated-user.component';

const routes: Routes = [  
{
  path : 'login',
  component : LoginComponent
},
{
  path : 'registration',
  component : RegistrationComponent
},
{
  path : 'admin',
  component : AdminComponent
},
{
  path : 'admin/registerPharmacy',
  component : RegisterPharmacyComponent
},
{
  path : 'admin/registerSupplier',
  component : RegisterSupplierComponent
},
{
  path : 'admin/addAdmin',
  component : AddAdminComponent
},
{
  path : 'admin/registerPharmacyAdmin',
  component : RegisterPharmacyadminComponent
},
{
  path: 'admin/registerDermatologist',
  component : RegisterDermatologistComponent
},
{
  path : 'pharmacyAdmin-profile',
  component : PharmacyAdminProfileComponent
},
{
  path : 'pharmacy-profile',
  component : PharmacyProfileComponent
},
{
  path:'pharmacy-pricelist',
  component:PharmacyPricelistComponent
},
{
  path:'dermatology-appointment',
  component:DermatologyAppointmentComponent
},
{
  path:'business-report',
  component:BusinessReportComponent
},
{
  path : 'admin/defineLoyalty',
  component : DefineLoyaltyComponent
},
{
  path : 'admin/addDrug',
  component : AdminRegisterDrugComponent
},
{ path: 'home',
    component: HomeComponent,
    canActivate: [AuthGuard]
},
{ path: '',
  component: UnauthenticatedUserComponent,
  
},
{ path: 'pharmacist',
  component: PharmacistComponent
},
{ path: 'pharmacist/profile',
  component: PharmacistProfileComponent
},
{
  path: 'pharmacist/calendar',
  component: PharmacistCalendarComponent
},
{
  path: 'pharmacist/patients',
  component: PharmacistPatientsComponent
},
{
  path: 'pharmacist/reports',
  component: PharmacistReportsComponent
},

{
  path: 'pharmacist/addReport',
  component: PharmacistAddReportComponent
},


{
  path: 'pharmacist/medication',
  component: PharmDermMedicationsComponent
}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
