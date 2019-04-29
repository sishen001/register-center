package com.zookeeper;

import com.zookeeper.zookeeper.MyZookeeper;
import org.junit.Test;

public class MyZookeeperTest {

    @Test
    public void nodeTest() throws Exception{
        MyZookeeper zookeeper = new MyZookeeper();
        if(null == zookeeper.exists("/testPersistent")){
            zookeeper.createPersistent("/testPersistent","2019");
        }
        System.out.println(zookeeper.getData("/testPersistent"));
        zookeeper.createEphemeral("/testEh","2018");
        System.out.println(zookeeper.getData("/testEh"));
    }
}
