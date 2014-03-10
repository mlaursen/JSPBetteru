--------------------------------------------------------------------------------
-- CREATES ALL THE VIEWS FOR THE BETTERU USER
--
-- LAST MODIFIED: 28-FEB-14
--------------------------------------------------------------------------------

CREATE OR REPLACE VIEW ACCOUNT_VIEW AS
  SELECT ACT.ID, 
         USERNAME, 
         BIRTHDAY, 
         GENDER, 
         UNIT, 
         HEIGHT, 
         M.NAME MULTIPLIER,
         M.AMOUNT MULTIPLIER_AMOUNT, 
         WEEKDAY, 
         GETAGE(BIRTHDAY) AGE, 
         ACTIVE_SINCE
  FROM ACCOUNT ACT
  JOIN ACCOUNT_SETTING ACTS ON ACT.ID = ACTS.ACCOUNTID
  LEFT JOIN MULTIPLIER M ON M.NAME=MULTIPLIER
  WHERE ACCOUNT_SETTING_PKG.LATEST(ACT.ID)=DATE_CHANGED
WITH READ ONLY;


CREATE OR REPLACE VIEW ACCOUNT_WEIGHT_VIEW AS
  SELECT AV.ID, 
         USERNAME, 
         WEIGHT, 
         WEIGHT_DATE, 
         MULTIPLIER_AMOUNT AMOUNT, 
         WEEKDAY, 
         AGE, 
         HEIGHT, 
         UNIT, 
         GENDER, 
         ACTIVE_SINCE
  FROM ACCOUNT_VIEW AV
  LEFT OUTER JOIN WEIGHT W ON AV.ID = W.ACCOUNT_ID
  ORDER BY ID, WEIGHT_DATE;
WITH READ ONLY;



CREATE OR REPLACE VIEW MEAL_PART_VIEW AS
  SELECT ID, 
         MEALID, 
         AMOUNT, 
         INGREDIENT_ID, 
         INGREDIENT, 
         BRAND, 
         CATEGORY, 
         SERVING_SIZE, 
         SERVING_UNIT, 
         (CALORIES*RATIO) TOTAL_CALORIES, 
         (FAT*RATIO) TOTAL_FAT, 
         (CARBS*RATIO) TOTAL_CARBS, 
         (PROTEIN*RATIO) TOTAL_PROTEIN
  FROM (SELECT ROWNUM ID, 
               MEALID, 
               AMOUNT, 
               DEFAULT_UNIT, 
               I.ID INGREDIENT_ID, 
               I.NAME INGREDIENT, 
               BRAND, 
               CATEGORY, 
               CALORIES, 
               FAT, 
               CARBS, 
               PROTEIN, 
               AMOUNT / DECODE(DEFAULT_UNIT, 0, SERVING_SIZE, ALT_SERVING_SIZE) RATIO,
               DECODE(DEFAULT_UNIT, 0, SERVING_SIZE, ALT_SERVING_SIZE) SERVING_SIZE,
               DECODE(DEFAULT_UNIT, 0, SERVING_UNIT, ALT_SERVING_UNIT) SERVING_UNIT
        FROM MEAL_PART MP INNER JOIN INGREDIENT i ON MP.INGREDIENTID = I.ID)
  ORDER BY ID
WITH READ ONLY;

CREATE OR REPLACE VIEW MEAL_VIEW AS
  SELECT ID, 
         NAME, 
         DESCRIPTION, 
         TOTAL_CALORIES, 
         TOTAL_FAT, 
         TOTAL_CARBS, 
         TOTAL_PROTEIN
  FROM (SELECT MEALID, 
               ROUND(SUM(TOTAL_CALORIES),2) TOTAL_CALORIES, 
               ROUND(SUM(TOTAL_FAT),2) TOTAL_FAT, 
               ROUND(SUM(TOTAL_CARBS),2) TOTAL_CARBS, 
               ROUND(SUM(TOTAL_PROTEIN),2) TOTAL_PROTEIN
        FROM MEAL_PART_VIEW
        GROUP BY MEALID) MPV
  JOIN MEAL M
  ON M.ID = MPV.MEALID
WITH READ ONLY;



CREATE OR REPLACE VIEW MIFFLIN_ST_JOER AS
  SELECT ID ACCOUNT_ID, 
         ROUND(AMOUNT*(WEIGHT+HEIGHT-AGE+EXTRA),2) TDEE,
         WEIGHT_DATE
  FROM (SELECT ID,
               10*WEIGHT/DECODE(UNIT,'IMPERIAL',2.2,1) WEIGHT,
               6.25*HEIGHT*DECODE(UNIT,'IMPERIAL',2.54,1) HEIGHT,
               AGE*5 AGE,
               DECODE(GENDER,'MALE',5,-161) EXTRA, AMOUNT, WEIGHT_DATE
        FROM ACCOUNT_WEIGHT_VIEW)
WITH READ ONLY;

CREATE OR REPLACE VIEW HARRIS_BENEDICT AS
  SELECT ID ACCOUNT_ID,
         ROUND(AMOUNT*(EXTRA+WEIGHT+HEIGHT-AGE),2) TDEE, 
         WEIGHT_DATE
  FROM (SELECT ID,
               WEIGHT*DECODE(GENDER,'MALE',6.23,4.35) WEIGHT,
               HEIGHT*DECODE(GENDER,'MALE',12.7,4.7) HEIGHT,
               AGE*DECODE(GENDER,'MALE',6.67,4.7) AGE,
               DECODE(GENDER,'MALE',66,655) EXTRA,
               AMOUNT, 
               WEIGHT_DATE
        FROM (SELECT ID, 
                     WEIGHT*DECODE(UNIT,'METRIC',2.2,1) WEIGHT, 
                     HEIGHT/DECODE(UNIT,'METRIC',2.54,1) HEIGHT, 
                     AGE, 
                     AMOUNT, 
                     GENDER, 
                     WEIGHT_DATE
              FROM ACCOUNT_WEIGHT_VIEW))
WITH READ ONLY;

CREATE OR REPLACE VIEW DAILY_INTAKE_VIEW AS
  SELECT DI.ID,
         DI.ACCOUNT_ID,
         DI.INTAKE_DATE,
         LISTAGG(MEAL_ID,',') WITHIN GROUP (ORDER BY DI.INTAKE_DATE) MEAL_IDS
  FROM DAILY_INTAKE DI
  INNER JOIN DAILY_MEAL_INTAKE DMI
  ON DI.ACCOUNT_ID=DMI.ACCOUNT_ID AND DI.INTAKE_DATE=DMI.INTAKE_DATE
  GROUP BY DI.ID, DI.ACCOUNT_ID, DI.INTAKE_DATE
  ORDER BY DI.ACCOUNT_ID, DI.INTAKE_DATE
WITH READ ONLY;

CREATE OR REPLACE VIEW MIFFLIN_ST_JOER_ACCOUNT_VIEW AS
  SELECT ID,
         DI.ACCOUNT_ID, 
         TDEE, 
         INTAKE_DATE, 
         CALORIE_CHANGE, 
         FAT_MULTIPLIER, 
         CARB_MULTIPLIER, 
         PROTEIN_MULTIPLIER
  FROM DAILY_INTAKE DI
  INNER JOIN MIFFLIN_ST_JOER MSJ
  ON DI.ACCOUNT_ID=MSJ.ACCOUNT_ID AND INTAKE_DATE=WEIGHT_DATE
  ORDER BY DI.ACCOUNT_ID, INTAKE_DATE
WITH READ ONLY;

CREATE OR REPLACE VIEW HARRIS_BENEDICT_ACCOUNT_VIEW AS
  SELECT ID,
         DI.ACCOUNT_ID, 
         TDEE, 
         INTAKE_DATE, 
         CALORIE_CHANGE, 
         FAT_MULTIPLIER, 
         CARB_MULTIPLIER, 
         PROTEIN_MULTIPLIER
  FROM DAILY_INTAKE DI
  INNER JOIN HARRIS_BENEDICT HB
  ON DI.ACCOUNT_ID=HB.ACCOUNT_ID AND INTAKE_DATE=WEIGHT_DATE
  ORDER BY DI.ACCOUNT_ID, INTAKE_DATE
WITH READ ONLY;