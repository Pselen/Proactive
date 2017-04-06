/*STUDENTS:
 *SELEN PARLAR 150113049
 *HAMZA OLAK 150113038
 *PROACTIVE-AN IT FIRM DATABASE
*/
--TABLE TO HOLD INFORMATION ABOUT THE FIRM ITSELF.
CREATE TABLE FIRM (
	firmID tinyint NOT NULL IDENTITY(1,1),
	firmName nvarchar(20),
	firmAddress nvarchar(100),
	phoneNumber varchar(11),
	faxNumber varchar(11),
	eMail varchar(50)
);
--TABLE FOR 5 DIFFERENT DEPARRTMENT.
CREATE TABLE DEPARTMENT(
	departmentID tinyint NOT NULL IDENTITY(1,1),
	departmentName nvarchar(50),
	departmentMail varchar(50),
	firmID tinyint NOT NULL,
	departmentManagerID bigint NOT NULL,
	phoneNumber varchar(11),
);
--TABLE FOR EMPLOYEES WORK FOR THE FIRM.
 CREATE TABLE EMPLOYEE(
	SSN bigint NOT NULL,
	employeeName nvarchar(50),
	employeeAddress nvarchar(100),
	phoneNumber varchar(11),
	eMail varchar(50),
	departmentID tinyint NOT NULL,
	startTime date,
	workingTime int,
	officeID tinyint NOT NULL,
    extraMoney int NULL
);
--TABLE FOR OFFICE INFORMATION OF OFFICES.
CREATE TABLE OFFICE(
	officeID  tinyint NOT NULL IDENTITY(1,1),
	officeName nvarchar(50),
	phoneNumber varchar(11),
);
--TABLE FOR PROJECTS THAT THE FIRM WORK ON.
CREATE TABLE PROJECT(
	projectID int NOT NULL IDENTITY(1,1),
	projectName nvarchar(50),
	importanceID tinyint CONSTRAINT checkconstraint_importanceID CHECK(importanceID BETWEEN 1 AND 5) NOT NULL,
	startDate date,
	dueDate date,
	timeLeft int,
	projectManagerID bigint NOT NULL,
	departmentID tinyint NOT NULL,
	explanation nvarchar(120)
);
--TABLE FOR HOLD THE INFORMATION ABOUT THE IMPORTANCES OF PROJECTS.
CREATE TABLE IMPORTANCE (
	importanceID tinyint NOT NULL IDENTITY(1,1),
	importanceDegree tinyint,
	importanceName nvarchar(20)
);
--TABLE FOR CUSTOMER INFORMATION.
CREATE TABLE CUSTOMER(
    customerID int NOT NULL IDENTITY(1,1),
    customerFirmName nvarchar(50),
    customerAddress nvarchar(100),
    phoneNumber varchar(11),
    faxNumber varchar(11),
    eMail varchar(50),
    explanation nvarchar(100),
	requiredTime int NULL
);
--TABLE FOR DEALS BETWEEN THE FIRM AND COMPANIES.
CREATE TABLE AGREEMENT(
    agreementID int NOT NULL IDENTITY(1,1),
    projectID int NOT NULL,
    customerID int NOT NULL,
    agreementDate date,
);
--TABLE FOR THE INVOICE OF EACH AGREEMENT.
CREATE TABLE INVOICE (
	invoiceID int NOT NULL IDENTITY(1,1),
	agreementID int NOT NULL,
	amount money CONSTRAINT defmoney DEFAULT (0) NOT NULL,
	explanation nvarchar(120)
);
--TABLE FOR SHOWING THE RELATIONSHIP BETWEEN EMPLOYEES AND PROJECTS THAT THEY WORK ON.
CREATE TABLE WORKSON(
	employeeID bigint NOT NULL,
	projectID int NOT NULL,
);
--TABLE FOR SHOWING RELATIONSHIP BETWEEN EMPLOYEES AND THEIR SUPERVISERS.
CREATE TABLE SUPERVISOR_EMPLOYEE(
	SSN bigint NOT NULL,
	supervisorID bigint
);
--TABLE FOR VERSIONS OF PROJECTS.
CREATE TABLE MODULE(
    moduleID int NOT NULL IDENTITY(1,1),
    moduleName nvarchar(50),
    startDate date,
    dueDate date,
    statusID tinyint CONSTRAINT defstatusID DEFAULT (1),
    explanation nvarchar(120),
	moduleManagerID bigint NOT NULL,
	projectID int NOT NULL
);
--TABLE FOR SHOWING THE PROCESSES OF A WORK.
CREATE TABLE STATUS(
    statusID tinyint NOT NULL IDENTITY(1,1),
    explanation nvarchar(120)
);

--PRIMARY KEYS.
ALTER TABLE FIRM
ADD CONSTRAINT pk_firmID PRIMARY KEY (firmID)

ALTER TABLE DEPARTMENT
ADD CONSTRAINT pk_departmentID PRIMARY KEY (departmentID)

ALTER TABLE EMPLOYEE
ADD CONSTRAINT pk_SSN PRIMARY KEY (SSN)

ALTER TABLE OFFICE
ADD CONSTRAINT pk_officeID PRIMARY KEY (officeID)

ALTER TABLE PROJECT
ADD CONSTRAINT pk_projectID PRIMARY KEY (projectID)

ALTER TABLE IMPORTANCE
ADD CONSTRAINT pk_importanceID PRIMARY KEY (importanceID)

ALTER TABLE CUSTOMER
ADD CONSTRAINT pk_customerID PRIMARY KEY (customerID)

ALTER TABLE AGREEMENT
ADD CONSTRAINT pk_agreementID PRIMARY KEY (agreementID)

ALTER TABLE INVOICE
ADD CONSTRAINT pk_invoiceID PRIMARY KEY (invoiceID)

ALTER TABLE WORKSON
ADD CONSTRAINT pk_empproj PRIMARY KEY (employeeID,projectID)

ALTER TABLE SUPERVISOR_EMPLOYEE
ADD CONSTRAINT pk_supemp PRIMARY KEY (SSN)

ALTER TABLE MODULE
ADD CONSTRAINT pk_moduleID PRIMARY KEY (moduleID)

ALTER TABLE STATUS
ADD CONSTRAINT pk_statusID PRIMARY KEY (statusID)
--PRIMARY KEYS.
--FOREIGN KEYS.
ALTER TABLE DEPARTMENT ADD CONSTRAINT fk_DeptFirm FOREIGN KEY(firmID) REFERENCES FIRM(firmID)
ALTER TABLE DEPARTMENT ADD CONSTRAINT fk_DeptEmp FOREIGN KEY(departmentManagerID) REFERENCES EMPLOYEE(SSN)

ALTER TABLE EMPLOYEE ADD CONSTRAINT fk_EmpDep FOREIGN KEY (departmentID) REFERENCES DEPARTMENT(departmentID)
ALTER TABLE EMPLOYEE ADD CONSTRAINT fk_EmpOff FOREIGN KEY (officeID) REFERENCES OFFICE(officeID)

ALTER TABLE PROJECT ADD CONSTRAINT fk_ProjEmp FOREIGN KEY (projectManagerID) REFERENCES EMPLOYEE(SSN)
ALTER TABLE PROJECT ADD CONSTRAINT fk_ProjDep FOREIGN KEY (departmentID) REFERENCES DEPARTMENT(departmentID)
ALTER TABLE PROJECT ADD CONSTRAINT fk_ProjImp FOREIGN KEY (importanceID) REFERENCES IMPORTANCE(importanceID)

ALTER TABLE AGREEMENT ADD CONSTRAINT fk_AgrPro FOREIGN KEY (projectID) REFERENCES PROJECT(projectID)
ALTER TABLE AGREEMENT ADD CONSTRAINT fk_AgrCus FOREIGN KEY (customerID) REFERENCES CUSTOMER(customerID)

ALTER TABLE INVOICE ADD CONSTRAINT fk_InvAgr FOREIGN KEY (agreementID) REFERENCES AGREEMENT(agreementID)

ALTER TABLE WORKSON ADD CONSTRAINT fk_WorkEmp FOREIGN KEY (employeeID) REFERENCES EMPLOYEE(SSN)
ALTER TABLE WORKSON ADD CONSTRAINT fk_WorkProj FOREIGN KEY (projectID) REFERENCES PROJECT(projectID)

ALTER TABLE MODULE ADD CONSTRAINT fk_ModSta FOREIGN KEY (statusID) REFERENCES STATUS(statusID)
ALTER TABLE MODULE ADD CONSTRAINT fk_ModEmp FOREIGN KEY (moduleManagerID) REFERENCES EMPLOYEE(SSN)
ALTER TABLE MODULE ADD CONSTRAINT fk_ModpROJ FOREIGN KEY (projectID) REFERENCES PROJECT(projectID)
--FOREIGN KEYS.

--UNIQUE, CHECK CONSTRAINT AND COMPUTED COLUMNS.
UPDATE EMPLOYEE
SET workingTime=DATEDIFF(DAY,startTime,GETDATE())

ALTER TABLE FIRM 
ADD CONSTRAINT unique_firm UNIQUE (firmName); 

ALTER TABLE DEPARTMENT
ADD CONSTRAINT unique_department UNIQUE (departmentName, departmentManagerID); 

ALTER TABLE OFFICE
ADD CONSTRAINT unique_office UNIQUE (officeName); 

ALTER TABLE PROJECT
ADD CONSTRAINT unique_project UNIQUE (projectName); 

ALTER TABLE MODULE
ADD CONSTRAINT unique_module UNIQUE (moduleName,projectID);

ALTER TABLE IMPORTANCE
ADD CONSTRAINT unique_importance UNIQUE (importanceDegree,importanceName); 

ALTER TABLE CUSTOMER
ADD CONSTRAINT unique_customer UNIQUE (customerFirmName); 

ALTER TABLE MODULE
ADD CONSTRAINT checkconstraint_statusID CHECK (statusID BETWEEN 1 AND 3);
--UNIQUE, CHECK CONSTRAINT AND COMPUTED COLUMNS.

--VIEW.
--VIEW FOR SHOWING CURRENTLY CONTINUES PROJECTS.
GO
CREATE VIEW [Current Projects] AS
SELECT	CONCAT(m.moduleID, ' - ', m.moduleName) AS Module, 
		CONCAT(m.moduleManagerID, ' - ', e.employeeName) AS Manager, 
		CONCAT(m.projectID, ' - ', p.projectName) AS Project
FROM MODULE m, PROJECT p, EMPLOYEE e
WHERE m.dueDate>GETDATE() AND m.projectID=p.projectID AND m.moduleManagerID=e.SSN

--VIEW FOR SHOWING PROJECTS' AMOUNT WHICH HAS HIGHER THAN THE 
--AVERAGE AMOUNT AND ALSO HAS THE IMPORTANCE DEGREE OF 3.
GO
CREATE VIEW [Projects Above Average Price] AS
SELECT	CONCAT(p.projectName, '-', c.customerFirmName) AS Project,
		i.amount AS [Price (TL)], p.dueDate AS [Deadline], 
		imp.importanceName AS [Importance]
FROM INVOICE i, AGREEMENT a,IMPORTANCE imp, PROJECT p, CUSTOMER c
WHERE	i.agreementID=a.agreementID AND a.projectID=p.projectID 
		AND imp.importanceID=p.importanceID AND 
		imp.importanceDegree>=3 AND c.customerID=a.customerID
GROUP BY p.projectName, i.amount,  p.dueDate, imp.importanceName, c.customerFirmName
HAVING i.amount> (SELECT AVG(amount) FROM INVOICE)

--VIEW FOR SHOWING TOP 2 EMPLOYEES WHO HAVE THE MAXIMUM EXPERIENCE.
GO
CREATE VIEW [TOP 2 EMPLOYEES] AS 
SELECT TOP 2 RANK() OVER (ORDER BY e.workingTime DESC) AS [Employee Order], 
		e.employeeName AS Employee, d.departmentName AS Depoartment
FROM EMPLOYEE e, DEPARTMENT d, FIRM f 
WHERE f.firmID=d.firmID AND d.departmentID=e.departmentID
GROUP BY d.departmentName, e.workingTime , e.employeeName
HAVING e.workingTime> (SELECT AVG(workingTime) FROM EMPLOYEE)

--VIEW FOR SHOWING EMPLOYEES WHO WORK FOR THE COMPANIES LOCATED IN ISTANBUL.
GO
CREATE VIEW [Customers Located in İstanbul, and Employees Work for Them] AS 
SELECT c.customerFirmName AS [Firm], p.projectName AS Project, e.employeeName AS Employee
FROM EMPLOYEE e, PROJECT p, CUSTOMER c,AGREEMENT a, WORKSON w
WHERE e.SSN=w.employeeID AND w.projectID=p.projectID AND p.projectID=a.projectID AND 
	a.customerID=c.customerID AND c.customerAddress LIKE '%stanbul%'
--VIEW.

--INDEX
GO
CREATE NONCLUSTERED INDEX IX_projectName
ON PROJECT(projectName)
--INDEX

--TRIGGER
CREATE TABLE transactionLog (
	logID int identity(1,1) primary key,
	logTime datetime,
	logType char(1),
	beforeState nvarchar(500),
	afterStare nvarchar(500)
);


GO
CREATE TRIGGER trg_rearrangeProjects ON PROJECT
AFTER INSERT, DELETE, UPDATE
AS
BEGIN
	IF @@ROWCOUNT=0
	RAISERROR('Row cannot be found',16,1)

	DECLARE @id bigint, @time date
	SET @time=(SELECT inserted.startDate FROM inserted)

	IF @time NOT BETWEEN '2013-01-01' AND GETDATE()
		BEGIN
		ROLLBACK TRANSACTION
		RAISERROR ('Invalid date.',16,1)
	END

	IF NOT EXISTS(SELECT * FROM deleted)
	BEGIN
		SET @id=(select inserted.projectID FROM inserted )
		IF @id NOT IN(
			SELECT p.projectID
			FROM PROJECT p, inserted i
			WHERE p.projectID=i.projectID AND p.startDate>='2013-01-01')
		BEGIN
			ROLLBACK TRANSACTION
			RAISERROR('Invalid operation',16,1)
		END
	END

	DECLARE @action char(1), @beforeState nvarchar(250), @afterState nvarchar(250);

	IF EXISTS (SELECT * FROM deleted)
	BEGIN
		IF EXISTS(SELECT * FROM inserted)
		BEGIN
			SET @action='U'
			SET @beforeState=(SELECT CONCAT(d.projectID, ',' , d.projectName, ',' , d.departmentID, ',' , d.projectManagerID, ',', d.startDate, ',' , d.dueDate) FROM deleted d)
			SET @afterState=(SELECT CONCAT(i.projectID, ',' , i.projectName, ',' , i.departmentID, ',' , i.projectManagerID, ',', i.startDate, ',' , i.dueDate) FROM inserted i)

			INSERT INTO transactionLog
			SELECT SYSDATETIME(), @action, @beforeState, @afterState
		END

		ELSE
		BEGIN
			
			SET @action='D'
			SET @beforeState=(SELECT CONCAT(d.projectID, ',' , d.projectName, ',' , d.departmentID, ',' , d.projectManagerID, ',', d.startDate, ',' , d.dueDate) FROM deleted d)
			SET @afterState=NULL
			INSERT INTO transactionLog
			SELECT SYSDATETIME(), @action,@beforeState, @afterState
		END
	END

	ELSE 
	BEGIN
		SET @action='I'
		SET @beforeState=NULL
		SET @afterState=(SELECT CONCAT(i.projectID, ',' , i.projectName, ',' , i.departmentID, ',' , i.projectManagerID, ',', i.startDate, ',' , i.dueDate) FROM inserted i)
		INSERT INTO transactionLog
		SELECT SYSDATETIME(), @action, @beforeState, @afterState
	END
END
--TRIGGER
UPDATE PROJECT SET importanceID = 3 WHERE projectID=8
select * from transactionLog

--PROCEDURES
--PROCEDURE FOR FINDING THE TIME LEFT FOR THE DEADLINE OF A GIVEN PROJECT.
GO
CREATE PROCEDURE sp_getTimeLeft
	@projectID bigint
AS
BEGIN
	DECLARE @time int
	IF @projectID NOT IN(SELECT projectID FROM PROJECT)
	BEGIN
		RAISERROR('Id not found.',16,1)
	END
	SET @time = (SELECT DATEDIFF(DAY,GETDATE(),dueDate) 
				from PROJECT WHERE PROJECT.projectID=@projectID)
	IF (@time<=0)
	BEGIN
		UPDATE PROJECT
		SET timeLeft = 0 WHERE PROJECT.projectID=@projectID
	END
	ELSE
	BEGIN
		UPDATE PROJECT
		SET timeLeft = @time WHERE PROJECT.projectID=@projectID
	END
END
--EXEC sp_getTimeLeft '8'
--drop proc sp_getTimeLeft

--PROCEDURE FOR FINDING THE STATUS OF A GIVEN PROJECT.
GO
CREATE PROCEDURE sp_getStatus
	@moduleID int
AS
BEGIN
	DECLARE @statusID tinyint , @time int
	IF @moduleID NOT IN (SELECT moduleID FROM MODULE)
	BEGIN
		RAISERROR('Id not found.',16,1)
	END
	SET @time = (SELECT DATEDIFF(DAY, GETDATE(), dueDate) 
				FROM MODULE WHERE MODULE.moduleID=@moduleID)
	IF (@time < 0)
	BEGIN
		UPDATE MODULE 
		SET statusID = 3 WHERE MODULE.moduleID=@moduleID
	END
	ELSE IF (@time BETWEEN -60 AND 0)
	BEGIN
		UPDATE MODULE 
		SET statusID = 2 WHERE  MODULE.moduleID=@moduleID
	END
	ELSE
	BEGIN
		UPDATE MODULE 
		SET statusID = 1 WHERE MODULE.moduleID=@moduleID
	END
END
--EXEC sp_getStatus '2'		
--DROP PROC sp_getStatus

--PROCEDURE FOR FINDING THE AVERAGE TIME TO GO FOR A PRESENTATION 
--TO COMPANIES THAT LOCATED IN DIFFERENT CITIES.
GO
CREATE PROCEDURE sp_getDistance
	@customerID int, @pace int 
AS 
BEGIN
	DECLARE @address nvarchar(200), @distance int, @result int
	IF @customerID NOT IN (SELECT customerID FROM CUSTOMER)
	BEGIN
		RAISERROR('No such customer.',16,1)
	END
	SET @address = (SELECT customerAddress FROM CUSTOMER WHERE customerID=@customerID)
		IF @address LIKE '%stanbul%'
		BEGIN
			SET @distance = 566
			SET @result= CONVERT(FLOAT,@distance)/ CONVERT(FLOAT,@pace)
			UPDATE CUSTOMER
			SET requiredTime = @result WHERE customerID=@customerID
		END
		ELSE IF @address LIKE '%Kahraraman%'
		BEGIN
			SET @distance = 197
			SET @result= (@distance / @pace)
			UPDATE CUSTOMER
			SET requiredTime = @result WHERE customerID=@customerID
		END
		ELSE IF @address LIKE '%Hatay%'
		BEGIN
			SET @distance = 192
			SET @result= (@distance / @pace)
			UPDATE CUSTOMER
			SET requiredTime = @result WHERE customerID=@customerID
		END
		ELSE IF @address LIKE '%Kayseri%'
		BEGIN
			SET @distance = 303
			SET @result= (@distance / @pace)
			UPDATE CUSTOMER
			SET requiredTime = @result WHERE customerID=@customerID
		END
		ELSE IF @address LIKE '%Diyaryarbak%'
		BEGIN
			SET @distance = 326
			SET @result= (@distance / @pace)
			UPDATE CUSTOMER
			SET requiredTime = @result WHERE customerID=@customerID
		END
		ELSE IF @address LIKE '%Ankara%'
		BEGIN
			SET @distance = 498
			SET @result= (@distance / @pace)
			UPDATE CUSTOMER
			SET requiredTime = @result WHERE customerID=@customerID
		END
		ELSE IF @address LIKE '%zmir%'
		BEGIN
			SET @distance = 896
			SET @result= (@distance / @pace)
			UPDATE CUSTOMER
			SET requiredTime = @result WHERE customerID=@customerID
		END
		ELSE IF @address LIKE '%Bursa%'
		BEGIN
			SET @distance = 822
			SET @result= (@distance / @pace)
			UPDATE CUSTOMER
			SET requiredTime = @result WHERE customerID=@customerID
		END
		ELSE
		BEGIN
			UPDATE CUSTOMER
			SET requiredTime = 0 WHERE customerID=@customerID
		END
		SELECT CONVERT(FLOAT,requiredTime) AS [Required Time (h)] FROM CUSTOMER
END
--EXEC sp_getDistance '10', '100'
--drop proc sp_getDistance

--PROCEDURE FOR CALCULATING THE EXTRA MONEY THAT AN EMPLOYEE CAN GAIN FOR DOING SOME JOBS.
GO
CREATE PROCEDURE sp_getExtraMoney
	@employeeID bigint 
AS 
BEGIN
	DECLARE @extraForTime int, @extraForSupervise int, @workingYear int, @extraMoney int, @extraForManage int
	IF @employeeID NOT IN(SELECT SSN FROM EMPLOYEE)
	BEGIN
		RAISERROR('No such employee.',16,1)
	END
	SELECT @workingYear= (SELECT workingTime FROM EMPLOYEE WHERE SSN=@employeeID)*250--İŞ GÜNÜ SAYISI
	IF @workingYear > 3 BEGIN SET @extraForTime = 500 PRINT 'YEAR' END
	ELSE SET @extraForTime=0

	IF @employeeID IN(SELECT supervisorID FROM SUPERVISOR_EMPLOYEE) BEGIN SET @extraForSupervise=500 PRINT 'SUPER' END
	ELSE SET @extraForSupervise=0

	IF @employeeID IN(SELECT d.departmentManagerID FROM DEPARTMENT d WHERE @employeeID=d.departmentManagerID)
	BEGIN 
	SET @extraForManage = 750  PRINT '' END
	ELSE SET @extraForManage=0

	SET @extraMoney=@extraForManage+@extraForSupervise+@extraForTime
	UPDATE EMPLOYEE
	SET extraMoney = @extraMoney FROM EMPLOYEE e WHERE e.SSN=@employeeID 
	SELECT extraMoney from EMPLOYEE
END

--exec sp_getExtraMoney '7891234567891'
--drop proc sp_getExtraMoney

--END OF THE QUERY.