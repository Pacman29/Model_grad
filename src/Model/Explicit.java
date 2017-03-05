package Model;

/**
 * Created by pacman29 on 05.03.17.
 */
public class Explicit {
    public static double Calculate(Func f,double prevX, double prevY, double x){
        double step = Math.abs(x - prevX);
        return prevY + step * f.calculate(prevX,prevY);
    }
}

