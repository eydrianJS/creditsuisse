import java.sql.Connection;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Set;

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
        for (int i=0; i<arr.size(); i++) {
            if (arr.get(i).state.contains("STARTED")) {
                startData.put(arr.get(i).id, arr.get(i));
            } else {
                finishData.put(arr.get(i).id, arr.get(i));
            }
        }
        buildResult();
    }

    private static void buildResult() {
        Set<String> keysID;
        ArrayList<Migration> insertsToDb= new ArrayList<Migration>();
        if(startData.size() >= finishData.size()) {
            keysID = startData.keySet();
        } else {
            keysID = finishData.keySet();
        }

       for(String id : keysID) {
           if (finishData.get(id) == null) throw new IllegalArgumentException("Id: " + id + " hasn't finish in this file." );
           if (startData.get(id) == null)  throw new IllegalArgumentException("Id: " + id + " hasn't start in this file." );
           DatabaseMigration start = startData.get(id);
           DatabaseMigration finish = finishData.get(id);
           Long duration = finish.timestamp - start.timestamp;
           String durationDB = duration+ "ms";
           Boolean alert = duration > 4;
           Migration dbImport = new Migration(id, durationDB, start.type, start.host, alert);
           insertsToDb.add(dbImport);
       }

        ConnectionDB dbConnection = new ConnectionDB();
        dbConnection.insertToTable(insertsToDb);
    }
}
