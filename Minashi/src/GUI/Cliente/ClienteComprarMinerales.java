package GUI.Cliente;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;



import BLLL.Clases.Cliente;
import BLLL.Clases.Mineral;

public class ClienteComprarMinerales extends JFrame {

  private static final long serialVersionUID = 1L;
  private JPanel contentPane;
  private static JTable table;
 
  public ClienteComprarMinerales(Cliente cliente) {
	ArrayList<Mineral> minerales = Mineral.mineralesEnStock();
	
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setBounds(100, 100, 900, 445);
    contentPane = new JPanel();
    contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

    setContentPane(contentPane);


    DefaultTableModel model = new DefaultTableModel(new String[]{"ID", "Nombre", "Pureza", "Toneladas", "Precio por Tonelada"}, 0);

    table = new JTable(model);

 


    imprimirTabla(minerales, model);

    JScrollPane scrollPane = new JScrollPane(table);

    contentPane.setLayout(new GridLayout(0, 1, 0, 0));
    contentPane.add(scrollPane);

    JPanel panel = new JPanel();
    panel.setBorder(new EmptyBorder(10,20,10,20));
    contentPane.add(panel);
    panel.setLayout(null);

    JPanel panel_1 = new JPanel();
    panel_1.setBounds(20, 10, 278, 407);
    panel_1.setBorder(new EmptyBorder(20,0,20,0));
    panel.add(panel_1);

    JButton btnNewButton = new JButton("Anadir al carrito");
    btnNewButton.setBounds(0, 20, 139, 165);
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
    panel_1.setLayout(null);
    panel_1.add(btnNewButton);

    JButton btnNewButton_1 = new JButton("Ver Carrito");
    btnNewButton_1.setBounds(139, 20, 139, 165);
    panel_1.add(btnNewButton_1);
    btnNewButton_1.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {

        CarritoCliente carritoCliente = new CarritoCliente(cliente);
        carritoCliente.setVisible(true);


      }
    });

    JPanel panel_2 = new JPanel();
    panel_2.setBounds(298, 10, 278, 407);
    panel.add(panel_2);
    panel_2.setBorder(new EmptyBorder(30,30,30,30));
    panel_2.setLayout(null);

    JTextField textField = new JTextField();
    textField.setBounds(30, 30, 182, 72);
    panel_2.add(textField);
    textField.setColumns(10);

    JButton btnNewButton_2 = new JButton("Buscar");
    btnNewButton_2.setBounds(30, 113, 182, 72);
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
    panel_3.setBounds(576, 10, 278, 255);
    panel.add(panel_3);
    panel_3.setLayout(null);

    JComboBox comboBox = new JComboBox();
    comboBox.setBounds(10, 51, 131, 49);
    comboBox.addItem("Toneladas");
    comboBox.addItem("Pureza");
    panel_3.add(comboBox);

    JLabel lblNewLabel = new JLabel("Ordenar Por:");
    lblNewLabel.setBounds(44, 28, 80, 14);
    panel_3.add(lblNewLabel);

    JButton btnNewButton_3 = new JButton("Ordenar");
    btnNewButton_3.setBounds(10, 111, 89, 23);
    panel_3.add(btnNewButton_3);
    JButton botonAtras = new JButton("<- VOLVER");
    panel_3.add(botonAtras);
    botonAtras.setBounds(157, 51, 121, 72);
    botonAtras.addActionListener(new ActionListener() {
    	public void actionPerformed(ActionEvent e) {
    		OpcionesCliente atras = new OpcionesCliente(cliente);
    		atras.setVisible(true);
    		dispose();
    		
    	}
    });

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
	    	String estado = mineral.getEstado();
	        if (estado == null || !mineral.getEstado().equalsIgnoreCase("eliminado")) {
	            model.addRow(new Object[]{
	                mineral.getIdMineral(),
	                mineral.getNombre(),
	                mineral.getPureza(),
	                mineral.getToneladas(),
	                mineral.getPrecioTonelada(),
	                mineral.getEstado()
	            });
	        }
	    }
	    }

  static public void refrescarTabla() {
    DefaultTableModel model = (DefaultTableModel) table.getModel();
    ArrayList<Mineral> minerales = Mineral.mineralesEnStock();
    imprimirTabla(minerales, model);
  }

}
