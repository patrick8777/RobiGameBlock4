package model;

import processing.core.PApplet;
import processing.core.PShape;
import processing.core.PVector;

public class Star {
	private PShape starShape;
	public PVector position;
	private PApplet applet;

	public Star(PVector position, PApplet applet) {
		this.position = position;
		this.applet = applet;
		starShape = applet.createShape();
		starShape.beginShape();
		starShape.vertex(0, -50);
		starShape.vertex(14, -20);
		starShape.vertex(47, -15);
		starShape.vertex(23, 7);
		starShape.vertex(29, 40);
		starShape.vertex(0, 25);
		starShape.vertex(-29, 40);
		starShape.vertex(-23, 7);
		starShape.vertex(-47, -15);
		starShape.vertex(-14, -20);
		starShape.endShape(PApplet.CLOSE);
		starShape.setFill(0xFFFFFF00);
		starShape.scale(0.5f);
	}
	
	public void draw() {
		applet.shape(starShape, this.position.x, position.y);
		starShape.rotate(0.02f);
	}
	
	public float getX() {
		return position.x;
	}
	public float getY() {
		return position.y;
	}
}
