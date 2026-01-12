import com.sun.source.doctree.ValueTree;

import java.lang.invoke.VarHandle;
import java.util.HashMap;
import java.util.Map;

public class GlobalEnvironment {
    private Map<String, Object> mpp;
    private static volatile GlobalEnvironment instance;

    private GlobalEnvironment(){
        mpp=new HashMap<>();
    }

    public static GlobalEnvironment getInstance(){
        if(instance==null){
            synchronized (GlobalEnvironment.class) {
                if(instance==null){
                    instance = new GlobalEnvironment();
                }

            }
        }
        return instance;
    }

    public void addVariable(String variable,Object value){
        mpp.put(variable,value);
    }

    public Object getVariable(String variable){
        if(!mpp.containsKey(variable)){
            System.out.println(variable+" not found");
        }
        return mpp.get(variable);
    }


}
