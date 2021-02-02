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
import { UnauthenticatedUserComponent } from './unauthenticated-user/unauthenticated-user.component';

const routes: Routes = [  {
  path : 'login',
  component : LoginComponent
},{
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
  component: UnauthenticatedUserComponent
}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
