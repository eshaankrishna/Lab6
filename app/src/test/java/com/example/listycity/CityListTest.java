package com.example.listycity;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CityListTest {

    /**
     * Creates a mock CityList with one city
     * @return A CityList containing one city
     */
    private CityList mockCityList() {
        CityList cityList = new CityList();
        cityList.add(mockCity());
        return cityList;
    }

    /**
     * Creates a mock City object
     * @return A City object (Edmonton, Alberta)
     */
    private City mockCity() {
        return new City("Edmonton", "Alberta");
    }

    /**
     * Test adding cities to the list
     */
    @Test
    void testAdd() {
        CityList cityList = mockCityList();
        assertEquals(1, cityList.getCities().size());

        City city = new City("Regina", "Saskatchewan");
        cityList.add(city);

        assertEquals(2, cityList.getCities().size());
        assertTrue(cityList.getCities().contains(city));
    }

    /**
     * Test that adding a duplicate city throws an exception
     */
    @Test
    void testAddException() {
        CityList cityList = mockCityList();
        City city = new City("Yellowknife", "Northwest Territories");
        cityList.add(city);

        assertThrows(IllegalArgumentException.class, () -> {
            cityList.add(city);
        });
    }

    /**
     * Test getting cities in sorted order
     */
    @Test
    void testGetCities() {
        CityList cityList = mockCityList();

        // Check if the first city is the mock city
        assertEquals(0, mockCity().compareTo(cityList.getCities().get(0)));

        // Add another city that comes before Edmonton alphabetically
        City city = new City("Charlottetown", "Prince Edward Island");
        cityList.add(city);

        // Now Charlottetown should be at position 0
        assertEquals(0, city.compareTo(cityList.getCities().get(0)));
        // Edmonton should be at position 1
        assertEquals(0, mockCity().compareTo(cityList.getCities().get(1)));
    }

    /**
     * Test hasCity method returns true for existing city
     */
    @Test
    void testHasCity() {
        CityList cityList = mockCityList();
        City city = new City("Edmonton", "Alberta");

        // The city should be in the list
        assertTrue(cityList.hasCity(city));
    }

    /**
     * Test hasCity method returns false for non-existing city
     */
    @Test
    void testHasCityNotFound() {
        CityList cityList = mockCityList();
        City notInList = new City("Vancouver", "British Columbia");

        // This city is not in the list
        assertFalse(cityList.hasCity(notInList));
    }

    /**
     * Test deleting a city from the list
     */
    @Test
    void testDelete() {
        CityList cityList = mockCityList();
        City city = mockCity();

        // Verify the city is in the list
        assertEquals(1, cityList.countCities());
        assertTrue(cityList.hasCity(city));

        // Delete the city
        cityList.delete(city);

        // Verify the city is no longer in the list
        assertEquals(0, cityList.countCities());
        assertFalse(cityList.hasCity(city));
    }

    /**
     * Test that deleting a non-existing city throws an exception
     */
    @Test
    void testDeleteException() {
        CityList cityList = mockCityList();
        City city = new City("Vancouver", "British Columbia");

        // This city is not in the list, so deleting should throw an exception
        assertThrows(IllegalArgumentException.class, () -> {
            cityList.delete(city);
        });
    }

    /**
     * Test counting cities in the list
     */
    @Test
    void testCountCities() {
        CityList cityList = new CityList();

        // Initially empty
        assertEquals(0, cityList.countCities());

        // Add one city
        cityList.add(mockCity());
        assertEquals(1, cityList.countCities());

        // Add another city
        City city = new City("Toronto", "Ontario");
        cityList.add(city);
        assertEquals(2, cityList.countCities());

        // Delete a city
        cityList.delete(city);
        assertEquals(1, cityList.countCities());
    }
}