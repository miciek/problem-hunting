package com.michalplachta.hunting.uva.a10258;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

/**
 * UVA 10258 - Contest Scoreboard problem solution
 * 
 * http://uva.onlinejudge.org/index.php?option
 * =com_onlinejudge&Itemid=8&category=24&page=show_problem&problem=1199
 * 
 * @author micio
 */
class Main {
    private final static int MAX_CONTESTANTS = 100;
    private final static int MAX_PROBLEMS = 9;
    private final Problem[][] contestantsProblems;
    
    public Main() {
        contestantsProblems = new Problem[MAX_CONTESTANTS + 1][];
    }
    
    public List<ContestantStats> getRanking() {
        List<ContestantStats> ranking = new ArrayList<ContestantStats>(MAX_CONTESTANTS);
        for (int i = 1; i <= MAX_CONTESTANTS; i++) {
            ContestantStats cs = getContestantStats(i);
            if (cs != null) {
                ranking.add(cs);
            }
        }
        
        Collections.sort(ranking);
        
        return ranking;
    }
    
    public String[] getRankingTestable() {
        List<ContestantStats> result = getRanking();
        String[] strings = new String[result.size()];
        int i = 0;
        for (ContestantStats cs: result) {
            strings[i++] = cs.toString();
        }
        
        return strings;
    }
    
    public void addEntry(int contestantId, int problemId, int time, char L) {
        Problem[] problems = getContestantProblems(contestantId);
        
        if (problems[problemId] == null) {
            problems[problemId] = new Problem();
        }
        
        if (problems[problemId].solved) {
            return;
        }
        
        if (L == 'C') { // correct
            problems[problemId].addSubmission(true, time);
        } else if (L == 'I') { // incorrect
            problems[problemId].addSubmission(false, time);
        }
    }
    
    private Problem[] getContestantProblems(int contestantId) {
        if (contestantsProblems[contestantId] == null) {
            contestantsProblems[contestantId] = new Problem[MAX_PROBLEMS + 1];
        }
        return contestantsProblems[contestantId];
    }
    
    // returns object representing contestant statistics or null, if no submission
    private ContestantStats getContestantStats(int contestantId) {
        if (contestantsProblems[contestantId] != null) {
            return new ContestantStats(contestantId, contestantsProblems[contestantId]);
        }
        
        return null;
    }
    
    private static class Problem {
        boolean solved = false;
        int submissions = 0;
        int timeSolved = 0;
        
        int getAccumulatedTime() {
            if (solved) {
                return timeSolved + 20 * (submissions - 1);
            } else {
                return 0;
            }
        }
        
        void addSubmission(boolean correct, int time) {
            if (!solved) {
                submissions++;
                if (correct) {
                    solved = true;
                    timeSolved = time;
                }
            }
        }
    }
    
    private static class ContestantStats implements Comparable<ContestantStats> {
        private final int id;
        private int solved;
        private int penalty;
        
        private ContestantStats(int contestantId, Problem[] contestantProblems) {
            id = contestantId;
            solved = 0;
            penalty = 0;
            for (Problem problem: contestantProblems) {
                if (problem != null && problem.solved) {
                    solved++;
                    penalty += problem.getAccumulatedTime();
                }
            }
        }
        
        @Override
        public String toString() {
            return String.format("%d %d %d", id, solved, penalty);
        }
        
        @Override
        public int compareTo(ContestantStats o) {
            if (solved != o.solved) {
                return o.solved - solved;
            }
            
            if (penalty != o.penalty) {
                return penalty - o.penalty;
            }
            
            return id - o.id;
        }
    }
    
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int N = in.nextInt();
        in.nextLine();
        in.nextLine();
        while (N > 0) {
            Main m = new Main();
            String entry;
            while(in.hasNextLine() && !(entry = in.nextLine()).isEmpty()) {
                String[] tokens = entry.split(" ");
                m.addEntry(Integer.parseInt(tokens[0]), Integer.parseInt(tokens[1]), Integer.parseInt(tokens[2]), tokens[3].toCharArray()[0]);
            }
            
            for(ContestantStats s : m.getRanking()) {
                System.out.println(s);
            }
            
            N--;
            if(N > 0) {
                System.out.println();
            }
        }
        in.close();
    }
}
