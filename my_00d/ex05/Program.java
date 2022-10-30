import java.util.Scanner;

public class Program {
    public final static String MODE_SEPARATOR = ".";
    public final static char WORD_SEPARATOR = ' ';
    public final static String CELL_SEPARATOR = "|";
    public final static String HERE = "HERE";
    public final static String NOT_HERE = "NOT_HERE";

    public final static int MODE_STUDENTS_INFO = 0;
    public final static int MODE_TIMETABLE_INFO = 1;
    public final static int MODE_ATTENDANCE_INFO = 2;
    public final static int MODE_DISPLAYING = 3;  

    public final static int MAX_STUDENT_COUNT = 10;
    public final static int MAX_TOTAL_CLASS_COUNT = 100;
    public final static int MAX_CLASS_PER_WEEK = 10;
    public final static int MAX_CLASS_PER_DAY = 6;
    public final static int MAX_STUDENT_NAME_LENGTH = 10;
    public final static int CELL_LENGTH = 10;
    
    public final static int FIRST_WEEKDAY_OF_MONTH = 1;
    public final static int DAY_COUNT_OF_MONTH = 30;
    public final static int DAY_COUNT_OF_WEEK = 7;
    public final static String[] WEEKDAY_NAMES = {"MO", "TU", "WE", "TH", "FR", "SA", "SU"};

    public static String[] students = new String[MAX_STUDENT_COUNT];
    public static int[] monthdayToWeekdayMatch = new int[DAY_COUNT_OF_MONTH];
    public static int[] classHoursCount = new int[DAY_COUNT_OF_WEEK];
    public static int[][] listOfClassHours = new int[DAY_COUNT_OF_WEEK][MAX_CLASS_PER_DAY];
    public static int[][] classIDToDatetimeMatch = new int[MAX_TOTAL_CLASS_COUNT][2];
    public static int[][] attendances = new int[MAX_STUDENT_COUNT][MAX_TOTAL_CLASS_COUNT];

    public static int studentCount = 0;
    public static int classCount = 0;
    public static int CURRENT_MODE = MODE_STUDENTS_INFO;

    public static void main(String args[]) {
        Scanner scanner = new Scanner(System.in);
        String nextLine = "";

        calculateWeekdays();
        while (true) { 
            nextLine = scanner.nextLine();
            if (nextLine.equals(MODE_SEPARATOR)){
                CURRENT_MODE += 1;
                if (CURRENT_MODE == MODE_DISPLAYING){
                    displayTimetable();
                    break;
                } else if (CURRENT_MODE == MODE_ATTENDANCE_INFO){
                    lineUpClasses();
                }
                continue;
            }
            if (CURRENT_MODE == MODE_STUDENTS_INFO){
                addStudent(nextLine);
            } else if (CURRENT_MODE == MODE_TIMETABLE_INFO){
                addTime(nextLine);
            } else if (CURRENT_MODE == MODE_ATTENDANCE_INFO){
                addAttendance(nextLine);
            } 
        }
    }

    public static void calculateWeekdays(){
        for (int i = 0; i < DAY_COUNT_OF_MONTH; i++){
            monthdayToWeekdayMatch[i] = (i + FIRST_WEEKDAY_OF_MONTH) % DAY_COUNT_OF_WEEK;
        }
    }

    public static void addStudent(String studentName){
        if (studentCount == MAX_STUDENT_COUNT) {
            exitProgram("TOO MUCH STUDENTS");
        }
        if (studentName.length() > MAX_STUDENT_NAME_LENGTH){
            exitProgram("TOO LONG NAME");
        }
        students[studentCount] = studentName;
        studentCount++;
    }

    public static void addTime(String timeAndWeekday){
        char[] chars = timeAndWeekday.toCharArray();
        int classTime = Character.getNumericValue(chars[0]);
        String classWeekday = Character.toString(chars[2]) + Character.toString(chars[3]);
        
        int weekdayInt = weekdayNameToInt(classWeekday);
        int currentClassCountOfWeekday = classHoursCount[weekdayInt];
        if (currentClassCountOfWeekday == MAX_CLASS_PER_DAY){
            exitProgram("TOO MUCH CLASSES AT THIS DAY");
        }
        listOfClassHours[weekdayInt][currentClassCountOfWeekday] = classTime;
        classHoursCount[weekdayInt]++;
    }

    public static void addAttendance(String attendanceInfo){
        int startPosition;
        int hour;
        int day;
        int studentID;
        int classID;
        int studentPresence;
        String studentName;
        String hourString;
        String dayString;
        String studentPresenceString;
        char[] chars = attendanceInfo.toCharArray();

        startPosition = 0;
        studentName = parseCharArray(chars, startPosition);
        startPosition += studentName.length() + 1;
        hourString = parseCharArray(chars, startPosition);
        startPosition += hourString.length() + 1;
        dayString = parseCharArray(chars, startPosition);
        startPosition += dayString.length() + 1;
        studentPresenceString = parseCharArray(chars, startPosition);

        if (studentPresenceString.equals(HERE)){
            studentPresence = 1;
        } else if (studentPresenceString.equals(NOT_HERE)){
            studentPresence = -1;
        } else {
            studentPresence = 0;
            exitProgram("BAD PARAMETERS");
        }
        try {
            hour = stringToInt(hourString);
            day = stringToInt(dayString);
            studentID = getStudentIDByName(studentName);
            classID = getClassIDByDatetime(hour, day);
            attendances[studentID][classID] = studentPresence;
        } catch (Throwable exception){
            exitProgram("BAD PARAMETERS");
        }
    }

    public static void lineUpClasses(){
        for (int weekdayInt = 0; weekdayInt < DAY_COUNT_OF_WEEK; weekdayInt++){
            listOfClassHours[weekdayInt] = sortArray(listOfClassHours[weekdayInt], classHoursCount[weekdayInt]);
        }
        for (int day = 0; day < DAY_COUNT_OF_MONTH; day++){
            int weekdayInt = monthdayToWeekdayMatch[day];
            if (classHoursCount[weekdayInt] > 0){
                for (int hourID = 0; hourID < classHoursCount[weekdayInt]; hourID++){
                    if (classCount == MAX_TOTAL_CLASS_COUNT){
                        exitProgram("TOO MUCH TOTAL CLASS COUNT");
                    }
                    classIDToDatetimeMatch[classCount][0] = listOfClassHours[weekdayInt][hourID];
                    classIDToDatetimeMatch[classCount][1] = day + 1;
                    classCount++;
                }
            }
        }
    }

    public static String parseCharArray(char[] chars, int startPosition){
        String parsedString = "";
        int i = startPosition;
        while (true){
            char c = chars[i];
            if (c == WORD_SEPARATOR){
                return parsedString;
            }
            parsedString += Character.toString(c);
            i++;
            if (i == chars.length){
                return parsedString;
            }
        }
    }

    public static int stringToInt(String stringToParse){
        char[] chars = stringToParse.toCharArray();
        int parsedInt = 0;
        int pow = 1;
        for (int i = chars.length - 1; i >= 0; i--){
            int d = Character.getNumericValue(chars[i]);
            parsedInt += d * pow;
            pow *= 10;
        }
        return parsedInt;
    }

    public static int getStudentIDByName(String studentName){
        for (int i  = 0; i < studentCount; i++){
            if (studentName.equals(students[i])){
                return i;
            }
        }
        return -1;
    }

    public static int getClassIDByDatetime(int hour, int day){
        for (int i = 0; i < classCount; i++){
            int tempHour = classIDToDatetimeMatch[i][0];
            int tempDay = classIDToDatetimeMatch[i][1];
            if (hour == tempHour && day == tempDay){
                return i;
            }
        }
        return -1;
    }

    public static int weekdayNameToInt(String weekdayName){
        for (int i = 0; i < DAY_COUNT_OF_WEEK; i++){
            if (WEEKDAY_NAMES[i].equals(weekdayName)){
                return i;
            }
        }
        exitProgram("WRONG WEEKDAY NAME");
        return -1;
    }

    public static void exitProgram(String message){
        System.out.println(message);
        System.exit(0);
    } 

    public static void displayTimetable(){
        String emptySpace;
        String studentName;
        int studentPresence;

        emptySpace = makeEmptySpace(MAX_STUDENT_NAME_LENGTH);
        System.out.print(emptySpace);
        for (int classID = 0; classID < classCount; classID++){
            int hour = classIDToDatetimeMatch[classID][0];
            int day = classIDToDatetimeMatch[classID][1];
            int weekdayInt = monthdayToWeekdayMatch[day - 1];
            String weekdayName = WEEKDAY_NAMES[weekdayInt];
            System.out.print(hour);
            System.out.print(":00 ");
            System.out.print(weekdayName);
            if (day < 10){
                System.out.print(" ");
            }
            System.out.print(" ");
            System.out.print(day);
            System.out.print(CELL_SEPARATOR);
        }
        System.out.println();

        for (int studentID = 0; studentID < studentCount; studentID++){
            studentName = students[studentID];
            emptySpace = makeEmptySpace(MAX_STUDENT_NAME_LENGTH - studentName.length());
            System.out.print(emptySpace);
            System.out.print(studentName);
            for (int classID = 0; classID < classCount; classID++){
                studentPresence = attendances[studentID][classID];
                if (studentPresence == 1){
                    emptySpace = makeEmptySpace(CELL_LENGTH - 1);
                } else if (studentPresence == -1){
                    emptySpace = makeEmptySpace(CELL_LENGTH - 2);
                } else {
                    emptySpace = makeEmptySpace(CELL_LENGTH);
                }
                System.out.print(emptySpace);
                if (studentPresence == 1 || studentPresence == -1){
                    System.out.print(studentPresence);
                }
                System.out.print(CELL_SEPARATOR);
            }
            System.out.println();
        }
    }

    public static String makeEmptySpace(int spaceLength){
        String emptySpace = "";
        for(int i = 0; i < spaceLength; i++){
            emptySpace += " ";
        }
        return emptySpace;
    }

    public static int[] sortArray(int[] arrayToSort, int elementCount){
        int[] sortedArray = new int[MAX_CLASS_PER_DAY];
        for (int i = 0; i < MAX_CLASS_PER_DAY; i++){
            sortedArray[i] = 0;
        }
        for (int passID = 0; passID < elementCount; passID++){
            int currentMin = 99;
            int currentMinID = 0;
            for (int i = 0; i < MAX_CLASS_PER_DAY; i++){
                if (arrayToSort[i] < currentMin && arrayToSort[i] > 0){
                    currentMin = arrayToSort[i];
                    currentMinID = i;
                }
            }
            sortedArray[passID] = currentMin;
            arrayToSort[currentMinID] = 0;
        }
        return sortedArray;
    }
}
