import { Component, OnInit } from '@angular/core';
import { JwtHelperService } from '@auth0/angular-jwt';
import { UserService } from 'src/app/services/user/user.service';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css'],
})
export class ProfileComponent implements OnInit {
  id: any;
  public user: any;
  public changedUser: any;
  public name: any;
  public surname: any;
  public email: any;
  public street: any;
  public country: any;
  public city: any;
  public number: any;
  public phone: any;
  public title: any;
  public role: any;
  public skill: number = 10;
  public isDisabled: boolean = true;
  public emailDisabled: boolean = true;
  public editInfo: any;
  public skills: any[] = [];

  constructor(
    private jwtHelper: JwtHelperService,
    private userService: UserService
  ) {}

  enableFields() {
    this.isDisabled = false;
    this.emailDisabled = true;
  }

  disableFields() {
    this.isDisabled = true;
  }

  editProfile() {
    this.id = this.jwtHelper.decodeToken().id;
    this.editInfo = {
      id: this.jwtHelper.decodeToken().id,
      name: this.name,
      surname: this.surname,
      email: this.email,
      phone: this.phone,
      address: {
        country: this.country,
        city: this.city,
        street: this.street,
        number: this.number,
      },
      title: this.title,
    };

    this.userService.editUser(this.id, this.editInfo).subscribe((data) => {
      this.disableFields();
    });
  }

  ngOnInit(): void {
    this.id = this.jwtHelper.decodeToken().id;
    this.userService.fetchUser(this.id).subscribe((data) => {
      this.user = data;
      this.name = this.user.name;
      this.surname = this.user.surname;
      this.email = this.user.email;
      this.country = this.user.address.country;
      this.city = this.user.address.city;
      this.street = this.user.address.street;
      this.number = this.user.address.number;
      this.phone = this.user.phone;
      this.title = this.user.title;
      this.role = this.user.role;
      this.skills = this.user.skills;
    });
  }
}
