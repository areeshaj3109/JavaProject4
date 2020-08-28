/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cinemasystem;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import static java.awt.Toolkit.getDefaultToolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;


class silverHall1 extends JFrame implements ActionListener{
    private static final int width =700;
    private static final int hieght =700;
    private static final int seatRows = 12;
    private static final int seatColumns = 21;
    private JLabel someL,keyL,key1L,key2L,key3L,screenL;
    private boolean value[][] = new boolean[seatRows][seatColumns];
    private boolean tempvalue[][] = new boolean[seatRows][seatColumns];
    private JButton exitJB,submitJB;
    private JButton seat [][];
    private ButtonHandler ebHandler,sbHandler;
    private ImageIcon icong,iconr,icony;
    private int id,c=0;
    private movie mov;
    private customer cus;
    
    public silverHall1(int id, movie m, customer c) throws FileNotFoundException, IOException{
        this.setLocation((int)getDefaultToolkit().getScreenSize().getWidth()/2 - 350,(int) getDefaultToolkit().getScreenSize().getHeight()/2 - 350);
        cus = new customer(c.getName(),c.getCnic(),c.getAddress(),c.getPhone());
        mov = new movie(m.getId(),m.getTitle(),m.getGenre(),m.getRating(),m.getDuration(),m.getDate(),m.getTime(),m.getHall());
        setTitle("Hall Seating Arrangement");
        someL = new JLabel("Enter the length: ",SwingConstants.RIGHT);
        RandomAccessFile file = new RandomAccessFile("hall.dat","rw");
        
        iconr = new ImageIcon(getClass().getResource("/cinemasystem/seatR.png"));
        icong = new ImageIcon(getClass().getResource("/cinemasystem/seatG.png"));
        icony = new ImageIcon(getClass().getResource("/cinemasystem/seatY.png"));
        
        // EXit button
        exitJB = new JButton("Exit");
        ebHandler = new ButtonHandler();
        exitJB.addActionListener(ebHandler);
        submitJB = new JButton("Book");
        sbHandler = new ButtonHandler();
        submitJB.addActionListener(sbHandler);
        
        // Seat buttons
        boolean found = false;
        while(!found && (file.getFilePointer() < file.length()) ) {
           
            if(file.readInt() == id){
                found = true;
                file.readInt();        
                seat = new JButton[seatRows][seatColumns];
                for(int i=0;i<seatRows;i++){
                    for(int j=0;j<seatColumns;j++){
                        seat[i][j] = new JButton();                
                        if(file.readBoolean()) {          
                            value[i][j] = true;
                            seat[i][j].setIcon(iconr); // NOI18N
                        }
                        else{
                            seat[i][j].setIcon(icong);
                        }
                        seat[i][j].setBorder(null);
                        seat[i][j].setPreferredSize(new Dimension(32, 32));
                        seat[i][j].setMaximumSize(new Dimension(32, 32));
                        seat[i][j].setMinimumSize(new Dimension(32, 32));
                        seat[i][j].addActionListener(this);
                    }
                }
            }
            else {
                int skip = file.readInt();
                file.seek(file.getFilePointer() + skip);
            }
        }
        JPanel panel1 = new JPanel(new GridLayout(seatRows+1,seatColumns+1));
        for(int i=0;i<seatRows+1;i++){
            if(i < seatRows)
                panel1.add(new JLabel(""+(char)((int)('A')+i)));
            else
                panel1.add(new JLabel(""));
            for(int j=0;j<seatColumns;j++){
                if(i < seatRows)
                    panel1.add(seat[i][j]);
                else
                    panel1.add(new JLabel(""+(seatColumns-j),SwingConstants.CENTER));
            }
        }
        
        JPanel key = new JPanel(new GridLayout(3,3));
        key.add(new JLabel("Please Select your Seats:"));
        key.add(new JLabel());
        key.add(new JLabel());
        keyL = new JLabel("Key:");
        keyL.setForeground(Color.RED);
        keyL.setFont(new Font("Arial", Font.PLAIN, 26));
        key.add(keyL);
        key.add(new JLabel());
        key.add(new JLabel());
        key1L = new JLabel();
        key1L.setIcon(icong);
        key1L.setText("= Availabile");
        key.add(key1L);
        key2L = new JLabel();
        key2L.setIcon(icony);
        key2L.setText("= Selected");
        key.add(key2L);
        key3L = new JLabel();
        key3L.setIcon(iconr);
        key3L.setText("= Booked");
        key.add(key3L);

        
        JPanel button = new JPanel(new GridLayout(2,2));
        button.add(new JLabel());
        button.add(new JLabel());
        button.add(exitJB);
        button.add(submitJB);
        JPanel panel2 = new JPanel(new GridLayout(2,1));
        screenL = new JLabel("Screen This Way", SwingConstants.CENTER);
        screenL.setBackground(Color.ORANGE);
        screenL.setOpaque(true);
        panel2.add(screenL);
        panel2.add(button);
        
          
        
        Container pane = getContentPane();
        pane.setLayout(new BorderLayout());
        pane.add(panel1, BorderLayout.CENTER);
        pane.add(panel2, BorderLayout.SOUTH);
        
      
      
        setSize(width,hieght);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        for(int i=0;i<seatRows;i++){
            for(int j=0;j<seatColumns;j++){
                if(ae.getSource()== seat[i][j])
                    if(! value[i][j]){
                        tempvalue[i][j] = !tempvalue[i][j];
                        paint(i,j);
                    }else{}
            }
        }
    }
    private class ButtonHandler implements ActionListener{
        public void actionPerformed(ActionEvent press){
            if(press.getSource()== exitJB)  {
                
                try {
                    new viewMovies().setVisible(true);
                } catch (SQLException ex) {
                    Logger.getLogger(silverHall1.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(silverHall1.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if(press.getSource()== submitJB){
                c=0;
               for(int i = 0; i < tempvalue.length; i++) {
            for(int j = tempvalue[0].length - 1; j >= 0; j--) {
                if(tempvalue[i][j]) {
                    c++;
                  
                }
            }
        }
                    
                    try {
                    if(c > 0) {
                        if(c<=10) {
                    int dialogresult = JOptionPane.showConfirmDialog(null, "Do you want to book these seats?","Warning",JOptionPane.YES_NO_OPTION);
                    if(dialogresult == JOptionPane.YES_NO_OPTION) {
                
                        JOptionPane.showMessageDialog(null, "Seats booked.");
                        new receipt(tempvalue,mov,cus).setVisible(true);
                        v();
                    }
                        }else JOptionPane.showMessageDialog(null, "Can not book more than 10 seats.");
                    }
                    else {
                        JOptionPane.showMessageDialog(null, "Please book a seat to proceed.");
                    }
                } catch (IOException ex) {
                    Logger.getLogger(silverHall1.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
      public void v() {
        this.setVisible(false);
    }
    
    public void paint(int i, int j){
        if (tempvalue[i][j]) seat[i][j].setIcon(icony);
        else seat[i][j].setIcon(icong);
            
    }
  
    
    
    public static void main(String[] args) throws IOException {
        // TODO code application logic here
        int id = 0;
        movie m = null;
        customer c = null;
        silverHall1 h = new silverHall1(id,m,c);
    }
    
}
