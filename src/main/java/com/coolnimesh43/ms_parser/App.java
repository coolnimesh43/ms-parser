package com.coolnimesh43.ms_parser;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.List;

import net.sf.mpxj.MPXJException;
import net.sf.mpxj.ProjectFile;
import net.sf.mpxj.Task;
import net.sf.mpxj.reader.ProjectReader;
import net.sf.mpxj.reader.ProjectReaderUtility;

/**
 * Hello world!
 *
 */
public class App {
    public static ProjectFile getProjectFile(String fileName, InputStream is) {
        try {
            ProjectReader projectReader = ProjectReaderUtility.getProjectReader(fileName);
            return projectReader.read(is);
        } catch (MPXJException e) {
            System.err.println("Exception while getting ProjectFile object from MS Project file. Exception is : {} " + e);
        }
        return null;
    }

    public static void main(String[] args) {
        String fileName = "/home/coolnimesh43/Downloads/Queen Anne - Progress Schedule - 2017.03.06.mpp";
        try {
            InputStream inputStream = new FileInputStream(fileName);
            ProjectFile file = getProjectFile(fileName, inputStream);
            List<Task> tasks = file.getAllTasks();
            tasks.forEach(t -> {
                System.out.println("Id: " + t.getID() + " Name: " + t.getName() + " actual start: " + t.getActualStart()
                        + " actual finish: " + t.getActualFinish() + " percentage: "
                        + (t.getPercentageComplete() == null ? 0 : t.getPercentageComplete().intValue()));
            });
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
