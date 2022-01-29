package bz.nils.dev.jmettbot.Data.ApiKeys;

import org.springframework.stereotype.Service;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class ApiKeyService {
    private final Logger logger = Logger.getLogger(this.getClass().toString());

    private List<String> keys;

    public Boolean keyExists(String key) {
        if (keys == null) {
            loadKeysFromFile();
        }

        if (keys == null) {
            return false;
        }

        return keys.contains(key);
    }

    private void loadKeysFromFile() {
        keys = new ArrayList<>();

        try {
            InputStream inputStream = new FileInputStream("keys.txt");
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

            while(reader.ready()) {
                String line = reader.readLine();
                keys.add(line);
            }
        } catch (IOException e) {
            keys = null;
            logger.log(Level.WARNING, "Could not load ApiKeys from file.", e);
        }
    }
}
