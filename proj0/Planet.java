public class Planet {
	public double xxPos; // x position 
	public double yyPos; // y position 
	public double xxVel;
	public double yyVel;
	public double mass;
	public String imgFileName;

	public Planet(double xP, double yP, double xV, double yV, double m, String img){
		xxPos = xP;
		yyPos = yP;
		xxVel = xV;
		yyVel = yV;
		mass = m;
		imgFileName = img;
	}

	public Planet(Planet p){          // Construnctor 
		xxPos = p.xxPos;
		yyPos = p.yyPos;
		xxVel = p.xxVel;
		yyVel = p.yyVel;
		mass = p.mass;
		imgFileName = p.imgFileName;
	}

	public double calcDistance(Planet p){
		double distx = p.xxPos-xxPos;
		double disty = p.yyPos-yyPos;
		return Math.sqrt(distx*distx + disty*disty);
	}


	private final static double gravity =  6.67e-11; // set gravity constant
	public double calcForceExertedBy(Planet p){
		return gravity*(p.mass*mass)/(calcDistance(p)*calcDistance(p));
	}

	public double calcForceExertedByX(Planet p){
		return calcForceExertedBy(p)*(p.xxPos-xxPos)/calcDistance(p);
	}

	public double calcForceExertedByY(Planet p){
		return calcForceExertedBy(p)*(p.yyPos-yyPos)/calcDistance(p);
	}

    public double calcNetForceExertedByX(Planet[] allPlanets){
		double netX = 0;
    	for(int i=0; i<allPlanets.length; i++){
			if(this.equals(allPlanets[i])){continue;}
			netX = netX + calcForceExertedByX(allPlanets[i]);
		}
    	return netX;
	}

	public double calcNetForceExertedByY(Planet[] allPlanets){
		double netY = 0;
		for(int i=0; i<allPlanets.length; i++){
			if(this.equals(allPlanets[i])){continue;}
			netY = netY + calcForceExertedByY(allPlanets[i]);
		}
		return netY;
	}

	// dt is the time that force act on the object
	// fx is the force in the x direction
	// fy is the force in the y direction
	public void update(double dt,double fx ,double fy){
		// First step is to calculated the accelerate
		double accX = fx/this.mass;
		double accY = fy/this.mass;
		// Second step is to calculate the new velocity
		this.xxVel = this.xxVel + accX*dt;
		this.yyVel = this.yyVel + accY*dt;
		// Third step is to calculate the new position
		this.xxPos = this.xxPos + this.xxVel*dt;
		this.yyPos = this.yyPos + this.yyVel*dt;
	}

	public void draw(){
		StdDraw.picture(this.xxPos,this.yyPos,"images/" + this.imgFileName);
	}


}