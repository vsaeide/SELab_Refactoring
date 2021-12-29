package parser;

import scanner.token.Token;

import java.util.*;

/**
 * Created by mohammad hosein on 6/25/2015.
 */
public class ParseTable {
    private static ArrayList<Map<Token, Action>> actionTable;
    private static ArrayList<Map<NonTerminal, Integer>> gotoTable;

    static ParseTable create(String jsonTable) throws Exception {
        jsonTable = jsonTable.substring(2, jsonTable.length() - 2);
        String[] rows = jsonTable.split("\\],\\[");
        Map<Integer, Token> terminals = new HashMap<Integer, Token>();
        Map<Integer, NonTerminal> nonTerminals = new HashMap<Integer, NonTerminal>();
        rows[0] = rows[0].substring(1, rows[0].length() - 1);
        String[] cols = rows[0].split("\",\"");
        for (int i = 1; i < cols.length; i++) {
            if (cols[i].startsWith("Goto")) {
                String temp = cols[i].substring(5);
                try {
                    nonTerminals.put(i, NonTerminal.valueOf(temp));
                } catch (Exception ignored) {
                }
            } else {
                terminals.put(i, new Token(Token.getTyepFormString(cols[i]), cols[i]));
            }
        }
        actionTable = new ArrayList<Map<Token, Action>>();
        gotoTable = new ArrayList<Map<NonTerminal, Integer>>();
        for (int i = 1; i < rows.length; i++) {

            rows[i] = rows[i].substring(1, rows[i].length() - 1);
            cols = rows[i].split("\",\"");
            actionTable.add(new HashMap<Token, Action>());
            gotoTable.add(new HashMap<>());
            for (int j = 1; j < cols.length; j++) {
                if (!cols[j].equals("")) {
                    if (cols[j].equals("acc")) {
                        actionTable.get(actionTable.size() - 1).put(terminals.get(j), new Action(Act.ACCEPT, 0));
                    } else if (terminals.containsKey(j)) {
                        Token t = terminals.get(j);
                        Action a = new Action(cols[j].charAt(0) == 'r' ? Act.REDUCE : Act.SHIFT, Integer.parseInt(cols[j].substring(1)));
                        actionTable.get(actionTable.size() - 1).put(t, a);

                    } else if (nonTerminals.containsKey(j)) {
                        gotoTable.get(gotoTable.size() - 1).put(nonTerminals.get(j), Integer.parseInt(cols[j]));
                    } else {
                        throw new Exception();
                    }
                }
            }
        }
        return new ParseTable();
    }

    public int getGotoTable(int currentState, NonTerminal variable) {
        return gotoTable.get(currentState).get(variable);
    }

    public Action getActionTable(int currentState, Token terminal) {
        return actionTable.get(currentState).get(terminal);
    }

}
