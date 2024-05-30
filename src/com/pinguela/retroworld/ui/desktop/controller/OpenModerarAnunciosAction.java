package com.pinguela.retroworld.ui.desktop.controller;

import javax.swing.Icon;

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
		RetroWorldWindow window = RetroWorldWindow.getInstance();
		window.setCenterView(view);
	}
	
}
