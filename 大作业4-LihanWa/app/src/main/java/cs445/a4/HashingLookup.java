package cs445.a4;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.ConcurrentModificationException;

public class HashingLookup<I,C> implements LookupInterface<I, C>, Iterable<I>{
    private Pair[] hashContents;
    private int secondMod;
    private int size;
    private int loadSize;
    private final Pair TOMBSTONE= new Pair(null,null);
    private int callIterator=0;
    private class Pair{
        I id;
        C Con;
        Pair(I k, C v){
            id=k;
            Con=v;
        }
        public String toString(){
            return "{"+id+","+Con+"}";
        }
    }
    public HashingLookup(){
        hashContents =(Pair[])new HashingLookup<?,?>.Pair[19];
        secondMod = 17;
        size=0;
        loadSize=0;
    }
    public HashingLookup(int Capacity){
        if (Capacity<5) throw new IllegalArgumentException();
        hashContents=(Pair[])new HashingLookup<?,?>.Pair[Primes.primeNoLessThan(Capacity)];
        secondMod=Primes.primeNoMoreThan(Capacity-1);
        size=0;
        loadSize=0;
    }
    private int hash(I id){
        int h=id.hashCode()%hashContents.length;
        if(h<0) h+=hashContents.length;
        return h;
    }
    private int hash2(I id){
        int offset=id.hashCode()%secondMod;
        if(offset<0) offset+=secondMod;
        offset++;
        return offset;
    }
    public int getSize(){
        return loadSize;
    }
    public boolean isEmpty(){
        if(loadSize==0) return true;
        else return false;
    }
    public boolean add(I newId, C newCon) throws NullPointerException{
        if(callIterator==1){callIterator++;}
        if (newId==null){throw new NullPointerException();}
        if (contains(newId)){return false;}
        if (isTooFull())  increaseCapacity();
        C result;
        int i=hash(newId);
        int offset=hash2(newId);
        int tombLoc=-1;
        while(hashContents[i] != null &&
                (hashContents[i] == TOMBSTONE || !hashContents[i].id.equals(newId))) {
            if (hashContents[i] == TOMBSTONE && tombLoc < 0) tombLoc = i;
            i=(i + offset) % hashContents.length;
        }
        size++;
        if(tombLoc>=0) i=tombLoc;
        else loadSize++;
        hashContents[i]=new Pair(newId,newCon);
        return true;

    }
        
    
    private boolean isTooFull(){
        return loadSize>=hashContents.length*0.5;
    }
    private void increaseCapacity(){
        if(callIterator==1){callIterator++;}
        Pair[] old=hashContents;
        int newCap=Primes.primeNoLessThan(hashContents.length*2);
        hashContents=(Pair[])new HashingLookup<?,?>.Pair[newCap];
        secondMod=Primes.primeNoMoreThan(newCap-1);
        size=0;
        loadSize=0;
        for(int i=0;i<old.length;i++){
            if (old[i]!=null&&old[i]!=TOMBSTONE){
                add(old[i].id,old[i].Con);
            }
        }
    }
    public boolean contains(I id) throws NullPointerException{
        if (id==null){throw new NullPointerException();}
        int i=hash(id);
        int offset=hash2(id);
        while (hashContents[i] != null &&
                (hashContents[i] == TOMBSTONE || !hashContents[i].id.equals(id))){
            
            i=(i+offset)%hashContents.length;
        }
        if(hashContents[i]!=null) return true;
        return false;
    }
    public C replace(I id, C newCon) throws IdentifierNotFoundException{
        if(callIterator==1){callIterator++;}
        int i=hash(id);
        int offset=hash2(id);
        while (hashContents[i] != null &&
                (hashContents[i] == TOMBSTONE || !hashContents[i].id.equals(id))){
            
            i=(i+offset)%hashContents.length;
        }
        if(hashContents[i]!=null){
            C result=hashContents[i].Con;
            hashContents[i].Con=newCon;
            return result;
        }
        throw new IdentifierNotFoundException();
    }
    public C get(I id) throws IdentifierNotFoundException{
        int i=hash(id);
        int offset=hash2(id);
        while (hashContents[i] != null &&
                (hashContents[i] == TOMBSTONE || !hashContents[i].id.equals(id))){
            
            i=(i+offset)%hashContents.length;
        }
        if(hashContents[i]!=null) 
        {
            return hashContents[i].Con;
        }
        throw new IdentifierNotFoundException();
    }
    public C remove(I id) throws IdentifierNotFoundException,NullPointerException{
        if(callIterator==1){callIterator++;}
        if (id==null){throw new NullPointerException();}
        int i=hash(id);
        int offset=hash2(id);
        while (hashContents[i] != null &&
                (hashContents[i] == TOMBSTONE || !hashContents[i].id.equals(id))){
            i = (i + offset) % hashContents.length;
        
        }
        if(hashContents[i].id.equals(id)) 
        {
            C result=hashContents[i].Con;
            hashContents[i]=TOMBSTONE;
            size--;
            return result;
        }
        throw new IdentifierNotFoundException();
    }
    public Object[] remove(){
        if(callIterator==1){callIterator++;}
        if(size==0){return null;}
        Object[] a=new Object[2];
        int i=0;
        for(;i<hashContents.length;i++){
            if(hashContents[i]!=null) break;
        }
        a[0]=hashContents[i].id;
        a[1]=hashContents[i].Con;
        hashContents[i]=TOMBSTONE;
        size--;
        return a;
    }
    public void clear(){
        if(callIterator==1){callIterator++;}
        hashContents=(Pair[])new HashingLookup<?,?>.Pair[19];
        secondMod=17;
        size=0;
        loadSize=0;
    }
    public Object[] getAllIdentifiers(){
        @SuppressWarnings("unchecked")
        I[] tempId=(I[])new Object[size];
        
        int j=0;
        for (int i=0; i<hashContents.length; i++){
            
            if(hashContents[i]!=null&&hashContents[i]!=TOMBSTONE){
                tempId[j]=hashContents[i].id;
                j++;
            }
              
            
            
            
        }
        return tempId;
    }
    public Iterator<I> iterator(){
        
        return new HashIterator();
    }

    private class HashIterator implements Iterator<I>{
        private int next;
        
        int nextIndex=0;
        private HashIterator(){
            
            callIterator=1;

            
        }
        public boolean hasNext(){
            if(callIterator==2) throw new ConcurrentModificationException();
            return next<size;
        }
        public I next(){
            if(callIterator==2) throw new ConcurrentModificationException();
            if (hasNext()) {
                while (hashContents[nextIndex] == null || hashContents[nextIndex] == TOMBSTONE) {
                    nextIndex++;
                }
                
                next++;
                return (I)hashContents[nextIndex++].id;
                
            } else throw new NoSuchElementException();
        }
        public void remove(){
            throw new UnsupportedOperationException();
        }
    }

}