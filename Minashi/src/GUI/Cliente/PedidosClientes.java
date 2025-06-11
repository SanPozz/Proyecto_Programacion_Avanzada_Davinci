package GUI.Cliente;

import BLL.Clases.Cliente;
import BLL.Clases.Mineral;
import BLL.Clases.OrdenDeCompra;
import DLL.Repository.OrdenesRepository;
import GUI.Cliente.DetallePedido;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;



public class PedidosClientes extends JFrame {

  private static final long serialVersionUID = 1L;
  private JPanel contentPane;

  /**
   * Create the frame.
   */

  public PedidosClientes(Cliente cliente) {

    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setBounds(100, 100, 793, 466);
    contentPane = new JPanel();
    contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

    setContentPane(contentPane);

    DefaultTableModel model = new DefaultTableModel(new String[]{"ID", "Total", "Fecha", "Estado"}, 0);
    JTable table = new JTable(model);

    imprimirTabla(cliente.encontrarOrdenes(), model);

    JScrollPane scrollPane = new JScrollPane(table);

    contentPane.setLayout(new GridLayout(0, 1, 0, 0));
    contentPane.add(scrollPane);

    JPanel panel = new JPanel();
    panel.setBorder(new EmptyBorder(10, 20, 10, 20));
    contentPane.add(panel);
    panel.setLayout(new GridLayout(0, 3, 0, 10));

    JPanel panel_1 = new JPanel();
    panel_1.setBorder(new EmptyBorder(20, 0, 20, 0));
    panel.add(panel_1);
    panel_1.setLayout(new GridLayout(0, 2, 0, 0));

    JButton btnNewButton = new JButton("Ver Detalle");
    btnNewButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {

        int selectedRow = table.getSelectedRow();
        if (selectedRow == -1) {
          JOptionPane.showMessageDialog(null, "Por favor, seleccione un pedido para ver los detalles.");
          return;
        }

        int idOrden = (int) model.getValueAt(selectedRow, 0);


        ArrayList<Mineral> minerales = OrdenesRepository.detalleOrden(idOrden);

        String fechaStr = model.getValueAt(selectedRow, 2).toString();



        DetallePedido detallePedido = new DetallePedido(
            minerales,
            idOrden,
            fechaStr
        );

        detallePedido.setVisible(true);




      }
    });

    panel_1.add(btnNewButton);

    JPanel panel_2 = new JPanel();
    panel.add(panel_2);
    panel_2.setBorder(new EmptyBorder(30, 30, 30, 30));
    panel_2.setLayout(new GridLayout(0, 1, 0, 0));

    JTextField textField = new JTextField();
    panel_2.add(textField);
    textField.setColumns(10);

    JButton btnNewButton_2 = new JButton("Buscar por ID");
    panel_2.add(btnNewButton_2);

    btnNewButton_2.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {

        String id = textField.getText();
        if (id.isEmpty()) {
          JOptionPane.showMessageDialog(null, "Por favor, ingrese un ID de pedido.");
          return;
        }

        ArrayList<OrdenDeCompra> pedidos = OrdenDeCompra.buscarPorId(Integer.parseInt(id));
        if (pedidos.isEmpty()) {
          JOptionPane.showMessageDialog(null, "No se encontraron pedidos con el ID: " + id);
        } else {
          imprimirTabla(pedidos, model);
        }

      }
    });


  }

  static public void imprimirTabla(ArrayList<OrdenDeCompra> pedidos, DefaultTableModel model) {

    model.setRowCount(0);

    for (OrdenDeCompra pedido : pedidos) {
      model.addRow(new Object[]{
            pedido.getIdOrden(),
            pedido.getTotal(),
            pedido.getFecha(),
            pedido.getEstado()
      });
    }


  }

}

