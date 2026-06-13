DROP TABLE IF EXISTS client_relationship;
DROP TABLE IF EXISTS client_hr_profile;
DROP TABLE IF EXISTS client_info;
DROP TABLE IF EXISTS admin_credentials;

CREATE TABLE client_info (
    reference_code VARCHAR(15) PRIMARY KEY,
    name VARCHAR(50) NOT NULL,
    address VARCHAR(50) NOT NULL,
    birth_date DATE NOT NULL CHECK (YEAR(birth_date) <= 1966),
    birth_place VARCHAR(50) NOT NULL,
    marital_status VARCHAR(1) NOT NULL CHECK (marital_status IN ('S', 'M', 'W')),
    sex VARCHAR(10) NOT NULL,
    contact_number VARCHAR(15),
    email_address VARCHAR(50),
    religion VARCHAR(15),
    ethnicity VARCHAR(15) NOT NULL,
    language_spoken VARCHAR(15) NOT NULL,
    osca_id_num VARCHAR(15) NOT NULL,
    gsis_sss_number VARCHAR(18),
    tin_num VARCHAR(17),
    philhealth_num VARCHAR(14),
    sc_association_id VARCHAR(15),
    other_gov_id VARCHAR(30),
    travel_capability VARCHAR(1) NOT NULL CHECK (travel_capability IN ('Y', 'N')),
    job VARCHAR(20),
    current_pension VARCHAR(10),
    highest_educational_attainment VARCHAR(20) NOT NULL
);

CREATE TABLE client_hr_profile (
    skill_id INT AUTO_INCREMENT PRIMARY KEY,
    reference_code VARCHAR(15) NOT NULL,
    technical_skills VARCHAR(30),
    community_service VARCHAR(30),
    FOREIGN KEY (reference_code) REFERENCES client_info(reference_code) ON DELETE CASCADE
);

CREATE TABLE client_relationship (
    relative_id INT AUTO_INCREMENT PRIMARY KEY,
    reference_code VARCHAR(15) NOT NULL,
    relationship VARCHAR(10),
    relative_name VARCHAR(50),
    relative_age VARCHAR(3),
    working_status VARCHAR(1) CHECK (working_status IN ('Y', 'N')),
    occupation VARCHAR(30),
    income VARCHAR(8),
    FOREIGN KEY (reference_code) REFERENCES client_info(reference_code) ON DELETE CASCADE
);

CREATE TABLE admin_credentials (
    admin_id INT NOT NULL AUTO_INCREMENT,
    username VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    PRIMARY KEY (admin_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

INSERT INTO admin_credentials (username, password) VALUES
('admin', 'admin123');

INSERT INTO client_info (
    reference_code, name, address, birth_date, birth_place, marital_status, sex,
    contact_number, email_address, religion, ethnicity, language_spoken,
    osca_id_num, gsis_sss_number, tin_num, philhealth_num, sc_association_id,
    other_gov_id, travel_capability, job, current_pension, highest_educational_attainment
) VALUES
('REF-2026-001', 'Eduardo Bautista', '12 Rizal St, Manila', '1955-04-12', 'Manila', 'M', 'Male', '09171112222', 'ed@email.com', 'Catholic', 'Tagalog', 'Tagalog', 'OSCA-001', '111-222', '333-444', '555-666', 'S001', NULL, 'Y', 'Retired Teacher', '5000', 'College Graduate'),
('REF-2026-002', 'Flora Mercado', '45 Mabini Ave, Quezon City', '1958-08-25', 'Bulacan', 'W', 'Female', '09182223333', NULL, 'Catholic', 'Tagalog', 'Tagalog', 'OSCA-002', '222-333', '444-555', '666-777', 'S002', 'Voter ID', 'Y', 'Retired Tailor', '3500', 'High School Grad'),
('REF-2026-003', 'Ramon Santos', '88 Luna St, Makati', '1950-11-03', 'Pampanga', 'M', 'Male', '09193334444', 'ramon.s@email.com', 'INC', 'Kapampangan', 'Tagalog', 'OSCA-003', NULL, '555-666', '777-888', 'S003', NULL, 'N', 'Former Driver', '2000', 'Elementary Grad'),
('REF-2026-004', 'Luzviminda Cruz', '90 Taft Ave, Pasay', '1961-01-15', 'Cebu', 'S', 'Female', '09204445555', 'luz@email.com', 'Catholic', 'Cebuano', 'Bisaya', 'OSCA-004', '444-555', '666-777', '888-999', 'S004', 'Passport', 'Y', 'Retired Nurse', '8000', 'College Graduate'),
('REF-2026-005', 'Nestor Reyes', '14 Burgos St, Pasig', '1948-07-22', 'Rizal', 'M', 'Male', '09215556666', NULL, 'Born Again', 'Tagalog', 'Tagalog', 'OSCA-005', '555-666', '777-888', '999-000', 'S005', NULL, 'N', 'Farmer', '1500', 'Elementary Level'),
('REF-2026-006', 'Carmela Fernandez', '56 Roxas Blvd, Manila', '1959-12-05', 'Iloilo', 'M', 'Female', '09226667777', 'carmela@email.com', 'Catholic', 'Ilonggo', 'Hiligaynon', 'OSCA-006', '666-777', '888-999', '000-111', 'S006', 'Driver License', 'Y', 'Business Owner', '10000', 'College Graduate'),
('REF-2026-007', 'Arturo Gonzales', '33 Bonifacio St, Taguig', '1953-09-18', 'Cavite', 'W', 'Male', '09277778888', NULL, 'Aglipayan', 'Tagalog', 'Tagalog', 'OSCA-007', '777-888', '999-000', '111-222', 'S007', NULL, 'Y', 'Carpenter', '2500', 'High School Level'),
('REF-2026-008', 'Teresita Villanueva', '101 Katipunan, QC', '1956-02-28', 'Laguna', 'M', 'Female', '09288889999', 'tess@email.com', 'Catholic', 'Tagalog', 'Tagalog', 'OSCA-008', '888-999', '000-111', '222-333', 'S008', 'Postal ID', 'N', 'Vendor', '1500', 'High School Grad'),
('REF-2026-009', 'Ricardo Mendoza', '7A Aguinaldo, Cavite', '1951-05-10', 'Batangas', 'M', 'Male', '09299990000', 'ricardo@email.com', 'Catholic', 'Batangueño', 'Tagalog', 'OSCA-009', '999-000', '111-222', '333-444', 'S009', NULL, 'Y', 'Retired Police', '12000', 'College Graduate'),
('REF-2026-010', 'Elena Castro', '42 Escolta, Manila', '1960-10-14', 'Pangasinan', 'S', 'Female', '09300001111', NULL, 'Methodist', 'Pangasinense', 'Ilocano', 'OSCA-010', '000-111', '222-333', '444-555', 'S010', 'National ID', 'Y', 'Accountant', '9000', 'College Graduate'),
('REF-2026-011', 'Domingo Perez', '22 Recto, Manila', '1945-03-30', 'Bataan', 'W', 'Male', '09311112222', NULL, 'Catholic', 'Tagalog', 'Tagalog', 'OSCA-011', NULL, '333-444', '555-666', 'S011', NULL, 'N', 'Fisherman', '0', 'Elementary Level'),
('REF-2026-012', 'Rosario Aquino', '8 Boni Ave, Mandaluyong', '1957-06-20', 'Tarlac', 'M', 'Female', '09322223333', 'rose@email.com', 'INC', 'Ilocano', 'Ilocano', 'OSCA-012', '222-333', '444-555', '666-777', 'S012', 'Voter ID', 'Y', 'Teacher', '6000', 'College Graduate'),
('REF-2026-013', 'Francisco Garcia', '19 EDSA, Quezon City', '1952-12-11', 'Nueva Ecija', 'M', 'Male', '09333334444', NULL, 'Catholic', 'Tagalog', 'Tagalog', 'OSCA-013', '333-444', '555-666', '777-888', 'S013', NULL, 'N', 'Security Guard', '3000', 'High School Grad'),
('REF-2026-014', 'Zenaida Ramos', '77 Ortigas, Pasig', '1954-08-08', 'Rizal', 'W', 'Female', '09344445555', 'zeny@email.com', 'Catholic', 'Tagalog', 'Tagalog', 'OSCA-014', '444-555', '666-777', '888-999', 'S014', 'Passport', 'Y', 'Store Owner', '4000', 'High School Grad'),
('REF-2026-015', 'Manuel Lopez', '11 Shaw Blvd, Pasig', '1958-01-25', 'Bicol', 'M', 'Male', '09355556666', 'manny@email.com', 'Catholic', 'Bicolano', 'Bicolano', 'OSCA-015', '555-666', '777-888', '999-000', 'S015', NULL, 'Y', 'Engineer', '15000', 'College Graduate'),
('REF-2026-016', 'Corazon Alonzo', '65 Commonwealth, QC', '1949-04-18', 'Quezon', 'W', 'Female', '09366667777', NULL, 'Born Again', 'Tagalog', 'Tagalog', 'OSCA-016', '666-777', '888-999', '000-111', 'S016', 'Postal ID', 'N', 'Baker', '2000', 'High School Level'),
('REF-2026-017', 'Victoriano Navarro', '32 España, Manila', '1955-11-22', 'Manila', 'M', 'Male', '09377778888', 'vic@email.com', 'Catholic', 'Tagalog', 'Tagalog', 'OSCA-017', '777-888', '999-000', '111-222', 'S017', 'Driver License', 'Y', 'Mechanic', '3500', 'High School Grad'),
('REF-2026-018', 'Anita Soriano', '45 Aurora Blvd, QC', '1962-02-14', 'Cebu', 'M', 'Female', '09388889999', 'anita@email.com', 'Catholic', 'Cebuano', 'Bisaya', 'OSCA-018', '888-999', '000-111', '222-333', 'S018', NULL, 'Y', 'Bank Teller', '8500', 'College Graduate'),
('REF-2026-019', 'Reynaldo Pascual', '12 Quirino Hwy, QC', '1953-07-07', 'Bulacan', 'M', 'Male', '09399990000', NULL, 'Catholic', 'Tagalog', 'Tagalog', 'OSCA-019', '999-000', '111-222', '333-444', 'S019', 'Voter ID', 'Y', 'Shoemaker', '1000', 'High School Grad'),
('REF-2026-020', 'Jovita De Leon', '99 Marcos Hwy, Marikina', '1950-09-09', 'Laguna', 'W', 'Female', '09400001111', NULL, 'INC', 'Tagalog', 'Tagalog', 'OSCA-020', '000-111', '222-333', '444-555', 'S020', NULL, 'N', 'Laundress', '0', 'Elementary Grad'),
('REF-2026-021', 'Emilio Rivera', '18 Amorsolo, Makati', '1957-05-31', 'Batangas', 'M', 'Male', '09411112222', 'emilio@email.com', 'Catholic', 'Batangueño', 'Tagalog', 'OSCA-021', '111-222', '333-444', '555-666', 'S021', 'National ID', 'Y', 'Factory Worker', '4000', 'High School Grad'),
('REF-2026-022', 'Belen Guzman', '27 Ayala Ave, Makati', '1959-12-25', 'Pampanga', 'S', 'Female', '09422223333', NULL, 'Catholic', 'Kapampangan', 'Tagalog', 'OSCA-022', '222-333', '444-555', '666-777', 'S022', NULL, 'Y', 'Clerk', '5500', 'College Level'),
('REF-2026-023', 'Mariano Suarez', '50 Legarda, Manila', '1946-08-16', 'Ilocos Sur', 'M', 'Male', '09433334444', NULL, 'Aglipayan', 'Ilocano', 'Ilocano', 'OSCA-023', NULL, '555-666', '777-888', 'S023', 'Postal ID', 'N', 'Jeepney Driver', '0', 'Elementary Grad'),
('REF-2026-024', 'Lilia Tolentino', '88 Gil Puyat, Pasay', '1961-04-04', 'Cavite', 'M', 'Female', '09444445555', 'lilia@email.com', 'Catholic', 'Tagalog', 'Tagalog', 'OSCA-024', '444-555', '666-777', '888-999', 'S024', NULL, 'Y', 'Seamstress', '2000', 'High School Grad'),
('REF-2026-025', 'Severino Bautista', '110 Del Monte, QC', '1952-01-01', 'Pangasinan', 'W', 'Male', '09455556666', 'sev@email.com', 'Catholic', 'Pangasinense', 'Ilocano', 'OSCA-025', '555-666', '777-888', '999-000', 'S025', 'Passport', 'Y', 'Plumber', '3000', 'High School Level'),
('REF-2026-026', 'Imelda Castro', '34 Timog Ave, QC', '1956-10-31', 'Manila', 'M', 'Female', '09466667777', NULL, 'Methodist', 'Tagalog', 'Tagalog', 'OSCA-026', '666-777', '888-999', '000-111', 'S026', NULL, 'Y', 'Gov Employee', '12000', 'College Graduate'),
('REF-2026-027', 'Guillermo Enriquez', '77 Tomas Morato, QC', '1949-06-12', 'Rizal', 'M', 'Male', '09477778888', 'gui@email.com', 'Catholic', 'Tagalog', 'Tagalog', 'OSCA-027', '777-888', '999-000', '111-222', 'S027', 'Driver License', 'N', 'Manager', '18000', 'College Graduate'),
('REF-2026-028', 'Estrella Aguilar', '120 East Ave, QC', '1960-03-08', 'Iloilo', 'S', 'Female', '09488889999', 'star@email.com', 'Born Again', 'Ilonggo', 'Hiligaynon', 'OSCA-028', '888-999', '000-111', '222-333', 'S028', 'Voter ID', 'Y', 'Professor', '20000', 'Post-Graduate'),
('REF-2026-029', 'Tomas Delgado', '99 Kamias Rd, QC', '1954-11-19', 'Batangas', 'M', 'Male', '09499990000', NULL, 'Catholic', 'Batangueño', 'Tagalog', 'OSCA-029', '999-000', '111-222', '333-444', 'S029', NULL, 'Y', 'Chef', '7000', 'College Graduate'),
('REF-2026-030', 'Amalia Flores', '45 Anonas, QC', '1958-07-27', 'Bulacan', 'W', 'Female', '09500001111', 'amy@email.com', 'Catholic', 'Tagalog', 'Tagalog', 'OSCA-030', '000-111', '222-333', '444-555', 'S030', 'National ID', 'Y', 'Midwife', '5000', 'College Graduate');

INSERT INTO client_hr_profile (reference_code, technical_skills, community_service) VALUES
('REF-2026-001', 'Curriculum Design', 'Daycare Volunteer'),
('REF-2026-002', 'Dressmaking, Tailoring', 'Parish Sewing Club'),
('REF-2026-003', 'Driving, Maintenance', 'Transport Union Officer'),
('REF-2026-004', 'Nursing, First Aid', 'Medical Mission Vol.'),
('REF-2026-005', 'Agriculture, Farming', 'Coop Board Member'),
('REF-2026-006', 'Business Mgmt, Sales', 'Rotary Club Member'),
('REF-2026-007', 'Carpentry, Masonry', 'Chapel Construction Vol'),
('REF-2026-008', 'Retail, Cooking', 'Feeding Program Cook'),
('REF-2026-009', 'Security, Investigation', 'HOA President'),
('REF-2026-010', 'Accounting, Bookkeeping', 'Parish Treasurer'),
('REF-2026-011', 'Fishing, Net Weaving', 'Coastal Cleanup Vol'),
('REF-2026-012', 'Lesson Planning, Math', 'Alternative Learning Sys'),
('REF-2026-013', 'Security Guard, First Aid', 'Barangay Tanod'),
('REF-2026-014', 'Inventory, Retail Mgmt', 'Livelihood Trainer'),
('REF-2026-015', 'AutoCAD, Structural', 'Housing Project Consult'),
('REF-2026-016', 'Baking, Food Prep', 'Parish Bake Sale Org'),
('REF-2026-017', 'Auto Mechanic, Engine Rep', 'Free Tricycle Repair'),
('REF-2026-018', 'Banking, Cash Handling', 'Senior Citizen Treasurer'),
('REF-2026-019', 'Shoemaking, Leathercraft', 'PWD Skills Trainer'),
('REF-2026-020', 'Laundry, Housekeeping', 'Church Cleaning Comm'),
('REF-2026-021', 'Assembly Line, QC', 'Barangay Cleanup Drive'),
('REF-2026-022', 'Filing, Data Entry', 'Elections Volunteer'),
('REF-2026-024', 'Seamstress, Patternmaking', 'Costume Maker for Fiesta'),
('REF-2026-025', 'Plumbing, Pipefitting', 'Water District Consult'),
('REF-2026-026', 'Public Admin, Policy', 'Barangay Council Adv'),
('REF-2026-027', 'Logistics, Mgmt', 'Disaster Relief Org'),
('REF-2026-028', 'Research, Public Speaking', 'Youth Mentor'),
('REF-2026-029', 'Culinary Arts, Catering', 'Community Soup Kitchen'),
('REF-2026-030', 'Midwifery, Childcare', 'Health Center Assistant');

INSERT INTO client_relationship (reference_code, relative_name, relationship, relative_age, working_status, occupation, income) VALUES
('REF-2026-001', 'Maria Bautista', 'Wife', '68', 'N', 'Housewife', '0'),
('REF-2026-001', 'Juan Bautista', 'Son', '35', 'Y', 'Software Engineer', '50000'),
('REF-2026-002', 'Jose Mercado', 'Son', '40', 'Y', 'Store Manager', '35000'),
('REF-2026-003', 'Elena Santos', 'Wife', '72', 'N', 'Housewife', '0'),
('REF-2026-003', 'Mark Santos', 'Son', '38', 'Y', 'Call Center Agent', '25000'),
('REF-2026-004', 'Paulo Cruz', 'Nephew', '28', 'Y', 'Nurse', '30000'),
('REF-2026-005', 'Anita Reyes', 'Wife', '75', 'N', 'Retired', '0'),
('REF-2026-006', 'Luis Fernandez', 'Husband', '68', 'Y', 'Consultant', '40000'),
('REF-2026-006', 'Carla Fernandez', 'Daughter', '30', 'Y', 'Marketing Exec', '45000'),
('REF-2026-007', 'Arthur Gonzales Jr.', 'Son', '45', 'Y', 'Foreman', '28000'),
('REF-2026-008', 'Ramon Villanueva', 'Husband', '71', 'N', 'Retired', '0'),
('REF-2026-009', 'Clara Mendoza', 'Wife', '73', 'N', 'Housewife', '0'),
('REF-2026-010', 'Diana Castro', 'Niece', '25', 'Y', 'Teacher', '22000'),
('REF-2026-011', 'Lourdes Perez', 'Daughter', '48', 'Y', 'Vendor', '15000'),
('REF-2026-012', 'Roberto Aquino', 'Husband', '70', 'N', 'Retired', '0'),
('REF-2026-013', 'Gina Garcia', 'Wife', '69', 'N', 'Housewife', '0'),
('REF-2026-014', 'Dennis Ramos', 'Son', '33', 'Y', 'Mechanic', '20000'),
('REF-2026-014', 'Sarah Ramos', 'Daughter', '31', 'Y', 'Clerk', '18000'),
('REF-2026-015', 'Teresa Lopez', 'Wife', '65', 'N', 'Retired Teacher', '0'),
('REF-2026-016', 'Michael Alonzo', 'Son', '45', 'Y', 'Driver', '15000'),
('REF-2026-017', 'Gloria Navarro', 'Wife', '68', 'N', 'Housewife', '0'),
('REF-2026-018', 'Henry Soriano', 'Husband', '65', 'Y', 'Accountant', '35000'),
('REF-2026-019', 'Rosa Pascual', 'Wife', '70', 'N', 'Housewife', '0'),
('REF-2026-020', 'Pedro De Leon', 'Son', '42', 'Y', 'Electrician', '20000'),
('REF-2026-021', 'Carmen Rivera', 'Wife', '65', 'N', 'Housewife', '0'),
('REF-2026-022', 'Lucy Guzman', 'Sister', '60', 'Y', 'Cashier', '16000'),
('REF-2026-023', 'Tomas Suarez', 'Son', '50', 'Y', 'Driver', '18000'),
('REF-2026-024', 'Vicente Tolentino', 'Husband', '66', 'N', 'Retired', '0'),
('REF-2026-025', 'Mario Bautista', 'Son', '40', 'Y', 'Plumber', '22000'),
('REF-2026-026', 'Eduardo Castro', 'Husband', '72', 'N', 'Retired', '0'),
('REF-2026-027', 'Sylvia Enriquez', 'Wife', '75', 'N', 'Housewife', '0'),
('REF-2026-028', 'Martin Aguilar', 'Nephew', '35', 'Y', 'IT Specialist', '60000'),
('REF-2026-029', 'Luisa Delgado', 'Wife', '70', 'N', 'Retired', '0'),
('REF-2026-029', 'Tom Delgado Jr.', 'Son', '42', 'Y', 'Chef', '45000'),
('REF-2026-030', 'Angela Flores', 'Daughter', '38', 'Y', 'Nurse', '35000');
