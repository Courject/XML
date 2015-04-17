import java.util.HashMap;
import java.util.Map;

/**
 * Created by Hugh on 2015/3/2 0002.
 */
public class NodeName {
    private Map<String,String> map;

    public NodeName() {
        initMap();
    }
    private void initMap(){
        map = new HashMap<String, String>();
        map.put("site","站点");
        map.put("people","人库");
        map.put("person","人");
        map.put("name","姓名");
        map.put("emailaddress","邮箱地址");
        map.put("creditcard","信用卡");
        map.put("address","地址");
        map.put("street","街道");
        map.put("city","城市");
        map.put("country","国家");
        map.put("zipcode","邮编");
        map.put("profile","资料");
        map.put("income","收入");
    }

    public String get(String nodeName){
        return map.get(nodeName);
    }

    public static void main(String[] args){
        System.out.println(new NodeName().get("title"));
    }
}
