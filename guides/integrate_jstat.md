# Integrate ```jstat```

```jstat-gui``` is a Spring based GUI application for ```jstat```. The latter is simply
our compute engine. In order to use ```jstat-gui``` we need to have ```jstat``` into our project.
This guide shows you how to integrate ```jstat``` in ```jstat-gui``` application.

## Using IntelliJ IDEA IDE

If you are using the IntelliJ IDEA IDE, then adding ```jstat.jar``` is easy. 

1. Build the ```jstat.jar``` as described here: <a href="https://github.com/jethronap/jstat/blob/master/guides/jstat_archive.md">Create a ```.jar``` archive for ```jstat```.
2. Open ```File->Project Structure``` 
3. Select Modules at the left panel
4. Dependencies tab
5. ```+ -> JARs or directories``` and navigate to the directory you have ```jstat.jar```