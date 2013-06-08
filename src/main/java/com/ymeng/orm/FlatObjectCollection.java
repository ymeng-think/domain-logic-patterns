package com.ymeng.orm;

import java.util.ArrayList;
import java.util.List;

public class FlatObjectCollection {

    private List<FlatObject> objects = new ArrayList<FlatObject>();

    public FlatObject get(int index) {
        return objects.get(index);
    }

    public void add(FlatObject object) {
        objects.add(object);
    }

    public int size() {
        return objects.size();
    }
}
