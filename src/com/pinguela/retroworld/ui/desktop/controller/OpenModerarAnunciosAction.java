package com.pinguela.retroworld.ui.desktop.controller;

import javax.swing.Icon;
import javax.swing.ImageIcon;

import com.pinguela.retroworld.ui.desktop.RetroWorldWindow;
import com.pinguela.retroworld.ui.desktop.view.ModerarAnunciosView;

public class OpenModerarAnunciosAction extends BaseAction{
	
	public OpenModerarAnunciosAction() {
	}
	
	public OpenModerarAnunciosAction(String name) {
		super(name);
	}
	
	public OpenModerarAnunciosAction(String name, Icon icon) {
		super(name, icon);
	}
	
	@Override
	public void doAction() {
		ModerarAnunciosView view = new ModerarAnunciosView();
		view.setStart(new ModerarAnuncioPagedSearchAction(PagedSearchAction.START, view,"",
				new ImageIcon(RetroWorldWindow.class.getResource("/icons/icons8-arrow-left-22.png"))));
		view.setNext(new ModerarAnuncioPagedSearchAction(PagedSearchAction.NEXT, view, "", 
				new ImageIcon(RetroWorldWindow.class.getResource("/icons/icons8-arrow-22.png"))));
		view.setPrevious(new ModerarAnuncioPagedSearchAction(PagedSearchAction.PREVIOUS, view, "", 
				new ImageIcon(RetroWorldWindow.class.getResource("/icons/icons8-arrow-pointing-left-22.png"))));
		view.setEnd(new ModerarAnuncioPagedSearchAction(PagedSearchAction.END, view, "", 
				new ImageIcon(RetroWorldWindow.class.getResource("/icons/icons8-arrow-right-22.png"))));
		RetroWorldWindow window = RetroWorldWindow.getInstance();
		window.setCenterView(view);
	}
	
}
