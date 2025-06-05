package GUI;

import BLL.Clases.Cliente;
import BLL.Clases.Deposito;
import BLL.Clases.Usuario;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;

public class LogIn extends JFrame {

  private static final long serialVersionUID = 1L;
  private JPanel contentPane;
  private JTextField textField;
  private JPasswordField passwordField;


  
  public LogIn() {
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setBounds(100, 100, 450, 300);
    contentPane = new JPanel();
    contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

    setContentPane(contentPane);
    contentPane.setLayout(null);

    textField = new JTextField();
    textField.setText("");
    textField.setBounds(114, 67, 215, 39);
    contentPane.add(textField);
    textField.setColumns(10);

    JButton btnNewButton = new JButton("Iniciar Sesion");
    btnNewButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        Usuario result = Usuario.logIn(textField.getText(), passwordField.getText());

        if (result != null) {

          switch (result.getRol()){
            case 1:
              OpcionesCliente op = new OpcionesCliente((Cliente) result);
              op.setVisible(true);
              dispose();
              break;
            case 2:
            	OpcionesDeposito op2 = new OpcionesDeposito((Deposito) result);
                op2.setVisible(true);
                dispose();
            	break;
          }

        } else {

        }
//
      }
    });
    btnNewButton.setFont(new Font("Roboto", Font.PLAIN, 14));
    btnNewButton.setBounds(152, 206, 138, 33);
    contentPane.add(btnNewButton);

    JLabel titulo = new JLabel("Iniciar Sesion");
    titulo.setFont(new Font("Roboto", Font.BOLD, 20));
    titulo.setBounds(153, 4, 187, 33);
    contentPane.add(titulo);

    JLabel lblNewLabel = new JLabel("Correo Electronico");
    lblNewLabel.setFont(new Font("Roboto", Font.PLAIN, 14));
    lblNewLabel.setBounds(116, 50, 154, 14);
    contentPane.add(lblNewLabel);

    JLabel lblContrasea = new JLabel("Contraseña");
    lblContrasea.setFont(new Font("Roboto", Font.PLAIN, 14));
    lblContrasea.setBounds(116, 126, 87, 14);
    contentPane.add(lblContrasea);
    
    passwordField = new JPasswordField();
    passwordField.setBounds(114, 151, 215, 33);
    contentPane.add(passwordField);
  }
}
