import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class FileExecute {
    private String filePath = "";

    public FileExecute(String path) {
        this.filePath = path;
    }

    public ArrayList<DatabaseMigration> CreateJsonToObject() {

        ArrayList<DatabaseMigration> ArrayResult = new ArrayList<DatabaseMigration>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                Gson gson = new Gson();
                ArrayResult.add(gson.fromJson(line, DatabaseMigration.class));
            }

        } catch (IOException e) {
            e.printStackTrace();
        }


        return ArrayResult;
    }
}
