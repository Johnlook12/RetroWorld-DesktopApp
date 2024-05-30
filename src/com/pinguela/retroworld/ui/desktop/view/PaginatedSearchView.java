package com.pinguela.retroworld.ui.desktop.view;
import java.awt.BorderLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.TableModel;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.pinguela.retroworld.model.AbstractCriteria;
import com.pinguela.retroworld.model.Results;
import com.pinguela.retroworld.ui.desktop.RetroWorldWindow;
import com.pinguela.retroworld.ui.desktop.controller.PaginacionAnteriorAction;
import com.pinguela.retroworld.ui.desktop.controller.PaginacionFinalAction;
import com.pinguela.retroworld.ui.desktop.controller.PaginacionInicialAction;
import com.pinguela.retroworld.ui.desktop.controller.PaginacionSiguienteAction;
import com.pinguela.retroworld.ui.desktop.controller.ShowDetailAction;
import com.pinguela.retroworld.ui.desktop.renderer.ButtonColumn;

public abstract class PaginatedSearchView<E> extends SearchView{
	private static Logger logger = LogManager.getLogger(PaginatedSearchView.class);
	
	private static final long serialVersionUID = 4887680524869419924L;
	public static final int PAGE_SIZE = 10;
	private Results<E> results;
	
	private JButton inicioBtn;
	private JButton anteriorBtn;
	private JButton siguienteBtn;
	private JButton finalBtn;
	
	private int currentPosition = 1;
	private JPanel paginationPanel;
	private JLabel paginationLbl;
	
	public PaginatedSearchView() {
		initialize();
	}
	
	private void initialize() {
		paginationPanel = new JPanel();
		add(paginationPanel, BorderLayout.SOUTH);
		
		inicioBtn = new JButton("Inicio");
		inicioBtn.setAction(new PaginacionInicialAction(this, "",new ImageIcon(RetroWorldWindow.class.getResource("/icons/icons8-arrow-left-22.png"))));
		inicioBtn.setEnabled(false);
		paginationPanel.add(inicioBtn);
		
		anteriorBtn = new JButton("Anterior");
		anteriorBtn.setAction(new PaginacionAnteriorAction(this,"",new ImageIcon(RetroWorldWindow.class.getResource("/icons/icons8-arrow-pointing-left-22.png"))));
		anteriorBtn.setEnabled(false);
		paginationPanel.add(anteriorBtn);
		
		paginationLbl = new JLabel("-");
		paginationPanel.add(paginationLbl);
		
		siguienteBtn = new JButton("siguiente");
		siguienteBtn.setAction(new PaginacionSiguienteAction(this,"",new ImageIcon(RetroWorldWindow.class.getResource("/icons/icons8-arrow-22.png"))));
		siguienteBtn.setEnabled(false);
		paginationPanel.add(siguienteBtn);
		
		finalBtn = new JButton("final");
		finalBtn.setAction(new PaginacionFinalAction(this, "", new ImageIcon(RetroWorldWindow.class.getResource("/icons/icons8-arrow-right-22.png"))));
		finalBtn.setEnabled(false);
		paginationPanel.add(finalBtn);
		
	}
	
	public void updateView() {
		setTableModel(getTableModel());
		addButtonsColumn();
		
		paginationLbl.setText(currentPosition+" - "+results.getTotal());
		
		inicioBtn.setEnabled(getCurrentPosition()>PAGE_SIZE);
		anteriorBtn.setEnabled(inicioBtn.isEnabled());
		siguienteBtn.setEnabled(getCurrentPosition()<results.getTotal()-PAGE_SIZE+1);
		finalBtn.setEnabled(siguienteBtn.isEnabled());
	}
	
	public int getCurrentPosition() {
		return currentPosition;
	}	
	
	public void setCurrentPosition(int currentPagingPosition) {
		this.currentPosition = currentPagingPosition;
	}
	
	public Results<E> getResults() {
		return this.results;
	}
	
	public void setResults(Results<E> results) {
		this.results = results;
	}
}
