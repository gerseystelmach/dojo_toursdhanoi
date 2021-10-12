public class Main {

    /*int nbDisk = 3;*/
    private static int[] disks;
    private static int[] column1;
    private static int[] column2;
    private static int[] column3;
    private static int[][] columns = new int[][] {column1, column2, column3};



    public static void main(String[] args) {
        createDisks(3);
        //System.out.println(createDisks(3).length);
        columns[0] = disks;
        for (int value : columns[0]) {
            System.out.println(value);
        }
        game();
    }

    public static int[] createDisks(int nbDisk) {
        disks = new int[nbDisk];
        for (int i = 0; i < nbDisk; i++) {
            disks[i] = nbDisk-i;
        }
        return disks;
    }


    public static Integer last(int column) {
        if(columns[column] == null) {
           return -1;
        }
        int nbNulls = 0;

        for (Integer value : columns[column]) {
            if (value == null) {
                nbNulls = nbNulls + 1;
            }
        }
        return columns[column].length -1 -nbNulls;

    }

    public static void game(){
        while (isFinished() == false) {
            if (move(1, 2)) {
                columns[last(1) + 1] = columns[last(0)];
                columns[last(0)] = null;
            }
            if (move(2, 3)) {
                columns[last(2) + 1] = columns[last(1)];
                columns[last(1)] = null;
            }

            if (move(1, 3)) {
                columns[last(2) + 1] = columns[last(0)];
                columns[last(0)] = null;
            }
            if (move(3, 2)) {
                columns[last(1) + 1] = columns[last(2)];
                columns[last(0)] = null;
            }
            if (move(3, 1)) {
                columns[last(0) + 1] = columns[last(2)];
                columns[last(0)] = null;
            }
        }
        System.out.println(isFinished());
    }

    public static boolean move(int columnFrom, int columnTo) {
        /* Taking the representation in int from columns index */
        if (columns[columnTo - 1] == null) {
            return true;
        }
        int nbNullsTo = 0;
        int nbNullsFrom = 0;
        for (int value : columns[columnTo]) {
            nbNullsTo = nbNullsTo+1;
        }
        for (int value : columns[columnFrom]) {
            nbNullsFrom = nbNullsFrom+1;
        }
        int lastColumnFrom = columns[columnFrom-1].length - 1 - nbNullsFrom;
        int lastColumnTo = columns[columnTo - 1].length - 1;

        if (columns[columnFrom - 1][lastColumnFrom] < columns[columnTo - 1][lastColumnTo]) {
            return true;
        }
        return false;
    }

    public static boolean isFinished(){
        if (columns[2] == null) {
            System.out.println("The last column has no disks.");
            return false;

        }
        if (columns[2].length == disks.length) {
            System.out.println("The game is finished.");
            return true;
        }
        System.out.println("Continue playing.");
        return false;
    }

}
