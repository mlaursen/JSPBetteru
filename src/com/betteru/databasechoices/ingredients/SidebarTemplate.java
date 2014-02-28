/**
 * 
 */
package com.betteru.databasechoices.ingredients;

import com.github.mlaursen.bootstrap.sidebar.SidebarItemList;
import com.github.mlaursen.bootstrap.sidebar.SidebarItemable;
import com.github.mlaursen.database.objects.MyResultRow;
import com.github.mlaursen.database.objects.ObjectManager;

/**
 * @author mikkel.laursen
 *
 */
public class SidebarTemplate extends Template implements SidebarItemable {

	/**
	 * 
	 */
	public SidebarTemplate() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param primaryKey
	 */
	public SidebarTemplate(String primaryKey) {
		super(primaryKey);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param name
	 * @param id
	 */
	public SidebarTemplate(String name, int id) {
		super(name, id);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param r
	 */
	public SidebarTemplate(MyResultRow r) {
		super(r);
		// TODO Auto-generated constructor stub
	}

	/**
	 * 
	 * @return
	 */
	public SidebarItemList<SidebarItemable> getSidebarList() {
		String n = getClass().getSimpleName();
		n = n.equals("Brand") ? "Brands" : "Categories";
		return new SidebarItemList(n.toLowerCase(), n, n.toLowerCase(), new ObjectManager(this.getClass()).getAll(getClass()));
	}

	/* (non-Javadoc)
	 * @see com.github.mlaursen.bootstrap.sidebar.SidebarItemable#getName()
	 */
	@Override
	public String getName() {
		return primaryKey;
	}
}
