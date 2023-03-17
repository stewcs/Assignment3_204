

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;


public class BasicDoubleLinkedList_STUDENT_Test {
	BasicDoubleLinkedList<String> linkedString;
	BasicDoubleLinkedList<Double> linkedDouble;
	BasicDoubleLinkedList<Person> linkedPerson;
	StringComparator comparator;
	DoubleComparator comparatorD;
	PersonComparator comparatorPerson;
	
	public Person a=new Person("Sim", "Soraka", 15);
	public Person b=new Person("Faker", "Leblanc", 17);
	public Person c=new Person("Alex", "Ornn", 19);
	public Person d=new Person("Jacob", "Viego", 21);
	public Person e=new Person("Keiran", "Tryndamere", 23);
	public Person f=new Person("Kamran", "Hecarim", 25);

	public ArrayList<Person> fill = new ArrayList<Person>();
	

	@Before
	public void setUp() throws Exception {
		linkedString = new BasicDoubleLinkedList<String>();
		linkedString.addToEnd("Montgomery");
		linkedString.addToEnd("College");
		comparator = new StringComparator();
		
		linkedDouble = new BasicDoubleLinkedList<Double>();
		linkedDouble.addToEnd(15.0);
		linkedDouble.addToEnd(100.0);
		comparatorD = new DoubleComparator();
		
		linkedPerson= new BasicDoubleLinkedList<Person>();
		linkedPerson.addToEnd(b);
		linkedPerson.addToEnd(c);
		comparatorPerson = new PersonComparator();
	}

	@After
	public void tearDown() throws Exception {
		linkedString = null;
		linkedDouble = null;
		linkedPerson = null;
		comparatorD = null;
		comparator = null;
	}

	@Test
	public void testGetSize() {
		assertEquals(2,linkedString.getSize());
		assertEquals(2,linkedDouble.getSize());
		assertEquals(2,linkedPerson.getSize());
	}
	
	@Test
	public void testAddToEnd() {
		assertEquals("College", linkedString.getLast());
		linkedString.addToEnd("Raptor");
		assertEquals("Raptor", linkedString.getLast());
		
		assertEquals(c,linkedPerson.getLast());
		linkedPerson.addToEnd(d);
		assertEquals(d,linkedPerson.getLast());
	}
	
	@Test
	public void testAddToFront() {
		assertEquals("Montgomery", linkedString.getFirst());
		linkedString.addToFront("Hello");
		assertEquals("Hello", linkedString.getFirst());
		
		assertEquals(b,linkedPerson.getFirst());
		linkedPerson.addToFront(a);
		assertEquals(a,linkedPerson.getFirst());
	}
	
	@Test
	public void testGetFirst() {
		assertEquals("Montgomery", linkedString.getFirst());
		linkedString.addToFront("Hello");
		assertEquals("Hello", linkedString.getFirst());
		
		assertEquals(b,linkedPerson.getFirst());
		linkedPerson.addToFront(a);
		assertEquals(a,linkedPerson.getFirst());
	}

	@Test
	public void testGetLast() {
		assertEquals("College", linkedString.getLast());
		linkedString.addToEnd("Raptor");
		assertEquals("Raptor", linkedString.getLast());
		
		assertEquals(c,linkedPerson.getLast());
		linkedPerson.addToEnd(d);
		assertEquals(d,linkedPerson.getLast());
	}
	
	@Test
	public void testToArrayList()
	{
		ArrayList<Person> list;
		linkedPerson.addToFront(a);
		linkedPerson.addToEnd(d);
		list = linkedPerson.toArrayList();
		assertEquals(a,list.get(0));
		assertEquals(b,list.get(1));
		assertEquals(c,list.get(2));
		assertEquals(d,list.get(3));
	}
	
	@Test
	public void testIteratorSuccessfulNext() {
		linkedString.addToFront("Begin");
		linkedString.addToEnd("End");
		ListIterator<String> iterator = linkedString.iterator();
		assertEquals(true, iterator.hasNext());
		assertEquals("Begin", iterator.next());
		assertEquals("Montgomery", iterator.next());
		assertEquals("College", iterator.next());
		assertEquals(true, iterator.hasNext());
		assertEquals("End", iterator.next());
		
		linkedPerson.addToFront(a);
		linkedPerson.addToEnd(d);
		ListIterator<Person> iteratorPerson = linkedPerson.iterator();
		assertEquals(true, iteratorPerson.hasNext());
		assertEquals(a, iteratorPerson.next());
		assertEquals(b, iteratorPerson.next());
		assertEquals(c, iteratorPerson.next());
		assertEquals(true, iteratorPerson.hasNext());
		assertEquals(d, iteratorPerson.next());
	}
	
	@Test
	public void testIteratorSuccessfulPrevious() {
		linkedPerson.addToFront(a);
		linkedPerson.addToEnd(d);
		ListIterator<Person> iteratorPerson = linkedPerson.iterator();
		assertEquals(true, iteratorPerson.hasNext());
		assertEquals(a, iteratorPerson.next());
		assertEquals(b, iteratorPerson.next());
		assertEquals(c, iteratorPerson.next());
		assertEquals(d, iteratorPerson.next());
		assertEquals(true, iteratorPerson.hasPrevious());
		assertEquals(d, iteratorPerson.previous());
		assertEquals(c, iteratorPerson.previous());
		assertEquals(b, iteratorPerson.previous());
		assertEquals(a, iteratorPerson.previous());
	}
	
	@Test
	public void testIteratorNoSuchElementExceptionNext() {
		linkedPerson.addToFront(a);
		linkedPerson.addToEnd(d);
		ListIterator<Person> iteratorPerson = linkedPerson.iterator();		
		assertEquals(true, iteratorPerson.hasNext());
		assertEquals(a, iteratorPerson.next());
		assertEquals(b, iteratorPerson.next());
		assertEquals(c, iteratorPerson.next());
		assertEquals(true, iteratorPerson.hasNext());
		assertEquals(d, iteratorPerson.next());
		
		try{
			//no more elements in list
			iteratorPerson.next();
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
	public void testIteratorNoSuchElementExceptionPrevious() {
		linkedPerson.addToFront(a);
		linkedPerson.addToEnd(d);
		ListIterator<Person> iteratorPerson = linkedPerson.iterator();		
		assertEquals(true, iteratorPerson.hasNext());
		assertEquals(a, iteratorPerson.next());
		assertEquals(b, iteratorPerson.next());
		assertEquals(c, iteratorPerson.next());
		assertEquals(d, iteratorPerson.next());
		assertEquals(true, iteratorPerson.hasPrevious());
		assertEquals(d, iteratorPerson.previous());
		assertEquals(c, iteratorPerson.previous());
		assertEquals(b, iteratorPerson.previous());
		assertEquals(a, iteratorPerson.previous());
		
		try{
			//no more elements in list
			iteratorPerson.previous();
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
	public void testIteratorUnsupportedOperationException() {
		linkedPerson.addToFront(a);
		linkedPerson.addToEnd(d);
		ListIterator<Person> iteratorPerson = linkedPerson.iterator();		
		assertEquals(true, iteratorPerson.hasNext());
		assertEquals(a, iteratorPerson.next());
		assertEquals(b, iteratorPerson.next());
		assertEquals(c, iteratorPerson.next());
		assertEquals(true, iteratorPerson.hasNext());
		assertEquals(d, iteratorPerson.next());
		
		try{
			//remove is not supported for the iterator
			iteratorPerson.remove();
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
	public void testRemove() {
		// remove the first
		assertEquals(b, linkedPerson.getFirst());
		assertEquals(c, linkedPerson.getLast());
		linkedPerson.addToFront(a);
		assertEquals(a, linkedPerson.getFirst());
		linkedPerson.remove(a, comparatorPerson);
		assertEquals(b, linkedPerson.getFirst());
		//remove from the end of the list
		linkedPerson.addToEnd(d);
		assertEquals(d, linkedPerson.getLast());
		linkedPerson.remove(d, comparatorPerson);
		assertEquals(c, linkedPerson.getLast());
		//remove from middle of list
		linkedPerson.addToFront(a);
		assertEquals(a, linkedPerson.getFirst());
		assertEquals(c, linkedPerson.getLast());
		linkedPerson.remove(b, comparatorPerson);
		assertEquals(a, linkedPerson.getFirst());
		assertEquals(c, linkedPerson.getLast());
		
	}

	@Test
	public void testRetrieveFirstElement() {
		assertEquals(b, linkedPerson.getFirst());
		linkedPerson.addToFront(a);
		assertEquals(a, linkedPerson.getFirst());
		assertEquals(a, linkedPerson.retrieveFirstElement());
		assertEquals(b,linkedPerson.getFirst());
		assertEquals(b, linkedPerson.retrieveFirstElement());
		assertEquals(c,linkedPerson.getFirst());
		
		assertEquals("Montgomery", linkedString.getFirst());
		linkedString.addToFront("Hello");
		assertEquals("Hello", linkedString.getFirst());
		assertEquals("Hello", linkedString.retrieveFirstElement());
		assertEquals("Montgomery",linkedString.getFirst());
		assertEquals("Montgomery", linkedString.retrieveFirstElement());
		assertEquals("College",linkedString.getFirst());
		
	}

	@Test
	public void testRetrieveLastElement() {
		assertEquals(c, linkedPerson.getLast());
		linkedPerson.addToEnd(d);
		assertEquals(d, linkedPerson.getLast());
		assertEquals(d, linkedPerson.retrieveLastElement());
		assertEquals(c,linkedPerson.getLast());
		
		assertEquals("College", linkedString.getLast());
		linkedString.addToEnd("Raptor");
		assertEquals("Raptor", linkedString.getLast());
		assertEquals("Raptor", linkedString.retrieveLastElement());
		assertEquals("College",linkedString.getLast());
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
