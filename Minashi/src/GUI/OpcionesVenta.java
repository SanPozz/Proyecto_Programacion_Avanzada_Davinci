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
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import BLL.Clases.Venta;




public class OpcionesVenta extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTable tableOrdenes;
    private JTextField txtBuscarOrden;
    private List<Venta> listaVentas;

    public OpcionesVenta() {
        listaVentas = obtenerListaVentas();

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 600, 400);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(10, 10, 10, 10));
        setContentPane(contentPane);
        contentPane.setLayout(new BorderLayout());

        JLabel lblTitulo = new JLabel("Panel de Ventas", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Roboto", Font.BOLD, 20));
        contentPane.add(lblTitulo, BorderLayout.NORTH);

        JPanel panelBuscar = new JPanel();
        panelBuscar.setLayout(new FlowLayout());
        txtBuscarOrden = new JTextField(10);
        JButton btnBuscar = new JButton("Buscar Orden");
        btnBuscar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                buscarOrdenPorID();
            }
        });

        panelBuscar.add(new JLabel("Orden:"));
        panelBuscar.add(txtBuscarOrden);
        panelBuscar.add(btnBuscar);
        contentPane.add(panelBuscar, BorderLayout.SOUTH);

        tableOrdenes = new JTable();
        actualizarTabla();
        contentPane.add(new JScrollPane(tableOrdenes), BorderLayout.CENTER);

        setVisible(true);
    }


private List<Venta> obtenerListaVentas() {
    List<Venta> ventas = new ArrayList<>();
    ventas.add(new Venta(1, "Juan", "Pérez", 30, "juan@example.com", 3, 10));
    ventas.add(new Venta(2, "María", "López", 25, "maria@example.com", 3, 15));
    return ventas;
}

private void actualizarTabla() {
    DefaultTableModel modelo = new DefaultTableModel();
    modelo.addColumn("ID");
    modelo.addColumn("Nombre");
    modelo.addColumn("Apellido");
    modelo.addColumn("Correo");
    modelo.addColumn("Ventas");

    for (Venta v : listaVentas) {
        modelo.addRow(new Object[]{v.getIdUsuario(), v.getNombre(), v.getApellido(), v.getCorreo(), v.getCantVentas()});
    }

    tableOrdenes.setModel(modelo);
}

private void buscarOrdenPorID() {
    try {
        int idBuscado = Integer.parseInt(txtBuscarOrden.getText());
        for (Venta v : listaVentas) {
            if (v.getIdUsuario() == idBuscado) {
                JOptionPane.showMessageDialog(this, "Orden encontrada:\n" + v.toString(), "Resultado", JOptionPane.INFORMATION_MESSAGE);
                return;
            }
        }
        JOptionPane.showMessageDialog(this, "Orden no encontrada.", "Error", JOptionPane.ERROR_MESSAGE);
    } catch (NumberFormatException e) {
        JOptionPane.showMessageDialog(this, "Ingrese un ID válido.", "Error", JOptionPane.ERROR_MESSAGE);
    }
}
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

	    setBounds(100, 100, 524, 328);

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

	    JButton btnSolicitarStock = new JButton("Solicitar Stock");
	    btnSolicitarStock.setFont(new Font("Roboto", Font.PLAIN, 16));
	    btnSolicitarStock.addActionListener(new ActionListener() {
	      public void actionPerformed(ActionEvent e) {
	      }
	    });
	    panel.add(btnSolicitarStock);

	    btnSolicitarStock.addActionListener(new ActionListener() {
	      public void actionPerformed(ActionEvent e) {

	       
	    	setVisible(true);
	        dispose();

	      }
	    });

	    JButton btnFinalizarOrden = new JButton("Finalizar Orden");
	    btnFinalizarOrden.setFont(new Font("Roboto", Font.PLAIN, 16));
	    panel.add(btnFinalizarOrden);


	    JButton btnVerOrdenes = new JButton("Ver Ordenes");
	    btnVerOrdenes.setFont(new Font("Roboto", Font.PLAIN, 16));
	    panel.add(btnVerOrdenes);
	}
}
