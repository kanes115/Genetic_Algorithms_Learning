import java.util.concurrent.TimeUnit;

/**
 * Created by Kanes on 04.02.2017.
 */
public class Evolution {
    private Population currentPop;
    private Member target;

    public Evolution(String target, double mutationRate){
        this.target = new Member(target);
        this.currentPop = new Population(500, target.length(), this.target, mutationRate);
    }

    public void run(){
        int amountOfGen = 0;
        Member currentLeader = currentPop.getMostFittingMember();

        while(!currentLeader.equals(target)) {
            amountOfGen++;
            System.out.println("Best fitted phrase: " + currentLeader.toString());
            System.out.println("His fitting: " + (getParcentageFittness(currentPop.getMaxFitting(), target.fittness(target))) + "%");
            System.out.println("Total generations: " + amountOfGen);
            //waitSec();
            currentPop.nextGeneration();
            currentLeader = currentPop.getMostFittingMember();
        }

        System.out.println("Best fitted phrase: " + currentLeader.toString());
        System.out.println("His fitting: " + (getParcentageFittness(currentPop.getMaxFitting(), target.fittness(target))) + "%");
        System.out.println("Total generations: " + amountOfGen);

    }

    private void waitSec(){
        try{
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private int getParcentageFittness(int fitting, int target){

        double fittD = (double) fitting;
        double targetD = (double) target;

        double res = fittD / targetD;

        res = res * 100;

        return (int) res;
    }


}
