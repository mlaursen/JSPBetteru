
DROP TABLE Formula;









-- create forula views
CREATE VIEW Mifflin_ST_Joer
AS
SELECT FormulaID, AccountID, F.Name
FROM Formula F INNER JOIN Account Act ON F.FormulaID = Act.FormulaID;

-- create sequences



-- insert data


INSERT INTO Unit_System(UnitSystemID, Name)
VALUES(1000, 'Imperial');

INSERT INTO Unit_System(UnitSystemID, Name)
VALUES(2000, 'Metric');



INSERT INTO Formula(FormulaID, UnitSystemID, Name)
VALUES(1000, 2000, 'Mifflin-St Joer');

INSERT INTO Formula(FormulaID, UnitSystemID, Name)
VALUES(2000, 1000, 'Harris-Benedict');

INSERT INTO Formula(FormulaID, UnitSystemID, Name)
VALUES(3000, 1000, 'Simple Multiplier');



INSERT INTO Day_Type(DayID, Name)
VALUES(1000, 'Workout');

INSERT INTO Day_Type(DayID, Name)
VALUES(2000, 'Rest');


COMMIT;