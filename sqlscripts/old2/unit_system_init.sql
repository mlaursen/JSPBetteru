CREATE TABLE UNIT_SYSTEM(NAME VARCHAR2(20), CONSTRAINT PK_UNIT_SYSTEM PRIMARY KEY (NAME));

-------------------------------------------------------------------------------
-- UNIT_SYSTEM PROCEDURES
-------------------------------------------------------------------------------
CREATE OR REPLACE PACKAGE UNIT_SYSTEM_PKG AS
  
  -- Returns a SYS_REFCURSOR for all UNIT_SYSTEMs
  PROCEDURE GET(PCURSOR OUT SYS_REFCURSOR);
  
END UNIT_SYSTEM_PKG;
/

CREATE OR REPLACE PACKAGE BODY UNIT_SYSTEM_PKG AS
  PROCEDURE GET(PCURSOR OUT SYS_REFCURSOR)
  IS
  BEGIN
    OPEN PCURSOR FOR
      SELECT NAME
      FROM UNIT_SYSTEM;
  END GET; 
  
END UNIT_SYSTEM_PKG;
/
-------------------------------------------------------------------------------
-- UNIT_SYSTEM INITIAL DATA
-------------------------------------------------------------------------------
INSERT INTO UNIT_SYSTEM
VALUES('IMPERIAL');
INSERT INTO UNIT_SYSTEM
VALUES('METRIC');
COMMIT;

ALTER TABLE UNIT_SYSTEM READ ONLY;