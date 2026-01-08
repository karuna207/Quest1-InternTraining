import java.util.ArrayList;
import java.util.List;

public class Pokemon {
    Power power;
    int level;

    Pokemon(String name,int levelPokemon){
        List<String> advantage=PowerKnowledge.getAdvantageForPower(name);
        power= new Power(name,advantage);
        level=levelPokemon;
    };

    Power getPower(){
        return power;
    }

    int getLevel(){
        return level;
    }
}