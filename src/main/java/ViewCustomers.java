import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

public class ViewCustomers extends JFrame{

    private String username, id_type, number, name, gender, country,address,phone,email;
    private JPanel contentPane;
	private JTable table;
	private JLabel lblAvailability,lblCleanStatus,lblNewLabel,lblNewLabel_1,lblId;
    
    public ViewCustomers(String username, String id_type, String number, String name, String gender, String country,
            String address, String phone, String email) {
        this.username = username;
        this.id_type = id_type;
        this.number = number;
        this.name = name;
        this.gender = gender;
        this.country = country;
        this.address = address;
        this.phone = phone;
        this.email = email;
    }

    
    public ViewCustomers(){
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(380, 120, 900, 680);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
                
        ImageIcon i1  = new ImageIcon(ClassLoader.getSystemResource("icons/viewall.jpg"));
        Image i3 = i1.getImage().getScaledInstance(626, 201,Image.SCALE_DEFAULT);
        ImageIcon i2 = new ImageIcon(i3);
        JLabel l1 = new JLabel(i2);
        l1.setBounds(0,450,626,201);
        add(l1);
        
        ImageIcon i4  = new ImageIcon(ClassLoader.getSystemResource("icons/viewall.jpg"));
        Image i5 = i4.getImage().getScaledInstance(626, 201,Image.SCALE_DEFAULT);
        ImageIcon i6 = new ImageIcon(i5);
        JLabel l2 = new JLabel(i6);
        l2.setBounds(615,450,626,201);
        contentPane.add(l2);
                
		table = new JTable();
		table.setBounds(0, 40, 900, 350);
		contentPane.add(table);


        try {
            ArrayList<ViewCustomers> viewList = new ArrayList<>();
            String[] col = {"username","id_type","number","name","gender","country","address","phone","email"};
            Conn conn = new Conn();
            String sql = "select * from customer";
            ResultSet rs = conn.s.executeQuery(sql);
            ViewCustomers viewCustomers;
            while(rs.next()){
                viewCustomers = new ViewCustomers(
                    rs.getString("username"),
                    rs.getString("id_type"), 
                    rs.getString("number"), 
                    rs.getString("name"), 
                    rs.getString("gender"), 
                    rs.getString("country"), 
                    rs.getString("address"), 
                    rs.getString("phone"), 
                    rs.getString("email"));
                    viewList.add(viewCustomers);
            }
            DefaultTableModel model = new DefaultTableModel(null, col);
            Object[] row = new Object[9];
            for(int i=0;i<viewList.size();i++){
                row[0] = viewList.get(i).username;
                row[1] = viewList.get(i).id_type;
                row[2] = viewList.get(i).number;
                row[3] = viewList.get(i).name;
                row[4] = viewList.get(i).gender;
                row[5] = viewList.get(i).country;
                row[6] = viewList.get(i).address;
                row[7] = viewList.get(i).phone;
                row[8] = viewList.get(i).email;
                model.addRow(row);
            }
            table.setModel(model);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }


        JButton btnBack = new JButton("Back"); 
        btnBack.setBounds(390, 400, 120, 30);
        btnBack.setBackground(Color.BLACK);
        btnBack.setForeground(Color.WHITE);
		contentPane.add(btnBack);
		btnBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
            }
            
        });

		lblAvailability = new JLabel("Username");
		lblAvailability.setBounds(10, 15, 69, 14);
		contentPane.add(lblAvailability);
		
		lblCleanStatus = new JLabel("Id Type");
		lblCleanStatus.setBounds(110, 15, 76, 14);
		contentPane.add(lblCleanStatus);
		
		lblNewLabel = new JLabel("Number");
		lblNewLabel.setBounds(220, 15, 46, 14);
		contentPane.add(lblNewLabel);
		
		lblNewLabel_1 = new JLabel("Name");
		lblNewLabel_1.setBounds(320, 15, 76, 14);
		contentPane.add(lblNewLabel_1);

		
		lblId = new JLabel("Gender");
		lblId.setBounds(420, 15, 90, 14);
		contentPane.add(lblId);
                
        JLabel l3 = new JLabel("Country");
		l3.setBounds(520, 15, 90, 14);
		contentPane.add(l3);
                
        JLabel l4 = new JLabel("Address");
		l4.setBounds(620, 15, 90, 14);
		contentPane.add(l4);
                
        JLabel l5 = new JLabel("Phone");
		l5.setBounds(720, 15, 90, 14);
		contentPane.add(l5);
                
        JLabel l6 = new JLabel("Email");
		l6.setBounds(820, 15, 90, 14);
		contentPane.add(l6);
                
        getContentPane().setBackground(Color.WHITE);
    }


    public static void main(String[] args){
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    new ViewCustomers().setVisible(true);
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
            
        });
    }

    // public String getUsername() {
    //     return username;
    // }

    // public String getId_type() {
    //     return id_type;
    // }

    // public String getNumber() {
    //     return number;
    // }

    // public String getName() {
    //     return name;
    // }

    // public String getGender() {
    //     return gender;
    // }

    // public String getCountry() {
    //     return country;
    // }

    // public String getAddress() {
    //     return address;
    // }

    // public String getPhone() {
    //     return phone;
    // }

    // public String getEmail() {
    //     return email;
    // }

}
