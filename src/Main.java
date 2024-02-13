import java.util.*;

public class Main {
    static Scanner scanner = new Scanner(System.in);
    static ArrayList<String> warriors = new ArrayList<>(Arrays.asList("horseman", "infantryman", "archer"));
    static ArrayList<String> animals = new ArrayList<>(Arrays.asList("bear", "lynx"));
    static int horsemanCounter = 0, infantrymanCounter = 0, archerCounter = 0;
    static Random random = new Random();
    static String actualAnimal;
    static String actualWarrior;

    public static void main(String[] args) {
        initializeGame();

        while (!(horsemanCounter == 1 && archerCounter == 2 && infantrymanCounter == 3)) {
            if (horsemanCounter == 1) warriors.removeIf(s -> s.equals("horseman"));
            if (archerCounter == 2) warriors.removeIf(s -> s.equals("archer"));
            if (infantrymanCounter == 3) warriors.removeIf(s -> s.equals("infantryman"));

            getRandomAnimal();
            getRandomWarrior();

            String result = handleWarrior(actualWarrior, actualAnimal);

            try {
                if (result.equals("error")) throw new Exception("Something went wrong. Please try again");
            } catch (Exception e) {
                System.out.println(e.getMessage());
                break;
            }


            if (result.equals("warriorDefeat")) {
                System.out.println();
                System.out.println("You Lose");
                break;

            }
            if (horsemanCounter == 1 && archerCounter == 2 && infantrymanCounter == 3) {
                System.out.println();
                System.out.println("You Win");
                break;
            }

        }
    }

    static String handleWarrior(String warrior, String animal) {
        String userChoice;
        switch (warrior) {
            case "horseman" -> {
                switch (animal) {
                    case "bear":
                        System.out.println("Can horseman go to ravine?");
                        userChoice = scanner.nextLine();
                        if (userChoice.equals("go")) {
                            System.out.println("Bear is dead, horseman is alive");
                            System.out.println();
                            horsemanCounter++;
                            return "good";
                        } else if (userChoice.equals("back")) {
                            System.out.println("You are coward");
                            System.out.println();
                            return "good";
                        }

                    case "lynx":
                        System.out.println("Can horseman go to ravine?");
                        userChoice = scanner.nextLine();
                        if (userChoice.equals("go")) {
                            System.out.println("That was stupid, horseman is dead");
                            System.out.println();
                            return "warriorDefeat";
                        } else if (userChoice.equals("back")) {
                            System.out.println("That was good move");
                            System.out.println();
                            return "good";
                        }
                }
            }
            case "archer" -> {
                switch (animal) {
                    case "bear":
                        System.out.println("Can archer go to ravine?");
                        userChoice = scanner.nextLine();
                        if (userChoice.equals("go")) {
                            System.out.println("Bear is dead, archer is alive");
                            System.out.println();
                            archerCounter++;
                            return "good";
                        } else if (userChoice.equals("back")) {
                            System.out.println("You are coward");
                            System.out.println();
                            return "good";
                        }

                    case "lynx":
                        System.out.println("Can archer go to ravine?");
                        userChoice = scanner.nextLine();
                        if (userChoice.equals("go")) {
                            System.out.println("Lynx is dead, archer is alive");
                            System.out.println();
                            archerCounter++;
                            return "good";
                        } else if (userChoice.equals("back")) {
                            System.out.println("You are coward");
                            System.out.println();
                            return "good";
                        }

                }
            }
            case "infantryman" -> {
                switch (animal) {
                    case "bear":
                        System.out.println("Can infantryman go to ravine?");
                        userChoice = scanner.nextLine();
                        if (userChoice.equals("go")) {
                            System.out.println("That was stupid, infantryman is dead");
                            System.out.println();
                            return "warriorDefeat";
                        } else if (userChoice.equals("back")) {
                            System.out.println("That was good move");
                            System.out.println();
                            return "good";
                        }

                    case "lynx":
                        System.out.println("Can infantryman go to ravine?");
                        userChoice = scanner.nextLine();
                        if (userChoice.equals("go")) {
                            System.out.println("Lynx is dead, infantryman is alive");
                            System.out.println();
                            infantrymanCounter++;
                            return "good";
                        } else if (userChoice.equals("back")) {
                            System.out.println("You are coward");
                            System.out.println();
                            return "good";
                        }

                }
            }
        }
        return "error";
    }

    static void initializeGame() {
        System.out.println("Info:");
        System.out.println("You must let a total of 1 horsemen, 2 archers, and 3 infantrymen pass through");
        System.out.println("Archer can defeat bear and lynx");
        System.out.println("Infantryman can defeat lynx");
        System.out.println("Horseman can defeat bear");
        System.out.println();
        System.out.println("Commands:");
        System.out.println("go");
        System.out.println("back");
        System.out.println();
    }

    static void getRandomAnimal() {
        int randomIndex = random.nextInt(animals.size());
        actualAnimal = animals.get(randomIndex);
        System.out.println("The " + actualAnimal + " is in the ravine");
    }

    static void getRandomWarrior() {
        int randomIndex = random.nextInt(warriors.size());
        actualWarrior = warriors.get(randomIndex);
        System.out.println("The " + actualWarrior + " is near to you");
    }

}