package GUI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.FlowLayout;
import javax.swing.JLabel;
import java.awt.Font;

public class OpcionesAdmin extends JFrame {

  private static final long serialVersionUID = 1L;
  private JPanel contentPane;

  /**
   * Launch the application.
   */
  public static void main(String[] args) {
    EventQueue.invokeLater(new Runnable() {
      public void run() {
        try {
          OpcionesAdmin frame = new OpcionesAdmin();
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
  public OpcionesAdmin() {
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setBounds(100, 100, 450, 300);
    contentPane = new JPanel();
    contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

    setContentPane(contentPane);
    contentPane.setLayout(null);

    JPanel panel = new JPanel();
    panel.setBounds(10, 92, 414, 158);
    contentPane.add(panel);

    JLabel lblNewLabel = new JLabel("Panel de Administrador");
    lblNewLabel.setFont(new Font("Roboto", Font.BOLD, 20));
    lblNewLabel.setBounds(113, 26, 209, 43);
    contentPane.add(lblNewLabel);

    panel.setLayout(new GridLayout(0, 2, 5, 5));

    JButton btnMC = new JButton("Mostrar Clientes");
    btnMC.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        System.out.println("TODO para el final");

      }
    });

    JButton btnME = new JButton("Mostrar Empleados");
    btnME.addActionListener(new ActionListener() {

      public void actionPerformed(ActionEvent e) {
        System.out.println("TODO para el final");

      }
    });

    JButton btnMP = new JButton("Mostrar Pedidos");
    btnMP.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e ) {
        System.out.println("TODO para el final");

      }
    });

    JButton btnMM = new JButton("Mostrar Minerales");
    btnMM.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e ) {
        System.out.println("TODO para el final");

      }
    });


    panel.add(btnMC);
    panel.add(btnME);
    panel.add(btnMP);
    panel.add(btnMM);

  }

}
