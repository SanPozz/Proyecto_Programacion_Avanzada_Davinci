package GUI;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import BLL.Clases.Venta;
import DLL.Conexion.Conexion;

public class OpcionesVenta extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField txtBuscarOrden;
    private Venta venta;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Venta venta = new Venta(2, "Sebastián", "Onacht", 23, "seba@gmail.com", 3, 10);
                    OpcionesVenta frame = new OpcionesVenta(venta);
                    frame.setVisible(true);
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
        this.venta = venta;
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 600, 400);

        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(10, 10, 10, 10));
        setContentPane(contentPane);
        contentPane.setLayout(new BorderLayout());

        JLabel lblTitulo = new JLabel("Panel de Ventas");
        lblTitulo.setFont(new Font("Roboto", Font.BOLD, 20));
        lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        contentPane.add(lblTitulo, BorderLayout.NORTH);

        JPanel panelBotones = new JPanel();
        panelBotones.setBorder(new EmptyBorder(20, 10, 20, 10));
        panelBotones.setLayout(new GridLayout(0, 2, 15, 15));
        contentPane.add(panelBotones, BorderLayout.CENTER);

        agregarBotones(panelBotones);

        actualizarTabla();

        setVisible(true);
    }

    private void agregarBotones(JPanel panel) {
        JButton btnVerOrdenes = new JButton("Ver Órdenes");
        btnVerOrdenes.setFont(new Font("Roboto", Font.PLAIN, 16));
        btnVerOrdenes.addActionListener(e -> actualizarTabla());
        panel.add(btnVerOrdenes);

        JButton btnSolicitarStock = new JButton("Solicitar Stock");
        btnSolicitarStock.setFont(new Font("Roboto", Font.PLAIN, 16));
        btnSolicitarStock.addActionListener(e -> solicitarStock());
        panel.add(btnSolicitarStock);

        JButton btnFinalizarOrden = new JButton("Finalizar Orden");
        btnFinalizarOrden.setFont(new Font("Roboto", Font.PLAIN, 16));
        btnFinalizarOrden.addActionListener(e -> finalizarVenta());
        panel.add(btnFinalizarOrden);
    }

    private void actualizarTabla() {
        DefaultTableModel modelo = new DefaultTableModel();
        modelo.addColumn("ID");
        modelo.addColumn("Total");
        modelo.addColumn("Peso");
        modelo.addColumn("Fecha");
        modelo.addColumn("Estado");

        try (Connection con = Conexion.getInstance().getConnection();
             PreparedStatement stmt = con.prepareStatement("SELECT id, total, peso, fecha, estado FROM ordenes WHERE comprador = ?")) {

            stmt.setInt(1, venta.getIdUsuario());
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                modelo.addRow(new Object[]{rs.getInt("id"), rs.getInt("total"), rs.getInt("peso"), rs.getTimestamp("fecha"), rs.getString("estado")});
            }

        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error al obtener órdenes.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void solicitarStock() {
        String idMineral = JOptionPane.showInputDialog(this, "Ingrese ID del mineral:");
        String cantidad = JOptionPane.showInputDialog(this, "Ingrese toneladas:");

        try {
            venta.solicitarStock(Integer.parseInt(idMineral), Double.parseDouble(cantidad));
            JOptionPane.showMessageDialog(this, "Stock solicitado correctamente.", "Información", JOptionPane.INFORMATION_MESSAGE);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Ingrese datos válidos.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void finalizarVenta() {
        String idOrden = JOptionPane.showInputDialog(this, "Ingrese ID de la orden a finalizar:");

        try {
            venta.finalizarVenta(Integer.parseInt(idOrden));
            actualizarTabla();
            JOptionPane.showMessageDialog(this, "Orden finalizada correctamente.", "Información", JOptionPane.INFORMATION_MESSAGE);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Ingrese un ID válido.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}