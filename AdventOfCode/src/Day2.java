private static final List<String> INSTRUCTION_LIST = new ArrayList<>();
private static BigInteger RESULT = BigInteger.ZERO;

void main() {
    //getTestDataFromFile();
    getDataFromFile();
    getAllInvalidIds();

    IO.println(RESULT);

}

private static void getDataFromFile() {
    try {
        File myObj = new File("data/inputDataDay2.txt");
        Scanner myReader = new Scanner(myObj);
        while (myReader.hasNextLine()) {
            String[] entries = myReader.nextLine().split(",");
            INSTRUCTION_LIST.addAll(Arrays.asList(entries));
        }
        myReader.close();
    } catch (FileNotFoundException e) {
        IO.println("An error occurred.");
        e.printStackTrace();
    }
}

private static void getTestDataFromFile() {
    try {
        File myObj = new File("data/test/inputDataDay2.txt");
        Scanner myReader = new Scanner(myObj);
        while (myReader.hasNextLine()) {
            String[] entries = myReader.nextLine().split(",");
            INSTRUCTION_LIST.addAll(Arrays.asList(entries));
        }
        myReader.close();
    } catch (FileNotFoundException e) {
        IO.println("An error occurred.");
        e.printStackTrace();
    }
}

private static void getAllInvalidIds() {

    for (String entry : INSTRUCTION_LIST) {
        String[] idPair = entry.split("-");
        BigInteger startInt = BigInteger.valueOf(Long.parseLong(idPair[0]));
        BigInteger endInt = BigInteger.valueOf(Long.parseLong(idPair[1]));
        interateFromStartToEnd(startInt, endInt);
    }

}

private static void interateFromStartToEnd(BigInteger start, BigInteger end) {
    BigInteger current = start;
    while (current.compareTo(end) <= 0) {
        if (checkIfIdIsInvalid(current)) {
            RESULT = RESULT.add(current);
        }
        current = current.add(BigInteger.ONE);
    }

    IO.println("Current result after " + start + " to " + end + " is: " + RESULT);
}

// Part1
/*private static boolean checkIfIdIsInvalid(BigInteger current) {
    String currentString = String.valueOf(current);
    if (currentString.length() % 2 != 0) {
        return false;
    }
    String front = currentString.substring(0, (currentString.length()/2));
    String end = currentString.substring(currentString.length()/2);

    return front.equals(end);
}*/

//Part2
private static boolean checkIfIdIsInvalid(BigInteger current) {
    String currentString = String.valueOf(current);
    int currentSize = currentString.length();
    List<Integer> divisorsList = getDivisors(currentSize);
    for (int entry : divisorsList) {
        int success = 0;
        int diffrence = currentSize / entry;
        for (int i = 0; i <= currentSize - 2 * diffrence; i += diffrence) {
            String front = currentString.substring(i, i + diffrence);
            String end = currentString.substring(i + diffrence, i + 2 * diffrence);
            if (front.equals(end)) {
                success++;
            }
        }
        if (success == entry - 1) {
            return true;
        }
    }
    return false;
}

private static List<Integer> getDivisors(int n) {
    List<Integer> result = new ArrayList<>();
    for (int i = 1; i <= Math.sqrt(n); i++) {
        if (n % i == 0) {
            result.add(i);
            if (i != n / i) {
                result.add(n / i);
            }
        }
    }
    Collections.sort(result);
    result.removeFirst();

    return result;
}
