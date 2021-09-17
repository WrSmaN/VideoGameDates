/**
 * @author Wade - wrshafer3
 * CIS175 - Fall 2021
 * Sep 16, 2021
 */
package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;



@Entity
@Table(name="videogames")
public class VideoGameItems {
	
	@Id
	@GeneratedValue
	@Column(name="ID")
	private int id;
	@Column(name="gameName")
	private String gameName;
	@Column(name="ITEM")
	private int creationYear;
	
	
	public VideoGameItems() {
		super();
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getGameName() {
		return gameName;
	}
	public void setGameName(String gameName) {
		this.gameName = gameName;
	}
	public int getCreationYear() {
		return creationYear;
	}
	public void setCreationYear(int creationYear) {
		this.creationYear = creationYear;
	}
	
	public VideoGameItems(String gameName, int creationYear) {
		this.gameName = gameName;
		this.creationYear = creationYear;
	}
	
	public String returnItemDetails() {
		return this.gameName + ": " + this.creationYear;
	}
}
