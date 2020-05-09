import sun.security.krb5.internal.crypto.Des;

import java.awt.*;
import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class DragonFileManager {

        private Scanner sc;
        private String currentPath;
        private File currentFile;

    public DragonFileManager() {
        this.currentPath = System.getProperty("user.dir")+ File.separator+"src";
        this.currentFile = new File(currentPath);
        this.sc = new Scanner(System.in);
    }

    public static void run(){
        DragonFileManager dFManager = new DragonFileManager();
        dFManager.parseCommands();

    }

    public void parseCommands(){
        ConsoleWriter.writeDragon();
        ConsoleWriter.writeInfo();
        System.out.println(getHelp());
        System.out.println();
        System.out.print(" <" + currentFile.getAbsolutePath() + "> ");
        String command = sc.nextLine();
        while (true) {
            switch (command.split(" ")[0]) {
                case "/cd":
                    if (command.split(" ").length != 2) {
                        System.out.println("Incorrect path. Please check your new path_1");
                    } else {
                        try {
                            Path newPath = Paths.get(command.split(" ")[1]);
                            File newFile = new File(newPath.toString());
                            boolean isNewFilePathiIsAbsolute = newFile.isAbsolute();
                            boolean isNewFileExists = newFile.exists();
                            if (!isNewFilePathiIsAbsolute) {
                                File changeableFile = new File(this.currentFile, newPath.toString()).getCanonicalFile();
                                if (changeableFile.exists()) {
                                    this.currentFile = changeableFile;
                                } else {
                                    System.out.println("Incorrect path.File doesn't exist_2");
                                }
                            } else {
                                if (newFile.exists()) {
                                    this.currentFile = newFile.getAbsoluteFile();
                                } else {
                                    System.out.println("Incorrect path.File doesn't exist_3");
                                }

                            }
                        } catch (InvalidPathException | IOException e) {
                            System.out.println("Incorrect path. Please check your new path_4");
                            e.printStackTrace();
                        }

                    }
                    break;


                case "/delete":
                    try {
                        Files.deleteIfExists(this.currentFile.toPath());
                        this.currentFile = new File(this.currentFile, "../").getCanonicalFile();
                    } catch (IOException e) {
                        System.out.println("IOError!");
                    }
                    break;


                case "/copyto":

                    if (command.split(" ").length != 2) {
                        System.out.println("Incorrect path. Please check your new path");
                    } else {
                        try {
                            Path newPath = Paths.get(command.split(" ")[1]);
                            File newFile = new File(newPath.toString() + "/" + this.currentFile.getName());
                            boolean isNewFilePathiIsAbsolute = newFile.isAbsolute();
                            boolean isNewFileExists = newFile.exists();
                            if (!isNewFilePathiIsAbsolute) {

                                File changeableFile = new File(this.currentFile, newPath.toString() + "/" + this.currentFile.getName()).getCanonicalFile();
                                Files.copy(currentFile.toPath(), changeableFile.toPath());
                            } else {
                                Files.copy(currentFile.toPath(), newFile.toPath());
                            }
                        } catch (InvalidPathException | IOException e) {
                            e.printStackTrace();
                        }

                    }


                    break;

                case "/open":
                    try {
                        Desktop desktop = Desktop.getDesktop();
                        if (this.currentFile.canExecute()) {
                            if (desktop.isSupported(Desktop.Action.OPEN)) {
                                desktop.open(this.currentFile);
                            }
                        } else {
                            System.out.println("This file can't be execute!");
                        }
                    } catch (IOException e) {
                        System.out.println("Problems with executing!");
                    }
                    break;

                case "/dir":
                    System.out.println();
                    ConsoleWriter.writeDir(currentFile);
                    System.out.println();
                    break;

                case "/print":
                    //test path - C:\Users\Azat\Desktop\EnglishEssays.DOCX
                    if(command.split(" ").length != 2){
                        System.out.println("Incorrect command.Maybe you have just forgotten to write some Charset?");
                    }
                    else{
                        String charset = command.split(" ")[1];
                        try (BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(currentFile), charset))) {
                            String line;
                            while ((line = in.readLine()) != null) {
                                System.out.println(line);
                            }
                        } catch (FileNotFoundException e0) {
                            System.out.println("Please , move to file directory , not folder!");
                        } catch (UnsupportedEncodingException e314){
                            System.out.println("Error!Please type existing encoding!");
                            System.out.println("US-ASCII");
                            System.out.println("ISO-8859-1");
                            System.out.println("UTF-8");
                            System.out.println("UTF-16BE");
                            System.out.println("UTF-16LE");
                            System.out.println("UTF-16");
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    break;
                case "/exit":
                    return;

                case "/help":
                    System.out.println(getHelp());
                    break;
                default:
                    System.out.println("This command is doesn't exist");
                    System.out.println("Input /help to get help with supported commands");

            }
            System.out.print(" <" + this.currentFile.getAbsolutePath() + "> ");
            command = sc.nextLine();
        }
    }

    public static String getHelp(){
        return "Available commands: /dir , /cd , /help , /delete , /copyto , /print";
    }


}
