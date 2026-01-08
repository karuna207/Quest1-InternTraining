import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class PokemonBattle {
    public static void main(String[] args) throws Exception {
        Breeder breeder1=new Breeder("Karna");
        Breeder breeder2=new Breeder("James");
        breeder1.addPokemon(new Pokemon("Fire",10));
        breeder1.addPokemon(new Pokemon("Water",20));
        breeder1.addPokemon(new Pokemon("Fighting",6));
        breeder1.addPokemon(new Pokemon("Psychic",10));
        breeder1.addPokemon(new Pokemon("Electric",12));


        breeder2.addPokemon(new Pokemon("Water",10));
        breeder2.addPokemon(new Pokemon("Fighting",10));
        breeder2.addPokemon(new Pokemon("Psychic",10));
        breeder2.addPokemon(new Pokemon("Fire",12));
        breeder2.addPokemon(new Pokemon("Grass",2));

        if(!(Validation.validateCountOfPokemons(breeder1.getPokemons()) && Validation.validateUniquePokemons(breeder1.getPokemons()))){
            throw new Exception("Number of Pokemons either doesnt match 5 or Replicate powers used");
        }


        FigureIdealCombination(breeder1.getPokemons(),breeder2.getPokemons());
}

    private static void FigureIdealCombination(List<Pokemon> breeder1, List<Pokemon> breeder2) {
        if(permute(breeder1, 0,breeder2)){
            System.out.println("Winnning combination found");
        }else{
            System.out.println("There is no chance of winning");
        };
    }

    private static boolean  permute(List<Pokemon> list, int index,List<Pokemon> breeder2) {
        if (index == list.size()) {
            return false;
        }

        for (int i = index; i < list.size(); i++) {
            Collections.swap(list, index, i);
            int winCount=0;
            for(int j=0;j<list.size();j++){
                if(comparePokemon(list.get(j),breeder2.get(j))){
                    winCount++;
                }
                if(winCount==3){
                    for(Pokemon p:list){
                        System.out.print(p.getPower().getPowerName()+" ");
                    }
                    return true;
                }
            }
            if(permute(list, index + 1, breeder2)){
                return true;
            };
            Collections.swap(list, index, i);
        }
        return false;

    }


    private static boolean comparePokemon(Pokemon p1, Pokemon p2) {
        String power1 = p1.getPower().getPowerName();
        String power2 = p2.getPower().getPowerName();
        int level1 = p1.getLevel();
        int level2 = p2.getLevel();
        boolean p1Adv = PowerKnowledge.getAdvantageForPower(power1).contains(power2);
        boolean p2Adv = PowerKnowledge.getAdvantageForPower(power2).contains(power1);

        if (p1Adv) {
            if (level2 >= 2 * level1) {
                return false;
            }
            return true;
        }

        if (p2Adv) {
            if (level1 > 2 * level2) {
                return true;
            }
            return false;
        }

        return level1>level2;
    }



}
