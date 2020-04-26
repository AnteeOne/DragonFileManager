import java.io.File;
import java.io.IOException;
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
                    if(command.split(" ").length != 2){
                        System.out.println("Incorrect path. Please check your new path");
                    }
                    else{
                        try {
                            Path newPath = Paths.get(command.split(" ")[1]);
                            File newFile = new File(newPath.toString());
                            boolean isNewFilePathiIsAbsolute = newFile.isAbsolute();
                            boolean isNewFileExists = newFile.exists();
                            if(!isNewFilePathiIsAbsolute){
                                File changeableFile = new File(this.currentFile,newPath.toString()).getCanonicalFile();
                                if(changeableFile.exists()){
                                    this.currentFile = changeableFile;
                                }
                                else{
                                    System.out.println("Incorrect path.File doesn't exist");
                                }
                            }
                            else{
                                if(newFile.exists()){
                                    this.currentFile = newFile.getAbsoluteFile();
                                }
                                else{
                                    System.out.println("Incorrect path.File doesn't exist");
                                }

                            }
                        }
                        catch (InvalidPathException | IOException e){
                            System.out.println("Incorrect path. Please check your new path");
                        }

                    }
                    break;
                case "/dir":
                    System.out.println();
                    ConsoleWriter.writeDir(currentFile);
                    System.out.println();
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
        return "Available commands: /dir , /cd , /help";
    }


}
