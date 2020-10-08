package calc2;
import java.util.Stack; 


public class EvaluateExp {
    String expression;

    public EvaluateExp(String expression)
    {
        this.expression = expression;
    }

    private static boolean hasPrecedence(char op1, char op2)
    { 
        if (op2 == '(' || op2 == ')'){
            return false; 
        }
        if ((op1 == '*' || op1 == '/') && (op2 == '+' || op2 == '-')){
            return false;
        } 
        return true; 
    }

    private static int applyOperation(char op, int b, int a) 
    { 
        if(op == '+')
            return a+b;
        else if(op == '-')
            return a-b;
        else if(op == '*')
            return a*b;
        else if(op == '/')
        {
            if(b == 0)
                throw new UnsupportedOperationException("Cannot divide by zero");
            else
                return a/b;
        }
        return 0;
    } 

    private boolean isNumber(char c)
    {
        return (c >= '0' && c <= '9');
    }

    public Integer solve()
    {
        if(expression.length() == 0)
            return null;

        char[] tokens = expression.toCharArray();
        int tokenLength = tokens.length;
        char c = tokens[tokenLength-1];

        if(!(c >= '0' && c <= '9')) //If last character is not an Integer, then operation is not allowed
            return null;

        Stack<Integer> values = new Stack<Integer>(); 
        Stack<Character> ops = new Stack<Character>();

        boolean isNeg = false;
        for(int i = 0; i < tokenLength; i++)
        {
            if(tokens[i] == ' ')
            {
                isNeg = false;
                continue;
            }

            if(tokens[i] == '-' && i+1 < tokenLength && isNumber(tokens[i+1]))
                isNeg = true;

            else if( isNumber(tokens[i]) )
            { 
                StringBuffer stringBuff = new StringBuffer();
                while (i < tokenLength && isNumber(tokens[i]))
                    stringBuff.append(tokens[i++]);
                
                if(isNeg)
                    values.push(-Integer.parseInt(stringBuff.toString()));
                else
                    values.push(Integer.parseInt(stringBuff.toString()));
                
                isNeg = false;
            }

            else if(tokens[i] == '+' || tokens[i] == '-' || tokens[i] == '*' || tokens[i] == '/') 
            { 
                while (!ops.empty() && hasPrecedence(tokens[i], ops.peek())){
                    values.push(applyOperation(ops.pop(), values.pop(), values.pop())); 
                }
                ops.push(tokens[i]);
                isNeg = false;
            } 
        }

        while (!ops.empty())
            values.push(applyOperation(ops.pop(), values.pop(), values.pop()));

        return values.pop();
    }
    
}
