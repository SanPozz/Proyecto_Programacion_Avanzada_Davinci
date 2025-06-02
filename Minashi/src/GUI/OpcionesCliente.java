package GUI;

import BLL.Clases.Cliente;
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

public class OpcionesCliente extends JFrame {

  private static final long serialVersionUID = 1L;
  private JPanel contentPane;

  public OpcionesCliente(Cliente cliente) {
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setBounds(100, 100, 450, 300);
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
    panel.setLayout(new GridLayout(0, 2, 15, 15));

    JButton btnNewButton = new JButton("Comprar Minerales");
    btnNewButton.setFont(new Font("Roboto", Font.PLAIN, 16));
    btnNewButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
      }
    });
    panel.add(btnNewButton);

    btnNewButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {

        ClienteComprarMinerales clienteComMin = new ClienteComprarMinerales(cliente);
        clienteComMin.setVisible(true);
        dispose();

      }
    });
    

    JButton btnVerPedidos = new JButton("Ver Pedidos");
    btnVerPedidos.setFont(new Font("Roboto", Font.PLAIN, 16));
    panel.add(btnVerPedidos);
  }

}
