import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Validation {
    public static boolean validateCountOfPokemons(List<Pokemon> pokemons){
        return pokemons.size()==5;
    }

    public static boolean validateUniquePokemons(List<Pokemon> pokemons) {
        Set<String> powersSeen = new HashSet<>();

        for (Pokemon p : pokemons) {
            if (!powersSeen.add(p.getPower().getPowerName())) {
                return false;
            }
        }
        return true;

    }
}
