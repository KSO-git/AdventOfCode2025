private static final List<String> INSTRUCTION_LIST = new ArrayList<>();
//private static int RESULT = 0;
private static BigInteger RESULT = BigInteger.ZERO;

void main() {
    //getTestDataFromFile();
    getDataFromFile();
    //getMaxJoltage();
    getMaxJoltage12();

    IO.println(RESULT);
}

private static void getDataFromFile() {
    try {
        File myObj = new File("data/inputDataDay3.txt");
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
        File myObj = new File("data/test/inputDataDay3.txt");
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

//Part 1
/*private static void getMaxJoltage() {

    for (String entry : INSTRUCTION_LIST) {
        int[] intArray = getIntegerArray(entry);
        int positionOfFirstDigit = findPOsitionOfBiggest(0, intArray, true);
        int positionOfSecondDigit = findPOsitionOfBiggest(positionOfFirstDigit + 1, intArray, false);

        RESULT += (10 * intArray[positionOfFirstDigit] + intArray[positionOfSecondDigit]);

    }
}

private static int findPositionOfBiggest(int startingPoint, int[] array, boolean isFirst) {
    int result = startingPoint;
    int end = isFirst ? array.length - 1 : array.length;
    for (int i = startingPoint; i < end; i++) {
        if (array[i] > array[result]) {
            result = i;
        }
    }
    return result;
}*/

private static void getMaxJoltage12() {

    for (String entry : INSTRUCTION_LIST) {
        int[] intArray = getIntegerArray(entry);
        String result = getStringMaxJoltage(intArray);


        RESULT = RESULT.add(BigInteger.valueOf(Long.parseLong(result)));

    }
}

private static String getStringMaxJoltage(int[] intArray) {
    StringBuilder result = new StringBuilder();
    int maxDiff = intArray.length - 12;
    int startingPoint = 0;
    int endPoint = maxDiff;
    for (int i = 0; i < 12; i++) {
        int currentPosition = findPositionOfBiggestFor12(startingPoint, endPoint, intArray);
        startingPoint = currentPosition + 1;
        endPoint = intArray.length - 12 + i + 1;

        result.append(intArray[currentPosition]);
    }

    return result.toString();
}

private static int[] getIntegerArray(String input) {
    String[] parts = input.split("");
    int[] integerArray = new int[parts.length];
    for (int n = 0; n < parts.length; n++) {
        integerArray[n] = Integer.parseInt(parts[n]);
    }
    return integerArray;
}

private static int findPositionOfBiggestFor12(int startingPoint, int endingPoint, int[] array) {
    int result = startingPoint;
    for (int i = startingPoint; i <= endingPoint; i++) {
        if (array[i] > array[result]) {
            result = i;
        }
    }
    return result;
}
