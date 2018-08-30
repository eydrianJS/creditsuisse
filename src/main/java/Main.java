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
        for (DatabaseMigration result : arr) {
            if (result.id.contains(firstElem)) {
                if (result.state.contains("STARTED")) {
                    first = result;

                } else {
                    last = result;
                }
            }

        }

        getModelToDB(first, last);
    }

    private static void getModelToDB(DatabaseMigration first,DatabaseMigration last) {
        Long time = last.timestamp - first.timestamp;
        System.out.print(last.timestamp + "\n");
        System.out.print(first.timestamp + "\n");
    }



}
