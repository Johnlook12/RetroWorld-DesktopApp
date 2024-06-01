package com.pinguela.retroworld.ui.desktop.controller;

import javax.swing.Icon;
import javax.swing.JOptionPane;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.pinguela.DataException;
import com.pinguela.retroworld.model.Videojuego;
import com.pinguela.retroworld.service.VideojuegoService;
import com.pinguela.retroworld.service.impl.VideojuegoServiceImpl;
import com.pinguela.retroworld.ui.desktop.model.VideojuegoTableModel;
import com.pinguela.retroworld.ui.desktop.view.PaginatedSearchView;
import com.pinguela.retroworld.ui.desktop.view.VideojuegoDetailView;
import com.pinguela.retroworld.ui.desktop.view.VideojuegoSearchView;

public class UpdateVideojuegoAction extends BaseAction{
	
	private static Logger logger = LogManager.getLogger(UpdateVideojuegoAction.class);
	private VideojuegoDetailView view = null;
	private VideojuegoSearchView videojuegoView;
	private VideojuegoService videojuegoService;
	
	public UpdateVideojuegoAction(VideojuegoDetailView view, VideojuegoSearchView videojuegoView) {
		this(view, videojuegoView, null, null);
	}
	
	public UpdateVideojuegoAction(VideojuegoDetailView view, VideojuegoSearchView videojuegoView, String name) {
		this(view, videojuegoView, name, null);
	}
	
	public UpdateVideojuegoAction(VideojuegoDetailView view, VideojuegoSearchView videojuegoView, String name, Icon icon) {
		super(name, icon);
		this.view=view;
		this.videojuegoView=videojuegoView;
		initServices();
	}
	
	
	@Override
	public void doAction() {
		try {
			view.updateVideojuego();
			Videojuego videojuego = view.getVideojuego();
			if(videojuegoService.update(videojuego)) {
				logger.info("Videojuego con id: "+videojuego.getId()+" actualizado");
				JOptionPane.showMessageDialog(view, "Videojuego actualizado correctamente");
				VideojuegoTableModel model = new VideojuegoTableModel(videojuegoService.findBy(videojuegoView.getCriteria(), 
						videojuegoView.getCurrentPosition(), PaginatedSearchView.PAGE_SIZE).getPage());
				videojuegoView.setTableModel(model);
				view.updateView();
				view.showEditInterface(false);
			} else {
				logger.error("error al actualizar el videojuego con id: "+videojuego.getId());
				JOptionPane.showMessageDialog(view, "Error al actualizar el videojuego", "Error de actualización", JOptionPane.ERROR_MESSAGE, null);
			}
			
		}catch(DataException de) {
			logger.error(de.getMessage(), de);
		}
	}
	
	private void initServices() {
		videojuegoService = new VideojuegoServiceImpl();
	}
}
