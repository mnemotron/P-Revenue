-- APPLICATION: 		REVENUE 
-- SCRIPT DESCRIPTION: 	INITIALISATION


-- ENTITY: 				CONFIG
-- ----------------------------------------------------------
-- LANGUAGE VALUE: ISO 639-1
INSERT INTO T_CONFIG (key, value) VALUES ('LANGUAGE', 'EN'); 

-- CURRENCY VALUE: ISO 4217
INSERT INTO T_CONFIG (key, value) VALUES ('CURRENCY', 'EUR'); 

-- SHOW SPLASH SCREEN: BOOLEAN (true|false)
INSERT INTO T_CONFIG (key, value) VALUES ('SPLASH_SCREEN', 'true');

-- ENTITY				STOCK DATA PROVIDER
-- ----------------------------------------------------------
INSERT INTO T_STOCKDATAPROVIDER (id, name) VALUES ('DY', 'DUMMY');
INSERT INTO T_STOCKDATAPROVIDER (id, name) VALUES ('YF', 'YAHOO FINANCE');