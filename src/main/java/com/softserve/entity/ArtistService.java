package com.softserve.entity;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

public class ArtistService {
	private EntityManager em;

	public ArtistService(EntityManager em) {
		this.em = em;
	}

	public Artist createArtist(int id, String name, String genre) {
		Artist artist = new Artist(id, name, genre);
		em.persist(artist);
		return artist;
	}

	public void removeArtist(int id) {
		Artist artist = em.find(Artist.class, id);
		if (artist != null) {
			em.remove(artist);
		}
	}

	public Artist changeArtistGenre(int id, String genre) {
		Artist artist = em.find(Artist.class, id);
		if (artist != null) {
			artist.setGenre(genre);
		}
		return artist;
	}

	public Artist findArtist(int id) {
		return em.find(Artist.class, id);
	}

	public List<Artist> findAllArtists() {
		TypedQuery<Artist> query = em.createQuery("SELECT a FROM Artist a", Artist.class);
		return query.getResultList();
	}
	
}
