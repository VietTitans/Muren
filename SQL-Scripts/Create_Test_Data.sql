-- Test Data for Zipcodes
INSERT INTO Zipcodes (Zipcode, CityName) VALUES 
(1000, 'Copenhagen'), 
(2000, 'Frederiksberg'),
(3000, 'Helsingør'),
(4000, 'Roskilde'),
(5000, 'Odense');

-- Test Data for Address
INSERT INTO Address (StreetName, BuildingNo, FloorNumber, Country, Zipcode) VALUES 
('Main St', '10A', 1, 'Denmark', 1000),
('Second St', '20B', 2, 'Denmark', 2000),
('Broadway', '33', NULL, 'Denmark', 3000),
('King Road', '101C', 3, 'Denmark', 4000),
('Queen Ave', '52', NULL, 'Denmark', 5000);

-- Test Data for Person
INSERT INTO Person (FirstName, LastName, phoneNo, Email, AddressId) VALUES 
('John', 'Doe', '12345678', 'john.doe@example.com', 1),
('Jane', 'Smith', '87654321', 'jane.smith@example.com', 2),
('Alice', 'Johnson', '11223344', 'alice.johnson@example.com', 3),
('Bob', 'Brown', '55667788', 'bob.brown@example.com', 4),
('Charlie', 'Taylor', '99887766', 'charlie.taylor@example.com', 5);

-- Test Data for Customer
INSERT INTO Customer (PersonId) VALUES 
(1), 
(2),
(3),
(4),
(5);

-- Test Data for BusinessCustomer
INSERT INTO BusinessCustomer (CVR, BusinessName, CustomerNo) VALUES 
(12345678, 'Tech Solutions', 1),
(87654321, 'Creative Designs', 2),
(56789012, 'BuildCorp', 3),
(34567890, 'GreenScape', 4),
(98765432, 'Skyline Builders', 5);

-- Test Data for Employee
INSERT INTO Employee (cpr, EmployeeType, PersonId) VALUES 
('1234567890', 'Manager', 1), 
('0987654321', 'Technician', 2),
('1111222233', 'Sales', 3),
('4444555566', 'Technician', 4),
('7777888899', 'Manager', 5);

-- Test Data for Material
INSERT INTO Material (ProductNo, productName) VALUES 
(1, 'Hammer'), 
(2, 'Screwdriver'),
(3, 'Wrench'),
(4, 'Drill'),
(5, 'Saw');

-- Test Data for MaterialDescription
INSERT INTO MaterialDescription (Descrip, MaterialDescriptionTimeStamp, ProductNo) VALUES 
('Heavy-duty hammer', GETDATE(), 1),
('Standard screwdriver', GETDATE(), 2),
('Adjustable wrench', GETDATE(), 3),
('Electric drill', GETDATE(), 4),
('Hand saw', GETDATE(), 5);

-- Test Data for PurchasePrice
INSERT INTO PurchasePrice (price, PurchasePriceTimeStamp, ProductNo) VALUES 
(50.00, GETDATE(), 1),
(20.00, GETDATE(), 2),
(40.00, GETDATE(), 3),
(100.00, GETDATE(), 4),
(30.00, GETDATE(), 5);

-- Test Data for SalesPrice
INSERT INTO SalesPrice (price, SalesPriceTimeStamp, ProductNo) VALUES 
(75.00, GETDATE(), 1),
(30.00, GETDATE(), 2),
(60.00, GETDATE(), 3),
(150.00, GETDATE(), 4),
(45.00, GETDATE(), 5);

-- Test Data for StockMaterial
INSERT INTO StockMaterial (ProductNo) VALUES 
(1), 
(2),
(3),
(4),
(5);

-- Test Data for StockReservation
INSERT INTO StockReservation (ReservationDate, Quantity, StockMaterialId) VALUES 
(GETDATE(), 10, 1),
(GETDATE(), 5, 2),
(GETDATE(), 15, 3),
(GETDATE(), 7, 4),
(GETDATE(), 12, 5);

-- Test Data for Orders
INSERT INTO Orders (StartDate, Deadline, EmployeeId, CustomerNo, IsFinished) VALUES 
(GETDATE(), DATEADD(DAY, 7, GETDATE()), 1, 1, 0),
(GETDATE(), DATEADD(DAY, 14, GETDATE()), 2, 2, 0),
(GETDATE(), DATEADD(DAY, 3, GETDATE()), 3, 3, 1),
(GETDATE(), DATEADD(DAY, 10, GETDATE()), 4, 4, 0),
(GETDATE(), DATEADD(DAY, 20, GETDATE()), 5, 5, 0);

-- Test Data for Offer
INSERT INTO Offer (EmployeeId, CustomerNo, EstimateHours) VALUES 
(1, 1, '10:00:00'),
(2, 2, '15:00:00'),
(3, 3, '8:30:00'),
(4, 4, '12:00:00'),
(5, 5, '20:00:00');

-- Test Data for Orderline
INSERT INTO Orderline (ProductNo, Quantity) VALUES 
(1, 2), 
(2, 3),
(3, 1),
(4, 5),
(5, 2);

-- Test Data for OfferOrderline
INSERT INTO OfferOrderline (OfferId, OrderlineId) VALUES 
(1, 1), 
(2, 2),
(3, 3),
(4, 4),
(5, 5);

-- Test Data for SupplyOrder
INSERT INTO SupplyOrder (IsDelivered) VALUES 
(0), 
(1),
(1),
(0),
(1);

-- Test Data for SupplierOrderline
INSERT INTO SupplierOrderline (SupplyOrderId, OrderlineId) VALUES 
(1, 1), 
(2, 2),
(3, 3),
(4, 4),
(5, 5);

-- Test Data for Logs
INSERT INTO Logs (OrderNo, EmployeeId, LogTimeStamp) VALUES 
(1, 1, GETDATE()),
(2, 2, GETDATE()),
(3, 3, GETDATE()),
(4, 4, GETDATE()),
(5, 5, GETDATE());

-- Test Data for MaterialLogs
INSERT INTO MaterialLogs (Quantity, ProductNo, LogId) VALUES 
(5, 1, 1), 
(10, 2, 2),
(15, 3, 3),
(7, 4, 4),
(12, 5, 5);

-- Test Data for HourLogs
INSERT INTO HourLogs (HoursWorked, LogId) VALUES 
(2.5, 1), 
(4.0, 2),
(3.5, 3),
(5.0, 4),
(6.0, 5);

-- Test Data for Inventory
INSERT INTO Inventory DEFAULT VALUES;
INSERT INTO Inventory DEFAULT VALUES;

-- Test Data for Locations
INSERT INTO Locations (InventoryId, AddressId) VALUES 
(1, 1), 
(1, 2),
(2, 3),
(2, 4),
(2, 5);

-- Test Data for Vans
INSERT INTO Vans (VanLicenseplate, InventoryId) VALUES 
('AB12345', 1),
('CD67890', 1),
('EF90123', 2),
('GH45678', 2),
('IJ78901', 2);

-- Test Data for MaterialQuantity
INSERT INTO MaterialQuantity (StockMaterialId, InventoryId, Quantity) VALUES 
(1, 1, 50), 
(2, 1, 20),
(3, 2, 15),
(4, 2, 30),
(5, 2, 25);
