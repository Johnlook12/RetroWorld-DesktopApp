package com.pinguela.retroworld.ui.desktop;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

import javax.swing.BorderFactory;
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
import javax.swing.border.Border;

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
import com.pinguela.retroworld.ui.desktop.view.LoginView;

public class RetroWorldWindow implements TabCloseListener{
	private static Logger logger = LogManager.getLogger(RetroWorldWindow.class);
	private JFrame frame;
	private JDialog loginDialog;
	private LoginView loginView;
	private int tabIndex = 0;

	private JPanel centerPanel;
	private JPanel northPanel;
	private Empleado empleadoAutenticado=null;
	private JTabbedPane centerTabbedPane;
	private JMenu empleadoMenu;

	private static RetroWorldWindow instance = null;
	private JToolBar empleadoToolBar;
	private JToolBar videojuegoToolBar;
	private JToolBar anuncioToolBar;
	private JToolBar pedidoToolBar;

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
		gbl_menuContainerPanel.columnWidths = new int[]{45, 0, 0, 0, 52, 0};
		gbl_menuContainerPanel.rowHeights = new int[]{19, 0};
		gbl_menuContainerPanel.columnWeights = new double[]{0.0, 0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
		gbl_menuContainerPanel.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		menuContainerPanel.setLayout(gbl_menuContainerPanel);

		anuncioToolBar = new JToolBar();
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
		nuevoAnuncioToolbarButton.setAction(new OpenCreateAnuncioAction("", new ImageIcon(RetroWorldWindow.class.getResource("/nuvola/32x32/1727_add_add.png"))));
		anuncioToolBar.add(nuevoAnuncioToolbarButton);

		JButton moderarAnunciosToolBarButton = new JButton("New button");
		moderarAnunciosToolBarButton.setAction(new OpenModerarAnunciosAction("", new ImageIcon(RetroWorldWindow.class.getResource("/icons/icons8-manage-32.png"))));
		anuncioToolBar.add(moderarAnunciosToolBarButton);

		videojuegoToolBar = new JToolBar();
		GridBagConstraints gbc_videojuegoToolBar = new GridBagConstraints();
		gbc_videojuegoToolBar.anchor = GridBagConstraints.WEST;
		gbc_videojuegoToolBar.insets = new Insets(0, 0, 0, 5);
		gbc_videojuegoToolBar.gridx = 1;
		gbc_videojuegoToolBar.gridy = 0;
		menuContainerPanel.add(videojuegoToolBar, gbc_videojuegoToolBar);

		JButton buscarVideojuegoToolbarButton = new JButton(new OpenVideojuegoSearchAction("", new ImageIcon(RetroWorldWindow.class.getResource("/icons/icons8-search-32.png"))));
		videojuegoToolBar.add(buscarVideojuegoToolbarButton);

		JButton addVideojuegoToolBarButton = new JButton("New button");
		addVideojuegoToolBarButton.setAction(new OpenCreateVideojuegoAction("", new ImageIcon(RetroWorldWindow.class.getResource("/icons/icons8-add-32.png"))));
		videojuegoToolBar.add(addVideojuegoToolBarButton);

		empleadoToolBar = new JToolBar();
		GridBagConstraints gbc_empleadoToolBar = new GridBagConstraints();
		gbc_empleadoToolBar.anchor = GridBagConstraints.WEST;
		gbc_empleadoToolBar.insets = new Insets(0, 0, 0, 5);
		gbc_empleadoToolBar.gridx = 2;
		gbc_empleadoToolBar.gridy = 0;
		menuContainerPanel.add(empleadoToolBar, gbc_empleadoToolBar);

		JButton gestionEmpleadoToolBarButton = new JButton("New button");
		gestionEmpleadoToolBarButton.setAction(new OpenEmpleadoSearchAction("", new ImageIcon(RetroWorldWindow.class.getResource("/icons/icons8-manage-employee-32.png"))));
		empleadoToolBar.add(gestionEmpleadoToolBarButton);

		JButton addEmpleadoToolBarButton = new JButton("New button");
		addEmpleadoToolBarButton.setAction(new OpenCreateEmpleadoAction("", new ImageIcon(RetroWorldWindow.class.getResource("/icons/icons8-add-administrator-32.png"))));
		empleadoToolBar.add(addEmpleadoToolBarButton);

		pedidoToolBar = new JToolBar();
		GridBagConstraints gbc_pedidoToolBar = new GridBagConstraints();
		gbc_pedidoToolBar.anchor = GridBagConstraints.WEST;
		gbc_pedidoToolBar.insets = new Insets(0, 0, 0, 5);
		gbc_pedidoToolBar.gridx = 3;
		gbc_pedidoToolBar.gridy = 0;
		menuContainerPanel.add(pedidoToolBar, gbc_pedidoToolBar);

		JButton gestionarPedidoToolBarButton = new JButton("New button");
		gestionarPedidoToolBarButton.setAction(new OpenPedidoSearchAction("", new ImageIcon(RetroWorldWindow.class.getResource("/icons/icons8-order-32.png"))));

		pedidoToolBar.add(gestionarPedidoToolBarButton);

		JMenuBar profileMenuBar = new JMenuBar();
		GridBagConstraints gbc_profileMenuBar = new GridBagConstraints();
		gbc_profileMenuBar.gridx = 4;
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
		setAdministradorFunctionsVisible(false);
		Border border = BorderFactory.createStrokeBorder(
				new BasicStroke(1.0f,                   
						BasicStroke.CAP_BUTT,   
						BasicStroke.JOIN_BEVEL, 
						0.0f,                  
						new float[]{2.0f},    
						0.0f),
				Color.DARK_GRAY);
		empleadoToolBar.setBorder(border);
		anuncioToolBar.setBorder(border);
		videojuegoToolBar.setBorder(border);
		pedidoToolBar.setBorder(border);
	}

	public void showLoginView() {
		loginDialog.setModal(true);
		loginDialog.pack();
		loginDialog.setVisible(true);
	}
	
	public void postLogOut() {
		loginView.clearFields();
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
	
	public void setLoginView(LoginView view) {
		loginView=view;
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

	public void setAdministradorFunctionsVisible(boolean visible) {
		empleadoMenu.setVisible(visible);
		empleadoToolBar.setVisible(visible);
	}

	@Override
	public void onTabClose() {
		decreaseTabIndex();

	}
}