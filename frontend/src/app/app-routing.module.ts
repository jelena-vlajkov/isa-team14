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
import {BusinessReportComponent } from './business-report/business-report.component';
import { DefineLoyaltyComponent } from './define-loyalty/define-loyalty.component';
import { UserProfileComponent} from './user-profile/user-profile.component';
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
import { UnauthenticatedUserPharmaciesComponent } from './unauthenticated-user-pharmacies/unauthenticated-user-pharmacies.component';
import { UnauthenticatedUserMedicationsComponent } from './unauthenticated-user-medications/unauthenticated-user-medications.component';
import { Role } from './model/users';
import { SupplierProfileComponent } from './supplier-profile/supplier-profile.component';
import { SupplierOrdersComponent } from './supplier-orders/supplier-orders.component';
import { SupplierOffersComponent } from './supplier-offers/supplier-offers.component';
import { PatientComplainComponent } from './patient-complain/patient-complain.component';
import { AdminComplaintsComponent } from './admin-complaints/admin-complaints.component';
import { SupplierAllOffersComponent } from './supplier-all-offers/supplier-all-offers.component';
import { SupplierStorageComponent } from './supplier-storage/supplier-storage.component';
import { PharmacyProfileMockComponent } from './pharmacy-profile-mock/pharmacy-profile-mock.component';
import { PatientSubscriptionsComponent } from './patient-subscriptions/patient-subscriptions.component';

const routes: Routes = [  
{
  path : 'login',
  component : LoginComponent,

},
{
  path : 'registration',
  component : RegistrationComponent
},
{
  path : 'admin',
  component : AdminComponent,
  canActivate : [AuthGuard],
  data: {roles:[Role.SysAdmin]}
},
{
  path : 'admin/registerPharmacy',
  component : RegisterPharmacyComponent,
  canActivate : [AuthGuard],
  data: {roles:[Role.SysAdmin]}
},
{
  path : 'admin/registerSupplier',
  component : RegisterSupplierComponent,
  canActivate : [AuthGuard],
  data: {roles:[Role.SysAdmin]}
},
{
  path : 'admin/addAdmin',
  component : AddAdminComponent,
  canActivate : [AuthGuard],
  data: {roles:[Role.SysAdmin]}
},
{
  path : 'admin/registerPharmacyAdmin',
  component : RegisterPharmacyadminComponent,
  canActivate : [AuthGuard],
  data: {roles:[Role.SysAdmin]}
},
{
  path: 'admin/registerDermatologist',
  component : RegisterDermatologistComponent,
  canActivate : [AuthGuard],
  data: {roles:[Role.SysAdmin]}
},
{
  path: 'admin/answerComplaints',
  component : AdminComplaintsComponent,
  canActivate : [AuthGuard],
  data: {roles:[Role.SysAdmin]}
},
{
  path : 'pharmacyAdmin-profile',
  component : PharmacyAdminProfileComponent,
  canActivate : [AuthGuard],
  data: {roles:[Role.PharmacyAdmin]}
},
{
  path : 'pharmacy-profile',
  component : PharmacyProfileComponent
},{
  path : 'pharmacyProfileMock',
  component : PharmacyProfileMockComponent
},
{
  path: 'patientsSubscriptions',
  component : PatientSubscriptionsComponent,
  canActivate : [AuthGuard],
  data:{role:[Role.Patient]}
}
,
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
  component : DefineLoyaltyComponent,
  canActivate : [AuthGuard],
  data: {roles:[Role.SysAdmin]}
},
{
  path : 'userProfile',
  component : UserProfileComponent,
  canActivate : [AuthGuard],
  data: {roles:[Role.Patient]}
},{
  path : 'admin/addDrug',
  component : AdminRegisterDrugComponent,
  canActivate : [AuthGuard],
  data: {roles:[Role.SysAdmin]}
},
{ path: 'home',
    component: HomeComponent
},
{ path: '',
  component: UnauthenticatedUserComponent,
  
},
{ path: 'pharmacist',
  component: PharmacistComponent,
  canActivate : [AuthGuard],
  data: {roles:[Role.Pharmacist, Role.Dermatologist]}

},
{ path: 'pharmacist/profile',
  component: PharmacistProfileComponent,
  canActivate : [AuthGuard],
  data: {roles:[Role.Pharmacist, Role.Dermatologist]}
},
{
  path: 'pharmacist/calendar',
  component: PharmacistCalendarComponent,
  canActivate : [AuthGuard],
  data: {roles:[Role.Pharmacist, Role.Dermatologist]}
},
{
  path: 'pharmacist/patients',
  component: PharmacistPatientsComponent,
  canActivate : [AuthGuard],
  data: {roles:[Role.Pharmacist, Role.Dermatologist]}
},
{
  path: 'pharmacist/reports',
  component: PharmacistReportsComponent,
  canActivate : [AuthGuard],
  data: {roles:[Role.Pharmacist, Role.Dermatologist]}
},

{
  path: 'pharmacist/addReport',
  component: PharmacistAddReportComponent,
  canActivate : [AuthGuard],
  data: {roles:[Role.Pharmacist, Role.Dermatologist]}
},
{
  path: 'pharmacist/medication',
  component: PharmDermMedicationsComponent,
  canActivate : [AuthGuard],
  data: {roles:[Role.Pharmacist, Role.Dermatologist]}
},
{
  path: 'searchPharmacies',
  component: UnauthenticatedUserPharmaciesComponent
},
{
  path: 'searchMedications',
  component: UnauthenticatedUserMedicationsComponent
},
{
  path:'supplier',
  component : SupplierProfileComponent,
  canActivate : [AuthGuard],
  data: {roles:[Role.Supplier]}
},
{
  path:'supplier/allOrders',
  component : SupplierOrdersComponent,
  canActivate : [AuthGuard],
  data: {roles:[Role.Supplier]}
},
{
  path:'supplier/offers',
  component : SupplierOffersComponent,
  canActivate : [AuthGuard],
  data: {roles:[Role.Supplier]}
},
{
  path:'supplier/allOffers',
  component : SupplierAllOffersComponent,
  canActivate : [AuthGuard],
  data: {roles:[Role.Supplier]}
},
{
  path:'supplier/storage',
  component : SupplierStorageComponent,
  canActivate : [AuthGuard],
  data: {roles:[Role.Supplier]}
}
,
{
  path:'userProfile/complain',
  component : PatientComplainComponent,
  canActivate : [AuthGuard],
  data: {roles:[Role.Patient]}
}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
