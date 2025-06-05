package GUI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import BLL.Clases.Deposito;
import BLL.Clases.Mineral;
import DLL.Repository.RegistrarMineRepository;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class RegistrarMineral extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField RegisNom;
	private JTextField RegisPur;
	private JTextField RegisTone;
	private JTextField RegisPrecio;



	
	public RegistrarMineral(Mineral mineral,Deposito deposito) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 490);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Nombre");
		lblNewLabel.setBounds(10, 99, 46, 14);
		contentPane.add(lblNewLabel);
		
		RegisNom = new JTextField();
		RegisNom.setBounds(10, 124, 152, 20);
		contentPane.add(RegisNom);
		RegisNom.setColumns(10);
		
		JLabel lblPureza = new JLabel("Pureza");
		lblPureza.setBounds(10, 155, 46, 14);
		contentPane.add(lblPureza);
		
		RegisPur = new JTextField();
		RegisPur.setColumns(10);
		RegisPur.setBounds(10, 180, 152, 20);
		contentPane.add(RegisPur);
		
		JLabel lblToneladas = new JLabel("Toneladas");
		lblToneladas.setBounds(10, 211, 63, 14);
		contentPane.add(lblToneladas);
		
		RegisTone = new JTextField();
		RegisTone.setColumns(10);
		RegisTone.setBounds(10, 236, 152, 20);
		contentPane.add(RegisTone);
		
		JLabel precio = new JLabel("Precio");
		precio.setBounds(10, 267, 46, 14);
		contentPane.add(precio);
		
		RegisPrecio = new JTextField();
		RegisPrecio.setColumns(10);
		RegisPrecio.setBounds(10, 292, 152, 20);
		contentPane.add(RegisPrecio);
		
		JLabel lblNewLabel_1 = new JLabel("Registrar mineral");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(10, 39, 414, 25);
		contentPane.add(lblNewLabel_1);
		
		JButton BtnRegis = new JButton("Registrar");
		BtnRegis.addActionListener(e -> {
			try {
				Mineral nuevo = new Mineral(
						0,
						RegisNom.getText(),
						Float.parseFloat(RegisPur.getText()),
						Float.parseFloat(RegisTone.getText()),
						Float.parseFloat(RegisPrecio.getText())
						);
				
				
				
				
				 String msg = RegistrarMineRepository.registrarMineral(nuevo);
	                JOptionPane.showMessageDialog(this, msg);
	               
	            } catch (Exception ex) {
	                JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
	            }
	        });
		BtnRegis.setBounds(10, 368, 89, 23);
		contentPane.add(BtnRegis);
		
		JLabel info = new JLabel("");
		info.setText(Mineral.RegisMineral(null));
		info.setBounds(251, 372, 157, 14);
		contentPane.add(info);
		
		info.setText(Mineral.RegisMineral(mineral));
		JButton btnNewButton = new JButton("VOLVER");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				OpcionesDeposito atras = new OpcionesDeposito(deposito);
				atras.setVisible(true);
				dispose();
			}
		});
		btnNewButton.setBounds(109, 368, 89, 23);
		contentPane.add(btnNewButton);
		
	}

}
