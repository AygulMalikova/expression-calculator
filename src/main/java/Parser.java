import AST.Expression;
import AST.expression_components.binary.relation.Equal;
import AST.expression_components.binary.relation.Greater;
import AST.expression_components.binary.relation.Less;
import AST.expression_components.binary.term.Minus;
import AST.expression_components.binary.factor.Multiplication;
import AST.expression_components.binary.term.Plus;
import AST.expression_components.primary.Int;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

public class Parser {
    private InputStreamReader input;
    private static final char EOF = (char) -1;
    private char current;
    private boolean endOdFile = false;

    public Parser(InputStream input) {
        this.input = new InputStreamReader(input, Charset.forName("UTF-8"));
    }

    //starting point of the program
    public Expression parse() throws IOException {
        Character character;
        if ((character = readSymbol()) != null) {
            this.current = character; //setting first char as current
        }
        else {
            return null;
        }
        return parseRelation();
    }

    private Expression parseRelation() throws IOException {
        Expression result = parseTerm();
        if (result == null) {
            return null;
        }
        //reading relation characters until stopping criteria (or until end of the file)
        while ( !endOdFile ) {
            Character temp = current;
            if (current == '<' || current == '>' || current == '=') {
                Character character;
                if ((character = readSymbol()) == null) {
                    return result;
                }
                current = character;
                //reading the right part of expression if it exists
                Expression right = parseTerm();
                if (right == null) {
                    return result;
                }
                if (temp == '<')
                    result = new Less(result,right);
                else if (temp == '>')
                    result = new Greater(result,right);
                else
                    result = new Equal(result,right);
            }
            else
                break;
        }
        return result;
    }

    private Expression parseTerm() throws IOException {
        Expression result = parseFactor();
        if (result == null) {
            return null;
        }
        //reading terms (+/-) until stopping criteria (or until end of the file)
        while ( !endOdFile ) {
            Character temp = current;
            if (current == '-' || current == '+') {
                Character character;
                if ((character = readSymbol()) == null) {
                    return result;
                }
                current = character;
                Expression right = parseFactor();
                if (right == null) {
                    return result;
                }
                if (temp == '-')
                    result = new Minus(result,right);
                else
                    result = new Plus(result,right);
            }
            else
                break;
        }
        return result;
    }

    private Expression parseFactor() throws IOException {
        Expression result = parsePrimary();
        if (result == null) {
            return null;
        }
        //reading factor (*) until stopping criteria (or until end of the file)
        while ( !endOdFile ) {
            if (current == '*') {
                Character character;
                if ((character = readSymbol()) == null) {
                    return result;
                }
                current = character;
                Expression right = parsePrimary();
                if (right == null) {
                    return result;
                }
                result = new Multiplication(result,right);
            }
            else
                break;
        }
        return result;
    }

    private Expression parsePrimary() throws IOException {
        Expression result = null;

        // firstly, we need to check whether current char is digit or opening parenthesis
        if (Character.isDigit(current) ) {
            result = parseInteger(Character.getNumericValue(current));
        }

        else if ( current == '(' ) {
            result = parse();
            skipNextChar();
        }
        return result;

    }
    private Expression parseInteger(int firstDigit) throws IOException {
        Expression result = null;
        String number = Integer.toString(firstDigit);
        Character character;
        //reading the char until they are digits and build the string
        while ((character = readSymbol()) != null && Character.isDigit(character)) {
            number = number.concat(String.valueOf(character));
        }
        //if there is more characters then assign new one to current
        if (character != null)
            current = character;

        return new Int(Integer.parseInt(number));

    }
    private void skipNextChar() throws IOException {
        Character op;
        //skipping the closing parenthesis
        while ((op = readSymbol()) != null && op == ')') {
            current = op;
        }
        if (op != null)
            current = op;
    }

    private Character readSymbol() throws IOException {
        //reading the stream of chars from input file
        char nextChar = (char) input.read();
        if (nextChar == EOF) {
            endOdFile = true;
            return null;
        }
        return nextChar;
    }
}
