package MyDeque;

public class MyDeque<E> implements Deque<E>  {
	
	private MyLinkedList<E> linker = new MyLinkedList<E>();

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return linker.size();
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return linker.isEmpty();
	}

	@Override
	public E getFirst() {
		// TODO Auto-generated method stub
		return linker.getFirst();
	}

	@Override
	public E getLast() {
		// TODO Auto-generated method stub
		return linker.getLast();
	}

	@Override
	public void addFirst(E e) {
		// TODO Auto-generated method stub
		linker.addFirst(e);
	}

	@Override
	public void addLast(E e) {
		linker.addLast(e);
		
	}

	@Override
	public E removeFirst() {
		// TODO Auto-generated method stub
		E temp = linker.getFirst();
		linker.removeFirst();
		return temp;
	}

	@Override
	public E removeLast() {
		// TODO Auto-generated method stub
		E temp = linker.getLast();
		linker.removeLast();
		return temp;
	}
	
	public void clear() {
		linker.clear();
	}
	
	public java.util.Iterator<E> iterator() {
		return linker.iterator();
	}

	@Override
	public String toString() {
		return String.format("%s", linker);
	}
	
	
	

}
