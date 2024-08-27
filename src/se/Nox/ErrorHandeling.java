package se.liu.noaan869.LabbTetris;

import java.io.IOException;

public class ErrorHandeling {
    private HighscoreList hslist;

    private TetrisViewer tetrisViewer;

    public ErrorHandeling(HighscoreList hslist) {
        this.hslist = hslist;
        this.tetrisViewer = new TetrisViewer();
    }

    public void errorCreateFile(boolean newFile) {
        if(!newFile) {
            if(this.tetrisViewer.showErrorMessage("failed to create new file!")) {
                this.hslist.readDataFromHScoreFile();
            }else {
                System.exit(1);
            }
        }
    }

    public void errorIOException(IOException ioException) {
        if(this.tetrisViewer.showErrorMessage(ioException.getMessage())) {
            this.hslist.readDataFromHScoreFile();
        }else {
            System.exit(1);
        }
        ioException.printStackTrace();
    }

    public void errorDeleteFile(boolean deleted) {
        if (!deleted) {
            this.tetrisViewer.showErrorMessage("file could not be deleted!");
        }

    }

    public void errorRenameFile(boolean flag) {
        if (!flag) {
            this.tetrisViewer.showErrorMessage("file could not be renamed/moved!");
        }
    }

}