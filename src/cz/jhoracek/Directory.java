package cz.jhoracek;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;
import java.util.stream.Collectors;

public class Directory {

    private File directory;

    public Directory(String path) {
        this.directory = new File(path);
    }

    public List<ArrayList<File>> findDuplicates() throws IOException, NoSuchAlgorithmException {
        HashMap<String, ArrayList<File>> hashes = new HashMap<>();

        File[] files = directory.listFiles();

        if (files != null) {
            for (File file : files) {
                String hash = hashValue(file);

                if (!hashes.containsKey(hash)) {
                    hashes.put(hash, new ArrayList<>());
                }

                ArrayList<File> filesWithSameHash = hashes.get(hash);

                if (!filesWithSameHash.isEmpty()) {
                    byte[] fileBytesWithSameHash = Files.readAllBytes(filesWithSameHash.get(0).toPath());
                    byte[] currentFileBytes = Files.readAllBytes(file.toPath());

                    if (Arrays.compare(fileBytesWithSameHash, currentFileBytes) == 0) {
                        filesWithSameHash.add(file);
                    }
                } else {
                    filesWithSameHash.add(file);
                }
            }

            // Pomoci Streamu
            List<ArrayList<File>> duplicateFiles = hashes.values()
                    .stream()
                    .filter(e -> e.size() > 1)
                    .collect(Collectors.toList());

            // Pomoci for cyklu
            /*
            List<ArrayList<File>> duplicateFiles = new LinkedList<>();

            for (ArrayList<File> value : hashes.values()) {
                if (value.size() > 1) {
                    duplicateFiles.add(value);
                }
            }
            */
            return duplicateFiles;

        }

        return null;
    }

    private String hashValue(File file) throws NoSuchAlgorithmException, IOException {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        md.update(Files.readAllBytes(file.toPath()));
        byte[] bytes = md.digest();
        return bytesToString(bytes);
    }

    private String bytesToString(byte[] hash) {
        StringBuilder hexString = new StringBuilder();

        for (byte b : hash) {
            if ((0xff & b) < 0x10) {
                hexString.append("0").append(Integer.toHexString((0xFF & b)));
            } else {
                hexString.append(Integer.toHexString(0xFF & b));
            }
        }
        return hexString.toString();
    }

}
