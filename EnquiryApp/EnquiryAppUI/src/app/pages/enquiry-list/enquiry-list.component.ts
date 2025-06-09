import { Component, inject, OnInit } from '@angular/core';
import { MasterService } from '../../service/master.service';
import { Enquiry } from '../../classes/enquiry-class/enquiryClass';
import { Observable } from 'rxjs';
import { DatePipe } from '@angular/common';

@Component({
  selector: 'app-enquiry-list',
  imports: [DatePipe],
  templateUrl: './enquiry-list.component.html',
  styleUrl: './enquiry-list.component.css'
})
export class EnquiryListComponent implements OnInit {
  MasterSrv = inject(MasterService);
  enquiryList: any[] = [];
  typeList: any[] = [];
  statusList: any[] = [];

  getTypeName(typeId: number): string {
    const type = this.typeList.find((t: any) => t.typeId === typeId);
    return type ? type.typeName : 'Unknown Type';
  }

  getStatusName(statusId: number): string {
    const status = this.statusList.find((s: any) => s.statusId === statusId);
    return status ? status.statusName : 'Unknown Status';
  }

  ngOnInit(): void {
    this.MasterSrv.getTypes().subscribe({
      next: (types: any[]) => {
        this.typeList = types;
      }
    });
    this.MasterSrv.getStatus().subscribe({
      next: (statuses: any[]) => {
        this.statusList = statuses;
      }
    });
    this.MasterSrv.getEnquiries().subscribe({
      next: (Res: any) => {
        this.enquiryList = Res;
        console.log('Enquiries fetched successfully:', this.enquiryList);
      },
      error: (error) => {
        console.error('Error creating enquiry:', error);
      }
    });
  }
}
