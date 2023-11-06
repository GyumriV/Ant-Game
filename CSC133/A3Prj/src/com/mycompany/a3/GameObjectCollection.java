package com.mycompany.a3;

import java.util.ArrayList;

//Creates a collection of game Objects with methods to add, get and iterate
public class GameObjectCollection implements ICollection{

	private ArrayList<GameObject> gameObjects;
	
	public GameObjectCollection() {
		gameObjects = new ArrayList<>();
	}
	@Override
	public void add(GameObject object) {
		gameObjects.add(object);
	}
	
	public IIterator getIterator() {
		return new GameObjectIterator();
	}
	
	private class GameObjectIterator implements IIterator {
		private int index;
		@Override
		public boolean hasNext() {
			return index < gameObjects.size();
		}
		@Override
		public GameObject getNext() {
			if(hasNext()) {
				GameObject gameObject = gameObjects.get(index);
				index++;
				return gameObject;
			}
			return null;
		}
		
	}
}
