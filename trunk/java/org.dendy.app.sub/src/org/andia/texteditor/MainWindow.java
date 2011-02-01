package org.andia.texteditor;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

public class MainWindow {

	public static void main(String[] args) {

		Display display = new Display();
		OpenShell editor = new OpenShell();

		Shell shell = editor.open(display);
		while (!shell.isDisposed())
			if (!display.readAndDispatch())
				display.sleep();
		display.dispose();

	}
}
