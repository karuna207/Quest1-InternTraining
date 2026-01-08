import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Breeder {
    String name;
    List<Pokemon> pokemons=new ArrayList<>();

    public Breeder(String breederName){
        name=breederName;
    }

    public void addPokemon(Pokemon pokemon){
        pokemons.add(pokemon);
    }

    public List<Pokemon> getPokemons(){
        return pokemons;
    }

}
