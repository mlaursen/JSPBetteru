package com.betteru.accounts.forms;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.github.mlaursen.bootstrap.forms.HtmlForm;
import com.github.mlaursen.bootstrap.forms.fields.ControlGroup;
import com.github.mlaursen.bootstrap.forms.fields.Dropdown;
import com.github.mlaursen.bootstrap.forms.fields.DropdownChoice;
import com.github.mlaursen.bootstrap.forms.fields.button.SubmitButton;
import com.github.mlaursen.bootstrap.forms.fields.input.DateField;
import com.github.mlaursen.bootstrap.forms.fields.input.NumberField;
import com.betteru.accounts.database.AccountView;
import com.betteru.databasechoices.accounts.AccountChoice;
import com.betteru.databasechoices.accounts.Gender;
import com.betteru.databasechoices.accounts.Multiplier;
import com.betteru.databasechoices.accounts.UnitSystem;
import com.betteru.databasechoices.accounts.Weekday;

public class EditAccountForm extends HtmlForm {
	public static final String ACTION = "index.jsp";
	public static final String BIRTHDAY = "birthday", GENDER="gender", UNIT="unit", MULTIPLIER="multiplier",
			WEEKDAY="weekday", HEIGHT="height";
	public static final List<DropdownChoice> GENDERS = new Gender().getAllChoices(),
											 UNITS   = new UnitSystem().getAllChoices(),
											 MULTIPLIERS = new Multiplier().getAllChoices(),
											 WEEKDAYS = new Weekday().getAllChoices();
	public EditAccountForm(AccountView av) {
		super(ACTION);
		DateField bday = new DateField(BIRTHDAY);
		bday.setValue(av.getBirthday());
		bday.setCss("span2");
		
		Dropdown genders = new Dropdown(GENDER, GENDERS);
		genders.setValue(getKey(av.getGender(), GENDERS));
		
		Dropdown unitSystems = new Dropdown(UNIT, UNITS);
		unitSystems.setValue(getKey(av.getUnitSystem(), UNITS));
		
		NumberField height = new NumberField(HEIGHT);
		height.setValue(av.getHeight());
		height.setCss("span2");
		
		Dropdown multipliers = new Dropdown(MULTIPLIER, MULTIPLIERS);
		multipliers.setValue(getKey(av.getMultiplier(), MULTIPLIERS));
		
		Dropdown weekdays = new Dropdown(WEEKDAY, WEEKDAYS);
		weekdays.setValue(getKey(av.getWeekday(), WEEKDAYS));
		
		SubmitButton update = new SubmitButton("update");
		update.setValue("Update");
		this.addFields(ControlGroup.wrap(bday, genders, unitSystems, height, weekdays, multipliers, update));
	}
	
	public EditAccountForm(HttpServletRequest request, AccountView av) {
		this(av);
		updateValue(BIRTHDAY, request);
		updateValue(GENDER, request);
		updateValue(UNIT, request);
		updateValue(HEIGHT, request);
		updateValue(WEEKDAY, request);
		updateValue(MULTIPLIER, request);
	}
	
	public String getGender() {
		return getFieldValue(GENDER);
	}
	
	public String getWeekday() {
		return getFieldValue(WEEKDAY);
	}
	
	public String getUnitSystem() {
		return getFieldValue(UNIT);
	}
	
	public String getMultiplier() {
		return getFieldValue(MULTIPLIER);
	}
	
	public String getBirthday() {
		return this.getFieldValue(BIRTHDAY);
	}
	
	public String getHeight() {
		return getFieldValue(HEIGHT);
	}
	
	private String getKey(AccountChoice ac, List<DropdownChoice> choices) {
		String v = ac == null ? "0" : ac.getDropdownValue();
		for(DropdownChoice c : choices) {
			if(c.getDropdownValue().equalsIgnoreCase(v))
				return c.getDropdownKey();
		}
		return "";
	}
}
