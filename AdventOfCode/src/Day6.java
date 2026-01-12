private static final List<String> INSTRUCTION_LIST = new ArrayList<>();
private static BigInteger RESULT = BigInteger.ZERO;
private static int NUMBER_OF_NUMBERS_IN_ROW = 0;
private static final List<Integer> LOCATION_OF_OPERATORS = new ArrayList<>();

void main() {
    //getTestDataFromFile();
    getDataFromFile();

    getNumberOfNumbersInRow();
    fillOperatorsLocations();

    doAllMath();

    IO.println(RESULT);
}

//PART1
/*private static void getDataFromFile() {
    try {
        File myObj = new File("data/inputDataDay6.txt");
        Scanner myReader = new Scanner(myObj);
        while (myReader.hasNextLine()) {
            String line = myReader.nextLine().replaceAll("\\s+", " ").trim();
                INSTRUCTION_LIST.add(line);
        }
        myReader.close();
    } catch (FileNotFoundException e) {
        IO.println("An error occurred.");
        e.printStackTrace();
    }
}

private static void getTestDataFromFile() {
    try {
        File myObj = new File("data/test/inputDataDay6.txt");
        Scanner myReader = new Scanner(myObj);
        while (myReader.hasNextLine()) {
            String line = myReader.nextLine().replaceAll("\\s+", " ").trim();
                INSTRUCTION_LIST.add(line);
        }
        myReader.close();
    } catch (FileNotFoundException e) {
        IO.println("An error occurred.");
        e.printStackTrace();
    }
}*/

private static void getDataFromFile() {
    try {
        File myObj = new File("data/inputDataDay6.txt");
        Scanner myReader = new Scanner(myObj);
        while (myReader.hasNextLine()) {
            String line = myReader.nextLine();
            INSTRUCTION_LIST.add(line);
        }
        myReader.close();
    } catch (FileNotFoundException e) {
        IO.println("An error occurred.");
        e.printStackTrace();
    }
}

private static void getTestDataFromFile() {
    try {
        File myObj = new File("data/test/inputDataDay6.txt");
        Scanner myReader = new Scanner(myObj);
        while (myReader.hasNextLine()) {
            String line = myReader.nextLine();
            INSTRUCTION_LIST.add(line);
        }
        myReader.close();
    } catch (FileNotFoundException e) {
        IO.println("An error occurred.");
        e.printStackTrace();
    }
}

private static void getNumberOfNumbersInRow() {
    NUMBER_OF_NUMBERS_IN_ROW = INSTRUCTION_LIST.getFirst().replaceAll("\\s+", " ").split(" ").length;
}

private static void fillOperatorsLocations() {
    String lastRow = INSTRUCTION_LIST.getLast();
    for (int i = 0; i < lastRow.length(); i++) {
        if (lastRow.charAt(i) != ' ') {
            LOCATION_OF_OPERATORS.add(i);
        }
    }
}

//PART1
/*private static void doAllMath(){
    for(int i = 0; i < NUMBER_OF_NUMBERS_IN_ROW; i++){
        BigInteger total = BigInteger.ONE;
        String operation = INSTRUCTION_LIST.getLast().split(" ")[i];
        for(int j = 0; j < INSTRUCTION_LIST.size() - 1; j++){
           total = switch (operation) {
                case "+" -> total.add(BigInteger.valueOf(Long.parseLong(INSTRUCTION_LIST.get(j).split(" ")[i])));
                case "*" -> total.multiply(BigInteger.valueOf(Long.parseLong(INSTRUCTION_LIST.get(j).split(" ")[i])));
                default -> total;
            };
        }
        if(operation.equals("+")){
            total = total.subtract(BigInteger.ONE);
        }
        RESULT = RESULT.add(total);
    }
}*/

private static void doAllMath() {
    for (int i = 0; i < NUMBER_OF_NUMBERS_IN_ROW; i++) {
        BigInteger total = BigInteger.ONE;
        String operation = String.valueOf(INSTRUCTION_LIST.getLast().charAt(LOCATION_OF_OPERATORS.get(i)));
        List<String> values = new ArrayList<>();
        for (int j = 0; j < INSTRUCTION_LIST.size() - 1; j++) {
            int currentPointer = LOCATION_OF_OPERATORS.get(i);
            boolean wasDigitHit = false;
            StringBuilder valueOfNumber = new StringBuilder();
            char currentChar = INSTRUCTION_LIST.get(j).charAt(currentPointer);
            do {
                valueOfNumber.append(currentChar);
                if (Character.isDigit(currentChar)) {
                    wasDigitHit = true;
                }
                currentPointer++;
                if (currentPointer != INSTRUCTION_LIST.getFirst().length()) {
                    currentChar = INSTRUCTION_LIST.get(j).charAt(currentPointer);
                }

            } while ((!wasDigitHit || currentChar != ' ') && currentPointer < INSTRUCTION_LIST.getFirst().length());
            values.add(valueOfNumber.toString());
        }
        values = fixValues(values);
        List<BigInteger> normalNumbers = getNormalNumbers(values);
        for (BigInteger entry : normalNumbers) {
            total = switch (operation) {
                case "+" -> total.add(entry);
                case "*" -> total.multiply(entry);
                default -> total;
            };
        }
        if (operation.equals("+")) {
            total = total.subtract(BigInteger.ONE);
        }
        RESULT = RESULT.add(total);
    }
}

private static List<String> fixValues(List<String> values) {
    List<String> result = new ArrayList<>();
    int maxSize = 0;
    for (String entry : values) {
        if (entry.length() > maxSize) {
            maxSize = entry.length();
        }
    }
    for (String entry : values) {
        if (entry.length() < maxSize) {
            StringBuilder entryBuilder = new StringBuilder(entry);
            while (entryBuilder.length() < maxSize) {
                entryBuilder.append(" ");
            }
            entry = entryBuilder.toString();
        }
        result.add(entry);
    }

    return result;
}

private static List<BigInteger> getNormalNumbers(List<String> values) {
    List<BigInteger> normalNumbers = new ArrayList<>();
    int length = values.getFirst().length();
    for (int i = length - 1; i >= 0; i--) {
        StringBuilder singleValue = new StringBuilder();
        for (String value : values) {
            singleValue.append(value.charAt(i));
        }
        normalNumbers.add(BigInteger.valueOf(Long.parseLong(singleValue.toString().trim())));
    }

    return normalNumbers;
}

