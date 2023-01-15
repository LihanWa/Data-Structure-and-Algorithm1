package cs445.rec10;

import java.util.Iterator;

public class IterableUtils {

    /**
     * Prints a collection of entries (of any type: T), using the traversal
     * behaviour of the Iterator pattern
     * @param collection  the collection to be printed
     */
    public static <T> void printAll(Iterable<T> collection) {
        // TODO: implement this method
        Iterator<T> printIterator=collection.iterator();
        while(printIterator.hasNext()){
            System.out.print(printIterator.next());
        }
    }

    /**
     * Removes all the enteries (of type: String) whose length is less than a
     * certain limit
     * @param collection  the collection to be printed
     * @param limit  the upper limit for the length of strings to be removed
     */
    public static void removeShortStrings(Iterable<String> collection, int limit) {
        // TODO: implement this method
        Iterator<String> removeIterator=collection.iterator();
        while(removeIterator.hasNext()){
            if(removeIterator.next().length()<limit){
                removeIterator.remove();
            }
        }
    }

    /**
     * Find the most repetitive entry (mode), in a collection of enteries (of
     * any type: T),
     * @param collection  the collection to be printed
     * @return  the mode
     */
    public static <T> T findMode(Iterable<T> collection) {
        // TODO: implement this method
        int max=0;
        int cnt=0;
        
        Iterator<T> findIterator=collection.iterator();
        T result=null;
        while(findIterator.hasNext()){
            
            Iterator<T> count=collection.iterator();
            T obj=findIterator.next();
            while(count.hasNext()){
                if(count.next().equals(obj)) {
                    cnt++;
                    if(cnt>1) count.remove();
                }
                
            }
            if(cnt>max) {
                max=cnt;
                result=obj;
            }
            cnt=0;
        }
        
        return result;
    }

}

