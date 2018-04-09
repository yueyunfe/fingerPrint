package com.machinezoo.sourceafis;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class AMain {
    public static void main(String[] argv){
        try {
            FilePath filePath1 = new FilePath("probe.png");
            FilePath filePath2 = new FilePath("nonmatching.png");

            byte[] probeImage = Files.readAllBytes(filePath1.getFilePath());
            byte[] candidateImage = Files.readAllBytes(filePath2.getFilePath());
            FingerprintTemplate probe = new FingerprintTemplate()
                    .dpi(500)
                    .create(probeImage);
            FingerprintTemplate candidate = new FingerprintTemplate()
                    .dpi(500)
                    .create(candidateImage);

            double score = new FingerprintMatcher()
                    .index(probe)
                    .match(candidate);

            if(score > 50){
                System.out.println("Matching");
            }else{
                System.out.println("NonMatching");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
