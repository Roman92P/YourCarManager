INSERT INTO Car (id, manufacturer, model, vin, production_year) VALUES (1, 'OPEL', 'ASTRA','VSX9AERLVD9DH4178','2016');
INSERT INTO YCMCUSTOMER (id, nick, email, role, name, last_name) VALUES (1, 'Roman','rp@gmail.com', 1,'Roman','Pashkov');
INSERT INTO ADDRESS (id, town, post_code, street, build_number, apartment,country) VALUES (1,'San Francisco','00-001','Alcatraz','1','4',2);
UPDATE YCMCUSTOMER SET ADDRESS_ID = 1 WHERE id = 1;
INSERT INTO Car (id, manufacturer, model, vin, production_year) VALUES (2, 'VW', 'GOLF','WV12754CJLB1J1918','2005');
INSERT INTO YCMCUSTOMER (id, nick, email, role, name, last_name) VALUES (2, 'Roman2','rp@gmail.com',2,'Roman','Pashkov');
INSERT INTO ADDRESS (id, town, post_code, street, build_number, apartment,country) VALUES (2,'Santa Barbara','00-002','East route','1','4',2);
UPDATE YCMCUSTOMER SET ADDRESS_ID = 2 WHERE id = 2;
INSERT INTO YCMSHOP (id, nick, email, role, address_id, shop_name) VALUES (3,'shopOne','rp@gmail.com',3,2,'ONE SHOP');


