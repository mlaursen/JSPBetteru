
-- Create procedures
CREATE OR REPLACE PROCEDURE Brand_Insert( pName     IN Brand.Name%TYPE
                                        , pBrandID  IN Brand.BrandID%TYPE DEFAULT seq_BrandID.nextval
                                        ) IS
BEGIN
  INSERT INTO Brand(BrandID, Name)
  VALUES(pBrandID, pName);
  COMMIT;
EXCEPTION
  WHEN OTHERS THEN
    ROLLBACK;
END;
/

desc Ingredient;

CREATE OR REPLACE PROCEDURE Ingredient_Insert( pBrandID     IN Ingredient.BrandID%TYPE
                                             , pCategoryID  IN Ingredient.CategoryID%TYPE
                                             , pName        IN Ingredient.Name%TYPE
                                             , pServSize    IN Ingredient.Serving_Size%TYPE
                                             , pServUnit    IN Ingredient.Serving_Size_Unit%TYPE
                                             , pCal         IN Ingredient.Calorie_Amount%TYPE
                                             , pFat         IN Ingredient.Fat_Amount%TYPE
                                             , pCarb        IN Ingredient.Carbohydrate_Amount%TYPE
                                             , pProtein     IN Ingredient.Protein_Amount%TYPE
                                             , pID          IN Ingredient.IngredientID%TYPE DEFAULT seq_IngredientID.nextval
                                             ) IS
  emsg varchar2(250);
BEGIN
  INSERT INTO Ingredient(IngredientID, BrandID, CategoryID, Name, Serving_Size, Serving_Size_Unit, Calorie_Amount, Fat_Amount, Carbohydrate_Amount, Protein_Amount)
  VALUES(pID, pBrandID, pCategoryID, pName, pServSize, pServUnit, pCal, pFat, pCarb, pProtein);
  COMMIT;
EXCEPTION
  WHEN DUP_VAL_ON_INDEX THEN
    RAISE DUP_VAL_ON_INDEX;
    ROLLBACK;
  WHEN OTHERS THEN
    ROLLBACK;
END;
/

CREATE OR REPLACE PROCEDURE Ingredient_GetAll( pCursor  OUT SYS_REFCURSOR ) IS
BEGIN
  OPEN pCursor FOR
    SELECT IngredientID, Brand.Name, Ingredient.Name, Serving_Size, Serving_Size_Unit, Calorie_Amount, Fat_Amount, Carbohydrate_Amount, Protein_Amount
    FROM Ingredient
    INNER JOIN Brand ON Ingredient.BrandID=Brand.BrandID
    ORDER BY Brand.BrandID;
END;
/

CREATE OR REPLACE PROCEDURE Ingredient_GetByBrand( pBrandID IN Ingredient.BrandID%TYPE
                                                 , pCursor  OUT SYS_REFCURSOR
                                                 ) IS
BEGIN
  OPEN pCursor FOR
    SELECT *
    FROM Ingredient
    WHERE BrandID=pBrandID;
END;
/

CREATE OR REPLACE PROCEDURE Recipe_Insert( pIngredientID  IN Recipe.IngredientID%TYPE
                                         , pAccountID     IN Recipe.AccountID%TYPE
                                         , pName          IN Recipe.Name%TYPE
                                         , pAmt           IN Recipe.Ingredient_Amount%TYPE
                                         , pRecipeID      IN Recipe.RecipeID%TYPE DEFAULT seq_RecipeID.nextval
                                         ) IS
BEGIN
  INSERT INTO Recipe(RecipeID, IngredientID, AccountID, Name, Ingredient_Amount)
  VALUES(pRecipeID, pIngredientID, pAccountID, pName, pAmt);
  COMMIT;
EXCEPTION
  WHEN OTHERS THEN
    ROLLBACK;
END;
/

CREATE OR REPLACE PROCEDURE RecipeTotal_GetByID( pRecipeID  IN Recipe.RecipeID%TYPE
                                               , pCursor    OUT SYS_REFCURSOR
                                               ) IS
BEGIN
  OPEN pCursor FOR
    SELECT *
    FROM Recipe_Total
    WHERE RecipeID=pRecipeID;
END;
/

CREATE OR REPLACE PROCEDURE RecipeTotal_GetAll( pCursor OUT SYS_REFCURSOR ) IS
BEGIN
  OPEN pCursor FOR
    SELECT *
    FROM Recipe_Total;
END;
/

CREATE OR REPLACE PROCEDURE Intake_GetByDay( pCursor  OUT SYS_REFCURSOR
                                           , pDate    IN Intake.Intake_Date%TYPE DEFAULT SYSDATE
                                           ) IS
BEGIN
  OPEN pCursor FOR
    SELECT R.Name, sum(Calorie_Amount) AS Total_Calories, sum(Fat_Amount) AS Total_Fat, sum(Carbohydrate_Amount) AS Total_Carbs, sum(Protein_Amount) AS Total_Protein
    FROM Recipe R
    INNER JOIN Ingredient I ON R.IngredientID=I.IngredientID
    GROUP BY R.Name;
END;
/

CREATE OR REPLACE PROCEDURE Intake_Insert( pMeals     IN Intake.Meals%TYPE
                                         , pAccountID IN Intake.AccountID%TYPE
                                         , pDate      IN Intake.Intake_Date%TYPE DEFAULT SYSDATE
                                         ) AS
BEGIN
  INSERT INTO Intake(Intake_Date, AccountID, Meals)
  VALUES(pDate, pAccountID, pMeals);
  COMMIT;
EXCEPTION
  WHEN DUP_VAL_ON_INDEX THEN
    UPDATE Intake
    SET Meals = pMeals
    WHERE trunc(Intake_Date)=trunc(pDate);
    COMMIT;
  WHEN OTHERS THEN
    ROLLBACK;
END;
/



CREATE OR REPLACE PROCEDURE Food_Category_Insert( pName IN Food_Category.Name%TYPE
                                                , pID   IN Food_Category.CategoryID%TYPE DEFAULT seq_CategoryID.nextval
                                                ) IS
  msg varchar2(200);
BEGIN
  INSERT INTO Food_Category(CategoryID, Name)
  VALUES(pID, pName);
  COMMIT;
EXCEPTION
  WHEN OTHERS THEN
  msg := SQLERRM;
  dbms_output.put_line('ERROR - ' || msg);
    ROLLBACK;
END;
/

CREATE OR REPLACE PROCEDURE Food_Category_Get( pCursor OUT SYS_REFCURSOR ) IS
BEGIN
  OPEN pCursor FOR
    SELECT Name
    FROM Food_Category;
END;
/

CREATE OR REPLACE PROCEDURE Food_Category_GetByID( pID      IN Food_Category.CategoryID%TYPE
                                                 , pCursor  OUT SYS_REFCURSOR
                                                 ) IS
BEGIN
  OPEN pCursor FOR
    SELECT Name
    FROM Food_Category
    WHERE CategoryID=pID;
END;
/


CREATE OR REPLACE PROCEDURE Account_Insert( pFName  IN Account.FName%TYPE
                                          , pLName  IN Account.LName%TYPE
                                          , pUser   IN Account.Username%TYPE
                                          , pPswd   IN Account.Password%TYPE
                                          , pID     IN Account.AccountID%TYPE DEFAULT seq_AccountID.nextval
                                          ) IS
BEGIN
  INSERT INTO Account(AccountID, FName, LName, Username, Password)
  VALUES(pID, pFName, pLName, pUser, pPswd);
  COMMIT;
EXCEPTION
  WHEN OTHERS THEN
    ROLLBACK;
END;
/

CREATE OR REPLACE PROCEDURE Account_GetByUserPass( pUser    IN Account.Username%TYPE
                                                 , pPswd    IN Account.Password%TYPE
                                                 , pCursor  OUT SYS_REFCURSOR
                                                 ) IS
BEGIN
  OPEN pCursor FOR
    SELECT AccountID, FName, LName
    FROM Account
    WHERE Username=pUser AND Password=pPswd;
END;
/

CREATE OR REPLACE PROCEDURE Account_GetByID( pID      IN Account.AccountID%TYPE
                                           , pCursor  OUT SYS_REFCURSOR
                                           ) IS
BEGIN
  OPEN pCursor FOR
    SELECT AccountID, FName, LName
    FROM Account
    WHERE AccountID=pID;
END;
/