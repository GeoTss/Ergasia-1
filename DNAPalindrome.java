import java.util.Scanner;

public class DNAPalindrome {

    static char compliment(char ch){
        switch(ch){
            case 'A': return 'T';
            case 'T': return 'A';
            case 'C': return 'G';
            case 'G': return 'C';
        }
        return ' ';
    }

    static boolean validSeq(String seq){
        for(int i = 0; i < seq.length(); ++i)
            if(compliment(seq.charAt(i)) == ' ')
                return false;
        return true;
    }

    static boolean WatsonCrickComplementedPalindrome(String seq){
        int n = seq.length();
        if(n == 0)
            return true;
        if(n % 2 != 0)
            return false;
        
        StringDoubleEndedQueueImpl<Character> q = new StringDoubleEndedQueueImpl<>();

        int i, j;
        j = n-1;
        i = 0;
        while(i < j){
            q.addLast(compliment(seq.charAt(i)));
            q.addFirst(compliment(seq.charAt(j)));

            if(q.getFirst() != seq.charAt(i) || q.getLast() != seq.charAt(j))
                return false;
            
            ++i;
            --j;
        }
        return true;
    }
    
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        
        String dna = input.nextLine();
        dna = dna.trim();

        if(validSeq(dna))
            System.out.println(WatsonCrickComplementedPalindrome(dna));
        else
            System.out.println("Not valid DNA sequence");

        input.close();
    }
}