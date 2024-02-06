import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.*;
import java.sql.SQLException;

class p1 extends JFrame implements ActionListener {
    JLabel lno, lname, lbasic, lda, lhra, lnet;
    JButton view, f, b, add1, exit, dlt;
    JTextField tno, tname, tbasic, tda, thra, tnet;
    int eno;
    String ename;
    float basic;

    p1() {
        lno = new JLabel("Emp_No.:");
        lno.setFont(new Font("Osward", Font.BOLD, 20));
        lno.setBounds(150, 100, 200, 30);
        add(lno);
        tno = new JTextField();
        tno.setBounds(270, 100, 150, 30);
        add(tno);

        lname = new JLabel("Emp_name:");
        lname.setFont(new Font("Osward", Font.BOLD, 20));
        lname.setBounds(150, 150, 200, 30);
        add(lname);
        tname = new JTextField();
        tname.setBounds(270, 150, 150, 30);
        add(tname);

        lbasic = new JLabel("Basic:");
        lbasic.setFont(new Font("Osward", Font.BOLD, 20));
        lbasic.setBounds(150, 200, 200, 30);
        add(lbasic);
        tbasic = new JTextField();
        tbasic.setBounds(270, 200, 150, 30);
        add(tbasic);

        lda = new JLabel("DA:");
        lda.setFont(new Font("Osward", Font.BOLD, 20));
        lda.setBounds(150, 250, 200, 30);
        add(lda);
        tda = new JTextField();
        tda.setBounds(270, 250, 150, 30);
        add(tda);

        lhra = new JLabel("HRA:");
        lhra.setFont(new Font("Osward", Font.BOLD, 20));
        lhra.setBounds(150, 300, 200, 30);
        add(lhra);
        thra = new JTextField();
        thra.setBounds(270, 300, 150, 30);
        add(thra);

        lnet = new JLabel("NET:");
        lnet.setFont(new Font("Osward", Font.BOLD, 20));
        lnet.setBounds(150, 350, 200, 30);
        add(lnet);
        tnet = new JTextField();
        tnet.setBounds(270, 350, 150, 30);
        add(tnet);

        ButtonGroup g = new ButtonGroup();
        view = new JButton("view");
        view.setFont(new Font("Osward", Font.BOLD, 20));
        view.setBounds(215, 410, 90, 30);
        add(view);

        f = new JButton(">");
        f.setFont(new Font("Osward", Font.BOLD, 19));
        f.setBounds(301, 410, 45, 30);
        add(f);
        b = new JButton("<");
        b.setFont(new Font("Osward", Font.BOLD, 19));
        b.setBounds(178, 410, 45, 30);
        add(b);
        g.add(view);
        g.add(f);
        g.add(b);

        add1 = new JButton("add");
        add1.setFont(new Font("Osward", Font.BOLD, 20));
        add1.setBounds(460, 170, 100, 35);
        add(add1);
        add1.addActionListener(this);

        dlt = new JButton("delete");
        dlt.setFont(new Font("Osward", Font.BOLD, 20));
        dlt.setBounds(460, 215, 100, 35);
        add(dlt);
        dlt.addActionListener(this);

        setLayout(null);
        setSize(800, 700);
        setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == add1) {
            if (tno.getText().equals("") || tname.getText().equals("") || tbasic.getText().equals("")) {
                JOptionPane.showMessageDialog(this, "Enter data");
            } else {
                eno = Integer.parseInt(tno.getText());
                ename = tname.getText();
                basic = Float.parseFloat(tbasic.getText());
                try {
                    JOptionPane.showMessageDialog(this, "trying to add a record");
                    Menu m = (Menu) Naming.lookup("rmi://192.168.1.5/server");
                    m.setEmp(eno, ename, basic);
                    tda.setText(String.valueOf(m.getDa()));
                    thra.setText(String.valueOf(m.getHra()));
                    tnet.setText(String.valueOf(m.getNet()));
                    JOptionPane.showMessageDialog(this, "Record added");
                } catch (Exception ignored) {
                    JOptionPane.showMessageDialog(this, "Record could not be added");
                }
            }
        } else if (e.getSource() == dlt) {
            if (tno.getText().equals(""))
                JOptionPane.showMessageDialog(this, "Enter Employee no.");
            else {

                try {
                    Menu m = (Menu) Naming.lookup("rmi://192.168.62.105/server");
                    int cnt = m.dltEmp(Integer.parseInt(tno.getText()));
                    if (cnt == 0)
                        JOptionPane.showMessageDialog(this, "Oops! No such Employee");
                    else
                        JOptionPane.showMessageDialog(this, "Record deleted succesfully");
                }
                catch (Exception e2) {}
            }
        }
       else if(e.getSource()==view) {
            if (tno.getText().equals(""))
                JOptionPane.showMessageDialog(this, "Enter Employee no.");
            //code to be written
        }

    }
}

    public class UI {
        public static void main(String[] args) {

            new p1();
        }
    }

