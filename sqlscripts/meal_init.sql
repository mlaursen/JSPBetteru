CREATE TABLE MEAL
( ID INTEGER NOT NULL
, NAME VARCHAR2(128) NOT NULL
, DESCRIPTION CLOB
, CONSTRAINT PK_MEAL_ID PRIMARY KEY (ID)
);


CREATE SEQUENCE SEQ_MEALID
START WITH 0
MINVALUE 0
INCREMENT BY 1
CACHE 20
NOCYCLE;


-----------------
-- MEAL VIEW
-----------------
CREATE OR REPLACE VIEW MEAL_VIEW AS
  SELECT ID, NAME, DESCRIPTION, TOTAL_CALORIES, TOTAL_FAT, TOTAL_CARBS, TOTAL_PROTEIN
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

-------------------------------------------------------------------------------
-- MEAL PROCEDURES
-------------------------------------------------------------------------------
CREATE OR REPLACE PACKAGE MEAL_PKG AS
  -- Creates a new meal
  PROCEDURE NEW( PNAME IN MEAL.NAME%TYPE
               , PDESC IN MEAL.DESCRIPTION%TYPE
               , PID   IN MEAL.ID%TYPE DEFAULT SEQ_MEALID.NEXTVAL
               );
  
  -- Returns a SYS_REFCURSOR for all meals
  PROCEDURE GET(PCURSOR OUT SYS_REFCURSOR);
  
  -- Returns a SYS_REFCURSOR for the meal by id
  PROCEDURE GET( PID IN MEAL.ID%TYPE
               , PCURSOR OUT SYS_REFCURSOR
               );
  
  
END MEAL_PKG;
/

CREATE OR REPLACE PACKAGE BODY MEAL_PKG AS
  PROCEDURE NEW( PNAME IN MEAL.NAME%TYPE
               , PDESC IN MEAL.DESCRIPTION%TYPE
               , PID   IN MEAL.ID%TYPE DEFAULT SEQ_MEALID.NEXTVAL
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

-----------------------------------------------------
-- initial
-----
EXEC MEAL_PKG.NEW('300g Chicken Breast', '300g Chicken breast with random veggies');
EXEC MEAL_PKG.NEW('Isopure Protein shake', '1 scoop isopure protein, 227g greek yogurt, 8oz milk mixed together in a blender with 10 ice cubes.');