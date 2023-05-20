import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { UserService } from 'src/app/services/user/user.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css'],
})
export class RegisterComponent {
  constructor(
    private userService: UserService,
    private toastr: ToastrService,
    private router: Router
  ) {}

  address: any = {
    country: '',
    city: '',
    street: '',
    number: '',
  };

  registerParams: any = {
    name: '',
    surname: '',
    email: '',
    password: '',
    address: this.address,
    re_password: '',
    phone: '',
    title: '',
    accountType: '',
  };

  register() {
    this.userService.createUser(this.registerParams).subscribe(
      (res) => {
        this.toastr.success('Successfully registered! Please login.');
        this.router.navigate(['/login']);
      },
      (error) => {
        this.toastr.error(error.error);
      }
    );
  }
}
