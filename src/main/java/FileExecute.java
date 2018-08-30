import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
public class FileExecute {
    private String filePath = "";

    public FileExecute(String path) {
        this.filePath = path;
    }

    public DatabaseMigration[] CreateJsonToObject() {

        DatabaseMigration[] ArrayResult = new DatabaseMigration[6];
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            int count = 0;
            while ((line = br.readLine()) != null) {
                Gson gson = new Gson();
                ArrayResult[count] = gson.fromJson(line, DatabaseMigration.class);
                count++;
            }

        } catch (IOException e) {
            e.printStackTrace();
        }


        return ArrayResult;
    }
}
