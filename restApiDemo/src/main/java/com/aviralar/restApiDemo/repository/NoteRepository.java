package com.aviralar.restApiDemo.repository;


import com.aviralar.restApiDemo.model.Note;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/* repository performs simple functions like 
 * save(), findOne(), findAll(), count(), delete() etc. */
@Repository
public interface NoteRepository extends JpaRepository<Note,Long> {

}
