package mongodb.mocks;

import mongodb.IDoc;
import org.springframework.data.mongodb.core.query.Query;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ComputeResultsCollectionMock implements IMongoDBCollectionMock {


    public ComputeResultsCollectionMock(){
        data_ = new ArrayList<>();
    }


    public <T extends IDoc> void add(T object){

        if(object == null){
            throw new IllegalArgumentException("Cannot save null object");
        }

        object.setId(Integer.toString(data_.size()));
        data_.add(object);
    }

    public <T extends IDoc> T findOne(Query query , Class<T> objClass){


        String val = query.toString();
        String queryStr = val.split(",")[0];
        String[] queryStrVals = queryStr.split(":");

        String finalQuery = new String();

        for(int i=1; i < queryStrVals.length; i += 2){

            if(i == 1){

                String strip = queryStrVals[i].replaceAll("\\{", "");
                strip = strip.replaceAll("\"", "");
                strip = strip.strip();
                queryStrVals[i]  = strip;
            }

            if(queryStrVals[i+1].endsWith(" }")){
                String strip = queryStrVals[i+1].replaceAll("\\}", "");
                strip = strip.replaceAll("\"", "");
                strip = strip.strip();
                queryStrVals[i+1]  = strip;
            }

            finalQuery += queryStrVals[i];
            finalQuery += ":";
            finalQuery += queryStrVals[i+1];
            finalQuery +=",";

        }

        Map<String, String> map = Arrays.stream(finalQuery.split(","))
                .map(entry -> entry.split(":"))
                .collect(Collectors.toMap(entry -> entry[0], entry -> entry[1]));


        for(int i=0; i<data_.size(); ++i){

            T item = (T) data_.get(i);

            if(item.satisfiesConditions(map)){
                return item;
            }
        }

        return null;
    }

    // documents held by the collection
    List<Object> data_;

}
