EXEC BRAND_PKG.NEW('Isopure');
EXEC BRAND_PKG.NEW('Regal Spring');
EXEC BRAND_PKG.NEW('Food City');
EXEC BRAND_PKG.NEW('Mahatma');
EXEC BRAND_PKG.NEW('Healthy Harvest');
EXEC BRAND_PKG.NEW('Nature''s Own');
EXEC BRAND_PKG.NEW('Chobani');
EXEC BRAND_PKG.NEW('Quaker Oats');
EXEC BRAND_PKG.NEW('Fage');

EXEC CATEGORY_PKG.NEW('Proteins');
EXEC CATEGORY_PKG.NEW('Dairy');
EXEC CATEGORY_PKG.NEW('Carbs');
EXEC CATEGORY_PKG.NEW('Other');

EXEC GENDER_PKG.NEW('MALE');
EXEC GENDER_PKG.NEW('FEMALE');

EXEC WEEKDAY_PKG.NEW('SUNDAY');
EXEC WEEKDAY_PKG.NEW('MONDAY');
EXEC WEEKDAY_PKG.NEW('TUESDAY');
EXEC WEEKDAY_PKG.NEW('WEDNESDAY');
EXEC WEEKDAY_PKG.NEW('THURSDAY');
EXEC WEEKDAY_PKG.NEW('FRIDAY');
EXEC WEEKDAY_PKG.NEW('SATURDAY');

INSERT INTO UNIT_SYSTEM VALUES('IMPERIAL');
INSERT INTO UNIT_SYSTEM VALUES('METRIC');
COMMIT;

EXEC FORMULA_PKG.NEW('MIFFLIN-ST JOER', 'METRIC');
EXEC FORMULA_PKG.NEW('HARRIS-BENEDICT');
EXEC FORMULA_PKG.NEW('SIMPLE MULTIPLIER');

EXEC MULTIPLIER_PKG.NEW('SEDENTARY', 1.2);
EXEC MULTIPLIER_PKG.NEW('LIGHTLY ACTIVE', 1.375);
EXEC MULTIPLIER_PKG.NEW('MODERATELY ACTIVE', 1.55);
EXEC MULTIPLIER_PKG.NEW('VERY ACTIVE', 1.725);
EXEC MULTIPLIER_PKG.NEW('EXTREMELY ACTIVE', 1.9);

EXEC FOOD_UNIT_PKG.NEW('g', 'gram');
EXEC FOOD_UNIT_PKG.NEW( 'mL', 'millileter');
EXEC FOOD_UNIT_PKG.NEW('oz', 'ounce');
EXEC FOOD_UNIT_PKG.NEW('unit', 'unit');
EXEC FOOD_UNIT_PKG.NEW('c', 'cup');
EXEC FOOD_UNIT_PKG.NEW('tbsp', 'tablespoon');
EXEC FOOD_UNIT_PKG.NEW('tsp', 'teaspoon');
EXEC FOOD_UNIT_PKG.NEW('unknown', 'unknown');
EXEC FOOD_UNIT_PKG.NEW('scoop', 'scoop');

EXEC INGREDIENT_PKG.NEW('Chicken Breast', 'Food City', 'Proteins', 4, 'oz', 112, 'g', 140, 4, 0, 25);
EXEC INGREDIENT_PKG.NEW('Brown Rice', 'Mahatma', 'Carbs', 43, 'g', 0.5, 'c', 150, 1, 32, 3);
EXEC INGREDIENT_PKG.NEW('Zero Carb Isolate', 'Isopure', 'Proteins', 2, 'scoop', 54, 'g', 210, 1, 0, 50);
EXEC INGREDIENT_PKG.NEW('Skim Milk', 'Food City', 'Dairy', 8, 'oz', 1, 'unit', 90, 0, 13, 8);
EXEC INGREDIENT_PKG.NEW('0% Greek Yogurt', 'Fage', 'Dairy', 227, 'g', 1, 'c', 140, 0, 9, 23);


EXEC MEAL_PKG.NEW('300g Chicken Breast', '300g Chicken breast with random veggies');
EXEC MEAL_PKG.NEW('Isopure Protein shake', '1 scoop isopure protein, 227g greek yogurt, 8oz milk mixed together in a blender with 10 ice cubes.');

EXEC MEAL_PART_PKG.NEW(0, 0, 300, 1);
EXEC MEAL_PART_PKG.NEW(1, 2, 1, 0);
EXEC MEAL_PART_PKG.NEW(1, 3, 8, 0);
EXEC MEAL_PART_PKG.NEW(1, 4, 227, 0);

-- account(example, example);
EXEC ACCOUNT_PKG.NEW('example','0ae67f13ff97fe8ab96520407b08b5b9e4dbf06ff5feeaf61ddf4cbb0a61bbba0d94766d0201c790e5e2287a94f3be0f3d11f3dee2b1132f7c9f49d0511312b0');