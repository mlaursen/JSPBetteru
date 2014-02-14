package com.betteru.accounts.forms;

import javax.servlet.http.HttpServletRequest;

import com.betteru.accounts.AccountView;
import com.betteru.accounts.Gender;
import com.betteru.accounts.Multiplier;
import com.betteru.accounts.UnitSystem;
import com.betteru.accounts.Weekday;
import com.github.mlaursen.bootstrap.forms.HtmlForm;
import com.github.mlaursen.bootstrap.forms.fields.ControlGroup;
import com.github.mlaursen.bootstrap.forms.fields.Dropdown;
import com.github.mlaursen.bootstrap.forms.fields.button.SubmitButton;
import com.github.mlaursen.bootstrap.forms.fields.input.DateField;
import com.github.mlaursen.bootstrap.forms.fields.input.NumberField;

public class EditAccountForm extends HtmlForm {
	public static final String ACTION = "index.jsp";
	public static final String BIRTHDAY = "birthday", GENDER="gender", UNIT="unit", MULTIPLIER="multiplier",
			WEEKDAY="weekday", HEIGHT="height";
	
	public EditAccountForm(AccountView av) {
		super(ACTION);
		DateField bday = new DateField(BIRTHDAY);
		bday.setValue(av.getBirthday());
		bday.setCss("span2");
		
		Dropdown genders = new Dropdown(GENDER, new Gender().getAllChoices());
		genders.setValue(av.getGender().getDropdownKey());
		
		Dropdown unitSystems = new Dropdown(UNIT, new UnitSystem().getAllChoices());
		unitSystems.setValue(av.getUnitSystem().getDropdownKey());
		
		NumberField height = new NumberField(HEIGHT);
		height.setValue(av.getHeight());
		height.setCss("span2");
		
		Dropdown multipliers = new Dropdown(MULTIPLIER, new Multiplier().getAllChoices());
		multipliers.setValue(av.getMultiplier().getDropdownKey());
		
		Dropdown weekdays = new Dropdown(WEEKDAY, new Weekday().getAllChoices());
		weekdays.setValue(av.getWeekday().getDropdownKey());
		
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
}
