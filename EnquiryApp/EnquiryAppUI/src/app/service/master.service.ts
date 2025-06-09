import { Injectable } from '@angular/core';
import { Enquiry } from '../classes/enquiry-class/enquiryClass';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class MasterService {

  constructor(private http: HttpClient) {}
    
    createEnquiry(enquiry: Enquiry) {
      return this.http.post<Enquiry>('https://localhost:7209/api/EnquiryMaster/CreateNewEnquiry', enquiry);
    }
    getStatus() {
      return this.http.get<string[]>('https://localhost:7209/api/EnquiryMaster/GetAllStatus');
    }
    getTypes() {
      return this.http.get<string[]>('https://localhost:7209/api/EnquiryMaster/GetAllTypes');
    }

    getEnquiries() {
      return this.http.get('https://localhost:7209/api/EnquiryMaster/GetAllEnquiries');
    }

    deleteEnquiry(enquiryId: number) {
      return this.http.delete(`https://localhost:7209/api/EnquiryMaster/DeleteEnquiry/${enquiryId}`);
    }
}
