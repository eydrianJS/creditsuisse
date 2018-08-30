public class Main {

    public static void main(String [ ] args)
    {
        FileExecute file = new FileExecute("C:\\Users\\adria\\IdeaProjects\\Recruiment_task\\src\\paths\\test.txt");
        DatabaseMigration[] ArrayResult = file.CreateJsonToObject();
        findAllPair(ArrayResult);

//        Migration;[]
    }

    private static void findAllPair(DatabaseMigration[] arr) {
        String firstElem = arr[0].id;
        DatabaseMigration first = null;
        DatabaseMigration last = null;
        int counter = 0;
        for (DatabaseMigration result : arr) {
            if (result.id.contains(firstElem)) {
                if (result.state.contains("STARTED")) {
                    first = result;
                    arr.remove();
                } else {
                    last = result;
                }
            }

        }

        Long duration = getModelToDB(first, last);
        Boolean alert = duration > 4;
        Migration migration = new Migration(firstElem, duration + "ms", first.type, first.host, alert);
        System.out.print(firstElem);
        System.out.print(migration.duration);
        System.out.print(migration.type);
        System.out.print(migration.host);
        System.out.print(migration.alert);
    }

    private static Long getModelToDB(DatabaseMigration first, DatabaseMigration last) {
        return last.timestamp - first.timestamp;
    }



}
