package com.zookeeper;

import com.zookeeper.zookeeper.MyWathcher;
import com.zookeeper.zookeeper.MyZookeeper;
import org.junit.Test;

public class MyWathcherTest {

    @Test
    public void wathcherTest() throws Exception{
        MyWathcher wathcher = new MyWathcher();
        if(null == wathcher.exists("/pengfeiTest",true)){
            wathcher.createEphemeral("/pengfeiTest","3069");
        }

        System.out.println(wathcher.getData("/pengfeiTest",true));
        Thread.sleep(100000);
    }
}
