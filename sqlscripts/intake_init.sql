DROP TABLE INTAKE;
CREATE TABLE INTAKE
( ID INTEGER
, ACCOUNT_ID INTEGER NOT NULL
, INTAKE_DATE DATE NOT NULL
, MEAL_ID INTEGER NOT NULL
, POSITION INTEGER NOT NULL
, CONSTRAINT PK_INTAKE PRIMARY KEY(ID)
, CONSTRAINT FK_INTAKE_ACTID FOREIGN KEY(ACCOUNT_ID) REFERENCES ACCOUNT(ID)
, CONSTRAINT FK_NTAKE_MEALID FOREIGN KEY(MEAL_ID) REFERENCES MEAL(ID)
);

CREATE SEQUENCE SEQ_INTAKE_ID
START WITH 0
MINVALUE 0
INCREMENT BY 1
CACHE 20
NOCYCLE;

CREATE OR REPLACE VIEW DAILY_INTAKE
AS
  SELECT ACCOUNT_ID, INTAKE_DATE, LISTAGG(MEAL_ID,',') WITHIN GROUP (ORDER BY POSITION) MEALS
  FROM INTAKE
  GROUP BY ACCOUNT_ID, INTAKE_DATE
WITH READ ONLY;

CREATE OR REPLACE PACKAGE INTAKE_PKG AS
  -- Returns all the meals for an account on a specified date.
  PROCEDURE GET( PACTID IN INTEGER, PDATE IN DATE, PCURSOR OUT SYS_REFCURSOR);
  
  -- Returns all meals for all days for an account
  PROCEDURE GET(ACTID IN INTAKE.ACCOUNT_ID%TYPE, PCURSOR OUT SYS_REFCURSOR);
  
  -- Creates a new intake for an account
  PROCEDURE NEW( PACTID IN INTAKE.ACCOUNT_ID%TYPE
               , PMEALID IN INTAKE.MEAL_ID%TYPE
               , PPOSITION IN INTEGER
               , PDATE IN DATE DEFAULT SYSDATE
               , PID IN INTEGER DEFAULT SEQ_INTAKE_ID.NEXTVAL);
  
  PROCEDURE UPDATEINTAKE( PID IN INTEGER
                        , PACTID IN INTEGER
                        , PDATE IN DATE
                        , PMEALID IN INTEGER
                        , PPOSITION IN INTEGER);
END INTAKE_PKG;
/

CREATE OR REPLACE PACKAGE BODY INTAKE_PKG AS
  PROCEDURE GET( PACTID IN INTEGER, PDATE IN DATE, PCURSOR OUT SYS_REFCURSOR)
  IS
  BEGIN
    OPEN PCURSOR FOR
      SELECT *
      FROM INTAKE
      WHERE ACCOUNT_ID=PACTID AND ROUND(INTAKE_DATE,'DDD')=ROUND(PDATE,'DDD');
  END GET;
  
  -- Returns all meals for all days for an account
  PROCEDURE GET(ACTID IN INTAKE.ACCOUNT_ID%TYPE, PCURSOR OUT SYS_REFCURSOR)
  IS
  BEGIN
    OPEN PCURSOR FOR
      SELECT *
      FROM INTAKE
      WHERE ACCOUNT_ID=ACTID;
  END GET;
  
  -- Creates a new intake for an account
  PROCEDURE NEW( PACTID IN INTAKE.ACCOUNT_ID%TYPE
               , PMEALID IN INTAKE.MEAL_ID%TYPE
               , PPOSITION IN INTEGER
               , PDATE IN DATE DEFAULT SYSDATE
               , PID IN INTEGER DEFAULT SEQ_INTAKE_ID.NEXTVAL)
  IS
  BEGIN
    INSERT INTO INTAKE(ID, INTAKE_DATE, ACCOUNT_ID, MEAL_ID, POSITION)
    VALUES(PID, ROUND(PDATE,'DDD'), PACTID, PMEALID, PPOSITION);
    COMMIT;
    
    EXCEPTION
      WHEN OTHERS THEN
        ROLLBACK;
        RAISE;
  END NEW;
  
  PROCEDURE UPDATEINTAKE( PID IN INTEGER
                        , PACTID IN INTEGER
                        , PDATE IN DATE
                        , PMEALID IN INTEGER
                        , PPOSITION IN INTEGER)
  IS
  BEGIN
    UPDATE INTAKE
    SET MEAL_ID=PMEALID, POSITION=PPOSITION
    WHERE ID=PID;
    COMMIT;
    
    EXCEPTION
      WHEN OTHERS THEN
        ROLLBACK;
        RAISE;
  END UPDATEINTAKE;
END INTAKE_PKG;
/

EXEC INTAKE_PKG.NEW(0, 0, 0);
EXEC INTAKE_PKG.NEW(0, 1, 1);

EXEC INTAKE_PKG.NEW(0, 1, 0, TO_DATE('23-FEB-14'));
EXEC INTAKE_PKG.NEW(0, 0, 1, TO_DATE('23-FEB-14'));

EXEC INTAKE_PKG.NEW(0, 0, 0, TO_DATE('24-FEB-14'));
EXEC INTAKE_PKG.NEW(0, 1, 1, TO_DATE('24-FEB-14'));