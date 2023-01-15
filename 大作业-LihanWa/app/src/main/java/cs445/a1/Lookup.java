package cs445.a1;
public class Lookup<I,C> implements LookupInterface<I,C>{
    private I[] identifier;
    private C[] content;
    private int size;
    private int capacity;
    private static final int default_capacity=25;
    public Lookup(int initialCapacity){
        @SuppressWarnings("unchecked")
        I[] tempIdentifier= (I[])new Object[initialCapacity];
        identifier=tempIdentifier;
        C[] tempContent=(C[])new Object[initialCapacity];
        content=tempContent;
        size = 0;
        capacity=initialCapacity;
    }
    public Lookup(){
        this(default_capacity);
        capacity=default_capacity;
    }
     /**
     * Determines the current number of (identifier, content) pairs in this
     * lookup.
     *
     * @return  The integer number of pairs currently in this lookup
     */
    public int getSize(){
        return size;
    }

    /**
     * Determines whether this lookup is empty.
     *
     * @return  true if this lookup is empty; false if not
     */
    public boolean isEmpty(){
        return size==0;
    
    }

    /**
     * Adds a new pair to this lookup, avoiding duplicate identifiers.
     *
     * <p> If newId is not null, this lookup does not contain newId, and this
     * lookup has available capacity (if applicable), then add modifies the
     * lookup so that it contains newId associated with content newCon (which is
     * allowed to be null or a duplicate). All other pairs remain unmodified.
     * Duplicate identifiers are determined using the .equals() method.
     *
     * <p> If newId is null, then add throws NullPointerException without
     * modifying the lookup. If this lookup already contains newId, then add
     * returns false without modifying ththise lookup. If  lookup has a capacity
     * limit, and does not have available capacity, then add throws
     * FullLookupException without modifying the lookup. If this lookup does not
     * have a capacity limit (i.e., if it resizes as needed), then it will never
     * throw FullLookupException.
     *
     * @param newId  The identifier to be added to the lookup
     * @param newCon  The content object associated with newId
     * @return  true if the addition is successful; false if newId already is in
     * this lookup
     * @throws FullLookupException  If this lookup has a fixed capacity and does
     * not have the capacity to store an additional pair
     * @throws NullPointerException  If newId is null
     */
    public boolean add(I newId, C newCon) throws FullLookupException, NullPointerException{
        if (newId==null){throw new NullPointerException();}
        if (contains(newId)){return false;}
        
        if(size==capacity){
            int newCap=2*capacity;
            @SuppressWarnings("unchecked")
            I[] tempIdentifier= (I[])new Object[newCap];
            for(int i=0;i<size;i++){
                tempIdentifier[i]=identifier[i];
            }
            identifier=tempIdentifier;
            @SuppressWarnings("unchecked")
            C[] tempContent=(C[])new Object[newCap];
            for(int i=0;i<size;i++){
                tempContent[i]=content[i];
            }
            content=tempContent;
            capacity=newCap;
        }
        
        identifier[size]=newId;
        content[size]=newCon;
        size++;
        return true;
    }

    /**
     * Replaces the content for an existing identifier.
     *
     * <p> If this lookup contains id, replace modifies the lookup so that id is
     * now associated with content newCon and returns its previous content. All
     * other pairs remain unmodified. Identifiers are compared using the
     * .equals() method.
     *
     * <p> If this lookup does not contain a pair with the identifier id,
     * replace will throw IdentifierNotFoundException without modifying the
     * lookup.
     *
     * @param id  The identifier of the pair to be updated
     * @param newCon  The new content for the given identifier
     * @return  The previous content object for id
     * @throws IdentifierNotFoundException  If no pair with id is found in this
     * lookup
     */
    public C replace(I id, C newCon) throws IdentifierNotFoundException{
        for(int i=0;i<size;i++){
            if(identifier[i].equals(id)){
                C result=content[i];
                content[i]=newCon;
                return result;
            }
        }
        throw new IdentifierNotFoundException();

    }

    /**
     * Retrieves the content object for the given identifier.
     *
     * <p> If this lookup contains a pair with identifier id, get returns the
     * content of that pair. Otherwise, get throws IdentifierNotFoundException.
     *
     * <p> The method get never modifies this lookup.
     *
     * @param id  The identifier to be searched
     * @return  The content associated with the specified identifier
     * @throws IdentifierNotFoundException  If no pair with id is found in this
     * lookup
     */
    public C get(I id) throws IdentifierNotFoundException{
        for(int i=0;i<size;i++){
            if(identifier[i].equals(id)){return content[i];}
        }
        throw new IdentifierNotFoundException();
    }

    /**
     * Removes a specific pair from this lookup, if possible.
     *
     * <p> If this lookup contains a pair with identifier id, remove modifies
     * the lookup so that it no longer contains pair. All other pairs remain
     * unmodified. Identifying the correct pair is accomplished using the
     * .equals() method on the identifier. The content object of the removed
     * pair will be returned.
     *
     * <p> If this lookup does not contain a pair with the identifier id, remove
     * will throw IdentifierNotFoundException without modifying the lookup.
     *
     * <p> If the specified id is null, then remove throws NullPointerException
     * without modifying the lookup.
     *
     * @param id  The identifier of the pair to be removed
     * @return  The content of the removed pair
     * @throws IdentifierNotFoundException  If no pair with id is found in the
     * lookup
     * @throws NullPointerException  If id is null
     */
    public C remove(I id) throws IdentifierNotFoundException,NullPointerException{
        if (id==null){throw new NullPointerException();}
        for(int i=0;i<size;i++){
            if (identifier[i].equals(id)){
                C result=content[i];
                identifier[i]=identifier[size-1];
                identifier[size-1]=null;
                content[i]=content[size-1];
                content[size-1]=null;
                size--;
                return result;
            }
        }
        throw new IdentifierNotFoundException();
    }

    /**
     * Removes an arbitrary pair from this lookup, if possible.
     *
     * <p> If this lookup contains at least one pair, remove will modify the
     * lookup so that it no longer contains one of its pairs. All other pairs
     * remain unmodified. The removed pair will be returned in the form of an
     * Object array with capacity 2: the first index points to the identifier,
     * the second to the content object.
     *
     * <p> If this lookup is empty, remove will return null without modifying
     * the lookup. Because null is not a valid pair, a return value of null will
     * never indicate a successful removal.
     *
     * @return  The removed pair if the removal was successful; null otherwise
     */
    public Object[] remove(){
        if(size==0){return null;}
        Object[] a=new Object[2];
        a[0]=identifier[size-1];
        identifier[size-1]=null;
        a[1]=content[size-1];
        content[size-1]=null;
        size--;
        return a;
    }
    

    /**
     * Removes all pairs from this lookup.
     *
     * <p> If this lookup is already empty, clear will not modify the lookup.
     * Otherwise, the lookup will be modified so that it contains no pairs.
     */
    public void clear(){
        while(!isEmpty()){
            remove();
        }
    }

    /**
     * Tests whether this lookup contains a pair with the given identifier.
     * Equality of identifiers is determined using the .equals() method.
     *
     * <p> If this lookup contains id, then contains returns true. Otherwise
     * (including if this lookup is empty), contains returns false. If id is
     * null, then contains throws NullPointerException. The method never
     * modifies this lookup.
     *
     * @param id  The identifier to locate
     * @return  true if this lookup contains a pair with identifier id; false if
     * not
     * @throws NullPointerException  If id is null
     */
    public boolean contains(I id) throws NullPointerException{
        if (id==null){throw new NullPointerException();}
        for(int i=0;i<size;i++){
            if(identifier[i].equals(id)){
                return true;
            }
        }
        return false;
    }

    /**
     * Retrieves all identifiers that are in this lookup.
     *
     * <p> An array is returned that contains a reference to each of the
     * identifiers in this lookup. The returned array's length will be equal to
     * the number of pairs in this lookup, and thus the array will contain no
     * null values.
     *
     * <p> If the implementation of lookup is array-backed, getAllIdentifiers
     * will not return the private backing array (i.e., data member). Instead, a
     * new array should always be allocated with exactly the appropriate
     * capacity (including an array of size 0, if this lookup is empty).
     *
     * <p> Since generic arrays cannot be created at runtime due to type
     * erasure, getAllIdentifiers must return an Object array (but each
     * reference is guaranteed to point to an object of type I thanks to
     * compile-time type checks).
     *
     * @return A newly-allocated array of all the identifiers in this lookup.
     */
    public Object[] getAllIdentifiers(){
        @SuppressWarnings("unchecked")
        I[] tempId=(I[])new Object[size];
        for (int i=0; i<size; i++){
            tempId[i]=identifier[i];
        }
        return tempId;
    }

}



