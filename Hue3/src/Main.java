import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Main {

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

        Printable tablePrinter = (data) -> {

            if (data.isEmpty()) {
                System.out.println("Die Tabelle ist leer.");
                return;
            }


            List<Integer> columnWidths = Arrays.asList(0, 0, 0, 0, 0, 0, 0); // 7 Spalten

            // Max Breite von Spalten
            for (Weapon row : data) {
                columnWidths.set(0, Math.max(columnWidths.get(0), row.getName().length()));
                columnWidths.set(1, Math.max(columnWidths.get(1), row.getCombatType().toString().length()));
                columnWidths.set(2, Math.max(columnWidths.get(2), row.getDamageType().toString().length()));
                columnWidths.set(3, Math.max(columnWidths.get(3), String.valueOf(row.getDamage()).length()));
                columnWidths.set(4, Math.max(columnWidths.get(4), String.valueOf(row.getSpeed()).length()));
                columnWidths.set(5, Math.max(columnWidths.get(5), String.valueOf(row.getStrength()).length()));
                columnWidths.set(6, Math.max(columnWidths.get(6), String.valueOf(row.getValue()).length()));
            }

            String separator = "+";
            for (int width : columnWidths) {
                separator += "-".repeat(width + 2) + "+";
            }

            // Erste Zeile ausgeben
            System.out.println(separator);
            System.out.println("| " + String.format("%-" + columnWidths.get(0) + "s", "Name") + " | "
                    + String.format("%-" + columnWidths.get(1) + "s", "CombatType") + " | "
                    + String.format("%-" + columnWidths.get(2) + "s", "DamageType") + " | "
                    + String.format("%-" + columnWidths.get(3) + "s", "Damage") + " | "
                    + String.format("%-" + columnWidths.get(4) + "s", "Speed") + " | "
                    + String.format("%-" + columnWidths.get(5) + "s", "Strength") + " | "
                    + String.format("%-" + columnWidths.get(6) + "s", "Value") + " |");
            System.out.println(separator);

            // Jede Zeile ausgeben
            for (Weapon row : data) {
                System.out.println("| " + String.format("%-" + columnWidths.get(0) + "s", row.getName()) + " | "
                        + String.format("%-" + columnWidths.get(1) + "s", row.getCombatType()) + " | "
                        + String.format("%-" + columnWidths.get(2) + "s", row.getDamageType()) + " | "
                        + String.format("%-" + columnWidths.get(3) + "d", row.getDamage()) + " | "
                        + String.format("%-" + columnWidths.get(4) + "d", row.getSpeed()) + " | "
                        + String.format("%-" + columnWidths.get(5) + "d", row.getStrength()) + " | "
                        + String.format("%-" + columnWidths.get(6) + "d", row.getValue()) + " |");
                System.out.println(separator);
            }
        };

        String filePath = "weapons.csv";

        try {

            List<Weapon> weapons = Files.lines(Paths.get(filePath)).skip(1)
                    .map(line -> {

                        String[] parts = line.split(";");
                        String name = parts[0];
                        DamageType damageType = DamageType.valueOf(parts[2]);
                        CombatType combatType = CombatType.valueOf(parts[1]);
                        int damage = Integer.parseInt(parts[3]);
                        int speed = Integer.parseInt(parts[4]);
                        int strength = Integer.parseInt(parts[5]);
                        int value = Integer.parseInt(parts[6]);


                        return new Weapon(name, combatType, damageType, damage, speed, strength, value);
                    })
                    .collect(Collectors.toList());


            weapons.forEach(System.out::println);


            tablePrinter.print(weapons);

        } catch (IOException e) {
            e.printStackTrace();
        }
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
            System.out.println("Error on the csv");
        }
    }
}