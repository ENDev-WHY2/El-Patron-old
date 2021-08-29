package org.endev.elpatron;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

public class Token {

    private final File TOKEN_FILE = new File("token");

    public Token() {

        if (!TOKEN_FILE.exists()) {
            try
            {
                Files.createFile(TOKEN_FILE.toPath());

                System.err.println("Token wasn't found.");
                System.exit(1);
            } catch (IOException e)
            {
                System.out.println("Error while creating new token file.");
                e.printStackTrace();
            }
        }
    }

    public String getToken() throws IOException
    {
        List<String> lines = Files.readAllLines(TOKEN_FILE.toPath());
        return lines.get(0);
    }
}
