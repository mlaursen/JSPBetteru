CREATE TABLE ACCOUNT_SETTING
( ID INTEGER
, ACCOUNTID INTEGER
, WEEKDAY VARCHAR2(22)
, MULTIPLIER VARCHAR2(30)
, HEIGHT NUMBER(5,1)
, DATE_CHANGED DATE
, CONSTRAINT PK_ACCOUNT_SETTING_ID PRIMARY KEY (ID)
, CONSTRAINT FK_ACCOUNTID FOREIGN KEY(ACCOUNTID) REFERENCES ACCOUNT(ID)
, CONSTRAINT FK_RECALCID FOREIGN KEY(WEEKDAY) REFERENCES WEEKDAY(NAME)
, CONSTRAINT FK_MULTIPLIERID FOREIGN KEY(MULTIPLIER) REFERENCES MULTIPLIER(NAME)
);

CREATE SEQUENCE SEQ_ACCOUNT_SETTINGID
START WITH 0
MINVALUE 0
INCREMENT BY 1
CACHE 20
NOCYCLE;

-------------------------------------------------------------------------------
-- ACCOUNT_SETTING PROCEDURES
-------------------------------------------------------------------------------
CREATE OR REPLACE PACKAGE ACCOUNT_SETTING_PKG AS
  -- Returns the last change date for an account's settings by account id
  FUNCTION LATEST(PACCTID IN ACCOUNT_SETTING.ACCOUNTID%TYPE) RETURN DATE;
  
  -- Creates a new account setting
  PROCEDURE NEW( PACCTID IN ACCOUNT_SETTING.ACCOUNTID%TYPE
               , PWEEKDAY IN ACCOUNT_SETTING.WEEKDAY%TYPE
               , PMULT IN ACCOUNT_SETTING.MULTIPLIER%TYPE
               , PHEIGHT IN ACCOUNT_SETTING.HEIGHT%TYPE
               );
  
  -- Gets all columns and all rows from the latest account settings
  PROCEDURE GET(PCURSOR OUT SYS_REFCURSOR);
  
  -- Gets all columns from the latest account setting for account id
  PROCEDURE GET(PID IN ACCOUNT_SETTING.ACCOUNTID%TYPE, PCURSOR OUT SYS_REFCURSOR);
  
END ACCOUNT_SETTING_PKG;
/

CREATE OR REPLACE PACKAGE BODY ACCOUNT_SETTING_PKG AS
  FUNCTION LATEST(PACCTID IN ACCOUNT_SETTING.ACCOUNTID%TYPE) RETURN DATE
  IS
    ILATEST DATE;
  BEGIN
    SELECT MAX(DATE_CHANGED) INTO ILATEST
    FROM ACCOUNT_SETTING
    WHERE ACCOUNTID=PACCTID
    GROUP BY ACCOUNTID;
    RETURN ILATEST;
  END LATEST;
  
  
  PROCEDURE NEW( PACCTID IN ACCOUNT_SETTING.ACCOUNTID%TYPE
               , PWEEKDAY IN ACCOUNT_SETTING.WEEKDAY%TYPE
               , PMULT IN ACCOUNT_SETTING.MULTIPLIER%TYPE
               , PHEIGHT IN ACCOUNT_SETTING.HEIGHT%TYPE
               )
  IS
    ICHANGED DATE;
  BEGIN
    ICHANGED := LATEST(PACCTID);
    IF ROUND(ICHANGED, 'DDD') = ROUND(SYSDATE, 'DDD') THEN
      UPDATE ACCOUNT_SETTING
      SET WEEKDAY=PWEEKDAY,
          MULTIPLIER=PMULT,
          HEIGHT=PHEIGHT
      WHERE ACCOUNTID=PACCTID AND DATE_CHANGED=ICHANGED;
    ELSE
      INSERT INTO ACCOUNT_SETTING(ID, ACCOUNTID, WEEKDAY, MULTIPLIER, HEIGHT, DATE_CHANGED)
      VALUES(SEQ_ACCOUNT_SETTINGID.NEXTVAL, PACCTID, PWEEKDAY, PMULT, PHEIGHT, SYSDATE);
    END IF;
    COMMIT;
    
    EXCEPTION
      WHEN NO_DATA_FOUND THEN
        INSERT INTO ACCOUNT_SETTING(ID, ACCOUNTID)
        VALUES(SEQ_ACCOUNT_SETTINGID.NEXTVAL, PACCTID);
        COMMIT;
      WHEN OTHERS THEN
        ROLLBACK;
        RAISE;
  END NEW;
  
  PROCEDURE GET(PID IN ACCOUNT_SETTING.ACCOUNTID%TYPE, PCURSOR OUT SYS_REFCURSOR)
  IS
  BEGIN
    OPEN PCURSOR FOR
      SELECT *
      FROM ACCOUNT_SETTING
      WHERE ACCOUNTID=PID AND DATE_CHANGED=LATEST(ACCOUNTID);
  END GET;
  
  PROCEDURE GET(PCURSOR OUT SYS_REFCURSOR)
  IS
  BEGIN
    OPEN PCURSOR FOR
      SELECT *
      FROM ACCOUNT_SETTING
      WHERE DATE_CHANGED=LATEST(ACCOUNTID)
      GROUP BY ACCOUNTID;
  END GET;
  
END ACCOUNT_SETTING_PKG;
/