--------------------------------------------------------------------------------
-- CREATES ALL THE PACKAGE BODY FOR THE BETTERU USER
--
-- LAST MODIFIED: 28-FEB-14
--------------------------------------------------------------------------------

--------------------------------------------------------------------------------
-- TEMP ACCOUNT PACKAGE
--------------------------------------------------------------------------------
CREATE OR REPLACE PACKAGE BODY TEMP_ACCOUNT_PKG AS
  PROCEDURE NEW( PUSER IN TEMP_ACCOUNT.USERNAME%TYPE
               , PPASS IN TEMP_ACCOUNT.PASSWORD%TYPE
               , PCODE IN TEMP_ACCOUNT.CODE%TYPE
               , PID   IN TEMP_ACCOUNT.ID%TYPE DEFAULT SEQ_TEMP_ACCOUNT_ID.NEXTVAL
               )
  IS
    IUSER TEMP_ACCOUNT.USERNAME%TYPE;
  BEGIN
    INSERT INTO TEMP_ACCOUNT(ID, USERNAME, PASSWORD, CODE, CREATED)
    SELECT PID, PUSER, PPASS, PCODE, SYSDATE FROM DUAL
    WHERE NOT EXISTS(SELECT NULL FROM TEMP_ACCOUNT TA, ACCOUNT A WHERE TA.USERNAME=PUSER OR A.USERNAME=PUSER);
    COMMIT;
    
    EXCEPTION
      WHEN OTHERS THEN
        ROLLBACK;
        RAISE;
  END NEW;
    
  PROCEDURE NEWACCOUNT( PUSER IN TEMP_ACCOUNT.USERNAME%TYPE )
  IS
    IUSER TEMP_ACCOUNT.USERNAME%TYPE;
    IPASS TEMP_ACCOUNT.PASSWORD%TYPE;
  BEGIN
    SELECT USERNAME, PASSWORD INTO IUSER, IPASS FROM TEMP_ACCOUNT WHERE USERNAME=PUSER;
    ACCOUNT_PKG.CREATEFROMTEMP(IUSER, IPASS);
  END NEWACCOUNT;
  
  PROCEDURE DEL(PID IN TEMP_ACCOUNT.ID%TYPE)
  IS
  BEGIN
    DELETE FROM TEMP_ACCOUNT
    WHERE ID=PID;
    COMMIT;
    
    EXCEPTION
      WHEN OTHERS THEN
        ROLLBACK;
        RAISE;
  END DEL;
  
  PROCEDURE DEL(PUSER IN TEMP_ACCOUNT.USERNAME%TYPE)
  IS
  BEGIN
    DELETE FROM TEMP_ACCOUNT
    WHERE USERNAME=PUSER;
    COMMIT;
    
    EXCEPTION
      WHEN OTHERS THEN
      ROLLBACK;
      RAISE;
  END DEL;
  
  PROCEDURE GET(PCURSOR OUT SYS_REFCURSOR)
  IS
  BEGIN
    OPEN PCURSOR FOR
      SELECT *
      FROM TEMP_ACCOUNT;
  END GET;
  
  PROCEDURE GET(PID IN TEMP_ACCOUNT.ID%TYPE, PCURSOR OUT SYS_REFCURSOR)
  IS
  BEGIN
    OPEN PCURSOR FOR
      SELECT ID, USERNAME, PASSWORD, CODE
      FROM TEMP_ACCOUNT
      WHERE ID=PID;
  END GET;
  
END TEMP_ACCOUNT_PKG;
/

--------------------------------------------------------------------------------
-- ACCOUNT PACKAGE
--------------------------------------------------------------------------------
CREATE OR REPLACE PACKAGE BODY ACCOUNT_PKG AS
    
  PROCEDURE DELETE(PID IN INTEGER)
  IS
  BEGIN
    DELETE FROM ACCOUNT WHERE ID=PID;
    COMMIT;
  END DELETE;
  
  PROCEDURE NEW(PUSER IN ACCOUNT.USERNAME%TYPE, PPASS IN ACCOUNT.PASSWORD%TYPE, PID IN ACCOUNT.ID%TYPE DEFAULT SEQ_ACCOUNT_ID.NEXTVAL) IS
  BEGIN
    INSERT INTO ACCOUNT(ID, USERNAME, PASSWORD, BIRTHDAY, UNIT, GENDER, LAST_LOGIN, ACTIVE_SINCE)
    VALUES(PID, PUSER, PPASS, NULL, NULL, NULL, SYSDATE, SYSDATE);
    COMMIT;
    
    ACCOUNT_SETTING_PKG.NEW(PID,NULL,NULL,NULL);
    EXCEPTION
    WHEN OTHERS THEN
      ROLLBACK;
      RAISE;
  END NEW;
  
  PROCEDURE CREATEFROMTEMP( PUSER IN TEMP_ACCOUNT.USERNAME%TYPE
                          , PPASS IN TEMP_ACCOUNT.PASSWORD%TYPE
                          )
  IS
    IID INTEGER;
  BEGIN
    NEW(PUSER, PPASS);
    TEMP_ACCOUNT_PKG.DEL(PUSER);
    SELECT ID INTO IID FROM ACCOUNT WHERE USERNAME=PUSER AND PASSWORD=PPASS;
    ACCOUNT_SETTING_PKG.NEW(IID, NULL, NULL, NULL);
    COMMIT;
    
    EXCEPTION
      WHEN OTHERS THEN
        ROLLBACK;
        RAISE;
  END CREATEFROMTEMP;

  PROCEDURE GET(PCURSOR OUT SYS_REFCURSOR)
  IS
  BEGIN
    OPEN PCURSOR FOR
      SELECT *
      FROM ACCOUNT
      ORDER BY ID;
  END GET;
  
  PROCEDURE GET(PID IN ACCOUNT.ID%TYPE, PCURSOR OUT SYS_REFCURSOR) IS
  BEGIN
    OPEN PCURSOR FOR
      SELECT *
      FROM ACCOUNT
      WHERE ID=PID;
  END GET;
  
  PROCEDURE GET(PUSER IN ACCOUNT.USERNAME%TYPE, PCURSOR OUT SYS_REFCURSOR) IS
  BEGIN
    OPEN PCURSOR FOR
      SELECT *
      FROM ACCOUNT
      WHERE USERNAME=PUSER;
  END GET;
  
  PROCEDURE GETFROMVIEW(PID IN ACCOUNT.ID%TYPE, PCURSOR OUT SYS_REFCURSOR)
  IS
  BEGIN
    OPEN PCURSOR FOR
      SELECT *
      FROM ACCOUNT_VIEW
      WHERE ID=PID;
  END GETFROMVIEW;
  
  PROCEDURE UPDATELASTLOGIN(PID IN ACCOUNT.ID%TYPE) IS
  BEGIN
    UPDATE ACCOUNT
    SET LAST_LOGIN=SYSDATE
    WHERE ID=PID;
    COMMIT;
    
    EXCEPTION
      WHEN OTHERS THEN
        ROLLBACK;
        RAISE;
    END UPDATELASTLOGIN;
  
  PROCEDURE UPDATEACCOUNT( PID IN ACCOUNT.ID%TYPE
                         , PGENDER IN ACCOUNT.GENDER%TYPE
                         , PUNITSYSTEM IN ACCOUNT.UNIT%TYPE
                         , PBIRTHDAY IN ACCOUNT.BIRTHDAY%TYPE
                         ) IS
  BEGIN
    UPDATE ACCOUNT
    SET GENDER=UPPER(PGENDER),
        UNIT=UPPER(PUNITSYSTEM),
        BIRTHDAY=PBIRTHDAY
    WHERE ID=PID;
    COMMIT;
    
    EXCEPTION
      WHEN OTHERS THEN
        ROLLBACK;
        RAISE;
  END UPDATEACCOUNT;
END ACCOUNT_PKG;
/

--------------------------------------------------------------------------------
-- ACCOUNT SETTING PACKAGE
--------------------------------------------------------------------------------
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
    IWEEKDAY WEEKDAY.NAME%TYPE;
    IMULTIPLIER MULTIPLIER.NAME%TYPE;
  BEGIN
    IWEEKDAY := UPPER(PWEEKDAY);
    IMULTIPLIER:= UPPER(PMULT);
    ICHANGED := LATEST(PACCTID);
    IF ROUND(ICHANGED, 'DDD') = ROUND(SYSDATE, 'DDD') OR ICHANGED=NULL THEN
      UPDATE ACCOUNT_SETTING
      SET WEEKDAY=IWEEKDAY,
          MULTIPLIER=IMULTIPLIER,
          HEIGHT=PHEIGHT
      WHERE ACCOUNTID=PACCTID AND DATE_CHANGED=ICHANGED;
    ELSE
      INSERT INTO ACCOUNT_SETTING(ID, ACCOUNTID, WEEKDAY, MULTIPLIER, HEIGHT, DATE_CHANGED)
      VALUES(SEQ_ACCOUNT_SETTING_ID.NEXTVAL, PACCTID, IWEEKDAY, IMULTIPLIER, PHEIGHT, SYSDATE);
    END IF;
    COMMIT;
    
    EXCEPTION
      WHEN NO_DATA_FOUND THEN
        INSERT INTO ACCOUNT_SETTING(ID, ACCOUNTID, DATE_CHANGED)
        VALUES(SEQ_ACCOUNT_SETTING_ID.NEXTVAL, PACCTID, SYSDATE);
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


--------------------------------------------------------------------------------
-- INGREDIENT PACKAGE
--------------------------------------------------------------------------------
CREATE OR REPLACE PACKAGE BODY INGREDIENT_PKG AS
  PROCEDURE NEW( PNAME IN INGREDIENT.NAME%TYPE
                         , PBRAND IN INGREDIENT.BRAND%TYPE
                         , PCATG IN INGREDIENT.CATEGORY%TYPE
                         , PSERV_SIZE IN INGREDIENT.SERVING_SIZE%TYPE
                         , PSERV_UNIT IN INGREDIENT.SERVING_UNIT%TYPE
                         , PALT_SERV_SIZE IN INGREDIENT.ALT_SERVING_SIZE%TYPE
                         , PALT_SERV_UNIT IN INGREDIENT.ALT_SERVING_UNIT%TYPE
                         , PCAL IN INGREDIENT.CALORIES%TYPE
                         , PFAT IN INGREDIENT.FAT%TYPE
                         , PCARB IN INGREDIENT.CARBS%TYPE
                         , PPROT IN INGREDIENT.PROTEIN%TYPE
                         , PID IN INGREDIENT.ID%TYPE DEFAULT SEQ_INGREDIENT_ID.NEXTVAL
                         )
  IS
  BEGIN
    INSERT INTO INGREDIENT(ID, NAME, BRAND, CATEGORY, SERVING_UNIT, SERVING_SIZE, ALT_SERVING_UNIT, ALT_SERVING_SIZE, CALORIES, FAT, CARBS, PROTEIN)
    VALUES(PID, PNAME, PBRAND, PCATG, PSERV_UNIT, PSERV_SIZE, PALT_SERV_UNIT, PALT_SERV_SIZE, PCAL, PFAT, PCARB, PPROT);
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
      FROM INGREDIENT
      ORDER BY ID;
  END GET;
  
  PROCEDURE GET(PID IN INGREDIENT.ID%TYPE, PCURSOR OUT SYS_REFCURSOR)
  IS
  BEGIN
    OPEN PCURSOR FOR
      SELECT *
      FROM INGREDIENT
      WHERE ID=PID;
  END GET;
  
  PROCEDURE GET(PNAME IN INGREDIENT.NAME%TYPE, PCURSOR OUT SYS_REFCURSOR)
  IS
  BEGIN
    OPEN PCURSOR FOR
      SELECT *
      FROM INGREDIENT
      WHERE NAME=PNAME;
  END GET;
  
  PROCEDURE FILTER( PCATEGORY IN VARCHAR2
                  , PBRAND IN VARCHAR2
                  , PCURSOR OUT SYS_REFCURSOR
                  )
  IS
  BEGIN
    IF UPPER(PCATEGORY) != 'ALL' AND UPPER(PBRAND) != 'ALL' THEN
      OPEN PCURSOR FOR
        SELECT * FROM INGREDIENT WHERE UPPER(CATEGORY)=UPPER(PCATEGORY) AND UPPER(BRAND)=UPPER(PBRAND);
    ELSIF UPPER(PCATEGORY) != 'ALL' THEN
      OPEN PCURSOR FOR
        SELECT * FROM INGREDIENT WHERE UPPER(CATEGORY)=UPPER(PCATEGORY);
    ELSIF UPPER(PBRAND) != 'ALL' THEN
      OPEN PCURSOR FOR
        SELECT * FROM INGREDIENT WHERE UPPER(BRAND)=UPPER(PBRAND);
    ELSE
      OPEN PCURSOR FOR
        SELECT * FROM INGREDIENT;
    END IF;
  END FILTER;
  
END INGREDIENT_PKG;
/


--------------------------------------------------------------------------------
-- BRAND PACKAGE
--------------------------------------------------------------------------------
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
  
  PROCEDURE DELETE(PNAME IN BRAND.NAME%TYPE)
  IS
  BEGIN
    DELETE FROM BRAND
    WHERE NAME=PNAME;
    COMMIT;
    
    EXCEPTION
      WHEN OTHERS THEN
        ROLLBACK;
        RAISE;
  END DELETE;
END BRAND_PKG;
/

--------------------------------------------------------------------------------
-- CATEGORY PACKAGE
--------------------------------------------------------------------------------
CREATE OR REPLACE PACKAGE BODY CATEGORY_PKG AS
  PROCEDURE NEW(PNAME IN CATEGORY.NAME%TYPE)
  IS
  BEGIN
    INSERT INTO CATEGORY(NAME)
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
      FROM CATEGORY;
  END GET; 
  
END CATEGORY_PKG;
/

--------------------------------------------------------------------------------
-- FOOD_UNIT PACKAGE
--------------------------------------------------------------------------------
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

--------------------------------------------------------------------------------
-- FORMULA PACKAGE
--------------------------------------------------------------------------------
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

--------------------------------------------------------------------------------
-- GENDER PACKAGE
--------------------------------------------------------------------------------
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

--------------------------------------------------------------------------------
-- MEAL PACKAGE
--------------------------------------------------------------------------------
CREATE OR REPLACE PACKAGE BODY MEAL_PKG AS
  PROCEDURE NEW( PNAME IN MEAL.NAME%TYPE
               , PDESC IN MEAL.DESCRIPTION%TYPE
               , PID   IN MEAL.ID%TYPE DEFAULT SEQ_MEAL_ID.NEXTVAL
               )
  IS
  BEGIN
    INSERT INTO MEAL(ID, NAME, DESCRIPTION)
    VALUES(PID, PNAME, PDESC);
    COMMIT;
    
    EXCEPTION
      WHEN OTHERS THEN
        ROLLBACK;
        RAISE;
  END NEW;
  
  PROCEDURE GET( PID IN MEAL.ID%TYPE
               , PCURSOR OUT SYS_REFCURSOR
               )
  IS
  BEGIN
    OPEN PCURSOR FOR
      SELECT *
      FROM MEAL_VIEW
      WHERE ID=PID;
  END GET;
  
  PROCEDURE GET( PCURSOR OUT SYS_REFCURSOR )
  IS
  BEGIN
    OPEN PCURSOR FOR
      SELECT *
      FROM MEAL_VIEW
      ORDER BY ID;
  END GET;
  
END MEAL_PKG;
/

--------------------------------------------------------------------------------
-- MEAL_PART PACKAGE
--------------------------------------------------------------------------------
CREATE OR REPLACE PACKAGE BODY MEAL_PART_PKG AS
  PROCEDURE NEW( PMEALID IN MEAL_PART.MEALID%TYPE
               , PINGID  IN MEAL_PART.INGREDIENTID%TYPE
               , PAMOUNT IN MEAL_PART.AMOUNT%TYPE
               , PDEFAULT_UNIT IN MEAL_PART.DEFAULT_UNIT%TYPE
               , PID IN MEAL_PART.ID%TYPE DEFAULT SEQ_MEAL_PART_ID.NEXTVAL
               )
  IS
  BEGIN
    INSERT INTO MEAL_PART(ID, MEALID, INGREDIENTID, AMOUNT, DEFAULT_UNIT)
    VALUES(PID, PMEALID, PINGID, PAMOUNT, PDEFAULT_UNIT);
    COMMIT;
    
    EXCEPTION
      WHEN OTHERS THEN
        ROLLBACK;
        RAISE;
  END NEW;
  
  PROCEDURE GET( PCURSOR OUT SYS_REFCURSOR )
  IS
  BEGIN
    OPEN PCURSOR FOR
      SELECT *
      FROM MEAL_PART_VIEW
      ORDER BY ID;
  END GET;
  
  PROCEDURE FILTER( PID IN MEAL_PART.MEALID%TYPE, PCURSOR OUT SYS_REFCURSOR)
  IS
  BEGIN
    OPEN PCURSOR FOR
      SELECT *
      FROM MEAL_PART_VIEW
      WHERE MEALID=PID;
  END GET;
  
END MEAL_PART_PKG;
/

--------------------------------------------------------------------------------
-- MULTIPLIER PACKAGE
--------------------------------------------------------------------------------
CREATE OR REPLACE PACKAGE BODY MULTIPLIER_PKG AS
  PROCEDURE NEW(PNAME IN MULTIPLIER.NAME%TYPE, PAMT IN MULTIPLIER.AMOUNT%TYPE)
  IS
  BEGIN
    INSERT INTO MULTIPLIER(NAME, AMOUNT)
    VALUES(PNAME, PAMT);
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
      FROM MULTIPLIER;
  END GET; 
  
  PROCEDURE GET(PNAME IN MULTIPLIER.NAME%TYPE
               , PCURSOR OUT SYS_REFCURSOR
               )
  IS
  BEGIN
    OPEN  PCURSOR FOR
      SELECT NAME, AMOUNT
      FROM MULTIPLIER 
      WHERE UPPER(NAME)=UPPER(PNAME);
  END GET;
  
END MULTIPLIER_PKG;
/

--------------------------------------------------------------------------------
-- UNIT_SYSTEM PACKAGE
--------------------------------------------------------------------------------
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


--------------------------------------------------------------------------------
-- WEEKDAY PACKAGE
--------------------------------------------------------------------------------
CREATE OR REPLACE PACKAGE BODY WEEKDAY_PKG AS
  PROCEDURE NEW(PNAME IN WEEKDAY.NAME%TYPE)
  IS
    INUM INTEGER;
  BEGIN
    SELECT COUNT(NAME) INTO INUM FROM WEEKDAY;
    INSERT INTO WEEKDAY(NAME, DAY_OF_WEEK)
    VALUES(PNAME, INUM);
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
      FROM WEEKDAY;
  END GET;
  
  PROCEDURE GET(PNAME IN WEEKDAY.NAME%TYPE, PCURSOR OUT SYS_REFCURSOR)
  IS
  BEGIN
    OPEN PCURSOR FOR
      SELECT *
      FROM WEEKDAY
      WHERE NAME=PNAME;
  END GET;
  
  PROCEDURE GET(PDOW IN WEEKDAY.DAY_OF_WEEK%TYPE, PCURSOR OUT SYS_REFCURSOR)
  IS
  BEGIN
    OPEN PCURSOR FOR
      SELECT *
      FROM WEEKDAY
      WHERE DAY_OF_WEEK=PDOW;
  END GET;
  
END WEEKDAY_PKG;
/

--------------------------------------------------------------------------------
-- WEIGHT PACKAGE
--------------------------------------------------------------------------------
CREATE OR REPLACE PACKAGE BODY WEIGHT_PKG AS
  -- Returns an person's weight for an account id and date given
  PROCEDURE GET(PID IN WEIGHT.ACCOUNT_ID%TYPE, PDATE IN WEIGHT.WEIGHT_DATE%TYPE, PCURSOR OUT SYS_REFCURSOR)
  IS
  BEGIN
    OPEN PCURSOR FOR
      SELECT *
      FROM WEIGHT
      WHERE ACCOUNT_ID=PID AND ROUND(WEIGHT_DATE,'DDD')=ROUND(PDATE,'DDD')
      ORDER BY WEIGHT_DATE;
  END GET;
  
  -- Returns all records for a person's weight for an account id;
  PROCEDURE GET(PID IN WEIGHT.ACCOUNT_ID%TYPE, PCURSOR OUT SYS_REFCURSOR)
  IS
  BEGIN
    OPEN PCURSOR FOR
      SELECT *
      FROM WEIGHT
      WHERE ACCOUNT_ID=PID
      ORDER BY WEIGHT_DATE;
  END GET;
  
  -- Creates a new weight. The default weight_date is sysdate
  PROCEDURE NEW( PACTID IN WEIGHT.ACCOUNT_ID%TYPE
               , PWEIGHT IN WEIGHT.WEIGHT%TYPE
               , PCAL IN WEIGHT.CALORIE_CHANGE%TYPE
               , PFAT IN WEIGHT.FAT_MULTIPLIER%TYPE
               , PCARB IN WEIGHT.CARB_MULTIPLIER%TYPE
               , PPROT IN WEIGHT.PROTEIN_MULTIPLIER%TYPE DEFAULT 1
               , PDATE IN WEIGHT.WEIGHT_DATE%TYPE DEFAULT SYSDATE
               )
  IS
  BEGIN
    INSERT INTO WEIGHT(WEIGHT_DATE,ACCOUNT_ID,WEIGHT,CALORIE_CHANGE,FAT_MULTIPLIER,CARB_MULTIPLIER,PROTEIN_MULTIPLIER)
    VALUES(PDATE,PACTID,PWEIGHT,PCAL,PFAT,PCARB,PPROT);
    COMMIT;
    
    EXCEPTION
      WHEN OTHERS THEN
        ROLLBACK;
        RAISE;
  END NEW;
  
END WEIGHT_PKG;
/


--------------------------------------------------------------------------------
-- CALORIE_SPLIT PACKAGE
--------------------------------------------------------------------------------
CREATE OR REPLACE PACKAGE BODY CALORIE_SPLIT_PKG AS
  FUNCTION FORABBR(PDEC IN DECIMAL, PDEC2 IN DECIMAL) RETURN VARCHAR2 DETERMINISTIC
  IS
    IDEC INTEGER;
    IDEC2 INTEGER;
    ISTR VARCHAR(20);
  BEGIN
    IDEC := ROUND(100-100*PDEC)*-1;
    IDEC2 := ROUND(100-100*PDEC2)*-1;
    ISTR := '(';
    IF IDEC > 0 THEN
      ISTR := ISTR||'+'||TO_CHAR(IDEC);
    ELSE
      ISTR := ISTR||TO_CHAR(IDEC);
    END IF;
    ISTR := ISTR||', ';
    IF IDEC2 > 0 THEN
      ISTR := ISTR||'+'||TO_CHAR(IDEC2);
    ELSE
      ISTR := ISTR||TO_CHAR(IDEC2);
    END IF;
    RETURN ISTR||')';
  END FORABBR;

  PROCEDURE GET(PID IN CALORIE_SPLIT.ID%TYPE, PCURSOR OUT SYS_REFCURSOR)
  IS
  BEGIN
    OPEN PCURSOR FOR
      SELECT *
      FROM CALORIE_SPLIT
      WHERE ID=PID;
  END GET;
  
  PROCEDURE GET(PCURSOR OUT SYS_REFCURSOR)
  IS
  BEGIN
    OPEN PCURSOR FOR
      SELECT *
      FROM CALORIE_SPLIT;
  END GET;
  
  PROCEDURE NEW( PNAME IN CALORIE_SPLIT.NAME%TYPE
               , PWORKOUT IN CALORIE_SPLIT.WORKOUT_MULTIPLIER%TYPE
               , PREST IN CALORIE_SPLIT.REST_MULTIPLIER%TYPE
               , PID IN CALORIE_SPLIT.ID%TYPE DEFAULT SEQ_CALORIE_SPLIT_ID.NEXTVAL
               )
  IS
  BEGIN
    INSERT INTO CALORIE_SPLIT(ID,NAME,WORKOUT_MULTIPLIER,REST_MULTIPLIER)
    VALUES(PID,UPPER(PNAME),PWORKOUT,PREST);
    COMMIT;
    
    EXCEPTION
      WHEN OTHERS THEN
        ROLLBACK;
        RAISE;
  END NEW;
END CALORIE_SPLIT_PKG;
/


