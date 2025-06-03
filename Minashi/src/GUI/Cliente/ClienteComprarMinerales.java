package GUI.Cliente;

import BLL.Clases.Cliente;
import BLL.Clases.Mineral;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

public class ClienteComprarMinerales extends JFrame {

  private static final long serialVersionUID = 1L;
  private JPanel contentPane;
  /**
   * Create the frame.
   */
  public ClienteComprarMinerales(Cliente cliente) {

    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setBounds(100, 100, 900, 445);
    contentPane = new JPanel();
    contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

    setContentPane(contentPane);

    DefaultTableModel model = new DefaultTableModel(new String[]{"ID", "Nombre", "Pureza", "Toneladas", "Precio por Tonelada"}, 0);
    JTable table = new JTable(model);

    ArrayList<Mineral> minerales = Mineral.mineralesEnStock();

    imprimirTabla(minerales, model);

    JScrollPane scrollPane = new JScrollPane(table);

    contentPane.setLayout(new GridLayout(0, 1, 0, 0));
    contentPane.add(scrollPane);

    JPanel panel = new JPanel();
    panel.setBorder(new EmptyBorder(10,20,10,20));
    contentPane.add(panel);
    panel.setLayout(new GridLayout(0, 3, 0, 10));

    JPanel panel_1 = new JPanel();
    panel_1.setBorder(new EmptyBorder(20,0,20,0));
    panel.add(panel_1);
    panel_1.setLayout(new GridLayout(0, 2, 0, 0));

    JButton btnNewButton = new JButton("Anadir al carrito");
    btnNewButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {

        if (table.getSelectedRow() == -1) {
          JOptionPane.showMessageDialog(null, "Por favor, seleccione un mineral de la tabla.");
        }

        if (table.getSelectedRow() != -1) {

          int idMineral = (int) table.getValueAt(table.getSelectedRow(), 0);
          String tipo = (String) table.getValueAt(table.getSelectedRow(), 1);
          double pureza = (double) table.getValueAt(table.getSelectedRow(), 2);
          double toneladas = (double) table.getValueAt(table.getSelectedRow(), 3);
          double precioTonelada = (double) table.getValueAt(table.getSelectedRow(), 4);

          Mineral mineralSeleccionado = new Mineral(idMineral, tipo, pureza, toneladas, precioTonelada);

          AnadirAlCarrito anadirAlCarrito = new AnadirAlCarrito(mineralSeleccionado, cliente);

            anadirAlCarrito.setVisible(true);

        }

      }
    });
    panel_1.add(btnNewButton);

    JButton btnNewButton_1 = new JButton("Ver Carrito");
    panel_1.add(btnNewButton_1);
    btnNewButton_1.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {

        CarritoCliente carritoCliente = new CarritoCliente(cliente);
        carritoCliente.setVisible(true);


      }
    });

    JPanel panel_2 = new JPanel();
    panel.add(panel_2);
    panel_2.setBorder(new EmptyBorder(30,30,30,30));
    panel_2.setLayout(new GridLayout(0, 1, 0, 0));

    JTextField textField = new JTextField();
    panel_2.add(textField);
    textField.setColumns(10);

    JButton btnNewButton_2 = new JButton("Buscar");
    panel_2.add(btnNewButton_2);

    btnNewButton_2.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {

        ArrayList<Mineral> minerales = Mineral.mineralesPorNombre(textField.getText());

        if (minerales == null || minerales.size() == 0) {
          JOptionPane.showMessageDialog(null, "No se encontraron minerales con ese nombre.");
        }


        if (minerales != null && !minerales.isEmpty()) {
          imprimirTabla(minerales, model);
        }

      }
    });

    JPanel panel_3 = new JPanel();
    panel.add(panel_3);
    panel_3.setLayout(null);

    JComboBox comboBox = new JComboBox();
    comboBox.setBounds(45, 53, 131, 49);
    comboBox.addItem("Toneladas");
    comboBox.addItem("Pureza");
    panel_3.add(comboBox);

    JLabel lblNewLabel = new JLabel("Ordenar Por:");
    lblNewLabel.setBounds(44, 28, 80, 14);
    panel_3.add(lblNewLabel);

    JButton btnNewButton_3 = new JButton("Ordenar");
    btnNewButton_3.setBounds(68, 111, 89, 23);
    panel_3.add(btnNewButton_3);

    btnNewButton_3.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        ArrayList mineralesOrdenados = Mineral.ordenarMinerales(minerales, (String) comboBox.getSelectedItem());

        imprimirTabla(mineralesOrdenados, model);

      }
    });

  }

  static public void imprimirTabla(ArrayList<Mineral> minerales, DefaultTableModel model) {

    model.setRowCount(0);

    for (Mineral mineral : minerales) {
      model.addRow(new Object[]{mineral.getIdMineral(), mineral.getTipo(), mineral.getPureza(), mineral.getToneladas(), mineral.getPrecioTonelada()});
    }


  }

}
