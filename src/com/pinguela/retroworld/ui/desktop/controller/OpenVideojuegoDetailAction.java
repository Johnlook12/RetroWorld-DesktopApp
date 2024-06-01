package com.pinguela.retroworld.ui.desktop.controller;

import java.util.List;

import javax.swing.Icon;
import javax.swing.JDialog;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.pinguela.DataException;
import com.pinguela.retroworld.model.Genero;
import com.pinguela.retroworld.model.Idioma;
import com.pinguela.retroworld.model.Plataforma;
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
import com.pinguela.retroworld.ui.desktop.view.VideojuegoDetailView;
import com.pinguela.retroworld.ui.desktop.view.VideojuegoSearchView;

public class OpenVideojuegoDetailAction extends BaseAction{
	
	private static Logger logger = LogManager.getLogger(OpenVideojuegoDetailAction.class);
	private VideojuegoSearchView view;
	
	private IdiomaService idiomaService;
	private GeneroService generoService;
	private PlataformaService plataformaService;
	private ImageService imageService;
	
	public OpenVideojuegoDetailAction(VideojuegoSearchView view) {
		this.view=view;
		initServices();
	}
	
	public OpenVideojuegoDetailAction(String name) {
		super(name);
		this.view=view;
		initServices();
	}
	
	public OpenVideojuegoDetailAction(String name, Icon icon) {
		super(name, icon);
		this.view=view;
		initServices();
	}
	
	@Override
	public void doAction() {
		int filaSeleccionada = view.getTableResults().getSelectedRow();
		Object selectedObject =view.getTableResults().getModel().getValueAt(filaSeleccionada, 0);
		Videojuego videojuego = (Videojuego) selectedObject;
		VideojuegoDetailView detailView = new VideojuegoDetailView(videojuego,view);
		JDialog detailDialog = new RWDialog();
		try {
			List<Idioma>idiomas=idiomaService.findByVideojuego(videojuego.getId());
			detailView.setIdiomas(idiomas);
			List<Genero>generos=generoService.findByVideojuego(videojuego.getId());
			detailView.setGeneros(generos);
			List<Plataforma>plataformas=plataformaService.findByVideojuego(videojuego.getId());
			detailView.setPlataformas(plataformas);
			detailDialog.getContentPane().add(detailView);
			SwingUtils.centerOnScreen(detailDialog);
			detailDialog.setVisible(true);
		}catch(DataException de) {
			logger.error(de.getMessage(), de);
		}
	}
	
	private void initServices() {
		idiomaService = new IdiomaServiceImpl();
		generoService = new GeneroServiceImpl();
		plataformaService = new PlataformaServiceImpl();
		imageService = new ImageServiceImpl();
	}

}
