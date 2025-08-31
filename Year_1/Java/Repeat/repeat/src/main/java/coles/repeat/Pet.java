package coles.repeat;

// Pet

// Represents a virtual pet that can be managed inside an AdoptionCenter.
// Each pet has attributes like name, species, age, hunger, mood, weight, 
// and adoption status. 

public class Pet implements Comparable<Pet>
{

    // Attributes
    private String name;         // Pet's name (unique identifier in most cases)
    private String species;      // Type of animal (e.g., Dog, Cat, Rabbit)
    private int age;             // Age of the pet in years
    private int hungerLevel;     // Hunger (0 = not hungry, 100 = starving)
    private String mood;         // Current mood (e.g., Happy, Playful, Grumpy)
    private double weight;       // Weight of the pet in kilograms
    private boolean isAdopted;   // Flag indicating if the pet has been adopted

    // Constructor
    
    // Creates a new Pet object with the provided details.
    // By default, pets are created as not adopted.
    
    public Pet(String name, String species, int age, int hungerLevel, String mood, double weight)
    {
        this.name = name;
        this.species = species;
        this.age = age;
        this.hungerLevel = hungerLevel;
        this.mood = mood;
        this.weight = weight;
        this.isAdopted = false; // default state
    }

    // Getters
    public String getName() { return name; }
    public String getSpecies() { return species; }
    public int getAge() { return age; }
    public int getHungerLevel() { return hungerLevel; }
    public String getMood() { return mood; }
    public double getWeight() { return weight; }
    public boolean isAdopted() { return isAdopted; }

    // Setters
    public void setHugerLevel(int hungerLevel) { this.hungerLevel = hungerLevel; }
    public void setMood(String mood) { this.mood = mood; }
    public void setWeight(double weight) { this.weight = weight; }
    public void setAdopted(boolean adopted) { isAdopted = adopted; }

    // Behavior Methods
    
    // Feed the pet: decreases hunger and improves mood.
    // Hunger will never go below 0.
    
    public void feed()
    {
        hungerLevel = Math.max(0, hungerLevel - 20); // Decrease hunger safely
        mood = "Happy"; // Feeding usually improves mood
    }

    // Play with the pet: increases hunger slightly and changes mood.
    // Hunger will never go above 100.
    
    public void play()
    {
        mood = "Playful"; // Playing changes mood
        hungerLevel = Math.min(100, hungerLevel + 10); // Playing makes the pet a bit hungrier
    }

    // Utility Methods
    @Override
    public String toString()
    {
        return name + " (" + species + ", Age: " + age + ", Hunger: " + hungerLevel +
                ", Mood: " + mood + ", Weight: " + weight + "kg, Adopted: " + isAdopted + ")";
    }

    // Two pets are considered equal if they share the same name and species.
    // Case-insensitive comparison for consistency.
    //
    @Override
    public boolean equals(Object obj)
    {
        if (this == obj) return true;              // Same reference
        if (obj == null || getClass() != obj.getClass()) return false; 
        Pet other = (Pet) obj;
        return name.equalsIgnoreCase(other.name) && species.equalsIgnoreCase(other.species);
    }
    
    // Compares pets by age (default sorting behavior).
    // @return negative if this pet is younger, positive if older, 0 if same age.
    
    @Override
    public int compareTo(Pet other)
    {
        return Integer.compare(this.age, other.age);
    }
}