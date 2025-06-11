package GUI.Cliente;

import BLL.Clases.Mineral;

import java.awt.EventQueue;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.util.Date;
import javax.swing.JSeparator;
import javax.swing.JScrollPane;

public class DetallePedido extends JFrame {

  private static final long serialVersionUID = 1L;
  private JPanel contentPane;

  public DetallePedido(ArrayList<Mineral> minerales, int idPedido, String fechaPedido) {
    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    setBounds(100, 100, 700, 500);
    contentPane = new JPanel();
    contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

    setContentPane(contentPane);
    contentPane.setLayout(null);

    JLabel lblNewLabel = new JLabel("New label");
    lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
    lblNewLabel.setBounds(164, 11, 321, 43);
    contentPane.add(lblNewLabel);

    lblNewLabel.setText("Detalle del Pedido con ID: " + idPedido);
    lblNewLabel.setFont(new Font("Roboto", Font.BOLD, 22));

    JScrollPane scrollPane = new JScrollPane();
    scrollPane.setBounds(10, 71, 664, 379);
    contentPane.add(scrollPane);

    JLabel lblNewLabel_1 = new JLabel("New label");
    lblNewLabel_1.setFont(new Font("Roboto", Font.ITALIC, 16));
    lblNewLabel_1.setBounds(10, 49, 124, 14);
    contentPane.add(lblNewLabel_1);
    lblNewLabel_1.setText("Fecha: " + fechaPedido.toString());

    JLabel lblNewLabel_1_1 = new JLabel("Peso: " + minerales.stream().mapToDouble(Mineral::getToneladas).sum() + " toneladas");
    lblNewLabel_1_1.setFont(new Font("Roboto", Font.ITALIC, 16));
    lblNewLabel_1_1.setBounds(550, 51, 124, 14);
    contentPane.add(lblNewLabel_1_1);

    JPanel panelMinerales = new JPanel();
    panelMinerales.setLayout(new javax.swing.BoxLayout(panelMinerales, javax.swing.BoxLayout.Y_AXIS));

    for (Mineral mineral : minerales) {
      JLabel lblMineral = new JLabel();
      lblMineral.setText("Mineral: " + mineral.getTipo() + ", Pureza: " + mineral.getPureza() + ", Toneladas: " + mineral.getToneladas());
      lblMineral.setFont(new Font("Roboto", Font.PLAIN, 16));
      panelMinerales.add(lblMineral);
    }

    scrollPane.setViewportView(panelMinerales);


  }
}
