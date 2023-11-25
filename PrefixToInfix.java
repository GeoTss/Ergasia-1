import java.util.Scanner;

public class PrefixToInfix {

    public static boolean isNum(Character ch) {
        return (ch >= '1' && ch <= '9');
    }

    public static boolean isOperation(Character ch) {
        return (ch == '+' || ch == '-' || ch == '*' || ch == '/');
    }

    public static int getPriority(Character ch) {
        switch (ch) {
            case '-':
            case '+':
                return 0;
            case '*':
            case '/':
                return 1;
        }
        return -1;
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String prefix = input.nextLine();
        input.close();

        StringDoubleEndedQueueImpl<String> queue = new StringDoubleEndedQueueImpl<>();

        boolean isFalse = false;

        for (int i = prefix.length() - 1; i >= 0; --i) {
            Character temp = prefix.charAt(i);
            if (isNum(temp))
                queue.addLast(temp.toString());
            
            else if (isOperation(temp)) {
                if (queue.isEmpty()) {
                    isFalse = true;
                    break;
                }
                String prev_expr1 = queue.removeLast();
                if (queue.isEmpty()) {
                    isFalse = true;
                    break;
                }
                String prev_expr2 = queue.removeLast();
                                
                String expr_concat;
                int currPriority = getPriority(temp);
                
                int nextPriority = -1;
                Character nextPriorityChar = ' ';
                int numsEncountered = 0;
                
                for(int j=i-1; j >= 0; --j){
                    Character _temp = prefix.charAt(j);
                    if(isOperation(_temp)){
                        if(numsEncountered < 2){
                            nextPriority = getPriority(_temp);
                            nextPriorityChar = _temp;
                            break;
                        }
                        else
                            numsEncountered -= 2;
                    }
                    else
                        ++numsEncountered;
                }

                if (nextPriority > currPriority || (nextPriority == 1 && (nextPriorityChar != '*' || temp != '*')))
                    expr_concat = "(" + prev_expr1 + temp + prev_expr2 + ")";
                else
                    expr_concat = prev_expr1 + temp + prev_expr2;

                queue.addLast(expr_concat);
            } else {
                isFalse = true;
                break;
            }
        }
        if (isFalse || queue.size() > 1)
            System.out.println("Incorrect prefix representation.");
        else
            queue.printQueue(System.out);
    }
}