import java.util.List;

public class PowerKnowledge {

    public static List<String> getAdvantageForPower(String name){
        if(name.equals("Fire")){
            return List.of("Grass","Ghost");
        }else if(name.equals("Water")){
            return List.of("Fire");
        }else if(name.equals("Grass")){
            return List.of("Electric","Fighting");
        }else if(name.equals("Electric")){
            return List.of("Water");
        }else if (name.equals("Psychic")) {
            return List.of("Ghost");
        } else if (name.equals("Ghost")) {
            return List.of("Fighting", "Fire", "Electric");
        } else if (name.equals("Fighting")) {
            return List.of("Electric");
        }
        return List.of();  //PowerNotFoundException create (todo)

    }

}


