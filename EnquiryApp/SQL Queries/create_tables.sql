CREATE TABLE EnquiryStatus (
	statusId int NOT NULL,
	statusName varchar(50) NOT NULL,
	PRIMARY KEY (statusID)
);

CREATE TABLE EnquiryType (
	typeId int NOT NULL,
	typeName varchar(50) NOT NULL,
	PRIMARY KEY(typeId)
);

CREATE TABLE Enquiry (
	enquiryId int IDENTITY NOT NULL,
	enquiryTypeId int NOT NULL,
	enquiryStatusId int	NOT NULL,
	customerName varchar(50) NOT NULL,
	mobileNo varchar(10) NOT NULL,
	email varchar(50),
	enquiryMessage varchar(2000) NOT NULL,
	createdDate datetime NOT NULL,
	resolution varchar(1000),
	PRIMARY KEY(enquiryId),
	FOREIGN KEY(enquiryTypeId) REFERENCES EnquiryType(typeId),
	FOREIGN KEY(enquiryStatusId) REFERENCES EnquiryStatus(statusId)
);
