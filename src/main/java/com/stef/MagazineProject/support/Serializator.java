package com.stef.MagazineProject.support;

import org.apache.log4j.Logger;
import java.io.*;


public class Serializator {
    private static final Logger log = Logger.getLogger(Serializator.class);


    public static void saveData(String path, Object obj) {
        try {
            FileOutputStream someFile = new FileOutputStream(path + ".ser");
            ObjectOutputStream someObj = new ObjectOutputStream(someFile);
            someObj.writeObject(obj);
            someFile.close();
            someObj.close();
        } catch (FileNotFoundException e) {
            System.out.println("Exception " + e);
            log.error("Exception " + e);
        } catch (IOException e) {
            System.out.println("Exception " + e);
            log.error("Exception " + e);
        } catch (NullPointerException e) {
            System.out.println("Exception " + e);
            log.error("Exception " + e);
        }
    }


    public static Object getData(String path) {
        Object temp = null;
        try {
            FileInputStream someFile = new FileInputStream(path + ".ser");
            ObjectInputStream someObj = new ObjectInputStream(someFile);
            temp = someObj.readObject();
            someFile.close();
            someObj.close();
        } catch (FileNotFoundException e) {
            System.out.println("Exception " + e);
            log.error("Exception " + e);
        } catch (IOException e) {
            System.out.println("Exception " + e);
            log.error("Exception " + e);
        } catch (ClassNotFoundException e) {
            System.out.println("Exception " + e);
            log.error("Exception " + e);
        }
        return temp;
    }
}