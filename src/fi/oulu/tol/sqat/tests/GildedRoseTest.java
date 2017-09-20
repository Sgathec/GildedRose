package fi.oulu.tol.sqat.tests;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import fi.oulu.tol.sqat.GildedRose;
import fi.oulu.tol.sqat.Item;

public class GildedRoseTest {

// Example scenarios for testing
//   Item("+5 Dexterity Vest", 10, 20));
//   Item("Aged Brie", 2, 0));
//   Item("Elixir of the Mongoose", 5, 7));
//   Item("Sulfuras, Hand of Ragnaros", 0, 80));
//   Item("Backstage passes to a TAFKAL80ETC concert", 15, 20));
//   Item("Conjured Mana Cake", 3, 6));

	@Test
	public void testUpdateEndOfDay_AgedBrie_Quality_10_11() {
		// Arrange
		GildedRose store = new GildedRose();
		store.addItem(new Item("Aged Brie", 2, 10) );
		
		// Act
		store.updateEndOfDay();
		
		// Assert
		List<Item> items = store.getItems();
		Item itemBrie = items.get(0);
		assertEquals(11, itemBrie.getQuality());
	}
    
	@Test
	public void testUpdateEndOfDay_itemQualityDecreasedTwiceAsFast() {
		//Arrange
		GildedRose store = new GildedRose();
		store.addItem(new Item("Reinforced Barrel", -1, 5));
		
		//Act
		store.updateEndOfDay();
		
		//Assert
		Item item = store.getItems().get(0);
		assertEquals(3, item.getQuality());
	}
	
	@Test
	public void testUpdateEndOfDay_backstagePassQualityIncreasesBy2WhenSellinIs10orLess() {
		//Arrange
		GildedRose store = new GildedRose();
		store.addItem(new Item("Backstage passes to a TAFKAL80ETC concert", 10, 5));
		
		//Act
		store.updateEndOfDay();
		
		//Assert
		Item item = store.getItems().get(0);
		assertEquals(7, item.getQuality());
	}
	
	@Test
	public void testUpdateEndOfDay_qualityIsNeverNegative() {
		//Arrange
		GildedRose store = new GildedRose();
		store.addItem(new Item("Ugly Hat", 30, 0));
		
		//Act
		store.updateEndOfDay();
		
		//Assert
		Item item = store.getItems().get(0);
		assertEquals(0, item.getQuality());
	}
	
	@Test
	public void testUpdateEndOfDay_qualityIsNeverOver50() {
		//Arrange
		GildedRose store = new GildedRose();
		store.addItem(new Item("Aged Brie", 30, 50));
		
		//Act
		store.updateEndOfDay();
		
		//Assert
		Item item = store.getItems().get(0);
		assertEquals(50, item.getQuality());
	}
	
	
	@Test
	public void testUpdateEndOfDay_backstagePassQualityIncreasesBy3WhenSellinIs5orLess() {
		//Arrange
		GildedRose store = new GildedRose();
		store.addItem(new Item("Backstage passes to a TAFKAL80ETC concert", 5, 5));
		
		//Act
		store.updateEndOfDay();
		
		//Assert
		Item item = store.getItems().get(0);
		assertEquals(8, item.getQuality());
	}
	
	@Test
	public void testUpdateEndOfDay_sulfurasDoesntLowerInQuality() {
		//Arrange
		GildedRose store = new GildedRose();
		store.addItem(new Item("Sulfuras, Hand of Ragnaros", 10, 80));
		
		//Act
		store.updateEndOfDay();
		
		//Assert
		Item item = store.getItems().get(0);
		assertEquals(80, item.getQuality());
	}
	
	@Test
	public void testUpdateEndOfDay_sulfurasDoesntLowerInSellinValue() {
		//Arrange
		GildedRose store = new GildedRose();
		store.addItem(new Item("Sulfuras, Hand of Ragnaros", 10, 80));
		
		//Act
		store.updateEndOfDay();
		
		//Assert
		Item item = store.getItems().get(0);
		assertEquals(10, item.getSellIn());
	}
	
	@Test
	public void testUpdateEndOfDay_itemQualityLoweredBy1() {
		//Arrange
		GildedRose store = new GildedRose();
		store.addItem(new Item("Colorful Hat", 20, 15));
		
		//Act
		store.updateEndOfDay();
		
		//Assert
		Item item = store.getItems().get(0);
		assertEquals(14, item.getQuality());
	}
	
	@Test
	public void testUpdateEndOfDay_itemSellinLoweredBy1() {
		//Arrange
		GildedRose store = new GildedRose();
		store.addItem(new Item("Blue Shoes", 25, 5));
		
		//Act
		store.updateEndOfDay();
		
		//Assert
		Item item = store.getItems().get(0);
		assertEquals(24, item.getSellIn());
	}
	
}
