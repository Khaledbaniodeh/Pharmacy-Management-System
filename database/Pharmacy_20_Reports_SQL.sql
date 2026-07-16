-- Noor AlHuda Pharmacy - Required 20 Report Queries
-- Uses the SQL topics from class: SELECT, JOIN, WHERE, BETWEEN, GROUP BY, SUM, COUNT, ORDER BY, LIMIT.

-- 1. Retrieve all medicines with their category, supplier, selling price, and expiration date.
SELECT m.medicine_id AS ID, m.medicine_name AS Medicine,
       c.category_name AS Category, s.supplier_name AS Supplier,
       m.selling_price AS Price, m.expiry_date AS Expiry_Date, m.status AS Status
FROM medicines m
JOIN categories c ON m.category_id = c.category_id
JOIN suppliers s ON m.supplier_id = s.supplier_id
ORDER BY m.medicine_name;

-- 2. Find all medicines that are expired or will expire within the next 30 days.
SELECT m.medicine_name AS Medicine, c.category_name AS Category,
       s.supplier_name AS Supplier, m.expiry_date AS Expiry_Date,
       DATEDIFF(m.expiry_date, CURDATE()) AS Days_Left
FROM medicines m
JOIN categories c ON m.category_id = c.category_id
JOIN suppliers s ON m.supplier_id = s.supplier_id
WHERE m.expiry_date < CURDATE()
   OR m.expiry_date <= DATE_ADD(CURDATE(), INTERVAL 30 DAY)
ORDER BY m.expiry_date;

-- 3. Retrieve all medicines whose total quantity across all warehouses is below the reorder level.
SELECT m.medicine_name AS Medicine,
       COALESCE(SUM(i.quantity), 0) AS Total_Quantity,
       COALESCE(SUM(i.reorder_level), 0) AS Total_Reorder_Level
FROM medicines m
JOIN inventory i ON m.medicine_id = i.medicine_id
GROUP BY m.medicine_id, m.medicine_name
HAVING COALESCE(SUM(i.quantity), 0) < COALESCE(SUM(i.reorder_level), 0)
ORDER BY Total_Quantity ASC;

-- 4. Compute the total available quantity of each medicine across all warehouses.
SELECT m.medicine_name AS Medicine, COALESCE(SUM(i.quantity), 0) AS Total_Quantity
FROM medicines m
LEFT JOIN inventory i ON m.medicine_id = i.medicine_id
GROUP BY m.medicine_id, m.medicine_name
ORDER BY Total_Quantity DESC, m.medicine_name;

-- 5. Retrieve the quantity of each medicine stored in each warehouse.
SELECT CONCAT(w.warehouse_name, ' / ', m.medicine_name) AS Warehouse_Medicine,
       w.warehouse_name AS Warehouse, m.medicine_name AS Medicine,
       i.quantity AS Quantity, i.status AS Status
FROM inventory i
JOIN warehouses w ON i.warehouse_id = w.warehouse_id
JOIN medicines m ON i.medicine_id = m.medicine_id
ORDER BY w.warehouse_name, m.medicine_name;

-- 6. Find the warehouse that stores the highest total number of medicine units.
SELECT w.warehouse_name AS Warehouse, COALESCE(SUM(i.quantity), 0) AS Total_Units
FROM warehouses w
JOIN inventory i ON w.warehouse_id = i.warehouse_id
GROUP BY w.warehouse_id, w.warehouse_name
ORDER BY Total_Units DESC
LIMIT 1;

-- 7. Retrieve all medicines supplied by a specific supplier, sorted by medicine name.
-- Replace ? with supplier_id in MySQL or pass it from Java PreparedStatement.
SELECT s.supplier_name AS Supplier, m.medicine_name AS Medicine,
       c.category_name AS Category, m.selling_price AS Price, m.expiry_date AS Expiry_Date
FROM medicines m
JOIN suppliers s ON m.supplier_id = s.supplier_id
JOIN categories c ON m.category_id = c.category_id
WHERE m.supplier_id = ?
ORDER BY m.medicine_name;

-- 8. List suppliers with the total number of medicines they supply.
SELECT s.supplier_name AS Supplier, COUNT(m.medicine_id) AS Number_Of_Medicines
FROM suppliers s
LEFT JOIN medicines m ON s.supplier_id = m.supplier_id
GROUP BY s.supplier_id, s.supplier_name
ORDER BY Number_Of_Medicines DESC, s.supplier_name;

-- 9. Retrieve all sales transactions within a specific date range.
SELECT s.sale_id AS Sale_ID, s.sale_date AS Sale_Date,
       CONCAT(c.first_name, ' ', c.last_name) AS Customer,
       CONCAT(p.first_name, ' ', p.last_name) AS Pharmacist,
       b.branch_name AS Branch, s.payment_method AS Payment,
       s.total_amount AS Total, s.sale_status AS Status
FROM sales s
JOIN customers c ON s.customer_id = c.customer_id
JOIN pharmacists p ON s.pharmacist_id = p.pharmacist_id
JOIN branches b ON s.branch_id = b.branch_id
WHERE s.sale_date BETWEEN ? AND ?
ORDER BY s.sale_date;

-- 10. Retrieve all sales transactions and their sold medicine details.
SELECT CONCAT('Sale ', s.sale_id, ' / ', m.medicine_name) AS Sale_Medicine,
       s.sale_id AS Sale_ID, s.sale_date AS Sale_Date,
       CONCAT(c.first_name, ' ', c.last_name) AS Customer,
       m.medicine_name AS Medicine, si.quantity AS Quantity,
       si.unit_price AS Unit_Price, si.line_total AS Line_Total
FROM sales s
JOIN customers c ON s.customer_id = c.customer_id
JOIN sale_items si ON s.sale_id = si.sale_id
JOIN medicines m ON si.medicine_id = m.medicine_id
ORDER BY s.sale_date DESC, s.sale_id;

-- 11. Compute the total sales amount for each day within a specific period.
SELECT DATE(s.sale_date) AS Sale_Day,
       COALESCE(SUM(s.total_amount), 0) AS Daily_Total
FROM sales s
WHERE s.sale_status <> 'CANCELLED'
  AND s.sale_date BETWEEN ? AND ?
GROUP BY DATE(s.sale_date)
ORDER BY DATE(s.sale_date);

-- 12. Compute the total profit generated within a specific date range.
SELECT DATE(sa.sale_date) AS Sale_Day,
       SUM(si.quantity * (si.unit_price - COALESCE(pc.avg_cost, 0))) AS Total_Profit
FROM sales sa
JOIN sale_items si ON sa.sale_id = si.sale_id
LEFT JOIN (
    SELECT medicine_id, AVG(cost_price) AS avg_cost
    FROM purchase_items
    GROUP BY medicine_id
) pc ON si.medicine_id = pc.medicine_id
WHERE sa.sale_status <> 'CANCELLED'
  AND sa.sale_date BETWEEN ? AND ?
GROUP BY DATE(sa.sale_date)
ORDER BY DATE(sa.sale_date);

-- 13. Retrieve the top 5 most sold medicines based on total quantity sold.
SELECT m.medicine_name AS Medicine, SUM(si.quantity) AS Total_Quantity_Sold,
       SUM(si.line_total) AS Total_Revenue
FROM sale_items si
JOIN sales s ON si.sale_id = s.sale_id
JOIN medicines m ON si.medicine_id = m.medicine_id
WHERE s.sale_status <> 'CANCELLED'
GROUP BY m.medicine_id, m.medicine_name
HAVING SUM(si.quantity) > 0
ORDER BY Total_Quantity_Sold DESC
LIMIT 5;

-- 14. Retrieve the least sold medicines to identify slow-moving products.
SELECT m.medicine_name AS Medicine, COALESCE(SUM(si.quantity), 0) AS Total_Sold
FROM medicines m
LEFT JOIN sale_items si ON m.medicine_id = si.medicine_id
LEFT JOIN sales s ON si.sale_id = s.sale_id
WHERE s.sale_status <> 'CANCELLED' OR s.sale_id IS NULL
GROUP BY m.medicine_id, m.medicine_name
ORDER BY Total_Sold ASC, m.medicine_name
LIMIT 10;

-- 15. Find customers with the highest total purchase amount.
SELECT CONCAT(c.first_name, ' ', c.last_name) AS Customer,
       COUNT(s.sale_id) AS Number_Of_Sales,
       COALESCE(SUM(s.total_amount), 0) AS Total_Spent
FROM customers c
JOIN sales s ON c.customer_id = s.customer_id
WHERE s.sale_status <> 'CANCELLED'
GROUP BY c.customer_id, c.first_name, c.last_name
ORDER BY Total_Spent DESC;

-- 16. List the number of sales processed by each pharmacist within a given period.
SELECT CONCAT(p.first_name, ' ', p.last_name) AS Pharmacist,
       COUNT(s.sale_id) AS Number_Of_Sales
FROM pharmacists p
JOIN sales s ON p.pharmacist_id = s.pharmacist_id
WHERE s.sale_status <> 'CANCELLED'
  AND s.sale_date BETWEEN ? AND ?
GROUP BY p.pharmacist_id, p.first_name, p.last_name
ORDER BY Number_Of_Sales DESC;

-- 17. Retrieve all purchase transactions from a specific supplier within a given date range.
SELECT pu.purchase_id AS Purchase_ID, pu.purchase_date AS Purchase_Date,
       su.supplier_name AS Supplier, CONCAT(ph.first_name, ' ', ph.last_name) AS Pharmacist,
       b.branch_name AS Branch, pu.total_cost AS Total_Cost, pu.purchase_status AS Status
FROM purchases pu
JOIN suppliers su ON pu.supplier_id = su.supplier_id
JOIN pharmacists ph ON pu.pharmacist_id = ph.pharmacist_id
JOIN branches b ON pu.branch_id = b.branch_id
WHERE pu.supplier_id = ?
  AND pu.purchase_date BETWEEN ? AND ?
ORDER BY pu.purchase_date;

-- 18. Compute the total purchase cost for each supplier.
SELECT su.supplier_name AS Supplier, COUNT(pu.purchase_id) AS Number_Of_Purchases,
       COALESCE(SUM(pu.total_cost), 0) AS Total_Purchases
FROM suppliers su
LEFT JOIN purchases pu ON su.supplier_id = pu.supplier_id
                         AND pu.purchase_status <> 'CANCELLED'
GROUP BY su.supplier_id, su.supplier_name
ORDER BY Total_Purchases DESC;

-- 19. Find the most commonly used payment method in sales transactions.
SELECT payment_method AS Payment_Method, COUNT(sale_id) AS Number_Of_Sales
FROM sales
WHERE sale_status <> 'CANCELLED'
GROUP BY payment_method
ORDER BY Number_Of_Sales DESC
LIMIT 1;

-- 20. Retrieve medicine categories ranked by total sales amount.
SELECT c.category_name AS Category, SUM(si.quantity) AS Total_Quantity_Sold,
       SUM(si.line_total) AS Total_Revenue
FROM sale_items si
JOIN sales s ON si.sale_id = s.sale_id
JOIN medicines m ON si.medicine_id = m.medicine_id
JOIN categories c ON m.category_id = c.category_id
WHERE s.sale_status <> 'CANCELLED'
GROUP BY c.category_id, c.category_name
ORDER BY Total_Revenue DESC;
