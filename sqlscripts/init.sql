start init_tables.sql;
start init_sequences.sql
start init_packages.sql;
start init_package_body.sql;
start init_views.sql;

-- I don't understand this. even with substring from 1-10, the size of the string is 40
ALTER TABLE CALORIE_SPLIT
ADD (ABBREVIATION VARCHAR2(40) GENERATED ALWAYS AS 
    (SUBSTR(CALORIE_SPLIT_PKG.FORABBR(WORKOUT_MULTIPLIER, REST_MULTIPLIER),1,10)));


start init_values.sql;

ALTER TABLE UNIT_SYSTEM READ ONLY;
ALTER TABLE WEEKDAY READ ONLY;
ALTER TABLE MULTIPLIER READ ONLY;
ALTER TABLE CATEGORY READ ONLY;

COMMIT;