package scanner.type;

/**
 * Created by Alireza on 2015-05-26.
 */
public enum Type {
    KEYWORDS("class|extends|public|static|void|return|main|boolean|int|if|else|while|true|false|System.out.println"),
    COMMENT("(/\\*([^*]|[\\r\\n]|(\\*+([^*/]|[\\r\\n])))*\\*+/)|//[^\\s\\r\\n]*"),
    ID("[A-Za-z][A-Za-z0-9]*"),
    ErrorID("[0-9]+[A-Za-z]+[A-Za-z0-9]*"),
    NUM("-?[0-9]+"),
    ARITHMATICOP("[*|+|-]"),
    //WHITESPACES("(\\s)+"),
    COMMA(","),
    RELOP("==|<"),
    ASSIGNMENTOP("="),
    LOGICALOP("&&"),

    SEMICOLON(";"),
    CLOSINGP("\\)"),
    OPENINGP("\\("),
    OPENINGCB("\\{"),
    CLOSINGCB("\\}"),
    DOT("\\."),
    EOF("\\$");

    public final String pattern;

    Type(String pattern) {
        this.pattern = pattern;
    }
}
