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
import javax.swing.table.TableModel;

public class CarritoCliente extends JFrame {

  private static final long serialVersionUID = 1L;
  private JPanel contentPane;
  /**
   * Create the frame.
   */
  public CarritoCliente(Cliente cliente) {

    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    setBounds(100, 100, 751, 381);
    contentPane = new JPanel();
    contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

    setContentPane(contentPane);

    DefaultTableModel model = new DefaultTableModel(new String[]{"ID", "Nombre", "Pureza", "Toneladas", "Precio por Tonelada"}, 0);
    JTable table = new JTable(model);


    imprimirTabla(cliente.getCarrito(), model);

    JScrollPane scrollPane = new JScrollPane(table);

    contentPane.setLayout(new GridLayout(0, 1, 0, 0));
    contentPane.add(scrollPane);

    JPanel panel = new JPanel();
    panel.setBorder(new EmptyBorder(10,20,10,20));
    contentPane.add(panel);
    panel.setLayout(new GridLayout(0, 2, 0, 10));

    JPanel panel_1 = new JPanel();
    panel_1.setBorder(new EmptyBorder(20,0,20,0));
    panel.add(panel_1);
    panel_1.setLayout(new GridLayout(0, 2, 0, 0));

    JButton btnNewButton = new JButton("Finalizar Compra");
    btnNewButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {

      }
    });

    panel_1.add(btnNewButton);

    JPanel panel_2 = new JPanel();
    panel.add(panel_2);
    panel_2.setBorder(new EmptyBorder(30,30,30,30));
    panel_2.setLayout(new GridLayout(0, 1, 0, 0));

    JLabel lblNewLabel = new JLabel("Total: " + cliente.calcularTotalCarrito() + "$");
    lblNewLabel.setFont(new Font("Roboto", Font.PLAIN, 18));
    panel_2.add(lblNewLabel);

    JButton btnNewButton_1 = new JButton("Eliminar del Carrito");
    panel_1.add(btnNewButton_1);
    btnNewButton_1.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {

        if (table.getSelectedRow() == -1) {
          JOptionPane.showMessageDialog(null, "Por favor, seleccione un mineral del carrito.");
        } else {
          int selectedRow = table.getSelectedRow();
          TableModel model = table.getModel();
          int idMineral = (int) model.getValueAt(selectedRow, 0);
          cliente.eliminarDelCarrito(idMineral);
            imprimirTabla(cliente.getCarrito(), (DefaultTableModel) model);
            JOptionPane.showMessageDialog(null, "Mineral eliminado del carrito con éxito.");
            lblNewLabel.setText("Total: " + cliente.calcularTotalCarrito() + "$");
        }
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
