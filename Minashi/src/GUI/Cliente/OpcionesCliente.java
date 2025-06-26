package GUI.Cliente;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;



import BLLL.Clases.Cliente;

import javax.swing.JLabel;
import java.awt.GridLayout;
import javax.swing.SwingConstants;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class OpcionesCliente extends JFrame {

  private static final long serialVersionUID = 1L;
  private JPanel contentPane;

  public OpcionesCliente(Cliente cliente) {
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setBounds(100, 100, 633, 300);
    contentPane = new JPanel();
    contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

    setContentPane(contentPane);
    contentPane.setLayout(new GridLayout(0, 1, 0, 0));

    JLabel lblNewLabel = new JLabel("Panel Cliente");
    lblNewLabel.setFont(new Font("Roboto", Font.BOLD, 20));
    lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
    contentPane.add(lblNewLabel);

    JPanel panel = new JPanel();
    contentPane.add(panel);
    panel.setBorder(new EmptyBorder(20,10,20,10));

    JButton btnNewButton = new JButton("Comprar Minerales");
    btnNewButton.setBounds(10, 52, 167, 45);
    btnNewButton.setFont(new Font("Roboto", Font.PLAIN, 16));
    btnNewButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
      }
    });
    panel.setLayout(null);
    panel.add(btnNewButton);

    btnNewButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {

        ClienteComprarMinerales clienteComMin = new ClienteComprarMinerales(cliente);
        clienteComMin.setVisible(true);
        dispose();

      }
    });
    
	JButton btnNewButton_2 = new JButton("SALIR");
	btnNewButton_2.setFont(new Font("Dialog", Font.PLAIN, 16));
	btnNewButton_2.setForeground(Color.RED);
	btnNewButton_2.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			System.exit(0);
		}
	});
	btnNewButton_2.setBounds(430, 52, 167, 45);
	panel.add(btnNewButton_2);
	
	    JButton btnVerPedidos = new JButton("Ver Pedidos");
	    btnVerPedidos.setBounds(222, 52, 167, 45);
	    btnVerPedidos.setFont(new Font("Roboto", Font.PLAIN, 16));
	    panel.add(btnVerPedidos);
	    
	        btnVerPedidos.addActionListener(new ActionListener() {
	          public void actionPerformed(ActionEvent e) {
	    
	            PedidosClientes pedidosClientes = new PedidosClientes(cliente);
	            pedidosClientes.setVisible(true);
	            dispose();
	    
	          }
	        });
  }

}