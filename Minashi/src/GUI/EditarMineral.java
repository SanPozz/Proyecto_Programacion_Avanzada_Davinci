package GUI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import BLL.Clases.Mineral;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JButton;

public class EditarMineral extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField inputTipo;
	private JTextField inputPureza;
	private JTextField inputTonelada;


	public EditarMineral(Mineral mineral) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 620);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Tipo");
		lblNewLabel.setBounds(34, 40, 42, 14);
		lblNewLabel.setVerticalAlignment(SwingConstants.TOP);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lblNewLabel);
		
		inputTipo = new JTextField();
		inputTipo.setText(mineral.getTipo());
		inputTipo.setBounds(34, 65, 177, 20);
		inputTipo.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(inputTipo);
		inputTipo.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Pureza");
		lblNewLabel_1.setVerticalAlignment(SwingConstants.TOP);
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(34, 107, 42, 14);
		contentPane.add(lblNewLabel_1);
		
		inputPureza = new JTextField();
		inputPureza.setText(String.valueOf(mineral.getPureza()));
		inputPureza.setHorizontalAlignment(SwingConstants.CENTER);
		inputPureza.setColumns(10);
		inputPureza.setBounds(34, 132, 177, 20);
		contentPane.add(inputPureza);
		
		JLabel lblNewLabel_1_1 = new JLabel("Toneladas");
		lblNewLabel_1_1.setVerticalAlignment(SwingConstants.TOP);
		lblNewLabel_1_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_1_1.setBounds(34, 172, 86, 14);
		contentPane.add(lblNewLabel_1_1);
		
		inputTonelada = new JTextField();
		inputTonelada.setText(String.valueOf(mineral.getToneladas()));
		inputTonelada.setHorizontalAlignment(SwingConstants.CENTER);
		inputTonelada.setColumns(10);
		inputTonelada.setBounds(34, 197, 177, 20);
		contentPane.add(inputTonelada);
		
		JButton btnNewButton = new JButton("Editar");
		btnNewButton.setBounds(31, 264, 89, 23);
		contentPane.add(btnNewButton);
	}
}
