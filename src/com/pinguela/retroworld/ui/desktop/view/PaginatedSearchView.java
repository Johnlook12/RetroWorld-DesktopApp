package com.pinguela.retroworld.ui.desktop.view;
import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.pinguela.retroworld.model.Results;
import com.pinguela.retroworld.ui.desktop.controller.PagedSearchAction;

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
	
	public PaginatedSearchView(PagedSearchAction<E> start, PagedSearchAction<E> previous, PagedSearchAction<E> next, PagedSearchAction<E> end) {
		this();
		
		setStart(start);
		setPrevious(previous);
		setNext(next);
		setEnd(end);
		
		initialize();
	}
	
	private void initialize() {
		paginationPanel = new JPanel();
		add(paginationPanel, BorderLayout.SOUTH);
		
		inicioBtn = new JButton();
		paginationPanel.add(inicioBtn);
		
		anteriorBtn = new JButton();
		paginationPanel.add(anteriorBtn);
		
		paginationLbl = new JLabel("-");
		paginationPanel.add(paginationLbl);
		
		siguienteBtn = new JButton();
		paginationPanel.add(siguienteBtn);
		
		finalBtn = new JButton();	
		paginationPanel.add(finalBtn);
	}
	
	public void updateView() {
		
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

	public void setStart(PagedSearchAction<E> start) {
		this.inicioBtn.setAction(start);
		this.inicioBtn.setEnabled(false);
	}

	public void setPrevious(PagedSearchAction<E> previous) {
		this.anteriorBtn.setAction(previous);
		anteriorBtn.setEnabled(false);
	}

	public void setNext(PagedSearchAction<E> next) {
		this.siguienteBtn.setAction(next);
		siguienteBtn.setEnabled(false);
	}

	public void setEnd(PagedSearchAction<E> end) {
		this.finalBtn.setAction(end);
		finalBtn.setEnabled(false);
	}
}
