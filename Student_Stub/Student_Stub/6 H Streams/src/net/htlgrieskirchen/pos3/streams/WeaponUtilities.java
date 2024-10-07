package net.htlgrieskirchen.pos3.streams;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class WeaponUtilities {

    public double average(int[] numbers) {
        double avg = 0.0;
        for (int number : numbers){
            avg += number;
        }
        return avg/numbers.length;
    }
    
    public List<String> upperCase(String[] strings) {
        return Arrays.stream(strings).map(String::toUpperCase).collect(Collectors.toList());
    }
    
    public Weapon findWeaponWithLowestDamage(List<Weapon> weapons) {
        return weapons.stream()
                .min(Comparator.comparingInt(Weapon::getDamage)).orElse(null);
    }
    
    public Weapon findWeaponWithHighestStrength(List<Weapon> weapons) {
        return weapons.stream()
                .max(Comparator.comparingInt(Weapon::getStrength))
                .orElse(null);
    }
    
    public List<Weapon> collectMissileWeapons(List<Weapon> weapons) {
        return weapons.stream()
                .filter(weapon -> weapon.getDamageType() == DamageType.MISSILE)
                .collect(Collectors.toList());
    }
    
    public Weapon findWeaponWithLongestName(List<Weapon> weapons) {
        return weapons.stream()
                .max(Comparator.comparingInt(weapon -> weapon.getName().length()))
                .orElse(null);
    }
    
    public List<String> toNameList(List<Weapon> weapons) {
        return weapons.stream()
                .map(Weapon::getName)
                .collect(Collectors.toList());
    }
    
    public int[] toSpeedArray(List<Weapon> weapons) {
        return weapons.stream()
                .mapToInt(Weapon::getSpeed)
                .toArray();
    }
    
    public int sumUpValues(List<Weapon> weapons) {
        return weapons.stream()
                .mapToInt(Weapon::getValue)
                .sum();
    }
    
    public long sumUpHashCodes(List<Weapon> weapons) {
        return weapons.stream()
                .mapToInt(Weapon::hashCode)
                .sum();
    }
    
    public List<Weapon> removeDuplicates(List<Weapon> weapons) {
        return weapons.stream()
                .distinct() //Duplikate weg
                .collect(Collectors.toList());
    }
    
    public void increaseValuesByTenPercent(List<Weapon> weapons) {
        weapons.forEach(weapon -> weapon.setValue((int) (weapon.getValue() * 1.1)));
    }
}
