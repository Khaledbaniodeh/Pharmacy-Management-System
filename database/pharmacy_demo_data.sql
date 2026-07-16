-- =========================================================
-- Noor AlHuda Pharmacy Demo Data
-- Run this AFTER the CREATE TABLE script for pharmacy_system_db
-- Compatible with the final schema used by PharmacyFX.java
-- =========================================================

USE pharmacy_system_db;

SET SQL_SAFE_UPDATES = 0;
SET FOREIGN_KEY_CHECKS = 0;

DELETE FROM invoices;
DELETE FROM sale_items;
DELETE FROM sales;
DELETE FROM purchase_items;
DELETE FROM purchases;
DELETE FROM inventory;
DELETE FROM medicines;
DELETE FROM warehouses;
DELETE FROM pharmacists;
DELETE FROM customers;
DELETE FROM suppliers;
DELETE FROM categories;
DELETE FROM branches;

ALTER TABLE invoices AUTO_INCREMENT = 1;
ALTER TABLE sales AUTO_INCREMENT = 1;
ALTER TABLE purchases AUTO_INCREMENT = 1;
ALTER TABLE medicines AUTO_INCREMENT = 1;
ALTER TABLE warehouses AUTO_INCREMENT = 1;
ALTER TABLE pharmacists AUTO_INCREMENT = 1;
ALTER TABLE customers AUTO_INCREMENT = 1;
ALTER TABLE suppliers AUTO_INCREMENT = 1;
ALTER TABLE categories AUTO_INCREMENT = 1;
ALTER TABLE branches AUTO_INCREMENT = 1;

SET FOREIGN_KEY_CHECKS = 1;

-- =========================
-- Branches
-- =========================
INSERT INTO branches (branch_name, location, phone_number, opening_hours, status) VALUES
('Main Branch', 'Ramallah - City Center', '022400001', '08:00 - 22:00', 'ACTIVE'),
('North Branch', 'Nablus - Rafidia', '092400002', '09:00 - 21:00', 'ACTIVE'),
('South Branch', 'Hebron - Downtown', '022400003', '08:30 - 21:30', 'ACTIVE'),
('Emergency Branch', 'Ramallah - Emergency Street', '022400004', '24 Hours', 'ACTIVE');

-- =========================
-- Categories
-- =========================
INSERT INTO categories (category_name, description) VALUES
('Painkillers', 'Pain relief medicines'),
('Antibiotics', 'Antibacterial medicines'),
('Digestive Health', 'Stomach and digestion medicines'),
('Vitamins', 'Vitamins and supplements'),
('Diabetes Care', 'Diabetes medicines and strips'),
('Baby Care', 'Medicines and care products for babies'),
('Skin Care', 'Creams and dermatology products'),
('Eye Care', 'Eye drops and eye treatments'),
('First Aid', 'Bandages and first aid products'),
('Heart Care', 'Cardiovascular medicines');

-- =========================
-- Suppliers
-- =========================
INSERT INTO suppliers (supplier_name, first_name, last_name, phone_number, address, email, company_name) VALUES
('Default Supplier', 'Ahmad', 'Saleh', '0590000000', 'Ramallah', 'default_supplier@pharmacy.com', 'Medical Supply Company'),
('HealthPlus Supplier', 'Mona', 'Hassan', '0591111111', 'Nablus', 'healthplus@pharmacy.com', 'HealthPlus Medical'),
('CareMed Supplier', 'Omar', 'Khalil', '0592222222', 'Hebron', 'caremed@pharmacy.com', 'CareMed Distribution'),
('LifeLine Supplier', 'Lina', 'Nasser', '0593333333', 'Bethlehem', 'lifeline@pharmacy.com', 'LifeLine Pharma'),
('MediWorld Supplier', 'Tariq', 'Qasem', '0594444444', 'Jenin', 'mediworld@pharmacy.com', 'MediWorld Group'),
('AlShifa Supplier', 'Rami', 'Younis', '0595555555', 'Ramallah', 'alshifa@pharmacy.com', 'AlShifa Medical Store'),
('GlobalCare Supplier', 'Dina', 'Mahmoud', '0596666666', 'Hebron', 'globalcare@pharmacy.com', 'GlobalCare Distribution');

-- =========================
-- Customers
-- =========================
INSERT INTO customers (first_name, last_name, phone_number, address, email, gender, date_of_birth) VALUES
('Khaled', 'Amin', '0597000001', 'Ramallah', 'khaled.amin@email.com', 'MALE', '2004-05-20'),
('Noor', 'Hassan', '0597000002', 'Nablus', 'noor.hassan@email.com', 'FEMALE', '2001-03-14'),
('Ahmad', 'Ali', '0597000003', 'Hebron', 'ahmad.ali@email.com', 'MALE', '1998-11-09'),
('Dana', 'Saleh', '0597000004', 'Ramallah', 'dana.saleh@email.com', 'FEMALE', '2000-07-28'),
('Majd', 'Yousef', '0597000005', 'Bethlehem', 'majd.yousef@email.com', 'MALE', '1995-01-12'),
('Yara', 'Nasser', '0597000006', 'Jenin', 'yara.nasser@email.com', 'FEMALE', '2002-09-03'),
('Fadi', 'Mahmoud', '0597000007', 'Nablus', 'fadi.mahmoud@email.com', 'MALE', '1997-12-30'),
('Ruba', 'Khalil', '0597000008', 'Hebron', 'ruba.khalil@email.com', 'FEMALE', '1999-04-19'),
('Zaid', 'Omar', '0597000009', 'Ramallah', 'zaid.omar@email.com', 'MALE', '2003-06-15'),
('Layan', 'Samer', '0597000010', 'Tulkarem', 'layan.samer@email.com', 'FEMALE', '2001-10-22');

-- =========================
-- Pharmacists
-- =========================
INSERT INTO pharmacists (first_name, last_name, phone_number, address, email, gender, date_of_birth, hire_date, branch_id) VALUES
('Rania', 'Adel', '0598000001', 'Ramallah', 'rania.adel@pharmacy.com', 'FEMALE', '1988-03-02', '2020-01-10', 1),
('Ibrahim', 'Saleh', '0598000002', 'Nablus', 'ibrahim.saleh@pharmacy.com', 'MALE', '1990-06-12', '2021-04-15', 2),
('Maya', 'Hassan', '0598000003', 'Hebron', 'maya.hassan@pharmacy.com', 'FEMALE', '1994-08-08', '2022-02-20', 3),
('Kareem', 'Younis', '0598000004', 'Ramallah', 'kareem.younis@pharmacy.com', 'MALE', '1992-12-01', '2023-05-01', 4),
('Salma', 'Khalil', '0598000005', 'Jenin', 'salma.khalil@pharmacy.com', 'FEMALE', '1996-07-09', '2023-09-11', 1),
('Basil', 'Omar', '0598000006', 'Nablus', 'basil.omar@pharmacy.com', 'MALE', '1993-01-27', '2024-01-05', 2);

-- =========================
-- Warehouses
-- =========================
INSERT INTO warehouses (warehouse_name, location, capacity, branch_id) VALUES
('Main Warehouse', 'Ramallah - Main Branch Basement', 5000, 1),
('Cold Storage', 'Ramallah - Main Branch', 1000, 1),
('North Warehouse', 'Nablus - North Branch', 3500, 2),
('South Warehouse', 'Hebron - South Branch', 3200, 3),
('Emergency Stock Room', 'Ramallah - Emergency Branch', 1500, 4),
('North Cold Room', 'Nablus - North Branch Cold Storage', 1200, 2);

-- =========================
-- Medicines
-- =========================
INSERT INTO medicines (medicine_name, description, selling_price, expiry_date, category_id, supplier_id, status) VALUES
('Panadol', 'Pain relief tablets', 4.50, '2027-03-15', 1, 1, 'ACTIVE'),
('Aspirin', 'Painkiller and blood thinner', 3.75, '2027-01-10', 1, 2, 'ACTIVE'),
('Ibuprofen', 'Anti-inflammatory painkiller', 5.25, '2026-07-10', 1, 2, 'ACTIVE'),
('Amoxicillin', 'Antibiotic capsules', 12.00, '2027-09-01', 2, 3, 'ACTIVE'),
('Augmentin', 'Broad spectrum antibiotic', 18.50, '2026-12-01', 2, 3, 'ACTIVE'),
('Azithromycin', 'Antibiotic tablets', 20.00, '2026-06-15', 2, 4, 'ACTIVE'),
('Omeprazole', 'Digestive health capsules', 9.00, '2027-05-20', 3, 5, 'ACTIVE'),
('Antacid Syrup', 'Stomach acidity relief', 6.50, '2026-07-01', 3, 5, 'ACTIVE'),
('Vitamin C', 'Immune support tablets', 7.00, '2028-02-14', 4, 6, 'ACTIVE'),
('Vitamin D', 'Vitamin D supplement', 8.50, '2027-11-30', 4, 6, 'ACTIVE'),
('Metformin', 'Diabetes medicine', 14.00, '2027-04-25', 5, 7, 'ACTIVE'),
('Glucose Strips', 'Blood glucose test strips', 22.00, '2027-08-08', 5, 7, 'ACTIVE'),
('Baby Syrup', 'Baby fever syrup', 6.75, '2026-10-12', 6, 4, 'ACTIVE'),
('Diaper Cream', 'Baby skin protection cream', 5.80, '2027-02-20', 6, 6, 'ACTIVE'),
('Skin Cream', 'Dermatology cream', 11.50, '2026-08-11', 7, 5, 'ACTIVE'),
('Sunscreen SPF50', 'Sun protection cream', 16.00, '2027-06-10', 7, 5, 'ACTIVE'),
('Eye Drops', 'Eye drops for dryness', 6.00, '2026-06-28', 8, 4, 'ACTIVE'),
('Old Eye Drop', 'Expired eye treatment', 5.00, '2026-05-01', 8, 4, 'ACTIVE'),
('Bandages', 'First aid bandages pack', 3.00, '2028-01-01', 9, 1, 'ACTIVE'),
('Thermometer', 'Digital thermometer', 15.00, '2029-01-01', 9, 2, 'ACTIVE'),
('Atorvastatin', 'Cholesterol treatment', 19.00, '2027-03-03', 10, 3, 'ACTIVE'),
('ORS', 'Oral rehydration salts', 2.50, '2027-09-09', 3, 6, 'ACTIVE'),
('Probiotic', 'Digestive probiotic capsules', 13.00, '2027-12-12', 3, 5, 'ACTIVE'),
('Inactive Sample', 'Inactive old product', 4.00, '2027-01-01', 1, 1, 'INACTIVE');

-- =========================
-- Inventory
-- =========================
INSERT INTO inventory (warehouse_id, medicine_id, quantity, reorder_level, status) VALUES
(1, 1, 180, 30, 'AVAILABLE'),
(2, 1, 40, 20, 'AVAILABLE'),
(3, 1, 75, 25, 'AVAILABLE'),
(1, 2, 90, 20, 'AVAILABLE'),
(3, 2, 22, 20, 'AVAILABLE'),
(4, 3, 8, 15, 'LOW_STOCK'),
(1, 4, 120, 25, 'AVAILABLE'),
(3, 4, 70, 25, 'AVAILABLE'),
(4, 5, 45, 20, 'AVAILABLE'),
(5, 6, 5, 15, 'LOW_STOCK'),
(1, 7, 80, 20, 'AVAILABLE'),
(4, 8, 7, 15, 'LOW_STOCK'),
(1, 9, 130, 30, 'AVAILABLE'),
(3, 10, 55, 20, 'AVAILABLE'),
(1, 11, 60, 20, 'AVAILABLE'),
(6, 11, 10, 15, 'LOW_STOCK'),
(2, 12, 15, 20, 'LOW_STOCK'),
(6, 12, 35, 20, 'AVAILABLE'),
(4, 13, 25, 10, 'AVAILABLE'),
(5, 14, 12, 10, 'AVAILABLE'),
(1, 15, 18, 15, 'AVAILABLE'),
(3, 16, 20, 10, 'AVAILABLE'),
(2, 17, 9, 15, 'LOW_STOCK'),
(5, 18, 0, 10, 'OUT_OF_STOCK'),
(1, 19, 200, 50, 'AVAILABLE'),
(3, 19, 100, 50, 'AVAILABLE'),
(4, 20, 18, 10, 'AVAILABLE'),
(1, 21, 42, 15, 'AVAILABLE'),
(4, 22, 65, 20, 'AVAILABLE'),
(2, 23, 28, 20, 'AVAILABLE'),
(6, 23, 8, 15, 'LOW_STOCK');

-- =========================
-- Purchases
-- =========================
INSERT INTO purchases (purchase_date, supplier_id, pharmacist_id, branch_id, total_cost, purchase_status) VALUES
('2026-06-01 09:15:00', 1, 1, 1, 300.00, 'RECEIVED'),
('2026-06-03 10:00:00', 2, 2, 2, 420.00, 'RECEIVED'),
('2026-06-05 13:30:00', 3, 3, 3, 570.00, 'RECEIVED'),
('2026-06-07 11:10:00', 4, 4, 4, 210.00, 'RECEIVED'),
('2026-06-10 14:20:00', 5, 1, 1, 460.00, 'RECEIVED'),
('2026-06-12 16:00:00', 6, 5, 1, 250.00, 'PENDING'),
('2026-06-14 12:45:00', 7, 6, 2, 600.00, 'RECEIVED'),
('2026-06-16 10:25:00', 3, 2, 2, 333.00, 'CANCELLED');

INSERT INTO purchase_items (purchase_id, medicine_id, quantity, cost_price) VALUES
(1, 1, 50, 2.00),
(1, 19, 100, 2.00),
(2, 2, 60, 2.50),
(2, 20, 18, 15.00),
(3, 4, 60, 6.00),
(3, 5, 30, 7.00),
(4, 13, 30, 3.00),
(4, 17, 20, 6.00),
(5, 7, 40, 4.00),
(5, 15, 20, 7.00),
(5, 23, 10, 16.00),
(6, 9, 50, 3.00),
(6, 10, 20, 5.00),
(7, 11, 30, 8.00),
(7, 12, 15, 24.00),
(8, 4, 20, 6.00),
(8, 5, 18, 7.00);

-- =========================
-- Sales
-- =========================
INSERT INTO sales (sale_date, customer_id, pharmacist_id, branch_id, payment_method, total_amount, sale_status) VALUES
('2026-06-02 10:05:00', 1, 1, 1, 'CASH', 31.50, 'COMPLETED'),
('2026-06-03 11:30:00', 2, 2, 2, 'CARD', 42.00, 'COMPLETED'),
('2026-06-04 15:45:00', 3, 3, 3, 'INSURANCE', 68.50, 'COMPLETED'),
('2026-06-05 17:10:00', 4, 4, 4, 'MOBILE_PAYMENT', 52.00, 'COMPLETED'),
('2026-06-06 09:40:00', 5, 1, 1, 'CASH', 43.50, 'COMPLETED'),
('2026-06-07 13:15:00', 6, 5, 1, 'CARD', 97.00, 'COMPLETED'),
('2026-06-08 14:30:00', 7, 6, 2, 'CASH', 72.00, 'COMPLETED'),
('2026-06-09 12:00:00', 8, 2, 2, 'OTHER', 30.00, 'CANCELLED'),
('2026-06-10 18:20:00', 9, 3, 3, 'CARD', 83.25, 'COMPLETED'),
('2026-06-11 16:40:00', 10, 4, 4, 'CASH', 55.50, 'COMPLETED'),
('2026-06-12 11:22:00', 1, 1, 1, 'CARD', 116.00, 'COMPLETED'),
('2026-06-13 19:10:00', 2, 2, 2, 'MOBILE_PAYMENT', 49.00, 'COMPLETED');

INSERT INTO sale_items (sale_id, medicine_id, quantity, unit_price) VALUES
(1, 1, 3, 4.50),
(1, 2, 2, 3.75),
(1, 19, 3, 3.00),
(2, 9, 6, 7.00),
(3, 4, 2, 12.00),
(3, 5, 2, 18.50),
(3, 8, 1, 6.50),
(4, 11, 2, 14.00),
(4, 12, 1, 22.00),
(4, 22, 1, 2.50),
(5, 7, 3, 9.00),
(5, 15, 1, 11.50),
(5, 22, 2, 2.50),
(6, 16, 2, 16.00),
(6, 21, 3, 19.00),
(6, 17, 1, 6.00),
(7, 1, 8, 4.50),
(7, 9, 3, 7.00),
(7, 19, 5, 3.00),
(8, 17, 5, 6.00),
(9, 3, 5, 5.25),
(9, 13, 4, 6.75),
(9, 14, 2, 5.80),
(10, 10, 3, 8.50),
(10, 20, 2, 15.00),
(11, 11, 4, 14.00),
(11, 12, 2, 22.00),
(11, 23, 1, 13.00),
(12, 1, 4, 4.50),
(12, 7, 2, 9.00),
(12, 22, 5, 2.50);

-- =========================
-- Invoices
-- =========================
INSERT INTO invoices (invoice_date, invoice_type, invoice_status, total_amount, sale_id, purchase_id) VALUES
('2026-06-01 09:20:00', 'PURCHASE', 'PAID', 300.00, NULL, 1),
('2026-06-03 10:10:00', 'PURCHASE', 'PAID', 420.00, NULL, 2),
('2026-06-05 13:40:00', 'PURCHASE', 'PAID', 570.00, NULL, 3),
('2026-06-07 11:20:00', 'PURCHASE', 'PAID', 210.00, NULL, 4),
('2026-06-10 14:30:00', 'PURCHASE', 'PAID', 460.00, NULL, 5),
('2026-06-12 16:10:00', 'PURCHASE', 'UNPAID', 250.00, NULL, 6),
('2026-06-14 12:55:00', 'PURCHASE', 'PAID', 600.00, NULL, 7),
('2026-06-16 10:30:00', 'PURCHASE', 'CANCELLED', 333.00, NULL, 8),
('2026-06-02 10:07:00', 'SALE', 'PAID', 31.50, 1, NULL),
('2026-06-03 11:35:00', 'SALE', 'PAID', 42.00, 2, NULL),
('2026-06-04 15:50:00', 'SALE', 'PAID', 68.50, 3, NULL),
('2026-06-05 17:13:00', 'SALE', 'PAID', 52.00, 4, NULL),
('2026-06-06 09:45:00', 'SALE', 'PAID', 43.50, 5, NULL),
('2026-06-07 13:17:00', 'SALE', 'PAID', 97.00, 6, NULL),
('2026-06-08 14:35:00', 'SALE', 'PAID', 72.00, 7, NULL),
('2026-06-09 12:03:00', 'SALE', 'CANCELLED', 30.00, 8, NULL),
('2026-06-10 18:23:00', 'SALE', 'PAID', 83.25, 9, NULL),
('2026-06-11 16:43:00', 'SALE', 'PAID', 55.50, 10, NULL),
('2026-06-12 11:25:00', 'SALE', 'PAID', 116.00, 11, NULL),
('2026-06-13 19:13:00', 'SALE', 'PAID', 49.00, 12, NULL);

SET SQL_SAFE_UPDATES = 1;

-- =========================================================
-- Quick check
-- =========================================================
SELECT 'branches' AS table_name, COUNT(*) AS total_rows FROM branches
UNION ALL SELECT 'categories', COUNT(*) FROM categories
UNION ALL SELECT 'suppliers', COUNT(*) FROM suppliers
UNION ALL SELECT 'customers', COUNT(*) FROM customers
UNION ALL SELECT 'pharmacists', COUNT(*) FROM pharmacists
UNION ALL SELECT 'warehouses', COUNT(*) FROM warehouses
UNION ALL SELECT 'medicines', COUNT(*) FROM medicines
UNION ALL SELECT 'inventory', COUNT(*) FROM inventory
UNION ALL SELECT 'purchases', COUNT(*) FROM purchases
UNION ALL SELECT 'purchase_items', COUNT(*) FROM purchase_items
UNION ALL SELECT 'sales', COUNT(*) FROM sales
UNION ALL SELECT 'sale_items', COUNT(*) FROM sale_items
UNION ALL SELECT 'invoices', COUNT(*) FROM invoices;
