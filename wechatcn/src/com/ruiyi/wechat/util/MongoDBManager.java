package com.ruiyi.wechat.util;


import java.util.Iterator;
import java.util.List;

import com.mongodb.AggregationOutput;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoException;



/**
 *
 * @author Administrator
 */
public class MongoDBManager {

    private static MongoClient mg = null;
    private static DB db = null;
    private final static MongoDBManager instance = new MongoDBManager();
    //private static final String USER = "iwtxokhtd";  
    //private static final String PASSWORD = "123456";  
    //private static boolean loginSuccess=false;
    private final static String DB = "cloud";
    private final static String HOST = "192.168.2.128";
//    private final static String HOST = "192.168.2.64";
    private final static int PORT = 27017;

    /**
     * 实例化
     *
     * @return
     * @throws Exception
     */
    public static MongoDBManager getInstance() throws Exception {
        return instance;
    }

    static {
        try {
            mg = new MongoClient(HOST, PORT);
            db = mg.getDB(DB);
            //loginSuccess=db.authenticate(USER, PASSWORD.toCharArray());//用户验证  
        } catch (Exception e) {
        }
    }

    /**
     * 获取集合（表）
     *
     * @param collection
     */
    public static DBCollection getCollection(String collection) {
        return db.getCollection(collection);
    }

    /**
     * ----------------------------------分割线--------------------------------------
     */
    /**
     * 插入
     *
     * @param collection
     * @param map
     */
    public void insert(String collection, DBObject dbObject) {
        try {
            getCollection(collection).insert(dbObject);
        } catch (MongoException e) {
        }
    }

    /**
     * 批量插入
     *
     * @param collection
     * @param list
     */
    public void insertBatch(String collection, List<DBObject> list) {
        if (list == null || list.isEmpty()) {
            return;
        }
        try {
            getCollection(collection).insert(list);
        } catch (MongoException e) {
        }
    }

    /**
     * 删除
     *
     * @param collection
     * @param map
     */
    public void delete(String collection, DBObject object) {
        getCollection(collection).remove(object);
    }

    /**
     * 删除全部
     *
     * @param collection
     * @param map
     */
    public void deleteAll(String collection) {
        List<DBObject> rs = findAll(collection);
        if (rs != null && !rs.isEmpty()) {
            for (int i = 0; i < rs.size(); i++) {
                getCollection(collection).remove(rs.get(i));
            }
        }
    }

    /**
     * 批量删除
     *
     * @param collection
     * @param list
     */
    public void deleteBatch(String collection, List<DBObject> list) {
        if (list == null || list.isEmpty()) {
            return;
        }
        for (int i = 0; i < list.size(); i++) {
            getCollection(collection).remove(list.get(i));
        }
    }

    /**
     * 计算满足条件条数
     *
     * @param collection
     * @param map
     */
    public long getCount(String collection, DBObject object) {
        return getCollection(collection).getCount(object);
    }

    /**
     * 计算集合总条数
     *
     * @param collection
     * @param map
     */
    public long getCount(String collection) {
        return getCollection(collection).find().count();
    }

    /**
     * 更新
     *
     * @param collection
     * @param setFields
     * @param whereFields
     */
    public void update(String collection, DBObject setFields, DBObject whereFields) {
        getCollection(collection).updateMulti(setFields, whereFields);
    }

    /**
     * 查找对象（根据主键_id）
     *
     * @param collection
     * @param _id
     */
/*    public DBObject findById(String collection, String _id) {
        DBObject obj = new BasicDBObject();
        obj.put("_id", ObjectId.massageToObjectId(_id));
        return getCollection(collection).findOne(obj);
    }*/

    /**
     * 查找集合所有对象
     *
     * @param collection
     */
    public List<DBObject> findAll(String collection) {
        return getCollection(collection).find().toArray();
    }

    /**
     * 查找（返回一个对象）
     *
     * @param map
     * @param collection
     */
    public DBObject findOne(String collection, DBObject object) {
        DBCollection coll = getCollection(collection);
        return coll.findOne(object);
    }

    /**
     * 查找（返回一个List<DBObject>）
     *
     * @param <DBObject>
     * @param map
     * @param collection
     * @throws Exception
     */
    public List<DBObject> find(String collection, DBObject object) throws Exception {
        DBCollection coll = getCollection(collection);
        DBCursor c = coll.find(object);
        if (c != null) {
            return c.toArray();
        } else {
            return null;
        }
    }
    
    public List<DBObject> find(String collection, DBObject object,DBObject object1) throws Exception {
        DBCollection coll = getCollection(collection);
        DBCursor c = coll.find(object,object1);
        if (c != null) {
            return c.toArray();
        } else {
            return null;
        }
    }
    
    public void createIndex(String collection, DBObject object){
       DBCollection coll = getCollection(collection);
       coll.createIndex(object);
    }
    
    public static Iterator<DBObject> aggregate(String collection, DBObject object,DBObject object1) throws Exception {
        DBCollection coll = getCollection(collection);
        AggregationOutput c = coll.aggregate(object,object1);
        if (c != null) {
            return c.results().iterator();
        } else {
            return null;
        }
    }
    
}
