CREATE TABLE BRAND(NAME VARCHAR2(40) NOT NULL, CONSTRAINT PK_BRAND_NAME PRIMARY KEY (NAME));


CREATE OR REPLACE PACKAGE BRAND_PKG AS
  -- Creates a new brand
  PROCEDURE NEW(PNAME IN BRAND.NAME%TYPE);
  
  -- Returns a SYS_REFCURSOR for all the brands
  PROCEDURE GET(PCURSOR OUT SYS_REFCURSOR);
  
END BRAND_PKG;
/

CREATE OR REPLACE PACKAGE BODY BRAND_PKG AS
  PROCEDURE NEW(PNAME IN BRAND.NAME%TYPE)
  IS
  BEGIN
    INSERT INTO BRAND(NAME)
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
      SELECT NAME FROM BRAND;
  END GET;
  
END BRAND_PKG;
/
-------------------------------------------------------------------------------
-- BRAND INSERTS
-------------------------------------------------------------------------------
EXEC BRAND_PKG.NEW('Isopure');
EXEC BRAND_PKG.NEW('Regal Spring');
EXEC BRAND_PKG.NEW('Food City');
EXEC BRAND_PKG.NEW('Mahatma');
EXEC BRAND_PKG.NEW('Healthy Harvest');
EXEC BRAND_PKG.NEW('Nature''s Own');
EXEC BRAND_PKG.NEW('Chobani');
EXEC BRAND_PKG.NEW('Quaker Oats');
EXEC BRAND_PKG.NEW('Fage');