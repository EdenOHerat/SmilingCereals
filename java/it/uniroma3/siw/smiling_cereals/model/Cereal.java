package it.uniroma3.siw.smiling_cereals.model;

import java.util.List;
import java.util.Objects;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Cereal {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	private String name;
	private String flavor;
	private String description;
	private String fileName; //for the image
	
	/*This is for the smiles (rating)*/
	private float avgSmiles = 0;
	private int totalVotes = 0;
	private long totalSmiles = 0;
	
	/*Review*/
	@OneToMany(mappedBy = "cereal", cascade = CascadeType.ALL)
	private List<Review> reviews;
	
	public Cereal() {}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFlavor() {
		return flavor;
	}

	public void setFlavor(String flavor) {
		this.flavor = flavor;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public float getAvgSmiles() {
		return avgSmiles;
	}

	public void setAvgSmiles(int avgSmiles) {
		this.avgSmiles = avgSmiles;
	}

	@Override
	public int hashCode() {
		return Objects.hash(description, flavor, name);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cereal other = (Cereal) obj;
		return Objects.equals(description, other.description) && Objects.equals(flavor, other.flavor)
				&& Objects.equals(name, other.name);
	}
	
	// getter/setter per aggiornare media
    public void addRating(float smiles) {
        totalSmiles += smiles;
        totalVotes++;
        avgSmiles = (float)(totalSmiles/totalVotes);
    }

	public List<Review> getReviews() {
		return reviews;
	}

	public void setReviews(List<Review> reviews) {
		this.reviews = reviews;
	}
	
	
}
