import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.awt.Choice;
import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class BookPackage extends JFrame{
    private JPanel contentPane;
    JTextField t1,t2;
    Choice c1,c2,c3;
    public BookPackage(String username){
        setBounds(420, 220, 1100, 450);
		contentPane = new JPanel();
		setContentPane(contentPane);
		contentPane.setLayout(null);
                
        ImageIcon i1  = new ImageIcon(ClassLoader.getSystemResource("icons/bookpackage.jpg"));
        Image i3 = i1.getImage().getScaledInstance(500, 300,Image.SCALE_DEFAULT);
        ImageIcon i2 = new ImageIcon(i3);
        JLabel la1 = new JLabel(i2);
        la1.setBounds(450,50,700,300);
        add(la1);

        JLabel lblName = new JLabel("BOOK PACKAGE");
		lblName.setFont(new Font("Yu Mincho", Font.PLAIN, 20));
		lblName.setBounds(118, 11, 300, 53);
		contentPane.add(lblName);
                
        JLabel la2 = new JLabel("Username :");
		la2.setBounds(35, 70, 200, 14);
		contentPane.add(la2);
                
        JLabel l1 = new JLabel(username);
		l1.setBounds(271, 70, 200, 14);
		contentPane.add(l1);
                
        JLabel lblId = new JLabel("Select Package :");
		lblId.setBounds(35, 110, 200, 14);
		contentPane.add(lblId);
                
        c1 = new Choice();
        c1.add("Gold Package");
        c1.add("Silver Package");
        c1.add("Bronze Package");
        c1.setBounds(271, 110, 150, 30);
        add(c1);
                
        JLabel la3 = new JLabel("Total Persons");
		la3.setBounds(35, 150, 200, 14);
		contentPane.add(la3);
                
        t1 = new JTextField();
        t1.setText("0");
		t1.setBounds(271, 150, 150, 20);
		contentPane.add(t1);
		t1.setColumns(10);
                
		JLabel lbl1 = new JLabel("ID :");
		lbl1.setBounds(35, 190, 200, 14);
		contentPane.add(lbl1);
                
        JLabel l2 = new JLabel();
		l2.setBounds(271, 190, 200, 14);
		contentPane.add(l2);
		
		JLabel lbl2 = new JLabel("Number :");
		lbl2.setBounds(35, 230, 200, 14);
		contentPane.add(lbl2);
                
        JLabel l3 = new JLabel();
		l3.setBounds(271, 230, 200, 14);
		contentPane.add(l3);
           	
		JLabel lbl3 = new JLabel("Phone :");
		lbl3.setBounds(35, 270, 200, 14);
		contentPane.add(lbl3);
                
        JLabel l4 = new JLabel();
		l4.setBounds(271, 270, 200, 14);
		contentPane.add(l4);

		
		JLabel lblDeposite = new JLabel("Total Price :");
		lblDeposite.setBounds(35, 310, 200, 14);
		contentPane.add(lblDeposite);
		
		JLabel l5 = new JLabel();
		l5.setBounds(271, 310, 200, 14);
        l5.setForeground(Color.RED);
		contentPane.add(l5);

        try {
            Conn conn = new Conn();
            String sql = "select * from customer where username = '"+username+"'";
            ResultSet rs = conn.s.executeQuery(sql);
            while(rs.next()){
                l2.setText(rs.getString("id_type"));
                l3.setText(rs.getString("number"));
                l4.setText(rs.getString("phone"));
            }
            rs.close();
        } catch (Exception e) {}

        JButton btnCheck = new JButton("Check");
        btnCheck.setBounds(50, 350, 120, 30);
        btnCheck.setBackground(Color.BLACK);
        btnCheck.setForeground(Color.WHITE);
		contentPane.add(btnCheck);
        btnCheck.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                String pack = c1.getSelectedItem();
                int cost = 0;
                if(pack.equals("Gold Package")){
                    cost += 12000;
                }
                if(pack.equals("Silver Package")){
                    cost += 25000;
                }
                if(pack.equals("Bronze Package")){
                    cost += 32000;
                }

                cost *= Integer.parseInt(t1.getText());
                l5.setText("Rs "+cost);
            }
        });

        JButton btnBook = new JButton("Book");
        btnBook.setBounds(200, 350, 120, 30);
        btnBook.setBackground(Color.BLACK);
        btnBook.setForeground(Color.WHITE);
		contentPane.add(btnBook);
        btnBook.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
            
                String s2 = c1.getSelectedItem();
                String s3 = t1.getText();
                String s4 = l2.getText();
                String s5 = l3.getText();
                String s6 = l4.getText();
                String s7 = l5.getText();
                try {
                    Conn conn = new Conn();
                    String sql = "insert into bookpackage values('"+username+"','"+s2+"','"+s3+"','"+s4+"','"+s5+"','"+s6+"','"+s7+"')";
                    conn.s.executeUpdate(sql);

                    JOptionPane.showMessageDialog(null, "Package Booked Successfully");
                    setVisible(false);
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
        });

        JButton btnBack = new JButton("Back");
        btnBack.setBounds(350, 350, 120, 30);
        btnBack.setBackground(Color.BLACK);
        btnBack.setForeground(Color.WHITE);
		contentPane.add(btnBack);
        btnBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
            }
            
        });

        getContentPane().setBackground(Color.WHITE);
    }

    public static void main(String[] args){
        EventQueue.invokeLater(new Runnable() {
            public void run(){
                try {
                    new BookPackage("Rami salh").setVisible(true);
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
        });
    }
}
