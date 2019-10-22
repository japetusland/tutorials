import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { UserDashboardComponent } from "./components/user-dashboard/user-dashboard.component";
import { DashboardComponent } from "./components/dashboard/dashboard.component";


const routes: Routes = [
  { path: "dashboard", component: DashboardComponent },
  { path: "**", pathMatch: "full", component: UserDashboardComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
