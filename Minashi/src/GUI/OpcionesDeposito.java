package GUI;

import javax.swing.JFrame;
import javax.swing.JPanel;

import javax.swing.JLabel;

import javax.swing.SwingConstants;

import BLLL.Clases.Deposito;
import BLLL.Clases.Mineral;

import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class OpcionesDeposito extends JFrame {
	private static final long serialVersionUID = 1L;

	public OpcionesDeposito(Deposito deposito) {

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 633, 300);
		getContentPane().setLayout(null);

		JLabel lblNewLabel = new JLabel("Panel Deposito");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(10, 11, 591, 94);
		lblNewLabel.setFont(new Font("Dialog", Font.BOLD, 20));
		getContentPane().add(lblNewLabel);

		JPanel panel = new JPanel();
		panel.setBounds(10, 139, 591, 111);
		getContentPane().add(panel);

		JButton btnNewButton = new JButton("VER STOCK");
		btnNewButton.setFont(new Font("Dialog", Font.PLAIN, 16));
		btnNewButton.setBounds(216, 35, 160, 45);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		panel.setLayout(null);
		panel.add(btnNewButton);

		JButton btnNewButton_1 = new JButton("REGIS MINERAL");
		btnNewButton_1.setFont(new Font("Dialog", Font.PLAIN, 16));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RegistrarMineral reMin = new RegistrarMineral(deposito);
				reMin.setVisible(true);
				dispose();
			}
		});
		btnNewButton_1.setBounds(10, 35, 160, 45);
		panel.add(btnNewButton_1);

		JButton btnNewButton_2 = new JButton("SALIR");
		btnNewButton_2.setFont(new Font("Dialog", Font.PLAIN, 16));
		btnNewButton_2.setForeground(Color.RED);
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnNewButton_2.setBounds(421, 35, 160, 45);
		panel.add(btnNewButton_2);

		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				DepositoVerStock verStock = new DepositoVerStock(deposito);
				verStock.setVisible(true);
				dispose();

			}
		});

	}
}
