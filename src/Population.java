import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;


public class Population {

    private List<Member> population = new ArrayList<>();
    private int amountOfGenes;
    private Member target;
    private int maxFitting;
    private double mutationRate;


    public Population(int amountOfMembers, int amountOfGenes, Member target, double mutationRate){

        this.amountOfGenes = amountOfGenes;
        this.target = target;
        this.mutationRate = mutationRate;

        for(int i = 0; i < amountOfMembers; i++){
            population.add(new Member(this.amountOfGenes));
        }

        getMostFittingMember();
    }


    public Member getMostFittingMember(){       //również aktualizuje maksymalny fitting
        int maxScore = -1;
        Member maxMem = new Member(3);

        for(Member m : population){
            if(m.fittness(target) > maxScore) {
                maxScore = m.fittness(target);
                maxMem = m;
            }
        }
        this.maxFitting = maxScore;
        return maxMem;
    }

    public int getMaxFitting(){
        return this.maxFitting;
    }


    public void nextGeneration(){

        List<Member> tmp = new ArrayList<>();
        List<Member> matingPool = new ArrayList<>();

        getMostFittingMember();         //potrzebujemy zaktualizować maxFitting

        for(Member m : population){     //tworzymy matingpool
            for(int j = 0; j < normalize(m.fittness(target)); j++){
                matingPool.add(m);
            }
        }


        for(int i = 0; i < population.size(); i++) {
            Member parentA = getRandomMember(matingPool);
            Member parentB = getRandomMember(matingPool);

            tmp.add(parentA.crossover(parentB).mutate(this.mutationRate));
        }

        this.population = tmp;

    }

    private double normalize(int fittness){            // procentowo
        return (int) (((double) fittness) / ((double) maxFitting) * 100);
    }

    private Member getRandomMember(List<Member> members){
        Random ran = new Random();
        return members.get(ran.nextInt(members.size()));
    }
}
