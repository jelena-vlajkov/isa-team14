import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LoginComponent } from './login/login.component';
import { RegistrationComponent } from './registration/registration.component';
import { FormsModule, ReactiveFormsModule } from "@angular/forms";
import {MatFormFieldModule} from '@angular/material/form-field';
import { MatMenuModule } from '@angular/material/menu'
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import {MatButtonModule} from '@angular/material/button';
import {MatCheckboxModule} from '@angular/material/checkbox';
import {MatList, MatListModule} from '@angular/material/list';
import { HttpClient, HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import {MatCardModule} from '@angular/material/card';
import {MatDividerModule} from '@angular/material/divider';
import {MatRadioModule} from '@angular/material/radio';
import {MatTableModule} from '@angular/material/table';
import {MatInputModule} from '@angular/material/input';
import {MatDatepickerModule} from '@angular/material/datepicker';
import {MatIconModule} from '@angular/material/icon';
import {MatTooltipModule} from '@angular/material/tooltip';
import { MatAutocompleteModule } from '@angular/material/autocomplete';
import {MatTabsModule} from '@angular/material/tabs';
import {MatSelectModule} from '@angular/material/select';
import { MatNativeDateModule } from '@angular/material/core';
import { AdminComponent } from './admin/admin.component';
import {MatToolbarModule} from '@angular/material/toolbar';
import {MatSidenavModule} from '@angular/material/sidenav';
import {MatGridListModule} from '@angular/material/grid-list';
import { RegisterPharmacyComponent } from './register-pharmacy/register-pharmacy.component';
import { RegisterSupplierComponent } from './register-supplier/register-supplier.component';
import { AddAdminComponent } from './add-admin/add-admin.component';
import { PharmacyAdminProfileComponent } from './pharmacy-admin-profile/pharmacy-admin-profile.component';
import { PharmacyProfileComponent } from './pharmacy-profile/pharmacy-profile.component';
import { DermatologyAppointmentComponent } from './dermatology-appointment/dermatology-appointment.component';
import { BusinessReportComponent } from './business-report/business-report.component';
import { PharmacyPricelistComponent } from './pharmacy-pricelist/pharmacy-pricelist.component';
import { DefineLoyaltyComponent } from './define-loyalty/define-loyalty.component';
import { UserProfileComponent } from './user-profile/user-profile.component';
import { AdminRegisterDrugComponent} from './admin-register-drug/admin-register-drug.component';
import { GooglePlacesComponent } from './google-places/google-places.component';
import {ErrorInterceptor,JwtInterceptor} from "./helpers";
import { HomeComponent } from './home';
import { UnauthenticatedUserComponent } from './unauthenticated-user/unauthenticated-user.component';
import { RegisterPharmacyadminComponent } from './register-pharmacyadmin/register-pharmacyadmin.component';
import { RegisterDermatologistComponent } from './register-dermatologist/register-dermatologist.component';
import {MatStepperModule} from '@angular/material/stepper';
import { MatSortModule } from '@angular/material/sort';
import { PharmacistComponent } from './pharmacist/pharmacist.component';
import { PharmacistProfileComponent } from './pharmacist.profile/pharmacist.profile.component';
import { CommonModule, DatePipe } from '@angular/common';
import { NgbModalModule } from '@ng-bootstrap/ng-bootstrap';
import {PharmacistCalendarComponent} from './pharmacist.calendar/pharmacist.calendar.component';
import {PharmacistPatientsComponent} from './pharmacist.patient/pharmacist.patient.component';
import {MatExpansionModule, MatExpansionPanel} from '@angular/material/expansion';
import { UsersPreview } from './users.preview/users.preview.component';
import { PharmacistAddReportComponent } from './pharmacist.add-report/pharmacist.add-report.component';
import { PharmDermMedicationsComponent } from './pharm-derm-medications/pharm.derm.medication.component';
import { MedicationOrderComponent } from './medication-order/medication-order.component';
import { UnauthenticatedUserPharmaciesComponent } from './unauthenticated-user-pharmacies/unauthenticated-user-pharmacies.component';
import { UnauthenticatedUserMedicationsComponent } from './unauthenticated-user-medications/unauthenticated-user-medications.component';
import { PatientHomePageComponent } from './patient/patient-home-page/patient-home-page.component';
import { PatientAllPharmaciesComponent } from './patient/patient-all-pharmacies/patient-all-pharmacies.component';
import { SupplierProfileComponent } from './supplier-profile/supplier-profile.component';
import { SupplierOrdersComponent } from './supplier-orders/supplier-orders.component';
import { SupplierOffersComponent } from './supplier-offers/supplier-offers.component';
import { PatientComplainComponent } from './patient-complain/patient-complain.component';
import { AdminComplaintsComponent } from './admin-complaints/admin-complaints.component';
import { SupplierAllOffersComponent } from './supplier-all-offers/supplier-all-offers.component';
import { SupplierStorageComponent } from './supplier-storage/supplier-storage.component';
import { PharmacyProfileMockComponent } from './pharmacy-profile-mock/pharmacy-profile-mock.component';
import { PatientSubscriptionsComponent } from './patient-subscriptions/patient-subscriptions.component';
import { AllergiesDialogComponent } from './user-profile/allergies-dialog/allergies-dialog.component';
import { EditAllergiesComponent } from './user-profile/edit-allergies-dialog/edit-allergies/edit-allergies.component';
import { WelcomeComponent } from './employee-first-login/employee.first.login';
import { PatientExaminationComoponent } from './patient-examination/patient-examination.component';
import { PatientFinishedCounselingComponent } from './patient/patient-finished-counseling/patient-finished-counseling/patient-finished-counseling.component';
import { PatientFinishedExaminationComponent } from './patient/patient-finished-examination/patient-finished-examination/patient-finished-examination.component';
import { PatientScheduledAppointmentsComponent } from './patient/patient-scheduled-appointments/patient-scheduled-appointments/patient-scheduled-appointments.component';
import { PatientEPrescriptionsComponent } from './patient/patient-ePrescriptions/patient-e-prescriptions/patient-e-prescriptions.component';
import { PatientIssuedEDrugsComponent } from './patient/patient-issued-eDrugs/patient-issued-e-drugs/patient-issued-e-drugs.component';
import { PatientScheduleCounselingComponent } from './patient/patient-schedule-counseling/patient-schedule-counseling/patient-schedule-counseling.component';
import {NgxMaterialTimepickerModule} from 'ngx-material-timepicker';
import { PatientDrugReservationComponent } from './patient/patient-drug-reservation/patient-drug-reservation/patient-drug-reservation.component';
import { FlatpickrModule } from 'angularx-flatpickr';
import { CalendarModule, DateAdapter } from 'angular-calendar';
import { adapterFactory } from 'angular-calendar/date-adapters/date-fns';
import { PatientReservedDrugsComponent } from './patient/patient-reserved-drugs/patient-reserved-drugs/patient-reserved-drugs.component';

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    RegistrationComponent,
    AdminComponent,
    RegisterPharmacyComponent,
    RegisterSupplierComponent,
    AddAdminComponent,
    DefineLoyaltyComponent,
    UserProfileComponent,
    PharmacyAdminProfileComponent,
    PharmacyProfileComponent,
    DermatologyAppointmentComponent,
    BusinessReportComponent,
    PharmacyPricelistComponent,
    PharmacyPricelistComponent,
    DefineLoyaltyComponent,
    AdminRegisterDrugComponent,
    GooglePlacesComponent,
    HomeComponent,
    RegisterPharmacyadminComponent,
    RegisterDermatologistComponent,
    UnauthenticatedUserComponent,
    RegisterPharmacyadminComponent,
    PharmacistComponent,
    PharmacistProfileComponent,
    PharmacistCalendarComponent,
    PharmacistPatientsComponent,
    UsersPreview,
    PharmacistAddReportComponent,
    PharmDermMedicationsComponent,
    MedicationOrderComponent,
    UnauthenticatedUserPharmaciesComponent,
    UnauthenticatedUserMedicationsComponent,
    PatientHomePageComponent,
    PatientAllPharmaciesComponent,
    SupplierProfileComponent,
    SupplierOrdersComponent,
    SupplierOffersComponent,
    PatientComplainComponent,
    AdminComplaintsComponent,
    SupplierAllOffersComponent,
    SupplierStorageComponent,
    PharmacyProfileMockComponent,
    PatientSubscriptionsComponent,
    AllergiesDialogComponent,
    EditAllergiesComponent,
    WelcomeComponent,
    PatientExaminationComoponent,
    PatientFinishedCounselingComponent,
    PatientFinishedExaminationComponent,
    PatientScheduledAppointmentsComponent,
    PatientEPrescriptionsComponent,
    PatientIssuedEDrugsComponent,
    PatientScheduleCounselingComponent,
    PatientDrugReservationComponent,
    PatientReservedDrugsComponent

  ],
  imports: [
    HttpClientModule,
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    CommonModule,
    FormsModule,
    ReactiveFormsModule,
    MatListModule,
    MatFormFieldModule,
    MatInputModule,
    MatCardModule,
    MatDividerModule,
    MatButtonModule,
    MatTableModule,
    MatDatepickerModule,
    MatRadioModule,
    MatInputModule,
    MatTabsModule,
    MatTableModule,
    MatSelectModule,
    MatNativeDateModule,
    MatIconModule,
    MatSelectModule,
    MatTooltipModule,
    MatDatepickerModule,
    MatNativeDateModule,
    MatAutocompleteModule,
    MatFormFieldModule,
    MatRadioModule,
    MatInputModule,
    MatSelectModule,
    MatToolbarModule,
    MatSidenavModule,
    MatGridListModule,
    MatCheckboxModule,
    MatSortModule,
    CommonModule,
    MatMenuModule,
    //NgbModule,
    MatExpansionModule,
    MatStepperModule,
    MatCardModule,
    NgxMaterialTimepickerModule,
    NgbModalModule,
    FlatpickrModule.forRoot(),
    CalendarModule.forRoot({
      provide: DateAdapter,
      useFactory: adapterFactory,
    }),
    CommonModule,
    FormsModule
  ],
  exports: [
    PharmacistCalendarComponent
  ],
  providers: [
    { provide: HTTP_INTERCEPTORS, useClass: JwtInterceptor, multi: true },
    { provide: HTTP_INTERCEPTORS, useClass: ErrorInterceptor, multi: true },
    DatePipe
  ],

  bootstrap: [AppComponent]
})
export class AppModule { }
