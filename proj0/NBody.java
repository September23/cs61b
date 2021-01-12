public class NBody {
    @Deprecated
    public static double readRadius(String filepath){
        In in = new In(filepath);
        int planetsnumbers = in.readInt();
        double radiusofuniverse = in.readDouble();
        return radiusofuniverse;
    }
    @Deprecated
    public static Planet[] readPlanets(String filepath){
        In in = new In(filepath);

        int planetsnumbers = in.readInt();
        Planet[] planets = new Planet[planetsnumbers];
        double rediusofuniverse = in.readDouble();
        for(int i = 0; i < planetsnumbers; i++){
            double xxPos = in.readDouble();
            double yyPos = in.readDouble();
            double xxVel = in.readDouble();
            double yyVel = in.readDouble();
            double mass = in.readDouble();
            String imgFileName = in.readString();

            planets[i] = new Planet(xxPos, yyPos, xxVel, yyVel, mass, imgFileName);

        }
        return planets;
    }

    public static void main(String[] args){
        double T = Double.parseDouble(args[0]);
        double dt = Double.parseDouble(args[1]);
        String filename = args[2];

        double radius = readRadius("./data/planets.txt");
        Planet[] planets = readPlanets("./data/planets.txt");


        String Background = "./images/starfield.jpg";
        StdDraw.setScale(-1.5*radius, 1.5*radius);
        StdDraw.clear();
        StdDraw.enableDoubleBuffering();


        StdDraw.picture(0, 0, Background);

        for(int i = 0; i < planets.length; i++){
            planets[i].draw();
        }

        double time = 0;
        while(time<T){
            time = time + dt;
            double[] xForces = new double[planets.length];
            double[] yForces = new double[planets.length];

            for(int i = 0; i<planets.length;i++){
                xForces[i] = planets[i].calcNetForceExertedByX(planets);
                yForces[i] = planets[i].calcNetForceExertedByY(planets);
            }

            for (int i = 0; i < planets.length; i++) {
                planets[i].update(dt, xForces[i], yForces[i]);
            }

            Background = "./images/starfield.jpg";

            for(int i = 0; i < planets.length; i++){
                planets[i].draw();
            }

            StdDraw.show();
            StdDraw.pause(10);
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
