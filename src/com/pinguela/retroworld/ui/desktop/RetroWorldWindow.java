package com.pinguela.retroworld.ui.desktop;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JToolBar;
import javax.swing.KeyStroke;
import javax.swing.UIManager;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.formdev.flatlaf.themes.FlatMacDarkLaf;
import com.pinguela.retroworld.model.Empleado;
import com.pinguela.retroworld.ui.desktop.controller.LogOutAction;
import com.pinguela.retroworld.ui.desktop.controller.OpenAnuncioSearchAction;
import com.pinguela.retroworld.ui.desktop.controller.OpenCreateAnuncioAction;
import com.pinguela.retroworld.ui.desktop.controller.OpenCreateEmpleadoAction;
import com.pinguela.retroworld.ui.desktop.controller.OpenCreateVideojuegoAction;
import com.pinguela.retroworld.ui.desktop.controller.OpenEmpleadoProfileAction;
import com.pinguela.retroworld.ui.desktop.controller.OpenEmpleadoSearchAction;
import com.pinguela.retroworld.ui.desktop.controller.OpenLoginAction;
import com.pinguela.retroworld.ui.desktop.controller.OpenModerarAnunciosAction;
import com.pinguela.retroworld.ui.desktop.controller.OpenPedidoSearchAction;
import com.pinguela.retroworld.ui.desktop.controller.OpenVideojuegoSearchAction;
import com.pinguela.retroworld.ui.desktop.dialog.RWDialog;
import com.pinguela.retroworld.ui.desktop.listeners.TabCloseListener;
import com.pinguela.retroworld.ui.desktop.renderer.TabCloseButton;
import com.pinguela.retroworld.ui.desktop.view.EmpleadoProfileView;
import com.pinguela.retroworld.ui.desktop.view.LoginView;

public class RetroWorldWindow implements TabCloseListener{
	private static Logger logger = LogManager.getLogger(RetroWorldWindow.class);
	private JFrame frame;
	private JDialog loginDialog;
	private int tabIndex = 0;
	
	private JPanel centerPanel;
	private JPanel northPanel;
	private Empleado empleadoAutenticado=null;
	private JTabbedPane centerTabbedPane;
	private JMenu empleadoMenu;
	
	private static RetroWorldWindow instance = null;
	
	public static RetroWorldWindow getInstance() {
		if(instance == null) {
			instance = new RetroWorldWindow();
		}
		return instance;
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UIManager.setLookAndFeel(new FlatMacDarkLaf());
					new OpenLoginAction().doAction();
					RetroWorldWindow window = RetroWorldWindow.getInstance();
				} catch (Exception e) {
					logger.error(e.getMessage(), e);
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	private RetroWorldWindow() {
		initialize();
		logger.info("Postinitializing window...");
		postInitialize();
		logger.info("Window fully intilialized");
	}
	


	private void initialize() {
		frame = new JFrame();
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage(RetroWorldWindow.class.getResource("/icons/favicon.png")));
		frame.setBounds(100, 100, 1050, 650);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel mainPanel = new JPanel();
		frame.getContentPane().add(mainPanel, BorderLayout.CENTER);
		mainPanel.setLayout(new BorderLayout(0, 0));
		
		northPanel = new JPanel();
		mainPanel.add(northPanel, BorderLayout.NORTH);
		northPanel.setLayout(new BorderLayout(0, 0));
		
		JMenuBar menuBar = new JMenuBar();
		northPanel.add(menuBar, BorderLayout.NORTH);
		
		empleadoMenu = new JMenu("Empleados");
		menuBar.add(empleadoMenu);
		
		JMenuItem gestionarEmpleadosMenuItem = new JMenuItem();
		gestionarEmpleadosMenuItem.setAction(new OpenEmpleadoSearchAction("Gestión"));
		empleadoMenu.add(gestionarEmpleadosMenuItem);
		
		JMenuItem addEmpleadoMenuItem = new JMenuItem();
		addEmpleadoMenuItem.setAction(new OpenCreateEmpleadoAction("Añadir"));
		empleadoMenu.add(addEmpleadoMenuItem);
		
		JMenu anuncioMenu = new JMenu("Anuncios");
		menuBar.add(anuncioMenu);
		
		JMenuItem buscarAnuncioMenuItem = new JMenuItem();
		buscarAnuncioMenuItem.setAction(new OpenAnuncioSearchAction("Buscar...",new ImageIcon(RetroWorldWindow.class.getResource("/nuvola/32x32/1467_xmag_xmag.png"))));
		buscarAnuncioMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F, InputEvent.CTRL_DOWN_MASK));
		anuncioMenu.add(buscarAnuncioMenuItem);
		
		JMenuItem moderarAnuncioMenuItem = new JMenuItem();
		moderarAnuncioMenuItem.setAction(new OpenModerarAnunciosAction("Moderar anuncios"));
		anuncioMenu.add(moderarAnuncioMenuItem);
		
		JMenuItem addAnuncioMenuItem = new JMenuItem("");
		addAnuncioMenuItem.setAction(new OpenCreateAnuncioAction("Crear"));
		anuncioMenu.add(addAnuncioMenuItem);
		
		JMenu videojuegoMenu = new JMenu("Videojuegos");
		menuBar.add(videojuegoMenu);
		
		JMenuItem buscarVideojuegosMenuItem = new JMenuItem();
		buscarVideojuegosMenuItem.setAction(new OpenVideojuegoSearchAction("Buscar..."));
		videojuegoMenu.add(buscarVideojuegosMenuItem);
		
		JMenuItem crearVideojuegoMenuItem = new JMenuItem("");
		crearVideojuegoMenuItem.setAction(new OpenCreateVideojuegoAction("Añadir"));
		videojuegoMenu.add(crearVideojuegoMenuItem);
		
		JMenu pedidoMenu = new JMenu("Pedidos");
		menuBar.add(pedidoMenu);
		
		JMenuItem gestionarPedidosMenuItem = new JMenuItem();
		gestionarPedidosMenuItem.setAction(new OpenPedidoSearchAction("Gestionar"));
		pedidoMenu.add(gestionarPedidosMenuItem);
		
		JPanel menuContainerPanel = new JPanel();
		northPanel.add(menuContainerPanel, BorderLayout.SOUTH);
		GridBagLayout gbl_menuContainerPanel = new GridBagLayout();
		gbl_menuContainerPanel.columnWidths = new int[]{45, 0, 52, 0};
		gbl_menuContainerPanel.rowHeights = new int[]{19, 0};
		gbl_menuContainerPanel.columnWeights = new double[]{0.0, 1.0, 0.0, Double.MIN_VALUE};
		gbl_menuContainerPanel.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		menuContainerPanel.setLayout(gbl_menuContainerPanel);
		
		JToolBar anuncioToolBar = new JToolBar();
		GridBagConstraints gbc_anuncioToolBar = new GridBagConstraints();
		gbc_anuncioToolBar.anchor = GridBagConstraints.WEST;
		gbc_anuncioToolBar.insets = new Insets(0, 0, 0, 5);
		gbc_anuncioToolBar.gridx = 0;
		gbc_anuncioToolBar.gridy = 0;
		menuContainerPanel.add(anuncioToolBar, gbc_anuncioToolBar);
		
		JButton buscarAnuncioToolbarButton = new JButton("");
		buscarAnuncioToolbarButton.setAction(new OpenAnuncioSearchAction("", new ImageIcon(RetroWorldWindow.class.getResource("/nuvola/32x32/1467_xmag_xmag.png"))));
		anuncioToolBar.add(buscarAnuncioToolbarButton);
		
		JButton nuevoAnuncioToolbarButton = new JButton("");
		nuevoAnuncioToolbarButton.setIcon(new ImageIcon(RetroWorldWindow.class.getResource("/nuvola/32x32/1727_add_add.png")));
		anuncioToolBar.add(nuevoAnuncioToolbarButton);
		
		JToolBar videojuegoToolBar = new JToolBar();
		GridBagConstraints gbc_videojuegoToolBar = new GridBagConstraints();
		gbc_videojuegoToolBar.anchor = GridBagConstraints.WEST;
		gbc_videojuegoToolBar.insets = new Insets(0, 0, 0, 5);
		gbc_videojuegoToolBar.gridx = 1;
		gbc_videojuegoToolBar.gridy = 0;
		menuContainerPanel.add(videojuegoToolBar, gbc_videojuegoToolBar);
		
		JButton buscarVideojuegoToolbarButton = new JButton(new OpenVideojuegoSearchAction("", new ImageIcon(RetroWorldWindow.class.getResource("/icons/icons8-search-32.png"))));
		videojuegoToolBar.add(buscarVideojuegoToolbarButton);
		
		JMenuBar profileMenuBar = new JMenuBar();
		GridBagConstraints gbc_profileMenuBar = new GridBagConstraints();
		gbc_profileMenuBar.gridx = 2;
		gbc_profileMenuBar.gridy = 0;
		menuContainerPanel.add(profileMenuBar, gbc_profileMenuBar);
		
		JMenu profileMenu = new JMenu("");
		profileMenu.setIcon(new ImageIcon(RetroWorldWindow.class.getResource("/nuvola/32x32/1447_man_male_male_man_user_employee_manager_employee_operator_manager_personal_operator_administrator_administrator_personal_user.png")));
		profileMenuBar.add(profileMenu);
		
		JMenuItem profileMenuItem = new JMenuItem();
		profileMenuItem.setAction(new OpenEmpleadoProfileAction(this, "Mi Perfil"));
		profileMenu.add(profileMenuItem);
		
		JMenuItem logOutMenuItem = new JMenuItem("Cerrar sesión");
		logOutMenuItem.addActionListener(new LogOutAction());
		profileMenu.add(logOutMenuItem);
		
		JPanel southPanel = new JPanel();
		FlowLayout fl_southPanel = (FlowLayout) southPanel.getLayout();
		mainPanel.add(southPanel, BorderLayout.SOUTH);
		
		JPanel westPanel = new JPanel();
		FlowLayout fl_westPanel = (FlowLayout) westPanel.getLayout();
		mainPanel.add(westPanel, BorderLayout.WEST);
		
		JPanel eastPanel = new JPanel();
		FlowLayout fl_eastPanel = (FlowLayout) eastPanel.getLayout();
		mainPanel.add(eastPanel, BorderLayout.EAST);
		
		centerPanel = new JPanel();
		mainPanel.add(centerPanel, BorderLayout.CENTER);
		centerPanel.setLayout(new BorderLayout(0, 0));
		
		centerTabbedPane = new JTabbedPane(JTabbedPane.TOP);
		centerPanel.add(centerTabbedPane, BorderLayout.CENTER);
	}
	
	private void postInitialize(){
		try {	
			empleadoMenu.setVisible(false);
		}catch(Exception e) {
			logger.fatal(e.getMessage(), e);
		}
	}
	
	public void showLoginView() {
		loginDialog.setModal(true);
		loginDialog.pack();
		loginDialog.setVisible(true);
	}
	
	
	public void addView(String name, JComponent view) {
		addClosableView(name, null, view);
	}
	
	public void addClosableView(String name, Icon icon, JComponent view) {
		centerPanel.removeAll();
		centerPanel.add(centerTabbedPane);
		centerTabbedPane.addTab(name, icon, view);
		String tabName = name+" - "+(tabIndex+1);

		centerTabbedPane.setTabComponentAt(tabIndex++, new TabCloseButton(tabName, centerTabbedPane, this));
		centerTabbedPane.repaint();
		centerTabbedPane.revalidate();
	}
	
	public void setCenterView(JComponent view) {
		centerPanel.removeAll();
		centerPanel.add(view, BorderLayout.CENTER);
		centerPanel.repaint();
		centerPanel.revalidate();
	}
	
	public void setLoginDialog(RWDialog dialog) {
		this.loginDialog=dialog;
	}
	
	public void setEmpleadoAutenticado(Empleado e) {
		this.empleadoAutenticado = e;
	}

	public Empleado getEmpleadoAutenticado() {
		return empleadoAutenticado;
	}	
	
	private void decreaseTabIndex() {
		tabIndex--;
	}
	
	public void setVisible(boolean visible) {
		frame.setVisible(visible);
	}
	
	public JMenu getEmpleadoMenu() {
		return empleadoMenu;
		}

	@Override
	public void onTabClose() {
		decreaseTabIndex();
		
	}
}