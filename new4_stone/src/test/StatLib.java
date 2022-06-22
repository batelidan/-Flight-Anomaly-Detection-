package test;


public class StatLib {

	
// simple average
public static float avg(float[] x){
	float sum=0;
	for(int i=0;i<x.length;i++)
		sum+=x[i];

	return sum/x.length;
}

// returns the variance of X and Y
public static float var(float[] x){
	float sum=0;
	float avgX=avg(x);
	for(int i=0;i<x.length;i++)
		sum+=Math.pow(x[i],2);
	return (float)(sum/x.length)-(float)(Math.pow(avgX,2));

}
 
	// returns the covariance of X and Y
	public static float cov(float[] x, float[] y){
float sum=0;
float avgX=avg(x);
float avgY=avg(y);
for(int i=0;i<x.length;i++)
{
sum+=(x[i]-avgX)*(y[i]-avgY);
}
	return sum/x.length;
	}


	// returns the Pearson correlation coefficient of X and Y
	public static float pearson(float[] x, float[] y){
	float covXY=cov(x,y);
	float sX=var(x);
	float sY=var(y);
	Float totalS=(float)(Math.sqrt(sX)*Math.sqrt(sY));
	return covXY/totalS;
	}

	// performs a linear regression and returns the line equation
	public static Line linear_reg(Point[] points){
		float _x[]=new float[points.length];
		float _y[]=new float[points.length];
		for(int i=0;i<points.length;i++)
		{
			_x[i]=points[i].x;
			_y[i]=points[i].y;
	
		}
		float covXY=cov(_x,_y);
		float varX=var(_x);
		float a=covXY/varX;
		float avgX=avg(_x);
		float avgY=avg(_y);
		float b=avgY-a*avgX;
	
		Line line=new Line(a,b);
		return line;
		
	}
	

	// returns the deviation between point p and the line equation of the points
	public static float dev(Point p,Point[] points){
		Line Y=linear_reg(points);
		float F=Y.f(p.x);
		float  deviation=F-p.y;
		return Math.abs(deviation);
	}

	// returns the deviation between point p and the line
	public static float dev(Point p,Line l){
		float F=l.f(p.x);
		float  deviation=F-p.y;
		return Math.abs(deviation);
	}
	
}
