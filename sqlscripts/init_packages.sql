--------------------------------------------------------------------------------
-- CREATES ALL THE PACKAGES FOR THE BETTERU USER
--
-- LAST MODIFIED: 28-FEB-14
--------------------------------------------------------------------------------

--------------------------------------------------------------------------------
-- TEMP ACCOUNT PACKAGE
--------------------------------------------------------------------------------
CREATE OR REPLACE PACKAGE TEMP_ACCOUNT_PKG AS
  -- creates a new temp account
  PROCEDURE NEW( PUSER IN TEMP_ACCOUNT.USERNAME%TYPE
               , PPASS IN TEMP_ACCOUNT.PASSWORD%TYPE
               , PCODE IN TEMP_ACCOUNT.CODE%TYPE
               , PID   IN TEMP_ACCOUNT.ID%TYPE DEFAULT SEQ_TEMP_ACCOUNT_ID.NEXTVAL
               );
  
  -- creates a new account from a temp account's id
  PROCEDURE NEWACCOUNT( PID IN TEMP_ACCOUNT.ID%TYPE );
  
  -- Deletes a temp account by id
  PROCEDURE DEL(PID IN TEMP_ACCOUNT.ID%TYPE);
  
  -- Returns a sys_refcursor for all temp accounts
  PROCEDURE GET(PCURSOR OUT SYS_REFCURSOR);
  
  -- Returns a sys_refcursor for a temp account by tempaccountid
  PROCEDURE GET(PID IN TEMP_ACCOUNT.ID%TYPE, PCURSOR OUT SYS_REFCURSOR);
  
  -- Returns a sys_refcursor for a temp account by username/password
  PROCEDURE GETID(PUSER IN TEMP_ACCOUNT.USERNAME%TYPE
                 , PCURSOR OUT SYS_REFCURSOR
                 );
  
  
END TEMP_ACCOUNT_PKG;
/

--------------------------------------------------------------------------------
-- ACCOUNT PACKAGE
--------------------------------------------------------------------------------
CREATE OR REPLACE PACKAGE ACCOUNT_PKG AS
  -- Gets the age for an account from the birthday
  FUNCTION GETAGE(PBIRTHDAY IN ACCOUNT.BIRTHDAY%TYPE) RETURN INTEGER;
  
  -- Creates a new account
  PROCEDURE NEW( PUSER IN ACCOUNT.USERNAME%TYPE
               , PPASS IN ACCOUNT.PASSWORD%TYPE
               , PID IN ACCOUNT.ID%TYPE DEFAULT SEQ_ACCOUNT_ID.NEXTVAL
               );
  
  -- Creates a new account from a temp account
  PROCEDURE CREATEFROMTEMP(PTEMPID IN TEMP_ACCOUNT.ID%TYPE);
  
  -- Retrieves a SYS_REFCURSOR for all the accounts
  PROCEDURE GET(PCURSOR OUT SYS_REFCURSOR);
  
  -- Retrieves a SYS_REFCURSOR for an account by account id
  PROCEDURE GET( PID IN ACCOUNT.ID%TYPE
               , PCURSOR OUT SYS_REFCURSOR
               );
  
  -- Retrives a SYS_REFCURSOR for an account (ONLY)password by username
  PROCEDURE GET( PUSER IN ACCOUNT.USERNAME%TYPE
               , PCURSOR OUT SYS_REFCURSOR
               );
  
  -- Deletes an account by account id
  PROCEDURE DELETE(PID IN INTEGER);
  
  -- Retrieves a SYS_REFCURSOR for an account and account_setting
  PROCEDURE GETFROMVIEW( PID IN ACCOUNT.ID%TYPE, PCURSOR OUT SYS_REFCURSOR);
  
  -- Updates the last login to the time of login (SYSDATE) by account id
  PROCEDURE UPDATELASTLOGIN(PID IN ACCOUNT.ID%TYPE);
  
  -- Updates the account details for an account id
  PROCEDURE UPDATEACCOUNT( PID IN ACCOUNT.ID%TYPE
                         , PGENDER IN ACCOUNT.GENDER%TYPE
                         , PUNITSYSTEM IN ACCOUNT.UNIT%TYPE
                         , PBIRTHDAY IN ACCOUNT.BIRTHDAY%TYPE
                         );
  
END ACCOUNT_PKG;
/

--------------------------------------------------------------------------------
-- ACCOUNT SETTING PACKAGE
--------------------------------------------------------------------------------
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


--------------------------------------------------------------------------------
-- INGREDIENT PACKAGE
--------------------------------------------------------------------------------
CREATE OR REPLACE PACKAGE INGREDIENT_PKG AS
  -- Creates a new ingredient
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
               );
  
  -- Returns a SYS_REFCURSOR for all the ingredients from with foreign key
  -- 'prettified'. << Brings names
  PROCEDURE GET(PCURSOR OUT SYS_REFCURSOR);
  
  -- Returns a SYS_REFCURSOR for an ingredient by ingredient id
  PROCEDURE GET(PID IN INGREDIENT.ID%TYPE, PCURSOR OUT SYS_REFCURSOR);
  
  -- Returns a SYS_REFCURSOR for an ingredient by ingredient name
  PROCEDURE GET(PNAME IN INGREDIENT.NAME%TYPE, PCURSOR OUT SYS_REFCURSOR);
  
  -- Returns a filtered SYS_REFCURSOR for all ingredients that 
  -- math the category and brand constraints.
  PROCEDURE FILTER( PCATEGORY IN VARCHAR2
                  , PBRAND IN VARCHAR2
                  , PCURSOR OUT SYS_REFCURSOR
                  );
  
END INGREDIENT_PKG;
/


--------------------------------------------------------------------------------
-- BRAND PACKAGE
--------------------------------------------------------------------------------
CREATE OR REPLACE PACKAGE BRAND_PKG AS
  -- Creates a new brand
  PROCEDURE NEW(PNAME IN BRAND.NAME%TYPE);
  
  -- Returns a SYS_REFCURSOR for all the brands
  PROCEDURE GET(PCURSOR OUT SYS_REFCURSOR);
  
  -- Deletes a brand by name
  PROCEDURE DELETE(PNAME IN BRAND.NAME%TYPE);
END BRAND_PKG;
/

--------------------------------------------------------------------------------
-- CATEGORY PACKAGE
--------------------------------------------------------------------------------
CREATE OR REPLACE PACKAGE CATEGORY_PKG AS
  -- Creates a new category
  PROCEDURE NEW(PNAME IN CATEGORY.NAME%TYPE);
  
  -- Returns a SYS_REFCURSOR for all categories
  PROCEDURE GET(PCURSOR OUT SYS_REFCURSOR);
  
END CATEGORY_PKG;
/

--------------------------------------------------------------------------------
-- FOOD_UNIT PACKAGE
--------------------------------------------------------------------------------
CREATE OR REPLACE PACKAGE FOOD_UNIT_PKG AS
  -- Creates a new FOOD_UNIT
  PROCEDURE NEW(PNAME IN FOOD_UNIT.NAME%TYPE, PLONG IN FOOD_UNIT.LONG_NAME%TYPE);
  
  -- Returns a SYS_REFCURSOR for all FOOD_UNITs
  PROCEDURE GET(PCURSOR OUT SYS_REFCURSOR);
  
  -- Returns a SYS_REFCURSOR for a single food unit
  PROCEDURE GET(PNAME IN FOOD_UNIT.NAME%TYPE, PCURSOR OUT SYS_REFCURSOR);
END FOOD_UNIT_PKG;
/

--------------------------------------------------------------------------------
-- FORMULA PACKAGE
--------------------------------------------------------------------------------
CREATE OR REPLACE PACKAGE FORMULA_PKG AS
  -- Creates a new formula
  PROCEDURE NEW( PNAME IN FORMULA.NAME%TYPE
               , PUNIT IN FORMULA.UNIT_SYSTEM%TYPE DEFAULT 'IMPERIAL');
  
  -- Returns a single formula by name
  PROCEDURE GET(PNAME IN FORMULA.NAME%TYPE, PCURSOR OUT SYS_REFCURSOR);
  
  -- Returns all formulas
  PROCEDURE GET(PCURSOR OUT SYS_REFCURSOR);
  
END FORMULA_PKG;
/

--------------------------------------------------------------------------------
-- GENDER PACKAGE
--------------------------------------------------------------------------------
CREATE OR REPLACE PACKAGE GENDER_PKG AS
  -- Creates a new Gender
  PROCEDURE NEW(PNAME IN GENDER.NAME%TYPE);
  
  -- Returns a SYS_REFCURSOR for all genders
  PROCEDURE GET(PCURSOR OUT SYS_REFCURSOR);
  
END GENDER_PKG;
/

--------------------------------------------------------------------------------
-- MEAL PACKAGE
--------------------------------------------------------------------------------
CREATE OR REPLACE PACKAGE MEAL_PKG AS
  -- Creates a new meal
  PROCEDURE NEW( PNAME IN MEAL.NAME%TYPE
               , PDESC IN MEAL.DESCRIPTION%TYPE
               , PID   IN MEAL.ID%TYPE DEFAULT SEQ_MEAL_ID.NEXTVAL
               );
  
  -- Returns a SYS_REFCURSOR for all meals
  PROCEDURE GET(PCURSOR OUT SYS_REFCURSOR);
  
  -- Returns a SYS_REFCURSOR for the meal by id
  PROCEDURE GET( PID IN MEAL.ID%TYPE
               , PCURSOR OUT SYS_REFCURSOR
               );
  
  
END MEAL_PKG;
/



--------------------------------------------------------------------------------
-- MEAL_PART PACKAGE
--------------------------------------------------------------------------------
CREATE OR REPLACE PACKAGE MEAL_PART_PKG AS
  -- Creates a new Meal Part
  PROCEDURE NEW( PMEALID IN MEAL_PART.MEALID%TYPE
               , PINGID  IN MEAL_PART.INGREDIENTID%TYPE
               , PAMOUNT IN MEAL_PART.AMOUNT%TYPE
               , PDEFAULT_UNIT IN MEAL_PART.DEFAULT_UNIT%TYPE
               , PID IN MEAL_PART.ID%TYPE DEFAULT SEQ_MEAL_PART_ID.NEXTVAL
               );
               
  -- Returns all meal parts
  PROCEDURE GET( PCURSOR OUT SYS_REFCURSOR );
  
  -- Returns a SYS_REFCURSOR for all meal parts for a given meal id
  PROCEDURE GET( PID IN MEAL_PART.MEALID%TYPE, PCURSOR OUT SYS_REFCURSOR);
END MEAL_PART_PKG;
/

--------------------------------------------------------------------------------
-- MULTIPLIER PACKAGE
--------------------------------------------------------------------------------
CREATE OR REPLACE PACKAGE MULTIPLIER_PKG AS
  -- Creates a new multiplier
  PROCEDURE NEW(PNAME IN MULTIPLIER.NAME%TYPE, PAMT IN MULTIPLIER.AMOUNT%TYPE);
  
  -- Returns a SYS_REFCURSOR for all multipliers
  PROCEDURE GET(PCURSOR OUT SYS_REFCURSOR);
  
  -- Returns a SYS_REFCURSOR for the multiplier amount for multiplier name
  PROCEDURE GET( PNAME IN MULTIPLIER.NAME%TYPE
               , PCURSOR OUT SYS_REFCURSOR
               );
  
END MULTIPLIER_PKG;
/

--------------------------------------------------------------------------------
-- UNIT_SYSTEM PACKAGE
--------------------------------------------------------------------------------
CREATE OR REPLACE PACKAGE UNIT_SYSTEM_PKG AS
  
  -- Returns a SYS_REFCURSOR for all UNIT_SYSTEMs
  PROCEDURE GET(PCURSOR OUT SYS_REFCURSOR);
  
END UNIT_SYSTEM_PKG;
/

--------------------------------------------------------------------------------
-- WEEKDAY PACKAGE
--------------------------------------------------------------------------------
CREATE OR REPLACE PACKAGE WEEKDAY_PKG AS
  -- Creates a new WEEKDAY
  PROCEDURE NEW(PNAME IN WEEKDAY.NAME%TYPE);
  
  -- Returns a SYS_REFCURSOR for all WEEKDAYs
  PROCEDURE GET(PCURSOR OUT SYS_REFCURSOR);
  
  -- Returns a SYS_REFCURSOR for a weekday by name
  PROCEDURE GET(Pname IN WEEKDAY.name%TYPE, PCURSOR OUT SYS_REFCURSOR);
  
  --Returns a SYS_REFCURSOR for a weekday by day of week number
  PROCEDURE GET(PDOW IN WEEKDAY.DAY_OF_WEEK%TYPE, PCURSOR OUT SYS_REFCURSOR);
END WEEKDAY_PKG;
/


--------------------------------------------------------------------------------
-- WEIGHT PACKAGE
--------------------------------------------------------------------------------
CREATE OR REPLACE PACKAGE WEIGHT_PKG AS
  -- Returns an person's weight for an account id and date given
  PROCEDURE GET( PID IN WEIGHT.ACCOUNT_ID%TYPE
               , PDATE IN WEIGHT.WEIGHT_DATE%TYPE
               , PCURSOR OUT SYS_REFCURSOR);
  
  -- Returns all records for a person's weight for an account id;
  PROCEDURE GET(PID IN WEIGHT.ACCOUNT_ID%TYPE, PCURSOR OUT SYS_REFCURSOR);
  
  -- Creates a new weight. The default weight_date is sysdate
  PROCEDURE NEW( PACTID IN WEIGHT.ACCOUNT_ID%TYPE
               , PWEIGHT IN WEIGHT.WEIGHT%TYPE
               , PCAL IN WEIGHT.CALORIE_CHANGE%TYPE
               , PFAT IN WEIGHT.FAT_MULTIPLIER%TYPE
               , PCARB IN WEIGHT.CARB_MULTIPLIER%TYPE
               , PPROT IN WEIGHT.PROTEIN_MULTIPLIER%TYPE DEFAULT 1
               , PDATE IN WEIGHT.WEIGHT_DATE%TYPE DEFAULT SYSDATE
               );
  
END WEIGHT_PKG;
/

--------------------------------------------------------------------------------
-- CALORIE_SPLIT PACKAGE
--------------------------------------------------------------------------------
CREATE OR REPLACE PACKAGE CALORIE_SPLIT_PKG AS
  -- Function for generating an abbreviation for a calorie split.
  -- It returns a string of '(+x, -y)'
  FUNCTION FORABBR(PDEC IN DECIMAL, PDEC2 IN DECIMAL) RETURN VARCHAR2 DETERMINISTIC;
  
  -- Returns a single calorie split by id
  PROCEDURE GET(PID IN CALORIE_SPLIT.ID%TYPE, PCURSOR OUT SYS_REFCURSOR);
  
  -- Returns all the calorie splits
  PROCEDURE GET(PCURSOR OUT SYS_REFCURSOR);
  
  -- Creates a new Calorie Split
  PROCEDURE NEW( PNAME IN CALORIE_SPLIT.NAME%TYPE
               , PWORKOUT IN CALORIE_SPLIT.WORKOUT_MULTIPLIER%TYPE
               , PREST IN CALORIE_SPLIT.REST_MULTIPLIER%TYPE
               , PID IN CALORIE_SPLIT.ID%TYPE DEFAULT SEQ_CALORIE_SPLIT_ID.NEXTVAL
               );

END CALORIE_SPLIT_PKG;
/
