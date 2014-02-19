package com_old.betteru.accounts.forms;

import javax.servlet.http.HttpServletRequest;

import com.github.mlaursen.bootstrap.forms.HtmlForm;
import com.github.mlaursen.bootstrap.forms.fields.ControlFieldGroup;
import com.github.mlaursen.bootstrap.forms.fields.ControlGroup;
import com.github.mlaursen.bootstrap.forms.fields.button.SubmitButton;
import com.github.mlaursen.bootstrap.forms.fields.input.PasswordField;
import com.github.mlaursen.bootstrap.forms.fields.input.TextField;
import com.github.mlaursen.bootstrap.forms.fields.link.Link;

public class LoginForm extends HtmlForm {

	public static final String ACTION = "index.jsp";
	public LoginForm() {
		super(ACTION);
		TextField username = new TextField("username");
		PasswordField password = new PasswordField("password");
		
		SubmitButton submit = new SubmitButton();
		submit.setValue("Login");
		submit.setCss("btn");
		
		Link create = new Link("create");
		create.setDataToggle();
		create.setHref("#create_account");
		create.setCss("btn btn-primary");
		create.setValue("Create Account");
		
		this.addFields(ControlGroup.wrap(username, password));
		this.addField(new ControlFieldGroup(submit, create));
	}
	
	public LoginForm(HttpServletRequest request) {
		this();
		updateValue("username", request);
		updateValue("password", request);
	}
	
}
