private static final int STARTING_POINT = 50;
private static final List<String> INSTRUCTION_LIST = new ArrayList<>();
private static final char RIGHT = 'R';

void main() {
    //getTestDataFromFile();
    getDataFromFile();

    //System.out.println(calcuatePassword());
    IO.println(calcuatePasswordWithAllClicks());

}

private static void getDataFromFile() {
    try {
        File myObj = new File("data/inputDataDay1.txt");
        Scanner myReader = new Scanner(myObj);
        while (myReader.hasNextLine()) {
            INSTRUCTION_LIST.add(myReader.nextLine());
        }
        myReader.close();
    } catch (FileNotFoundException e) {
        IO.println("An error occurred.");
        e.printStackTrace();
    }
}

private static void getTestDataFromFile() {
    try {
        File myObj = new File("data/test/inputDataDay1.txt");
        Scanner myReader = new Scanner(myObj);
        while (myReader.hasNextLine()) {
            INSTRUCTION_LIST.add(myReader.nextLine());
        }
        myReader.close();
    } catch (FileNotFoundException e) {
        IO.println("An error occurred.");
        e.printStackTrace();
    }
}

private static int calcuatePassword() {

    int result = 0;
    int currentPoint = STARTING_POINT;
    for (String entry : INSTRUCTION_LIST) {
        IO.println("Instruction " + entry);
        if (entry.charAt(0) == RIGHT) {
            currentPoint += Integer.parseInt(entry.substring(1));
        } else {
            currentPoint -= Integer.parseInt(entry.substring(1));
        }
        IO.println("Point after manipulation " + currentPoint);
        if (currentPoint % 100 == 0) {
            result++;
        }
        while (currentPoint < 0) {
            currentPoint += 100;
        }
        currentPoint = Math.abs(currentPoint % 100);
        IO.println("After limiting " + currentPoint);
    }


    return result;
}

private static int calcuatePasswordWithAllClicks() {

    int result = 0;
    int currentPoint = STARTING_POINT;
    int numberToMove = 0;
    int previous = 0;
    for (String entry : INSTRUCTION_LIST) {
        IO.println("Instruction " + entry);
        numberToMove = Integer.parseInt(entry.substring(1));
        previous = currentPoint;
        if (entry.charAt(0) == RIGHT) {
            currentPoint += numberToMove;
        } else {
            currentPoint -= numberToMove;
        }
        IO.println("Point after manipulation " + currentPoint);
        if (currentPoint % 100 == 0) {
            result++;
        }
        while (currentPoint < 0) {
            currentPoint += 100;
            result++;
            if (previous == 0) {
                result--;
            }
            previous = currentPoint;
        }
        while (currentPoint > 100) {
            currentPoint -= 100;
            result++;

        }
        IO.println("Result " + result);
        currentPoint = Math.abs(currentPoint % 100);
        IO.println("After limiting " + currentPoint);
    }


    return result;
}