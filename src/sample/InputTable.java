package sample;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.*;

/**
 * Created by pacman29 on 24.03.17.
 */
public class InputTable {
    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }
    private String filename;
    private File file;


    public ArrayList<ArrayList<Double>> getFileTable() {
        return fileTable;
    }

    public void setFileTable(ArrayList<ArrayList<Double>> fileTable) {
        this.fileTable = fileTable;
    }

    private ArrayList<ArrayList<Double>> fileTable;

    public InputTable(String filename){
        this.filename = filename;

        this.OpenFiles();

        try {
            List<String> tmp = Files.readAllLines(file.toPath());
            this.fileTable = new ArrayList<ArrayList<Double>>(4);
            this.fileTable.add(new ArrayList<Double>());
            this.fileTable.add(new ArrayList<Double>());
            this.fileTable.add(new ArrayList<Double>());


            for(int i = 0; i<tmp.size(); ++i){
                String[] values = tmp.get(i).split(" ");
                this.fileTable.get(0).add(Double.valueOf(values[0]));
                this.fileTable.get(1).add(Double.valueOf(values[1]));
                this.fileTable.get(2).add(Double.valueOf(values[2]));
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void OpenFiles(){
        this.file = new File(this.filename);
    }
}
