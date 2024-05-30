package com.pinguela.retroworld.ui.desktop.controller;

import java.io.File;
import java.util.List;

import javax.swing.Icon;

import com.pinguela.retroworld.service.ImageService;
import com.pinguela.retroworld.service.impl.ImageServiceImpl;
import com.pinguela.retroworld.ui.desktop.dialog.CreateAnuncioDialog;

public class AddAnuncioImageAction extends BaseAction{

	private ImageService imageService;
	private CreateAnuncioDialog dialog;
	
	public AddAnuncioImageAction(CreateAnuncioDialog dialog) {
		this.dialog=dialog;
		initServices();
	}
	
	public AddAnuncioImageAction(CreateAnuncioDialog dialog, String name) {
		super(name);
		this.dialog=dialog;
		initServices();
	}
	
	public AddAnuncioImageAction(CreateAnuncioDialog dialog, String name, Icon icon) {
		 super(name, icon);
		 this.dialog=dialog;
		 initServices();
	}
	
	@Override
	public void doAction() {
		List<File> imageFiles = dialog.getSelectedImages();
		imageService.saveAnuncioImages(dialog.getAnuncio().getId(), imageFiles);
	}
	
	private void initServices() {
		imageService = new ImageServiceImpl();
	}

}
