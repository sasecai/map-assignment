package model.containers;

import exception.MyException;
import model.values.Value;

import java.util.ArrayList;

public class MyList<T> implements MyIList<T>{
    ArrayList<T> array;
    public MyList() {
        array = new ArrayList<>();
    }
    @Override
    public void add(T elem) {
        array.add(elem);
    }

    @Override
    public void erase(int index) {
        array.remove(index);
    }

    @Override
    public T get(int index) {
        return array.get(index);
    }

    public int size() { return array.size(); }
    public String toStr() throws MyException {

        String s = "";
        for(int i = 0; i < array.size(); i ++) {
            try{
                s += ((Value)(array.get(i))).toString();
            } catch (Exception e) {
                s += array.get(i).toString();
            }
            s += ";";
        }
        return s;
    }
}
