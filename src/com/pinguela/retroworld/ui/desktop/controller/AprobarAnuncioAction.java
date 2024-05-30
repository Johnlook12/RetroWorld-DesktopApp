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

public class AprobarAnuncioAction extends BaseAction{

	private static Logger logger = LogManager.getLogger(AprobarAnuncioAction.class);

	private ModerarAnunciosView view;
	private AnuncioService anuncioService;

	public AprobarAnuncioAction(ModerarAnunciosView view) {
		this.view=view;
		initServices();
	}

	public AprobarAnuncioAction(ModerarAnunciosView view, String name) {
		super(name);
		this.view=view;
		initServices();
	}

	public AprobarAnuncioAction(ModerarAnunciosView view, String name, Icon icon) {
		super(name, icon);
		this.view=view;
		initServices();
	}

	@Override
	public void doAction() {
		try {
			int filaSeleccionada=view.getTableResults().getSelectedRow();
			Anuncio anuncio = (Anuncio) view.getTableResults().getModel().getValueAt(filaSeleccionada, 0);
			int answer = JOptionPane.showConfirmDialog(view, "¿Aprobar anuncio?", "Confirmar aprobar anuncio", JOptionPane.YES_NO_OPTION);
			if(answer == JOptionPane.YES_OPTION) {
				anuncio.setIdEstadoAnuncio(Anuncio.ESTADO_ACTIVO);
				if(anuncioService.update(anuncio, null)) {
					logger.info("Anuncio con id: "+anuncio.getId()+" aprobado");
					JOptionPane.showMessageDialog(view, "Anuncio aprobado");
					Results<Anuncio> results = anuncioService.findBy(view.getCriteria(), view.getCurrentPosition(), view.PAGE_SIZE);
					view.setResults(results);
					view.updateView();
				} else {
					logger.error("Error al aprobar el anuncio con id: "+anuncio.getId());
					JOptionPane.showMessageDialog(view, "No se pudo aprobar el anuncio", "Error al aprobar", JOptionPane.ERROR_MESSAGE);
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
