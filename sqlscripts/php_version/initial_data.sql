---------------------------------------------------------------------------------
-- constants/slowly changing
exec Food_Category_Insert('Vegetables');
exec Food_Category_Insert('Protein Foods');
exec Food_Category_Insert('Dairy');
exec Food_Category_Insert('Grains');
exec Food_Category_Insert('Fruits');
exec Food_Category_Insert('Other');

exec Unit_System_Insert('Imperial');
exec Unit_System_Insert('Metric');

exec Formula_Insert(2000, 'Mifflin-St Joer');
exec Formula_Insert(1000, 'Harris-Benedict');

exec Day_Type_Insert('Workout');
exec Day_Type_Insert('Rest');

exec Activity_Insert('Sedentary', 1.2);
exec Activity_Insert('Lightly Active', 1.375);
exec Activity_Insert('Moderately Active', 1.55);
exec Activity_Insert('Very Active', 1.725);
exec Activity_Insert('Extremely Active', 1.9);

exec Calorie_Split_Insert('Weight Loss', '(-20, 0)', 0.80, 1.00);
exec Calorie_Split_Insert('Standard Recomp', '(-20, 20)', 0.80, 1.20);
exec Calorie_Split_Insert('Weight Loss $2', '(-40, 20)', 0.60, 1.20);
exec Calorie_Split_Insert('Faster Weight Loss', '(-30, -10)', 0.70, 0.90);
exec Calorie_Split_Insert('Lean Massing', '(-10, 20)', 0.90, 1.20);
exec Calorie_Split_Insert('Weight Gain', '(10, 20)', 1.10, 1.20);
exec Calorie_Split_Insert('Weight Gain #2', '(-10, 30)', 0.90, 1.30);
exec Calorie_Split_Insert('Maintain', '(0, 0)', 1.00, 1.00);

exec Carb_Fat_Split_Insert('(50/50) - (50/50)', 0.50, 0.50, 0.50, 0.50);
exec Carb_Fat_Split_Insert('(25/75) - (75/25)', 0.25, 0.75, 0.75, 0.25);
exec Carb_Fat_Split_Insert('(20/80) - (80/20)', 0.20, 0.80, 0.80, 0.20);
exec Carb_Fat_Split_Insert('(50/75) - (75/25)', 0.50, 0.50, 0.75, 0.25);
exec Carb_Fat_Split_Insert('(15/85) - (85/15)', 0.15, 0.85, 0.85, 0.15);
exec Carb_Fat_Split_Insert('(10/90) - (90/10)', 0.10, 0.90, 0.90, 0.10);




---------------------------------------------------------------------------------
-- initial data
exec Brand_Insert('Great Value');
exec Brand_Insert('Walmart');
exec Brand_Insert('Optimum Nutrition');
exec Brand_Insert('Fage');
exec Brand_Insert('Dannon');
exec Brand_Insert('Mahatma');


exec Ingredient_Insert('1000', '4000', 'Whole Wheat Oatmeal', 40, 'g', 150, 3, 27, 5);
exec Ingredient_Insert('1000', '3000', 'Fat Free Milk', 8, 'oz', 90, 0, 27, 5);
exec Ingredient_Insert('1001', '2000', 'Chicken Breast', 112, 'g', 110, 3, 0, 21);
exec Ingredient_Insert('1002', '2000', 'Double Chocolate Whey Protein', 30.4, 'g', 120, 1, 3, 24);
exec Ingredient_Insert('1003', '3000', 'Total 0% Greek Yogurt', 227, 'g', 130, 0, 9, 23);
exec Ingredient_Insert('1004', '3000', 'Oikos 0% Greek Yogurt', 225, 'g', 120, 0, 9, 22);
exec Ingredient_Insert('1005', '4000', 'Brown Rice', 96, 'g (prepared)', 150, 1, 32, 3);

exec Meal_Insert('1000', '1000', 'Greek Yogurt and Oatmeal', 40);
exec Meal_Insert('1005', '1000', 'Greek Yogurt and Oatmeal', 225, '1000');
exec Meal_Insert('1003', '1000', 'Protein Shake w/ Water', 30.4);
exec Meal_Insert('1003', '1000', 'Protein Shake w/ Milk', 30.4);
exec Meal_Insert('1001', '1000', 'Protein Shake w/ Milk', 8, '1002');

exec Intake_Insert(1000, '1000');
exec Intake_Insert(1001, '1000');


exec Sex_Insert('Male');
exec Sex_Insert('Female');
exec Account_Insert('mlaursen', '3919e4a1a967f27dc36393488afa7e743b2d4413b0c469606e11f180bb2aa386956271647610aa2dec23177435df9657da63afb8bd7d85f1d04b26f0766bcabc');
exec Account_Insert('isis', '85aabfb09fa1959c04cca7864257d9438c61ccc3cecea7300844ee41dff5f713b24fe3c58bf03e35f8e24b439842d5a5de12323304b57fdbf4d4ec6ca591fe1f');
exec Account_UpdateBirthday(1000, to_date('01/21/1991', 'MM/DD/YYYY'));
exec Account_UpdateActivity(1000, 1000);
exec Account_UpdateHeight(1000, 71);
exec Account_UpdateFormula(1000, 1000);
exec Account_UpdateSex(1000, 1000);
exec Account_UpdateUnitSystem(1000, 1000);
exec Weight_Insert(1000, 4000, 1000, sysdate, 244.0);

exec PHP_Columns_Insert('Ingredients', 'ingredient', 'Name');
exec PHP_Columns_Insert('Ingredients', 'brand', 'Brand');
exec PHP_Columns_Insert('Ingredients', 'food_category', 'Category');
exec PHP_Columns_Insert('Ingredients', 'serving_size', 'Serving Size');
exec PHP_Columns_Insert('Ingredients', 'serving_size_unit', 'Serving Unit');
exec PHP_Columns_Insert('Ingredients', 'calorie_amount', 'Calories (kCal)');
exec PHP_Columns_Insert('Ingredients', 'fat_amount', 'Fat (g)');
exec PHP_Columns_Insert('Ingredients', 'carbohydrate_amount', 'Carbohydrates (g)');
exec PHP_Columns_Insert('Ingredients', 'protein_amount', 'Protein (g)');
