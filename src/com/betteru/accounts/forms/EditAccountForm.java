package com.betteru.accounts.forms;

import javax.servlet.http.HttpServletRequest;

import com.betteru.accounts.AccountView;
import com.betteru.utils.Util;
import com.github.mlaursen.bootstrap.forms.HtmlForm;
import com.github.mlaursen.bootstrap.forms.fields.ControlGroup;
import com.github.mlaursen.bootstrap.forms.fields.Dropdown;
import com.github.mlaursen.bootstrap.forms.fields.button.SubmitButton;
import com.github.mlaursen.bootstrap.forms.fields.input.DateField;
import com.github.mlaursen.bootstrap.forms.fields.input.NumberField;

public class EditAccountForm extends HtmlForm {
	public static final String ACTION = "index.jsp";
	public static final String BIRTHDAY = "birthday", GENDER="gender", UNIT="unit", MULTIPLIER="multiplier",
			RECALC="recalc", HEIGHT="height";
	
	public EditAccountForm(AccountView av) {
		super(ACTION);
	}
	
	public EditAccountForm(HttpServletRequest request, AccountView av) {
		this(av);
		updateValue(BIRTHDAY, request);
		updateValue(GENDER, request);
		updateValue(UNIT, request);
		updateValue(HEIGHT, request);
		updateValue(RECALC, request);
		updateValue(MULTIPLIER, request);
	}
	/**
	
	public static final String BIRTHDAY = "birthday", GENDER="gender", UNIT="unit", MULTIPLIER="multiplier",
								RECALC="recalc", HEIGHT="height";
	public EditAccountForm(AccountView_Old av) {
		super(ACTION);
		Account_Old a = new Account_Old(av);
		AccountSetting_Old as = new AccountSetting_Old(av);

		DateField birthday = new DateField(BIRTHDAY);
		birthday.setValue(Util.sqlDateToString(a.getBirthday()));
		birthday.setCss("span2");
		Dropdown genders = new Dropdown(GENDER, new GenderChoice().getAll());
		genders.setValue(a.getGender().getKey());
		
		Dropdown units = new Dropdown("unit", new UnitChoice().getAll());
		units.setValue(a.getUnit().getKey());
		
		//String h = as.getHeight();
		NumberField height = new NumberField(HEIGHT);
		height.setValue(as.getHeight()+"");
		height.setCss("span2");
		
		Dropdown recalc = new Dropdown(RECALC, new RecalcChoice().getAll());
		recalc.setLabel("Recalculate Day");
		recalc.setValue(as.getRecalc().getKey());
		
		Dropdown multiplier = new Dropdown(MULTIPLIER, new MultiplierChoice().getAll());
		multiplier.setLabel("Activity Multiplier");
		multiplier.setValue(as.getMultiplier().getKey());
		
		SubmitButton update = new SubmitButton("update");
		update.setValue("Update");
		this.addFields(ControlGroup.wrap(birthday, genders, units, height, recalc, multiplier, update));
	}
	public EditAccountForm(HttpServletRequest request, AccountView_Old av) {
		this(av);
		updateValue(BIRTHDAY, request);
		updateValue(GENDER, request);
		updateValue(UNIT, request);
		updateValue(HEIGHT, request);
		updateValue(RECALC, request);
		updateValue(MULTIPLIER, request);
	}
	
	*/
}
