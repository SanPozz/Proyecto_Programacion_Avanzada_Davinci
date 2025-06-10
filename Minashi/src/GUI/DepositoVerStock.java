package GUI;

import BLL.Clases.Deposito;
import BLL.Clases.Mineral;
import DLL.Repository.ActualizarMineRepository;

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
	 
	  
	public DepositoVerStock(Deposito deposito) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    setBounds(100, 100, 720, 781);
	    contentPane = new JPanel();
	    contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

	    setContentPane(contentPane);

	    DefaultTableModel model = new DefaultTableModel(new String[]{"ID", "Nombre", "Pureza", "Toneladas","PrecioTonelada"}, 0);
	    
	    JTable table = new JTable(model);
	   
	    model.setRowCount(0);
	    ArrayList<Mineral> minerales = Mineral.mineralesEnStock();

	    for (Mineral mineral : minerales) {
	      System.out.println(mineral);
	      model.addRow(new Object[]{mineral.getIdMineral(), mineral.getNombre(), mineral.getPureza(), mineral.getToneladas(),mineral.getPrecioTonelada()});
	    }

	    JScrollPane scrollPane = new JScrollPane(table);
	    setBounds(100, 100, 720, 445);

	    contentPane.setLayout(new GridLayout(0, 1, 0, 0));
	    contentPane.add(scrollPane);

	    JPanel panel = new JPanel();
	    panel.setBorder(new EmptyBorder(10,20,10,20));
	    contentPane.add(panel);
	    panel.setLayout(null);
//----------------------------------------------------------------------------
	    JPanel panel_1 = new JPanel();
	    panel_1.setBounds(10, 10, 238, 200);
	    panel_1.setBorder(new EmptyBorder(20,0,20,0));
	    panel.add(panel_1);
	    
	    JButton btnNewButton = new JButton("Editar Mineral");
	    btnNewButton.setBounds(116, 27, 122, 101);
	    
	    btnNewButton.addActionListener(e -> {
	      if (idMineralSelect != null) {
			EditarMineral editar = new EditarMineral(idMineralSelect, deposito);
			editar.setVisible(true);
			dispose();
		}else {
			JOptionPane.showMessageDialog(btnNewButton, "Seleccione un mineral");
		}

	      }
	    );
	    
	   
	    panel_1.setLayout(null);
	    panel_1.add(btnNewButton);

	    JButton btnNewButton_1 = new JButton("Eliminar mineral");
	    btnNewButton_1.setBounds(0, 27, 122, 101);
	    btnNewButton_1.addActionListener(e-> {
	    	 if (idMineralSelect != null) {
	    		 ActualizarMineRepository.eliminarMineral(idMineralSelect);
	    		 String mensajeResul =  ActualizarMineRepository.eliminarMineral(idMineralSelect);
	    		 JOptionPane.showMessageDialog(this, mensajeResul,"Resultado de la Eliminación", JOptionPane.INFORMATION_MESSAGE);
	    		 
			} else {
				JOptionPane.showMessageDialog(btnNewButton, "Seleccione un mineral");
			}
	    });
	    table.getSelectionModel().addListSelectionListener(e -> {
	        if (!e.getValueIsAdjusting()) {
	            int row = table.getSelectedRow();
	            if (row != -1) {
	                idMineralSelect = new Mineral(
	                    (int) model.getValueAt(row, 0),
	                    (String) model.getValueAt(row, 1),
	                    ((Number) model.getValueAt(row, 2)).floatValue(),
	                    ((Number) model.getValueAt(row, 3)).floatValue(),
	                    ((Number) model.getValueAt(row, 4)).floatValue()
	                );
	              
	            }
	        }
	    });
	    panel_1.add(btnNewButton_1);
	    JButton botonAtras = new JButton("<- VOLVER");
	    botonAtras.setBounds(0, 139, 112, 29);
	    panel_1.add(botonAtras);
	    botonAtras.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		OpcionesDeposito atras = new OpcionesDeposito(deposito);
	    		atras.setVisible(true);
	    		dispose();
	    		
	    	}
	    });
	  //----------------------------------------------------------------------------
	    JPanel panel_2 = new JPanel();
	    panel_2.setBounds(248, 10, 218, 200);
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

	        if (minerales.size() > 0) {
	          imprimirTabla(minerales, model);
	        }

	      }
	    });

	    JPanel panel_3 = new JPanel();
	    panel_3.setBounds(476, 10, 218, 200);
	    panel.add(panel_3);
	    panel_3.setLayout(null);

	    JComboBox comboBox = new JComboBox();
	    comboBox.setBounds(45, 53, 131, 49);
	    comboBox.addItem("Nombre");
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
	      model.addRow(new Object[]{mineral.getIdMineral(), mineral.getNombre(), mineral.getPureza(), mineral.getToneladas(),mineral.getPrecioTonelada()});
	    }
	    

	  }
	
   
}