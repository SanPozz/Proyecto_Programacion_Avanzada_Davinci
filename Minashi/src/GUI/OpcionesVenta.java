package GUI;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import BLL.Clases.Venta;
import DLL.Conexion.Conexion;

public class OpcionesVenta extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTable tableOrdenes;
    private Venta venta;
    private JTextField textField; // 🔹 Lo declaramos globalmente

    /**
     * Launch the application.
     */

    /**
     * Create the frame.
     */
    public OpcionesVenta(Venta venta) {
        this.venta = venta;
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 720, 445);

        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(10, 10, 10, 10));
        setContentPane(contentPane);
        contentPane.setLayout(new GridLayout(0, 1, 0, 0));

        JLabel lblPanel = new JLabel("Panel de Ventas");
        lblPanel.setFont(new Font("Roboto", Font.BOLD, 24));
        lblPanel.setHorizontalAlignment(SwingConstants.CENTER);
        contentPane.add(lblPanel);

        JLabel lblTitulo = new JLabel("Órdenes de Venta");
        lblTitulo.setFont(new Font("Dialog", Font.BOLD, 18));
        lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        contentPane.add(lblTitulo);

        tableOrdenes = new JTable();
        JScrollPane scrollPane = new JScrollPane(tableOrdenes);
        contentPane.add(scrollPane);

        JPanel panel = new JPanel();
        panel.setBorder(new EmptyBorder(10, 20, 10, 20));
        panel.setLayout(new GridLayout(0, 3, 10, 10)); // 🔹 Asegura alineación correcta
        contentPane.add(panel);

        agregarBotones(panel);

        actualizarTabla();

        setVisible(true);
    }

    private void agregarBotones(JPanel panel) {
        JButton btnSolicitarStock = new JButton("Solicitar Stock");
        btnSolicitarStock.setFont(new Font("Roboto", Font.PLAIN, 16));
        btnSolicitarStock.addActionListener(e -> solicitarStock());
        panel.add(btnSolicitarStock);

        JButton btnFinalizarOrden = new JButton("Finalizar Orden");
        btnFinalizarOrden.setFont(new Font("Roboto", Font.PLAIN, 16));
        btnFinalizarOrden.addActionListener(e -> finalizarVenta());
        panel.add(btnFinalizarOrden);

        // 🔹 Panel para el campo de texto y el botón Buscar (alineados verticalmente)
        JPanel panelBuscar = new JPanel(new GridLayout(0, 1, 0, 5));
        panel.add(panelBuscar);

        // Campo de texto para búsqueda de órdenes
        textField = new JTextField();
        textField.setColumns(10);
        panelBuscar.add(textField);

        // Botón Buscar
        JButton btnBuscarOrden = new JButton("Buscar");
        btnBuscarOrden.setFont(new Font("Roboto", Font.PLAIN, 16));
        panelBuscar.add(btnBuscarOrden);

        // Acción para buscar orden al presionar el botón
        btnBuscarOrden.addActionListener(e -> buscarOrdenPorID(textField.getText()));
    }

    // 🔹 Método para actualizar la tabla con órdenes desde la base de datos
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

            tableOrdenes.setModel(modelo);

        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error al obtener órdenes.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // 🔹 Método para solicitar stock
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

    // 🔹 Método para finalizar una orden
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

    // 🔹 Método para buscar una orden por ID
    private void buscarOrdenPorID(String idOrden) {
        try {
            int idBuscado = Integer.parseInt(idOrden);

            try (Connection con = Conexion.getInstance().getConnection();
                 PreparedStatement stmt = con.prepareStatement("SELECT id, total, peso, fecha, estado FROM ordenes WHERE id = ?")) {

                stmt.setInt(1, idBuscado);
                ResultSet rs = stmt.executeQuery();

                if (rs.next()) {
                    JOptionPane.showMessageDialog(this, "Orden encontrada:\nID: " + rs.getInt("id") +
                            "\nTotal: " + rs.getInt("total") +
                            "\nPeso: " + rs.getInt("peso") +
                            "\nFecha: " + rs.getTimestamp("fecha") +
                            "\nEstado: " + rs.getString("estado"), "Resultado", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(this, "Orden no encontrada.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }

        } catch (NumberFormatException | SQLException e) {
            JOptionPane.showMessageDialog(this, "Ingrese un ID válido.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}