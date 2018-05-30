
// array of gesture position and id

package com.example.snippet.multi_touchgesture;

import java.util.ArrayList;

public class GestureArray {

    private ArrayList<GestureItem> data;

    // constructor
    public GestureArray() {
        data = new ArrayList<GestureItem>();
    }

    // adds new item to the end of an array
    public void add(int id, float x, float y){

        GestureItem item = new GestureItem();

        item.x = x;
        item.y = y;
        item.id = id;

        data.add(item);
    }

    // remove item from an array that match the id
    public void remove(int id){

        int i;
        GestureItem item;

        for(i=0;i<data.size();i++){
            item = data.get(i);
            if(item.id == id) break;
        }

        data.remove(i);
    }

    // return item from array by index
    public GestureItem get(int i){

        GestureItem item = data.get(i);
        return item;
    }

    // return the size of an array
    public int size(){
        return data.size();
    }

    // element of an array
    public class GestureItem{
        float x, y;
        int id;
    }
}
