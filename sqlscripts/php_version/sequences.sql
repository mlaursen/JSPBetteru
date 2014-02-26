-- drop sequences
DROP SEQUENCE seq_BrandID;
DROP SEQUENCE seq_IngredientID;
DROP SEQUENCE seq_MealID;
DROP SEQUENCE seq_AccountID;
DROP SEQUENCE seq_WeightID;
DROP SEQUENCE seq_PHPColumnID;
DROP SEQUENCE seq_ActivityID;
DROP SEQUENCE seq_CalorieID;
DROP SEQUENCE seq_CarbFatID;
DROP SEQUENCE seq_CategoryID;
DROP SEQUENCE seq_UnitSystemID;
DROP SEQUENCE seq_FormulaID;
DROP SEQUENCE seq_DayTypeID;
DROP SEQUENCE seq_SexID;

-- Create sequences
CREATE SEQUENCE seq_BrandID
MINVALUE 1000
START WITH 1000
INCREMENT BY 1
NOCACHE;

CREATE SEQUENCE seq_IngredientID
MINVALUE 1000
START WITH 1000
INCREMENT BY 1
NOCACHE;

CREATE SEQUENCE seq_MealID
MINVALUE 1000
START WITH 1000
INCREMENT BY 1
NOCACHE;


CREATE SEQUENCE seq_AccountID
MINVALUE 1000
START WITH 1000
INCREMENT BY 1
NOCACHE;


CREATE SEQUENCE seq_WeightID
MINVALUE 1000
START WITH 1000
INCREMENT BY 1
NOCACHE;

CREATE SEQUENCE seq_PHPColumnID
MINVALUE 1000
START WITH 1000
INCREMENT BY 1
NOCACHE;


CREATE SEQUENCE seq_ActivityID
MINVALUE 1000
START WITH 1000
INCREMENT BY 1000
NOCACHE;

CREATE SEQUENCE seq_CarbFatID
MINVALUE 1000
START WITH 1000
INCREMENT BY 1000
NOCACHE;
CREATE SEQUENCE seq_CalorieID
MINVALUE 1000
START WITH 1000
INCREMENT BY 1000
NOCACHE;


CREATE SEQUENCE seq_CategoryID
MINVALUE 1000
START WITH 1000
INCREMENT BY 1000
NOCACHE;

CREATE SEQUENCE seq_UnitSystemID
MINVALUE 1000
START WITH 1000
INCREMENT BY 1000
NOCACHE;

CREATE SEQUENCE seq_FormulaID
MINVALUE 1000
START WITH 1000
INCREMENT BY 1000
NOCACHE;

CREATE SEQUENCE seq_DayTypeID
MINVALUE 1000
START WITH 1000
INCREMENT BY 1000
NOCACHE;

CREATE SEQUENCE seq_SexID
MINVALUE 1000
START WITH 1000
INCREMENT BY 1000
NOCACHE;