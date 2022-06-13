package es.ieslavereda.taserfan.api;

public class API {
    public static class Routes {
        // Oracle
        public static final String AUTHENTICATE = "/authenticate";
        public static String URL = "http://10.13.0.129:4567";
        public static void changeURL(String IP, String Puerto){
            //URL = "http://"+IP+":"+Puerto;
        }
    }
}
