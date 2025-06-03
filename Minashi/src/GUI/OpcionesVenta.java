package GUI;

import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import com.mysql.jdbc.Connection;

import DLL.Conexion.Conexion;
import BLL.Clases.Venta;

import java.awt.GridLayout;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class OpcionesVenta extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public OpcionesVenta(Venta venta) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    setBounds(100, 100, 450, 300);
	    contentPane = new JPanel();
	    contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

	    setContentPane(contentPane);
	    contentPane.setLayout(new GridLayout(0, 1, 0, 0));

	    JLabel lblNewLabel = new JLabel("Panel Venta");
	    lblNewLabel.setFont(new Font("Roboto", Font.BOLD, 20));
	    lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
	    contentPane.add(lblNewLabel);

	    JPanel panel = new JPanel();
	    contentPane.add(panel);
	    panel.setBorder(new EmptyBorder(20,10,20,10));
	    panel.setLayout(new GridLayout(0, 2, 15, 15));

	    JButton btnNewButton = new JButton("Solicitar Stock");
	    btnNewButton.setFont(new Font("Roboto", Font.PLAIN, 16));
	    btnNewButton.addActionListener(new ActionListener() {
	      public void actionPerformed(ActionEvent e) {
	      }
	    });
	    panel.add(btnNewButton);

	    btnNewButton.addActionListener(new ActionListener() {
	      public void actionPerformed(ActionEvent e) {

	       
	    	setVisible(true);
	        dispose();

	      }
	    });

	    JButton btnVerOrdenes = new JButton("Ver Ordenes");
	    btnVerOrdenes.setFont(new Font("Roboto", Font.PLAIN, 16));
	    panel.add(btnVerOrdenes);
	}
}
