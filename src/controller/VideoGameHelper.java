/**
 * @author Wade - wrshafer3
 * CIS175 - Fall 2021
 * Sep 16, 2021
 */
package controller;

import model.VideoGameItems;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

public class VideoGameHelper {

	
	EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("VideoGameDates");
	
	public void insertItem(VideoGameItems vgi) {
		
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		em.persist(vgi);
		em.getTransaction().commit();
		em.close();
	}
	
	public List<VideoGameItems> showAllItems(){
		EntityManager em = emfactory.createEntityManager();
		List<VideoGameItems> allItems = em.createQuery("SELECT i FROM VideoGameItems i").getResultList();
		return allItems;
	}
	
	public void deleteItem(VideoGameItems toDelete) {
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		TypedQuery<VideoGameItems> typedQuery =  em.createQuery("select vgi from VideoGameItems vgi where vgi.gameName = :selectedGame and vgi.creationYear = :selectedYear", VideoGameItems.class);
		
		typedQuery.setParameter("selectedGame", toDelete.getGameName());
		typedQuery.setParameter("selectedYear",toDelete.getCreationYear());

		typedQuery.setMaxResults(1);
		
		VideoGameItems result = typedQuery.getSingleResult();
		
		em.remove(result);
		em.getTransaction().commit();
		em.close();
	}
	
	public void updateItem(VideoGameItems toEdit) {
		// TODO Auto-generated method stub
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		em.merge(toEdit);
		em.getTransaction().commit();
		em.close();
	}
	
	public List<VideoGameItems> searchForItemByStore(String gameName) {
		// TODO Auto-generated method stub
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		TypedQuery<VideoGameItems> typedQuery = em.createQuery("select vgi from VideoGameItems vgi where vgi.gameName = :selectedGame", VideoGameItems.class);
		typedQuery.setParameter("selectedGame", gameName);
		List<VideoGameItems> foundItems = typedQuery.getResultList();
		em.close();
		
		return foundItems;
	}
	
	public List<VideoGameItems> searchForItemByItem(String year) {
		// TODO Auto-generated method stub
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		TypedQuery<VideoGameItems> typedQuery = em.createQuery("select vgi from VideoGameItems vgi where vgi.gameName = :selectedGame", VideoGameItems.class);
		typedQuery.setParameter("selectedGame", year);
		List<VideoGameItems> foundItems = typedQuery.getResultList();
		em.close();
		
		return foundItems;
	}
	
	public List<VideoGameItems> searchForItemByItemInt(int year) {
		// TODO Auto-generated method stub
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		TypedQuery<VideoGameItems> typedQuery = em.createQuery("select vgi from VideoGameItems vgi where vgi.creationYear = :selectedYear", VideoGameItems.class);
		typedQuery.setParameter("selectedYear", year);
		List<VideoGameItems> foundItems = typedQuery.getResultList();
		em.close();
		
		return foundItems;
	}
	
	public VideoGameItems searchForItemById(int idToEdit) {
		// TODO Auto-generated method stub
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		VideoGameItems found = em.find(VideoGameItems.class, idToEdit);
		em.close();
		return found;
	}
	
	public void cleanUp() {
		emfactory.close();
	}
}
