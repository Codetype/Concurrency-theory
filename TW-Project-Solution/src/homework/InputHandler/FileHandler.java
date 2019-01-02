package homework.InputHandler;

import java.io.*;

public class FileHandler {
    private static FileHandler instance = null;
    private BufferedWriter writer;
    private String path;

    @SuppressWarnings("deprecation")
    private FileHandler() {
        this.path = "./src/output/output.txt";
        BufferedWriter writer;
        try {
            File file = new File(path);
            writer = new BufferedWriter(new FileWriter(file));
            this.writer = writer;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public BufferedWriter getWriter() {
        return this.writer;
    }

    public static synchronized FileHandler getInstance() {
        if(instance == null) {
            instance = new FileHandler();
        }
        return instance;
    }
}