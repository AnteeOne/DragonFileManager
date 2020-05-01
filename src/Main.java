import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        Main main = new Main();
        main.run();
    }

    public void run(){
        try {
            new ProcessBuilder("C:\\Users\\Azat\\Desktop\\JoJo s Bizarre Adventure.mp3").start();
        } catch (IOException e) {
            e.printStackTrace();
        }
        DragonFileManager.run();

    }

}
