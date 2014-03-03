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
         ACCOUNT_PKG.GETAGE(BIRTHDAY) AGE, 
         ACTIVE_SINCE
  FROM ACCOUNT ACT
  JOIN ACCOUNT_SETTING ACTS ON ACT.ID = ACTS.ACCOUNTID
  JOIN MULTIPLIER M ON M.NAME=MULTIPLIER
WITH READ ONLY;


CREATE OR REPLACE VIEW ACCOUNT_WEIGHT_VIEW AS
  SELECT ID, 
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
  LEFT OUTER JOIN WEIGHT W ON ID = W.ACCOUNT_ID
  ORDER BY ID, WEIGHT_DATE
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





