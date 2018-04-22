package org.aviral.springcore;

import java.util.List;

public class Triangle {

	private Point pointA;
	private List<Point> points;
	private Point pointC;
	
	
	public Point getPointA() {
		return pointA;
	}


	public void setPointA(Point pointA) {
		this.pointA = pointA;
	}


	public List<Point> getPoints() {
		return points;
	}


	public void setPoints(List<Point> points) {
		this.points = points;
	}


	public Point getPointC() {
		return pointC;
	}


	public void setPointC(Point pointC) {
		this.pointC = pointC;
	}


	public void draw() {
		System.out.println("Point A is ( " + getPointA().getX() + "," + getPointA().getY() +")");
		System.out.println("Point C is ( " + getPointC().getX() + "," + getPointC().getY() +")");
		for(Point point : points) {
			System.out.println("Point is ( " + point.getX() + "," + point.getY() +")");
		}
	}
	
}
