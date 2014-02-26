CREATE TABLE FORMULA
( NAME VARCHAR2(25)
, UNIT_SYSTEM VARCHAR2(20)
, CONSTRAINT PK_FORMULA_NAME PRIMARY KEY(NAME)
, CONSTRAINT FK_FORMULA_UNIT_SYSTEM FOREIGN KEY(UNIT_SYSTEM) REFERENCES UNIT_SYSTEM(NAME)
);


CREATE OR REPLACE PACKAGE FORMULA_PKG AS
  -- Creates a new formula
  PROCEDURE NEW(PNAME IN FORMULA.NAME%TYPE, PUNIT IN FORMULA.UNIT_SYSTEM%TYPE DEFAULT 'IMPERIAL');
  
  -- Returns a single formula by name
  PROCEDURE GET(PNAME IN FORMULA.NAME%TYPE, PCURSOR OUT SYS_REFCURSOR);
  
  -- Returns all formulas
  PROCEDURE GET(PCURSOR OUT SYS_REFCURSOR);
  
END FORMULA_PKG;
/

CREATE OR REPLACE PACKAGE BODY FORMULA_PKG AS
  PROCEDURE NEW(PNAME IN FORMULA.NAME%TYPE, PUNIT IN FORMULA.UNIT_SYSTEM%TYPE DEFAULT 'IMPERIAL')
  IS
  BEGIN
    INSERT INTO FORMULA(NAME, UNIT_SYSTEM)
    VALUES(UPPER(PNAME), UPPER(PUNIT));
    COMMIT;
    
    EXCEPTION
      WHEN OTHERS THEN
        ROLLBACK;
        RAISE;
  END NEW;
  
  PROCEDURE GET(PNAME IN FORMULA.NAME%TYPE, PCURSOR OUT SYS_REFCURSOR)
  IS
  BEGIN
    OPEN PCURSOR FOR
      SELECT *
      FROM FORMULA
      WHERE NAME=PNAME;
  END GET;
  
  PROCEDURE GET(PCURSOR OUT SYS_REFCURSOR)
  IS
  BEGIN
    OPEN PCURSOR FOR
      SELECT *
      FROM FORMULA;
  END GET;
  
END FORMULA_PKG;
/





-----------------------------------------------------------------
-- Initial Data
---------------
EXEC FORMULA_PKG.NEW('MIFFLIN-ST JOER', 'METRIC');
EXEC FORMULA_PKG.NEW('HARRIS-BENEDICT');
EXEC FORMULA_PKG.NEW('SIMPLE MULTIPLIER');