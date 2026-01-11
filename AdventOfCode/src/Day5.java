private static final List<String> INSTRUCTION_LIST = new ArrayList<>();
private static final List<String> MERGRED_INSTRUCTION_LIST = new ArrayList<>();
private static final List<BigInteger> ID_LIST = new ArrayList<>();
//private static int RESULT = 0;
private static BigInteger RESULT = BigInteger.ZERO;

void main() {
    //getTestDataFromFile();
    getDataFromFile();

    //PART1
    //findAndAddAllFresh();
    mergeOverlappingRanges();
    findAndCalculateAllFreshInRanges();

    //IO.println(RESULT);
    IO.println(RESULT);
}

private static void getDataFromFile() {
    try {
        File myObj = new File("data/inputDataDay5.txt");
        Scanner myReader = new Scanner(myObj);
        boolean hasEmptyLineBeenRead = false;
        while (myReader.hasNextLine()) {
            String line = myReader.nextLine();
            if (line.isEmpty()) {
                hasEmptyLineBeenRead = true;
            } else if (!hasEmptyLineBeenRead) {
                INSTRUCTION_LIST.add(line);
            } else {
                ID_LIST.add(BigInteger.valueOf(Long.parseLong(line)));
            }
        }
        myReader.close();
    } catch (FileNotFoundException e) {
        IO.println("An error occurred.");
        e.printStackTrace();
    }
}

private static void getTestDataFromFile() {
    try {
        File myObj = new File("data/test/inputDataDay5.txt");
        Scanner myReader = new Scanner(myObj);
        boolean hasEmptyLineBeenRead = false;
        while (myReader.hasNextLine()) {
            String line = myReader.nextLine();
            if (line.isEmpty()) {
                hasEmptyLineBeenRead = true;
            } else if (!hasEmptyLineBeenRead) {
                INSTRUCTION_LIST.add(line);
            } else {
                ID_LIST.add(BigInteger.valueOf(Long.parseLong(line)));
            }
        }
        myReader.close();
    } catch (FileNotFoundException e) {
        IO.println("An error occurred.");
        e.printStackTrace();
    }
}

//PART1
/*private static void findAndAddAllFresh(){
    for(BigInteger entry : ID_LIST){
        if(checkAgainstIdRanges(entry)){
            RESULT++;
        }
    }
}

private static boolean checkAgainstIdRanges(BigInteger value){
    for(String entry : INSTRUCTION_LIST){
        String [] idRange = entry.split("-");
        BigInteger start = BigInteger.valueOf(Long.parseLong(idRange[0]));
        BigInteger end = BigInteger.valueOf(Long.parseLong(idRange[1]));
        if(value.compareTo(start) >= 0 && value.compareTo(end) <= 0){
            return true;
        }
    }
    return false;
}*/

private static void mergeOverlappingRanges() {
    for (String entry : INSTRUCTION_LIST) {
        String[] idRange = entry.split("-");

        BigInteger finalStart = BigInteger.ZERO;
        BigInteger finalEnd = BigInteger.ZERO;

        BigInteger start = BigInteger.valueOf(Long.parseLong(idRange[0]));
        BigInteger end = BigInteger.valueOf(Long.parseLong(idRange[1]));
        if (MERGRED_INSTRUCTION_LIST.isEmpty()) {
            MERGRED_INSTRUCTION_LIST.add(entry);
        } else {
            finalStart = start;
            finalEnd = end;
            boolean isBetween = false;
            List<String> entriesToRemove = new ArrayList<>();
            for (String entryMerge : MERGRED_INSTRUCTION_LIST) {
                String[] idRangeMerged = entryMerge.split("-");
                BigInteger mergedStart = BigInteger.valueOf(Long.parseLong(idRangeMerged[0]));
                BigInteger mergedEnd = BigInteger.valueOf(Long.parseLong(idRangeMerged[1]));
                if (start.compareTo(mergedStart) >= 0 && end.compareTo(mergedEnd) <= 0) {
                    isBetween = true;
                } else if (start.compareTo(mergedStart) <= 0 && end.compareTo(mergedEnd) >= 0) {
                    entriesToRemove.add(entryMerge);
                } else {

                    if (start.compareTo(mergedStart) <= 0 && end.compareTo(mergedStart) >= 0 && end.compareTo(mergedEnd) <= 0) {

                        finalEnd = mergedEnd;
                        entriesToRemove.add(entryMerge);
                    }
                    if (start.compareTo(mergedStart) >= 0 && start.compareTo(mergedEnd) <= 0 && end.compareTo(mergedEnd) >= 0) {
                        finalStart = mergedStart;
                        entriesToRemove.add(entryMerge);
                    }
                }
            }
            if (!entriesToRemove.isEmpty()) {
                MERGRED_INSTRUCTION_LIST.removeAll(entriesToRemove);
            }
            if (!isBetween) {
                MERGRED_INSTRUCTION_LIST.add(finalStart + "-" + finalEnd);
            }
        }

    }
}

private static void findAndCalculateAllFreshInRanges() {
    for (String entry : MERGRED_INSTRUCTION_LIST) {
        String[] idRange = entry.split("-");
        BigInteger start = BigInteger.valueOf(Long.parseLong(idRange[0]));
        BigInteger end = BigInteger.valueOf(Long.parseLong(idRange[1]));

        RESULT = RESULT.add(end.subtract(start));
        RESULT = RESULT.add(BigInteger.ONE);
    }
}

