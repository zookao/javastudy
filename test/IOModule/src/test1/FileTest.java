package test1;

import org.junit.Test;

import java.io.File;
import java.io.IOException;

/**
 * User: czc
 * Date: 2021-10-08
 */
public class FileTest {
    @Test
    public void test1() throws IOException {
        File dir1 = new File("folder1");
        if(!dir1.exists()){
            dir1.mkdir();
        }
        File file1 = new File(dir1,"1.txt");
        if(!file1.exists()){
            file1.createNewFile();
        }
        File dir2 = new File(dir1, "folder2");
        if(!dir2.exists()){
            dir2.mkdir();
        }
        File file2 = new File(dir2, "2.txt");
        if(!file2.exists()){
            file2.createNewFile();
        }
        file1.delete();
    }
}
