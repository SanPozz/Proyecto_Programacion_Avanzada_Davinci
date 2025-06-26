package GUI;

import BLLL.Clases.OrdenDeCompra;
import BLLL.Clases.Venta;
import DLL.Repository.OrdenesRepository;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class OpcionesVenta extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;

    public OpcionesVenta(Venta venta) {
        setTitle("Órdenes de Compra");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 800, 471);

        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(10, 10, 10, 10));
        setContentPane(contentPane);

        // ---------------- Tabla ----------------
        DefaultTableModel model = new DefaultTableModel(
                new String[]{"ID", "Destinatario", "Fecha", "Total", "Estado"}, 0
        );
        JTable table = new JTable(model);

        // Renderer para fecha en formato dd/MM/yyyy
        table.getColumnModel().getColumn(2).setCellRenderer(new DefaultTableCellRenderer() {
            private final SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            @Override
            public void setValue(Object value) {
                if (value instanceof Date) {
                    setText(sdf.format((Date) value));
                } else {
                    super.setValue(value);
                }
            }
        });
        contentPane.setLayout(null);

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(10, 43, 764, 286);
        contentPane.add(scrollPane);

        // ---------------- Panel arriba: Buscar por ID ----------------
        JPanel panelTitulo = new JPanel();
        panelTitulo.setBounds(10, 10, 764, 33);
        contentPane.add(panelTitulo);
        panelTitulo.setLayout(null);
        
        JLabel lblNewLabel = new JLabel("Opciones Ventas");
        lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel.setBounds(5, 0, 759, 31);
        lblNewLabel.setFont(new Font("Dialog", Font.BOLD, 20));
        panelTitulo.add(lblNewLabel);
        
        JPanel panel = new JPanel();
        panel.setBounds(10, 327, 764, 80);
        contentPane.add(panel);
                panel.setLayout(null);
        
                
                JButton btnFinalizar = new JButton("Finalizar orden");
                btnFinalizar.setBounds(281, 32, 137, 37);
                panel.add(btnFinalizar);
                
                
                JButton btnBuscar = new JButton("Buscar por ID");
                btnBuscar.setBounds(174, 32, 97, 37);
                panel.add(btnBuscar);

                JTextField inputId = new JTextField(10);
                inputId.setBounds(86, 32, 86, 36);
                panel.add(inputId);

                JLabel label = new JLabel("ID Orden:");
                label.setBounds(10, 36, 66, 14);
                panel.add(label);
                
                JButton btnNewButton = new JButton("Salir");
                btnNewButton.setForeground(new Color(255, 0, 0));
                btnNewButton.addActionListener(new ActionListener() {
                	public void actionPerformed(ActionEvent e) {
                		System.exit(0);
                	}
                });
                btnNewButton.setBounds(629, 32, 107, 37);
                panel.add(btnNewButton);

             
                btnBuscar.addActionListener(e -> {
                    String texto = inputId.getText().trim();

                    if (texto.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Por favor, ingrese un ID válido.");
                        return;
                    }

                    try {
                        int idBuscado = Integer.parseInt(texto);

                        ArrayList<OrdenDeCompra> resultado = OrdenesRepository.buscarOrdenPorIdOrden(idBuscado);

                        if (resultado == null || resultado.isEmpty()) {
                            JOptionPane.showMessageDialog(null, "No se encontró una orden con ese ID.");
                            imprimirTabla(OrdenesRepository.obtenerTodas(), model); // Mostrar toda la tabla
                        } else {
                            imprimirTabla(resultado, model);
                        }

                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(null, "Ingrese un número de ID válido.");
                    }
                });
                
                
        btnFinalizar.addActionListener(e -> {
            int filaSeleccionada = table.getSelectedRow();
            if (filaSeleccionada == -1) {
                JOptionPane.showMessageDialog(this, "Seleccione una orden de compra.");
                return;
            }

            int id = (int) model.getValueAt(filaSeleccionada, 0);
            String estado = (String) model.getValueAt(filaSeleccionada, 4);
            Date fecha = (Date) model.getValueAt(filaSeleccionada, 2);
            double total = (double) model.getValueAt(filaSeleccionada, 3);

            if (estado.equalsIgnoreCase("Finalizado")) {
                JOptionPane.showMessageDialog(this, "La orden ya está finalizada.");
                return;
            }

            OrdenDeCompra orden = new OrdenDeCompra(id, null, fecha, total, estado);
            String mensaje = venta.finalizarVenta(orden);
            JOptionPane.showMessageDialog(this, mensaje);

            imprimirTabla(OrdenesRepository.obtenerTodas(), model);
        });

        
        imprimirTabla(OrdenesRepository.obtenerTodas(), model);
    }

    public static void imprimirTabla(ArrayList<OrdenDeCompra> ordenes, DefaultTableModel model) {
        model.setRowCount(0);
        for (OrdenDeCompra orden : ordenes) {
            String nombreDestinatario = orden.getDestinatario() != null
                    ? orden.getDestinatario().getNombre()
                    : "Sin cliente";

            model.addRow(new Object[]{
                    orden.getIdOrden(),
                    nombreDestinatario,
                    orden.getFecha(),
                    orden.getTotal(),
                    orden.getEstado()
            });
        }
    }
}
