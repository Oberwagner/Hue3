import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MainTest {

    @Test
    @DisplayName("Test if sorted correctly")
    public void ComparatorTest(){
        List<Weapon> weaponList = new ArrayList<>();

        weaponList.add(new Weapon("Spreizbeidl", CombatType.RANGED, DamageType.PIERCING, 100, 1, 10,9800));
        weaponList.add(new Weapon("Brunnentaler Andreas", CombatType.MELEE, DamageType.NONE, 200, 1, 20, 4500));

        weaponList.sort((o1, o2) -> o2.getDamage() - o1.getDamage());

        Weapon expected = new Weapon("Brunnentaler Andreas", CombatType.MELEE, DamageType.NONE, 200, 1, 20, 4500);

        assertEquals(expected.toString(), weaponList.get(0).toString());
    }

    @Test
    @DisplayName("Test if mulitplesorted")
    public void MulitpleSortTest(){
        List<Weapon> weaponList = Main.weaponList;
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

        assertEquals(mulipleSortedList,mulipleSortedList);
    }
}