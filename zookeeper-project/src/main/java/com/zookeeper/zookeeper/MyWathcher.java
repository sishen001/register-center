package com.zookeeper.zookeeper;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;

import java.io.IOException;

public class MyWathcher implements  Watcher {

    private String ceterStr = "192.168.1.12,192.168.1.13,192.168.1.14";
    private ZooKeeper zookeeper;

    public MyWathcher(){
        try{
            zookeeper = new ZooKeeper(ceterStr, 5000,this);
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    public String createPersistent(String path,String data){
        try {
            return  zookeeper.create(path, data.getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
        } catch (KeeperException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return  null;
    }


    public String createEphemeral(String path,String data){
        try {
            return  zookeeper.create(path, data.getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL);
        } catch (KeeperException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return  null;
    }



    /***
     * 更新信息
     * @param path
     * @return
     *
     * @throws KeeperException
     * @throws InterruptedException
     */
    public String getData(String path,boolean watch) throws KeeperException, InterruptedException {
        byte data[] = zookeeper.getData(path,watch,null);
        data = (data == null)? "null".getBytes() : data;
        return new String(data);
    }


    /***
     * 更新信息
     * @param path
     * @param data
     * @return
     * @throws KeeperException
     * @throws InterruptedException
     */
    public Stat setData(String path, String data) throws KeeperException, InterruptedException {
        return zookeeper.setData(path, data.getBytes(), -1);
    }

    /***
     * 是否存在
     * @param path
     * @return
     * @throws KeeperException
     * @throws InterruptedException
     */
    public Stat exists(String path,boolean watcher) throws KeeperException, InterruptedException {
        return zookeeper.exists(path,watcher);

    }


    /***
     * 删除
     * @param path
     * @return
     * @throws KeeperException
     * @throws InterruptedException
     */
    public void delete(String path) throws KeeperException, InterruptedException {
        zookeeper.delete(path,-1);
    }
    /***
     * 删除 递归
     * @param path
     * @return
     * @throws KeeperException
     * @throws InterruptedException
     */
    public void deleteRecursive(String path) throws KeeperException, InterruptedException {
        ZKUtil.deleteRecursive(zookeeper, path);
    }

    public void close() throws InterruptedException {
        zookeeper.close();
    }

    @Override
    public void process(WatchedEvent event) {
        // 连接状态
        Event.KeeperState keeperState = event.getState();
        // 事件类型
        Event.EventType eventType = event.getType();
        // 受影响的path
        String path = event.getPath();
        //step 1:
        System.out.println("连接状态:"+keeperState+",事件类型："+eventType+",受影响的path:"+path);

        //step:2
        try {
            if(null!=this.exists("/pengfeiTest",false)) {
                System.out.println("内容:"+ this.getData("/pengfeiTest", true));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("--------------------");
    }
}
