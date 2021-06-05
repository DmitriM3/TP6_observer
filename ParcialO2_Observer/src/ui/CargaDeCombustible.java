package ui;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import modelo.EstacionServicio;
import modelo.exceptions.CantidadLitrosInvalidaException;
import modelo.exceptions.RegistroDeVentasException;

public class CargaDeCombustible extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -279310512763808614L;
	private JPanel contentPane;
	private JTextField litros;
	private EstacionServicio miEstacion;
	private JTextField txtEmail;

	public CargaDeCombustible(EstacionServicio estacionServicio) {
		this.miEstacion = estacionServicio;
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(400, 250, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel_descripcion = new JPanel();
		panel_descripcion.setBounds(10, 11, 414, 39);
		contentPane.add(panel_descripcion);
		panel_descripcion.setLayout(new BorderLayout(0, 0));

		JLabel lblNewLabel = new JLabel("Carga de Combustible");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 25));
		panel_descripcion.add(lblNewLabel);

		JPanel panel_datos = new JPanel();
		panel_datos.setBounds(10, 61, 414, 99);
		contentPane.add(panel_datos);
		panel_datos.setLayout(null);

		JLabel lblLitrosCargados = new JLabel("Litros Cargados :");
		lblLitrosCargados.setHorizontalAlignment(SwingConstants.RIGHT);
		lblLitrosCargados.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblLitrosCargados.setBounds(25, 10, 120, 25);
		panel_datos.add(lblLitrosCargados);

		litros = new JTextField();
		litros.setBounds(160, 10, 50, 25);
		panel_datos.add(litros);
		litros.setColumns(10);

		JLabel lblTipoNafta = new JLabel("Tipo de Nafta : ");
		lblTipoNafta.setHorizontalAlignment(SwingConstants.RIGHT);
		lblTipoNafta.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblTipoNafta.setBounds(25, 50, 120, 25);
		panel_datos.add(lblTipoNafta);

		JRadioButton rdbtnComun = new JRadioButton("Comun");
		rdbtnComun.setBounds(160, 50, 80, 25);
		panel_datos.add(rdbtnComun);

		JRadioButton rdbtnSuper = new JRadioButton("Super");
		rdbtnSuper.setBounds(250, 50, 80, 25);
		panel_datos.add(rdbtnSuper);

		JPanel panel = new JPanel();
		panel.setBounds(10, 206, 414, 39);
		contentPane.add(panel);

		JPanel panel_1 = new JPanel();
		panel_1.setBounds(10, 165, 414, 35);
		contentPane.add(panel_1);
		panel_1.setLayout(null);

		JLabel lblEmail = new JLabel("Email : ");
		lblEmail.setBounds(25, 6, 120, 25);
		lblEmail.setHorizontalAlignment(SwingConstants.RIGHT);
		lblEmail.setFont(new Font("Tahoma", Font.BOLD, 14));
		panel_1.add(lblEmail);

		JLabel label = new JLabel("");
		label.setBounds(188, 15, 0, 0);
		panel_1.add(label);

		txtEmail = new JTextField();
		txtEmail.setBounds(160, 6, 175, 25);
		panel_1.add(txtEmail);
		txtEmail.setColumns(10);

		JButton btnConsultarMonto = new JButton("Consultar Monto");
		btnConsultarMonto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (rdbtnComun.isSelected()) {
					try {
						JOptionPane.showMessageDialog(
								null, "Monto a cobrar : $ " + miEstacion.calcularMonto(litros.getText(),
										rdbtnComun.getText(), LocalDateTime.now()),
								"Consulta de Monto", JOptionPane.INFORMATION_MESSAGE);
					} catch (CantidadLitrosInvalidaException ex) {
						JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
					}

				}
				if (rdbtnSuper.isSelected()) {
					try {
						JOptionPane.showMessageDialog(
								null, "Monto a cobrar : $ " + miEstacion.calcularMonto(litros.getText(),
										rdbtnSuper.getText(), LocalDateTime.now()),
								"Consulta de Monto", JOptionPane.INFORMATION_MESSAGE);
					} catch (CantidadLitrosInvalidaException ex) {
						JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});
		panel.add(btnConsultarMonto);

		JButton btnConfirmar = new JButton("Confirmar Pago");
		btnConfirmar.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				if (rdbtnComun.isSelected()) {
					try {
						miEstacion.realizarVenta(litros.getText(), rdbtnComun.getText(), LocalDateTime.now(),
								txtEmail.getText());
						JOptionPane.showMessageDialog(null, "La venta se registro con éxito ", "CONFIRMACIÓN",
								JOptionPane.INFORMATION_MESSAGE);
						dispose();
					} catch (CantidadLitrosInvalidaException | RegistroDeVentasException ex) {
						JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
					}

				}
				if (rdbtnSuper.isSelected()) {
					try {
						miEstacion.realizarVenta(litros.getText(), rdbtnSuper.getText(), LocalDateTime.now(),
								txtEmail.getText());
						JOptionPane.showMessageDialog(null, "La venta se registro con éxito ", "CONFIRMACIÓN",
								JOptionPane.INFORMATION_MESSAGE);
						dispose();
					} catch (CantidadLitrosInvalidaException | RegistroDeVentasException ex) {
						JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
					}

				}
			}
		});
		panel.add(btnConfirmar);

		JButton btnCerrar = new JButton("Volver");
		btnCerrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				dispose();
				VentanaPrincipal miVentana = new VentanaPrincipal(miEstacion);
				miVentana.setVisible(true);
			}
		});
		panel.add(btnCerrar);

		rdbtnComun.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (rdbtnComun.isSelected()) {
					rdbtnSuper.setSelected(false);
				}
			}
		});
		rdbtnSuper.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (rdbtnSuper.isSelected()) {
					rdbtnComun.setSelected(false);
				}
			}
		});
	}
}
