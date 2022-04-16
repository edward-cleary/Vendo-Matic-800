package com.techelevator.logs;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public abstract class Log {

    private File logFile;

    public Log(String filePath) {
        initialize(filePath);
    }

    public void initialize(String filePath) {
        logFile = new File(filePath);

        //create file if it doesn't exist
        if (!logFile.exists()) {
            try {
                logFile.createNewFile();
            } catch (IOException exception) {
                System.err.println(exception.getMessage());
            }
        }
    }

    protected void writeToLog(String logData) {
        try (PrintWriter logOutput = new PrintWriter(new FileOutputStream(logFile, true))) {
            logOutput.write(logData + System.lineSeparator());
        } catch (FileNotFoundException exception) {
            System.err.println("Invalid log file.");
        }
    }

    protected static String formatTime(LocalDateTime time) {
        return time.format(DateTimeFormatter.ofPattern("MM/dd/yyyy hh:mm:ss a"));
    }

    public void logSeparator() {
        writeToLog("```");
    }

}
