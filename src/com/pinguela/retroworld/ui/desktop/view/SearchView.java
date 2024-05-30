package com.pinguela.retroworld.ui.desktop.view;
import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.TableModel;

import com.pinguela.retroworld.model.AbstractCriteria;

public abstract class SearchView extends View{
	/**
	 * 
	 */
	private static final long serialVersionUID = -923338180889846813L;
	private JTable tableResults;
	private JPanel searchFieldPanel;
	private JScrollPane resultsScrollPane;
	
	public SearchView() {
		initialize();
	}
	
	private void initialize() {
		searchFieldPanel = new JPanel();
		add(searchFieldPanel, BorderLayout.NORTH);
		
		resultsScrollPane = new JScrollPane();
		add(resultsScrollPane, BorderLayout.CENTER);
		
		tableResults = new JTable();
		resultsScrollPane.setViewportView(tableResults);
		
	}
	
	public JTable getTableResults() {
		return this.tableResults;
	}
	
	public JScrollPane getResultsScrollPane() {
		return this.resultsScrollPane;
	}

	protected JPanel getSearchFieldPanel() {
		return searchFieldPanel;
	}
	
	public void setTableModel(TableModel model) {
		this.tableResults.setModel(model);
	}
	
	public TableModel getTableModel() {
		return this.tableResults.getModel();
	}
	
	public abstract AbstractCriteria getCriteria();
	
	protected abstract void addButtonsColumn();
}
