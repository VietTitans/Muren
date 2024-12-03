
CREATE TABLE Zipcodes(
Zipcode int PRIMARY KEY,
CityName varchar(50) NOT NULL,
);
CREATE TABLE Address(
AddressId int IDENTITY(1,1) PRIMARY KEY,
StreetName varchar(30) NOT NULL,
BuildingNo varchar(10) NOT NULL,
FloorNo INT, 
Country varchar(50) NOT NULL,
Zipcode int NOT NULL,
FOREIGN KEY(Zipcode) REFERENCES Zipcodes(Zipcode)
);

CREATE TABLE EmployeeType(
EmployeeTypeNo INT IDENTITY (1,1) PRIMARY KEY, 
EmployeeTypeName varchar(20),
);
CREATE TABLE Person(
PersonId int IDENTITY(1,1) PRIMARY KEY,
FirstName varchar(30) NOT NULL,
LastName varchar(30) NOT NULL,
phoneNo varchar(30) NOT NULL,
Email varchar(100) NOT NULL,
AddressId INT  NOT NULL,
FOREIGN KEY (AddressId) REFERENCES Address(AddressId),
);
CREATE TABLE Customer(
CustomerNo INT IDENTITY(1,1) PRIMARY KEY, 
PersonId INT NOT NULL,
FOREIGN KEY (PersonId) REFERENCES Person(PersonId),
);
CREATE TABLE BusinessCustomer(
CVR INT PRIMARY KEY, 
BusinessName varchar(50) NOT NULL,
CustomerNo int NOT NULL, 
FOREIGN KEY(CustomerNo) REFERENCES Customer(CustomerNo),
);

CREATE TABLE Employee(
EmployeeId int IDENTITY(1,1) PRIMARY KEY,
cpr varchar(10)NOT NULL,
EmployeeTypeNo INT NOT NULL,
PersonId int NOT NULL,
FOREIGN KEY (EmployeeTypeNo) REFERENCES EmployeeType(EmployeeTypeNo),
FOREIGN KEY (PersonId) REFERENCES Person(PersonId),

);

CREATE TABLE Material(
ProductNo int PRIMARY KEY, 
ProductName varchar(30) NOT NULL,
)

CREATE TABLE MaterialDescription(
Description varchar(100) not null, 
MaterialDescriptionTimeStamp DATETIME NOT NULL,		
ProductNo int NOT NULL, 
FOREIGN KEY (ProductNo) REFERENCES Material(ProductNo), 
PRIMARY KEY (ProductNo , MaterialDescriptionTimeStamp),
);

CREATE TABLE PurchasePrice(
price SmallMoney NOT NULL, 
PurchasePriceTimeStamp DATETIME NOT NULL, 
ProductNo int NOT NULL,
FOREIGN KEY (ProductNo) REFERENCES Material(ProductNo), 
PRIMARY KEY (ProductNo , PurchasePriceTimeStamp),

);
CREATE TABLE SalesPrice(
price SmallMoney NOT NULL, 
SalesPriceTimeStamp DATETIME NOT NULL, 
ProductNo int NOT NULL,
FOREIGN KEY (ProductNo) REFERENCES Material(ProductNo), 
PRIMARY KEY (ProductNo , SalesPriceTimeStamp),
);

CREATE TABLE StockMaterial( 
StockMaterialId int IDENTITY(1,1) PRIMARY KEY, 
ProductNo INT NOT NULL, 
MinStock INT NOT NULL,
MaxStock INT NOT NULL,
Quantity INT NOT NULL,
FOREIGN KEY (ProductNo) REFERENCES Material(ProductNo), 
);
CREATE TABLE StockReservation(
ReservationDate DATETIME NOT NULL, 
Quantity int NOT NULL, 
StockMaterialId INT NOT NULL, 
Foreign Key (StockMaterialId) REFERENCES StockMaterial(StockMaterialId),
PRIMARY KEY(StockMaterialId , ReservationDate),
);

CREATE TABLE Orders(
OrderNo INT IDENTITY(1,1) PRIMARY KEY,
StartDate DATETIME NOT NULL,
Deadline DATETIME NOT NULL,
EmployeeId INT NOT NULL,
CustomerNo INT NOT NULL,
IsFinished BIT NOT NULL,
FOREIGN KEY(EmployeeId) REFERENCES Employee(EmployeeId),
FOREIGN KEY(CustomerNo) REFERENCES Customer(CustomerNo),
);

CREATE TABLE OFFER(
OfferId INT IDENTITY(1,1) PRIMARY KEY,
ValidToDate DATE NOT NULL, 
DateCreated DATE NOT NULL, 
IsAccepted BIT NOT NULL, 
EmployeeId INT NOT NULL,
CustomerNo INT NOT NULL,
EstimateHours TIME NOT NULL,
FOREIGN KEY(EmployeeId) REFERENCES Employee(EmployeeId),
FOREIGN KEY(CustomerNo) REFERENCES Customer(CustomerNo),
);

CREATE TABLE Orderline(
OrderlineId INT IDENTITY(1,1) PRIMARY KEY, 
ProductNo INT NOT NULL, 
Quantity INT NOT NULL,
FOREIGN KEY (ProductNo) REFERENCES Material(ProductNo),
);

CREATE TABLE OfferOrderline(
OfferId INT NOT NULL,
OrderlineId INT NOT NULL,
FOREIGN KEY(OfferId) REFERENCES Offer(OfferId),
FOREIGN KEY (OrderlineId) REFERENCES Orderline(OrderlineId),
PRIMARY KEY(OfferId, OrderlineId),
);

CREATE TABLE SupplyOrder(
SupplyOrderId INT IDENTITY(1,1) PRIMARY KEY, 
IsDelivered BIT NOT NULL,
);

CREATE TABLE SupplierOrderline(
SupplyOrderId INT NOT NULL, 
OrderlineId INT NOT NULL,
FOREIGN KEY(SupplyOrderId) REFERENCES SupplyOrder(SupplyOrderId),
FOREIGN KEY(OrderlineId) REFERENCES Orderline(OrderlineId),
PRIMARY KEY (SupplyOrderId, OrderlineId),
);

CREATE TABLE Logs(
LogId INT IDENTITY(1,1) PRIMARY KEY,
OrderNo INT NOT NULL, 
EmployeeId INT NOT NULL,
LogTimeStamp DATETIME NOT NULL,
FOREIGN KEY (OrderNo) REFERENCES Orders(OrderNo),
FOREIGN KEY (EmployeeId) REFERENCES Employee(EmployeeId),
);

CREATE TABLE MaterialLogs(
MaterialLogId INT IDENTITY(1,1) PRIMARY KEY, 
Quantity INT NOT NULL, 
ProductNo INT NOT NULL, 
LogId INT NOT NULL,
FOREIGN KEY (LogId) REFERENCES Logs(LogId),
FOREIGN KEY (ProductNo) REFERENCES Material(ProductNo),
);
CREATE TABLE HourLogs(
HourlogId INT IDENTITY(1,1) PRIMARY KEY, 
HoursWorked DECIMAL NOT NULL, 
LogId INT NOT NULL,
FOREIGN KEY (LogId) REFERENCES Logs(LogId),
);

CREATE TABLE Inventory(
InventoryId INT IDENTITY(1,1) PRIMARY KEY
);
CREATE TABLE Locations(
LocationId INT IDENTITY(1,1) PRIMARY KEY,
InventoryId INT NOT NULL,
AddressId INT NOT NULL, 
FOREIGN KEY (InventoryId) REFERENCES Inventory(InventoryId),
FOREIGN KEY (AddressId) REFERENCES Address
);

CREATE TABLE Vans(
VanId INT IDENTITY(1,1) PRIMARY KEY,
VanLicenseplate varchar(20) NOT NULL,
InventoryId INT NOT NULL,
EmployeeId INT NOT NULL,
FOREIGN KEY (InventoryId) REFERENCES Inventory(InventoryId),
FOREIGN KEY (EmployeeId) REFERENCES Employee(EmployeeId),
);
CREATE TABLE MaterialQuantity(
StockMaterialId INT NOT NULL, 
InventoryId INT NOT NULL, 
Quantity INT NOT NULL,
FOREIGN KEY (InventoryId) REFERENCES Inventory(InventoryId),
FOREIGN KEY (stockMaterialId) REFERENCES StockMaterial(StockMaterialId),
PRIMARY KEY(StockMaterialId , InventoryId),
);