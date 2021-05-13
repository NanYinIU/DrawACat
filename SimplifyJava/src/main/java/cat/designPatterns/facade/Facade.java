package cat.designPatterns.facade;

import java.util.ArrayList;
import java.util.List;

public class Facade {
    List<Services> services;

    public Facade() {
        services = new ArrayList<>();
        registerService();
    }

    private void registerService(){
        services.add(new MysqlService());
        services.add(new ApacheService());
    }

    public void startService(){
        for (Services services : this.services){
            services.start();
            System.out.println("service run....");
        }
    }

    public void stopService(){
        for (Services services : this.services){
            services.stop();
            System.out.println("service stop....");
        }
    }

    public void restartService(){
        for (Services services : this.services){
            services.restart();
            System.out.println("service restart....");
        }
    }
}
