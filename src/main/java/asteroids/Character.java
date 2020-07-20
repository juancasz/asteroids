/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package asteroids;

/**
 *
 * @author juan
 */
import javafx.geometry.Point2D;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Shape;

public abstract class Character {
    
    private Polygon character;
    private Point2D movement;
    private boolean alive;
    
    public Character(Polygon polygon,int x,int y){
        this.character = polygon;
        this.character.setTranslateX(x);
        this.character.setTranslateY(y);
        
        this.movement = new Point2D(0,0);
        this.alive = true;
    }
    
    public Polygon getCharacter(){
        return this.character;
    }
    
    public void turnLeft(){
        this.character.setRotate(this.character.getRotate()-2);
    }
    
    public void turnRight(){
        this.character.setRotate(this.character.getRotate()+2);
    }
    
    public void move(){
        this.character.setTranslateX(this.character.getTranslateX()+this.movement.getX());
        this.character.setTranslateY(this.character.getTranslateY()+this.movement.getY());
        
        if(this.character.getTranslateX() < 0){
            this.character.setTranslateX(AsteroidsApplication.WIDTH + this.character.getTranslateX());
        }
        
        if(this.character.getTranslateY() < 0){
            this.character.setTranslateY(AsteroidsApplication.HEIGHT + this.character.getTranslateY());
        }
        
        if(this.character.getTranslateX() > AsteroidsApplication.WIDTH ){
            this.character.setTranslateX(this.character.getTranslateX()-AsteroidsApplication.WIDTH );
        }
        
        if(this.character.getTranslateY() > AsteroidsApplication.HEIGHT ){
            this.character.setTranslateY(this.character.getTranslateY()-AsteroidsApplication.HEIGHT);
        }
    }
    
    public void accelerate(){
        double changeX = Math.cos(Math.toRadians(this.character.getRotate()));
        double changeY = Math.sin(Math.toRadians(this.character.getRotate()));
        
        changeX *= 0.01;
        changeY *= 0.01;
        
        this.movement = this.movement.add(changeX,changeY);
    }
    
    public boolean collide(Character other){
        Shape collisionArea = Shape.intersect(this.character, other.getCharacter());
        return collisionArea.getBoundsInLocal().getWidth() != -1;
    }
    
    public Point2D getMovement(){
        return this.movement;
    }
    
    public void setMovement(Point2D movement){
        this.movement = movement;
    }
    
    public void setAlive(boolean condition){
        this.alive = condition;
    }
    
    public boolean isAlive(){
        return this.alive;
    }
}
