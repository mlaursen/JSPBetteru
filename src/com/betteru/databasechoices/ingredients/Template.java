/**
 * 
 */
package com.betteru.databasechoices.ingredients;

import java.util.ArrayList;
import java.util.List;

import com.github.mlaursen.bootstrap.forms.fields.DropdownChoice;
import com.github.mlaursen.bootstrap.sidebar.SidebarItemList;
import com.github.mlaursen.bootstrap.sidebar.SidebarItemable;
import com.github.mlaursen.database.objects.DatabaseObject;
import com.github.mlaursen.database.objects.MyResultRow;
import com.github.mlaursen.database.objecttypes.GetAllable;
import com.github.mlaursen.database.objecttypes.Getable;
import com_old.betteru.ingredients.database.Brand;

/**
 * @author mikkel.laursen
 *
 */
public abstract class Template extends DatabaseObject implements Getable, GetAllable, DropdownChoice, SidebarItemable {
	{ setPrimaryKeyName("name"); }
	
	protected int id;
	public Template() { }

	/**
	 * @param primaryKey
	 */
	public Template(String primaryKey) {
		super();
		this.primaryKey = primaryKey;
	}

	public Template(String name, int id) {
		this(name);
		this.id = id;
	}

	/**
	 * @param r
	 */
	public Template(MyResultRow r) {
		super();
		setAll(r);
	}

	/* (non-Javadoc)
	 * @see com.github.mlaursen.bootstrap.sidebar.SidebarItemable#getName()
	 */
	@Override
	public String getName() {
		return primaryKey;
	}

	/* (non-Javadoc)
	 * @see com.github.mlaursen.bootstrap.forms.fields.DropdownChoice#getDropdownValue()
	 */
	@Override
	public String getDropdownValue() {
		return primaryKey;
	}

	/* (non-Javadoc)
	 * @see com.github.mlaursen.bootstrap.forms.fields.DropdownChoice#getDropdownKey()
	 */
	@Override
	public String getDropdownKey() {
		return id + "";
	}

	/* (non-Javadoc)
	 * @see com.github.mlaursen.bootstrap.forms.fields.DropdownChoice#setDropdownKey(int)
	 */
	@Override
	public void setDropdownKey(int k) {
		id = k;
	}
	
	/* (non-Javadoc)
	 * @see com.github.mlaursen.bootstrap.forms.fields.DropdownChoice#getAllChoices()
	 */
	@Override
	public List<DropdownChoice> getAllChoices() {
		List<DropdownChoice> choices = new ArrayList<DropdownChoice>();
		if(this.getClass().getSimpleName().equals("Brand")) {
			choices.add(new Brand("New Brand"));
		}
		choices.addAll((List<DropdownChoice>) this.getAll(this.getClass()));
		for(int i = 0; i < choices.size(); i++) {
			choices.get(i).setDropdownKey(i);
		}
		return choices;
	}
	
	@Override
	public String toString() {
		return primaryKey;
	}
	/**
	 * 
	 * @return
	 */
	public SidebarItemList<SidebarItemable> getSidebarList() {
		String n = getClass().getSimpleName();
		n = n.equals("Brand") ? "Brands" : "Categories";
		return new SidebarItemList(n.toLowerCase(), n, n.toLowerCase(), getAll(getClass()));
	}
}
