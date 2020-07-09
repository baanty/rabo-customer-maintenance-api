 
INSERT INTO ADDRESS (
	ID, 
	STREET_NAME, 
	CITY) VALUES
  (10, 'Eemnesserweg', 'Hilversum'),
  (11, 'Seinstraat', 'Hilversum');

  
INSERT INTO CUSTOMER (
	ID, 
	FIRST_NAME, 
	LAST_NAME,
	AGE,
	ADDRESS_ID) VALUES
  (10, 'Pijush K', 'Das', 99, 1),
  (11, 'Mijndert', 'Rebel', 45, 2);
  
  
  
COMMIT;