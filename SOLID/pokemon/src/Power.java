import java.util.List;

public class Power {
    String name;
    List<String> advantageOver;

    Power(String powerName,List<String> advantagePower){
        name=powerName;
        advantageOver=advantagePower;
    }

    public String getPowerName(){
        return name;
    }

    public List<String> getAdvantageOver() {
        return advantageOver;
    }
}
