package com.mycompany.trabahlo;

import java.util.Scanner;
import java.util.Stack;

public class Trabahlo {

    public static boolean verifica(char c) {
        if (Character.isLetterOrDigit(c)) {
            return true;
        }
        return false;
    }

    public static boolean verifica2(char c) {
        if (Character.isLetterOrDigit(c)) {
            return false;
        }
        return true;
    }

    public static int verificaOrdemOperador(char op) {
        if (op == '+' || op == '-') {
            return 1;
        } else if (op == '*' || op == '/') {
            return 2;
        } else if (op == '^') {
            return 3;
        } else {
            return -1;
        }
    }

    public static boolean operadores(char op) {
        if (op == '+' || op == '-' || op == '/' || op == '*') {
            return true;
        } else {
            return false;
        }
    }

    public static String infixaPosFixa(String equacao) {
        Stack<Character> pilha = new Stack<>();
        String equacaoPosFixa = ("");

        for (int i = 0; i < equacao.length(); ++i) {
            char c = equacao.charAt(i);
            if (verifica(c)) {
                equacaoPosFixa += c;
            } else if (c == '(') {
                pilha.push(c);
            } else if (c == ')') {
                while (!pilha.isEmpty() && pilha.peek() != '(') {
                    equacaoPosFixa += pilha.pop();
                }
                pilha.pop();
            } else {
                while (!pilha.isEmpty() && verificaOrdemOperador(c) <= verificaOrdemOperador(pilha.peek()) && operadores(c)) {
                    equacaoPosFixa += pilha.pop();
                }
                pilha.push(c);
            }
        }

        while (!pilha.isEmpty()) {
            if (pilha.peek() == '(') {
                return "Essa expressao é invalida";
            }
            equacaoPosFixa += pilha.pop();
        }
        return equacaoPosFixa;
    }

    public static String infixaPreFixa(String equacao) {
        Stack<Character> pilha = new Stack<>();
        String equacaoPreFixa = ("");

        for (int i = equacao.length() - 1; i >= 0; i--) {
            char c = equacao.charAt(i);
            if (verifica2(c)) {
                equacaoPreFixa += c;
            } else if (c == ')') {
                pilha.push(c);
            } else if (c == '(') {
                while (!pilha.isEmpty() && pilha.peek() != '(') {
                    equacaoPreFixa += pilha.pop();
                }
                pilha.pop();
            } else {
                while (!pilha.isEmpty() && verificaOrdemOperador(c) <= verificaOrdemOperador(pilha.peek()) && operadores(c)) {
                    equacaoPreFixa += pilha.pop();
                }
                pilha.push(c);
            }
        }

        while (!pilha.isEmpty()) {
            if (pilha.peek() == '(') {
                return "Essa expressao é invalida";
            }
            equacaoPreFixa += pilha.pop();
        }
        return equacaoPreFixa;
    }

    public static String posFixainfixa(String posFixa) {
        Stack<String> pilha = new Stack<>();

        // Percorre a expressão posfixa
        for (int i = 0; i < posFixa.length(); i++) {
            char c = posFixa.charAt(i);
            if (operadores(c)) {
                String operand2 = pilha.pop();
                String operand1 = pilha.pop();
                pilha.push("(" + operand1 + c + operand2 + ")");
            } else {
                pilha.push(c + "");
            }
        }
        return pilha.pop();
    }

    public static String posFixaPreFixa(String postfix) {
        Stack<String> pilha = new Stack<>();

        // Percorre a expressão posfixa
        for (int i = 0; i < postfix.length(); i++) {
            char c = postfix.charAt(i);
            if (operadores(c)) {
                // Pop os dois operandos da pilha
                String operand2 = pilha.pop();
                String operand1 = pilha.pop();
                // Forma a expressão prefixa
                String expr = c + operand1 + operand2;
                // Empilha a expressão prefixa formada
                pilha.push(expr);
            } else {
                // Se for um operando, apenas empilha
                pilha.push(c + "");
            }
        }

        // O topo da pilha contém a expressão prefixa final
        return pilha.pop();
    }

    public static String preFixoInfixo(String prefix) {
        Stack<String> pilha = new Stack<>();

        // Percorre a expressão prefixa da direita para a esquerda
        for (int i = prefix.length() - 1; i >= 0; i--) {
            char c = prefix.charAt(i);
            if (operadores(c)) {
                // Pop os dois operandos da pilha
                String operand1 = pilha.pop();
                String operand2 = pilha.pop();
                // Forma a expressão infixa
                String expr = "(" + operand1 + c + operand2 + ")";
                // Empilha a expressão infixa formada
                pilha.push(expr);
            } else {
                // Se for um operando, apenas empilha
                pilha.push(c + "");
            }
        }

        // O topo da pilha contém a expressão infixa final
        return pilha.pop();
    }
    
    public static String preFixoPosFixo(String prefix) {
        Stack<String> stack = new Stack<>();

        // Percorre a expressão prefixa da direita para a esquerda
        for (int i = prefix.length() - 1; i >= 0; i--) {
            char c = prefix.charAt(i);
            if (operadores(c)) {
                // Pop os dois operandos da pilha
                String operand1 = stack.pop();
                String operand2 = stack.pop();
                // Forma a expressão posfixa
                String expr = operand1 + operand2 + c;
                // Empilha a expressão posfixa formada
                stack.push(expr);
            } else {
                // Se for um operando, apenas empilha
                stack.push(c + "");
            }
        }

        // O topo da pilha contém a expressão posfixa final
        return stack.pop();
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        String equacao;
        System.out.println("Qual tipo de notacao vai escrever?\n"
                + "[1]- infixa\n"
                + "[2]- pos-fixa\n"
                + "[3]- pre-fixa");
        int resposta = scan.nextInt();
        scan.nextLine();
        switch (resposta) {
            case 1:
                System.out.println("Digite sua notacao infixa:");
                equacao = scan.nextLine();
                System.out.println("notacao infixa: " + equacao);
                System.out.println("notacao pos-fixa: " + infixaPosFixa(equacao));
                System.out.println("notacao pre-fixa: " + infixaPreFixa(equacao));
                break;
                
            case 2:
                System.out.println("Digite sua notacao pos-fixa:");
                equacao = scan.nextLine();
                System.out.println("notacao pos-fixa: " + equacao);
                System.out.println("notacao infixa: " + posFixainfixa(equacao));
                System.out.println("notacao pre-fixa: " + posFixaPreFixa(equacao));
                break;

            case 3:
                System.out.println("Digite sua notacao pre-fixa:");
                equacao = scan.nextLine();
                System.out.println("notacao pre-fixa: " + equacao);
                System.out.println("notacao infixa: "+preFixoInfixo(equacao));
                System.out.println("notacao pos-fixa: "+preFixoPosFixo(equacao));
                break;
        }
    }
}