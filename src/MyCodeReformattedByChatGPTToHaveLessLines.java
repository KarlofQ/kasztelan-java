import java.util.*;

public class MyCodeReformattedByChatGPTToHaveLessLines {
    static Scanner scanner = new Scanner(System.in);
    static Map<String, Integer> warriors = new HashMap<>() {{
        put("horseman", 1);
        put("infantryman", 3);
        put("archer", 2);
    }};
    static List<String> animals = Arrays.asList("bear", "lynx");
    static Random random = new Random();
    static String actualAnimal;
    static String actualWarrior;

    public static void main(String[] args) {
        System.out.println("""
                Info:
                You must let a total of 1 horsemen, 2 archers, and 3 infantrymen pass through
                Archer can defeat bear and lynx
                Infantryman can defeat lynx
                Horseman can defeat bear

                Commands:
                go
                back
                """);

        while (warriors.values().stream().anyMatch(i -> i > 0)) {
            actualAnimal = animals.get(random.nextInt(animals.size()));
            actualWarrior = new ArrayList<>(warriors.keySet()).get(random.nextInt(warriors.size()));

            System.out.println("The " + actualAnimal + " is in the ravine");
            System.out.println("The " + actualWarrior + " is near to you");

            String result = handleWarrior(actualWarrior, actualAnimal);

            try {
                if (result.equals("error")) throw new Exception("Something went wrong. Please try again");
            } catch (Exception e) {
                System.out.println(e.getMessage());
                break;
            }

            if (result.equals("warriorDefeat")) {
                System.out.println("\nYou Lose");
                break;
            }
            if (warriors.values().stream().allMatch(i -> i == 0)) {
                System.out.println("\nYou Win");
                break;
            }
        }
    }

    static String handleWarrior(String warrior, String animal) {
        System.out.println("Can " + warrior + " go to ravine?");
        String userChoice = scanner.nextLine();

        if (userChoice.equals("go")) {
            if ((warrior.equals("horseman") && animal.equals("bear")) ||
                    (warrior.equals("archer") && (animal.equals("bear") || animal.equals("lynx"))) ||
                    (warrior.equals("infantryman") && animal.equals("lynx"))) {
                System.out.println(animal + " is dead, " + warrior + " is alive\n");
                warriors.put(warrior, warriors.get(warrior) - 1);
                return "good";
            } else {
                System.out.println("That was stupid, " + warrior + " is dead\n");
                return "warriorDefeat";
            }
        } else if (userChoice.equals("back")) {
            System.out.println("You are coward\n");
            return "good";
        }
        return "error";
    }
}