package model.containers;

import model.values.Value;

import java.io.BufferedReader;
import java.util.*;

public class MyDictionary<K, V> implements MyIDictionary<K, V>{
    Map<K, V> dic;
    public MyDictionary() {
        dic = new HashMap<>();
    }
    public MyDictionary(Map<K, V> dic) {
        this.dic = dic;
    }
    @Override
    public void insert(K key, V value) {
        dic.put(key, value);
    }

    @Override
    public V access(K key) {
        return dic.get(key);
    }

    @Override
    public boolean isDefined(K key) {
        return dic.containsKey(key);
    }
    public void remove(K key) {
        dic.remove(key);
    }
    public String toStr() {
        String s = "";
        for(Map.Entry<K, V> entry : dic.entrySet()) {
            if((Object)entry.getValue() instanceof BufferedReader) {
//                System.out.println(entry.getKey().getClass());
                s += entry.getKey().toString();
                s += ';';
            }
            else {
                s += entry.getKey();
                s += "=";
                try {
                    s += ((Value) (entry.getValue())).toString();
                } catch (Exception e) {
                    s += entry.getValue().toString();
                }
                s += ";";
            }
        }
        return s;
    }

    public Set<K> getKeys() {
        return dic.keySet();
    }
    public Collection<V> getValues() {
        return dic.values();
    }
}
