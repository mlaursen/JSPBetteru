package com.betteru.accounts.forms;

import javax.servlet.http.HttpServletRequest;

import com.github.mlaursen.bootstrap.forms.HtmlForm;
import com.github.mlaursen.bootstrap.forms.fields.ControlGroup;
import com.github.mlaursen.bootstrap.forms.fields.Errorable;
import com.github.mlaursen.bootstrap.forms.fields.input.PasswordField;
import com.github.mlaursen.bootstrap.forms.fields.input.TextField;

public class CreateAccountForm extends HtmlForm {

	private static final String ACTION = "index.jsp";//"CreateAccountServlet";
	public CreateAccountForm() {
		super(ACTION);
		getMiddlewareToken().setName("createmiddlewaretoken");
		TextField username = new TextField("username");
		username.setMaxlength(60);
		username.setMinlength(3);
		
		PasswordField password = new PasswordField("password");
		password.setMinlength(4);
		
		PasswordField passConf = new PasswordField("confirm_password");
		passConf.setMinlength(4);
		
		this.addFields(ControlGroup.wrap(username, password, passConf));
		setModalName("create_account");
	}
	
	public CreateAccountForm(HttpServletRequest request) {
		this();
		updateValue("username", request);
		updateValue("password", request);
		updateValue("confirm_password", request);
	}
	
	@Override
	public boolean isValid() {
		boolean valid = super.isValid();
		String err = "Password fields must match.";
		Errorable pass = (Errorable) this.getField("password");
		Errorable conf = (Errorable) this.getField("confirm_password");
		if(!pass.getValue().equals(conf.getValue())) {
			valid = false;
			pass.addError(err);
			conf.addError(err);
			this.updateFields(pass, conf);
		}
		return valid;
	}

}
