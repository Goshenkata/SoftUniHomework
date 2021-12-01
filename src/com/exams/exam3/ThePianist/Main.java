package com.exams.exam3.ThePianist;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = Integer.parseInt(scanner.nextLine());
        PieceMap pieceMap = new PieceMap(new ArrayList<>());
        for (int i = 0; i < n; i++) {
            String[] inputs = scanner.nextLine().split("\\|");
            String piece = inputs[0];
            String composer = inputs[1];
            String key = inputs[2];
            pieceMap.put(new Piece(piece, composer, key));
        }
        String input = scanner.nextLine();
        while (!input.equals("Stop")) {
            String command = input.split("\\|")[0];
            String piece = input.split("\\|")[1];
            switch (command) {
                case "Add":
                    String composer = input.split("\\|")[2];
                    String key = input.split("\\|")[3];
                    if (pieceMap.contains(piece)) {
                        System.out.printf("%s is already in the collection!%n", piece);
                    } else {
                        System.out.printf("%s by %s in %s added to the collection!%n", piece, composer, key);
                        pieceMap.put(new Piece(piece, composer, key));
                    }
                    break;
                case "Remove":
                    if (pieceMap.contains(piece)) {
                        System.out.printf("Successfully removed %s!%n", piece);
                        pieceMap.remove(piece);
                    } else System.out.printf("Invalid operation! %s does not exist in the collection.%n", piece);
                    break;
                case "ChangeKey":
                    String newKey = input.split("\\|")[2];
                    if (pieceMap.contains(piece)) {
                        System.out.printf("Changed the key of %s to %s!%n", piece, newKey);
                        Piece updatedPiece = pieceMap.get(piece).setKey(newKey);
                        pieceMap.put(updatedPiece);
                    } else System.out.printf("Invalid operation! %s does not exist in the collection.%n", piece);
            }
            input = scanner.nextLine();
        }
        pieceMap.getPieces().stream()
                .sorted(Comparator.comparing(Piece::getPiece).thenComparing(Piece::getComposer))
                .forEach(System.out::println);
    }

    static class PieceMap {
        List<Piece> pieces;

        public List<Piece> getPieces() {
            return pieces;
        }

        public PieceMap(List<Piece> pieces) {
            this.pieces = pieces;
        }

        public boolean contains(String piece) {
            for (Piece p : pieces) {
                if (p.getPiece().equals(piece)) {
                    return true;
                }
            }
            return false;
        }

        public List<Piece> remove(String piece) {
            for (int i = 0; i < piece.length(); i++) {
                if (pieces.get(i).getPiece().equals(piece)) {
                    pieces.remove(i);
                    return pieces;
                }
            }
            throw new IllegalStateException("No piece to remove");
        }

        public List<Piece> put(Piece piece) {
            if (this.contains(piece.getPiece())) {
                this.remove(piece.getPiece());
            }
            pieces.add(piece);
            return pieces;
        }

        public Piece get(String piece) {
            for (Piece p : pieces) {
                if (p.getPiece().equals(piece)) {
                    return p;
                }
            }
            throw new NullPointerException("No piece to call get()");
        }
    }

    static class Piece {
        String piece;
        String composer;
        String key;

        @Override
        public String toString() {
            return String.format("%s -> Composer: %s, Key: %s", piece, composer, key);
        }

        public String getPiece() {
            return piece;
        }

        public Piece setPiece(String piece) {
            this.piece = piece;
            return this;
        }

        public String getComposer() {
            return composer;
        }

        public Piece setComposer(String composer) {
            this.composer = composer;
            return this;
        }

        public String getKey() {
            return key;
        }

        public Piece setKey(String key) {
            this.key = key;
            return this;
        }


        public Piece(String piece, String composer, String key) {
            this.piece = piece;
            this.composer = composer;
            this.key = key;
        }
    }
}
