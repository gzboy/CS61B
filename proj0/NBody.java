public class NBody {
	public static double readRadius(String s) {
		In in = new In(s);

		int number = in.readInt();
		double radius = in.readDouble();

		return radius;
	}
	public static Planet[] readPlanets(String s) {
		In in = new In(s);

		int number = in.readInt();
		double radius = in.readDouble();

		Planet[] planets = new Planet[number];

		int i = 0;
		while (i < number) {
			double xxpos = in.readDouble();
			double yypos = in.readDouble();
			double xxvel = in.readDouble();
			double yyvel = in.readDouble();
			double mass = in.readDouble();
			String img = in.readString();
			planets[i] = new Planet(xxpos, yypos, xxvel, yyvel, mass, img);
			i ++ ;
		}
		return planets;
	}

	public static void main(String[] args) {
		double T = Double.parseDouble(args[0]);
		double dt = Double.parseDouble(args[1]);
		String filename = args[2];
		double radius = readRadius(filename);
		Planet[] planets = readPlanets(filename);
		//public static String imageToDraw = "./images/starfield.jpg"

		StdDraw.setScale(-radius, radius);
		StdDraw.clear();
		StdDraw.picture(0, 0, "./images/starfield.jpg");

		for(Planet p: planets) {
			p.draw();
		}

		StdDraw.enableDoubleBuffering();
		double time = 0;
		while (time <= T) {
			double[] xForces = new double[planets.length];
			double[] yForces = new double[planets.length];
			
			for(int i = 0; i < planets.length; i++) {
				xForces[i] = planets[i].calcNetForceExertedByX(planets);
				yForces[i] = planets[i].calcNetForceExertedByY(planets);
			}

			for(int i = 0; i < planets.length; i++) {
				planets[i].update(dt, xForces[i], yForces[i]);
			}

			StdDraw.picture(0, 0, "./images/starfield.jpg");
			for(Planet p: planets) {
			p.draw();
		}
			StdDraw.show();
			StdDraw.pause(10);

			time += dt;
		}
		StdOut.printf("%d\n", planets.length);
	StdOut.printf("%.2e\n", radius);
	for (int i = 0; i < planets.length; i++) {
    StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                  planets[i].xxPos, planets[i].yyPos, planets[i].xxVel,
                  planets[i].yyVel, planets[i].mass, planets[i].imgFileName);   
}

	}
}