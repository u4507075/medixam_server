/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package exammap;

import exam.Detail;
import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.io.FilenameUtils;
import properties.P;

/**
 *
 * @author bon
 */
public class Exammap {
    HashMap<String,Exam> namemap = new HashMap<>();
    HashMap<String,String> imagemap = new HashMap<>();
    public String read()
    {
        File docfolder = new File(P.DOCFILEPATH);
        File[] filelist = docfolder.listFiles(new DocFileFilter());

        for(int i=0;i<filelist.length;i++)
        {
            Exam ex = new Exam();
                try {
                    String complete = ex.read(filelist[i]);
                    if(!complete.equals("complete"))
                    {
                        return complete;
                    }
                } catch (IOException ex1) {
                    return filelist[i].getName()+" is not found.";
                }
            namemap.put(FilenameUtils.getBaseName(filelist[i].getName()), ex);
        }
        return "complete";
    }
    public Object[] getKeyset()
    {
        return namemap.keySet().toArray();
    }
    public int getExamsize()
    {
        return namemap.size();
    }
    public Exam getExam(String examname)
    {
        return namemap.get(examname);
    }
    public HashMap<String,String> getImagemap()
    {
        return imagemap;
    }
    public class DocFileFilter implements FileFilter
    {
    private final String[] okFileExtensions =
        new String[] {"docx"};

        @Override
    public boolean accept(File file)
    {
        for (String extension : okFileExtensions)
        {
        if (file.getName().toLowerCase().endsWith(extension))
        {
            return true;
        }
        }
        return false;
    }
    }
}
