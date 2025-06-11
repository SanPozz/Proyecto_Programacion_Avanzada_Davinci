package GUI;

import BLL.Clases.Usuario;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class SignUp extends JFrame {

  private static final long serialVersionUID = 1L;
  private JPanel contentPane;
  private JTextField textField;
  private JTextField textField_1;
  private JTextField textField_2;
  private JTextField textField_3;
  private JTextField textField_4;
  private JTextField textField_5;

  /**
   * Launch the application.
   */
  public static void main(String[] args) {
    EventQueue.invokeLater(new Runnable() {
      public void run() {
        try {
          SignUp frame = new SignUp();
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
  public SignUp() throws SQLException {
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setBounds(100, 100, 450, 441);
    contentPane = new JPanel();
    contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

    setContentPane(contentPane);
    contentPane.setLayout(null);

    JButton btnNewButton = new JButton("Enviar");
    btnNewButton.setFont(new Font("Roboto", Font.PLAIN, 14));
    btnNewButton.setBounds(145, 358, 138, 33);
    contentPane.add(btnNewButton);

    JLabel lblRegistrarse = new JLabel("Registrarse");
    lblRegistrarse.setFont(new Font("Roboto", Font.BOLD, 20));
    lblRegistrarse.setBounds(159, 6, 114, 33);
    contentPane.add(lblRegistrarse);

    JPanel panel = new JPanel();
    panel.setBounds(84, 41, 254, 306);
    contentPane.add(panel);
    panel.setLayout(new GridLayout(0, 1, 0, 0));

    JLabel lblNewLabel = new JLabel("Nombre");
    lblNewLabel.setFont(new Font("Roboto", Font.PLAIN, 11));
    panel.add(lblNewLabel);

    textField = new JTextField();
    panel.add(textField);
    textField.setColumns(10);

    JLabel lblNewLabel_1 = new JLabel("Apellido");
    lblNewLabel_1.setFont(new Font("Roboto", Font.PLAIN, 11));
    panel.add(lblNewLabel_1);

    textField_1 = new JTextField();
    panel.add(textField_1);
    textField_1.setColumns(10);

    JLabel lblNewLabel_2 = new JLabel("Edad");
    lblNewLabel_2.setFont(new Font("Roboto", Font.PLAIN, 11));
    panel.add(lblNewLabel_2);

    textField_2 = new JTextField();
    panel.add(textField_2);
    textField_2.setColumns(10);

    JLabel lblNewLabel_3 = new JLabel("Email");
    lblNewLabel_3.setFont(new Font("Roboto", Font.PLAIN, 11));
    panel.add(lblNewLabel_3);

    textField_3 = new JTextField();
    panel.add(textField_3);
    textField_3.setColumns(10);

    JLabel lblNewLabel_4 = new JLabel("Contraseña");
    lblNewLabel_4.setFont(new Font("Roboto", Font.PLAIN, 11));
    panel.add(lblNewLabel_4);

    textField_4 = new JTextField();
    panel.add(textField_4);
    textField_4.setColumns(10);

    JLabel lblNewLabel_4_1 = new JLabel("Repetir Contraseña");
    lblNewLabel_4_1.setFont(new Font("Roboto", Font.PLAIN, 11));
    panel.add(lblNewLabel_4_1);

    textField_5 = new JTextField();
    textField_5.setColumns(10);
    panel.add(textField_5);

    btnNewButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        Boolean result = null;
        try {
          result = Usuario.signUp(textField.getText(), textField_1.getText(), Integer.parseInt(textField_2.getText()), textField_3.getText(), textField_4.getText(), textField_5.getText());
        } catch (SQLException ex) {
          throw new RuntimeException(ex);
        }
        if (result) {
          LogIn log = new LogIn();
          log.setVisible(true);
          dispose();
        }
      }
    });




  }
}
