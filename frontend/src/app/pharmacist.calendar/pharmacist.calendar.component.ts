import {
  Component,
  ChangeDetectionStrategy,
  ViewChild,
  TemplateRef,
} from '@angular/core';

import {
  startOfDay,
  endOfDay,
  subDays,
  addDays,
  endOfMonth,
  isSameDay,
  isSameMonth,
  addHours,
} from 'date-fns';

import { Subject } from 'rxjs';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import {
  CalendarEvent,
  CalendarEventAction,
  CalendarEventTimesChangedEvent,
  CalendarView,
} from 'angular-calendar';


import { Router } from '@angular/router';
import { from } from 'rxjs';
import {AuthenticationService} from '../service/user/authentication.service'
import { EmployeeService } from '@app/service/employee/employee.service';
import { Appointment } from '@app/model/appointment/appointment';
import { DatePipe } from '@angular/common';


const colors: any = {
  red: {
    primary: '#ad2121',
    secondary: '#FAE3E3',
  },
  blue: {
    primary: '#1e90ff',
    secondary: '#D1E8FF',
  },
  yellow: {
    primary: '#e3bc08',
    secondary: '#FDF1BA',
  },
};


@Component({
  selector: 'mwl-demo-component',
  changeDetection: ChangeDetectionStrategy.OnPush,
  templateUrl: './pharmacist.calendar.component.html',
  styleUrls: ['./pharmacist.calendar.component.css']
})

export class PharmacistCalendarComponent {

  @ViewChild('modalContent', { static: true }) modalContent: TemplateRef<any>;

  view: CalendarView = CalendarView.Month;

  CalendarView = CalendarView;

  viewDate: Date = new Date();

  modalData: {
    action: string;
    event: CalendarEvent;
  };

  

  refresh: Subject<any> = new Subject();

  events: CalendarEvent[] = [];

  activeDayIsOpen: boolean = true;
  public appointments : Appointment[];


  dayClicked({ date, events }: { date: Date; events: CalendarEvent[] }): void {
    if (isSameMonth(date, this.viewDate)) {
      if (
        (isSameDay(this.viewDate, date) && this.activeDayIsOpen === true) ||
        events.length === 0
      ) {
        this.activeDayIsOpen = false;
      } else {
        this.activeDayIsOpen = true;
      }
      this.viewDate = date;
    } else {
      this.events = [];
      this.viewDate = date;
      let stringDate = this.datePipe.transform(this.viewDate, 'dd.MM.yyyy.');
    
    this.employeeService.getAppointmentsByMonth(stringDate, Number(localStorage.getItem("userId"))).subscribe(
      data => {
        this.appointments = data;

        for (let a of this.appointments) {

          let startTimeString = this.datePipe.transform(a.startTime, 'hh:mm');

          let endTimeString = this.datePipe.transform(a.endTime, 'hh:mm');
          this.events.push({
            start: new Date(a.startTime),
            end: new Date(a.endTime),
            title: a.patientName + " Start: " + startTimeString + " End: " + endTimeString,
            color: colors.yellow,
            resizable: {
              beforeStart: false,
              afterEnd: false,
            },
            draggable: false,
            id : String(a.id)
          });
        }
      }
    )

    }
  }



  constructor(private authService: AuthenticationService, private router : Router, private modal: NgbModal, private employeeService : EmployeeService, private datePipe : DatePipe) { 

    let date = this.datePipe.transform(this.viewDate, 'dd.MM.yyyy.');
    
    this.employeeService.getAppointmentsByMonth(date, Number(localStorage.getItem("userId"))).subscribe(
      data => {
        this.appointments = data;

        for (let a of this.appointments) {

          let startTimeString = this.datePipe.transform(a.startTime, 'hh:mm');

          let endTimeString = this.datePipe.transform(a.endTime, 'hh:mm');
          if (isSameDay(this.viewDate, new Date(a.startTime))) {
          this.events.push({
            start: new Date(a.startTime),
            end: new Date(a.endTime),
            title: a.patientName + " Start: " + startTimeString + " End: " + endTimeString,
            color: colors.yellow,
            actions:  [
              {
                label: ' Examine patient ' ,
                a11yLabel: 'Examine patient',
                onClick: ({ event }: { event: CalendarEvent }): void => {
                  console.log(event)
                  this.router.navigate(['/appointment'], {state: {data: event.id}})
                },
          
              }
            ],
            resizable: {
              beforeStart: false,
              afterEnd: false,
            },
            draggable: false,
            id : String(a.patientId)
          });
        } else {
          this.events.push({
            start: new Date(a.startTime),
            end: new Date(a.endTime),
            title: a.patientName + " Start: " + startTimeString + " End: " + endTimeString,
            color: colors.yellow,
            resizable: {
              beforeStart: false,
              afterEnd: false,
            },
            draggable: false,
            id : String(a.patientId)

        });
        }
      }
    
  })}
  ngOnInit() {
    if ((localStorage.getItem('firstTimeChanged') === 'false')) { 
      this.router.navigate(["/employee-welcome"]);
    }

    

    console.log(this.events)

    console.log(this.viewDate)
  }

  logout() {
    this.authService.logout();
  }

  addEvent(): void {
    this.events = [
      ...this.events,
      {
        title: 'New event',
        start: startOfDay(new Date()),
        end: endOfDay(new Date()),
        color: colors.red,
        draggable: true,
        resizable: {
          beforeStart: true,
          afterEnd: true,
        },
      },
    ];
  }

  deleteEvent(eventToDelete: CalendarEvent) {
    this.events = this.events.filter((event) => event !== eventToDelete);
  }

  setView(view: CalendarView) {
    this.view = view;
    console.log(this.viewDate)
  }

  closeOpenMonthViewDay() {
    this.activeDayIsOpen = false;
  }

  

}