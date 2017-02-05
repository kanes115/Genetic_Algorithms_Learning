import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 *
 */
public class Member {

    private List<Gene> genes = new ArrayList<>();


    public Member(int length){

        for(int i = 0; i < length; i++){
            genes.add(new Gene());
        }
    }

    public Member(String geneSeq){
        for(int i = 0; i < geneSeq.length(); i++){
            genes.add(new Gene(geneSeq.charAt(i)));
        }
    }


    public int fittness(Member target){      //how many genes are the same, order is important
        int score = 0;

        for(int i = 0; i < genes.size(); i++){
            if(genes.get(i).equals(target.getGene(i)))
                score++;
        }

        return score*score*score*score;
    }

    public Gene getGene(int i){
        return genes.get(i);
    }

    public Member crossover(Member other){          // ważna funkcja, tu wszelkie modyfikacje wskazane
        Random ran = new Random();
        int midpoint = ran.nextInt(this.genes.size() / 2);

        String res = "";

        for(int i = 0; i < midpoint; i++){
            res = res.concat(this.genes.get(i).toString());
        }
        for(int i = midpoint; i < other.genes.size(); i++){
            res = res.concat(other.genes.get(i).toString());
        }

        return new Member(res);
    }

    public Member mutate(double mutationRate){
        Random ran = new Random();
        String res = "";


        for(Gene g : genes){
            if(ran.nextDouble() < mutationRate)
                res = res.concat(new Gene().toString());
            else
                res = res.concat(g.toString());

        }
        return new Member(res);
    }

    public String toString(){
        String res = "";
        for(int i = 0; i < genes.size(); i++){
            res = res.concat(genes.get(i).toString());
        }
        return res;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    public boolean equals(Object obj){
        Member other = (Member) obj;
        return this.genes.equals(other.genes);
    }
}
