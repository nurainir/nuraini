package org.andia.texteditor;

import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

public class OpenShell {

	private Shell shell = null;

	Bar bar = null;

	Item itemClass = null;

	/**
	 * Membuat tampilan display
	 * 
	 * @param display
	 */
	void createShell(Display display) {
		shell = new Shell(display);
		shell.setText("SWT NOTEPAD");
		GridLayout layout = new GridLayout();
		layout.numColumns = 1;
		shell.setLayout(layout);
	}

	/**
	 * fungsi membuat tampilan dengan memanggil semua method yang ada di class
	 * 
	 * 
	 * @return shell
	 */
	public Shell open(Display display) {
		createShell(display);

		bar = new Bar(shell);
		bar.createMenuBar();

		itemClass = new Item(shell);
		itemClass.createToolBar(display);

		itemClass.createStyledText();

		shell.setSize(700, 500);
		shell.open();
		return shell;
	}

}
