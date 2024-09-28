package com.example.tttwinnerchecker.strategies;

import com.example.tttwinnerchecker.models.Board;
import com.example.tttwinnerchecker.models.Move;

public class WinningStrategyImpl implements WinningStrategy {
    @Override
    public boolean checkWinner(Board board, Move move) {
        //Implement this method to check if the current move is winning move or not.
        return true;
    }
}
