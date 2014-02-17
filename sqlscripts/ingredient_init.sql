CREATE TABLE INGREDIENT
( ID INTEGER NOT NULL
, NAME VARCHAR2(128) NOT NULL
, BRAND VARCHAR2(60) NOT NULL
, CATEGORY VARCHAR2(40) NOT NULL
, SERVING_SIZE NUMBER(6,3) NOT NULL
, SERVING_UNIT VARCHAR2(8) NOT NULL
, ALT_SERVING_SIZE NUMBER(6,3)
, ALT_SERVING_UNIT VARCHAR2(8)
, CALORIES NUMBER(6,2)
, FAT NUMBER(6,2)
, CARBS NUMBER(6,2)
, PROTEIN NUMBER(6,2)
, CONSTRAINT PK_INGREDIENT_ID PRIMARY KEY (ID)
, CONSTRAINT FK_BRAND FOREIGN KEY(BRAND) REFERENCES BRAND(NAME)
, CONSTRAINT FK_CATEGORY FOREIGN KEY(CATEGORY) REFERENCES CATEGORY(NAME)
, CONSTRAINT FK_SERVING_UNIT FOREIGN KEY(SERVING_UNIT) REFERENCES FOOD_UNIT(NAME)
, CONSTRAINT FK_ALT_SERVING_UNIT FOREIGN KEY(ALT_SERVING_UNIT) REFERENCES FOOD_UNIT(NAME)
);

CREATE SEQUENCE SEQ_INGREDIENTID
START WITH 0
MINVALUE 0
INCREMENT BY 1
CACHE 20
NOCYCLE;

-------------------------------------------------------------------------------
-- INGREDIENT PROCEDURES
-------------------------------------------------------------------------------
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
               , PID IN INGREDIENT.ID%TYPE DEFAULT SEQ_INGREDIENTID.NEXTVAL
               );
  
  -- Returns a SYS_REFCURSOR for all the ingredients from with foreign key
  -- 'prettified'. << Brings names
  PROCEDURE GET(PCURSOR OUT SYS_REFCURSOR);
  
  -- Returns a SYS_REFCURSOR for an ingredient by ingredient id
  PROCEDURE GET(PID IN INGREDIENT.ID%TYPE, PCURSOR OUT SYS_REFCURSOR);
  
  -- Returns a filtered SYS_REFCURSOR for all ingredients that 
  -- math the category and brand constraints.
  PROCEDURE FILTER( PCATEGORY IN VARCHAR2
                  , PBRAND IN VARCHAR2
                  , PCURSOR OUT SYS_REFCURSOR
                  );
  
END INGREDIENT_PKG;
/

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
                         , PID IN INGREDIENT.ID%TYPE DEFAULT SEQ_INGREDIENTID.NEXTVAL
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



---
EXEC INGREDIENT_PKG.NEW('Chicken Breast', 'Food City', 'Proteins', 4, 'oz', 112, 'g', 140, 4, 0, 25);
EXEC INGREDIENT_PKG.NEW('Brown Rice', 'Mahatma', 'Carbs', 43, 'g', 0.5, 'c', 150, 1, 32, 3);