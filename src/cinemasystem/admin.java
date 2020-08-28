
package cinemasystem;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;


public class admin {
    RandomAccessFile file = new RandomAccessFile("admin.dat","rw");
    private String name, cnic, username, password;

    public admin() throws FileNotFoundException {
    }

    public admin(String name, String cnic, String username, String password) throws FileNotFoundException {
        this.name = name;
        this.cnic = cnic;
        this.username = username;
        this.password = password;
    }
    
    
    public void read() throws IOException {
        char[] temp = new char[15];
        for (int i = 0; i < temp.length; i++)
            temp[i] = file.readChar();
        name = new String(temp);
        name = name.trim();
        
        temp = new char[13];
        for (int i = 0; i < temp.length; i++)
            temp[i] = file.readChar();
        cnic = new String(temp);
        cnic = cnic.trim();
        
        temp = new char[15];
        for (int i = 0; i < temp.length; i++)
            temp[i] = file.readChar();
        username = new String(temp);
        username = username.trim();
        
        temp = new char[15];
        for (int i = 0; i < temp.length; i++)
            temp[i] = file.readChar();
        password = new String(temp);
        password = password.trim();

    }
    
    public int size() {
        return 2*(15 + 13 + 15 + 15);
    }
    
   
    public void write() throws IOException {
         
        StringBuffer sb = new StringBuffer(name);
        sb.setLength(15);
        file.writeChars(sb.toString());
        
        sb = new StringBuffer(cnic);
        sb.setLength(13);
        file.writeChars(sb.toString());
        
        sb = new StringBuffer(username);
        sb.setLength(15);
        file.writeChars(sb.toString());
        
        sb = new StringBuffer(password);
        sb.setLength(15);
        file.writeChars(sb.toString());
    }
    
     public boolean login(String username, String password) throws IOException {
        file.seek(0);
        boolean access = false;
        int n = (int) file.length()/size();
        for(int i = 0; i < n; i++) {
            read();
            if(this.username.equals(username) && this.password.equals(password)) {
                access = true;
                break;
            }
        }
        return access;
    }
    
    public void updatePassword(String username, String newPassword) throws IOException {
        file.seek(0);
        int n = (int) file.length()/size();
        for(int i = 0; i < n; i++) {
            read();
            if(this.username.equals(username)) {
                file.seek(file.getFilePointer() - 30);
                StringBuffer sb = new StringBuffer(newPassword);
                sb.setLength(15);
                file.writeChars(sb.toString());
                break;
            }
        }
    }
    
    public void addAdmin() throws IOException {
        file.seek(file.length());
        write();
    }
    
    public boolean removeAdmin(String username) throws IOException {
        file.seek(0);
        boolean found = false;
        int n = (int) file.length()/size();
        for(int i = 0; i < n; i++) {
            read();
            if(this.username.equals(username)) {
                found = true;
                for(int j = i+1; j < n; j++) {
                    read();               
                    file.seek(file.getFilePointer() - 2*size());
                    write();
                    file.seek(file.getFilePointer() + size());
                }
                file.setLength(file.length() - size());
                break;
            }
              
        }
        return found;
    }
        
    
   
}
