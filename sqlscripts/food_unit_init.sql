CREATE TABLE FOOD_UNIT(NAME VARCHAR2(8), LONG_NAME VARCHAR2(15), CONSTRAINT PK_FOOD_UNIT PRIMARY KEY(NAME));
-------------------------------------------------------------------------------
-- UNIT PROCEDURES
-------------------------------------------------------------------------------
CREATE OR REPLACE PACKAGE FOOD_UNIT_PKG AS
  -- Creates a new FOOD_UNIT
  PROCEDURE NEW(PNAME IN FOOD_UNIT.NAME%TYPE, PLONG IN FOOD_UNIT.LONG_NAME%TYPE);
  
  -- Returns a SYS_REFCURSOR for all FOOD_UNITs
  PROCEDURE GET(PCURSOR OUT SYS_REFCURSOR);
  
  -- Returns a SYS_REFCURSOR for a single food unit
  PROCEDURE GET(PNAME IN FOOD_UNIT.NAME%TYPE, PCURSOR OUT SYS_REFCURSOR);
END FOOD_UNIT_PKG;
/

CREATE OR REPLACE PACKAGE BODY FOOD_UNIT_PKG AS
  PROCEDURE NEW(PNAME IN FOOD_UNIT.NAME%TYPE, PLONG IN FOOD_UNIT.LONG_NAME%TYPE)
  IS
  BEGIN
    INSERT INTO FOOD_UNIT(NAME, LONG_NAME)
    VALUES(PNAME, PLONG);
    COMMIT;
    
    EXCEPTION
      WHEN OTHERS THEN
        ROLLBACK;
        RAISE;
  END NEW;
  
  PROCEDURE GET(PCURSOR OUT SYS_REFCURSOR)
  IS
  BEGIN
    OPEN PCURSOR FOR
      SELECT *
      FROM FOOD_UNIT;
  END GET; 
  
  PROCEDURE GET(PNAME IN FOOD_UNIT.NAME%TYPE, PCURSOR OUT SYS_REFCURSOR)
  IS
  BEGIN
    OPEN PCURSOR FOR
      SELECT *
      FROM FOOD_UNIT
      WHERE UPPER(NAME)=UPPER(PNAME);
  END GET; 
END FOOD_UNIT_PKG;
/

-------------------------------------------------------------------------------
-- UNIT INITIAL DATA
-------------------------------------------------------------------------------
EXEC FOOD_UNIT_PKG.NEW('g', 'gram');
EXEC FOOD_UNIT_PKG.NEW( 'mL', 'millileter');
EXEC FOOD_UNIT_PKG.NEW('oz', 'ounce');
EXEC FOOD_UNIT_PKG.NEW('unit', 'unit');
EXEC FOOD_UNIT_PKG.NEW('c', 'cup');
EXEC FOOD_UNIT_PKG.NEW('tbsp', 'tablespoon');
EXEC FOOD_UNIT_PKG.NEW('tsp', 'teaspoon');
EXEC FOOD_UNIT_PKG.NEW('unknown', 'unknown');
EXEC FOOD_UNIT_PKG.NEW('scoop', 'scoop');