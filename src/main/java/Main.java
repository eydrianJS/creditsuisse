import java.util.ArrayList;
import java.util.LinkedHashMap;

public class Main {
    private static LinkedHashMap <String,DatabaseMigration> startData = new LinkedHashMap<String,DatabaseMigration>();
    private static LinkedHashMap <String,DatabaseMigration> finishData = new LinkedHashMap<String,DatabaseMigration>();

    public static void main(String [] args)
    {
        String path = args[0];
        FileExecute file = new FileExecute(path);
        ArrayList<DatabaseMigration> ArrayResult = file.CreateJsonToObject();
        findAllPair(ArrayResult);
    }

    private static void findAllPair(ArrayList<DatabaseMigration> arr) {
        String firstElem = arr.get(0).id;
        for (int i=0; i<arr.size(); i++) {
            if (arr.get(i).id.contains(firstElem)) {
                if (arr.get(i).state.contains("STARTED")) {
                    startData.put(arr.get(i).id, arr.get(i));
                    arr.remove(i);
                } else {
                    finishData.put(arr.get(i).id, arr.get(i));
                    arr.remove(i);
                }
            }
        }
        if (arr.size() >= 1) {
            findAllPair(arr);
        } else  {
            buildResult();
        }

    }

    private static void buildResult() {
       for(String id : startData.keySet()) {
           DatabaseMigration start = startData.get(id);
           DatabaseMigration finish = finishData.get(id);
           Long duration = finish.timestamp - start.timestamp;
           String durationDB = duration+ "ms";
           Boolean alert = duration >= 4;
           Migration dbImport = new Migration(id, durationDB, start.type, start.host, alert);
           ConnectionDB dbConnection = new ConnectionDB();
           dbConnection.insertToTable(dbImport);
       }
    }


}
