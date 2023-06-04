INSERT INTO Car (id, manufacturer, model, vin, production_year) VALUES (1, 'OPEL', 'ASTRA','VSX9AERLVD9DH4178','2016');
INSERT INTO YCMCUSTOMER (id,nick, email, role, name, last_name) VALUES (1,'Roman','rp@gmail.com', 1,'Roman','Pashkov');
INSERT INTO ADDRESS (id, town, post_code, street, build_number, apartment,country) VALUES (1,'San Francisco','00-001','Alcatraz','1','4',1);
UPDATE YCMCUSTOMER SET ADDRESS_ID = 1 WHERE id = 1;
INSERT INTO Car (id, manufacturer, model, vin, production_year) VALUES (2, 'VW', 'GOLF','WV12754CJLB1J1918','2005');
INSERT INTO YCMCUSTOMER (id,nick, email, role, name, last_name) VALUES (2,'Roman2','rp@gmail.com',2,'Roman','Pashkov');
INSERT INTO ADDRESS (id, town, post_code, street, build_number, apartment,country) VALUES (2,'Santa Barbara','00-002','East route','1','4',1);
UPDATE YCMCUSTOMER SET ADDRESS_ID = 2 WHERE id = 2;
INSERT INTO YCMSHOPPRODUCT ( service_price, service_type, currency, timing_hours, service_description, short_service_name) VALUES ('500.0',9,0,'0.5','Change motor oil with oil filter', 'Motor oil');
INSERT INTO YCMSHOPPRODUCT (service_price, service_type, currency, timing_hours, service_description, short_service_name) VALUES ('300.0',9,0,'0.5','Replacement of spark plug candles', 'Engine candles');
INSERT INTO YCMSHOPPRODUCT (service_price, service_type, currency, timing_hours, service_description, short_service_name) VALUES ('800.0',9,0,'1.0','Engine cleaning', 'Engine cleaning');
INSERT INTO YCMSHOP (nick, email, role, address_id, shop_name) VALUES ('shopOne','rp@gmail.com',2,2,'ONE SHOP');
INSERT INTO YCM_SHOP_WORKER(shop_worker_lastname, shop_worker_name, ycm_shop_id) VALUES ('Pashkov', 'Roman', 1);
INSERT INTO YCMWORKER_SERVICETYPE (worker_id, worker_specialization) VALUES (1, 'AIR_CONDITIONER');
INSERT INTO YCMWORKER_SERVICETYPE (worker_id, worker_specialization) VALUES (1, 'ELECTRICAL_SYSTEM');
INSERT INTO YCMWORKER_SERVICETYPE (worker_id, worker_specialization) VALUES (1, 'BRAKE_SERVICES');
INSERT INTO YCMWORKER_SERVICETYPE (worker_id, worker_specialization) VALUES (1, 'AUTO_DIAGNOSTIC');
INSERT INTO YCMWORKER_SERVICETYPE (worker_id, worker_specialization) VALUES (1, 'ENGINE');
INSERT INTO YCMWORKER_SERVICETYPE (worker_id, worker_specialization) VALUES (1, 'TRANSMISSION');
INSERT INTO YCMWORKER_SERVICETYPE (worker_id, worker_specialization) VALUES (1, 'TIRES');
INSERT INTO YCMWORKER_SERVICETYPE (worker_id, worker_specialization) VALUES (1, 'MAINTENANCE');
INSERT INTO YCMWORKER_SERVICETYPE (worker_id, worker_specialization) VALUES (1, 'OTHER');
INSERT INTO YCMCUSTOMERAPPOINTMENT(service_type, service_description,service_price, currency, service_appointment_day, ycmcustomer_id, service_hour, shop_id, short_service_name, worker_id, start_timestamp, end_timestamp) VALUES (9, 'Change motor oil with oil filter','500', 0, '30-Jun-2023',1, '16:00', 1, 'Motor oil', 1, '2023-06-30 16:00:00.0', '2023-06-30 16:30:00.0');
INSERT INTO YCMCUSTOMERAPPOINTMENT(service_type, service_description,service_price, currency, service_appointment_day, ycmcustomer_id, service_hour, shop_id, short_service_name, worker_id, start_timestamp, end_timestamp) VALUES (9, 'Replacement of spark plug candles','300', 0,'30-Jun-2023',2, '16:30', 1,  'Engine candles', 1, '2023-06-30 16:30:00.0', '2023-06-30 17:00:00.0');
INSERT INTO YCMCUSTOMERAPPOINTMENT(service_type, service_description,service_price, currency, service_appointment_day, ycmcustomer_id, service_hour, shop_id, short_service_name, worker_id, start_timestamp, end_timestamp) VALUES (9, 'Engine cleaning','800', 0,'30-Jun-2023',1, '17:00', 1, 'Engine cleaning', 1, '2023-06-30 17:00:00.0', '2023-06-30 18:00:00.0');
INSERT INTO SHOP_SERVICES (ycm_shop_id, service_id) VALUES (1,1);
INSERT INTO SHOP_SERVICES (ycm_shop_id, service_id) VALUES (1,2);
INSERT INTO SHOP_SERVICES (ycm_shop_id, service_id) VALUES (1,3);
INSERT INTO YCM_WORKER_JOBS (ycm_shop_worker_id, ycm_customer_appointment_id) VALUES (1,1);
INSERT INTO YCM_WORKER_JOBS (ycm_shop_worker_id, ycm_customer_appointment_id) VALUES (1,2);
INSERT INTO YCM_WORKER_JOBS (ycm_shop_worker_id, ycm_customer_appointment_id) VALUES (1,3);






