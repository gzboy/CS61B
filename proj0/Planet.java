public class Planet {
	public double xxPos;
	public double yyPos;
	public double xxVel;
	public double yyVel;
	public double mass;
	public String imgFileName;
	public static final double G = 6.67*Math.pow(10, -11);

	public Planet(double xP, double yP, double xV, double yV, double m, String img) {
		this.xxPos = xP;
		this.yyPos = yP;
		this.xxVel = xV;
		this.yyVel = yV;
		this.mass = m;
		this.imgFileName = img;
	}

	public Planet(Planet p) {
		this.xxPos = p.xxPos;
		this.yyPos = p.yyPos;
		this.xxVel = p.xxVel;
		this.yyVel = p.yyVel;
		this.mass = p.mass;
		this.imgFileName = p.imgFileName;
	}

	public double calcDistance (Planet x) {
		return Math.sqrt((this.xxPos - x.xxPos)*(this.xxPos - x.xxPos) + (this.yyPos - x.yyPos)*(this.yyPos - x.yyPos));
	}

	public double calcForceExertedBy (Planet p) {
		double a = this.calcDistance(p);
		return G*this.mass*p.mass/(a*a);
	}

	public double calcForceExertedByX (Planet p) {
		return this.calcForceExertedBy(p) * (p.xxPos - this.xxPos) / this.calcDistance(p);
	}

	public double calcForceExertedByY (Planet p) {
		return this.calcForceExertedBy(p) * (p.yyPos - this.yyPos) / this.calcDistance(p);
	}

	public double calcNetForceExertedByX (Planet[] p) {
		double netForceByX = 0;
		for (Planet a: p) {
			if (this.equals(a)) continue;
			netForceByX += this.calcForceExertedByX(a);
		}
		return netForceByX;
	}

	public double calcNetForceExertedByY (Planet[] p) {
		double netForceByY = 0;
		for (Planet a: p) {
			if (this.equals(a)) continue;
			netForceByY += this.calcForceExertedByY(a);
		}
		return netForceByY;
	}

	public void update(double dt, double fX, double fY) {
		this.xxVel = this.xxVel + dt*(fX/this.mass);
		this.yyVel = this.yyVel + dt*(fY/this.mass);
		this.xxPos = this.xxPos + dt*this.xxVel;
		this.yyPos = this.yyPos + dt*this.yyVel;
	}

	public void draw() {
		
		StdDraw.picture(xxPos, yyPos, "./images/" + imgFileName);
	}
}