package com.pinguela.retroworld.ui.desktop.controller;

import javax.swing.Icon;
import javax.swing.JDialog;

import com.pinguela.retroworld.model.Anuncio;
import com.pinguela.retroworld.service.ImageService;
import com.pinguela.retroworld.service.impl.ImageServiceImpl;
import com.pinguela.retroworld.ui.desktop.dialog.RWDialog;
import com.pinguela.retroworld.ui.desktop.utils.SwingUtils;
import com.pinguela.retroworld.ui.desktop.view.AnuncioDetailView;
import com.pinguela.retroworld.ui.desktop.view.PaginatedSearchView;

public class OpenAnuncioDetailAction extends BaseAction{
	
	private PaginatedSearchView view;
	
	private ImageService imageService;	
	
	
	public OpenAnuncioDetailAction(PaginatedSearchView view) {
		this(view, null, null);
	}
	
	public OpenAnuncioDetailAction(PaginatedSearchView view, String name) {
		this(view, name, null);
	}
	
	public OpenAnuncioDetailAction(PaginatedSearchView view, String name, Icon icon) {
		super(name, icon);
		this.view=view;
		initServices();
	}
	
	@Override
	public void doAction() {
		int filaSeleccionada = view.getTableResults().getSelectedRow();
		Object selectedObject =view.getTableResults().getModel().getValueAt(filaSeleccionada, 0);
		JDialog detailDialog = new RWDialog();
		Anuncio anuncio = (Anuncio) selectedObject;
		AnuncioDetailView detailView = new AnuncioDetailView(anuncio,view);
		detailView.setImages(imageService.getAnuncioImages(anuncio.getId()));
		detailView.printImages();
		detailDialog.getContentPane().add(detailView);
		SwingUtils.centerOnScreen(detailDialog);
		detailDialog.setVisible(true);
	}
	
	private void initServices() {
		imageService = new ImageServiceImpl();
	}
}
