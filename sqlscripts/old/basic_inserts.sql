---------
-- BRANDS
---------
EXEC BRAND_INSERT('Isopure');
EXEC BRAND_INSERT('Regal Spring');
EXEC BRAND_INSERT('Food City');
EXEC BRAND_INSERT('Mahatma');
EXEC BRAND_INSERT('Healthy Harvest');
EXEC BRAND_INSERT('Nature''s Own');
EXEC BRAND_INSERT('Chobani');
EXEC BRAND_INSERT('Quaker Oats');


EXEC RESET_SEQ('BRAND');




-----------------
-- CATEGORIES
-----------------
EXEC CATEGORY_INSERT('Proteins');
EXEC CATEGORY_INSERT('Dairy');
EXEC CATEGORY_INSERT('Carbs');
EXEC CATEGORY_INSERT('Other');
EXEC RESET_SEQ('CATEGORY');


--------------------
-- UNITS
--------------------
EXEC UNIT_INSERT('gram', 'g');
EXEC UNIT_INSERT('millileter', 'mL');
EXEC UNIT_INSERT('ounce', 'oz');
EXEC UNIT_INSERT('unit', 'unit');
EXEC UNIT_INSERT('cup', 'c');
EXEC UNIT_INSERT('tablespoon', 'tbsp');
EXEC UNIT_INSERT('teaspoon', 'tsp');
EXEC UNIT_INSERT('unknown', 'unknown');
EXEC UNIT_INSERT('scoop', 'scoop');
EXEC RESET_SEQ('UNIT');

-----------------------
-- INGREDIENTS
-----------------------
-- NAME, BRAND, CATG, UNIT, SIZE, ALTUNIT, ALTSIZE, CAL, FAT, CAR, PRO
EXEC INGREDIENT_INSERT_NONID('Chicken Breast', 'Food City', 'Proteins', 'ounce', 4, 'gram', 112, 140, 4, 0, 25);
EXEC INGREDIENT_INSERT_NONID('Brown Rice', 'Mahatma', 'Carbs', 'gram', 43, 'cup', 0.500, 150, 1, 32, 3);




INSERT INTO INGREDIENTS_INGREDIENT (ID,NAME,BRAND_ID,CATEGORY_ID,DEFAULT_SERVING_SIZE,DEFAULT_SERVING_UNIT,ALT_SERVING_SIZE,ALT_SERVING_UNIT,CALORIES,FAT,CARBOHYDRATES,PROTEIN) values (1,'Chicken Breast',3,1,4,'oz',112,'g',140,4,0,25);
INSERT INTO INGREDIENTS_INGREDIENT (ID,NAME,BRAND_ID,CATEGORY_ID,DEFAULT_SERVING_SIZE,DEFAULT_SERVING_UNIT,ALT_SERVING_SIZE,ALT_SERVING_UNIT,CALORIES,FAT,CARBOHYDRATES,PROTEIN) values (2,'Whole Grain Thin Spaghetti',5,3,2,'oz',56,'g',180,2,39,9);
INSERT INTO INGREDIENTS_INGREDIENT (ID,NAME,BRAND_ID,CATEGORY_ID,DEFAULT_SERVING_SIZE,DEFAULT_SERVING_UNIT,ALT_SERVING_SIZE,ALT_SERVING_UNIT,CALORIES,FAT,CARBOHYDRATES,PROTEIN) values (3,'Egg (Large)',3,1,1,'unit',0,'unknown',70,5,0,6);
INSERT INTO INGREDIENTS_INGREDIENT (ID,NAME,BRAND_ID,CATEGORY_ID,DEFAULT_SERVING_SIZE,DEFAULT_SERVING_UNIT,ALT_SERVING_SIZE,ALT_SERVING_UNIT,CALORIES,FAT,CARBOHYDRATES,PROTEIN) VALUES (4,'0% Greek Yogurt (Plain)',7,2,227,'g',0,'unknown',140,0,9,23);
INSERT INTO INGREDIENTS_INGREDIENT (ID,NAME,BRAND_ID,CATEGORY_ID,DEFAULT_SERVING_SIZE,DEFAULT_SERVING_UNIT,ALT_SERVING_SIZE,ALT_SERVING_UNIT,CALORIES,FAT,CARBOHYDRATES,PROTEIN) values (5,'Brown Rice',4,3,0,'c',42,'g',150,1,32,3);
INSERT INTO INGREDIENTS_INGREDIENT (ID,NAME,BRAND_ID,CATEGORY_ID,DEFAULT_SERVING_SIZE,DEFAULT_SERVING_UNIT,ALT_SERVING_SIZE,ALT_SERVING_UNIT,CALORIES,FAT,CARBOHYDRATES,PROTEIN) values (6,'Zero Carb Vanilla',1,1,2,'scoop',62,'g',210,1,0,50);
INSERT INTO INGREDIENTS_INGREDIENT (ID,NAME,BRAND_ID,CATEGORY_ID,DEFAULT_SERVING_SIZE,DEFAULT_SERVING_UNIT,ALT_SERVING_SIZE,ALT_SERVING_UNIT,CALORIES,FAT,CARBOHYDRATES,PROTEIN) values (7,'Tilapia Filet',2,1,4,'oz',113,'g',90,1,0,21);
INSERT INTO INGREDIENTS_INGREDIENT (ID,NAME,BRAND_ID,CATEGORY_ID,DEFAULT_SERVING_SIZE,DEFAULT_SERVING_UNIT,ALT_SERVING_SIZE,ALT_SERVING_UNIT,CALORIES,FAT,CARBOHYDRATES,PROTEIN) values (8,'0% Milk',3,2,1,'c',8,'oz',90,0,13,8);
INSERT INTO INGREDIENTS_INGREDIENT (ID,NAME,BRAND_ID,CATEGORY_ID,DEFAULT_SERVING_SIZE,DEFAULT_SERVING_UNIT,ALT_SERVING_SIZE,ALT_SERVING_UNIT,CALORIES,FAT,CARBOHYDRATES,PROTEIN) values (9,'Whole Grain Oats',8,1,1,'c',40,'g',150,3,27,5);

exec RESET_SEQ('INGREDIENT');
