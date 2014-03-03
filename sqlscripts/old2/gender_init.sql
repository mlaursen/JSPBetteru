CREATE TABLE GENDER(NAME VARCHAR2(15), CONSTRAINT PK_GENDER PRIMARY KEY(NAME));

CREATE SEQUENCE SEQ_GENDERID
START WITH 0
MINVALUE 0
INCREMENT BY 1
CACHE 20
NOCYCLE;

-------------------------------------------------------------------------------
-- GENDER STORED PROCEDURES
-------------------------------------------------------------------------------
CREATE OR REPLACE PACKAGE GENDER_PKG AS
  -- Creates a new Gender
  PROCEDURE NEW(PNAME IN GENDER.NAME%TYPE);
  
  -- Returns a SYS_REFCURSOR for all genders
  PROCEDURE GET(PCURSOR OUT SYS_REFCURSOR);
  
END GENDER_PKG;
/

CREATE OR REPLACE PACKAGE BODY GENDER_PKG AS
  PROCEDURE NEW(PNAME IN GENDER.NAME%TYPE)
  IS
  BEGIN
    INSERT INTO GENDER(NAME)
    VALUES(PNAME);
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
      SELECT NAME
      FROM GENDER;
  END GET; 
  
END GENDER_PKG;
/

-------------------------------------------------------------------------------
-- GENDER DATA
-------------------------------------------------------------------------------
EXEC GENDER_PKG.NEW('MALE');
EXEC GENDER_PKG.NEW('FEMALE');