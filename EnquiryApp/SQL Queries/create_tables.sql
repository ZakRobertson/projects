CREATE TABLE enquiry_status (
	statusId int NOT NULL,
	statusName varchar(50) NOT NULL,
	PRIMARY KEY (statusID)
);

CREATE TABLE enquiry_type (
	typeId int NOT NULL,
	typeName varchar(50) NOT NULL,
	PRIMARY KEY(typeId)
);

CREATE TABLE enquiry (
	enquiryId int NOT NULL,
	enquiryTypeId int NOT NULL,
	enquiryStatusID int	NOT NULL,
	customerName varchar(50) NOT NULL,
	mobileNo varbinary(10) NOT NULL,
	email varchar(50),
	enquiryMessage varchar(2000) NOT NULL,
	createdDate datetime NOT NULL,
	resolution varchar(1000),
	PRIMARY KEY(enquiryId),
	FOREIGN KEY(enquiryTypeId) REFERENCES enquiry_type(typeId),
	FOREIGN KEY(enquiryStatusId) REFERENCES enquiry_status(statusId)
);
