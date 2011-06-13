Mule Mongo Cloud Connector
==========================

Mule Cloud connector to mongo

Installation
------------

The connector can either be installed for all applications running within the Mule instance or can be setup to be used
for a single application.

*All Applications*

Download the connector from the link above and place the resulting jar file in
/lib/user directory of the Mule installation folder.

*Single Application*

To make the connector available only to single application then place it in the
lib directory of the application otherwise if using Maven to compile and deploy
your application the following can be done:

Add the connector's maven repo to your pom.xml:

    <repositories>
        <repository>
            <id>muleforge-releases</id>
            <name>MuleForge Snapshot Repository</name>
            <url>https://repository.muleforge.org/release/</url>
            <layout>default</layout>
        </repsitory>
    </repositories>

Add the connector as a dependency to your project. This can be done by adding
the following under the dependencies element in the pom.xml file of the
application:

    <dependency>
        <groupId>org.mule.modules</groupId>
        <artifactId>mule-module-mongo</artifactId>
        <version>1.0-SNAPSHOT</version>
    </dependency>

Configuration
-------------

You can configure the connector as follows:

    <mongo:config client="value" database="value" host="value" port="value"/>

Here is detailed list of all the configuration attributes:

| attribute | description | optional | default value |
|:-----------|:-----------|:---------|:--------------|
|name|Give a name to this configuration so it can be later referenced by config-ref.|yes||
|client||yes|
|database||yes|test
|host||yes|localhost
|port||yes|27017


List Collections
----------------

Lists names of collections available at this database



     <list-collections/>

| attribute | description | optional | default value | possible values |
|:-----------|:-----------|:---------|:--------------|:----------------|
|config-ref|Specify which configuration to use for this invocation|yes||

Returns list of names of collections available at this database



Exists Collection
-----------------

Answers if a collection exists given its name



     <exists-collection name="aColllection"/>

| attribute | description | optional | default value | possible values |
|:-----------|:-----------|:---------|:--------------|:----------------|
|config-ref|Specify which configuration to use for this invocation|yes||
|collection|the name of the collection|no||

Returns the collection exists



Drop Collection
---------------

Deletes a collection and all the objects it contains.  
If the collection does not exist, does nothing.



     <drop-collection name="aCollection"/>

| attribute | description | optional | default value | possible values |
|:-----------|:-----------|:---------|:--------------|:----------------|
|config-ref|Specify which configuration to use for this invocation|yes||
|collection|the name of the collection to drop|no||



Create Collection
-----------------

Creates a new collection. 
If the collection already exists, a MongoException will be thrown.



     <create-collection name="aCollection" capped="true"/>

| attribute | description | optional | default value | possible values |
|:-----------|:-----------|:---------|:--------------|:----------------|
|config-ref|Specify which configuration to use for this invocation|yes||
|collection|the name of the collection to create|no||
|capped|if the collection will be capped TODO document its meaning|yes|false|
|maxObjects||yes||
|size|the maximum size of the new collection TODO maximum?|yes||



Insert Object
-------------

Inserts an object in a collection, setting its id if necessary.



     <insert-object collection="Employees" object="#[header:aBsonEmployee]" writeConcern="SAFE"/>

| attribute | description | optional | default value | possible values |
|:-----------|:-----------|:---------|:--------------|:----------------|
|config-ref|Specify which configuration to use for this invocation|yes||
|collection|the name of the collection where to insert the given object|no||
|object|the object to insert|no||
|writeConcern|the optional write concern of insertion|yes|NORMAL|*NONE*, *NORMAL*, *SAFE*, *FSYNC_SAFE*, *REPLICAS_SAFE*, *mongoWriteConcern*



Update Object
-------------

Updates the first object that matches the given query



     <update-object collection="#[map-payload:aCollectionName]" 
            query="#[variable:aBsonQuery]" object="#[variable:aBsonObject]" upsert="true"/>

| attribute | description | optional | default value | possible values |
|:-----------|:-----------|:---------|:--------------|:----------------|
|config-ref|Specify which configuration to use for this invocation|yes||
|collection|the name of the collection to update|no||
|query|the query object used to detect the element to update|no||
|object|the object that will replace that one which matches the query|no||
|upsert|TODO|yes|false|
|writeConcern||yes|NORMAL|*NONE*, *NORMAL*, *SAFE*, *FSYNC_SAFE*, *REPLICAS_SAFE*, *mongoWriteConcern*



Save Object
-----------

Inserts or updates an object based on its object _id.
 


     <save-object 
             collection="#[map-payload:aCollectionName]"
             object="#[header:aBsonObject]"/>

| attribute | description | optional | default value | possible values |
|:-----------|:-----------|:---------|:--------------|:----------------|
|config-ref|Specify which configuration to use for this invocation|yes||
|collection||no||
|object||no||
|writeConcern||yes|NORMAL|*NONE*, *NORMAL*, *SAFE*, *FSYNC_SAFE*, *REPLICAS_SAFE*, *mongoWriteConcern*



Remove Objects
--------------

Removes all the objects that match the a given optional query. 
If query is not specified, all objects are removed. However, please notice that this is normally
less performant that dropping the collection and creating it and its indices again




     <remove-objects collection="#[map-payload:aCollectionName]" query="#[map-payload:aBsonQuery]"/>

| attribute | description | optional | default value | possible values |
|:-----------|:-----------|:---------|:--------------|:----------------|
|config-ref|Specify which configuration to use for this invocation|yes||
|collection|the collection whose elements will be removed|no||
|query|the query object. Objects that match it will be removed|yes||



Map Reduce Objects
------------------

Maps and folds objects in a collection by applying a mapping function and then a folding function 




      <map-reduce-objects 
         collection="myCollection"
         mapFunction="#[header:aJSMapFunction]"
         reduceFunction="#[header:aJSFoldFunction]"/>

| attribute | description | optional | default value | possible values |
|:-----------|:-----------|:---------|:--------------|:----------------|
|config-ref|Specify which configuration to use for this invocation|yes||
|collection|the name of the collection to map and reduce|no||
|mapFunction|a JavaScript encoded mapping function|no||
|reduceFunction|a JavaScript encoded folding function|no||



Count Objects
-------------

Counts the number of objects that match the given query. If no query
is passed, returns the number of elements in the collection



     <count-objects 
         collection="#[variable:aCollectionName]"
         query="#[variable:aBsonQuery]"/>

| attribute | description | optional | default value | possible values |
|:-----------|:-----------|:---------|:--------------|:----------------|
|config-ref|Specify which configuration to use for this invocation|yes||
|collection||no||
|query||yes||



Find Objects
------------

Finds all objects that match a given query. If no query is specified, all objects of the 
collection are retrieved. If no fields object is specified, all fields are retrieved. 



     <find-objects query="#[map-payload:aBsonQuery]" fields="#[header:aBsonFieldsSet]"/>

| attribute | description | optional | default value | possible values |
|:-----------|:-----------|:---------|:--------------|:----------------|
|config-ref|Specify which configuration to use for this invocation|yes||
|collection||no||
|query|the query object. If unspecified, all documents are returned|yes||
|fields|the fields to return. If unspecified, all fields are returned|yes||



Find One Object
---------------

Finds the first object that matches a given query. TODO if not exists?



     <find-one-object 
         query="#[variable:aBsonQuery]" 
         fields="#[map-payload:aBsonFieldsSet]"/>

| attribute | description | optional | default value | possible values |
|:-----------|:-----------|:---------|:--------------|:----------------|
|config-ref|Specify which configuration to use for this invocation|yes||
|collection||no||
|query||no||
|fields||no||



Create Index
------------

Creates a new index



     <create-index collection="myCollection" keys="#[header:aBsonFieldsSet]"/>

| attribute | description | optional | default value | possible values |
|:-----------|:-----------|:---------|:--------------|:----------------|
|config-ref|Specify which configuration to use for this invocation|yes||
|collection||no||
|field|the name of the field which will be indexed|no||
|order|the indexing order|yes|ASC|*ASC*, *DESC*



Drop Index
----------

Drops an existing index



     <drop-index collection="myCollection" name="#[map-payload:anIndexName]"/>

| attribute | description | optional | default value | possible values |
|:-----------|:-----------|:---------|:--------------|:----------------|
|config-ref|Specify which configuration to use for this invocation|yes||
|collection||no||
|index|the name of the index to drop|no||



List Indices
------------

List existent indices in a collection



     <drop-index collection="myCollection" name="#[map-payload:anIndexName]"/>

| attribute | description | optional | default value | possible values |
|:-----------|:-----------|:---------|:--------------|:----------------|
|config-ref|Specify which configuration to use for this invocation|yes||
|collection||no||













































