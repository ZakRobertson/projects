export class Enquiry {
    constructor(
        public enquiryTypeId: number | null,
        public enquiryStatusId: number | null,
        public customerName: string,
        public mobileNo: string,
        public email: string,
        public enquiryMessage: string,
        public resolution: string
    ) {}
    //make sure names are same as in the API (columns in the database)
}

