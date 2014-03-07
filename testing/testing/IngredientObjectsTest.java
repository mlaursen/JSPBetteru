/**
 * 
 */
package testing;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.ClassRule;
import org.junit.Test;
import org.junit.rules.ExternalResource;

import com.betteru.databasechoices.ingredients.Brand;
import com.betteru.databasechoices.ingredients.Category;
import com.betteru.databasechoices.ingredients.FoodUnit;
import com.betteru.ingredients.AltServing;
import com.betteru.ingredients.Calorie;
import com.betteru.ingredients.Carbohydrate;
import com.betteru.ingredients.Fat;
import com.betteru.ingredients.Protein;
import com.betteru.ingredients.Serving;
import com.betteru.ingredients.objects.Ingredient;
import com.github.mlaursen.database.managers.TestingObjectManager;

/**
 * @author mikkel.laursen
 * 
 */
public class IngredientObjectsTest {
	
	protected static TestingObjectManager tom;
	
	@ClassRule
	public static ExternalResource resource = new ExternalResource() {
		
		@Override
		protected void before() {
			boolean debug = false;
			boolean delete = true;
			boolean copyData = false;
			tom = new TestingObjectManager(delete, debug, copyData, "ingredient", "category", "brand", "food_unit");
			tom.addPackage(Ingredient.class);
			tom.addPackage(Brand.class);
			tom.addPackage(Category.class);
			tom.recompile();
		}
		
		@Override
		protected void after() {
			tom.cleanUp();
		}
	};
	
	protected Ingredient getTestIngredient() {
		return getTestIngredient("Test Ingredient", "Test Brand", "Proteins", 22, "g", 30, "other", 400, 10, 33, 30);
	}
	
	protected static Ingredient getTestIngredient(String name, String brand, String category, double s1, String u1, double s2, String u2,
			double cal, double fat, double carb, double pro) {
		return new Ingredient(name, new Brand(brand), new Category(category), new Serving(s1, new FoodUnit(u1)), new AltServing(s2,
				new FoodUnit(u2)), new Calorie(cal), new Fat(fat), new Carbohydrate(carb), new Protein(pro));
	}
	
	@Test
	public void testCreateAndGetIngredient() {
		Ingredient i = getTestIngredient();
		assertTrue(tom.create(i));
		i = tom.get(0, Ingredient.class);
		assertNotNull(i);
	}
	
	public static void createTestIngredients(TestingObjectManager tom) {
		String[] catgs = {"Proteins", "Carbs", "Dairy", "Other" };
		List<Ingredient> ings = new ArrayList<Ingredient>();
		for(int i = 0; i < 20; i++) {
			String brand = "Test Brand" + (i % 5);
			String catg = catgs[i%catgs.length];
			Ingredient ing = getTestIngredient("Test Ingredient"+i, brand, catg, 22, "g", 30, "other", 400, 10, 33, 30);
			ings.add(ing);
			assertTrue(tom.create(ing));
		}
		List<Ingredient> fromDB = tom.getAll(Ingredient.class);
		for(Ingredient i : fromDB) {
			assertTrue(ings.contains(i));
		}
	}
	
	@Test
	public void testCreateAndGetMultiplieIngredient() {
		String[] catgs = {"Proteins", "Carbs", "Dairy", "Other" };
		List<Ingredient> ings = new ArrayList<Ingredient>();
		for(int i = 0; i < 20; i++) {
			String brand = "Test Brand" + (i % 5);
			String catg = catgs[i%catgs.length];
			Ingredient ing = getTestIngredient("Test Ingredient"+i, brand, catg, 22, "g", 30, "other", 400, 10, 33, 30);
			ings.add(ing);
			assertTrue(tom.create(ing));
		}
		List<Ingredient> fromDB = tom.getAll(Ingredient.class);
		for(Ingredient i : fromDB) {
			assertTrue(ings.contains(i));
		}
	}
	
	@Test
	public void testFilterIngredient() {
		List<Ingredient> allTestBrand1 = tom.filter(Ingredient.class, null, "Test Brand1");
		for(Ingredient i : allTestBrand1) {
			assertTrue(i.getBrand().equals(new Brand("Test Brand1")));
		}
		
		List<Ingredient> allProteins = tom.filter(Ingredient.class, "Proteins", null);
		for(Ingredient i : allProteins) {
			assertTrue(i.getCategory().equals(new Category("Proteins")));
		}
		
		List<Ingredient> allProteinBrand1 = tom.filter(Ingredient.class, "Proteins", "Test Brand1");
		for(Ingredient i : allProteinBrand1) {
			assertTrue(i.getBrand().equals(new Brand("Test Brand1")) && i.getCategory().equals(new Category("Proteins")));
		}
	}
}
