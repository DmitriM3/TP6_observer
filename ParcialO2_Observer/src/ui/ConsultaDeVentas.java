package ui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import modelo.EstacionServicio;
import modelo.Venta;
import modelo.exceptions.FechaInvalidaException;
import modelo.exceptions.RegistroDeVentasException;

public class ConsultaDeVentas extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7180801671456434213L;
	private JPanel contentPane;
	ArrayList<Venta> misVentas;
	private JTable table;
	private DefaultTableModel modelo;
	private EstacionServicio miEstacion;
	private JTextField textFechaInicio;
	private JTextField textFechaFin;

	/**
	 * Create the frame.
	 * 
	 * @param string2
	 * @param string
	 * 
	 * @throws AppSQLException
	 * @throws ClassNotFound
	 */
	public ConsultaDeVentas(EstacionServicio estacionServicio, String fechaInicio, String fechaFin) {
		this.miEstacion = estacionServicio;
		setTitle("Registro de Ventas");
		String[] titulos = { "FECHA", "HORA", "LITROS CARGADOS", "MONTO TOTAL" };
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(400, 250, 530, 460);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.NORTH);

		JLabel lblNewLabel = new JLabel("Fecha Inicio : ");
		panel.add(lblNewLabel);

		textFechaInicio = new JTextField();
		textFechaInicio.setHorizontalAlignment(SwingConstants.CENTER);
		textFechaInicio.setToolTipText("Ejemplo : 05/18/2021");
		textFechaInicio.setText("mm/dd/aaaa");
		panel.add(textFechaInicio);
		textFechaInicio.setColumns(10);

		JLabel lblNewLabel_1 = new JLabel("Fecha Fin : ");
		panel.add(lblNewLabel_1);

		textFechaFin = new JTextField();
		textFechaFin.setHorizontalAlignment(SwingConstants.CENTER);
		textFechaFin.setToolTipText("Ejemplo : 05/20/2021");
		textFechaFin.setText("mm/dd/aaaa");
		panel.add(textFechaFin);
		textFechaFin.setColumns(10);

		JScrollPane scrollPane = new JScrollPane();
		contentPane.add(scrollPane, BorderLayout.CENTER);

		table = new JTable();

		modelo = new DefaultTableModel(new Object[][] {}, titulos);

		generarTablaFiltrada(fechaInicio, fechaFin);

		table.setModel(modelo);
		// Centrar los datos en las columnas
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment(JLabel.CENTER);
		for (int i = 0; i < titulos.length; i++) {
			table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
		}

		scrollPane.setViewportView(table);

		JButton btnNewButton = new JButton("Generar filtro");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					miEstacion.validacionDeFechas(textFechaInicio.getText(), textFechaFin.getText());
					generarTablaFiltrada(textFechaInicio.getText(), textFechaFin.getText());
				} catch (FechaInvalidaException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
				}

			}
		});
		panel.add(btnNewButton);

		JButton cerrarButton = new JButton("Cerrar");
		cerrarButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				dispose();
				VentanaPrincipal miVentana = new VentanaPrincipal(miEstacion);
				miVentana.setVisible(true);
			}
		});
		contentPane.add(cerrarButton, BorderLayout.SOUTH);

	}

	public void generarTablaFiltrada(String fechaInicio, String fechaFin) {
		vaciarTabla();
		try {
//			this.misVentas = this.miEstacion.obtenerMisVentas();
			this.misVentas = this.miEstacion.obtenerMisVentasConFiltroFechaInicioFin(fechaInicio, fechaFin);
		} catch (RegistroDeVentasException e1) {
			JOptionPane.showMessageDialog(null, e1.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		}

		// Agrego las ventas a una tabla
		for (Venta venta : misVentas) {
			modelo.addRow(new Object[] { venta.obtenerFechaVenta(), venta.obtenerHoraVenta(),
					venta.obtenerCantidadLitrosCargados(), venta.consultarMontoTotal() });
		}
	}

	public void vaciarTabla() {
		int filas = modelo.getRowCount();
		// Remuevo filas
		for (int i = filas - 1; i >= 0; i--) {
			modelo.removeRow(i);
		}
	}

}
