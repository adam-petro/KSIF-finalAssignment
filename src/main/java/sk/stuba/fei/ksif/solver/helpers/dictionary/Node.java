package sk.stuba.fei.ksif.solver.helpers.dictionary;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Node {

    private char znak;
    private boolean slovo;
    private List<Node> childs;
    private int length;

    public Node(char znak, boolean slovo, int length) {
        this.znak = znak;
        this.slovo = slovo;
        this.childs = new ArrayList<>();
        this.length = length;
    }

    public char getZnak() {
        return znak;
    }

    public boolean isSlovo() {
        return slovo;
    }

    public void addToChilds(Node next) {
        this.childs.add(next);
    }

    public boolean containsChild(char c) {
        for (Node n : this.childs) {
            if (n.znak == c) {
                return true;
            }
        }
        return false;
    }

    public Node getChild(char c) {
        for (Node n : this.childs) {
            if (n.znak == c) {
                return n;
            }
        }
        return null;
    }

    public static Node loadDictionary(Collection<String> words) {
        Node root = new Node('*', false, 0);
        
        for (String word : words) {
            Node tmp = root;
            int i = 1;
            for (char c : word.toCharArray()) {
                if (tmp.containsChild(c) == false) {
                    Node newNode = new Node(c, false, i);
                    tmp.addToChilds(newNode);
                    tmp = newNode;
                } else {
                    tmp = tmp.getChild(c);
                }
                i++;
            }
            tmp.slovo = true;
        }
        return root;
    }

    public static List<String> readDictionaryWords(String path) {
        File f = new File(path);
        List<String> words = new ArrayList<>();
        if (f.exists()) {
            try {
                BufferedReader br = new BufferedReader(new FileReader(f));
                String line;
                while ((line = br.readLine()) != null) {
                    words.add(line.trim().toLowerCase());
                }
            } catch (FileNotFoundException ex) {
            } catch (IOException ex) {
            }
        }
        return words;
    }

    public double evaluate(String txt, int minLength, int maxLength) {
        double s = 0;
        for (int i = 0; i <= txt.length() - maxLength; i++) {
            String ss = txt.substring(i, i + maxLength);
            Node tmp = this;
            Node maxWord = null;
            
            for (char c : ss.toCharArray()) {
                if (tmp.containsChild(c)) {
                    tmp = tmp.getChild(c);
                    if (tmp.slovo) {
                        maxWord = tmp;
                    }
                } else {
                    break;
                }
            }

            if (maxWord != null && maxWord.length >= minLength) {
                s += maxWord.length;
                i += maxWord.length;
            }
        }
        return s / (double) txt.length();
    }

}

