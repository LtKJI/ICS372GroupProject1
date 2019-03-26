package edu.metrostate.ics372groupproject1.scientificDataCollectionApp;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * This class is a collection of study in the program 
 * It stores a study object along with the sites and 
 * the readings associated with the study
 */
public class Record {
	private static final Object LOCK = new Object();
	private static Record  recordSelf = null;
	@SerializedName("Record")
	@Expose
	private static List<Study> studyList = new ArrayList<Study>();
	
	private Record() {}
	//Singleton instance method
	public static Record getInstance() {
		if(recordSelf == null) {
			synchronized (LOCK) {
				if(recordSelf == null) {
					recordSelf = new Record();
				}
			}
		}
		return recordSelf;
	}
	
	//Appends the specified element to the end of this list (optional operation). 
	public boolean addStudy(Study s) {
		return studyList.add(s);
	}
	
	/**
	 * @param arg0 - Integer position to add to
	 * @param arg1- Study to be added
	 * Inserts the specified element at the specified position in
	 *  this list(optional operation).
	 */
	public void addStudy(int arg0, Study arg1) {
		studyList.add(arg0, arg1);
	}

	/**
	 * @param c- collection containing elements to be added to this list
	 * @return
	 * Appends all of the elements in the specified collection to 
	 * the end of this list, in the order that they are returned 
	 * by the specifiedcollection's iterator (optional operation). 
	 */
	public boolean addAllStudy(Collection<? extends Study> c) {
		return studyList.addAll(c);
	}

	/**
	 * Removes all of the elements from this list (optional operation).
	 * The list will be empty after this call returns
	 */
	public void clearStudy() {
		studyList.clear();
	}

	//Returns true if this list contains the specified element.
	public boolean contains(Object o) {
		return studyList.contains(o);
	}
	
	/**
	 * @param String name, ID
	 * Find a study by study name and ID
	 * @return 
	 * Study
	 * Null if not found
	 */
	public static Study findByAttributes(String name, String Id) {
		Study found = null;
		//it the studyList is not empty 
		if (!studyList.isEmpty()) {
			Iterator<Study> iterate = studyList.iterator();
			while (iterate.hasNext()) {
				Study st = iterate.next();
				if (st.getStudyID().equals(Id) && st.getStudyName().equals(name)) {
					found = st;
					break;
				}
			} 
		}
		return found;
	}

	/**
	 * Returns true if this list contains all of the elements
	 * of the specified collection.
	 */
	public boolean containsAll(Collection<?> c) {
		return studyList.containsAll(c);
	}

	//Returns the element at the specified position in this list.
	public Study get(int index) {
		return studyList.get(index);
	}

	/**
	 * Returns the index of the first occurrence of the specified
	 *  element in this list, or -1 if this list does not contain 
	 *  the element.
	 */
	public int indexOf(Object o) {
		return studyList.indexOf(o);
	}

	//Returns true if this list contains no elements.
	public boolean isEmpty() {
		return studyList.isEmpty();
	}

	/*
	 * Returns an iterator over the elements in this list
	 * in proper sequence
	 */
	public Iterator<Study> iterator() {
		return studyList.iterator();
	}

	// Remove a study from the list by passing in a study object to be remove
	public boolean remove(Object o) {
		return studyList.remove(o);
	}

	// Remove a study from the list by index
	public Study remove(int index) {
		return studyList.remove(index);
	}

	// Removes from this list all of its elements that are contained
	// in the specified collection (optional operation)
	public boolean removeAll(Collection<?> c) {
		return studyList.removeAll(c);
	}

	// Replaces the element at the specified position in this list
	// with the specified element (optional operation).
	public Study set(int index, Study element) {
		return studyList.set(index, element);
	}

	// Returns the number of elements in this list.
	public int size() {
		return studyList.size();
	}

	// Returns an array containing all of the elements in this list in
	// proper sequence (from first to last element).
	public Object[] toArray() {
		return studyList.toArray();
	}
}
