package org.andia.texteditor;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Shell;

public class Bar {

	private Shell shell = null;

	private Menu bar = null;

	Item itemClass = null;

	public Bar(Shell shell) {
		this.shell = shell;
		bar = new Menu(shell, SWT.BAR);
	}

	void createMenuBar() {

		itemClass = new Item(shell);

		shell.setMenuBar(bar);

		// menu Item File
		MenuItem fileItem = new MenuItem(bar, SWT.CASCADE);
		fileItem.setText("File");
		fileItem.setMenu(itemClass.createMenuFile());

		// Menu Item Edit
		MenuItem editItem = new MenuItem(bar, SWT.CASCADE);
		editItem.setText("Edit");
		editItem.setMenu(itemClass.createEditMenu());

		// Menu Item View
		MenuItem viewItem = new MenuItem(bar, SWT.CASCADE);
		viewItem.setText("View");

		// Menu Item Search
		MenuItem searchItem = new MenuItem(bar, SWT.CASCADE);
		searchItem.setText("Search");

		// Menu Item Tool
		MenuItem toolsItem = new MenuItem(bar, SWT.CASCADE);
		toolsItem.setText("Tool");

		// Menu Item Documents
		MenuItem DocItem = new MenuItem(bar, SWT.CASCADE);
		DocItem.setText("Documents");

		// Menu Item Help
		MenuItem helpItem = new MenuItem(bar, SWT.CASCADE);
		helpItem.setText("Help");
	}
}
