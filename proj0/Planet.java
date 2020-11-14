public class Planet {
    public double xxPos;
    public double yyPos;
    public double xxVel;
    public double yyVel;
    public double mass;
    public String imgFileName;

    public static final double G0 = 6.67e-11;

    public Planet(double xP, double yP, double xV,
                  double yV, double m, String img){
        this.xxPos = xP;
        this.yyPos = yP;
        this.xxVel = xV;
        this.yyVel = yV;
        this.mass = m;
        this.imgFileName = img;
    }

    public Planet(Planet p){
        this.xxPos = p.xxPos;
        this.yyPos = p.yyPos;
        this.xxVel = p.xxVel;
        this.yyVel = p.yyVel;
        this.mass = p.mass;
        this.imgFileName = p.imgFileName;
    }

    public void draw(){
//        StdDraw.setScale(-radius, radius);
//        StdDraw.clear();
        StdDraw.picture(xxPos,yyPos, "images/"+ imgFileName);
        StdDraw.show();
//        StdDraw.pause(2000);
    }

    public double calcDistance(Planet p){
        double dx = xxPos - p.xxPos;
        double dy = yyPos - p.yyPos;
//        return Math.sqrt(dx * dx + dy * dy);
        return Math.sqrt(Math.pow(dx, 2) + Math.pow(dy, 2));
    }

    public double calcForceExertedBy(Planet p){
        double distance = this.calcDistance(p);
        return G0*mass*p.mass/Math.pow(distance, 2);
    }

    public double calcForceExertedByX(Planet p){
        double force = calcForceExertedBy(p);
        double distance = calcDistance(p);
        double forceX = force*(p.xxPos - xxPos)/distance;
        return  forceX;
    }

    public double calcForceExertedByY(Planet p){
        double force = calcForceExertedBy(p);
        double distance = calcDistance(p);
        double forceY = force*(p.yyPos - yyPos)/distance;
        return  forceY;
    }

    public double calcNetForceExertedByX(Planet[] allPlanets){
//        Planet[] allPlanets = {samh, rocinante, aegir};
//        samh.calcNetForceExertedByX(allPlanets);
//        samh.calcNetForceExertedByY(allPlanets);
        double netForceX = 0;
        for (Planet p: allPlanets){
            if (this.equals(p)){
                continue;
            }
            netForceX += this.calcForceExertedByX(p);
        }
        return netForceX;
    }

    public double calcNetForceExertedByY(Planet[] allPlanets){
        double netForceY = 0;
        for (Planet p: allPlanets){
            if (this.equals(p)){
                continue;
            }
            netForceY += this.calcForceExertedByY(p);
        }
        return netForceY;
    }

    public void update(double dt, double fX, double fY){
        double aX = fX/mass;
        double aY = fY/mass;
        xxVel = xxVel + aX*dt;
        yyVel = yyVel + aY*dt;
        xxPos = xxPos + dt*xxVel;
        yyPos = yyPos + dt*yyVel;
    }
}
