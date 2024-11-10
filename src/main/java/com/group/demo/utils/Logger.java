package com.group.demo.utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import jakarta.servlet.http.HttpServletRequest;

public class Logger {

    private final String logName;
    private StringBuffer sb = new StringBuffer();

    public Logger(String logName) {
        this.logName = logName;
    }

    public String getLogName() {
        return logName;
    }

    public String getLoggerPath() {

        /*
         * This path os only intended for local development.
         * For real implementation, log files will be directed to any desired folder in
         * the server
         */
        Path path = Paths.get("logs/rest-api-logger");
        return path.toString();

    }

    public void startLog() {
        String logPrefix = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(Calendar.getInstance().getTime())
                .concat(": ");

        String divider = "======================================================";

        sb.append(logPrefix);
        sb.append(divider);
        sb.append(System.lineSeparator());
    }

    public void logRequest(HttpServletRequest request, String payload) {
        this.startLog();
        this.writeLog("HOST: " + request.getServerName());
        this.writeLog("REQUEST URL: " + request.getMethod() + " " + request.getRequestURI());

        if (request.getQueryString() != null) {
            this.writeLog("QUERY STRING: " + request.getQueryString());
        }

        if (payload != null) {
            this.writeLog("PAYLOAD: " + payload);
        }
    }

    public void writeLog(String logString) {
        String logPrefix = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(Calendar.getInstance().getTime())
                .concat(": ");

        sb.append(logPrefix);
        sb.append(logString);
        sb.append(System.lineSeparator());
    }

    public void printStackTrace(Exception e) {
        for (StackTraceElement el : e.getStackTrace()) {
            this.writeLog(el.toString());
        }
    }

    public void flushLog() {
        try {
            File file = Paths.get(this.getLoggerPath(), logName).toFile();
            if (!file.exists()) {
                file.mkdirs();
            }

            file = Paths.get(file.getAbsolutePath(),
                    new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime()).concat(".txt"))
                    .toFile();

            BufferedWriter out = new BufferedWriter(new FileWriter(file, true));
            out.write(sb.toString());
            out.flush();
            out.close();

            sb.setLength(0);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}
