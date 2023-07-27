import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import java.awt.Choice;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BookHotel extends JFrame{
    private JPanel contentPane;
    JTextField t1,t2;
    Choice c1,c2,c3;
    public BookHotel(String username){
        setBounds(420, 220, 1100, 600);
		contentPane = new JPanel();
		setContentPane(contentPane);
		contentPane.setLayout(null);
                
        ImageIcon i1  = new ImageIcon(ClassLoader.getSystemResource("icons/book.jpg"));
        Image i3 = i1.getImage().getScaledInstance(500, 300,Image.SCALE_DEFAULT);
        ImageIcon i2 = new ImageIcon(i3);
        JLabel la1 = new JLabel(i2);
        la1.setBounds(450,100,700,300);
        add(la1);
		
		JLabel lblName = new JLabel("BOOK HOTEL");
		lblName.setFont(new Font("Yu Mincho", Font.PLAIN, 20));
		lblName.setBounds(118, 11, 300, 53);
		contentPane.add(lblName);
                
        JLabel la2 = new JLabel("Username :");
		la2.setBounds(35, 70, 200, 14);
		contentPane.add(la2);
                
        JLabel l1 = new JLabel(username);
		l1.setBounds(271, 70, 200, 14);
		contentPane.add(l1);
                
        JLabel lblId = new JLabel("Select Hotel :");
		lblId.setBounds(35, 110, 200, 14);
		contentPane.add(lblId);
                
        c1 = new Choice();
        Conn c = new Conn();
        try{

            ResultSet rs = c.s.executeQuery("select * from hotels");
            while(rs.next()){
                c1.add(rs.getString("name"));
            }

            rs.close();
        }catch(SQLException e){}

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
                
        JLabel la4 = new JLabel("Number of Days");
        la4.setBounds(35, 190, 200, 14);
		contentPane.add(la4);
		
		t2 = new JTextField();
		t2.setText("0");
		t2.setBounds(271, 190, 150, 20);
		contentPane.add(t2);
		t2.setColumns(10);
		
		JLabel la5 = new JLabel("AC / Non-AC");
		la5.setBounds(35, 230, 200, 14);
		contentPane.add(la5);
                
        c2 = new Choice();
        c2.add("AC");
        c2.add("Non-AC");
        c2.setBounds(271, 230, 150, 30);
        add(c2);

                
		JLabel la6 = new JLabel("Food Included :");
		la6.setBounds(35, 270, 200, 14);
		contentPane.add(la6);
                
        c3 = new Choice();
        c3.add("Yes");
        c3.add("No");
        c3.setBounds(271, 270, 150, 30);
        add(c3);
                
		JLabel lbl1 = new JLabel("ID :");
		lbl1.setBounds(35, 310, 200, 14);
		contentPane.add(lbl1);
                
        JLabel l2 = new JLabel();
		l2.setBounds(271, 310, 200, 14);
		contentPane.add(l2);
		
		JLabel lbl2 = new JLabel("Number :");
		lbl2.setBounds(35, 350, 200, 14);
		contentPane.add(lbl2);
                
        JLabel l3 = new JLabel();
		l3.setBounds(271, 350, 200, 14);
		contentPane.add(l3);
           	
		JLabel lbl3 = new JLabel("Phone :");
		lbl3.setBounds(35, 390, 200, 14);
		contentPane.add(lbl3);
                
        JLabel l4 = new JLabel();
		l4.setBounds(271, 390, 200, 14);
		contentPane.add(l4);

		
		JLabel lblDeposite = new JLabel("Total Price :");
		lblDeposite.setBounds(35, 430, 200, 14);
		contentPane.add(lblDeposite);
		
		JLabel l5 = new JLabel();
		l5.setBounds(271, 430, 200, 14);
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
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        JButton btnCheck = new JButton("Check");
        btnCheck.setBounds(50, 470, 120, 30);
        btnCheck.setBackground(Color.BLACK);
        btnCheck.setForeground(Color.WHITE);
		contentPane.add(btnCheck);
        btnCheck.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                try {
                    Conn conn = new Conn();
                    String s1 = c1.getSelectedItem();
                    String sql = "select * from hotels where name = '"+s1+"'";
                    ResultSet rs = conn.s.executeQuery(sql);
                    if(rs.next()){
                        int cost = Integer.parseInt(rs.getString("cost_per_day"));
                        int food = Integer.parseInt(rs.getString("food_charges"));
                        int ac = Integer.parseInt(rs.getString("ac_charges"));

                        int persons = Integer.parseInt(t1.getText());
                        int days = Integer.parseInt(t2.getText());

                        String acprice = c2.getSelectedItem();
                        String foodprice = c3.getSelectedItem();

                        if(persons * days > 0){
                            int total = 0;
                            total += acprice.equals("AC") ? ac : 1;
                            total += foodprice.equals("Yes") ? food : 1;
                            total += cost;
                            total = total * persons * days;
                            l5.setText("Rs "+ total);
                        }
                    }
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                } 
            }
        });

        JButton btnBook = new JButton("Book");
        btnBook.setBounds(200, 470, 120, 30);
        btnBook.setBackground(Color.BLACK);
        btnBook.setForeground(Color.WHITE);
		contentPane.add(btnBook);
        btnBook.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                String s2 = c1.getSelectedItem();
                String s3 = t1.getText();
                String s4 = t2.getText();
                String s5 = c2.getSelectedItem();
                String s6 = c3.getSelectedItem();
                String s7 = l2.getText();
                String s8 = l3.getText();
                String s9 = l4.getText();
                String s10 = l5.getText();
                try {
                    Conn conn = new Conn();
                    String sql = "insert into bookhotel values('"+username+"','"+s2+"','"+s3+"','"+s4+"','"+s5+"','"+s6+"','"+s7+"','"+s8+"','"+s9+"','"+s10+"')";
                    conn.s.executeUpdate(sql);

                    JOptionPane.showMessageDialog(null, "Add Client Successfully");
                    setVisible(false);
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
        });
        JButton btnBack = new JButton("Back");
        btnBack.setBounds(350, 470, 120, 30);
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
            @Override
            public void run() {
                new BookHotel("").setVisible(true);
            }
            
        });
    }
}
