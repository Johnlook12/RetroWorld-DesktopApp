package com.pinguela.retroworld.ui.desktop.controller;

import javax.swing.Icon;
import javax.swing.JOptionPane;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.pinguela.DataException;
import com.pinguela.retroworld.model.Anuncio;
import com.pinguela.retroworld.service.AnuncioService;
import com.pinguela.retroworld.service.impl.AnuncioServiceImpl;
import com.pinguela.retroworld.ui.desktop.model.AnuncioTableModel;
import com.pinguela.retroworld.ui.desktop.view.AnuncioSearchView;
import com.pinguela.retroworld.ui.desktop.view.PaginatedSearchView;

public class SoftDeleteAnuncioAction extends BaseAction{
	
	private static Logger logger = LogManager.getLogger(SoftDeleteAnuncioAction.class);
	private AnuncioSearchView view = null;
	private AnuncioService anuncioService = null;
	
	public SoftDeleteAnuncioAction(AnuncioSearchView view) {
		this.view=view;
		initServices();
	}
	
	public SoftDeleteAnuncioAction(String name) {
		super(name);
		this.view=view;
		initServices();
	}
	
	public SoftDeleteAnuncioAction(String name, Icon icon) {
		super(name, icon);
		this.view=view;
		initServices();
	}
	
	@Override
	public void doAction() {
		try {
			int filaSeleccionada = view.getTableResults().getSelectedRow();
			Anuncio anuncio = (Anuncio) view.getTableResults().getModel().getValueAt(filaSeleccionada, 0);
			if(anuncio.getFechaFin()==null) {
				int answer = JOptionPane.showConfirmDialog(view, "¿Dar de baja anuncio?", "Confirmar baja", JOptionPane.YES_NO_OPTION);
				if(answer==JOptionPane.YES_OPTION) {
					if(anuncioService.delete(anuncio.getId())) {
						logger.info("anuncio con id: "+anuncio.getId()+" dado de baja");
						JOptionPane.showMessageDialog(view, "Anuncio dado de baja correctamente");
						AnuncioTableModel model = new AnuncioTableModel(anuncioService.findBy(view.getCriteria(), 
								view.getCurrentPosition(), PaginatedSearchView.PAGE_SIZE).getPage());
						view.setTableModel(model);
						view.addButtonsColumn();
					} else {
						logger.error("error al dar de baja el anuncio con id: "+anuncio.getId());
						JOptionPane.showMessageDialog(view,"Error al dar de baja el anuncio", "Error al eliminar", JOptionPane.ERROR_MESSAGE);
					}
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
