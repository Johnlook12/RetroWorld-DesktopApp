package com.pinguela.retroworld.ui.desktop.controller;

import java.io.File;
import java.util.List;

import javax.swing.Icon;

import com.pinguela.retroworld.service.ImageService;
import com.pinguela.retroworld.service.impl.ImageServiceImpl;
import com.pinguela.retroworld.ui.desktop.dialog.CreateVideojuegoDialog;

public class AddVideojuegoImageAction extends BaseAction{
	private ImageService imageService;
	private CreateVideojuegoDialog dialog;
	
	public AddVideojuegoImageAction(CreateVideojuegoDialog dialog) {
		this.dialog=dialog;
		initServices();
	}

	public AddVideojuegoImageAction(CreateVideojuegoDialog dialog, String name) {
		super(name);
		this.dialog=dialog;
		initServices();
	}

	public AddVideojuegoImageAction(CreateVideojuegoDialog dialog, String name, Icon icon) {
		super(name,icon);
		this.dialog=dialog;
		initServices();
	}

	@Override
	public void doAction() {
		List<File> imageFiles = dialog.getSelectedImages();
		imageService.saveVideojuegoImages(dialog.getVideojuegoId(), imageFiles);
	}
	
	private void initServices() {
		imageService = new ImageServiceImpl();
	}
}
