private static final List<String> INSTRUCTION_LIST = new ArrayList<>();
private static char[] ROLLS_ON_GRID = new char[1];
private static int ROWS = 0;
private static int COLS = 0;
private static int RESULT = 0;

void main() {
    //getTestDataFromFile();
    getDataFromFile();

    fillGrid();
    //printGrid();

    //Part1
    //claculateAccessibleRolls();

    boolean anyRemoved;
    do {
        anyRemoved = wasAnyRollsRemovedAndAddedToAccesible();
    } while (anyRemoved);

    IO.println(RESULT);
}

private static void getDataFromFile() {
    try {
        File myObj = new File("data/inputDataDay4.txt");
        Scanner myReader = new Scanner(myObj);
        while (myReader.hasNextLine()) {
            INSTRUCTION_LIST.add(myReader.nextLine());
        }
        myReader.close();

        ROWS = INSTRUCTION_LIST.getFirst().length();
        COLS = INSTRUCTION_LIST.size();
    } catch (FileNotFoundException e) {
        IO.println("An error occurred.");
        e.printStackTrace();
    }
}

private static void getTestDataFromFile() {
    try {
        File myObj = new File("data/test/inputDataDay4.txt");
        Scanner myReader = new Scanner(myObj);
        while (myReader.hasNextLine()) {
            INSTRUCTION_LIST.add(myReader.nextLine());
        }
        myReader.close();

        ROWS = INSTRUCTION_LIST.getFirst().length();
        COLS = INSTRUCTION_LIST.size();
    } catch (FileNotFoundException e) {
        IO.println("An error occurred.");
        e.printStackTrace();
    }
}

private static void fillGrid() {
    ROLLS_ON_GRID = new char[ROWS * COLS];
    for (int i = 0; i < ROWS; i++) {
        for (int j = 0; j < COLS; j++) {
            ROLLS_ON_GRID[i * COLS + j] = INSTRUCTION_LIST.get(i).charAt(j);
        }
    }
}

private static void printGrid() {
    for (int i = 0; i < ROWS; i++) {
        for (int j = 0; j < COLS; j++) {
            IO.print(ROLLS_ON_GRID[i * COLS + j]);
        }
        IO.println();
    }
}

//Part1
/*private static void claculateAccessibleRolls(){
    for(int i = 0; i < ROWS; i++) {
        for (int j = 0; j < COLS; j++) {
            if(ROLLS_ON_GRID[i*COLS+j] == '@' && checkIfHaveLessThan4Around(i, j)){
                RESULT++;
            }
        }
    }
}*/

private static boolean wasAnyRollsRemovedAndAddedToAccesible() {
    int removed = 0;

    for (int i = 0; i < ROWS; i++) {
        for (int j = 0; j < COLS; j++) {
            if (ROLLS_ON_GRID[i * COLS + j] == '@' && checkIfHaveLessThan4Around(i, j)) {
                removed++;
                ROLLS_ON_GRID[i * COLS + j] = '.';
                RESULT++;
            }
        }
    }
    return removed > 0;
}

private static boolean checkIfHaveLessThan4Around(int i, int j) {
    int startPosX = (i - 1 < 0) ? i : i - 1;
    int startPosY = (j - 1 < 0) ? j : j - 1;
    int endPosX = (i + 1 > ROWS - 1) ? i : i + 1;
    int endPosY = (j + 1 > COLS - 1) ? j : j + 1;
    int adjesentRolls = 0;

    for (int rowNum = startPosX; rowNum <= endPosX; rowNum++) {
        for (int colNum = startPosY; colNum <= endPosY; colNum++) {
            if (ROLLS_ON_GRID[rowNum * COLS + colNum] == '@') {
                adjesentRolls++;
            }
        }
    }
    return adjesentRolls < 5;
}
