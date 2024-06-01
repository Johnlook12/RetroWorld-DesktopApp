package com.pinguela.retroworld.ui.desktop.controller;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JDialog;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.pinguela.retroworld.model.Anuncio;
import com.pinguela.retroworld.model.Videojuego;
import com.pinguela.retroworld.service.GeneroService;
import com.pinguela.retroworld.service.IdiomaService;
import com.pinguela.retroworld.service.ImageService;
import com.pinguela.retroworld.service.PlataformaService;
import com.pinguela.retroworld.service.impl.GeneroServiceImpl;
import com.pinguela.retroworld.service.impl.IdiomaServiceImpl;
import com.pinguela.retroworld.service.impl.ImageServiceImpl;
import com.pinguela.retroworld.service.impl.PlataformaServiceImpl;
import com.pinguela.retroworld.ui.desktop.dialog.RWDialog;
import com.pinguela.retroworld.ui.desktop.utils.SwingUtils;
import com.pinguela.retroworld.ui.desktop.view.PaginatedSearchView;

public class ShowDetailAction extends AbstractAction{
	
	private static Logger logger = LogManager.getLogger(ShowDetailAction.class);
	
	private PaginatedSearchView view;
	private IdiomaService idiomaService;
	private GeneroService generoService;
	private PlataformaService plataformaService;
	private ImageService imageService;
	
	public ShowDetailAction(PaginatedSearchView view) {
		this.view=view;
		initServices();
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		int filaSeleccionada = view.getTableResults().getSelectedRow();
		Object selectedObject =view.getTableResults().getModel().getValueAt(filaSeleccionada, 0);
		JDialog detailDialog = new RWDialog();
		if(selectedObject instanceof Anuncio) {
			
			
		} else if(selectedObject instanceof Videojuego) {
			
		}
		SwingUtils.centerOnScreen(detailDialog);
		detailDialog.setVisible(true);
	}
	
	private void initServices() {
		idiomaService = new IdiomaServiceImpl();
		generoService = new GeneroServiceImpl();
		plataformaService = new PlataformaServiceImpl();
		imageService = new ImageServiceImpl();
	}

}
