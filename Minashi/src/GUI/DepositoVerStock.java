package GUI;

import DLL.Repository.ActualizarMineRepository;
import DLL.Repository.MineralesRepository;
import BLLL.Clases.Mineral;
import BLLL.Clases.Deposito;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

public class DepositoVerStock extends JFrame {

	private static final long serialVersionUID = 1L;

	private JPanel contentPane;
	private Mineral idMineralSelect;

	private ArrayList<Mineral> minerales;

	public DepositoVerStock(Deposito deposito) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 720, 781);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);

		DefaultTableModel model = new DefaultTableModel(
				new String[] { "ID", "Nombre", "Pureza", "Toneladas", "PrecioTonelada", "Estado" }, 0);
		JTable table = new JTable(model);

		minerales = Mineral.mineralesEnStock();

		imprimirTabla(minerales, model);

		JScrollPane scrollPane = new JScrollPane(table);
		setBounds(100, 100, 720, 445);

		contentPane.setLayout(new GridLayout(0, 1, 0, 0));
		contentPane.add(scrollPane);

		JPanel panel = new JPanel();
		panel.setBorder(new EmptyBorder(10, 20, 10, 20));
		contentPane.add(panel);
		panel.setLayout(null);

		JPanel panel_1 = new JPanel();
		panel_1.setBounds(10, 10, 238, 200);
		panel_1.setBorder(new EmptyBorder(20, 0, 20, 0));
		panel.add(panel_1);

		JButton btnEditar = new JButton("Editar Mineral");
		btnEditar.setBounds(116, 27, 122, 101);

		btnEditar.addActionListener(e -> {
			if (idMineralSelect != null) {
				EditarMineral editar = new EditarMineral(idMineralSelect, deposito);
				editar.setVisible(true);
				dispose();
			} else {
				JOptionPane.showMessageDialog(btnEditar, "Seleccione un mineral");
			}
		});

		panel_1.setLayout(null);
		panel_1.add(btnEditar);

		JButton btnEliminar = new JButton("Eliminar mineral");
		btnEliminar.setBounds(0, 27, 122, 101);
		btnEliminar.addActionListener(e -> {
			if (idMineralSelect != null) {
				String mensajeResul = ActualizarMineRepository.eliminarMineral(idMineralSelect);
				minerales = Mineral.mineralesEnStock();
				imprimirTabla(minerales, model);
				JOptionPane.showMessageDialog(this, mensajeResul, "Resultado de la Eliminación",
						JOptionPane.INFORMATION_MESSAGE);
			} else {
				JOptionPane.showMessageDialog(btnEliminar, "Seleccione un mineral");
			}
		});

		panel_1.add(btnEliminar);

		table.getSelectionModel().addListSelectionListener(e -> {
			if (!e.getValueIsAdjusting()) {
				int row = table.getSelectedRow();
				if (row != -1) {
					idMineralSelect = new Mineral((int) model.getValueAt(row, 0), (String) model.getValueAt(row, 1),
							((Number) model.getValueAt(row, 2)).floatValue(),
							((Number) model.getValueAt(row, 3)).floatValue(),
							((Number) model.getValueAt(row, 4)).floatValue(), (String) model.getValueAt(row, 5));
				}
			}
		});

		JButton botonAtras = new JButton("<- VOLVER");
		botonAtras.setBounds(0, 139, 112, 29);
		panel_1.add(botonAtras);
		botonAtras.addActionListener(e -> {
			OpcionesDeposito atras = new OpcionesDeposito(deposito);
			atras.setVisible(true);
			dispose();
		});

		JPanel panel_2 = new JPanel();
		panel_2.setBounds(248, 10, 218, 200);
		panel.add(panel_2);
		panel_2.setBorder(new EmptyBorder(30, 30, 30, 30));
		panel_2.setLayout(new GridLayout(0, 1, 0, 0));

		JTextField textField = new JTextField();
		panel_2.add(textField);
		textField.setColumns(10);

		JButton btnBuscar = new JButton("Buscar");
		panel_2.add(btnBuscar);

		btnBuscar.addActionListener(e -> {
			String nombre = textField.getText().trim();

			if (nombre.isEmpty()) {
				JOptionPane.showMessageDialog(null, "Por favor, ingrese un nombre para buscar.");
				return;
			}

			ArrayList<Mineral> minerales = MineralesRepository.encontrarMineralesPorNombre(nombre);

			if (minerales != null && !minerales.isEmpty()) {
				imprimirTabla(minerales, model);
			} else {
				JOptionPane.showMessageDialog(null, "No se encontraron minerales con ese nombre.");
				minerales = Mineral.mineralesEnStock();
				imprimirTabla(minerales, model);
			}
		});

		JPanel panel_3 = new JPanel();
		panel_3.setBounds(476, 10, 218, 200);
		panel.add(panel_3);
		panel_3.setLayout(null);

		JComboBox<String> comboBox = new JComboBox<>();
		comboBox.setBounds(45, 53, 131, 49);
		comboBox.addItem("Nombre");
		comboBox.addItem("Toneladas");
		comboBox.addItem("Pureza");
		panel_3.add(comboBox);

		JLabel lblOrdenar = new JLabel("Ordenar Por:");
		lblOrdenar.setBounds(44, 28, 80, 14);
		panel_3.add(lblOrdenar);

		JButton btnOrdenar = new JButton("Ordenar");
		btnOrdenar.setBounds(68, 111, 89, 23);
		panel_3.add(btnOrdenar);

		btnOrdenar.addActionListener(e -> {
			if (minerales != null) {
				ArrayList<Mineral> mineralesOrdenados = Mineral.ordenarMinerales(minerales,
						(String) comboBox.getSelectedItem());
				imprimirTabla(mineralesOrdenados, model);
			} else {
				JOptionPane.showMessageDialog(null, "No hay minerales para ordenar.");
			}
		});
	}

	static public void imprimirTabla(ArrayList<Mineral> minerales, DefaultTableModel model) {
		model.setRowCount(0);
		for (Mineral mineral : minerales) {
			String estado = mineral.getEstado();
			if (estado == null || !estado.equalsIgnoreCase("eliminado")) {
				model.addRow(new Object[] { mineral.getIdMineral(), mineral.getNombre(), mineral.getPureza(),
						mineral.getToneladas(), mineral.getPrecioTonelada(), mineral.getEstado() });
			}
		}
	}
}
