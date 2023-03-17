


import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;



public class SortedDoubleLinkedList_STUDENT_Test {
	SortedDoubleLinkedList<String> sortedLinkedString;
	SortedDoubleLinkedList<Double> sortedLinkedDouble;
	SortedDoubleLinkedList<Person> sortedLinkedPerson;
	StringComparator comparator;
	DoubleComparator comparatorD;
	PersonComparator comparatorPerson;
	
	//Order: C, B, D, E, F, A
	public Person a=new Person("Sim", "Soraka", 15);
	public Person b=new Person("Faker", "Leblanc", 17);
	public Person c=new Person("Alex", "Ornn", 19);
	public Person d=new Person("Jacob", "Viego", 21);
	public Person e=new Person("Keiran", "Tryndamere", 23);
	public Person f=new Person("Kamran", "HePersonim", 25);

	
	
	@Before
	public void setUp() throws Exception {
		comparator = new StringComparator();
		sortedLinkedString = new SortedDoubleLinkedList<String>(comparator);
		
		comparatorD = new DoubleComparator();
		sortedLinkedDouble = new SortedDoubleLinkedList<Double>(comparatorD);
		
		comparatorPerson = new PersonComparator();
		sortedLinkedPerson = new SortedDoubleLinkedList<Person>(comparatorPerson);
		
	}

	@After
	public void tearDown() throws Exception {
		comparator = null;
		comparatorD = null;
		comparatorPerson = null;
		sortedLinkedString = null;
		sortedLinkedDouble = null;
		sortedLinkedPerson = null;
	}

	@Test
	public void testAddToEnd() {
		try {
			sortedLinkedString.addToEnd("Raptor");
			assertTrue("Did not throw an UnsupportedOperationException", false);
		}
		catch (UnsupportedOperationException e)
		{
			assertTrue("Successfully threw an UnsupportedOperationException", true);
		}
		catch (Exception e)
		{
			assertTrue("Threw an exception other than the UnsupportedOperationException", false);
		}
	}

	@Test
	public void testAddToFront() {
		try {
			sortedLinkedString.addToFront("Montgomery");
			assertTrue("Did not throw an UnsupportedOperationException", false);
		}
		catch (UnsupportedOperationException e)
		{
			assertTrue("Successfully threw an UnsupportedOperationException", true);
		}
		catch (Exception e)
		{
			assertTrue("Threw an exception other than the UnsupportedOperationException", false);
		}
	}

	@Test
	public void testIteratorSuccessfulNext() {
		sortedLinkedPerson.add(a);
		sortedLinkedPerson.add(b);
		sortedLinkedPerson.add(c);
		sortedLinkedPerson.add(d);
		ListIterator<Person> iterator = sortedLinkedPerson.iterator();
		assertEquals(true, iterator.hasNext());
		assertEquals(c, iterator.next());
		assertEquals(b, iterator.next());
		assertEquals(d, iterator.next());
		assertEquals(true, iterator.hasNext());
	}

	@Test
	public void testIteratorSuccessfulStringPrevious() {
		sortedLinkedString.add("Snow");
		sortedLinkedString.add("Boba");
		sortedLinkedString.add("Rice");
		sortedLinkedString.add("Apple");
		ListIterator<String> iterator = sortedLinkedString.iterator();
		assertEquals(true, iterator.hasNext());
		assertEquals("Apple", iterator.next());
		assertEquals("Boba", iterator.next());
		assertEquals("Rice", iterator.next());
		assertEquals("Snow", iterator.next());
		assertEquals(true, iterator.hasPrevious());
		
		assertEquals("Snow", iterator.previous());
		assertEquals("Rice", iterator.previous());
		assertEquals("Boba", iterator.previous());
	}
	@Test
	public void testIteratorSuccessfulPersonPrevious() {
		sortedLinkedPerson.add(e);
		sortedLinkedPerson.add(c);
		sortedLinkedPerson.add(b);
		sortedLinkedPerson.add(d);
		ArrayList<Person> PersonList = sortedLinkedPerson.toArrayList();
		//alphabetic order: cbde
		// cbde
		ListIterator<Person> iterator = sortedLinkedPerson.iterator();
		assertEquals(true, iterator.hasNext());
		assertEquals(c, iterator.next());
		assertEquals(b, iterator.next());
		assertEquals(d, iterator.next());
		assertEquals(e, iterator.next());
		assertEquals(true, iterator.hasPrevious());
		assertEquals(e, iterator.previous());
		assertEquals(b, iterator.previous());
		assertEquals(c, iterator.previous());
	}
	@Test
	public void testIteratorSuccessfulDoubleNext() {
		sortedLinkedDouble.add((Double)8.0);
		sortedLinkedDouble.add((Double)6.0);
		sortedLinkedDouble.add((Double)1.0);
		sortedLinkedDouble.add((Double)2.0);
		ListIterator<Double> iterator = sortedLinkedDouble.iterator();
		assertEquals(true, iterator.hasNext());
		assertEquals((Double)1.0, iterator.next());
		assertEquals((Double)2.0, iterator.next());
		assertEquals((Double)6.0, iterator.next());
		assertEquals(true, iterator.hasNext());	}
	
	@Test
	public void testIteratorSuccessfulDoublePrevious() {
		
	
		sortedLinkedDouble.add((Double)4.0);
		sortedLinkedDouble.add((Double)9.0);
		
		sortedLinkedDouble.add((Double)7.0);
		
		sortedLinkedDouble.add((Double)1.0);	
		
		ListIterator<Double> iterator = sortedLinkedDouble.iterator();
		
		assertEquals((Double)1.0, iterator.next());
		assertEquals((Double)4.0, iterator.next());
	
		assertEquals((Double)7.0, iterator.next());
		
		assertEquals(true, iterator.hasPrevious());
		
		assertEquals((Double)7.0, iterator.previous());
		assertEquals(true, iterator.hasPrevious());
	}
	
	@Test
	public void testIteratorNoSuchElementException() {
		
		sortedLinkedPerson.add(e);
		sortedLinkedPerson.add(c);
		sortedLinkedPerson.add(b);
		sortedLinkedPerson.add(d);
		
		ArrayList<Person> PersonList = sortedLinkedPerson.toArrayList();
		
		ListIterator<Person> iterator = sortedLinkedPerson.iterator();
		
		assertEquals(true, iterator.hasNext());
		assertEquals(c, iterator.next()); 
		assertEquals(b, iterator.next()); 
		
	
		assertEquals(d, iterator.next()); 
		assertEquals(true, iterator.hasNext());
		
		assertEquals(e, iterator.next()); 
		
		try{
			//no more elements in list
			iterator.next();
			
			assertTrue("Did not throw a NoSuchElementException",false);
		}
		catch (NoSuchElementException e)
		{
			assertTrue("Successfully threw a NoSuchElementException",true);
		}
		catch (Exception e)
		{
			assertTrue("Threw an exception other than the NoSuchElementException", false);
		}
	}
	
	@Test
	public void testIteratorUnsupportedOperationExceptionString() {
		sortedLinkedPerson.add(e);
		sortedLinkedPerson.add(c);
		sortedLinkedPerson.add(b);
		sortedLinkedPerson.add(d);
		ArrayList<Person> PersonList = sortedLinkedPerson.toArrayList();
		
		ListIterator<Person> iterator = sortedLinkedPerson.iterator();
		
		try{
			//remove is not supported for the iterator
			iterator.remove();
			assertTrue("Did not throw a UnsupportedOperationException",false);
		}
		catch (UnsupportedOperationException e)
		{
			assertTrue("Successfully threw a UnsupportedOperationException",true);
		}
		catch (Exception e)
		{
			assertTrue("Threw an exception other than the UnsupportedOperationException", false);
		}
	}

	@Test
	public void testAddPerson() {
		
		
		sortedLinkedPerson.add(a); 
		sortedLinkedPerson.add(b); 
		
		sortedLinkedPerson.add(c);
		assertEquals(c, sortedLinkedPerson.getFirst());
		
		assertEquals(a, sortedLinkedPerson.getLast());
		sortedLinkedPerson.add(d);
		sortedLinkedPerson.add(e);
		assertEquals(c, sortedLinkedPerson.getFirst());
		assertEquals(a, sortedLinkedPerson.getLast());
		//deletes Elephant from linked list
		assertEquals(a,sortedLinkedPerson.retrieveLastElement());
		assertEquals(b, sortedLinkedPerson.getLast());
	}

	@Test
	public void testRemoveFirstPerson() {
		
		sortedLinkedPerson.add(b);
		sortedLinkedPerson.add(c);
		
		assertEquals(c, sortedLinkedPerson.getFirst());
		assertEquals(b, sortedLinkedPerson.getLast());
		sortedLinkedPerson.add(a);
		assertEquals(c, sortedLinkedPerson.getFirst());
		// remove the first
		sortedLinkedPerson.remove(a, comparatorPerson);
		assertEquals(c, sortedLinkedPerson.getFirst());
	}
	
	@Test
	public void testRemoveEndPerson() {
	
		sortedLinkedPerson.add(b);
		
		sortedLinkedPerson.add(f);
		
		assertEquals(b, sortedLinkedPerson.getFirst());
		assertEquals(f, sortedLinkedPerson.getLast());
		sortedLinkedPerson.add(d);
		
		assertEquals(f, sortedLinkedPerson.getLast());
		//remove from the end of the list
		sortedLinkedPerson.remove(d, comparatorPerson);
		
		assertEquals(f, sortedLinkedPerson.getLast());
	
	}

	@Test
	public void testRemoveMiddlePerson() {
		
		sortedLinkedPerson.add(a);
		sortedLinkedPerson.add(b);
		
		assertEquals(b, sortedLinkedPerson.getFirst());
		assertEquals(a, sortedLinkedPerson.getLast());
		sortedLinkedPerson.add(f);
		assertEquals(b, sortedLinkedPerson.getFirst());
		assertEquals(a, sortedLinkedPerson.getLast());
		assertEquals(3,sortedLinkedPerson.getSize());
		//remove from middle of list
		
		sortedLinkedPerson.remove(f, comparatorPerson);
		assertEquals(b, sortedLinkedPerson.getFirst());
		assertEquals(a, sortedLinkedPerson.getLast());
		assertEquals(2,sortedLinkedPerson.getSize());
	}

	private class StringComparator implements Comparator<String>
	{

		@Override
		public int compare(String arg0, String arg1) {
			// TODO Auto-generated method stub
			return arg0.compareTo(arg1);
		}
		
	}
	
	private class DoubleComparator implements Comparator<Double>
	{

		@Override
		public int compare(Double arg0, Double arg1) {
			// TODO Auto-generated method stub
			return arg0.compareTo(arg1);
		}
		
	}
	
	private class PersonComparator implements Comparator<Person>
	{

		@Override
		public int compare(Person arg0, Person arg1) {
			// Just put Person in alphabetic order
			return arg0.toString().compareTo(arg1.toString());
		}
		
	}
	
	private class Person{
		String firstName;
		String lastName;
		int age;
		
		public Person(String firstName, String lastName, int age){
			this.firstName = firstName;
			this.lastName = lastName;
			this.age = age;
		}
		
		public String getfirstName(){
			return firstName;
		}
		public String getlastName(){
			return lastName;
		}
		public int getAge(){
			return age;
		}
		
		public String toString() {
			return (getfirstName()+" "+getlastName()+" "+getAge());
		}
	}
}
