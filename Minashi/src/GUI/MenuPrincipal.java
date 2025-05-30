package GUI;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Font;
import java.awt.Label;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.sql.SQLException;

public class MenuPrincipal extends JFrame {

  private static final long serialVersionUID = 1L;
  private JPanel contentPane;

  // MAIN
  public static void main(String[] args) {
    EventQueue.invokeLater(new Runnable() {
      public void run() {
        try {
          MenuPrincipal frame = new MenuPrincipal();
          frame.setVisible(true);
        } catch (Exception e) {
          e.printStackTrace();
        }
      }
    });
  }

  // CONSTRUCTOR
  public MenuPrincipal() {
    setBackground(new Color(0, 0, 0));
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setBounds(100, 100, 486, 338);
    contentPane = new JPanel();
    contentPane.setForeground(new Color(0, 0, 0));
    contentPane.setBackground(new Color(255, 255, 255));
    contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
    setContentPane(contentPane);
    contentPane.setLayout(null);

    // Botón Iniciar Sesión
    JButton btnNewButton = new JButton("Iniciar Sesion");
    btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
    btnNewButton.setBounds(94, 181, 138, 46);
    contentPane.add(btnNewButton);
    btnNewButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        LogIn login = new LogIn();
        login.setVisible(true);
        dispose(); // cerrar esta ventana
      }
    });

    // Botón Registrarse (por ahora vacío)
    JButton btnNewButton_1 = new JButton("Registrarse");
    btnNewButton_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
    btnNewButton_1.setBounds(242, 181, 138, 46);
    contentPane.add(btnNewButton_1);
    btnNewButton_1.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {

        SignUp reg = null;
        try {
          reg = new SignUp();
        } catch (SQLException ex) {
          throw new RuntimeException(ex);
        }
        reg.setVisible(true);
        dispose();
      }
    });

    // Título
    Label label = new Label("Sistema Minashi");
    label.setForeground(new Color(0, 0, 0));
    label.setFont(new Font("Dialog", Font.PLAIN, 26));
    label.setBounds(144, 95, 195, 33);
    contentPane.add(label);
  }
}
