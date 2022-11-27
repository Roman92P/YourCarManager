INSERT INTO Car (id, manufacturer, model, vin, production_year) VALUES (1, 'OPEL', 'ASTRA','VSX9AERLVD9DH4178','2016');
INSERT INTO YCMCUSTOMER (id,nick, email, role, name, last_name) VALUES (1,'Roman','rp@gmail.com', 1,'Roman','Pashkov');
INSERT INTO ADDRESS (id, town, post_code, street, build_number, apartment,country) VALUES (1,'San Francisco','00-001','Alcatraz','1','4',1);
UPDATE YCMCUSTOMER SET ADDRESS_ID = 1 WHERE id = 1;
INSERT INTO Car (id, manufacturer, model, vin, production_year) VALUES (2, 'VW', 'GOLF','WV12754CJLB1J1918','2005');
INSERT INTO YCMCUSTOMER (id,nick, email, role, name, last_name) VALUES (2,'Roman2','rp@gmail.com',2,'Roman','Pashkov');
INSERT INTO ADDRESS (id, town, post_code, street, build_number, apartment,country) VALUES (2,'Santa Barbara','00-002','East route','1','4',1);
UPDATE YCMCUSTOMER SET ADDRESS_ID = 2 WHERE id = 2;
INSERT INTO YCMSHOPSERVICE ( service_price, service_type, currency, timing_hours, service_description) VALUES ('500.0',9,0,'0.5','Change motor oil with oil filter');
INSERT INTO YCMSHOPSERVICE (service_price, service_type, currency, timing_hours, service_description) VALUES ('300.0',9,0,'0.5','Replacement of spark plug candles');
INSERT INTO YCMSHOPSERVICE (service_price, service_type, currency, timing_hours, service_description) VALUES ('800.0',9,0,'1.0','Engine cleaning');
INSERT INTO YCMSHOP (nick, email, role, address_id, shop_name) VALUES ('shopOne','rp@gmail.com',2,2,'ONE SHOP');
INSERT INTO  YCMCUSTOMERSERVICE(service_type, service_description,service_price, currency, service_appointment_day, ycmcustomer_id, service_hour, shop_id) VALUES (9, 'Change motor oil with oil filter','500', 0, '30-Sep-2022',1, '16:00', 1);
INSERT INTO  YCMCUSTOMERSERVICE(service_type, service_description,service_price, currency, service_appointment_day, ycmcustomer_id, service_hour, shop_id) VALUES (9, 'Replacement of spark plug candles','300', 0,'30-Sep-2022',2, '16:30', 1);
INSERT INTO  YCMCUSTOMERSERVICE(service_type, service_description,service_price, currency, service_appointment_day, ycmcustomer_id, service_hour, shop_id) VALUES (9, 'Engine cleaning','800', 0,'30-Sep-2022',1, '17:00', 1);
INSERT INTO SHOP_SERVICES (ycm_shop_id, service_id) VALUES (1,1);
INSERT INTO SHOP_SERVICES (ycm_shop_id, service_id) VALUES (1,2);
INSERT INTO SHOP_SERVICES (ycm_shop_id, service_id) VALUES (1,3);

