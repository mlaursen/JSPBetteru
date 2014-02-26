

start tables.sql;
start sequences.sql;
start initial_data.sql;
commit;


SELECT *
FROM MIFFLIN_ST_JOER;

SELECT *
FROM HARRIS_BENEDICT;

SELECT *
FROM Account_View_Formula;

SELECT *
FROM Ingredient;

SELECT *
FROM Formula;
Select *
FROM Unit_System;
SELECT *
FROM Activity;
SELECT *
FROM Account;
SELECT *
FROM WEIGHT;
SELECT *
FROM SEX;
SELECT *
FROM Calorie_Split
WHERE SplitID=4000;
SELECT * 
FROM Food_Category;

SELECT IngredientID, Brand.Name AS Brand, Ingredient.Name AS Ingredient, Serving_Size, Serving_Size_Unit, Calorie_Amount, Fat_Amount, Carbohydrate_Amount, Protein_Amount
FROM Ingredient
INNER JOIN Brand ON Ingredient.BrandID=Brand.BrandID;

INNER JOIN Food_Category ON Food_Category.CategoryID=Ingredient.CategoryID
ORDER BY Food_Category.CategoryID;



SELECT Username, Birthday, Height, Sex.Name as Sex, Unit_System.Name AS Unit_System, Activity.Name AS Actvity
FROM Account
INNER JOIN Sex on Account.SexID=Sex.SexID
INNER JOIN Unit_System ON Account.UnitSystemID=Unit_System.UnitSystemID
INNER JOIN Activity ON Account.ActivityID=Activity.ActivityID;