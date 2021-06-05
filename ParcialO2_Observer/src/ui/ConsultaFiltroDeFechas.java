package ui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import modelo.EstacionServicio;
import modelo.exceptions.FechaInvalidaException;

public class ConsultaFiltroDeFechas extends JFrame {

	private static final long serialVersionUID = 3965156981775482438L;
	private JPanel contentPane;
	private JTextField textFechaInicio;
	private JTextField textFechaFin;
	private EstacionServicio miEstacion;

	public ConsultaFiltroDeFechas(EstacionServicio estacionServicio) {
		setTitle("Filtro de Fechas");
		this.miEstacion = estacionServicio;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(400, 250, 540, 160);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.NORTH);

		JLabel lblNewLabel = new JLabel("Fecha Inicio : ");
		panel.add(lblNewLabel);

		textFechaInicio = new JTextField();
		textFechaInicio.setToolTipText("Ejemplo : 02/15/2021");
		textFechaInicio.setHorizontalAlignment(SwingConstants.CENTER);
		textFechaInicio.setColumns(10);
		panel.add(textFechaInicio);

		JLabel lblNewLabel_1 = new JLabel("Fecha Fin : ");
		panel.add(lblNewLabel_1);

		textFechaFin = new JTextField();
		textFechaFin.setToolTipText("Ejemplo : 02/20/2021");
		textFechaFin.setHorizontalAlignment(SwingConstants.CENTER);
		textFechaFin.setColumns(10);
		panel.add(textFechaFin);

		JButton btnNewButton = new JButton("Generar filtro");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					miEstacion.validacionDeFechas(textFechaInicio.getText(), textFechaFin.getText());
					ConsultaDeVentas consulta = new ConsultaDeVentas(miEstacion(), textFechaInicio.getText(),
							textFechaFin.getText());
					consulta.setVisible(true);
					dispose();
				} catch (FechaInvalidaException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
				}

			}
		});
		panel.add(btnNewButton);

		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1, BorderLayout.SOUTH);

		JButton btnNewButton_1 = new JButton("Voler");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				VentanaPrincipal volver = new VentanaPrincipal(miEstacion);
				volver.setVisible(true);
			}
		});
		panel_1.add(btnNewButton_1);
	}

	public EstacionServicio miEstacion() {
		return this.miEstacion;
	}

}
