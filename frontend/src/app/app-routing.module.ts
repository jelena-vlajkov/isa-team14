import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { AdminComponent } from './admin/admin.component';
import { LoginComponent } from './login/login.component';
import { RegistrationComponent } from './registration/registration.component';
import { RegisterPharmacyComponent } from './register-pharmacy/register-pharmacy.component';
import { RegisterSupplierComponent } from './register-supplier/register-supplier.component';
import {AddAdminComponent} from './add-admin/add-admin.component';
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
}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
