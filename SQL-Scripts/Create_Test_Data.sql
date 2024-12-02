-- Insert data into Zipcodes
INSERT INTO Zipcodes (Zipcode, CityName)
VALUES
(10001, 'New York'),
(90001, 'Los Angeles'),
(60601, 'Chicago'),
(77001, 'Houston'),
(85001, 'Phoenix');

-- Insert data into Address
INSERT INTO Address (StreetName, BuildingNo, FloorNo, Country, Zipcode)
VALUES
('Broadway', '10A', 3, 'USA', 10001),
('Hollywood Blvd', '22B', 1, 'USA', 90001),
('Magnificent Mile', '5C', 2, 'USA', 60601),
('Main St', '7D', NULL, 'USA', 77001),
('Central Ave', '15E', 4, 'USA', 85001);

-- Insert data into EmployeeType
INSERT INTO EmployeeType (EmployeeTypeName)
VALUES
('Manager'),
('Technician'),
('Salesperson'),
('Driver');

-- Insert data into Person
INSERT INTO Person (FirstName, LastName, PhoneNo, Email, AddressId)
VALUES
('John', 'Doe', '123-456-7890', 'john.doe@example.com', 1),
('Jane', 'Smith', '987-654-3210', 'jane.smith@example.com', 2),
('Emily', 'Brown', '555-123-4567', 'emily.brown@example.com', 3),
('Michael', 'Johnson', '444-555-6666', 'michael.johnson@example.com', 4),
('Sarah', 'Davis', '111-222-3333', 'sarah.davis@example.com', 5);

-- Insert data into Customer
INSERT INTO Customer (PersonId)
VALUES
(1),
(2),
(3),
(4),
(5);

-- Insert data into BusinessCustomer
INSERT INTO BusinessCustomer (CVR, BusinessName, CustomerNo)
VALUES
(100001, 'Tech Corp', 1),
(100002, 'Health Ltd', 2),
(100003, 'Retail Co', 3),
(100004, 'Logistics LLC', 4),
(100005, 'Edu Ventures', 5);

-- Insert data into Employee
INSERT INTO Employee (CPR, EmployeeTypeNo, PersonId)
VALUES
('1234567890', 1, 1),
('2345678901', 2, 2),
('3456789012', 3, 3),
('4567890123', 4, 4),
('5678901234', 2, 5);

-- Insert data into Material
INSERT INTO Material (ProductNo, ProductName)
VALUES
(1001, 'Laptop'),
(1002, 'Monitor'),
(1003, 'Keyboard'),
(1004, 'Mouse'),
(1005, 'Printer');

-- Insert data into MaterialDescription
INSERT INTO MaterialDescription (Description, MaterialDescriptionTimeStamp, ProductNo)
VALUES
('High-performance laptop', GETDATE(), 1001),
('4K UHD monitor', GETDATE(), 1002),
('Mechanical keyboard', GETDATE(), 1003),
('Wireless mouse', GETDATE(), 1004),
('All-in-one printer', GETDATE(), 1005);

-- Insert data into PurchasePrice
INSERT INTO PurchasePrice (Price, PurchasePriceTimeStamp, ProductNo)
VALUES
(1200.00, GETDATE(), 1001),
(300.00, GETDATE(), 1002),
(80.00, GETDATE(), 1003),
(40.00, GETDATE(), 1004),
(150.00, GETDATE(), 1005);

-- Insert data into SalesPrice
INSERT INTO SalesPrice (Price, SalesPriceTimeStamp, ProductNo)
VALUES
(1500.00, GETDATE(), 1001),
(400.00, GETDATE(), 1002),
(100.00, GETDATE(), 1003),
(50.00, GETDATE(), 1004),
(200.00, GETDATE(), 1005);

-- Insert data into StockMaterial
INSERT INTO StockMaterial (ProductNo)
VALUES
(1001),
(1002),
(1003),
(1004),
(1005);

-- Insert data into StockReservation
INSERT INTO StockReservation (ReservationDate, Quantity, StockMaterialId)
VALUES
(GETDATE(), 10, 1),
(GETDATE(), 5, 2),
(GETDATE(), 15, 3),
(GETDATE(), 20, 4),
(GETDATE(), 8, 5);

-- Insert data into Orders
INSERT INTO Orders (StartDate, Deadline, EmployeeId, CustomerNo, IsFinished)
VALUES
(GETDATE(), DATEADD(DAY, 5, GETDATE()), 1, 1, 0),
(GETDATE(), DATEADD(DAY, 10, GETDATE()), 2, 2, 0),
(GETDATE(), DATEADD(DAY, 7, GETDATE()), 3, 3, 1),
(GETDATE(), DATEADD(DAY, 3, GETDATE()), 4, 4, 0),
(GETDATE(), DATEADD(DAY, 14, GETDATE()), 5, 5, 1);

-- Insert data into OFFER
INSERT INTO OFFER (ValidToDate, DateCreated, IsAccepted, EmployeeId, CustomerNo, EstimateHours)
VALUES
(DATEADD(DAY, 30, GETDATE()), GETDATE(), 1, 1, 1, '10:00'),
(DATEADD(DAY, 20, GETDATE()), GETDATE(), 0, 2, 2, '15:00'),
(DATEADD(DAY, 25, GETDATE()), GETDATE(), 1, 3, 3, '20:00'),
(DATEADD(DAY, 10, GETDATE()), GETDATE(), 0, 4, 4, '12:00'),
(DATEADD(DAY, 15, GETDATE()), GETDATE(), 1, 5, 5, '8:00');

-- Insert data into Orderline
INSERT INTO Orderline (ProductNo, Quantity)
VALUES
(1001, 2),
(1002, 4),
(1003, 1),
(1004, 3),
(1005, 2);

-- Insert data into OfferOrderline
INSERT INTO OfferOrderline (OfferId, OrderlineId)
VALUES
(1, 1),
(2, 2),
(3, 3),
(4, 4),
(5, 5);

-- Insert data into SupplyOrder
INSERT INTO SupplyOrder (IsDelivered)
VALUES
(1),
(0),
(1),
(0),
(1);

-- Insert data into SupplierOrderline
INSERT INTO SupplierOrderline (SupplyOrderId, OrderlineId)
VALUES
(1, 1),
(2, 2),
(3, 3),
(4, 4),
(5, 5);

-- Insert data into Logs
INSERT INTO Logs (OrderNo, EmployeeId, LogTimeStamp)
VALUES
(1, 1, GETDATE()),
(2, 2, GETDATE()),
(3, 3, GETDATE()),
(4, 4, GETDATE()),
(5, 5, GETDATE());

-- Insert data into MaterialLogs
INSERT INTO MaterialLogs (Quantity, ProductNo, LogId)
VALUES
(5, 1001, 1),
(3, 1002, 2),
(8, 1003, 3),
(2, 1004, 4),
(6, 1005, 5);

-- Insert data into HourLogs
INSERT INTO HourLogs (HoursWorked, LogId)
VALUES
(8.5, 1),
(6.0, 2),
(7.25, 3),
(5.5, 4),
(9.0, 5);

-- Insert data into Inventory
INSERT INTO Inventory DEFAULT VALUES;
INSERT INTO Inventory DEFAULT VALUES;

-- Insert data into Locations
INSERT INTO Locations (InventoryId, AddressId)
VALUES
(1, 1),
(2, 2);

-- Insert data into Vans
INSERT INTO Vans (VanLicenseplate, InventoryId, EmployeeId)
VALUES
('ABC123', 1, 1),
('XYZ789', 2, 2);

-- Insert data into MaterialQuantity
INSERT INTO MaterialQuantity (StockMaterialId, InventoryId, Quantity)
VALUES
(1, 1, 20),
(2, 2, 10),
(3, 1, 15),
(4, 2, 25),
(5, 1, 5);
