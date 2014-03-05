package testing;

import com.betteru.accounts.objects.Account;
import com.github.mlaursen.database.managers.ConnectionManager;
import com.github.mlaursen.database.managers.ObjectManager;


public class Testing {

	public static void main(String[] args) throws Exception {
		ConnectionManager m = new ConnectionManager();
		ObjectManager om = new ObjectManager(Account.class);
		System.out.println(om);
		//System.out.println(new IngredientChoice().getAllChoices());
		/*
		MealPartView mpv = new MealPartView();
		System.out.println(mpv.getAll());
		System.out.println(mpv.getAll(0));		
		System.out.println(mpv.getDatabaseManagerToString());
		
		MealView mv = new MealView();
		mv = mv.get("0", MealView.class);
		MealView mv0 = new MealView(0);
		System.out.println(mv0);
		System.out.println(mv);
		System.out.println(mv.getDatabaseManagerToString());
		System.out.println(mv.getIngredientList());
		System.out.println(mv.getAll());
		List<MealView> mvs = new MealView().getAll(MealView.class);
		System.out.println(mvs);
		Ingredient i = new Ingredient();
		/*
		//Ingredient i = new Ingredient("Chicken Breast");
		Ingredient i = new Ingredient();
		System.out.println(i.getDatabaseManagerToString());
		i.setName("0% Greek Yogurt");
		i.setBrand(new Brand("Chobani"));
		i.setCategory(new Category("Dairy"));
		i.setDefaultServing(new Serving(227, new FoodUnit("g")));
		i.setAlternateServing(new AltServing(1, new FoodUnit("c")));
		i.setCalories(new Calorie(140));
		i.setFat(new Fat(0));
		i.setCarbs(new Carbohydrate(9));
		i.setProtein(new Protein(23));
		System.out.println(i);
		System.out.println(i.create());
		//.out.println(i);
		System.out.println(i.getDatabaseManagerToString());
		String n = "getAltServingSize";
		System.out.println(n.matches("(?i)getaltservingsize.*"));
		//System.out.println(i.getAll());
		/*
		Brand b = new Brand("Food City");
		//System.out.println(b.getAll());
		//System.out.println(b.getAllChoices());
		System.out.println(b);
		System.out.println(b.getDatabaseManagerToString());
		System.out.println(b.getAll(Brand.class));
		
		Category c = new Category("Carbs");
		System.out.println(c);
		System.out.println(c.getDatabaseManagerToString());
		System.out.println(c.getAll());
		
		FoodUnit fu = new FoodUnit("g");
		System.out.println(fu);
		System.out.println(fu.getDatabaseManagerToString());
		System.out.println(fu.getAll());
		//System.out.println(b.get("Fage"));
		//Brand fage = new Brand("Fage");
		//System.out.println(fage);
		/*
		Account a = new Account(20);
		System.out.println(a);
		a.setBirthday(SecurityUtil.stringToDate("01/21/1950"));
		a.setUnitSystem(new UnitSystem("IMPERIAL"));
		a.setGender(new Gender("MALE"));
		System.out.println(a);
		//System.out.println(a.update());
		//a.setGender(new Gender("FEMALE"));
		//System.out.println(a.update());
		//AccountSetting as = new AccountSetting(0);
		//System.out.println(as.getDatabaseManagerToString());
		//System.out.println(as.update());
		/*
		TempAccount ta = new TempAccount("test", "teset");
		System.out.println(ta);
		Account a = new Account(0);
		System.out.println(a.getDatabaseManagerToString());
		System.out.println(a);
		AccountSetting as = new AccountSetting(0);
		System.out.println(as);
		System.out.println(as.getDatabaseManagerToString());
		AccountView av = new AccountView(0);
		System.out.println(av);
		System.out.println(new Gender().getAll());
		List<DropdownChoice> GENDERS = new Gender().getAllChoices();
		System.out.println(GENDERS);
		//System.out.println(new Account());
		//AccountView av = new AccountView(0);
		//System.out.println(av);
		//System.out.println(new Gender("male"));
		//System.out.println(new Gender().getAll(Gender.class));
		/*
		System.out.println(new MealPartView().getAll("1"));
		/*
		List<DropdownChoice> foodUnits = new FoodUnit().getAllChoices();
		List<FoodUnit> units = new FoodUnit().getAll(FoodUnit.class);
		System.out.println(units);
		System.out.println(foodUnits);
		System.out.println(foodUnits);
		Dropdown d = new Dropdown("test", foodUnits);
		System.out.println(d);
		for(DropdownChoice c : d.getChoices()) {
			System.out.println("choice: " + c.getDropdownKey() + ", " + c.getDropdownValue());
		}
		System.out.println(d.toHtml());
		/*
		Account a = new Account("0");
		AccountSetting as = new AccountSetting("0");
		System.out.println(a);
		System.out.println(as);
		AccountView av = new AccountView("0");
		System.out.println(av);
		
		System.out.println(Multiplier.get("lightly active"));
		//System.out.println(new AccountView("0"));
		/*
		Gender g = new Gender();
		System.out.println(g.getAll());
		System.out.println(new Weekday().getAll());
		System.out.println(new Multiplier().getAll());
		System.out.println(new UnitSystem().getAll());
		//System.out.println(g.get("male"));
		
		/*
		Procedure p = new Procedure("GET", "ID");
		Procedure p2 = new Procedure("GETALL");
		p2.setDisplayName("GET");
		Package gets = new Package("GETS", p, p2);
		System.out.println(gets.call("GETALL"));
		System.out.println(gets.call("GET"));
		
		Package ex = new Package(Account.class, p, p2);
		System.out.println(ex.call("get"));
		
		Multiplier m = new Multiplier();
		System.out.println(m.getPackage());
		
		System.out.println(m.getAllProcedureString());
		System.out.println(m.call("getall"));
		
		Account a = new Account();
		System.out.println(a.getPackage());
		
		TempAccount ta = new TempAccount("test", "teset");
		System.out.println(ta.getPackage());
		//TempAccount ta1 = new TempAccount("0");
		//System.out.println(ta1);
		
		Account a1 = new Account("0");
		System.out.println("A! Call: " + a.call("get"));
		System.out.println(a1);
		
		//System.out.println(a1);
		//System.out.println(a1.createFromTemp(ta1));
		//System.out.println(ta.create());
		//System.out.println(ta.create());
		//System.out.println(m.getAll(Multiplier.class));
		/*
		Account_Old a = new Account_Old("0");
		Account_Old a2 = new Account_Old("test", "test");
		System.out.println(a);
		System.out.println(a2.isValidUser());
		
		List<DropdownChoice> gs = new GenderChoice().getAll();
		System.out.println(gs);
		
		System.out.println(new RecalcChoice().getAll());
		System.out.println(new UnitChoice().getAll());
		System.out.println(new MultiplierChoice().getAll());
		
		System.out.println(GenderChoice.getGenderChoice("male").getKey());
		System.out.println(MultiplierChoice.getMultiplierChoice("SEDENTARY"));
		System.out.println(UnitChoice.getUnitChoice("metric"));
		System.out.println(RecalcChoice.getRecalcChoice("monday"));
		/*
		//PasswordHash h = new PasswordHash("test", "test");
		//System.out.println(h.getHash());
		
		TempAccount ta = new TempAccount("test", "test");
		ta.create();
		ta = new TempAccount("0");
		Account_Old.createFromTemp(ta);
		//ta = new TempAccount("2");
		//System.out.println(Account_Old.createFromTemp(ta));
		/*
		System.out.println(ta);
		Account_Old a = new Account_Old("1");
		System.out.println(a);
		Account_Old a2 = new Account_Old("testing", "test");
		System.out.println(a2.isValidUser());
		//System.out.println(ta.create());
		/*
		//System.out.println(ta.create());
		//System.out.println(ta.create());
		ta = new TempAccount("0");
		System.out.println(ta);
		
		Account_Old a = new Account_Old("0");
		System.out.println(a);
		System.out.println(new Account_Old("test", "test").isValidUser());
		//System.out.println(Account_Old.createFromTemp(ta));
		//System.out.println(ta.getUsername() + " " + ta.getPassword() + " " + ta.getCode());
		//boolean worked = ta.create();
		//TempAccount ta = new TempAccount("0");
		//System.out.println(ta.getUsername());
		//Account_Old a = new Account_Old("test", "test");
		//System.out.println(a);
		//System.out.println(a.isValidUser());
		//System.out.println(Account_Old.createFromTemp(ta));
		/*
		List<GenderChoice> gs = new GenderChoice().lookupAll();
		List<MultiplierChoice> ms = new MultiplierChoice().lookupAll();
		List<RecalcChoice> rs = new RecalcChoice().lookupAll();
		List<UnitChoice> us = new UnitChoice().lookupAll();
		
		for(GenderChoice g : gs) {
			System.out.println(g.getId() + " " + g.getName());
		}
		
		for(MultiplierChoice m : ms) {
			System.out.println(m.getId() + " " + m.getName() + " " + m.getMultiplier());
		}
		
		for(RecalcChoice r : rs) {
			System.out.println(r.getId() + " " + r.getName());
		}
		
		for(UnitChoice u : us) {
			System.out.println(u.getId() + " " + u.getName());
		}
		/*
		GenderChoice g = new GenderChoice().lookup("0");
		System.out.println(g.getName());
		MultiplierChoice c = new MultiplierChoice().lookup("1");
		System.out.println(c.getName());
		/*
		//MyResultRow r = DatabaseManager.getStoredProcedureFirstRow("MULTIPLIER_GETALL(:CURSOR)");
		MyResultRow r = DatabaseManager.getStoredProcedureFirstRow("MULTIPLIER_GET_BYID(:ID, :CURSOR)", "0");
		MultiplierChoice c = new MultiplierChoice(r);//new MultiplierChoice().lookup("0");
		System.out.println(c.getName());
		
		/*
		MultiplierChoice c = new MultiplierChoice();
		List<MultiplierChoice> choices = c.lookupAll();
		System.out.println(choices.get(0).getName());
		/*
		Account_Old a = new Account_Old("0");
		a.getFullAccountDetails();
		//System.out.println(a);
		AccountSettings as = new AccountSettings(a);
		//System.out.println(as);
		//as.setDayChoices(Account_Old.DayChoices.MONDAY);
		as.setMultiplierChoices(Account_Old.MultiplierChoices.SEDENTARY);
		as.setHeight(70);
		System.out.println(as);
		boolean success = as.update();
		System.out.println("Success ?"+success);
		//System.out.println(as);
		//EditAccountForm f = new EditAccountForm(a);
		//System.out.println(a);
		
		
		/*
		Dropdown d = new Dropdown("choices", new Account_Old(0).getMultiplierChoices());
		System.out.println(d.toHtml());
		
		System.out.println("\ntesting\n");
		ControlGroup cg = new ControlGroup(d);
		System.out.println(cg.toHtml());
		/*
		LoginForm f = new LoginForm();
		System.out.println(f.isValid());
		System.out.println(f.getErrorList());
		//System.out.println(f.getErrors());
		//System.out.println(f);
		InputField iFld = new TextField("test");
		
		LoginForm f2 = new LoginForm();
		
		System.out.println(f2.getFields());
		System.out.println(f2.getFieldKeys());
		System.out.println(f2.getField("username"));
		System.out.println(f2.isValid());
	
		
		//System.out.println(new Account_Old(1).getFullAccountDetails());
		
		//Account_Old a = new Account_Old("test2", "test2");
		//a.create();
		
		//AccountSettings as = new AccountSettings(0);
		//System.out.println(as);
		
		//Account_Old a = new Account_Old("test", "test");
		//a.create();
		
		/*
		System.out.println(new TextField("testing").toHtml());
		System.out.println(new SubmitButton().setValue("login").toHtml());
		System.out.println(new PasswordField().toHtml());
		
		String middleware = SecurityUtil.createCode(16);
		System.out.println(middleware);
		System.out.println(new HiddenField(middleware).toHtml());
		System.out.println(new HiddenField(SecurityUtil.createCode(16)).toHtml());
		
		System.out.println("\n\n\nStarting Account_Old");
		System.out.println(new EditAccountForm().toHtml());
		
		System.out.println(new LoginForm().toHtml());
		
		
		InputField t = new TextField("username");
		System.out.println(t);
		System.out.println(t.getLabel());
		System.out.println(t.getPlaceholder());
		
		Link l = new Link("create");
		l.setCssClass("btn btn-primary");
		l.setHref("#login");
		l.setDataToggle("create-account-modal");
		
		Button b = new SubmitButton();
		b.setValue("Submit");
		System.out.println(l.toHtml());
		System.out.println(new Link("create").toHtml());
		System.out.println(b.toHtml());
		
		System.out.println(new CreateAccountForm().toHtml());
		
		System.out.println("\n\n\nStarting CreateAccountForm");
		System.out.println(new CreateAccountForm().asModal());
		/*
		System.out.println(SecurityUtil.createCode().length());
		Account_Old a = new Account_Old("test", "test");
		a.setId();
		int id = a.getId();
		System.out.println(a);
		System.out.println(a.isValidUser());
		
		//TempAccount ta = new TempAccount("teest", "testing");
		//ta.create();
		TempAccount ta = TempAccount.getTempAccount(0);
		System.out.println(ta);
		//Account_Old.createFromTemp(ta);
		
		//ta.delete();
		/*
		MyResultSet rs = DatabaseManager.getStoredProcedureCursor("account_getpassword_byusername(?, ?)", Arrays.asList("test"));
		for(Object row : rs) {
			MyResultRow r = (MyResultRow) row;
			System.out.println(r.get("password"));
			System.out.println();
		}
		/*
		for(Map<String,String> row : rs) {
			System.out.println(row);
			System.out.println(row.get("password"));
		}
		
		//Account_Old a = new Account_Old("test", "test");
		//System.out.println("Is valid? " + a.isValidUser());
		//System.out.println(a.getPassword());
		//System.out.println(a.getUsername());
		//System.out.println(a.isValidUser());
		
		/*
		String hash = createHash(a.getUsername(), a.getPassword());
		String salt = hash.substring(0, 64);
		String pswd = repeatedHashing(salt, a.getPassword());
		
		System.out.println("Hash: " + hash);
		System.out.println("Salt: " + salt);
		System.out.println("Pswd: " + pswd);
		System.out.println("Equal? " + hash.equals(pswd));
		
		Connection conn = DatabaseManager.getConnection();
		Statement   stmt = conn.createStatement();
		ResultSet  rs   = stmt.executeQuery("SELECT * FROM ACCOUNTS_ACCOUNT");
		ResultSetMetaData rsmd = rs.getMetaData();
		rs.next();
		String username = rs.getString("username");
		String password = rs.getString("password");
		String salt2    = password.substring(0, 64);
		String hash2    = repeatedHashing(salt2, "test");
		
		System.out.println("Username: " + username + "\nPassword: " + password + "\nSalt    : " + salt2);
		System.out.println("Hash    : " + hash2);
		/*
		int cols = rsmd.getColumnCount();
		while(rs.next()) {
			for(int i = 1; i <= cols; i++) {
				System.out.println(rsmd.getColumnName(i) + ": " + rs.getString(i));
			}
			System.out.println("next");
		}
		*/
		//System.out.println("Account_Old valid? " + a.isValidUser());
		
		/*
		
		String hash = createHash("test", "test");
		System.out.println(hash);
		String salt = hash.substring(0,64);
		String pswd = hash.substring(64);
	
		System.out.println(salt);
		System.out.println(pswd);
		
		String hash2 = "bd750b6c1f8ccb2eed3eab3b5981cd959f8c256d257895004de11da1eb7e25478480d61dfe74b49d6483ed103422cbb8351bd1fb71f6886f1731e27855673262";
		String salt2 = hash2.substring(0, 64);
		String pswd2 = repeatedHashing(salt2, "test");
		System.out.println(pswd2);
		System.out.println(hash2);
		System.out.println(hash2.equals(pswd2));
		*/
	}

}
