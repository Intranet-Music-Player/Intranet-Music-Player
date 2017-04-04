package com.example.entities;


 import javax.persistence.Column;
import javax.persistence.EmbeddedId;
 import javax.persistence.Entity;
 
 @Entity
 public class Valoration {
	 @EmbeddedId
	 ValorationPk id;
	 @Column(name = "VALORATION", nullable = false)
	 private Long points;
	 
	 public ValorationPk getId() {
		 return id;
	 }
	 public void setId(ValorationPk id) {
		 this.id = id;
	 }
	 public Long getPoints() {
		 return points;
	 }
	 public void setPoints(Long points) {
		 this.points = points;
	 }	
 }