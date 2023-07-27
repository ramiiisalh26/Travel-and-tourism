import java.awt.Color;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;

public class Loading extends JFrame implements Runnable{
    
    private JPanel contentPane;
    private JProgressBar progressBar;
    String username;
    int s;
    Thread th;
    public Loading(String username){
        this.username = username;
        th = new Thread((Runnable) this);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(600, 300, 600, 400);
        contentPane = new JPanel();
        contentPane.setBackground(new Color(51,204, 255));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lbllibraryManagement = new JLabel("Travel and Toursim Application");
        lbllibraryManagement.setForeground(new Color(72, 209, 204));
        lbllibraryManagement.setFont(new Font("Trebuchet MS", Font.BOLD, 35));
        lbllibraryManagement.setBounds(50, 46, 700, 35);
        contentPane.add(lbllibraryManagement);

        progressBar = new JProgressBar();
        progressBar.setFont(new Font("Tahoma", Font.BOLD, 12));
        progressBar.setStringPainted(true);
        progressBar.setBounds(130, 135, 300, 25);
        contentPane.add(progressBar);

        JLabel lblNewLabel_2 = new JLabel("Please Wait....");
        lblNewLabel_2.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 20));
        lblNewLabel_2.setForeground(new Color(160, 82, 45));
        lblNewLabel_2.setBounds(200, 165, 150, 20);
        contentPane.add(lblNewLabel_2);

        JPanel panel = new JPanel();
        panel.setBackground(Color.WHITE);
        panel.setBounds(10, 10, 580, 380);
        contentPane.add(panel);
        setUpload();
    }


    @Override
    public void run() {
        try{
            for(int i = 0;i < 200;i++){
                int m = progressBar.getMaximum();
                int v = progressBar.getValue();

                if(v < m){
                    progressBar.setValue(progressBar.getValue() + 1);
                }else{
                    i = 200;
                    setVisible(false);
                    new Home(username).setVisible(true);
                }
                Thread.sleep(40);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }


    public void setUpload(){
        setVisible(false);
        th.start();
    }
 
    public static void main(String[] args){
        new Loading("").setVisible(true);
    }
}
