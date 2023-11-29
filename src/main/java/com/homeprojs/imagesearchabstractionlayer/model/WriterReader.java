package com.homeprojs.imagesearchabstractionlayer.model;

import java.io.*;
import java.util.List;

/**
 * @author Yao Zhang
 * @date 2023-11-29
 * # @apiNote
 */
public class WriterReader {

    public WriterReader() {
    }

    public void writeToFile(List<Image> obj, String filePath) {
        FileOutputStream f = null;
        ObjectOutputStream o = null;
        try {
            f = new FileOutputStream(filePath);
            o = new ObjectOutputStream(f);
            o.writeObject(obj);

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                o.close();
                f.close();
            } catch (IOException | NullPointerException e) {
                e.printStackTrace();
            }
        }
    }

    public List<Image> readToObj(String filePath) {
        List<Image> dst = null;
        FileInputStream fi = null;
        ObjectInputStream oi = null;
        try {
            fi = new FileInputStream(new File(filePath));
            oi = new ObjectInputStream(fi);
            dst = (List<Image>) oi.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                oi.close();
                fi.close();
            } catch (IOException | NullPointerException e) {
                e.printStackTrace();
            }
        }
        return dst;
    }
}
