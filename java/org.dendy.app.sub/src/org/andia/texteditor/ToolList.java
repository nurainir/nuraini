package org.andia.texteditor;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;

public class ToolList{

	private Shell shell = null;


	public ToolList(Shell shell) {
		this.shell = shell;
	}

	void createToolBar(Display display) {

		Image newOpen = new Image(display, "/home/dendy/image/document-new.png");
		Image open = new Image(display, "/home/dendy/image/document-open.png");
		Image save = new Image(display, "/home/dendy/image/document-save.png");
		Image print = new Image(display, "/home/dendy/image/print.png");
		Image undo = new Image(display, "/home/dendy/image/edit-undo.png");
		Image redo = new Image(display, "/home/dendy/image/edit-redo.png");
		Image cut = new Image(display, "/home/dendy/image/edit-cut.png");
		Image copy = new Image(display, "/home/dendy/image/copy.png");
		Image paste = new Image(display, "/home/dendy/image/edit-paste.png");
		Image search = new Image(display, "/home/dendy/image/search.png");

		ToolBar bar = new ToolBar(shell, SWT.FLAT | SWT.HORIZONTAL);
		ToolItem item = new ToolItem(bar, SWT.PUSH);
		item.setImage(newOpen);
		item.setToolTipText("New");
		item.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				shell.update();
			}
		});

		ToolItem itemOpen = new ToolItem(bar, SWT.PUSH | SWT.FLAT);
		itemOpen.setImage(open);
		itemOpen.setToolTipText("Open");
		itemOpen.addSelectionListener(new SelectionAdapter() {
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
						// text.setText(buf.toString());
					} catch (FileNotFoundException e1) {
						e1.printStackTrace();
					} catch (IOException e2) {
						e2.printStackTrace();
					}
				}
			}
		});

		ToolItem itemSave = new ToolItem(bar, SWT.PUSH);
		itemSave.setImage(save);
		itemSave.setToolTipText("Save");

		new ToolItem(bar, SWT.SEPARATOR);

		ToolItem itemPrint = new ToolItem(bar, SWT.PUSH);
		itemPrint.setImage(print);
		itemPrint.setToolTipText("Print");

		new ToolItem(bar, SWT.SEPARATOR);

		ToolItem itemUndo = new ToolItem(bar, SWT.PUSH);
		itemUndo.setImage(undo);
		itemUndo.setToolTipText("Undo");

		ToolItem itemRendo = new ToolItem(bar, SWT.PUSH);
		itemRendo.setImage(redo);
		itemRendo.setToolTipText("Redo");

		new ToolItem(bar, SWT.SEPARATOR);

		ToolItem itemCut = new ToolItem(bar, SWT.PUSH);
		itemCut.setImage(cut);
		itemCut.setToolTipText("Cut");

		ToolItem itemCopy = new ToolItem(bar, SWT.PUSH);
		itemCopy.setImage(copy);
		itemCopy.setToolTipText("Copy");

		ToolItem itemPaste = new ToolItem(bar, SWT.PUSH);
		itemPaste.setImage(paste);
		itemPaste.setToolTipText("Paste");

		new ToolItem(bar, SWT.SEPARATOR);

		ToolItem itemSearch = new ToolItem(bar, SWT.PUSH);
		itemSearch.setImage(search);
		itemSearch.setToolTipText("Search");
	}

}
