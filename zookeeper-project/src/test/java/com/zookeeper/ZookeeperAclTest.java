package com.zookeeper;

import com.zookeeper.zookeeper.MyZookeeper;
import com.zookeeper.zookeeper.ZookeeperAcl;
import org.junit.Test;

public class ZookeeperAclTest {

    @Test
    public void aclTest() throws Exception{
        ZookeeperAcl acl = new ZookeeperAcl();
        acl.createEphemeral("/abcd","bbbb");
        System.out.println(acl.getData("/abcd"));
    }
}
