/**
 * 
 */
package testing;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.ClassRule;
import org.junit.Test;
import org.junit.rules.ExternalResource;

import com.betteru.databasechoices.ingredients.Brand;
import com.betteru.databasechoices.ingredients.Category;
import com.betteru.ingredients.objects.Ingredient;
import com.betteru.meals.objects.Meal;
import com.betteru.meals.objects.MealPart;
import com.betteru.meals.objects.MealPartView;
import com.betteru.meals.objects.MealView;
import com.github.mlaursen.database.managers.TestingObjectManager;


/**
 * @author mikkel.laursen
 *
 */
public class MealObjectsTest {
protected static TestingObjectManager tom;
	
	@ClassRule
	public static ExternalResource resource = new ExternalResource() {
		
		@Override
		protected void before() {
			boolean debug = false;
			boolean delete = true;
			boolean copyData = false;
			tom = new TestingObjectManager(delete, debug, copyData, "meal", "meal_part", "meal_part_view", "meal_view", "ingredient", "brand", "category");
			tom.addPackage(Brand.class);
			tom.addPackage(Category.class);
			tom.addPackage(Ingredient.class);
			tom.addPackage(MealPart.class);
			tom.addPackage(Meal.class);
			tom.addPackage(MealPartView.class);
			tom.addPackage(MealView.class);
			tom.recompile();
			IngredientObjectsTest.createTestIngredients(tom);
		}
		
		@Override
		protected void after() {
			tom.cleanUp();
		}
	};
	@Test
	public void test() {
		List<Ingredient> ingredients = tom.getAll(Ingredient.class);
		assertNotNull(ingredients);
		assertTrue(ingredients.size() > 0);
		Ingredient i = ingredients.get(0);
		Meal m = new Meal("Test Meal", "Mealy stuff mane");
		assertTrue(tom.create(m));
		MealPart mp = new MealPart("0", i.getPrimaryKey(), 20, "0");
		assertTrue(tom.create(mp));
		MealView mv = tom.get(0, MealView.class);
		assertEquals(m.getName(), mv.getName());
		assertEquals(m.getDescription(), mv.getDescription());
		mv.generateMealParts(tom);
		assertTrue(mv.getMealParts().size() > 0);
		assertEquals(1, mv.getMealParts().size());
		List<MealPartView> mpvs = tom.filter(MealPartView.class, "0");
		assertNotNull(mpvs);
		assertEquals(1, mpvs.size());
		MealPartView mpv = mpvs.get(0);
		assertEquals(mp.getAmount(), mpv.getAmount(), 0);
		assertEquals(mp.getMealId(), mpv.getMealId());
		
	}
	
}
