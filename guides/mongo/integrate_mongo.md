# Integrate MongoDB

This guide shows how to integrate MongoDB into ```jstat-gui```.
The general steps are:

- Install MongoDB as described <a href="https://docs.mongodb.com/manual/installation/">here</a>
- Start the MongoDB server 
```aidl
sudo service mongod start
```  

- Check the status by

```aidl
sudo service mongod status
```

By default MongoDB uses the 27017 port on localhost.


- The file ```src/main/resources/application.properties``` specifies the the name of the MongoDB the 
application is using, the url and the and port. Change there accordingly or leave the defaults 

Note that an instance of ```mongod``` should be running in order to read/write to the DB.

### Some Issues With MongoDB

- https://stackoverflow.com/questions/40099847/error-creating-bean-with-name-mongotemplate-while-connecting-mongodb-through-s/54516317

