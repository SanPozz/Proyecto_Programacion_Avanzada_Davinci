package GUI;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;



import BLLL.Clases.Deposito;

import BLLL.Clases.Mineral;


import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;

public class EditarMineral extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField inputnombre;
	private JTextField inputPureza;
	private JTextField inputTonelada;
	private JTextField inputPrecioTonelada;


	public EditarMineral(Mineral mineral , Deposito deposito) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 398, 465);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Nombre");
		lblNewLabel.setBounds(34, 94, 42, 14);
		lblNewLabel.setVerticalAlignment(SwingConstants.TOP);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lblNewLabel);
		
		inputnombre = new JTextField();
		inputnombre.setText(mineral.getNombre());
		inputnombre.setBounds(34, 119, 177, 20);
		inputnombre.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(inputnombre);
		inputnombre.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Pureza");
		lblNewLabel_1.setVerticalAlignment(SwingConstants.TOP);
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(34, 161, 42, 14);
		contentPane.add(lblNewLabel_1);
		
		inputPureza = new JTextField();
		inputPureza.setText(String.valueOf(mineral.getPureza()));
		inputPureza.setHorizontalAlignment(SwingConstants.CENTER);
		inputPureza.setColumns(10);
		inputPureza.setBounds(34, 186, 177, 20);
		contentPane.add(inputPureza);
		
		JLabel lblNewLabel_1_1 = new JLabel("Toneladas");
		lblNewLabel_1_1.setVerticalAlignment(SwingConstants.TOP);
		lblNewLabel_1_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_1_1.setBounds(34, 226, 86, 14);
		contentPane.add(lblNewLabel_1_1);
		
		inputTonelada = new JTextField();
		inputTonelada.setText(String.valueOf(mineral.getToneladas()));
		inputTonelada.setHorizontalAlignment(SwingConstants.CENTER);
		inputTonelada.setColumns(10);
		inputTonelada.setBounds(34, 251, 177, 20);
		contentPane.add(inputTonelada);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("Precio Toneladas");
		lblNewLabel_1_1_1.setVerticalAlignment(SwingConstants.TOP);
		lblNewLabel_1_1_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_1_1_1.setBounds(34, 291, 86, 14);
		contentPane.add(lblNewLabel_1_1_1);
		
		inputPrecioTonelada = new JTextField();
		inputPrecioTonelada.setText(String.valueOf(mineral.getPrecioTonelada()));
		inputPrecioTonelada.setHorizontalAlignment(SwingConstants.CENTER);
		inputPrecioTonelada.setColumns(10);
		inputPrecioTonelada.setBounds(34, 316, 177, 20);
		contentPane.add(inputPrecioTonelada);
		
		JButton btnNewButton = new JButton("Editar");
		btnNewButton.setBounds(34, 363, 89, 34);
		contentPane.add(btnNewButton);
		
		JLabel labelInfo = new JLabel("");
		labelInfo.setBounds(221, 316, 145, 14);
		contentPane.add(labelInfo);
		
		JLabel lblNewLabel_2 = new JLabel("Editar mineral");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_2.setBounds(34, 28, 145, 25);
		contentPane.add(lblNewLabel_2);
		
		JButton botonAtras = new JButton("VOLVER");
		botonAtras.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DepositoVerStock atras = new DepositoVerStock(deposito);
				atras.setVisible(true);
				dispose();
				
			}
		});
		botonAtras.setBounds(125, 363, 86, 34);
		contentPane.add(botonAtras);
		
		
		
		
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mineral.setNombre(inputnombre.getText());
				mineral.setPureza(Float.parseFloat(inputPureza.getText()));
				mineral.setToneladas(Float.parseFloat(inputTonelada.getText()));
				mineral.setPrecioTonelada(Float.parseFloat(inputPrecioTonelada.getText()));
				
				
				labelInfo.setText(Mineral.EditarMineral(mineral));
				
			}
		});
		
	}
}
