/**
 * Mule MongoDB Cloud Connector
 *
 * Copyright (c) MuleSoft, Inc.  All rights reserved.  http://www.mulesoft.com
 *
 * The software in this package is published under the terms of the CPAL v1.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE.txt file.
 */

/**
 * This file was automatically generated by the Mule Cloud Connector Development Kit
 */
package org.mule.module.mongo;
import static org.mockito.Mockito.*;
import static org.junit.Assert.*;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.mule.module.mongo.api.IndexOrder;
import org.mule.module.mongo.api.MongoClient;
import org.mule.module.mongo.api.MongoClientImpl;
import org.mule.module.mongo.api.WriteConcern;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.Mongo;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

public class MongoTestCase
{
    private static final String A_COLLECTION = "myCollection";
    private MongoClient client;
    private DBCollection collectionMock;
    private DB dbMock;
    
    @Before
    public void setup()
    {
        //TODO remember that Mongo objects should be cached
        dbMock = mock(DB.class);
        client = new MongoClientImpl(dbMock);
        collectionMock = mock(DBCollection.class);
        when(dbMock.getCollection(A_COLLECTION)).thenReturn(collectionMock);
    }
    
    /**Test {@link MongoClient#listCollections()}*/
    @Test
    public void listCollections()
    {
        client.listCollections();
        verify(dbMock).getCollectionNames();
    }
    
    /**Test {@link MongoClient#existsCollection(String)}*/
    @Test
    public void existsCollection()
    {
        client.existsCollection(A_COLLECTION);
        verify(dbMock).collectionExists(A_COLLECTION);
    }
    
    /**Test {@link MongoClient#dropCollection(String)}*/
    @Test
    public void dropCollection()
    {
        client.dropCollection(A_COLLECTION);
        verify(collectionMock).drop();
    }
    
    /**Test {@link MongoClient#insertObject(String, com.mongodb.DBObject, org.mule.module.mongo.api.WriteConcern)}*/
    @Test
    public void insertObject() throws Exception
    {
        BasicDBObject dbObject = new BasicDBObject();
        client.insertObject(A_COLLECTION, dbObject, WriteConcern.NONE);
        verify(collectionMock).insert(dbObject, com.mongodb.WriteConcern.NONE);
    }
    
    @Test
    public void removeObjects() throws Exception
    {
        client.removeObjects(A_COLLECTION, null);
        verify(collectionMock).remove(refEq(new BasicDBObject()));
    }

    /**Test {@link MongoClient#countObjects(String, com.mongodb.DBObject)}*/
    @Test
    public void countObjectsWithQuery() throws Exception
    {
        BasicDBObject o = new BasicDBObject();
        client.countObjects(A_COLLECTION, o);
        verify(collectionMock).count(o);
    }
    
    /**Test {@link MongoClient#countObjects(String, com.mongodb.DBObject)}*/
    @Test
    public void countObjects() throws Exception
    {
        client.countObjects(A_COLLECTION, null);
        verify(collectionMock).count();
    }

    /**Test {@link MongoClient#updateObject(String, com.mongodb.DBObject, com.mongodb.DBObject, boolean, org.mule.module.mongo.api.WriteConcern)}*/
    @Test
    public void updateObject() throws Exception
    {
        DBObject query = new BasicDBObject();
        DBObject dbObject = new BasicDBObject();
        client.updateObject(A_COLLECTION, query , dbObject, false, WriteConcern.SAFE);
        verify(collectionMock).update(query, dbObject, false, false /*TODO*/, com.mongodb.WriteConcern.SAFE);
    }

    /**Test {@link MongoClient#createIndex(String, com.mongodb.DBObject)}*/
    @Test
    public void createIndex() throws Exception
    {
        client.createIndex(A_COLLECTION, "i", IndexOrder.ASC);
        verify(collectionMock).createIndex(refEq(new BasicDBObject("i", 1)));
    }
    
    /**Tests {@link MongoClient#dropIndex(String, String)}*/
    @Test
    public void dropIndex() throws Exception
    {
        client.dropIndex(A_COLLECTION, "anIndex");
        verify(collectionMock).dropIndex(eq("anIndex"));
    }
    
    /**Tests {@link MongoClient#listIndices(String)}*/
    @Test
    public void listIndices() throws Exception
    {
        client.listIndices(A_COLLECTION);
        verify(collectionMock).getIndexInfo();
    }
    
    @Test
    public void findObjectsWithQueryAndFields() throws Exception
    {
        BasicDBObject field = new BasicDBObject();
        BasicDBObject query = new BasicDBObject();
        client.findObjects(A_COLLECTION, query, field);
        verify(collectionMock).find(query, field);
    }
    
    @Test
    public void findObjectsWithQueryWithoutFields() throws Exception
    {
        BasicDBObject query = new BasicDBObject();
        client.findObjects(A_COLLECTION, query, null);
        verify(collectionMock).find(query, null);
    }
    
    @Test
    public void findObjectsWithoutWithoutFields() throws Exception
    {
        client.findObjects(A_COLLECTION, null, null);
        verify(collectionMock).find(new BasicDBObject(), null);
    }
}
