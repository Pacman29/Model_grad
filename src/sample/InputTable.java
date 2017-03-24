package sample;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.*;

/**
 * Created by pacman29 on 24.03.17.
 */
public class InputTable {
    public String getFilename_n() {
        return filename_n;
    }

    public void setFilename_n(String filename_n) {
        this.filename_n = filename_n;
    }

    public String getFilename_t() {
        return filename_t;
    }

    public void setFilename_t(String filename_t) {
        this.filename_t = filename_t;
    }

    public String getFilename_sigma() {
        return filename_sigma;
    }

    public void setFilename_sigma(String filename_sigma) {
        this.filename_sigma = filename_sigma;
    }

    private String filename_n, filename_t, filename_sigma;
    private File file_n, file_t, file_sigma;


    private ArrayList<ArrayList<Double>> n, sigma, n_log, sigma_log, t;

    public InputTable(String filename_n, String filename_t, String filename_sigma){
        this.filename_n = filename_n;
        this.filename_sigma = filename_sigma;
        this.filename_t = filename_t;

        this.OpenFiles();

        try {
            List<String> tmp = Files.readAllLines(file_n.toPath());
            this.n = new ArrayList<ArrayList<Double>>(4);
            this.n.add(new ArrayList<Double>());
            this.n.add(new ArrayList<Double>());
            this.n.add(new ArrayList<Double>());
            this.n.add(new ArrayList<Double>());

            for(int i = 0; i<tmp.size(); ++i){
                String[] values = tmp.get(i).split(" ");
                this.n.get(0).add(Double.valueOf(values[0]));
                this.n.get(1).add(Double.valueOf(values[1]));
                this.n.get(2).add(Double.valueOf(values[2]));
                this.n.get(3).add(Double.valueOf(values[3]));
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            List<String> tmp = Files.readAllLines(file_sigma.toPath());
            this.sigma = new ArrayList<ArrayList<Double>>(4);
            this.sigma.add(new ArrayList<Double>());
            this.sigma.add(new ArrayList<Double>());
            this.sigma.add(new ArrayList<Double>());
            this.sigma.add(new ArrayList<Double>());

            for(int i = 0; i<tmp.size(); ++i){
                String[] values = tmp.get(i).split(" ");
                this.sigma.get(0).add(Double.valueOf(values[0]));
                this.sigma.get(1).add(Double.valueOf(values[1]));
                this.sigma.get(2).add(Double.valueOf(values[2]));
                this.sigma.get(3).add(Double.valueOf(values[3]));
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        this.t = this.ReadTable(this.file_t);

        try {
            List<String> tmp = Files.readAllLines(file_t.toPath());
            this.t = new ArrayList<ArrayList<Double>>(3);
            this.t.add(new ArrayList<Double>());
            this.t.add(new ArrayList<Double>());
            this.t.add(new ArrayList<Double>());


            for(int i = 0; i<tmp.size(); ++i){
                String[] values = tmp.get(i).split(" ");
                this.t.get(0).add(Double.valueOf(values[0]));
                this.t.get(1).add(Double.valueOf(values[1]));
                this.t.get(2).add(Double.valueOf(values[2]));
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        this.n_log = this.get_log(this.n);
        this.sigma_log = this.get_log(this.sigma);
    }

    private ArrayList<ArrayList<Double>> ReadTable(File file){

        ArrayList<ArrayList<Double>> result = new ArrayList<ArrayList<Double>>();
        try {
            List<String> tmp = Files.readAllLines(file.toPath());
            for(int i = 0; i<tmp.size(); ++i){
                String[] values = tmp.get(i).split(" ");

            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    private ArrayList<ArrayList<Double>> get_log(ArrayList<ArrayList<Double>> table){
        ArrayList<ArrayList<Double>> result = new ArrayList<ArrayList<Double>>();
        for(Integer i = 0; i< table.size(); ++i){
            result.add(new ArrayList<Double>());
            for(Double val: table.get(i)){
                result.get(i).add(Math.log(val));
            }
        }
        return result;
    }

    private void OpenFiles(){
        this.file_n = new File(this.filename_n);
        this.file_t = new File(this.filename_t);
        this.file_sigma = new File(this.filename_sigma);
    }

    public Double get_sigma(Double t, Double p){

        Integer it = 0;
        if(t < this.sigma.get(0).get(0)){
            it = 1;
        } else if( t > sigma.get(0).get(sigma.get(0).size()-1)){
            it = sigma.get(0).size()-1;
        } else {
            while (sigma.get(0).get(it) < t)
                it++;
        }

        Integer ip = 2;
        Double p0 = 5.;
        Double p1 = 15.;
        if(p > 15){
            ip = 3;
            p0 += 10.;
            p1 += 10.;
        }
        Double sigma1 = this.sigma_log.get(ip-1).get(it-1) + (sigma_log.get(ip -1).get(it) - sigma_log.get(ip-1).get(it-1)) *
                (t - sigma.get(0).get(it-1)) / (sigma.get(0).get(it) - sigma.get(0).get(it-1));

        Double sigma2 = this.sigma_log.get(ip).get(it-1) + (sigma_log.get(ip).get(it) - sigma_log.get(ip).get(it-1)) *
                (t - sigma.get(0).get(it-1)) / (sigma.get(0).get(it) - sigma.get(0).get(it-1));

        sigma1 = Math.exp(sigma1);
        sigma2 = Math.exp(sigma2);

        return sigma1 + (sigma2 - sigma1) * (p - p0)/(p1 - p0);

    }

    public Double get_n(Double t, Double p){

        Integer it = 0;
        if(t < this.n.get(0).get(0)){
            it = 1;
        } else if( t > n.get(0).get(n.get(0).size()-1)){
            it = n.get(0).size()-1;
        } else {
            while (n.get(0).get(it) < t)
                it++;
        }

        Integer ip = 2;
        Double p0 = 5.;
        Double p1 = 15.;
        if(p > 15){
            ip = 3;
            p0 += 10;
            p1 += 10;
        }
        Double n1 = this.n_log.get(ip-1).get(it-1) + (n_log.get(ip -1).get(it) - n_log.get(ip-1).get(it-1)) *
                (t - n.get(0).get(it-1)) / (n.get(0).get(it) - n.get(0).get(it-1));

        Double n2 = this.n_log.get(ip).get(it-1) + (n_log.get(ip).get(it) - n_log.get(ip).get(it-1)) *
                (t - n.get(0).get(it-1)) / (n.get(0).get(it) - n.get(0).get(it-1));

        n1 = Math.exp(n1);
        n2 = Math.exp(n2);

        return n1 + (n2 - n1) * (p - p0)/(p1 - p0);
    }

    public Double get_t_t(Double t, Double m, Double i){
        Integer ind = 0;

        if(i < this.t.get(0).get(0)){
            ind = 1;
        } else if (i > this.t.get(0).get(this.t.get(0).size()-1)){
            ind = this.t.get(0).size()-1;
        } else {
            while (this.t.get(0).get(ind) < i)
                ind++;
        }

        if(ind == 0.){
            return  (this.t.get(1).get(ind)) *
                    (i) / (this.t.get(0).get(ind));
        }

        return this.t.get(1).get(ind - 1) + (this.t.get(1).get(ind) - this.t.get(1).get(ind - 1)) *
                (i - this.t.get(0).get(ind - 1)) / (this.t.get(0).get(ind) - this.t.get(0).get(ind - 1));

    }

    public Double get_t_m(Double t, Double m, Double i){
        Integer ind = 0;

        if(i < this.t.get(0).get(0)){
            ind = 1;
        } else if (i > this.t.get(0).get(this.t.get(0).size()-1)){
            ind = this.t.get(0).size()-1;
        } else {
            while (this.t.get(0).get(ind) < i)
                ind++;
        }

        if(ind == 0.){
            return (this.t.get(2).get(ind)) *
                    (i) / (this.t.get(0).get(ind));
        }
        return this.t.get(2).get(ind - 1) + (this.t.get(2).get(ind) - this.t.get(2).get(ind - 1)) *
                (i - this.t.get(0).get(ind - 1)) / (this.t.get(0).get(ind) - this.t.get(0).get(ind - 1));

    }

}
