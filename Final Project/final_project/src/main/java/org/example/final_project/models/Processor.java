package org.example.final_project.models;

import java.io.File;

public interface Processor {
    void converImage(File inputFile, String outputFormat, File outputDirectory) throws Exception;

    void applyFilter(File inputFile, String filterType, File outputDirectory) throws Exception;

    void convertImage(File inputFile, String outputFormat, File outputDirectory) throws Exception;
}
