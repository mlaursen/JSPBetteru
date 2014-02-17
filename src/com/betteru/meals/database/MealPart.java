package com.betteru.meals.database;

import com.betteru.database.DatabaseCreateable;
import com.betteru.database.DatabaseManager;
import com.betteru.database.DatabaseObject;
import com.betteru.database.MyResultRow;
import com.betteru.ingredients.database.Ingredient_Old;

public class MealPart extends DatabaseObject implements DatabaseCreateable {

	private static final String LOOKUP = "MEALPART_GET_BYID(:ID, :CUROSR)";
	private static final String CREATE = "MEALPART_INSERT(:MEALID, :INGID, :AMT, :UNIT)";
	private boolean defUnit;
	private String mealId;
	private Ingredient_Old i;
	private double amount;
	public MealPart() {	}
	public MealPart(String id) {
		super(id);
		MealPart mp = lookup(id);
		setMealId(mp.getMealId());
		setAmount(mp.getAmount());
		setDefaultUnit(mp.getDefaultUnit());
		setIngredient(mp.getIngredient());
	}

	public MealPart(MyResultRow r) {
		super(r);
		setMealId(r.get("mealid"));
		setAmount(r.get("amount"));
		setDefaultUnit(r.get("default_unit"));
		setIngredient(r.get("ingredientid"));
	}
	
	public void setMealId(String id) {
		mealId = id;
	}
	
	public void setIngredient(String id) {
		setIngredient(new Ingredient_Old(id));
	}
	
	public void setIngredient(Ingredient_Old i) {
		this.i = i;
	}
	
	public void setAmount(String amt) {
		setAmount(Double.parseDouble(amt));
	}
	
	public void setAmount(double amt) {
		amount = amt;
	}
	
	public void setDefaultUnit(String u) {
		defUnit = u.equals("0");
	}
	
	public void setDefaultUnit(boolean b) {
		defUnit = b;
	}
	
	public String getMealId() {
		return mealId;
	}
	
	public double getAmount() {
		return amount;
	}
	
	public boolean getDefaultUnit() {
		return defUnit;
	}
	
	public int getDefaultUnitForDB() {
		return defUnit ? 0 : 1;
	}
	
	public Ingredient_Old getIngredient() {
		return i;
	}
	
	public String getIngredientId() {
		return i.getId();
	}

	@Override
	public boolean create() {
		return DatabaseManager.executeStoredProcedure(CREATE, getMealId(), getIngredientId(), getAmount(), getDefaultUnitForDB());
	}
	
	public MealPart lookup(String id) {
		return lookup(id, MealPart.class);
	}

	@Override
	protected <T extends DatabaseObject> T lookup(String id, Class<T> type) {
		return type.cast(new MealPart(DatabaseManager.getStoredProcedureFirstRow(LOOKUP, id)));
	}

}
