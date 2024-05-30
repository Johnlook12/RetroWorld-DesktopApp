package com.pinguela.retroworld.ui.desktop.controller;

import javax.swing.Icon;
import javax.swing.JOptionPane;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.pinguela.retroworld.dao.DataException;
import com.pinguela.retroworld.model.Anuncio;
import com.pinguela.retroworld.model.Results;
import com.pinguela.retroworld.service.AnuncioService;
import com.pinguela.retroworld.service.impl.AnuncioServiceImpl;
import com.pinguela.retroworld.ui.desktop.view.ModerarAnunciosView;

public class RechazarAnuncioAction extends BaseAction{

	private static Logger logger = LogManager.getLogger(RechazarAnuncioAction.class);

	private ModerarAnunciosView view;
	private AnuncioService anuncioService;

	public RechazarAnuncioAction(ModerarAnunciosView view) {
		this.view=view;
		initServices();
	}

	public RechazarAnuncioAction(ModerarAnunciosView view, String name) {
		super(name);
		this.view=view;
		initServices();
	}

	public RechazarAnuncioAction(ModerarAnunciosView view, String name, Icon icon) {
		super(name, icon);
		this.view=view;
		initServices();
	}

	@Override
	public void doAction() {
		try {
			int filaSeleccionada=view.getTableResults().getSelectedRow();
			Anuncio anuncio = (Anuncio) view.getTableResults().getModel().getValueAt(filaSeleccionada, 0);
			int answer = JOptionPane.showConfirmDialog(view, "¿Rechazar anuncio?", "Confirmar rechazar anuncio", JOptionPane.YES_NO_OPTION);
			if(answer == JOptionPane.YES_OPTION) {
				anuncio.setIdEstadoAnuncio(Anuncio.ESTADO_FINALIZADO);
				if(anuncioService.update(anuncio, null)) {
					logger.info("Anuncio con id: "+anuncio.getId()+" rechazado");
					JOptionPane.showMessageDialog(view, "Anuncio rechazado");
					Results<Anuncio> results = anuncioService.findBy(view.getCriteria(), view.getCurrentPosition(), view.PAGE_SIZE);
					view.setResults(results);
					view.updateView();
				} else {
					logger.error("Error al rechazar el anuncio con id: "+anuncio.getId());
					JOptionPane.showMessageDialog(view, "No se pudo rechazar el anuncio", "Error al rechazar", JOptionPane.ERROR_MESSAGE);
				}
			}
		}catch(DataException de) {
			logger.error(de.getMessage(), de);
		}
	}

	private void initServices() {
		anuncioService = new AnuncioServiceImpl();
	}

}
