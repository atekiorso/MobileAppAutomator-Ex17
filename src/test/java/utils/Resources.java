package utils;

import java.io.File;
import java.nio.file.Files;
import java.util.Base64;

public class Resources {
    final String RESOURCED_DIRECTORY = "src/test/resources";

    public String getImageBase64(String imageFileName) throws Exception {
        File imageFile = new File(RESOURCED_DIRECTORY + "/" + imageFileName);
        return Base64.getEncoder().encodeToString(Files.readAllBytes(imageFile.toPath()));
    }
}
