import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class Main{

    static List<Weapon> weaponList = new ArrayList<>();

    public static void main(String[] args) {


        weaponList.add(new Weapon("Spreizbeidl", CombatType.RANGED, DamageType.PIERCING, 100, 1, 10,9800));
        weaponList.add(new Weapon("Brunnentaler Andreas", CombatType.MELEE, DamageType.NONE, 200, 1, 20, 4500));
        weaponList.add(new Weapon("Kopfinger Messerstecher", CombatType.MELEE, DamageType.SLASHING, 150, 2, 5, 3700));

        weaponList.sort(new Comparator<Weapon>() {
            @Override
            public int compare(Weapon o1, Weapon o2) {
                return o1.getDamage() - o2.getDamage();
            }
        });

        System.out.println("Without Lambda:");
        weaponList.forEach(System.out::println);

        System.out.println("\nWith Lambda:");
        weaponList.sort((o1, o2) -> o2.getDamage() - o1.getDamage());
        weaponList.forEach(System.out::println);

        weaponList.clear();
        System.out.println("\n-------------------\nWeaponlist Cleared\n-------------------\nSorted Weaponlist:\n");

        loadCsv("weapons.csv");
        List<Weapon> sortedWeaopnList;

        sortedWeaopnList = weaponList.stream().sorted((o1, o2) -> o2.getDamage() - o1.getDamage()).toList();

        sortedWeaopnList.forEach(System.out::println);

        System.out.println("\n-------------------\nWeaponlist Cleared\n-------------------\n");

        List<Weapon> mulipleSortedList;

        mulipleSortedList = weaponList.stream().sorted((o1, o2) -> {
            if (o1.getCombatType().equals(o2.getCombatType())){
                if (o1.getDamageType().equals(o2.getDamageType())){
                    return o1.getName().compareTo(o2.getName());
                }
                return o1.getDamageType().compareTo(o2.getDamageType());
            }
            return o1.getCombatType().compareTo(o2.getCombatType());
        }).toList();

        mulipleSortedList.forEach(System.out::println);


    }

    public static void loadCsv(String csv){

        try {
            weaponList = Files.lines(new File(csv).toPath())
                    .skip(1)
                    .map(s -> s.split(";"))
                    .map(s -> new Weapon(
                        s[0],
                        CombatType.valueOf(s[1]),
                        DamageType.valueOf(s[2]),
                        Integer.parseInt(s[3]),
                        Integer.parseInt(s[4]),
                        Integer.parseInt(s[5]),
                        Integer.parseInt(s[6])

            )).toList();

        } catch (IOException e) {
            System.out.println("Error on teh csv");
        }
    }

}
