CREATE TABLE MULTIPLIER
( ID INTEGER PRIMARY KEY
, NAME VARCHAR2(30)
, AMOUNT NUMBER (4,3)
);

CREATE SEQUENCE SEQ_MULTIPLIERID
START WITH 0
MINVALUE 0
INCREMENT BY 1
CACHE 20
NOCYCLE;

-------------------------------------------------------------------------------
-- MULTIPLIER PROCEDURES
-------------------------------------------------------------------------------
CREATE OR REPLACE PROCEDURE MULTIPLIER_INSERT
( PNAME IN MULTIPLIER.NAME%TYPE
, PAMT MULTIPLIER.AMOUNT%TYPE
)
IS
BEGIN
  INSERT INTO MULTIPLIER(ID, NAME, AMOUNT)
  VALUES(SEQ_MULTIPLIERID.NEXTVAL, PNAME, PAMT);
  COMMIT;
  
  EXCEPTION
    WHEN OTHERS THEN
      ROLLBACK;
      RAISE;
END;
/

CREATE OR REPLACE PROCEDURE MULTIPLIER_GETALL(PCURSOR OUT SYS_REFCURSOR)
IS
BEGIN
  OPEN PCURSOR FOR
    SELECT * FROM MULTIPLIER ORDER BY ID;
END;
/

CREATE OR REPLACE PROCEDURE MULTIPLIER_GET_BYID
( PID IN MULTIPLIER.ID%TYPE
, PCURSOR OUT SYS_REFCURSOR
)
IS
BEGIN
  OPEN PCURSOR FOR
    SELECT * 
    FROM MULTIPLIER
    WHERE ID=PID;
END;
/

CREATE OR REPLACE PROCEDURE MULTIPLIER_GET_BYNAME
( PNAME IN MULTIPLIER.NAME%TYPE
, PCURSOR OUT SYS_REFCURSOR
)
IS
BEGIN
  OPEN PCURSOR FOR
    SELECT * FROM MULTIPLIER WHERE UPPER(NAME)=UPPER(PNAME);
END;
/


-------------------------------------------------------------------------------
-- MULTIPLIER INITIAL DATA
-------------------------------------------------------------------------------
EXEC MULTIPLIER_INSERT('SELECT AN ACTIVITY MULTIPLIER', 0);
EXEC MULTIPLIER_INSERT('SEDENTARY', 1.2);
EXEC MULTIPLIER_INSERT('LIGHTLY ACTIVE', 1.375);
EXEC MULTIPLIER_INSERT('MODERATELY ACTIVE', 1.55);
EXEC MULTIPLIER_INSERT('VERY ACTIVE', 1.725);
EXEC MULTIPLIER_INSERT('EXTREMELY ACTIVE', 1.9);