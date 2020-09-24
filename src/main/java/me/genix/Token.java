package me.genix;

import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

/**
 * @author github.com/genixzero
 * @created 24/09/2020 - 9:28 PM
 */
public class Token {
    private String token;

    public Token() throws IOException {
        File file = new File("config.txt");
        if (file.exists()) {
            List<String> lines = Files.readAllLines(Paths.get(file.getPath()));

            for (String str : lines) {
                if (str.startsWith("Token: ") && !str.equals("Token: ")) {
                    String i = str.substring(7);
                    if (i.length() > 24) {
                        token = i;
                    }
                }
            }

            if (token == null) {
                PrintWriter pw = new PrintWriter(file);
                pw.print("Token: Token-Goes-Here");
                pw.close();

                JOptionPane.showMessageDialog(null, "Please enter a token.");
                System.exit(-1);
            }
        } else {
            file.createNewFile();

            PrintWriter pw = new PrintWriter(file);
            pw.print("Token: Token-Goes-Here");
            pw.close();

            JOptionPane.showMessageDialog(null, "Please enter a token in the config file.");
            System.exit(-1);
        }
    }

    public String getToken() {
        return this.token;
    }
}
