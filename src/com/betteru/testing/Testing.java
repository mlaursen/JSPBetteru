package com.betteru.testing;

import com.betteru.database.Procedure;
import com.betteru.database.Package;

public class Testing {

	public static void main(String[] args) throws Exception {
		Procedure p = new Procedure("GET", "ID");
		Procedure p2 = new Procedure("GETALL");
		p2.setDisplayName("GET");
		Package gets = new Package("GETS", p, p2);
		System.out.println(gets.call("GETALL"));
		
		/*
		Account a = new Account("0");
		Account a2 = new Account("test", "test");
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
		Account.createFromTemp(ta);
		//ta = new TempAccount("2");
		//System.out.println(Account.createFromTemp(ta));
		/*
		System.out.println(ta);
		Account a = new Account("1");
		System.out.println(a);
		Account a2 = new Account("testing", "test");
		System.out.println(a2.isValidUser());
		//System.out.println(ta.create());
		/*
		//System.out.println(ta.create());
		//System.out.println(ta.create());
		ta = new TempAccount("0");
		System.out.println(ta);
		
		Account a = new Account("0");
		System.out.println(a);
		System.out.println(new Account("test", "test").isValidUser());
		//System.out.println(Account.createFromTemp(ta));
		//System.out.println(ta.getUsername() + " " + ta.getPassword() + " " + ta.getCode());
		//boolean worked = ta.create();
		//TempAccount ta = new TempAccount("0");
		//System.out.println(ta.getUsername());
		//Account a = new Account("test", "test");
		//System.out.println(a);
		//System.out.println(a.isValidUser());
		//System.out.println(Account.createFromTemp(ta));
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
		Account a = new Account("0");
		a.getFullAccountDetails();
		//System.out.println(a);
		AccountSettings as = new AccountSettings(a);
		//System.out.println(as);
		//as.setDayChoices(Account.DayChoices.MONDAY);
		as.setMultiplierChoices(Account.MultiplierChoices.SEDENTARY);
		as.setHeight(70);
		System.out.println(as);
		boolean success = as.update();
		System.out.println("Success ?"+success);
		//System.out.println(as);
		//EditAccountForm f = new EditAccountForm(a);
		//System.out.println(a);
		
		
		/*
		Dropdown d = new Dropdown("choices", new Account(0).getMultiplierChoices());
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
	
		
		//System.out.println(new Account(1).getFullAccountDetails());
		
		//Account a = new Account("test2", "test2");
		//a.create();
		
		//AccountSettings as = new AccountSettings(0);
		//System.out.println(as);
		
		//Account a = new Account("test", "test");
		//a.create();
		
		/*
		System.out.println(new TextField("testing").toHtml());
		System.out.println(new SubmitButton().setValue("login").toHtml());
		System.out.println(new PasswordField().toHtml());
		
		String middleware = Util.createCode(16);
		System.out.println(middleware);
		System.out.println(new HiddenField(middleware).toHtml());
		System.out.println(new HiddenField(Util.createCode(16)).toHtml());
		
		System.out.println("\n\n\nStarting Account");
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
		System.out.println(Util.createCode().length());
		Account a = new Account("test", "test");
		a.setId();
		int id = a.getId();
		System.out.println(a);
		System.out.println(a.isValidUser());
		
		//TempAccount ta = new TempAccount("teest", "testing");
		//ta.create();
		TempAccount ta = TempAccount.getTempAccount(0);
		System.out.println(ta);
		//Account.createFromTemp(ta);
		
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
		
		//Account a = new Account("test", "test");
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
		//System.out.println("Account valid? " + a.isValidUser());
		
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
