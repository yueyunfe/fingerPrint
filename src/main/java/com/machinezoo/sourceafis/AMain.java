package com.machinezoo.sourceafis;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class AMain {

    private static List<candidateMatching> candidateList = new ArrayList<>();
    private static List<Double> scores = new ArrayList<>();
    private static String[] names =new String[]{"backgroundfilter","contrast","halfblur","linefilter","pixelate3","rotate90","shadow","transparency","penarteffect","thermalFilter","freedraw"};
    public static void main(String[] argv){
        try {
            initialCandidate();

            FilePath filePath1 = new FilePath("probe.png");
            byte[] probeImage = Files.readAllBytes(filePath1.getFilePath());

            FingerprintTemplate probe = new FingerprintTemplate()
                    .dpi(500)
                    .create(probeImage);


            for(int i = 0; i < candidateList.size(); i++){
                FilePath candidatePath = new FilePath(candidateList.get(i).getName()+".png");
                byte[] candidateImage = Files.readAllBytes(candidatePath.getFilePath());
                FingerprintTemplate candidate = new FingerprintTemplate()
                        .dpi(500)
                        .create(candidateImage);
                double score = new FingerprintMatcher()
                        .index(probe)
                        .match(candidate);
                scores.add(score);
            }

            for(int i = 0; i < scores.size(); i++){
                String words = "\nName: " + names[i] + "     Matching Score: "+ scores.get(i);
                System.out.println(words);
            }







//            if(score > 50){
//                System.out.println("Matching");
//            }else{
//                System.out.println("NonMatching");
//            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void initialCandidate(){
        for(int i = 0; i < names.length; i++){
            candidateMatching candidateMatching = new candidateMatching(names[i]);
            candidateList.add(candidateMatching);
        }

    }
}