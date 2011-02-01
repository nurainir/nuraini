package org.andia.texteditor;

/*
 * Program ini untuk text editor yang merefrensi ke GEdit
 * @author : Aryandie (dendy)
 * @since 1 Febuary 2011
 * @license : GPL
 * @libary swt.jar for linux
 */
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
