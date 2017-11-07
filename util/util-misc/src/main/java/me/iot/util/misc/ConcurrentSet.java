//package me.iot.util.misc;
//
//import java.util.AbstractSet;
//import java.util.Iterator;
//import java.util.concurrent.ConcurrentHashMap;
//import java.util.concurrent.ConcurrentMap;
//
//public final class ConcurrentSet<E> extends AbstractSet<E> {
//
//    private final ConcurrentMap<E, Boolean> map = new ConcurrentHashMap<>();
//
//    @Override
//    public int size() {
//        return map.size();
//    }
//
//    @Override
//    public boolean contains(Object o) {
//        return map.containsKey(o);
//    }
//
//    @Override
//    public boolean add(E o) {
//        return map.putIfAbsent(o, Boolean.TRUE) == null;
//    }
//
//    @Override
//    public boolean remove(Object o) {
//        return map.remove(o) != null;
//    }
//
//    @Override
//    public void clear() {
//        map.clear();
//    }
//
//    @Override
//    public Iterator<E> iterator() {
//        return map.keySet().iterator();
//    }
//}
