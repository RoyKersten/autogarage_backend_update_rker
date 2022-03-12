
INSERT INTO users (username, password, enabled) VALUES ('mechanic', '$2a$10$wPHxwfsfTnOJAdgYcerBt.utdAvC24B/DWfuXfzKBSDHO0etB1ica', TRUE);
INSERT INTO users (username, password, enabled) VALUES ('admin_clerk', '$2a$10$wPHxwfsfTnOJAdgYcerBt.utdAvC24B/DWfuXfzKBSDHO0etB1ica', TRUE);
INSERT INTO users (username, password, enabled) VALUES ('cashier', '$2a$10$wPHxwfsfTnOJAdgYcerBt.utdAvC24B/DWfuXfzKBSDHO0etB1ica', TRUE);
INSERT INTO users (username, password, enabled) VALUES ('backoffice', '$2a$10$wPHxwfsfTnOJAdgYcerBt.utdAvC24B/DWfuXfzKBSDHO0etB1ica', TRUE);
INSERT INTO users (username, password, enabled) VALUES ('admin', '$2a$10$wPHxwfsfTnOJAdgYcerBt.utdAvC24B/DWfuXfzKBSDHO0etB1ica', TRUE);

INSERT INTO authorities (username, authority) VALUES ('admin_clerk', 'ROLE_ADMIN_CLERK');
INSERT INTO authorities (username, authority) VALUES ('cashier', 'ROLE_CASHIER');
INSERT INTO authorities (username, authority) VALUES ('backoffice', 'ROLE_BACKOFFICE');
INSERT INTO authorities (username, authority) VALUES ('mechanic', 'ROLE_MECHANIC');
INSERT INTO authorities (username, authority) VALUES ('admin', 'ROLE_ADMIN');


INSERT INTO customer (first_name, last_name, phone_number, email)
VALUES
('Tom', 'de Graaf', '+31612345678', 'tom.degraaf@gmail.com'),
('Elly', 'van Doorn', '+31687654321', 'e.vandoorn@hmail.com'),
('Debby', 'Huismans', '+31654678515', 'debby.huismans@kpn.nl'),
('Piet', 'van de Berg', '+31654678222', 'piet.vdberg@mail.com'),
('Karel', 'Viends', '+31685746114', 'karel.vriends@ziggo.nl');


INSERT INTO car (brand, model,year_of_construction, license_plate_number, customer_id_customer, experiation_date_inspection)
VALUES
('Volkswagen', 'Polo', '2021', '55-AWA-23','1', 20220521),
('Seat', 'Ibiza', '2018', '26-DEV-38','2', 20220305),
('Renault', 'Megan', '2019', '41-EGA-22', '3', 20220803),
('Opel', 'Zafira', '2016', '66-PGA-74', '4', 20221018),
('Ford', 'Mustang', '1976', 'WZ-HH-65', '5', 20221207);


INSERT INTO item (item_type, item_name, qty, price, brand, item_category, status)
VALUES
('activity','keuring auto', '1', 45.00,'' ,'keuring','LOCKED'),
('activity','overige handelingen', '1',0.00 ,'' ,'overige activiteit','OPEN'),
('activity','banden vervangen', '1',25.00 ,'' ,'banden','LOCKED'),
('activity','remmen vervangen', '1',35.00 ,'' ,'remmen','LOCKED'),
('activity','uitlaat vervangen', '1',25.00 ,'' ,'uitlaat','LOCKED'),
('activity','olie verversen', '1',10.00 ,'' ,'olie','LOCKED'),
('activity','voorruit vervangen', '1',35.00 ,'' ,'ruiten','LOCKED'),
('activity','voorruit repareren', '1',10.00 ,'' ,'ruiten','LOCKED'),
('activity','verlichting repareren', '1',5.00 ,'' ,'verlichting','LOCKED'),

('part','zomerband 205/55/R16', '20', 55.00, 'Goodyear', 'banden','LOCKED'),
('part','zomerband 200/45/R15', '20', 54.00, 'Goodyear', 'banden','LOCKED'),
('part','zomerband 190/50/R15', '20', 50.00, 'Continental', 'banden','LOCKED'),

('part','winterband 205/55/R16', '12', 60.00, 'Goodyear', 'banden', 'LOCKED'),
('part','winterband 200/45/R15', '12', 58.00, 'Goodyear', 'banden', 'LOCKED'),
('part','winterband 90/50/R15', '12', 56.00, 'Continental','banden', 'LOCKED'),

('part','remschijf voor', '20', 129.99, 'Bosch', 'remmen','LOCKED'),
('part','remschijf achter', '20', 149.99, 'Bosch', 'remmen','LOCKED'),

('part','uitlaat Opel', '5', 249.99, 'Bosal', 'uitlaat','LOCKED'),
('part','uitlaat Ford', '5', 244.99, 'Bosch', 'uitlaat','LOCKED'),
('part','uitlaat Volkswagen / Seat', '5', 299.99, 'Bosch', 'uitlaat','LOCKED'),

('part','voorruit Opel', '2', 149.99, 'Saint-Gobain Sekurit', 'ruiten','LOCKED'),
('part','voorruit Ford', '2', 189.99, 'Pilkington', 'ruiten','LOCKED'),
('part','voorruit Volkswagen / Seat', '2', 199.99, 'Saint-Gobain Sekurit', 'ruiten','LOCKED'),
('part','achterruit Opel', '1', 119.99, 'Saint-Gobain Sekurit', 'ruiten','LOCKED'),
('part','achterruit Ford', '1', 129.99, 'Pilkington', 'ruiten','LOCKED'),
('part','achterruit Volkswagen / Seat', '1', 149.99, 'Saint-Gobain Sekurit', 'ruiten','LOCKED');



INSERT INTO service (service_type, service_date, service_status, issues_found_inspection, issues_to_repair,customer_id_customer, car_id_car)
VALUES
('inspection', '2021-06-08' , 'NIET_UITVOEREN' ,'remmen voor vervangen, 4 nieuwe banden, olie verversen',null,1,1),
('repair', '2021-01-05' , 'VOLTOOID' ,null, '4 x banden vervangen, uitlaat vervangen',2,1),
('inspection', '2021-06-08' , 'NIET_UITVOEREN' ,'remverlichting vervangen, uitlaat vervangen, olie verversen',null,3,2),
('repair', '2021-07-08' , 'UITVOEREN' ,null, 'remschijven voor vervangen',2,1);



INSERT INTO invoice (invoice_type, invoice_status, invoice_subtotal, vat_rate, vat_amount,invoice_total, customer_id_customer, service_id_service)
VALUES
('inspection_invoice', 'BETAALD' , 45.00, 0.21, 9.45, 54.45,'1','1'),
('repair_invoice', 'OPEN' , 354.99, 0.21, 74.55, 429.54,'2','2');

INSERT INTO service_line (service_line_number,item_id_item, item_name,qty, price, line_subtotal, vat_amount,vat_rate, line_total,service_id_service, invoice_id_invoice)
VALUES
('1','1', 'keuring' ,'1', 45.00, 45.00,9.45,0.21,54.45,'1','1'),
('1','10', 'zomerband 205/55/R16' ,'4', 55.00, 220.00,46.20,0.21,266.20,'2','2'),
('2','20', 'uitlaat Volkswagen / Seat' ,'1', 299.99, 299.99,62.99,0.21,362.98,'2','2'),
('1','1', 'keuring' ,'1', 45.00, 45.00,9.45,0.21,54.45,'3',null),
('1','1', 'keuring' ,'1', 45.00, 45.00,9.45,0.21,54.45,'4',null),
('2','5', 'remmen voor vervangen' ,'1', 35.00, 35.00,7.35,0.21,52.35,'4',null),
('3','16', 'remschijf voor' ,'2', 129.99, 259.98,54.60,0.21,314.58,'4',null);