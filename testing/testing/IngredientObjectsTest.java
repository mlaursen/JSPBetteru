/**
 * 
 */
package testing;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

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
			boolean delete = false;
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
	
	public Ingredient getTestIngredient() {
		String name = "Test Ingredient";
		Brand brand = new Brand("Test Brand");
		Category catg = new Category("Proteins");
		Serving def = new Serving(22, new FoodUnit("g"));
		Serving alt = new AltServing(30, new FoodUnit("other"));
		Calorie calorie = new Calorie(400);
		Fat fat = new Fat(10);
		Carbohydrate carbs = new Carbohydrate(33);
		Protein protein = new Protein(30);
		return new Ingredient(name, brand, catg, def, alt, calorie, fat, carbs, protein);
	}
	
	@Test
	public void testCreateIngredient() {
		Ingredient i = getTestIngredient();
		assertTrue(tom.create(i));
	}

	@Test
	public void testGetIngredient() {
		Ingredient i = tom.get(0, Ingredient.class);
		assertNotNull(i);
	}
	
	@Test
	public void testGetAllIngredient() {
		List<Ingredient> all = tom.getAll(Ingredient.class);
		assertNotNull(all);
		assertEquals(1, all.size());
		assertEquals(getTestIngredient(), all.get(0));
	}
	
}
