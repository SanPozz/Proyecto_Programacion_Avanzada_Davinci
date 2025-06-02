package GUI;

import BLL.Clases.Deposito;
import BLL.Clases.Usuario;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.GridLayout;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import java.awt.BorderLayout;
import javax.swing.JTextPane;
import java.awt.FlowLayout;


public class OpcionesDeposito extends JFrame{
	private static final long serialVersionUID = 1L;
	  
	public OpcionesDeposito(Deposito deposito) {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Panel Deposito");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(10, 11, 414, 94);
		lblNewLabel.setFont(new Font("DialogInput", Font.BOLD, 20));
		getContentPane().add(lblNewLabel);
		
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 139, 414, 111);
		getContentPane().add(panel);
		
		JButton btnNewButton = new JButton("VER STOCK");
		btnNewButton.setBounds(244, 34, 160, 45);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		panel.setLayout(null);
		panel.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("REGIS MINERAL");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton_1.setBounds(10, 35, 160, 45);
		panel.add(btnNewButton_1);

	    btnNewButton.addActionListener(new ActionListener() {
	      public void actionPerformed(ActionEvent e) {

	    	  DepositoVerStock verStock = new DepositoVerStock(deposito);
	    	  verStock.setVisible(true);
	        dispose();

	      }
	    });
		
		
		
		
		  
	}
}
