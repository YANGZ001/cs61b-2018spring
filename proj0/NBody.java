//package examples;

public class NBody {

    public static void main(String[] args) {
        double T = Double.parseDouble(args[0]);
        double dt = Double.parseDouble(args[1]);
        String filename = args[2];
        Planet[] planets = readPlanets(filename);
        double radius = readRadius(filename);

        StdDraw.setScale(-radius, radius);
        StdDraw.clear();
        StdDraw.picture(0,0, "images/starfield.jpg");
//        StdDraw.show();
//        StdDraw.pause(2000);

        for (Planet p: planets){
            p.draw();
        }

        StdDraw.enableDoubleBuffering();
        double time = 0;
        double[] xForces = new double[planets.length];
        double[] yForces = new double[planets.length];
        while (time < T){
            for (int i = 0; i< planets.length ; i++){
                xForces[i] = planets[i].calcNetForceExertedByX(planets);
                yForces[i] = planets[i].calcNetForceExertedByY(planets);
            }

            for (int i = 0; i< planets.length ; i++){
                planets[i].update(time, xForces[i], yForces[i]);
            }

            StdDraw.clear();
            StdDraw.picture(0,0, "images/starfield.jpg");

            for (int i = 0; i< planets.length ; i++){
                planets[i].draw();
            }

            StdDraw.show();
            StdDraw.pause(100);
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

    public static double readRadius(String s){
        In in = new In(s);
        int firstItemInFile = in.readInt();
        double secondItemInFile = in.readDouble();
        return secondItemInFile;
    }

    public static Planet[] readPlanets(String s){
        In in = new In(s);
        int firstItemInFile = in.readInt();
        double secondItemInFile = in.readDouble();
        Planet[] Planets = new Planet[firstItemInFile];
//        int i = 0;
//        while (!in.isEmpty()){
        for (int i = 0; i < firstItemInFile ;i++){
            double xxPos = in.readDouble();
            double yyPos = in.readDouble();
            double xxVel = in.readDouble();
            double yyVel = in.readDouble();
            double mass = in.readDouble();
            String name = in.readString();
            Planet p = new Planet(xxPos, yyPos, xxVel, yyVel, mass, name);
            Planets[i] = p;
        }
        return Planets;
    }
}
