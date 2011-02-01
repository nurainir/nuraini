package org.andia.texteditor;

import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

public class OpenShell {

	MenuList menuList = null;
	ToolList toolList = null;
	StatusBar statusBar = null;
	private Shell shell = null;
	

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

		menuList = new MenuList(shell);
		menuList.createMenuBar();

		toolList = new ToolList(shell);
		toolList.createToolBar(display);

		menuList.createStyledText();

		statusBar = new StatusBar(shell);
		statusBar.createStatusPanel(display);
		shell.setSize(700, 500);
		shell.open();
		return shell;
	}

}
