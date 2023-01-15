package cs445.a2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StreamTokenizer;
import java.io.StringReader;

import cs445.stack.ArrayStack;
import cs445.stack.StackInterface;

/**
 * This class uses two stacks to evaluate an arithmetic expression written in
 * infix notation. The expression is read from a {@link java.io.Reader} object
 * and is tokenized by a {@link java.io.StreamTokenizer}. For convenience,
 * constructors are provided to initialize based on a {@link java.lang.String}
 * containing the expression or an {@link java.io.InputStream} from which to
 * read the expression.
 *
 * This class should not convert the entire expression to postfix first; it
 * should convert and evaluate in a single pass (like in evaluateInfix from the
 * text).
 */
public class Calculator {
    // This tokenizes (breaks up) our input into pieces that can be handled
    StreamTokenizer tokenizer;

    // Stacks for operators (like in convertToPostfix from the text) and
    // operands (like in evaluatePostfix from the text)
    StackInterface<Character> operatorStack;
    StackInterface<Double> operandStack;

    /**
     * Initializes the two-stack calculator using a String representing the
     * expression
     * @param expression the expression to be evaluated
     */
    public Calculator(String expression) {
        this(new StringReader(expression));
    }

    /**
     * Initializes the two-stack calculator to read an expression from an
     * InputStream
     * @param input the input stream from which to read the expression
     */
    public Calculator(InputStream input) {
        this(new BufferedReader(new InputStreamReader((input))));
    }

    /**
     * Initializes the two-stack calculator to read an expression from a Reader
     * @param input the reader from which to read the expression
     */
    public Calculator(Reader input) {
        // Initialize the tokenizer to read from the given stream
        tokenizer = new StreamTokenizer(input);

        // By default, StreamTokenizer considers '-' and '/' to have special
        // meanings based on "regular expressions". Below, we change these to
        // "ordinary characters," so that they can be parsed as operators
        tokenizer.ordinaryChar('-');
        tokenizer.ordinaryChar('/');

        // Allow the tokenizer to use end-of-line for the end of the expression
        tokenizer.eolIsSignificant(true);

        // Initialize the two stacks
        operatorStack = new ArrayStack<Character>();
        operandStack = new ArrayStack<Double>();
        
    }

    /**
     * Evaluates the expression read from the provided stream, then returns the
     * resulting double value
     * @return the value of the expression that was parsed
     */
    public Double evaluate() throws InvalidExpressionException {
        // Try to get the first token. If an IO exception occurs, replace it
        // with a runtime exception, causing an immediate crash. This is bad
        // error-checking, but this shouldn't happen in normal situations.
        try {
            tokenizer.nextToken();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        String M_opr="not opr";
        String M_ob=" not ob";
        String M_cb=" not cb";
        String M_num="not num";
        boolean start=true;
        // Process tokens until we find end-of-line or end-of-stream character
        while (tokenizer.ttype != StreamTokenizer.TT_EOL &&
                        tokenizer.ttype != StreamTokenizer.TT_EOF) {
            
            
            // Which type of token is this?
            switch (tokenizer.ttype) {
                case StreamTokenizer.TT_NUMBER:
                    // If the token is a number, handle it as a double-valued
                    // operand
                    start=false;
                    M_ob="not ob";
                    M_opr="not opr";
                    if(M_cb.equals("cb")){throw new InvalidExpressionException("Closed bracket can't be followed by number.");}
                    handleOperand((double)tokenizer.nval);
                    if(M_num.equals("num")){throw new InvalidExpressionException("number can't be followed by number.");}
                    M_num="num";
                    break;
                case '+':
                case '-':
                case '*':
                case '/':
                case '^':
                    // If the token is any of the above characters, handle it is
                    // an operator
                    if (M_opr.equals("opr")){throw new InvalidExpressionException("Operator +-*/^ can't be followed by +-*/^ " );}
                    M_opr="opr";
                    if(start){throw new InvalidExpressionException("+-*/^ can't at the beginning");}
                    if (M_ob.equals("ob")){throw new InvalidExpressionException("Open brackets can't be followed by +-*/^");}
                    M_ob="not ob";
                    M_cb="not cb";
                    M_num="not num";
                    handleOperator((char)tokenizer.ttype);
                    
                    break;
                case '(':
                case '[':
                    // If the token is open bracket, handle it as such. Forms of
                    // bracket mean the same thing, but must nest properly.
                    M_opr="not opr";
                    start=false;
                    M_ob="ob";
                    if(M_cb.equals("cb")){throw new InvalidExpressionException("Closed bracket can't be followed by open bracket.");}
                    handleOpenBracket((char)tokenizer.ttype);
                    if(M_num.equals("num")){throw new InvalidExpressionException("number can't be followed by open bracket.");}
                    break;
                case ')':
                case ']':
                    // If the token is close bracket, handle it as such. Forms
                    // of bracket mean the same thing, but must nest properly.
                    if (M_opr.equals("opr")) {throw new InvalidExpressionException("operator +-*/^ can't be followed by close bracket " +
                                    tokenizer.sval);}
                    M_opr="not opr";
                    if(start){throw new InvalidExpressionException("closed bracket can't at the beginning");}
                    if (M_ob.equals("ob")){throw new InvalidExpressionException("Open bracket can't be followed by close bracket");}
                    M_ob="not ob";
                    M_cb="cb";
                    M_num="not num";
                    handleCloseBracket((char)tokenizer.ttype);
                    
                    break;
                case StreamTokenizer.TT_WORD:
                    // If the token is a word, throw an expression error (none
                    // of our tokens should be words)
                    throw new InvalidExpressionException("Unrecognized symbol: " +
                                    tokenizer.sval);
                default:
                    // If the token is anything else, throw an expression error
                    throw new InvalidExpressionException("Unrecognized symbol: " +
                                    String.valueOf((char)tokenizer.ttype));
            }

            // Read the next token, converting any potential IO exception as
            // above
            try {
                tokenizer.nextToken();
            } catch(IOException e) {
                throw new RuntimeException(e);
            }
        }

        // Last step: we may have operators in the operators stack to handle,
        // still
        handleRemainingOperators();

        // Return the result of the evaluation
        // TODO: Fix this return statement
        return operandStack.pop();
    }

    /**
     * This method is called by evaluate() when it encounters an operand. It
     * should handle the operand according to the combined "convert and
     * evaluate" algorithm.
     * @param operand the operand that was encountered
     */
    void handleOperand(double operand) {
        // TODO: Complete this method
        operandStack.push(operand);
    }

    /**
     * This method is called by evaluate() when it encounters an operator. It
     * should handle the operator according to the combined "convert and
     * evaluate" algorithm.
     * @param operator the operator that was encountered
     */
    void handleOperator(char operator) {
        // TODO: Complete this method
        
        switch(operator){
            
            case '+':
            case '-':
                while(!(operatorStack.isEmpty()||operatorStack.peek()=='['||operatorStack.peek()=='(')){
                    double ope1=operandStack.pop();
                    double ope2=operandStack.pop();
                    char opr=operatorStack.pop();
                    double result=0;
                    switch (opr){
                        case ('+'):
                            result=ope2+ope1;
                            break;
                        case('-'):
                            result=ope2-ope1;
                            break;
                        case('*'):
                            result=ope2*ope1;
                            break;
                        case('/'):
                            result=ope2/ope1;
                            break;
                        case('^'):
                            result=Math.pow(ope2,ope1);
                            break;
                    }
                    operandStack.push(result);
                }
                operatorStack.push(operator);
                break;
            case '*':
            case '/':
                while((!operatorStack.isEmpty())&&( operatorStack.peek()=='*'||operatorStack.peek()=='/'||operatorStack.peek()=='^')){
                    double ope1=operandStack.pop();
                    double ope2=operandStack.pop();
                    char opr=operatorStack.pop();
                    double result=0;
                    switch (opr){
                        case('*'):
                            result=ope2*ope1;
                            break;
                        case('/'):
                            result=ope2/ope1;
                            break;
                        case('^'):
                            result=Math.pow(ope2,ope1);
                            break;
                    }
                    operandStack.push(result);
                }
                operatorStack.push(operator);
                break;
            case '^':
                while(!operatorStack.isEmpty()&&(operatorStack.peek()=='^')){
                    double ope1=operandStack.pop();
                    double ope2=operandStack.pop();
                    operatorStack.pop();
                    double result=Math.pow(ope2,ope1);
                    operandStack.push(result);
                }       
                operatorStack.push(operator);
                break;
        }
    }

    /**
     * This method is called by evaluate() when it encounters an open bracket.
     * It should handle the open bracket according to the combined "convert and
     * evaluate" algorithm.
     * @param openBracket the open bracket that was encountered
     */
    void handleOpenBracket(char openBracket) {
        // TODO: Complete this method
        operatorStack.push(openBracket);
    }

    /**
     * This method is called by evaluate() when it encounters a close bracket.
     * It should handle the close bracket according to the combined "convert and
     * evaluate" algorithm.
     * @param closeBracket the close bracket that was encountered
     */
    void handleCloseBracket(char closeBracket) {
        // TODO: Complete this method
        double result=0;
        while(!(operatorStack.peek()=='['||operatorStack.peek()=='(')){
            double ope1=operandStack.pop();
            double ope2=operandStack.pop();
            char opr=operatorStack.pop();
                switch (opr){
                    case ('+'):
                        result=ope2+ope1;
                        break;
                    case('-'):
                        result=ope2-ope1;
                        break;
                    case('*'):
                        result=ope2*ope1;
                        break;
                    case('/'):
                        result=ope2/ope1;
                        break;
                    case('^'):
                        result=Math.pow(ope2,ope1);
                        break;
                }
                operandStack.push(result);
                        
                    
        }
        char open=operatorStack.pop();
        if((open=='['&&closeBracket==')')||(open=='('&&closeBracket==']')){throw new InvalidExpressionException("Bracket doesn't nest correctly");}


    }

    /**
     * This method is called by evaluate() when it encounters the end of the
     * expression. It should handle any remaining operators on the stack
     * according to the combined "convert and evaluate" algorithm.
     */
    void handleRemainingOperators() {
        // TODO: Complete this method
        while(!operatorStack.isEmpty()){
            double result=0;
            double ope1=operandStack.pop();
            double ope2=operandStack.pop();
            char opr=operatorStack.pop();
            switch (opr){
                case ('+'):
                    result=ope2+ope1;
                    break;
                case('-'):
                    result=ope2-ope1;
                    break;
                case('*'):
                    result=ope2*ope1;
                    break;
                case('/'):
                    result=ope2/ope1;
                    break;
                case('^'):
                    result=Math.pow(ope2,ope1);
                    break;
            }
            operandStack.push(result);
        }
    }
}

