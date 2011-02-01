package org.andia.texteditor;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.printing.PrintDialog;
import org.eclipse.swt.printing.Printer;
import org.eclipse.swt.printing.PrinterData;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Shell;

public class MenuList {

	private Shell shell = null;
	private Menu bar = null;
	protected StyledText text;

	public MenuList(Shell shell) {
		this.shell = shell;
		bar = new Menu(shell, SWT.BAR);
	}

	/**
	 * Membuat StyledText tampilan editor
	 */
	void createStyledText() {
		text = new StyledText(shell, SWT.BORDER | SWT.MULTI | SWT.V_SCROLL
				| SWT.H_SCROLL);
		GridData spec = new GridData();
		spec.horizontalAlignment = GridData.FILL;
		spec.grabExcessHorizontalSpace = true;
		spec.verticalAlignment = GridData.FILL;
		spec.grabExcessVerticalSpace = true;
		text.setLayoutData(spec);
	}

	// ====================================================================
	// Membuat Menu Bar
	// ====================================================================
	void createMenuBar() {

		shell.setMenuBar(bar);

		// menu Item File
		MenuItem fileItem = new MenuItem(bar, SWT.CASCADE);
		fileItem.setText("File");
		fileItem.setMenu(createMenuFile());

		// Menu Item Edit
		MenuItem editItem = new MenuItem(bar, SWT.CASCADE);
		editItem.setText("Edit");
		editItem.setMenu(createEditMenu());

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

	// =====================================================================
	// Membuat Menu Item
	// =====================================================================

	/**
	 * Create Menu Item File
	 * 
	 * @return menu
	 */
	Menu createMenuFile() {
		Menu bar = shell.getMenuBar();
		Menu menu = new Menu(bar);

		// File->New
		MenuItem item = new MenuItem(menu, SWT.PUSH);
		item.setText("&New\t\tCtrl+N");
		item.setAccelerator(SWT.MOD1 + 'N');
		item.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent event) {
				shell.update();
			}
		});

		// File->Open
		item = new MenuItem(menu, SWT.PUSH);
		item.setText("&Open..\t\tCtrl+O");
		item.setAccelerator(SWT.MOD1 + 'O');
		item.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				final FileDialog dialog = new FileDialog(shell, SWT.OPEN);
				dialog.open();
				String filename = dialog.getFilterPath() + File.separator
						+ dialog.getFileName();
				File file = new File(filename);
				if (file.isFile()) {
					BufferedReader br;
					try {
						br = new BufferedReader(new FileReader(file));
						StringBuffer buf = new StringBuffer();
						String line = null;
						while ((line = br.readLine()) != null) {
							buf.append(line + '\n');
						}
						br.close();
						text.setText(buf.toString());
					} catch (FileNotFoundException e1) {
						e1.printStackTrace();
					} catch (IOException e2) {
						e2.printStackTrace();
					}
				}
			}
		});

		// File->Save
		item = new MenuItem(menu, SWT.PUSH);
		final FileDialog dialog = new FileDialog(shell, SWT.SAVE);
		dialog.setFilterNames(new String[] { "Text Files (*.txt)" });
		dialog.setFilterExtensions(new String[] { "*.txt" });
		dialog.setFilterPath("/home/dendy");
		item.setText("&Save..\t\tCtrl+S");
		item.setAccelerator(SWT.MOD1 + 'S');
		item.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {

				String fn = dialog.open();
				if (fn != null) {
					File file = new File(fn);

					FileWriter fileWriter;
					try {
						fileWriter = new FileWriter(file);
						fileWriter.write(text.getText());
						fileWriter.close();
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}
			}
		});

		new MenuItem(menu, SWT.SEPARATOR);

		// File->Print
		item = new MenuItem(menu, SWT.PUSH);
		item.setText("&Print..\t\tCtrl+P");
		item.setAccelerator(SWT.MOD1 + 'P');
		final PrintDialog printDialog = new PrintDialog(shell, SWT.NONE);
		item.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				PrinterData printerData = printDialog.open();
				if (!(printerData == null)) {
					Printer p = new Printer(printerData);
					p.startJob("PrintJob");
					p.startPage();
					Rectangle trim = p.computeTrim(0, 0, 0, 0);
					Point dpi = p.getDPI();
					int leftMargin = dpi.x + trim.x;
					int topMargin = dpi.y / 2 + trim.y;
					GC gc = new GC(p);
					Font font = gc.getFont();
					String printText = text.getText();
					gc.drawString(printText, leftMargin,
							topMargin + font.getFontData()[0].getHeight());
					p.endPage();
					gc.dispose();
					p.endJob();
					p.dispose();
				}
			}
		});

		new MenuItem(menu, SWT.SEPARATOR);

		// File->Quit
		item = new MenuItem(menu, SWT.PUSH);
		item.setText("&Quit\t\tCtrl+Q");
		item.setAccelerator(SWT.MOD1 + 'Q');
		item.addListener(SWT.Selection, new Listener() {
			public void handleEvent(Event event) {
				shell.close();
			}
		});

		return menu;
	}

	/**
	 * Create Menu Item File
	 * 
	 * @return
	 * 
	 * @return menu
	 */
	Menu createEditMenu() {
		Menu bar = shell.getMenuBar();
		Menu menu = new Menu(bar);

		MenuItem item = new MenuItem(menu, SWT.PUSH);

		// File->Undo
		item.setText("&Undo\t\tCtrl+Z");
		item.setAccelerator(SWT.MOD1 + 'Z');
		item.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent event) {
				LinkedList<String> undoStack = new LinkedList<String>();
				LinkedList<String> redoStack = new LinkedList<String>();
				if (undoStack.size() > 0) {
					String lastEdit = (String) undoStack.remove(0);
					int editLength = lastEdit.length();
					String currText = text.getText();
					int startReplaceIndex = currText.length() - editLength;
					text.replaceTextRange(startReplaceIndex, editLength, "");
					redoStack.add(0, lastEdit);
				}
			}
		});

		// File->Rendo
		item = new MenuItem(menu, SWT.PUSH);
		item.setText("&Rendo\t\tCtrl+R");
		item.setAccelerator(SWT.MOD1 + 'R');
		item.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent event) {
				text.cut();
			}
		});

		new MenuItem(menu, SWT.SEPARATOR);

		// File->Cut
		item = new MenuItem(menu, SWT.PUSH);
		item.setText("&Cut\t\tCtrl+X");
		item.setAccelerator(SWT.MOD1 + 'X');
		item.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent event) {
				text.cut();
			}
		});

		// File->Copy
		item = new MenuItem(menu, SWT.PUSH);
		item.setText("&Cut\t\tCtrl+C");
		item.setAccelerator(SWT.MOD1 + 'X');
		item.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent event) {
				text.copy();
			}
		});

		// File->Paste
		item = new MenuItem(menu, SWT.PUSH);
		item.setText("&Paste\t\tCtrl+V");
		item.setAccelerator(SWT.MOD1 + 'V');
		item.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent event) {
				text.paste();
			}
		});

		new MenuItem(menu, SWT.SEPARATOR);

		// File->SelectAll
		item = new MenuItem(menu, SWT.PUSH);
		item.setText("&SelectAll\t\tCtrl+A");
		item.setAccelerator(SWT.MOD1 + 'A');
		item.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent event) {
				text.selectAll();
			}
		});

		return menu;
	}
}
