package com.vvtalico.workshopmongo.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.vvtalico.workshopmongo.domain.Post;

@Repository
public interface PostRepository extends MongoRepository<Post, String> {
	
	List<Post> findByTitleContainingIgnoreCase(String text);
	
	@Query("{ 'title': { $regex:?0, $options: 'i' } }")
	List<Post> searchTitle(String text);
	
	@Query("{ $and: [{ $or: [ { 'title': { $regex:?0, $options: 'i' } }, { 'body': { $regex:?0, $options: 'i' } }, { 'comments.text': { $regex:?0, $options: 'i' } } ] }, {date: {$gte: ?1} }  , { date: { $lte: ?2} } ] }")
	List<Post> FullSearch(String text, Date minDate, Date maxDate);

}
