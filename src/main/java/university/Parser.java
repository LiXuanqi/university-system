//package university;
//
//import java.io.BufferedReader;
//import java.io.File;
//import java.io.FileReader;
//import java.io.IOException;
//import java.util.Scanner;
//import java.util.regex.Matcher;
//import java.util.regex.Pattern;
//
//public class Parser {
//    public static void main(String[] args) throws IOException {
//        // CLI mode
//        if (args.length == 1 && args[0].equals("command")) {
//            inputFromCLI();
//            return;
//        }
//
//        // File mode
//        if (args.length == 2 && args[0].equals("readFile")) {
//            readFromFile(args[1]);
//            return;
//        }
//
//        printNotice();
//    }
//
//    private static void inputFromCLI() {
//        boolean isFinished = false;
//        while (!isFinished) {
//            Scanner in = new Scanner(System.in);
//
//            printCommands();
//
//            String input = in.nextLine();
//
//            System.out.print('\n');
//
//            translateToCommand(input);
//
//        }
//    }
//
//    private static void readFromFile(String filepath) throws IOException {
//        File file = new File(filepath);
//
//        BufferedReader br = new BufferedReader(new FileReader(file));
//
//        String st;
//        while ((st = br.readLine()) != null) {
//            translateToCommand(st);
//        }
//    }
//
//    private static void translateToCommand(String command) {
//        System.out.println("[input] " + command);
//
//        Matcher createAnimalMatcher = Pattern.compile("^create animal (\\S*) (\\S*)$").matcher(command);
//        Matcher createZoolMatcher = Pattern.compile("^create zoo (\\S*)$").matcher(command);
//        Matcher addAnimalToZooMatcher = Pattern.compile("^add animal (\\S*) zoo (\\S*)$").matcher(command);
//        Matcher transferMatcher = Pattern.compile("^transfer (\\S*) (\\S*) (\\S*)$").matcher(command);
//        Matcher showCollectionsMatcher = Pattern.compile("^show collection (\\S*)$").matcher(command);
//
//        if (createAnimalMatcher.find()) {
//            String animalName = createAnimalMatcher.group(1);
//            String type = createAnimalMatcher.group(2);
////            System.out.println(animalName + type);
//            world.createAnimal(animalName, type);
//
//        } else if (createZoolMatcher.find()) {
//            String zooName = createZoolMatcher.group(1);
////            System.out.println(zooName);
//            world.buildZoo(zooName);
//
//        } else if (addAnimalToZooMatcher.find()) {
//            String animalName = addAnimalToZooMatcher.group(1);
//            String zooName = addAnimalToZooMatcher.group(2);
////            System.out.println(animalName + zooName);
//            world.addAnimalToZoo(animalName, zooName);
//
//        } else if (transferMatcher.find()) {
//            String animalName = transferMatcher.group(1);
//            String from = transferMatcher.group(2);
//            String to = transferMatcher.group(3);
////            System.out.println(animalName + from + to);
//            world.transferAnimal(from, to, animalName);
//        } else if (showCollectionsMatcher.find()) {
//            String zooName = showCollectionsMatcher.group(1);
////            System.out.println(zooName);
//            world.findAllAnimalsByZooName(zooName);
//        } else {
//            System.out.println("input is invalid.");
//        }
//
//        System.out.println();
//    }
//
//    private static void printCommands() {
//        System.out.println("Commands:");
//        System.out.println("    1. create animal <name> <type>");
//        System.out.println("    2. create zoo <name>");
//        System.out.println("    3. add animal <animalName> zoo <zooName>");
//        System.out.println("    4. transfer <animalName> <fromZooName> <toZooName>");
//        System.out.println("    5. show collection <zooName> \n");
//        System.out.println("Press control+c to exit.\n");
//    }
//
//
//    private static void printNotice() {
//        System.out.println("You should run the program with args");
//        System.out.println("Command:");
//        System.out.println("    1. java -cp <jarfile> <main class> readFile <filename>");
//        System.out.println("    2. java -cp <jarfile> <main class> command\n");
//    }
//}
