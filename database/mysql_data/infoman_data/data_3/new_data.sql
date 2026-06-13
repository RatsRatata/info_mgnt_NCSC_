
-- change data to your database name
USE data;

DROP TABLE IF EXISTS `client_hr_profile`;
DROP TABLE IF EXISTS `client_relationship`;
DROP TABLE IF EXISTS `client_info`;

CREATE TABLE `client_info` (
  `reference_code` varchar(20) NOT NULL,
  `name` varchar(100) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  `birth_date` date DEFAULT NULL,
  `birth_place` varchar(100) DEFAULT NULL,
  `marital_status` varchar(20) DEFAULT NULL,
  `sex` varchar(10) DEFAULT NULL,
  `contact_number` varchar(20) DEFAULT NULL,
  `email_address` varchar(100) DEFAULT NULL,
  `religion` varchar(50) DEFAULT NULL,
  `ethnicity` varchar(50) DEFAULT NULL,
  `language_spoken` varchar(50) DEFAULT NULL,
  `osca_id_num` varchar(50) DEFAULT NULL,
  `gsis_sss_number` varchar(50) DEFAULT NULL,
  `tin_num` varchar(50) DEFAULT NULL,
  `philhealth_num` varchar(50) DEFAULT NULL,
  `sc_association_id` varchar(50) DEFAULT NULL,
  `other_gov_id` varchar(50) DEFAULT NULL,
  `travel_capability` varchar(50) DEFAULT NULL,
  `job` varchar(50) DEFAULT NULL,
  `current_pension` varchar(50) DEFAULT NULL,
  `highest_educational_attainment` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`reference_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `client_hr_profile` (
  `skill_id` int NOT NULL AUTO_INCREMENT,
  `reference_code` varchar(20) NOT NULL,
  `technical_skills` varchar(50) DEFAULT NULL,
  `community_service` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`skill_id`),
  CONSTRAINT `fk_hr_ref_code` FOREIGN KEY (`reference_code`) REFERENCES `client_info` (`reference_code`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `client_relationship` (
  `relative_id` int NOT NULL AUTO_INCREMENT,
  `reference_code` varchar(20) NOT NULL,
  `relative_name` varchar(50) DEFAULT NULL,
  `relationship` varchar(20) DEFAULT NULL,
  `relative_age` int DEFAULT NULL,
  `working_status` varchar(3) DEFAULT NULL,
  `occupation` varchar(50) DEFAULT NULL,
  `income` decimal(10,2) DEFAULT '0.00',
  PRIMARY KEY (`relative_id`),
  CONSTRAINT `fk_rel_ref_code` FOREIGN KEY (`reference_code`) REFERENCES `client_info` (`reference_code`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

INSERT INTO `client_info` VALUES
('REF-2026-001', 'Eduardo Bautista', '12 Sampaguita St, Manila', '1955-04-12', 'Manila', 'Married', 'Male', '0917-111-2222', 'eduardo.b@email.com', 'Catholic', 'Tagalog', 'Tagalog, English', 'OSCA-001', 'GSIS-1001', 'TIN-2001', 'PHIL-3001', 'SCA-4001', 'VOTER-5001', 'Independent', 'Retired Teacher', '12000', 'College Graduate'),
('REF-2026-002', 'Flora Mercado', '34 Rosal St, Cebu City', '1958-08-25', 'Cebu', 'Widowed', 'Female', '0918-222-3333', 'flora.m@email.com', 'Catholic', 'Cebuano', 'Cebuano, Tagalog', 'OSCA-002', 'SSS-1002', 'TIN-2002', 'PHIL-3002', 'SCA-4002', 'VOTER-5002', 'Needs Assistance', 'Retired Tailor', '8500', 'High School Graduate'),
('REF-2026-003', 'Ramon Santos', '56 Ilang-Ilang Ave, Quezon City', '1950-11-03', 'Bulacan', 'Married', 'Male', '0919-333-4444', 'ramon.s@email.com', 'Iglesia ni Cristo', 'Tagalog', 'Tagalog', 'OSCA-003', 'SSS-1003', 'TIN-2003', 'PHIL-3003', 'SCA-4003', 'VOTER-5003', 'Independent', 'Former Driver', '5000', 'High School Level'),
('REF-2026-004', 'Luzviminda Cruz', '78 Mabini St, Davao City', '1961-01-15', 'Davao', 'Single', 'Female', '0920-444-5555', 'luz.cruz@email.com', 'Catholic', 'Bisaya', 'Bisaya, English', 'OSCA-004', 'GSIS-1004', 'TIN-2004', 'PHIL-3004', 'SCA-4004', 'VOTER-5004', 'Independent', 'Retired Nurse', '18000', 'College Graduate'),
('REF-2026-005', 'Nestor Reyes', '90 Rizal Ave, Makati', '1948-07-22', 'Pampanga', 'Widowed', 'Male', '0921-555-6666', 'nestor.r@email.com', 'Catholic', 'Kapampangan', 'Kapampangan, Tagalog', 'OSCA-005', 'SSS-1005', 'TIN-2005', 'PHIL-3005', 'SCA-4005', 'VOTER-5005', 'Bedridden', 'Farmer', '3000', 'Elementary Graduate'),
('REF-2026-006', 'Carmela Fernandez', '12 Bonifacio St, Baguio', '1959-12-05', 'Benguet', 'Married', 'Female', '0922-666-7777', 'carmela.f@email.com', 'Protestant', 'Ilocano', 'Ilocano, English', 'OSCA-006', 'GSIS-1006', 'TIN-2006', 'PHIL-3006', 'SCA-4006', 'VOTER-5006', 'Independent', 'Business Owner', '25000', 'College Graduate'),
('REF-2026-007', 'Arturo Gonzales', '34 Luna St, Iloilo City', '1953-09-18', 'Iloilo', 'Married', 'Male', '0927-777-8888', 'arturo.g@email.com', 'Catholic', 'Ilonggo', 'Hiligaynon, Tagalog', 'OSCA-007', 'SSS-1007', 'TIN-2007', 'PHIL-3007', 'SCA-4007', 'VOTER-5007', 'Needs Assistance', 'Carpenter', '4000', 'High School Graduate'),
('REF-2026-008', 'Teresita Villanueva', '56 Aguinaldo St, Bacolod', '1956-02-28', 'Negros', 'Separated', 'Female', '0928-888-9999', 'tere.v@email.com', 'Catholic', 'Ilonggo', 'Hiligaynon', 'OSCA-008', 'SSS-1008', 'TIN-2008', 'PHIL-3008', 'SCA-4008', 'VOTER-5008', 'Independent', 'Vendor', '3500', 'High School Level'),
('REF-2026-009', 'Ricardo Mendoza', '78 Del Pilar St, Naga', '1951-05-10', 'Camarines Sur', 'Married', 'Male', '0929-999-0000', 'ricardo.m@email.com', 'Catholic', 'Bicolano', 'Bicolano, Tagalog', 'OSCA-009', 'GSIS-1009', 'TIN-2009', 'PHIL-3009', 'SCA-4009', 'VOTER-5009', 'Independent', 'Retired Police', '22000', 'College Graduate'),
('REF-2026-010', 'Elena Castro', '90 Roxas Blvd, Pasay', '1960-10-14', 'Manila', 'Single', 'Female', '0930-000-1111', 'elena.c@email.com', 'Born Again', 'Tagalog', 'Tagalog, English', 'OSCA-010', 'SSS-1010', 'TIN-2010', 'PHIL-3010', 'SCA-4010', 'VOTER-5010', 'Independent', 'Accountant', '15000', 'College Graduate'),
('REF-2026-011', 'Domingo Perez', '11 Taft Ave, Manila', '1945-03-30', 'Pangasinan', 'Widowed', 'Male', '0931-111-2222', 'domingo.p@email.com', 'Catholic', 'Ilocano', 'Ilocano', 'OSCA-011', 'SSS-1011', 'TIN-2011', 'PHIL-3011', 'SCA-4011', 'VOTER-5011', 'Bedridden', 'Fisherman', '2000', 'Elementary Level'),
('REF-2026-012', 'Rosario Aquino', '22 Espana Blvd, Manila', '1957-06-20', 'Tarlac', 'Married', 'Female', '0932-222-3333', 'rosario.a@email.com', 'Catholic', 'Kapampangan', 'Tagalog', 'OSCA-012', 'GSIS-1012', 'TIN-2012', 'PHIL-3012', 'SCA-4012', 'VOTER-5012', 'Independent', 'Teacher', '14000', 'College Graduate'),
('REF-2026-013', 'Francisco Garcia', '33 Quezon Ave, QC', '1952-12-11', 'Manila', 'Married', 'Male', '0933-333-4444', 'kiko.g@email.com', 'Catholic', 'Tagalog', 'Tagalog', 'OSCA-013', 'SSS-1013', 'TIN-2013', 'PHIL-3013', 'SCA-4013', 'VOTER-5013', 'Needs Assistance', 'Security Guard', '6000', 'High School Graduate'),
('REF-2026-014', 'Zenaida Ramos', '44 Aurora Blvd, QC', '1954-08-08', 'Nueva Ecija', 'Widowed', 'Female', '0934-444-5555', 'zeny.r@email.com', 'Iglesia ni Cristo', 'Tagalog', 'Tagalog', 'OSCA-014', 'SSS-1014', 'TIN-2014', 'PHIL-3014', 'SCA-4014', 'VOTER-5014', 'Independent', 'Sari-sari Store Owner', '4000', 'High School Graduate'),
('REF-2026-015', 'Manuel Lopez', '55 Ortigas Ave, Pasig', '1958-01-25', 'Rizal', 'Single', 'Male', '0935-555-6666', 'manny.l@email.com', 'Catholic', 'Tagalog', 'English, Tagalog', 'OSCA-015', 'GSIS-1015', 'TIN-2015', 'PHIL-3015', 'SCA-4015', 'VOTER-5015', 'Independent', 'Engineer', '28000', 'College Graduate'),
('REF-2026-016', 'Corazon Alonzo', '66 Shaw Blvd, Mandaluyong', '1949-04-18', 'Laguna', 'Married', 'Female', '0936-666-7777', 'cora.a@email.com', 'Catholic', 'Tagalog', 'Tagalog', 'OSCA-016', 'SSS-1016', 'TIN-2016', 'PHIL-3016', 'SCA-4016', 'VOTER-5016', 'Needs Assistance', 'Baker', '5500', 'High School Level'),
('REF-2026-017', 'Victoriano Navarro', '77 EDSA, Makati', '1955-11-22', 'Cavite', 'Married', 'Male', '0937-777-8888', 'vic.n@email.com', 'Catholic', 'Tagalog', 'Tagalog', 'OSCA-017', 'SSS-1017', 'TIN-2017', 'PHIL-3017', 'SCA-4017', 'VOTER-5017', 'Independent', 'Mechanic', '7000', 'High School Graduate'),
('REF-2026-018', 'Anita Soriano', '88 C5 Road, Taguig', '1962-02-14', 'Batangas', 'Widowed', 'Female', '0938-888-9999', 'anita.s@email.com', 'Born Again', 'Tagalog', 'Tagalog, English', 'OSCA-018', 'GSIS-1018', 'TIN-2018', 'PHIL-3018', 'SCA-4018', 'VOTER-5018', 'Independent', 'Bank Teller', '16000', 'College Graduate'),
('REF-2026-019', 'Reynaldo Pascual', '99 Marcos Highway, Marikina', '1953-07-07', 'Rizal', 'Married', 'Male', '0939-999-0000', 'rey.p@email.com', 'Catholic', 'Tagalog', 'Tagalog', 'OSCA-019', 'SSS-1019', 'TIN-2019', 'PHIL-3019', 'SCA-4019', 'VOTER-5019', 'Independent', 'Shoemaker', '6500', 'High School Graduate'),
('REF-2026-020', 'Jovita De Leon', '101 Sumulong Highway, Antipolo', '1950-09-09', 'Quezon', 'Separated', 'Female', '0940-000-1111', 'jovita.d@email.com', 'Catholic', 'Tagalog', 'Tagalog', 'OSCA-020', 'SSS-1020', 'TIN-2020', 'PHIL-3020', 'SCA-4020', 'VOTER-5020', 'Needs Assistance', 'Laundress', '3000', 'Elementary Graduate'),
('REF-2026-021', 'Emilio Rivera', '202 MacArthur Highway, Valenzuela', '1957-05-31', 'Bulacan', 'Married', 'Male', '0941-111-2222', 'emilio.r@email.com', 'Iglesia ni Cristo', 'Tagalog', 'Tagalog', 'OSCA-021', 'SSS-1021', 'TIN-2021', 'PHIL-3021', 'SCA-4021', 'VOTER-5021', 'Independent', 'Factory Worker', '8000', 'High School Graduate'),
('REF-2026-022', 'Belen Guzman', '303 Quirino Highway, Caloocan', '1959-12-25', 'Manila', 'Single', 'Female', '0942-222-3333', 'belen.g@email.com', 'Catholic', 'Tagalog', 'Tagalog', 'OSCA-022', 'GSIS-1022', 'TIN-2022', 'PHIL-3022', 'SCA-4022', 'VOTER-5022', 'Independent', 'Clerk', '11000', 'College Level'),
('REF-2026-023', 'Mariano Suarez', '404 Alabang-Zapote Rd, Las Pinas', '1946-08-16', 'Cavite', 'Widowed', 'Male', '0943-333-4444', 'mariano.s@email.com', 'Catholic', 'Tagalog', 'Tagalog', 'OSCA-023', 'SSS-1023', 'TIN-2023', 'PHIL-3023', 'SCA-4023', 'VOTER-5023', 'Bedridden', 'Jeepney Driver', '4500', 'Elementary Graduate'),
('REF-2026-024', 'Lilia Tolentino', '505 Dr A Santos Ave, Paranaque', '1961-04-04', 'Laguna', 'Married', 'Female', '0944-444-5555', 'lilia.t@email.com', 'Catholic', 'Tagalog', 'Tagalog', 'OSCA-024', 'SSS-1024', 'TIN-2024', 'PHIL-3024', 'SCA-4024', 'VOTER-5024', 'Independent', 'Seamstress', '5000', 'High School Graduate'),
('REF-2026-025', 'Severino Bautista', '606 NAIA Rd, Pasay', '1952-01-01', 'Batangas', 'Married', 'Male', '0945-555-6666', 'sev.b@email.com', 'Protestant', 'Tagalog', 'Tagalog', 'OSCA-025', 'SSS-1025', 'TIN-2025', 'PHIL-3025', 'SCA-4025', 'VOTER-5025', 'Independent', 'Plumber', '7500', 'High School Level'),
('REF-2026-026', 'Imelda Castro', '707 Chino Roces Ave, Makati', '1956-10-31', 'Samar', 'Widowed', 'Female', '0946-666-7777', 'imelda.c@email.com', 'Catholic', 'Waray', 'Waray, Tagalog', 'OSCA-026', 'GSIS-1026', 'TIN-2026', 'PHIL-3026', 'SCA-4026', 'VOTER-5026', 'Needs Assistance', 'Government Employee', '17000', 'College Graduate'),
('REF-2026-027', 'Guillermo Enriquez', '808 Paseo de Roxas, Makati', '1949-06-12', 'Cebu', 'Married', 'Male', '0947-777-8888', 'guillermo.e@email.com', 'Catholic', 'Cebuano', 'Cebuano, English', 'OSCA-027', 'SSS-1027', 'TIN-2027', 'PHIL-3027', 'SCA-4027', 'VOTER-5027', 'Independent', 'Manager', '21000', 'College Graduate'),
('REF-2026-028', 'Estrella Aguilar', '909 Ayala Ave, Makati', '1960-03-08', 'Iloilo', 'Single', 'Female', '0948-888-9999', 'estrella.a@email.com', 'Catholic', 'Ilonggo', 'Hiligaynon, English', 'OSCA-028', 'GSIS-1028', 'TIN-2028', 'PHIL-3028', 'SCA-4028', 'VOTER-5028', 'Independent', 'Professor', '26000', 'Post-Graduate'),
('REF-2026-029', 'Tomas Delgado', '1001 BGC, Taguig', '1954-11-19', 'Pampanga', 'Married', 'Male', '0949-999-0000', 'tomas.d@email.com', 'Catholic', 'Kapampangan', 'Kapampangan, Tagalog', 'OSCA-029', 'SSS-1029', 'TIN-2029', 'PHIL-3029', 'SCA-4029', 'VOTER-5029', 'Independent', 'Chef', '13000', 'College Graduate'),
('REF-2026-030', 'Amalia Flores', '1102 McKinley Hill, Taguig', '1958-07-27', 'Bohol', 'Widowed', 'Female', '0950-000-1111', 'amalia.f@email.com', 'Catholic', 'Boholano', 'Bisaya, Tagalog', 'OSCA-030', 'SSS-1030', 'TIN-2030', 'PHIL-3030', 'SCA-4030', 'VOTER-5030', 'Needs Assistance', 'Midwife', '9000', 'College Graduate');

INSERT INTO `client_hr_profile` (`reference_code`, `technical_skills`, `community_service`) VALUES
('REF-2026-001', 'Teaching', 'Barangay Volunteer'),
('REF-2026-002', 'Tailoring', 'Friendly Visits'),
('REF-2026-003', 'Driving', 'Church Volunteer'),
('REF-2026-004', 'Nursing / First Aid', 'Medical Mission'),
('REF-2026-005', 'Farming', 'None'),
('REF-2026-006', 'Accounting', 'Treasurer (HOA)'),
('REF-2026-007', 'Carpentry', 'Barangay Maintenance'),
('REF-2026-008', 'Cooking', 'Feeding Program'),
('REF-2026-009', 'Security Consulting', 'Barangay Tanod Advisor'),
('REF-2026-010', 'Bookkeeping', 'Church Finance'),
('REF-2026-011', 'Fishing', 'None'),
('REF-2026-012', 'Tutoring', 'Reading Program'),
('REF-2026-013', 'Security Management', 'Neighborhood Watch'),
('REF-2026-014', 'Retail Management', 'Community Pantry'),
('REF-2026-015', 'Engineering', 'HOA President'),
('REF-2026-016', 'Baking', 'Feeding Program'),
('REF-2026-017', 'Auto Mechanics', 'Transport Strike Support'),
('REF-2026-018', 'Finance', 'Senior Citizen Org Treasurer'),
('REF-2026-019', 'Shoemaking', 'Livelihood Training'),
('REF-2026-020', 'Housekeeping', 'None'),
('REF-2026-021', 'Machine Operation', 'Union Leader'),
('REF-2026-022', 'Data Entry', 'Barangay Office Assistant'),
('REF-2026-023', 'Driving', 'None'),
('REF-2026-024', 'Dressmaking', 'Livelihood Training'),
('REF-2026-025', 'Plumbing', 'Barangay Maintenance'),
('REF-2026-026', 'Public Administration', 'Senior Citizen Org Pres'),
('REF-2026-027', 'Business Management', 'Rotary Club'),
('REF-2026-028', 'Curriculum Design', 'Academic Advisor'),
('REF-2026-029', 'Culinary Arts', 'Fiesta Committee'),
('REF-2026-030', 'Midwifery', 'Health Center Volunteer');

INSERT INTO `client_relationship` (`reference_code`, `relative_name`, `relationship`, `relative_age`, `working_status`, `occupation`, `income`) VALUES
('REF-2026-001', 'Carmelita Bautista', 'Spouse', 68, 'No', 'Housewife', 0.00),
('REF-2026-002', 'Gloria Mercado', 'Daughter', 36, 'Yes', 'Nurse', 38000.00),
('REF-2026-003', 'Elena Santos', 'Spouse', 72, 'No', 'Housewife', 0.00),
('REF-2026-004', 'Mark Cruz', 'Nephew', 28, 'Yes', 'IT Professional', 45000.00),
('REF-2026-005', 'Jose Reyes', 'Son', 40, 'Yes', 'Farmer', 15000.00),
('REF-2026-006', 'Antonio Fernandez', 'Spouse', 69, 'Yes', 'Business Owner', 30000.00),
('REF-2026-007', 'Maria Gonzales', 'Spouse', 70, 'No', 'Housewife', 0.00),
('REF-2026-008', 'Juan Villanueva', 'Son', 35, 'Yes', 'Call Center Agent', 25000.00),
('REF-2026-009', 'Lourdes Mendoza', 'Spouse', 71, 'No', 'Housewife', 0.00),
('REF-2026-010', 'Pedro Castro', 'Brother', 60, 'Yes', 'Driver', 18000.00),
('REF-2026-011', 'Ana Perez', 'Daughter', 42, 'Yes', 'Vendor', 12000.00),
('REF-2026-012', 'Luis Aquino', 'Spouse', 68, 'No', 'Retired', 0.00),
('REF-2026-013', 'Rosa Garcia', 'Spouse', 73, 'No', 'Housewife', 0.00),
('REF-2026-014', 'Miguel Ramos', 'Son', 38, 'Yes', 'Seaman', 80000.00),
('REF-2026-015', 'Sofia Lopez', 'Sister', 65, 'No', 'Retired', 0.00),
('REF-2026-016', 'Roberto Alonzo', 'Spouse', 76, 'No', 'Retired', 0.00),
('REF-2026-017', 'Carmen Navarro', 'Spouse', 67, 'No', 'Housewife', 0.00),
('REF-2026-018', 'Paolo Soriano', 'Son', 30, 'Yes', 'Engineer', 50000.00),
('REF-2026-019', 'Teresa Pascual', 'Spouse', 70, 'No', 'Housewife', 0.00),
('REF-2026-020', 'Clara De Leon', 'Daughter', 45, 'Yes', 'Teacher', 28000.00),
('REF-2026-021', 'Marta Rivera', 'Spouse', 68, 'No', 'Housewife', 0.00),
('REF-2026-022', 'Julio Guzman', 'Brother', 64, 'Yes', 'Guard', 16000.00),
('REF-2026-023', 'Lina Suarez', 'Daughter', 50, 'Yes', 'Cook', 14000.00),
('REF-2026-024', 'Emil Tolentino', 'Spouse', 66, 'No', 'Retired', 0.00),
('REF-2026-025', 'Sonia Bautista', 'Spouse', 71, 'No', 'Housewife', 0.00),
('REF-2026-026', 'Rico Castro', 'Son', 40, 'Yes', 'Manager', 60000.00),
('REF-2026-027', 'Fe Enriquez', 'Spouse', 75, 'No', 'Retired', 0.00),
('REF-2026-028', 'Leo Aguilar', 'Brother', 62, 'Yes', 'Lawyer', 90000.00),
('REF-2026-029', 'Paz Delgado', 'Spouse', 70, 'No', 'Housewife', 0.00),
('REF-2026-030', 'Nilo Flores', 'Son', 33, 'Yes', 'Nurse', 35000.00);

SELECT * FROM data.admin_credentials;
SELECT * FROM data.client_info;
SELECT * FROM data.client_hr_profile;
SELECT * FROM data.client_relationship;
