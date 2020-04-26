import java.io.File;
import java.util.Date;

public class ConsoleWriter {

    public static void writeDir(File currentfile){

        File[] filesArr = currentfile.listFiles();
        System.out.printf("%24s","LastWriteTime");
        System.out.printf("%14s","Bytes");
        System.out.println("\t" + "Name");
        System.out.printf("%24s","-------------");
        System.out.printf("%14s","-----");
        System.out.println("\t" + "----");
        for(File file:filesArr){
            System.out.printf("%25s",new Date(file.lastModified()).toGMTString() + "\t");
            System.out.printf("%10s",file.length());
            System.out.print("\t" + file.getName());
            System.out.println();
        }

    }

    public static void writeDragon(){
        System.out.println("░░░░░░▀█▄░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░");
        System.out.println("▀▄▄░░░░░▀▀███▄▄▄░░░░░░░░░░░░░░░░░░░░░░░");
        System.out.println("░░▀▀██▄▄▄▄░░░▀▀▀██▄▄░░░░░░░░░░░░░░░░░░░");
        System.out.println("░░░░░░▀▀▀███▄▄░░░▀▀░▄▄▄▄░░░░░░░░░░░░░░░");
        System.out.println("░░░░░░░░░░▄█████████▀░░▀█░░░░░░░░░░░░░░");
        System.out.println("░░░░░░░░░▀█░░▀███▀░░░░░░▀█▄▄░░░░░░░░░░░");
        System.out.println("░░░░░▄█████░░░░░░░░░░░░░░░▀▀█░░░░░░░░░░");
        System.out.println("░░░░░░██▄░░░▀▀██░░░░░░░░░░░░██░░░░░░░░░");
        System.out.println("░░▄▄█▀▀▀▀░░░░▄░░░░░░░░▀▀██░░▀█▄░░░░░░░░");
        System.out.println("░░▄▄██░░░░░▀▀▀▀░░░░░░░░░░░░░░░▀█▄░░░░░░");
        System.out.println("██▀▀▀░░░░░░░▄░░░░░░░██▀▀█▄▄░░░▀████░░░░");
        System.out.println("░█▀░░░░░░░░▄▀▀░░░░░░░▀█▄░░▀██▄░░░▀▀█▄░░");
        System.out.println("░█▄░░░░░░░░░░░░░░░░░░░░█▄░░▀▀██▄░░░░██░");
        System.out.println("░░█▄░░░░░░░░░░░░██▀█▄░░▀██░░░░▄██▄░░█▀░");
        System.out.println("░░░█▄░░░░░░░░░▄█▀░░░▀█▄░▀██▄▄░░░▄███▀░░");
        System.out.println("░░░░▀█▄░░░░░░░█▀░░░░░░▀█▄░▀▀█▄░░░░░░░░░");
        System.out.println("░░░░░░▀██▄▄▄░▄█░░░░░░░░░████▀░░░░░░░░░░");
        System.out.println("░░░░░░░░░░▀▀▀▀▀░░░░░░░░░░░░░░░░░░░░░░░░");
        System.out.println();
    }

    public static void writeInfo(){
        System.out.println("DragonFileManager.Version 1.1 ");
        System.out.println("(c) AnteeOne 2019-2020 ");
    }
}
