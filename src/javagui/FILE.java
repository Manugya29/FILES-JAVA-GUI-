package javagui;


import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;


class emp
{
    public final String UID;
    public String UnAme;
    public emp(String a,String b)
    {
        UID=a;
        UnAme=b;
    }
    public String getUID()
    {
        return UID;
    }
    public String getUname()
    {
        return UnAme;
    }
}

class MyPanel extends JPanel implements ActionListener
{
    private JLabel l1,l2;
    public JTextField t1,t2;
    private JButton b1,b2,b3,b4;
    List<emp> listt = new LinkedList<>();
    File fp;
    FileWriter fw;
    //private int I;
    private JLabel makeLabel(String cap, int x, int y,Color col, boolean b)
    {
        JLabel temp=new JLabel(cap,JLabel.LEFT);
        temp.setOpaque(b);
        temp.setBackground(col);
        temp.setBounds(x,y,200,30);
        temp.setFont(new Font("Courier New", 1, 20));
        add(temp);
        return temp;
    }
   
    public MyPanel()
    {
        
        try
        {
      
            
           l1 = makeLabel("User ID",70,60,Color.RED,true);
      
             t1=new JTextField();
            t1.setBounds(500,50,300,30);
            t1.setBackground(Color.WHITE);
            t1.setFont(new Font("Courier New",1,20));
            add(t1);
           
              l2 = makeLabel("User Name",70,150,Color.ORANGE,true);
            t2=new JTextField();
            t2.setBounds(500,150,300,30);
            t2.setFont(new Font("Courier New",1,20));
            t2.setBackground(Color.WHITE);
            add(t2); 
            
            
             b1=new JButton("Submit");
            b1.setBounds(50,350,100,30);
           b1.addActionListener(this);
            add(b1);
            
            
            b2=new JButton("Commit");
            b2.setBounds(200,350,100,30);
           b2.addActionListener(this);
            add(b2);
            
            
            b3=new JButton("Show");
            b3.setBounds(350,350,100,30);
            b3.addActionListener(this);
            add(b3);
            
            b4=new JButton("Exit");
            b4.setBounds(500,350,100,30);
            add(b4); 
              b4.addActionListener(this);
           
            
            
                fp=new File("Manu.CSV");
           
            fw=new FileWriter(fp,true);
        }
        catch(Exception e){}
}
    @Override
    public void actionPerformed(ActionEvent e)
    { 
        
        
     

        
        Object objt= e.getSource();
        if(objt==b1)
        {
           emp ep =new emp(t1.getText(),t2.getText());
           listt.add(ep);
           JOptionPane.showMessageDialog(this,"Submitted" );
           //t1.setText("");
           //t2.setText("");
        }
        else if(objt==b2)
        {
            try
            {
            fw=new FileWriter(fp,true);
            for(int j=0;j<listt.size();j++)
            {
                emp em= listt.get(j);
                fw.write(em.getUID() + "," + em.getUname() + "\n");               
            }
                fw.close();
                listt = new LinkedList<>(); 
                //-----------------------------------------------------------------------------------------------
                String msg= "UserId=  "+ t1.getText()  +"\n" +
                            "User-Name=" + t2.getText();
                    JOptionPane optionpane=new JOptionPane();
                    optionpane.setMessage(msg);
                    optionpane.setMessageType(JOptionPane.INFORMATION_MESSAGE);
                    JDialog dialog=optionpane.createDialog(this,"Committed");
               dialog.setVisible(true);
              //--------------------------------------------------------------------------------------------------------
           //     JOptionPane.showMessageDialog(this,"Committed" );
            }
            catch(Exception f){}
        }   
        else if(objt==b3)
        {
            int i=1;            
            try{
                //fw.close();
                String rec;
                Scanner sc = new Scanner(fp);
             
                if(fp.exists())
                {
                    System.out.println("------------------------------------------");
                    System.out.println("SNO|USER ID         USER NAME");
                    System.out.println("------------------------------------------");
                    while((rec=sc.nextLine())!=null)
                    {
                        String recarray[]=rec.split(",");
                        System.out.printf("%3s|%4s          |%-20s\n",i,recarray[0],recarray[1]);
                        i++;
                    }                                    
                }
                
                sc.close();
                
        }
            catch(Exception d){}
                    System.out.println("------------------------------------------");
                    System.out.println("TOTAL USERS FOUND " + (i-1) );
                    System.out.println("------------------------------------------");
                    
                    
                    JOptionPane.showMessageDialog(this,"Total users are" + (i-1));
        }
        else if(objt==b4)
            System.exit(0);
            //JOptionPane.showMessageDialog(this,"Press close to Exit" );
    }
}


class MyFrame extends JFrame
{
    private MyPanel mp;
    public MyFrame()
    {
        mp=new MyPanel();
        mp.setBackground(Color.YELLOW);
        mp.setLayout(new BorderLayout());   //layout manager
        super.add(mp);
    }
}
public class FILE {

    public static void main(String[] args) {
       MyFrame f = new MyFrame();
       f.setVisible(true);
        f.setTitle("Login Form...");
        Toolkit tk = Toolkit.getDefaultToolkit();
        int w = tk.getScreenSize().width;
        int h = tk.getScreenSize().height;
        f.setSize(w/2, h/2);
        f.setLocationRelativeTo(null);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Dimension dim = new Dimension(400, 400);
        f.setMinimumSize(dim);      
    }
    
}
