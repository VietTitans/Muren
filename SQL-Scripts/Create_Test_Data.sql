-- Insert data into Zipcodes
INSERT INTO Zipcodes (Zipcode, CityName)
VALUES
(10001, 'New York'),
(90001, 'Los Angeles'),
(60601, 'Chicago'),
(77001, 'Houston'),
(85001, 'Phoenix'),
(60091, 'USA'),
(8000, 'Aarhus'),
(1620, 'Copenhagen S'),
(2100, 'Copenhagen N'),
(2300, 'Copenhagen NV');

-- Insert data into Address
INSERT INTO Address (StreetName, BuildingNo, FloorNo, Country, Zipcode)
VALUES
('Broadway', '10A', 3, 'USA', 60091),
('Hollywood Blvd', '22B', 1, 'USA', 60091),
('Magnificent Mile', '5C', 2, 'USA', 60091),
('Main St', '7D', 0, 'USA', 60091),
('Central Ave', '15E', 4, 'USA', 60091),
('Maple Avenue', '101', 1, 'USA', 60091),
('Pine Street', '202', 2, 'Phoenix', 85001),
('Oak Road', '303', 3, 'New York', 10001),
('Cedar Lane', '404', 4, 'New York', 10001),
('Birch Boulevard', '505', 5, 'Chicago', 60601),
('Nørregade', '12A', 2, 'Denmark', 8000),
('Vesterbrogade', '45', 3, 'Denmark', 1620),
('Øster Allé', '23B', 1, 'Denmark', 2100),
('Amagerbrogade', '89', 4, 'Denmark', 2300);

-- Insert data into EmployeeType
INSERT INTO EmployeeType (EmployeeTypeName)
VALUES
('Apprentice'),
('Journeyman'),
('Owner');

-- Insert data into Person
INSERT INTO Person (FirstName, LastName, PhoneNo, Email, AddressId)
VALUES
('John', 'Doe', '12345678', 'john.doe@example.com', 1),
('Jane', 'Smith', '22222222', 'jane.smith@example.com', 2),
('Emily', 'Brown', '71024388', 'emily.brown@example.com', 3),
('Michael', 'Johnson', '20318107', 'michael.johnson@example.com', 4),
('Sarah', 'Davis', '47530941', 'sarah.davis@example.com', 5),
('Aaron', 'Smith', '88551906', 'aaron.smith@example.com', 6),
('Jeremy', 'Jones', '78123194', 'jeremy.jones@example.com', 7),
('William', 'Lee', '19532742', 'willaim.lee@example.com', 8),
('Michael', 'Scott', '91629532', 'michael.scott@example.com', 9),
('Frederik', 'Nielsen', '4512345678', 'frederik.nielsen@example.dk', 10),
('Emma', 'Hansen', '4523456789', 'emma.hansen@example.dk', 11),
('Mikkel', 'Jensen', '4534567890', 'mikkel.jensen@example.dk', 12),
('Sofie', 'Andersen', '4545678901', 'sofie.andersen@example.dk', 13);

-- Insert data into Customer
INSERT INTO Customer (PersonId)
VALUES
(1),(2),(3),(4),(5),(6),(7),(8);

-- Insert data into BusinessCustomer
INSERT INTO BusinessCustomer (CVR, BusinessName, CustomerNo)
VALUES
(100001, 'Tech Corp', 6),
(100002, 'Health Ltd', 7),
(100003, 'Retail Co', 8);

-- Insert data into Employee
INSERT INTO Employee (CPR, EmployeeTypeNo, PersonId)
VALUES
('1234567890', 1, 9),
('2345678901', 2, 10),
('3456789012', 3, 11),
('2213489012', 3, 12),
('3453389012', 3, 13);

-- Insert data into Material
INSERT INTO Material (MaterialNo, ProductName)
VALUES
(1001, 'Cement'),
(1002, 'Mursten'),
(1003, 'Fliser'),
(1004, 'Klinker'),
(1005, 'Mørtel');

-- Insert data into MaterialDescription
INSERT INTO MaterialDescription (Description, MaterialDescriptionTimeStamp, MaterialNo)
VALUES
('Dette er cement', GETDATE(), 1001),
('Dette er mursten', GETDATE(), 1002),
('Dette er store blå fliser', GETDATE(), 1003),
('Dette er marmor klinker', GETDATE(), 1004),
('Dette er mørtel fra Aalborg cement', GETDATE(), 1005);

-- Insert data into PurchasePrice
INSERT INTO PurchasePrice (Price, PurchasePriceTimeStamp, MaterialNo)
VALUES
(1200.00, GETDATE(), 1001),
(300.00, GETDATE(), 1002),
(80.00, GETDATE(), 1003),
(40.00, GETDATE(), 1004),
(150.00, GETDATE(), 1005);

-- Insert data into SalesPrice
INSERT INTO SalesPrice (Price, SalesPriceTimeStamp, MaterialNo)
VALUES
(1500.00, GETDATE(), 1001),
(400.00, GETDATE(), 1002),
(100.00, GETDATE(), 1003),
(50.00, GETDATE(), 1004),
(200.00, GETDATE(), 1005);

-- Insert data into StockMaterial
INSERT INTO StockMaterial (MaterialNo, MinStock, MaxStock, Quantity)
VALUES
(1001, 15, 75, 50 ),
(1002, 60, 150, 100);


-- Insert data into StockReservation
INSERT INTO StockReservation (ReservationDate, Quantity, StockMaterialId)
VALUES
(GETDATE(), 10, 1),
(GETDATE(), 5, 2), 
('2024-12-5', 15, 2);


--Insert data into GenericMaterial
INSERT INTO GenericMaterial (MaterialNo, ProductType)
VALUES
(1003, 'Indendørs'),
(1004, 'Indendørs'),
(1005, 'Udendørs');


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
INSERT INTO Orderline (MaterialNo, Quantity)
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
INSERT INTO MaterialLogs (Quantity, MaterialNo, LogId)
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
(2, 2, 10);
