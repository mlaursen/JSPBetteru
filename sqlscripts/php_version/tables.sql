/*
 * Creates the Tables and views for my Fitness Helper Project
 *
 * Author: Mikkel Laursen
 * Last Modifed: 03/09/2013
 */

-- drop views
DROP VIEW Meal_Total;
DROP VIEW Account_View_Formula;
DROP VIEW MIFFLIN_ST_JOER;
DROP VIEW HARRIS_BENEDICT;

-- drop tables
DROP TABLE Weight;
DROP TABLE Intake;
DROP TABLE Meal;
DROP TABLE Ingredient;
DROP TABLE Brand;
DROP TABLE Account;
DROP TABLE PHP_Columns;
DROP TABLE Activity;
DROP TABLE Calorie_Split;
DROP TABLE Carb_Fat_Split;
DROP TABLE Formula;
DROP TABLE Unit_System;
DROP TABLE Day_Type;
DROP TABLE Food_Category;
DROP TABLE Sex;

CREATE TABLE Sex
( SexID number
, Name   varchar2(6)
, CONSTRAINT SexID_pk PRIMARY KEY(SexID)
);

CREATE TABLE PHP_Columns
( PHP_ColumnID  number(4)
, Table_Name    varchar2(20)
, Column_Name   varchar2(20)
, PHP_Value     varchar2(20)
, CONSTRAINT PHP_ColumnID_pk PRIMARY KEY (PHP_ColumnID)
);


-- Create tables
CREATE TABLE Brand
( BrandID     number(4)
, Name        varchar2(30)
, CONSTRAINT BrandID_pk Primary Key(BrandID)
, CONSTRAINT Name_uq UNIQUE(Name)
);

CREATE TABLE Activity
( ActivityID  number(4)
, Name        varchar2(17)
, Multiplier  number
, CONSTRAINT ActivityID_pk PRIMARY KEY (ActivityID)
);

CREATE TABLE Calorie_Split
( SplitID       number(4)
, Name          varchar2(40)
, Abbreviation  varchar2(20)
, Workout_Mult  number(3,2)
, Rest_Mult     number(3,2)
, CONSTRAINT CalorieSplitID_pk PRIMARY KEY (SplitID)
);

CREATE TABLE Carb_Fat_Split
( SplitID       number(4)
, Name          varchar2(40)
, Rest_Fat      number(3,2)
, Rest_Carb     number(3,2)
, Workout_Fat   number(3,2)
, Workout_Carb  number(3,2)
, CONSTRAINT SplitID_pk PRIMARY KEY (SplitID)
);

CREATE TABLE Unit_System
( UnitSystemID    number(4)
, Name          varchar2(30)
, CONSTRAINT UnitSystemID_pk PRIMARY KEY (UnitSystemID)
);

CREATE TABLE Formula
( FormulaID     number(4)
, UnitSystemID  number(4)
, Name          varchar2(25)
, CONSTRAINT FormulaID_pk PRIMARY KEY (FormulaID)
, CONSTRAINT UnitSystemID_fk FOREIGN KEY (UnitSystemID) REFERENCES Unit_System(UnitSystemID)
);


CREATE TABLE Account
( AccountID     number
, ActivityID    number
, FormulaID     number
, UnitSystemID  number
, SexID         number
, Username      varchar2(50)
, Hash          char(128)
, Birthday      date
, Height        number
, CONSTRAINT AccountID_pk PRIMARY KEY (AccountID)
, CONSTRAINT ActivityID_fk FOREIGN KEY (ActivityID) REFERENCES Activity(ActivityID)
, CONSTRAINT FormulaID_fk FOREIGN KEY (FormulaID) REFERENCES Formula(FormulaID)
, CONSTRAINT SexID_fk FOREIGN KEY (SexID) REFERENCES Sex(SexID)
, CONSTRAINT Account_UnitSystemID_fk FOREIGN KEY (UnitSystemID) REFERENCES Unit_System(UnitSystemID)
, CONSTRAINT Username_uk UNIQUE(Username)
);

CREATE TABLE Day_Type
( DayID number(4)
, Name  varchar2(8)
, CONSTRAINT DayID_pk PRIMARY KEY (DayID)
);


CREATE TABLE Food_Category
( CategoryID  number(4)
, Name        varchar2(40)
, CONSTRAINT CategoryID_pk PRIMARY KEY (CategoryID)
);

CREATE TABLE Weight
( WeightID        number
, AccountID       number
, Calorie_SplitID number
, CarbFat_SplitID number
, Weight_Date     date
, Weight          number(6,2)
, CONSTRAINT WeightID_pk PRIMARY KEY (WeightID)
, CONSTRAINT CalorieID_fk FOREIGN KEY (Calorie_SplitID) REFERENCES Calorie_Split(SplitID)
, CONSTRAINT CarbFatID_fk FOREIGN KEY (CarbFat_SplitID) REFERENCES Carb_Fat_Split(SplitID)
);



CREATE TABLE Ingredient
( IngredientID        number
, BrandID             number
, CategoryID          number
, Name                varchar2(50)
, Serving_Size        number(6,2)
, Serving_Size_Unit   varchar2(15)
, Calorie_Amount      number(7,2)
, Fat_Amount          number(7,2)
, Carbohydrate_Amount number(7,2)
, Protein_Amount      number(7,2)
, CONSTRAINT IngredientID_pk Primary Key(IngredientID)
, CONSTRAINT BrandID_fk FOREIGN KEY(BrandID) REFERENCES Brand(BrandID)
, CONSTRAINT BrandID_Name_uk UNIQUE(BrandID, Name)
);

CREATE TABLE Meal
( MealID            number
, IngredientID      number
, AccountID         number
, Name              varchar2(50)
, Ingredient_Amount number
, CONSTRAINT IngredientID_fk FOREIGN KEY(IngredientID) REFERENCES Ingredient(IngredientID)
, CONSTRAINT AccountID_fk FOREIGN KEY(AccountID) REFERENCES Account(AccountID)
);



CREATE TABLE Intake
( Intake_Date date
, AccountID   number
, MealID      number
, CONSTRAINT Intake_AccountID_fk FOREIGN KEY(AccountID) REFERENCES Account(AccountID)
);


-- create views


CREATE VIEW Meal_Total AS
  SELECT MealID, AccountID, Name AS Meal_Name, sum(Calorie_Total) AS Calorie_Total, sum(Fat_Total) AS Fat_Total, sum(Carb_Total) AS Carb_Total, sum(Protein_Total) AS Protein_Total
  FROM (SELECT MealID, AccountID, Meal.Name, (Calorie_Amount*(Ingredient_Amount/Serving_Size)) AS Calorie_Total, (Fat_Amount*(Ingredient_Amount/Serving_Size)) AS Fat_Total, 
                                      (Carbohydrate_Amount*(Ingredient_Amount/Serving_Size)) AS Carb_Total, (Protein_Amount*(Ingredient_Amount/Serving_Size)) AS Protein_Total
        FROM Ingredient
        INNER JOIN Meal ON Ingredient.IngredientID = Meal.IngredientID)
  GROUP BY MealID, Name, AccountID;


CREATE VIEW Account_View_Formula
AS
  SELECT *
  FROM (SELECT Account.AccountID AS AccountID, Weight, Height, (to_char(sysdate, 'YYYY') - to_char(Birthday, 'YYYY')) AS Age, Multiplier, Sex.Name AS SEX, Unit_System.Name AS Units
        FROM Account
        INNER JOIN Activity ON Account.ActivityID=Activity.ActivityID
        INNER JOIN Weight ON Weight.AccountID=Account.AccountID
        INNER JOIN Sex ON Sex.SexID=Account.SexID
        INNER JOIN Unit_System ON Account.UnitSystemID=Unit_System.UnitSystemID)
  GROUP BY AccountID, Weight, Height, Age, Multiplier, Sex, Units;


CREATE VIEW MIFFLIN_ST_JOER
AS
  SELECT AccountID, (Multiplier * (Weight + Height - age + extra)) AS TDEE
  FROM (SELECT AccountID,
               (10 * (case when Units='Imperial' then (Weight/2.2) else Weight end)) AS Weight,
               (6.25 * (case when Units='Imperial' then (Height*2.54) else Height end)) as Height,
               (Age*5) as Age,
               case when Sex='Male' then 5 else (-161) end as Extra,
               Multiplier
         FROM Account_View_Formula);

CREATE VIEW HARRIS_BENEDICT
AS
  SELECT AccountID, (Multiplier * (extra + weight + height - age)) as TDEE
  FROM (SELECT AccountID,
               (Weight * (case when Sex='Male' then 6.23 else 4.35 end)) AS Weight,
               (Height * (case when Sex='Male' then 12.7 else 4.7 end)) as Height,
               (Age * (case when Sex='Male' then 6.67 else 4.7 end)) AS Age,
               case when Sex='Male' then 66 else 655 end AS Extra,
               Multiplier
        FROM (SELECT AccountID,
                     case when Units='Metric' then (Weight*2.2) else Weight end AS Weight,
                     case when Units='Metric' then (Height/2.54) else Height end AS Height,
                     Age,
                     Multiplier,
                     Sex
               FROM Account_View_Formula));
COMMIT;

