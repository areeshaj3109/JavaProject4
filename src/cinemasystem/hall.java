
package cinemasystem;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

/**
 *
 * @author Ali
 */
public class hall {
    private String category;
    seat[][] seats;
    
 
     public hall() throws FileNotFoundException {
        
    }
}

class silverHall extends hall {
    
    private int hallSize = 12*21;
    private double ticketPrice;

    public silverHall() throws FileNotFoundException {
        seats = new seat[12][21];
    }
    
    
    public silverHall(int id) throws FileNotFoundException, IOException {
        RandomAccessFile file = new RandomAccessFile("hall.dat","rw");
        file.seek(file.length());
        file.writeInt(id);
        file.writeInt(hallSize);
        seats = new seat[12][21];
        for(int i = 0; i < 12; i++) {
            for(int j = 0; j < 21; j++) {
                file.writeBoolean(false);
            }
        }
     }
}
class goldHall extends hall {
    
    private int hallSize = 9*18;
    private double ticketPrice;

    
    public goldHall(int id) throws FileNotFoundException, IOException {
        RandomAccessFile file = new RandomAccessFile("hall.dat","rw");
        file.seek(file.length());
        file.writeInt(id);
        file.writeInt(hallSize);
        seats = new seat[9][18];
        for(int i = 0; i < 9; i++) {
            for(int j = 0; j < 18; j++) {
                file.writeBoolean(false);
            }
        }
        
    }
    
}
class platinumHall extends hall {
    
    private int hallSize = 7*18;
    private double ticketPrice;

    
    public platinumHall(int id) throws FileNotFoundException, IOException {
        RandomAccessFile file = new RandomAccessFile("hall.dat","rw");
        file.seek(file.length());
        file.writeInt(id);
        file.writeInt(hallSize);
        seats = new seat[7][18];
        for(int i = 0; i < 7; i++) {
            for(int j = 0; j < 18; j++) {
                file.writeBoolean(false);
            }
        }
        
    }
    
}
    
