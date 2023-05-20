import { SkillService } from './../../services/skill/skill.service';
import { Component, OnInit, TemplateRef, ViewChild } from '@angular/core';
import { JwtHelperService } from '@auth0/angular-jwt';
import { UserService } from 'src/app/services/user/user.service';
import { NgbModal, ModalDismissReasons } from '@ng-bootstrap/ng-bootstrap';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ToastrService } from 'ngx-toastr';
import { ProjectService } from 'src/app/services/project/project.service';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css'],
})
export class ProfileComponent implements OnInit {
  @ViewChild('project') content!: TemplateRef<any>;

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
  public projectName: any;
  public projectDescription: any;
  public role: any;
  public skill: number = 10;
  public isDisabled: boolean = true;
  public emailDisabled: boolean = true;
  public editInfo: any;
  public userSkills: any[] = [];
  public projects: any[] = [];
  public skills: any[] = [];
  public profileForm!: FormGroup;
  public selectedSkill: any;
  public selectedGrade: any;

  constructor(
    private jwtHelper: JwtHelperService,
    private userService: UserService,
    private modalService: NgbModal,
    private fb: FormBuilder,
    private skillService: SkillService,
    private toastr: ToastrService,
    private projectService: ProjectService
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
      role: this.role,
    };

    this.userService.editUser(this.id, this.editInfo).subscribe(
      (response) => {
        this.toastr.success('You have successfully edited your data', 'Edit');
        this.disableFields();
      },
      (error) => {
        this.toastr.error('Error editing data!', 'Error');
      }
    );
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
      this.userSkills = this.user.skills;
      this.projects = this.user.projects;
    });
    this.profileForm = this.fb.group({
      skills: ['', Validators.required],
      grade: ['', Validators.required],
    });
  }

  openModal(content: any) {
    this.skillService.fetchSkills().subscribe((skills) => {
      this.skills = skills;
      this.modalService
        .open(content, { ariaLabelledBy: 'modal-basic-title' })
        .result.then(
          (result) => {
            console.log('Skills:', this.profileForm.value.skills);
            console.log('Grade:', this.profileForm.value.grade);
          },
          (reason) => {
            console.log('Modal dismissed');
          }
        );
    });
  }

  openEditModal(project: any) {
    this.modalService
      .open(this.content, { ariaLabelledBy: 'modal-edit' })
      .result.then(
        (result) => {},
        (reason) => {
          console.log('Modal dismissed');
        }
      );
    // Set the values of fields in the modal based on the project data
    this.projectName = project.name;
    this.projectDescription = project.description;
  }

  saveProjectChanges() {
    this.projectService
      .editProjectDescription({
        userId: this.jwtHelper.decodeToken().id,
        name: this.projectName,
        description: this.projectDescription,
      })
      .subscribe(
        (response) => {
          this.toastr.success(
            'You have successfully edited a project description!',
            'Editing description'
          );
          window.location.reload();
        },
        (error) => {
          this.toastr.error(
            'Error edtiding description!',
            'Editing description'
          );
        }
      );
  }

  getStars(grade: number): number[] {
    return Array.from({ length: grade }, (_, index) => index + 1);
  }

  getStarColor(grade: number): string {
    if (grade === 1) {
      return 'red';
    } else if (grade === 5) {
      return 'green';
    } else {
      const colorIndex = Math.floor(((grade - 1) / 4) * 4);
      const colors = ['red', 'orange', 'yellow', 'light-green', 'green'];
      return colors[colorIndex];
    }
  }

  saveModal() {
    this.skillService
      .addSkill({
        userId: this.jwtHelper.decodeToken().id,
        name: this.selectedSkill,
        grade: this.selectedGrade,
      })
      .subscribe(
        (response) => {
          this.toastr.success(
            'You have successfully added a skill!',
            'Adding skill'
          );
          window.location.reload();
        },
        (error) => {
          this.toastr.error('Error adding skill!', 'Adding skill');
        }
      );
  }
}
