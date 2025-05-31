package com.savr.savr.service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class YtDlpService {

    public List<String> getDownloadLinks(String url, boolean audioOnly) {
        List<String> result = new ArrayList<>();
        try {
            List<String> command = new ArrayList<>();
            command.add("yt-dlp");
            if (audioOnly) {
                command.add("-f");
                command.add("bestaudio");
            } else {
                command.add("-f");
                command.add("best");
            }
            command.add("-g"); // retorna links diretos
            command.add(url);

            ProcessBuilder pb = new ProcessBuilder(command);
            Process process = pb.start();

            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                result.add(line);
            }

            int exitCode = process.waitFor();
            if (exitCode != 0) {
                throw new RuntimeException("yt-dlp falhou com código: " + exitCode);
            }
        } catch (Exception e) {
            throw new RuntimeException("Erro ao executar yt-dlp", e);
        }
        return result;
    }
}
