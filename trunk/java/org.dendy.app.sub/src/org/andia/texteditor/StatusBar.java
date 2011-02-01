package org.andia.texteditor;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;

public class StatusBar {

	private Shell shell = null;
	private Label statusLabel = null;

	public StatusBar(Shell shell) {
		this.shell = shell;
	}

	/**
	 * Add a status bar to the panel.
	 * 
	 * @param shell
	 */
	void createStatusPanel(Display display) {
		Composite statusGroup = new Composite(shell, SWT.NONE);
		statusGroup.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		GridLayout gridLayout = new GridLayout();
		gridLayout.marginHeight = 0;
		gridLayout.marginWidth = 0;
		gridLayout.numColumns = 3;
		statusGroup.setLayout(gridLayout);
		statusLabel = new Label(statusGroup, SWT.BORDER);
		GridData labelGridData = new GridData();
		labelGridData.heightHint = 15;
		labelGridData.horizontalAlignment = GridData.FILL;
		labelGridData.grabExcessHorizontalSpace = true;
		labelGridData.horizontalSpan = 2;
		statusLabel.setLayoutData(labelGridData);
	}

}
