import java.util.Random;

/**
 * Every gene is a certain character.
 */
public class Gene {

    private char g;

    public Gene(){
        randomGene();
    }

    public Gene(char c){
        this.g = c;
    }


    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        Gene other = (Gene) obj;
        return other.g == this.g;
    }


    private void randomGene(){
        Random generator = new Random();
        if(generator.nextInt(27) == 26){
            this.g = Character.toChars(32)[0];
            return;
        }
        this.g = Character.toChars(generator.nextInt(26) + 97)[0];
    }

    public String toString(){
        return String.valueOf(g);
    }
}
