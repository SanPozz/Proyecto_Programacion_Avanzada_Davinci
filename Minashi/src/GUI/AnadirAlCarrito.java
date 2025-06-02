package GUI;

import BLL.Clases.Cliente;
import BLL.Clases.Mineral;

import java.awt.EventQueue;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;
import java.awt.Font;
import java.awt.BorderLayout;

public class AnadirAlCarrito extends JFrame {

  private static final long serialVersionUID = 1L;
  private JPanel contentPane;
  private JTextField textField;


  public AnadirAlCarrito(Mineral mineral, Cliente cliente) {
    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    setBounds(100, 100, 600, 400);
    contentPane = new JPanel();
    contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

    setContentPane(contentPane);
    contentPane.setLayout(new GridLayout(0, 2, 0, 0));

    JPanel panel = new JPanel();
    contentPane.add(panel);
    panel.setLayout(new GridLayout(0, 1, 0, 0));
    panel.setBorder(new EmptyBorder(20, 20, 20, 20));

    textField = new JTextField();
    panel.add(textField);
    textField.setColumns(10);

    JButton btnNewButton = new JButton("Añadir al carrito");
    btnNewButton.setFont(new Font("Roboto", Font.PLAIN, 14));
    btnNewButton.addActionListener(e -> {
      String cantidadStr = textField.getText();
      if (cantidadStr.isEmpty()) {
        JOptionPane.showMessageDialog(null, "Por favor, introduzca una cantidad.");
        return;
      }
      try {
        Double cantidad = Double.parseDouble(cantidadStr);
        if (cantidad <= 0) {
          JOptionPane.showMessageDialog(null, "La cantidad debe ser un número positivo.");
        }

        if (cantidad > mineral.getToneladas()) {
          JOptionPane.showMessageDialog(null, "No hay suficientes toneladas en stock.");
        }

        if (cantidad <= mineral.getToneladas()) {

          System.out.println();
          cliente.anadirAlCarrito(new Mineral(
              mineral.getIdMineral(),
              mineral.getTipo(),
              mineral.getPureza(),
              cantidad,
              mineral.getPrecioTonelada()
          ));

          JOptionPane.showMessageDialog(null, "Mineral añadido al carrito con éxito.");
          dispose();
        }

//        JOptionPane.showMessageDialog(null, "Mineral añadido al carrito con éxito.");
      } catch (NumberFormatException ex) {
        JOptionPane.showMessageDialog(null, "Por favor, introduzca un número válido.");
      }
    });
    panel.add(btnNewButton);

    JPanel panel_1 = new JPanel();
    contentPane.add(panel_1);
    panel_1.setLayout(new GridLayout(0, 1, 0, 0));

    JLabel lblNewLabel = new JLabel("Mineral Seleccionado");
    lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
    lblNewLabel.setFont(new Font("Roboto", Font.BOLD, 16));
    panel_1.add(lblNewLabel);

    JSeparator separator = new JSeparator();
    panel_1.add(separator);

    JLabel lblNewLabel_1 = new JLabel("ID: " + mineral.getIdMineral());
    lblNewLabel_1.setFont(new Font("Roboto", Font.PLAIN, 14));
    lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
    panel_1.add(lblNewLabel_1);

    JLabel lblNewLabel_2 = new JLabel("Tipo: " + mineral.getTipo());
    lblNewLabel_2.setFont(new Font("Roboto", Font.PLAIN, 14));
    lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
    panel_1.add(lblNewLabel_2);

    JLabel lblNewLabel_3 = new JLabel("Pureza: " + mineral.getPureza() + "%");
    lblNewLabel_3.setFont(new Font("Roboto", Font.PLAIN, 14));
    lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
    panel_1.add(lblNewLabel_3);

    JLabel lblNewLabel_4 = new JLabel("Toneladas en stock: " + mineral.getToneladas());
    lblNewLabel_4.setFont(new Font("Roboto", Font.PLAIN, 14));
    lblNewLabel_4.setHorizontalAlignment(SwingConstants.CENTER);
    panel_1.add(lblNewLabel_4);

    JLabel lblNewLabel_5 = new JLabel("Precio por tonelada: " + mineral.getPrecioTonelada() + "$");
    lblNewLabel_5.setFont(new Font("Roboto", Font.PLAIN, 14));
    lblNewLabel_5.setHorizontalAlignment(SwingConstants.CENTER);
    panel_1.add(lblNewLabel_5);

  }

}
