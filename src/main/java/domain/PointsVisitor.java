package domain;

public class PointsVisitor implements Visitor {

    private final int premiumPoints;
    private final int normalPoints;

    public PointsVisitor(int premiumPoints, int normalPoints) {
        this.premiumPoints = premiumPoints;
        this.normalPoints = normalPoints;
    }


    @Override
    public void visit(User user){
        if (user.getPremium()){
            user.setPuntos(user.getPuntos() + premiumPoints);
        }else{
            user.setPuntos(normalPoints);
        }
    }
}
