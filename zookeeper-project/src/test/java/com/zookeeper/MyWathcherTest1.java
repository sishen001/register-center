package com.zookeeper;

import com.zookeeper.zookeeper.MyWathcher;
import org.junit.Test;

public class MyWathcherTest1 {

    @Test
    public void wathcherTest() throws Exception{
        MyWathcher wathcher = new MyWathcher();
        if(null == wathcher.exists("/pengfeiTest",false)){
            wathcher.createEphemeral("/pengfeiTest","3069");
        }

        System.out.println(wathcher.getData("/pengfeiTest",false));
        Thread.sleep(100000);
    }
}
